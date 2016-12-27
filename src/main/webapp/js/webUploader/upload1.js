(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('#uploader');
        
            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' ).appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            // 进度条
            $progress = $statusBar.find( '.progress' ),//.hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},

            // WebUploader实例
            uploader;
            
            // 初始化Web Uploader
            uploader = WebUploader.create({
            	// 选完文件后，是否自动上传
            	auto: false,
            	//是否可重复选择同一文件
            	duplicate:false,
            	//上传并发数。允许同时最大上传进程数。
            	threads:1,
            	// 选择文件的按钮
                pick: {
                    id: '#filePicker',   // 选择文件的按钮容器
                    innerHTML:'点击选择图片', // 按钮文字
                    multiple: true,     // 是否开启选择多个文件
                },
                
                // swf文件路径  
                swf: 'Uploader.swf',   
                // 文件接收服务端
                server: 'upload',  
                // 只允许选择图片文件。
                accept: {
                    title: 'Images',  // 文字描述
                    extensions: 'gif,jpg,jpeg,bmp,png',  // 允许的文件后缀
                    mimeTypes: 'image/*'
                },
                
               // 文件上传请求的参数表，每次发送都会发送此对象中的参数。
                formData: {
                    uid: 123
                },
                
                dnd: '#dndArea',
                paste: '#uploader',
               
                // 是否开启分片上传
                chunked: false,
                // 每片大小
                chunkSize: 512 * 1024,  
                
//                 runtimeOrder: 'flash',

                // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
                disableGlobalDnd: true,
                
                fileNumLimit: 30,  // 文件总数量限制
                fileSizeLimit: 200 * 1024 * 1024,    // 文件总大小限制
                fileSingleSizeLimit: 50 * 1024 * 1024    // 单个文件大小限制
            });

        // 拖拽时触发----主要限制某些文件拖入进来         return true可以加入
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,  i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';
            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }
            return !denied;
        });

        // 添加“添加文件”的按钮，
        uploader.addButton({
        	id: '#filePicker2',   // 选择文件的按钮容器
            innerHTML:'继续添加',   // 按钮文字
            multiple: true,     // 是否开启选择多个文件
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                
                // 当前图片的进度
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),
                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;
                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }
                    img = $('<img src="'+src+'">');
                    $wrap.empty().append( img );
                }, thumbnailWidth, thumbnailHeight );
               
                percentages[ file.id ] = [ file.size, 0 ];
                console.log("----->>>>>> "+JSON.stringify(percentages));
                file.rotation = 0;
            }
            
            // 文件状态改变的时候执行
            file.on('statuschange', function( cur, prev ) {
            	// 初始化  inited   已入队列 queued    正在上传 progress   上传出错 error   上传成功 complete   上传取消 cancelled
            	console.log("statuschange------------>>>> cur: " + cur + "   ,prev: " + prev);
                if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' ); // 关闭文件上的操作(旋转  删除)
                    $btns.remove();
                }
               
                if ( cur === 'error' || cur === 'invalid' ) {
                	// 文件失败时提醒   (1) 给出错误提示    (2) 让该文件的进度为 0%
                    showError();
                    updateFileUploadProgress(file, 0);
                } else if ( cur === 'interrupt' ) {
                	// 文件暂停时提醒
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                	// 已入队列    (1) 初始化该文件的进度为 0%
                    updateFileUploadProgress(file, 0);
                } else if ( cur === 'progress' ) {
                	// 正在上传   (1) 让该文件的进度为 30%
                	updateFileUploadProgress(file, 0.58);
                    $info.remove();
                    $prgress.css('display', 'block');
                    
                } else if ( cur === 'complete' ) {
                	// 上传成功   (1) 让该文件的进度为 100%  (2) 让该文件的进度为隐藏     (3) 给出成功提示
                	updateFileUploadProgress(file, 1);
                	$prgress.hide().width(0);
                    $li.append( '<span class="success"></span>' );
                }
                
                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            // 设置操作按钮（左旋转，右旋转，删除）的动画效果
            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });
            
            // 设置操作按钮（左旋转，右旋转，删除）的点击事件
            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;
                    case 1:
                        file.rotation += 90;
                        break;
                    case 2:
                        file.rotation -= 90;
                        break;
                }

                deg = 'rotate(' + file.rotation + 'deg)';
	            $wrap.css({
	                  '-webkit-transform': deg,
	                  '-mos-transform': deg,
	                  '-o-transform': deg,
	                  'transform': deg
	             });
            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        // 更新总进度
        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;
            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                console.log("v: " + v);
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;
            
            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
        	console.log("*******  updateStatus   ********** state:  " + state);
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        function setState( val ) {
        	console.log("******************************  setState   ***********************************  " + val);
        	
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;
            
            // 可能有      (移除)pedding,   (准备好) ready,  (开始上传) uploading,  (暂停) paused   
            //       (所有文件都上传结束)confirm,  (没有成功的图片,重设)done.   (所有文件上传结束 没有不成功的)finish 
            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':   // 准备好 （即已选择了，或加入预览中了）
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':   // 开始上传
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();   
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':  // 所有文件上传结束
                    $progress.hide();   // 隐藏总的上传进度
                    $( '#filePicker2' ).removeClass( 'element-invisible' ); // 让 "继续添加" 按钮显示
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
	                 // successNum 上传成功的文件数
	                 // progressNum 上传中的文件数
	                 // cancelNum 被删除的文件数
	                 // invalidNum 无效的文件数
	                 // uploadFailNum 上传失败的文件数
	                 // queueNum 还在队列中的文件数
	                 // interruptNum 被暂停的文件数
                    if ( stats.successNum && !stats.uploadFailNum ) {  
                        setState( 'finish' );   // 全部都上传成功了
                        return;
                    }
                    break;
                    
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        console.log("上传成功");
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

       // 上传过程中触发，携带上传进度
//        uploader.onUploadProgress = function( file, percentage ) {  // 上传进度
//            var $li = $('#'+file.id),
//                $percent = $li.find('.progress span');
//            console.log("percentage: " + percentage);
//            // 当前单个文件的进度为100%
//            $percent.css( 'width', percentage * 100 + '%' );
//            percentages[ file.id ][ 1 ] = percentage;
//            updateTotalProgress();
//        };
        
        //当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效。
        //如果此事件handler返回值为false, 则此文件将派送server类型的uploadError事件
        uploader.onUploadAccept = function( file, response ) {
        	console.log("------- onUploadAccept ------->>> response: " + JSON.stringify(response));
        	if(response.statusCode == 0){
        		return true;
        	}else{
        		return false;
        	}
        };
        
        
        // 当文件上传成功时触发
        uploader.onUploadSuccess = function( file ) {
        	updateFileUploadProgress(file, 1);
        };
        
        // 当文件上传出错时触发
        uploader.onUploadError = function( file, reason ) {
        	console.log("----------------onUploadError  (当文件上传出错时触发) ------------------- ");
        	return true;
        };
        
        // 不管成功或者失败，文件上传完成时触发
        uploader.onUploadComplete = function( file ) {
        	// 隐藏进度条  TODO
        	console.log("----------------onUploadComplete  (隐藏进度条  TODO) ------------------- ");
        };
        
        
        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        
        
        // 所有的事件触发都会响应到。同时此类callback中的arguments有一个不同处，
        // 就是第一个参数为type，记录当前是什么事件在触发。此类callback的优先级比脚低，会再正常callback执行完后触发。
        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':   // 所有文件上传结束时
                    setState( 'confirm' );
                    break;
                case 'startUpload':   // 开始上传流程时
                    setState( 'uploading' );
                    break;
                case 'stopUpload':   // 开始上传流程暂停时
                    setState( 'paused' );
                    break;
            }
        });
        

        // 当validate不通过时
        uploader.onError = function( code ) {
        	// console.log("code: " + code);
        	var text="";
        	 switch( code ) {
	             case 'F_EXCEED_SIZE':
	                 text = '文件大小超出  ' + WebUploader.formatSize( uploader.option( 'fileSingleSizeLimit' ) );
	                 break;
	             case 'Q_EXCEED_SIZE_LIMIT':
	                 text = '文件总大小超出  ' + WebUploader.formatSize( uploader.option( 'fileSingleSizeLimit' ) );
	                 break;
	             case 'Q_EXCEED_NUM_LIMIT':
	            	 text = '文件总数量超出  ' + uploader.option( 'fileNumLimit' ) +' 个';
	            	 break;
	             default:
	                 text = '其他限制';
	                 break;
        	 }
        	 alert(text);
        };

        // 文件的   上传, 暂停, 停止
        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }
            
            if ( state === 'ready' ) {  // 当准备好时点击上传
                uploader.upload();
            } else if ( state === 'paused' ) { // 当暂停时点击会继续上传
                uploader.upload();
            } else if ( state === 'uploading' ) {  // 当上传中点击时会停止上传
                uploader.stop();
            }
        });
        
        // 重试上传，从出错的文件重新上传
        $info.on( 'click', '.retry', function() {
            uploader.retry();  
        } );
        
        
        $info.on( 'click', '.ignore', function() {
            setState( 'finish' );   // 全部都上传成功了
           // alert( 'todo' );
        } );


        $upload.addClass( 'state-' + state );
        updateTotalProgress();
        
       // File file = WebUploader.File(guidGenerator36(),'http://wqjpassport.6655.la:24801/upload/160716/94e829b9-c158-4a7e-afd7-b82222b94b87.png')
        // 添加缩略图
//        WebUploader.WUFile('http://wqjpassport.6655.la:24801/upload/160716/94e829b9-c158-4a7e-afd7-b82222b94b87.png'); 
       
        
        //=============================================  helper  ====================================================== 
        function updateFileUploadProgress(file, percentage){
        	var $li = $('#'+file.id),
        	$percent = $li.find('.progress span');
        	
        	// 当前单个文件的进度
        	$percent.css( 'width', percentage * 100 + '%' );
        	percentages[ file.id ][ 1 ] = percentage;
        	// 总进度
        	updateTotalProgress();
        };
        
        
    });

})( jQuery );