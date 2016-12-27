package com.wqj.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class NettyServer {

	public void start(String inetHost, int inetPort){
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		
		EventLoopGroup childGroup = new NioEventLoopGroup();
		
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		try {
			bootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel sc) throws Exception {
					  System.out.println("client initChannel..");
					//  拿到一个工作流
					sc.pipeline().addLast(new HttpResponseEncoder())
								 .addLast(new HttpRequestDecoder());
//							 .addLast(new HttpServerI);
					
				}
			}).option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			
			ChannelFuture future = bootstrap.bind(inetHost, inetPort).sync();
			future.channel().closeFuture().sync();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
      
	public static void main(String[] args) {
		 new NettyServer().start("127.0.0.1", 8188);
	}
}
