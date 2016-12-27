<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>textarea还剩余字数统计</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Courgette">
<link rel="stylesheet" href="${ctx }/js/plug/sweet/example/example.css">
<link rel="stylesheet" href="${ctx }/js/plug/sweet/example/buttons.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${ctx }/js/plug/sweet/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${ctx }/js/plug/sweet/dist/sweetalert2.min.css">

<!-- IE support -->
<script
	src="https://cdn.jsdelivr.net/es6-promise/latest/es6-promise.min.js"></script>

<!-- Promise.finally support -->
<script
	src="https://cdn.jsdelivr.net/promise.prototype.finally/1.0.1/finally.js"></script>

<!--[if IE]>
		<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
</head>

<body>
	<div class="jq22-container">
		<div class="jq22-header">
			<h3>
				SweetAlert2-强大的纯Js模态消息对话框插件 <span>A beautiful and
					customizable replacement for JavaScript's popup boxes</span>
			</h3>
		</div>
	</div>
	<header>
	<h1>
		Sweet<span>Alert</span>2
	</h1>
	</header>

	<p>
		下面是一个默认的浏览器弹出对话框和<strong>SweetAlert2</strong>对话框的比较效果。
	</p>

	<div class="showcase normal">
		<h4>浏览器内置的对话框</h4>
		<button>显示一个错误信息</button>

		<h5>Code:</h5>
		<pre>
			<span class="func">alert</span>(<span class="str">'Oops! Something went wrong!'</span>)</pre>

		<div class="vs-icon"></div>
	</div>

	<div class="showcase sweet">
		<h4>
			Sweet<span>Alert</span>2
		</h4>
		<button>显示一个错误信息</button>

		<h5>Code:</h5>
		<pre>
sweetAlert(
  <span class="str">'Oops...'</span>,
  <span class="str">'Something went wrong!'</span>,
  <span class="str">'error'</span>
)</pre>
	</div>

	<p>Pretty cool huh? SweetAlert2 automatically centers itself on the
		page and looks great no matter if you're using a desktop computer,
		mobile or tablet. It's even highly customizeable, as you can see
		below!</p>


	<!-- Examples -->
	<h3>More examples</h3>

	<p class="center">
		In these examples, we're using the shorthand function <strong>swal</strong>.
	</p>

	<ul class="examples">

		<li class="message">
			<div class="ui">
				<p>基本信息对话框</p>
				<button>Try me!</button>
			</div> <pre>swal(<span class="str">'Any fool can use a computer'</span>)</pre>
		</li>

		<li class="title-text">
			<div class="ui">
				<p>带标题的信息对话框</p>
				<button>Try me!</button>
			</div> <pre>
swal(
  <span class="str">'The Internet?'</span>,
  <span class="str">'That thing is still around?'</span>,
  <span class="str">'question'</span>
)</pre>
		</li>

		<li class="success">
			<div class="ui">
				<p>成功信息对话框</p>
				<button>Try me!</button>
			</div> <pre>
swal(
  <span class="str">'Good job!'</span>,
  <span class="str">'You clicked the button!'</span>,
  <span class="str">'success'</span>
)</pre>
		</li>

		<li class="timer">
			<div class="ui">
				<p>自动关闭的对话框</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Auto close alert!'</span>,
  text: <span class="str">'I will close in 2 seconds.'</span>,
  timer: <span class="val">2000</span>
})</pre>
		</li>

		<li class="html">
			<div class="ui">
				<p>自定义HTML标签和按钮的对话框</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'&lt;i&gt;HTML&lt;/i&gt; &lt;u&gt;example&lt;/u&gt;'</span>,
  type: <span class="str">'info'</span>,
  html:
    <span class="str">'You can use &lt;b&gt;bold text&lt;/b&gt;, '</span> +
    <span class="str">'&lt;a href="//github.com"&gt;links&lt;/a&gt; '</span> +
    <span class="str">'and other HTML tags'</span>,
  showCloseButton: <span class="val">true</span>,
  showCancelButton: <span class="val">true</span>,
  confirmButtonText:
    <span class="str">'&lt;i class="fa fa-thumbs-up"&gt;&lt;/i&gt; Great!'</span>,
  cancelButtonText:
    <span class="str">'&lt;i class="fa fa-thumbs-down"&gt;&lt;/i&gt;'</span>
})</pre>
		</li>

		<li class="html-jquery">
			<div class="ui">
				<p>jQuery HTML</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'jQuery HTML example'</span>,
  html: $(<span class="str">'&lt;div&gt;'</span>)
    .addClass(<span class="str">'some-class'</span>)
    .text(<span class="str">'jQuery is everywhere.'</span>)
})</pre>
		</li>

		<li class="warning confirm">
			<div class="ui">
				<p>一个警告信息对话框，“确认”按钮带有回调函数</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Are you sure?'</span>,
  text: <span class="str">"You won't be able to revert this!"</span>,
  type: <span class="str">'warning'</span>,
  showCancelButton: <span class="val">true</span>,
  confirmButtonColor: <span class="str">'#3085d6'</span>,
  cancelButtonColor: <span class="str">'#d33'</span>,
  confirmButtonText: <span class="str">'Yes, delete it!'</span>
}).then(<span class="func"><i>function</i></span>(isConfirm) {
  <span class="tag">if</span> (isConfirm) {
    swal(
      <span class="str">'Deleted!'</span>,
      <span class="str">'Your file has been deleted.'</span>,
      <span class="str">'success'</span>
    );
  }
})</pre>
		</li>

		<li class="warning cancel">
			<div class="ui">
				<p>... and by passing a parameter, you can execute something
					else for "Cancel".</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Are you sure?'</span>,
  text: <span class="str">"You won't be able to revert this!"</span>,
  type: <span class="str">'warning'</span>,
  showCancelButton: <span class="val">true</span>,
  confirmButtonColor: <span class="str">'#3085d6'</span>,
  cancelButtonColor: <span class="str">'#d33'</span>,
  confirmButtonText: <span class="str">'Yes, delete it!'</span>,
  cancelButtonText: <span class="str">'No, cancel!'</span>,
  confirmButtonClass: <span class="str">'btn btn-success'</span>,
  cancelButtonClass: <span class="str">'btn btn-danger'</span>,
  buttonsStyling: <span class="val">false</span>
}).then(<span class="func"><i>function</i></span>(isConfirm) {
  <span class="tag">if</span> (isConfirm === <span class="val">true</span>) {
    swal(
      <span class="str">'Deleted!'</span>,
      <span class="str">'Your file has been deleted.'</span>,
      <span class="str">'success'</span>
    );
  } <span class="tag">else if</span> (isConfirm === <span class="val">false</span>) {
    swal(
      <span class="str">'Cancelled'</span>,
      <span class="str">'Your imaginary file is safe :)'</span>,
      <span class="str">'error'</span>
    );
  } <span class="tag">else</span> {
    <span class="comment">// Esc, close button or outside click</span>
    <span class="comment">// isConfirm is undefined</span>
  }
})</pre>
		</li>

		<li class="custom-icon">
			<div class="ui">
				<p>A message with a custom icon and CSS animation disabled</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Sweet!'</span>,
  text: <span class="str">'Modal with a custom image.'</span>,
  imageUrl: <span class="str">'https://unsplash.it/400/200'</span>,
  imageWidth: <span class="val">400</span>,
  imageHeight: <span class="val">200</span>,
  animation: <span class="val">false</span>
})</pre>
		</li>

		<li class="custom-width-padding-background">
			<div class="ui">
				<p>A message with custom width, padding and background</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Custom width, padding, background.'</span>,
  width: <span class="val">600</span>,
  padding: <span class="val">100</span>,
  background: <span class="str">'#fff url(//bit.ly/1Nqn9HU)'</span>
})</pre>
		</li>

		<li class="ajax-request" id="ajax-request">
			<div class="ui">
				<p>Ajax request example</p>
				<button>Try me!</button>
			</div> <pre>
swal({
  title: <span class="str">'Submit email to run ajax request'</span>,
  input: <span class="str">'email'</span>,
  showCancelButton: <span class="val">true</span>,
  confirmButtonText: <span class="str">'Submit'</span>,
  showLoaderOnConfirm: <span class="val">true</span>,
  preConfirm: <span class="func"><i>function</i></span>() {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
					class="func"><i>function</i></span>(resolve) {
      <span class="func">setTimeout</span>(<span class="func">function</span>() {
        resolve();
      }, <span class="val">2000</span>);
    });
  },
  allowOutsideClick: <span class="val">false</span>
}).then(<span class="func"><i>function</i></span>(email) {
  <span class="tag">if</span> (email) {
    swal({
      type: <span class="str">'success'</span>,
      title: <span class="str">'Ajax request finished!'</span>,
      html: <span class="str">'Submitted email: '</span> + email
    });
  }
})</pre>
		</li>

		<li class="chaining-modals" id="chaining-modals">
			<div class="ui">
				<p>Chaining modals example</p>
				<button>Try me!</button>
			</div> <pre>
swal.setDefaults({
  confirmButtonText: <span class="str">'Next &amp;rarr;'</span>,
  showCancelButton: <span class="val">true</span>,
  animation: <span class="val">false</span>
});

var steps = [
  {
    title: <span class="str">'Step 1'</span>,
    text: <span class="str">'Chaining swal2 modals is easy'</span>
  },
  <span class="str">'Step 2'</span>,
  <span class="str">'Step 3'</span>
];

swal.queue(steps).then(<span class="func"><i>function</i></span>() {
  swal({
    title: <span class="str">'All done!'</span>,
    confirmButtonText: <span class="str">'Lovely!'</span>,
    showCancelButton: <span class="val">false</span>
  });
}).finally(<span class="func"><i>function</i></span>() {
  swal.resetDefaults();
})</pre>
		</li>
	</ul>

	<!-- Modal Types -->
	<h3>Modal Types</h3>
	<table class="modal-types">
		<tr>
			<td><strong>success</strong></td>
			<td><div class="swal2-icon swal2-success">
					<span class="line tip animate-success-tip"></span> <span
						class="line long animate-success-long"></span>
					<div class="placeholder"></div>
				</div></td>
			<td><button class="type-success">Try me!</button></td>
		</tr>
		<tr>
			<td><strong>error</strong></td>
			<td><div class="swal2-icon swal2-error">
					<span class="x-mark"><span class="line left"></span><span
						class="line right"></span></span>
				</div></td>
			<td><button class="type-error">Try me!</button></td>
		</tr>
		<tr>
			<td><strong>warning</strong></td>
			<td><div class="swal2-icon swal2-warning">!</div></td>
			<td><button class="type-warning">Try me!</button></td>
		</tr>
		<tr>
			<td><strong>info</strong></td>
			<td><div class="swal2-icon swal2-info">i</div></td>
			<td><button class="type-info">Try me!</button></td>
		</tr>
		<tr>
			<td><strong>question</strong></td>
			<td><div class="swal2-icon swal2-question">?</div></td>
			<td><button class="type-question">Try me!</button></td>
		</tr>
	</table>


	<!-- Input Types -->
	<h3>Input Types</h3>
	<table class="modal-input-types">
		<tr id="input-field">
			<td><strong>text</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Input something'</span>,
  input: <span class="str">'text'</span>,
  showCancelButton: <span class="val">true</span>,
  inputValidator: <span class="func"><i>function</i></span>(value) {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
						class="func"><i>function</i></span>(resolve, reject) {
      <span class="tag">if</span> (value) {
        resolve();
      } <span class="tag">else</span> {
        reject(<span class="str">'You need to write something!'</span>);
      }
    });
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal({
      type: <span class="str">'success'</span>,
      html: <span class="str">'You entered: '</span> + result
    });
  }
})</pre></td>
			<td><button class="input-type-text">Try me!</button></td>
		</tr>

		<tr id="input-email">
			<td><strong>email</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Input email address'</span>,
  input: <span class="str">'email'</span>
}).then(<span class="func"><i>function</i></span>(email) {
  <span class="tag">if</span> (email) {
    swal({
      type: <span class="str">'success'</span>,
      html: <span class="str">'Entered email: '</span> + email
    });
  }
})</pre></td>
			<td><button class="input-type-email">Try me!</button></td>
		</tr>

		<tr id="input-password">
			<td><strong>password</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Enter your password'</span>,
  input: <span class="str">'password'</span>,
  inputAttributes: {
    <span class="str">'maxlength'</span>: <span class="val">10</span>,
    <span class="str">'autocapitalize'</span>: <span class="str">'off'</span>,
    <span class="str">'autocorrect'</span>: <span class="str">'off'</span>
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (password) {
    swal({
      type: <span class="str">'success'</span>,
      html: <span class="str">'Entered password: '</span> + password
    });
  }
})</pre></td>
			<td><button class="input-type-password">Try me!</button></td>
		</tr>

		<tr>
			<td><strong>textarea</strong></td>
			<td><pre>
swal({
  input: <span class="str">'textarea'</span>,
  showCancelButton: <span class="val">true</span>
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal(result);
  }
})</pre></td>
			<td><button class="input-type-textarea">Try me!</button></td>
		</tr>

		<tr id="select-box">
			<td><strong>select</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Select Ukraine'</span>,
  input: <span class="str">'select'</span>,
  inputOptions: {
    <span class="str">'SRB'</span>: <span class="str">'Serbia'</span>,
    <span class="str">'UKR'</span>: <span class="str">'Ukraine'</span>,
    <span class="str">'HRV'</span>: <span class="str">'Croatia'</span>
  },
  inputPlaceholder: <span class="str">'Select country'</span>,
  showCancelButton: <span class="val">true</span>,
  inputValidator: <span class="func"><i>function</i></span>(value) {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
						class="func"><i>function</i></span>(resolve, reject) {
      <span class="tag">if</span> (value === <span class="str">'UKR'</span>) {
        resolve();
      } <span class="tag">else</span> {
        reject(<span class="str">'You need to select Ukraine :)'</span>);
      }
    });
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal({
      type: <span class="str">'success'</span>,
      html: <span class="str">'You selected: '</span> + result
    });
  }
})</pre></td>
			<td><button class="input-type-select">Try me!</button></td>
		</tr>

		<tr id="radio-inputs">
			<td><strong>radio</strong></td>
			<td><pre>
<span class="comment">// inputOptions can be an object or Promise</span>
<span class="func">var</span> inputOptions = <span class="tag">new</span> <span
						class="func">Promise</span>(<span class="func"><i>function</i></span>(resolve) {
  setTimeout(<span class="func"><i>function</i></span>() {
    resolve({
      <span class="str">'#ff0000'</span>: <span class="str">'Red'</span>,
      <span class="str">'#00ff00'</span>: <span class="str">'Green'</span>,
      <span class="str">'#0000ff'</span>: <span class="str">'Blue'</span>
    });
  }, <span class="val">2000</span>);
});

swal({
  title: <span class="str">'Select color'</span>,
  input: <span class="str">'radio'</span>,
  inputOptions: inputOptions,
  inputValidator: <span class="func"><i>function</i></span>(result) {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
						class="func"><i>function</i></span>(resolve, reject) {
      <span class="tag">if</span> (result) {
        resolve();
      } <span class="tag">else</span> {
        reject(<span class="str">'You need to select something!'</span>);
      }
    });
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal({
      type: <span class="str">'success'</span>,
      html: <span class="str">'You selected: '</span> + result
    });
  }
})</pre></td>
			<td><button class="input-type-radio">Try me!</button></td>
		</tr>

		<tr id="checkbox">
			<td><strong>checkbox</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Terms and conditions'</span>,
  input: <span class="str">'checkbox'</span>,
  inputValue: <span class="val">1</span>,
  inputPlaceholder:
    <span class="str">'I agree with the terms and conditions'</span>,
  confirmButtonText:
    <span class="str">'Continue &lt;i class="fa fa-arrow-right&gt;&lt;/i&gt;'</span>,
  inputValidator: <span class="func"><i>function</i></span>(result) {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
						class="func"><i>function</i></span>(resolve, reject) {
      <span class="tag">if</span> (result) {
        resolve();
      } <span class="tag">else</span> {
        reject(<span class="str">'You need to agree with T&amp;C'</span>);
      }
    });
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal({
      type: <span class="str">'success'</span>,
      text: <span class="str">'You agreed with T&amp;C :)'</span>
    });
  }
})</pre></td>
			<td><button class="input-type-checkbox">Try me!</button></td>
		</tr>

		<tr id="input-file">
			<td><strong>file</strong></td>
			<td><pre>
swal({
  title: <span class="str">'Select image',</span>
  input: <span class="str">'file',</span>
  inputAttributes: {
    accept: <span class="str">'image/*'</span>
  }
}).then(<span class="func"><i>function</i></span>(file) {
  <span class="tag">if</span> (file) {
    var reader = <span class="tag">new</span> <span class="func">FileReader</span>;
    reader.onload = <span class="func"><i>function</i></span>(e) {
      swal({
        imageUrl: e.target.result
      });
    };
    reader.readAsDataURL(file);
  }
})</pre></td>
			<td><button class="input-type-file">Try me!</button></td>
		</tr>
	</table>

	<div class="mobile-hidden">
		<p>
			Multiple inputs aren't supported, you can achieve them by using <strong>html</strong>
			and <strong>preConfirm</strong> parameters.<br> Notice that in <strong>preConfirm</strong>
			function you can pass the custom result to <strong>resolve()</strong>:
		</p>
		<table class="modal-input-types">
			<tr id="multiple-inputs">
				<td></td>
				<td><pre>
swal({
  title: <span class="str">'Multiple inputs'</span>,
  html:
    <span class="str">'&lt;input id="swal-input1" class="swal2-input" autofocus&gt;'</span> +
    <span class="str">'&lt;input id="swal-input2" class="swal2-input"&gt;'</span>,
  preConfirm: <span class="func"><i>function</i></span>() {
    <span class="tag">return new</span> <span class="func">Promise</span>(<span
							class="func"><i>function</i></span>(resolve) {
      <span class="tag">if</span> (result) {
        resolve([
          $(<span class="str">'#swal-input1'</span>).val(),
          $(<span class="str">'#swal-input2'</span>).val()
        ]);
      }
    });
  }
}).then(<span class="func"><i>function</i></span>(result) {
  <span class="tag">if</span> (result) {
    swal(JSON.stringify(result));
  }
})</pre></td>
				<td><button class="input-type-multiple">Try me!</button></td>
			</tr>

		</table>



	</div>


	<script>
  $('.download').on('click', function() {
    $('html, body').animate({scrollTop: $('.download-section').offset().top}, 1000);
  });

  $('.showcase.normal button').on('click', function() {
    window.alert('Oops! Something went wrong!');
  });

  $('.showcase.sweet button').on('click', function() {
    swal('Oops...', 'Something went wrong!', 'error');
  });

  $('.examples .message button').on('click', function() {
    swal('Any fool can use a computer');
  });

  $('.examples .timer button').on('click', function() {
    swal({
      title: 'Auto close alert!',
      text: 'I will close in 2 seconds.',
      timer: 2000
    });
  });

  $('.examples .html button').on('click', function() {
    swal({
      title: '<i>HTML</i> <u>example</u>',
      type: 'info',
      html:
        'You can use <b>bold text</b>, ' +
        '<a href="//github.com">links</a> ' +
        'and other HTML tags',
      showCloseButton: true,
      showCancelButton: true,
      confirmButtonText: '<i class="fa fa-thumbs-up"></i> Great!',
      cancelButtonText: '<i class="fa fa-thumbs-down"></i>'
    });
  });

  $('.examples .html-jquery button').on('click', function() {
    swal({
      title: 'jQuery HTML example',
      html: $('<div>').addClass('some-class').text('jQuery is everywhere.')
    });
  });

  $('.examples .title-text button').on('click', function() {
    swal('The Internet?', 'That thing is still around?', 'question');
  });

  $('.examples .success button').on('click', function() {
    swal('Good job!', 'You clicked the button!', 'success');
  });

  $('.examples .warning.confirm button').on('click', function() {
    swal({
      title: 'Are you sure?',
      text: 'You will not be able to recover this imaginary file!',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then(function(isConfirm) {
      if (isConfirm) {
        swal('Deleted!', 'Your file has been deleted!', 'success');
      }
    });
  });

  $('.examples .warning.cancel button').on('click', function() {
    swal({
      title: 'Are you sure?',
      text: 'Buttons below are styled with Bootstrap classes',
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, cancel!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false
    }).then(function(isConfirm) {
      if (isConfirm === true) {
        swal('Deleted!', 'Your file has been deleted!', 'success');
      } else if (isConfirm === false) {
        swal('Cancelled', 'Your imaginary file is safe :)', 'error');
      } else {
        // Esc, close button or outside click
        // isConfirm is undefined
      }
    });
  });

  $('.examples .custom-icon button').on('click', function() {
    swal({
      title: 'Sweet!',
      text: 'Modal with a custom image.',
      imageUrl: 'https://unsplash.it/400/200/?random',
      imageWidth: 400,
      imageHeight: 200,
      animation: false
    });
  });

  $('.examples .custom-width-padding-background button').on('click', function() {
    swal({
      title: 'Custom width, padding, background.',
      width: 600,
      padding: 100,
      background: '#fff url(https://bit.ly/1Nqn9HU)'
    });
  });

  $('.input-type-text').on('click', function() {
    swal({
      title: 'Input something',
      input: 'text',
      showCancelButton: true,
      inputValidator: function(value) {
        return new Promise(function(resolve, reject) {
          if (value) {
            resolve();
          } else {
            reject('You need to write something!');
          }
        });
      }
    }).then(function(result) {
      if (result) {
        swal({
          type: 'success',
          html: 'You entered: <strong>' + result + '</strong>'
        });
      }
    });
  });

  $('.input-type-email').on('click', function() {
    swal({
      title: 'Input email address',
      input: 'email'
    }).then(function(email) {
      if (email) {
        swal({
          type: 'success',
          html: 'Entered email: <strong>' + email + '</strong>'
        });
      }
    });
  });

  $('.input-type-password').on('click', function() {
    swal({
      title: 'Enter your password',
      input: 'password',
      inputAttributes: {
        'maxlength': 10,
        'autocapitalize': 'off',
        'autocorrect': 'off'
      }
    }).then(function(password) {
      if (password) {
        swal({
          type: 'success',
          html: 'Entered password: <strong>' + password + '</strong>'
        });
      }
    });
  });

  $('.input-type-textarea').on('click', function() {
    swal({
      input: 'textarea',
      showCancelButton: true
    }).then(function(result) {
      if (result) {
        swal(result);
      }
    });
  });

  $('.input-type-select').on('click', function() {
    swal({
      title: 'Select Ukraine',
      input: 'select',
      inputOptions: {
        'SRB': 'Serbia',
        'UKR': 'Ukraine',
        'HRV': 'Croatia'
      },
      inputPlaceholder: 'Select country',
      showCancelButton: true,
      inputValidator: function(value) {
        return new Promise(function(resolve, reject) {
          if (value === 'UKR') {
            resolve();
          } else {
            reject('You need to select Ukraine :)');
          }
        });
      }
    }).then(function(result) {
      if (result) {
        swal({
          type: 'success',
          html: 'You entered: <strong>' + result + '</strong>'
        });
      }
    });
  });

  $('.input-type-radio').on('click', function() {
    var inputOptions = new Promise(function(resolve) {
      setTimeout(function() {
        resolve({
          '#FF0000': 'Red',
          '#00FF00': 'Green',
          '#0000FF': 'Blue'
        });
      }, 2000);
    });

    swal({
      title: 'Select color',
      input: 'radio',
      inputOptions: inputOptions,
      inputValidator: function(value) {
        return new Promise(function(resolve, reject) {
          if (value) {
            resolve();
          } else {
            reject('You need to choose something!');
          }
        });
      }
    }).then(function(result) {
      if (result) {
        swal({
          type: 'success',
          html: 'You selected: <strong>' + result + '</strong>'
        });
      }
    });
  });

  $('.input-type-checkbox').on('click', function() {
    swal({
      title: 'Terms and conditions',
      input: 'checkbox',
      inputValue: 1,
      inputClass: 'aaa',
      inputPlaceholder: 'I agree with the terms and conditions',
      confirmButtonText: 'Continue <i class="fa fa-arrow-right" style="margin-left: 10px"></i>',
      inputValidator: function(result) {
        return new Promise(function(resolve, reject) {
          if (result) {
            resolve();
          } else {
            reject('To continue you need to agree with T&amp;C');
          }
        });
      }
    }).then(function(result) {
      if (result) {
        swal({
          type: 'success',
          text: 'You agreed with T&amp;C :)'
        });
      }
    });
  });

  $('.input-type-file').on('click', function() {
    swal({
      title: 'Select image',
      input: 'file',
      inputAttributes: {
        accept: 'image/*'
      }
    }).then(function(file) {
      if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
          swal({
            imageUrl: e.target.result
          });
        };
        reader.readAsDataURL(file);
      }
    });
  });

  $('.input-type-multiple').on('click', function() {
    swal({
      title: 'Multiple inputs',
      html: '<input id="swal-input1" class="swal2-input" autofocus><input id="swal-input2" class="swal2-input">',
      preConfirm: function() {
        return new Promise(function(resolve) {
          resolve([
            $('#swal-input1').val(),
            $('#swal-input2').val()
          ]);
        });
      }
    }).then(function(result) {
      if (result) {
        swal(JSON.stringify(result));
      }
    });
  });

  $('.examples .ajax-request button').on('click', function() {
    swal({
      title: 'Submit email to run ajax request',
      input: 'email',
      showCancelButton: true,
      confirmButtonText: 'Submit',
      width: 600,
      showLoaderOnConfirm: true,
      preConfirm: function() {
        return new Promise(function(resolve) {
          setTimeout(function() {
            resolve();
          }, 2000);
        });
      },
      allowOutsideClick: false
    }).then(function(email) {
      if (email) {
        swal({
          type: 'success',
          title: 'Ajax request finished!',
          html: 'Submitted email: ' + '<strong>' + email + '</strong>'
        });
      }
    });
  });

  $('.examples .chaining-modals button').on('click', function() {
    swal.setDefaults({
      confirmButtonText: 'Next &rarr;',
      showCancelButton: true,
      animation: false
    });

    var steps = [
      {title: 'Step 1', text: 'Chaining swal2 modals is easy'},
      'Step 2',
      'Step 3'
    ];

    swal.queue(steps).then(function() {
      swal({title: 'All done!', confirmButtonText: 'Lovely!', showCancelButton: false});
    }).finally(function() {
      swal.resetDefaults();
    });
  });

  $('.modal-types button').on('click', function() {
    var type = $(this).attr('class').slice(5);
    swal(type + '!', '', type);
  });
</script>
</body>
</html>