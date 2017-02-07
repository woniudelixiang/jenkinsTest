package com.wqj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端
 * @author 小路
 */
public class NIOServer {
	//通道管理器
	private Selector selector;
	int i = 1;

	/**
	 * 获得一个ServerSocket通道，并对该通道做一些初始化的工作
	 * @param port  绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException {
		// 获得一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		
		// 设置通道为非阻塞
		serverChannel.configureBlocking(false);
		
		// 将该通道对应的ServerSocket绑定到port端口
		serverChannel.socket().bind(new InetSocketAddress(port));
		
		// 获得一个通道管理器
		this.selector = Selector.open();
		
		//将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT（服务端接收客户端连接事件）事件,注册该事件后，
		//当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void listen() throws IOException {
		System.out.println("服务端启动成功！");
		// 轮询访问selector
		while (true) {
			//当注册的事件到达时，方法返回；否则,该方法会一直阻塞
			int n = selector.select();
			// 如果感兴趣的时间发生个数n为0，则继续轮询  这个地方会返回的原因有：轮循超时了等
			if(n == 0){
				System.out.println("-------------服务端------------------");
				continue;
			}
		
			// 获得selector中选中的项的迭代器，选中的项为注册的事件---- 获得就绪信道的键迭代器
			Iterator ite = this.selector.selectedKeys().iterator();
			
			while (ite.hasNext()) {
				SelectionKey key = (SelectionKey) ite.next();
				// 删除已选的key,以防重复处理
				ite.remove();
				// 客户端请求连接事件（这种情况是有客户端连接过来了）
				if (key.isAcceptable()) {
					System.out.println("--------------服务器接收到客户端连接----------------" + System.nanoTime());
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					// 获得和客户端连接的通道
					SocketChannel channel = server.accept();
					// 设置成非阻塞
					channel.configureBlocking(false);

					//在这里可以给客户端发送信息哦
					channel.write(ByteBuffer.wrap(new String("向客户端发送第" + (i++) + "条信息"+ System.nanoTime()).getBytes()));
					
					//在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
					channel.register(this.selector, SelectionKey.OP_READ);
					
					// 获得了可读的事件
				} else if (key.isReadable()) {
						read(key);
				}

			}

		}
	}
	/**
	 * 处理读取客户端发来的信息 的事件
	 * @param key
	 * @throws IOException 
	 */
	public void read(SelectionKey key) throws IOException{
		// 获得与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(50);
		  // 读取信息获得读取的字节数
		int read = clientChannel.read(buffer);
		if(read==-1){
			// 没有读取到内容的情况
			clientChannel.close();
		}else{
			// 将缓冲区准备为数据传出状态
			buffer.flip();
			
			byte[] data = buffer.array();
			String msg = new String(data).trim();
			System.out.println("服务端"+ System.nanoTime() +"收到信息："+ msg );
			
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			clientChannel.write(ByteBuffer.wrap(new String("向客户端发送第" + (i++) + "条信息 "+ System.nanoTime()).getBytes()));// 将消息回送给客户端
		}
	}
	
	/**
	 * 启动服务端测试
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(8000);
		server.listen();
	}
}
