<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SweetAlert2对话框</title>

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
	<div class="showcase sweet">
		<button>显示一个错误信息</button>
	</div>
	
	<script type="text/javascript">
		$('.showcase.sweet button').on('click', function() {
		    swal('Oops...', 'Something went wrong!', 'error');
		  });
	</script>

	
	
	<ul class="examples">

		<li class="message">
			<div class="ui">
				<p>基本信息对话框</p>
				<button>Try me!</button>
			</div>
			<script type="text/javascript">
				$('.examples .message button').on('click', function() {
				    swal('Any fool can use a computer...');
				  });
			</script>
		</li>
		

		<li class="title-text">
			<div class="ui">
				<p>带标题的信息对话框</p>
				<button>Try me!</button>
			</div>
			<script type="text/javascript">
			 $('.examples .title-text button').on('click', function() {
				  swal('The Internet?', 'That thing is still around...?', 'question');
			  });
			</script>
		</li>

		<li class="success">
			<div class="ui">
				<p>成功信息对话框</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">
			  $('.examples .success button').on('click', function() {
				    swal('Good job!', 'You clicked the button...!', 'success');
			   });
			</script>
		</li>

		<li class="timer">
			<div class="ui">
				<p>自动关闭的对话框</p>
				<button>Try me!</button>
			</div>
			<script type="text/javascript">
			  $('.examples .timer button').on('click', function() {
				    swal({
				      title: 'Auto close alert!',
				      text: 'I will close in 2 seconds.',
				      timer: 2000
				    });
				  });
			</script>
		</li>

		<li class="html">
			<div class="ui">
				<p>自定义HTML标签和按钮的对话框</p>
				<button>Try me!</button>
			</div> 
				<script type="text/javascript">
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
					      cancelButtonText: '<i class="fa fa-thumbs-down"></i>1234'
					    });
					  });
				</script>
		</li>

		<li class="html-jquery">
			<div class="ui">
				<p>jQuery HTML</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">
				$('.examples .html-jquery button').on('click', function() {
			    swal({
			      title: 'jQuery HTML example',
			      html: $('<div>').addClass('some-class').text('jQuery is everywhere.')
			    });
			  });
			</script>
		</li>

		<li class="warning confirm">
			<div class="ui">
				<p>一个警告信息对话框，“确认”按钮带有回调函数</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">

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
			</script>
		</li>

		<li class="warning cancel">
			<div class="ui">
				<p>... and by passing a parameter, you can execute something
					else for "Cancel".</p>
				<button>Try me!</button>
			</div> <script type="text/javascript">
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
			</script>
		</li>

		<li class="custom-icon">
			<div class="ui">
				<p>A message with a custom icon and CSS animation disabled</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">
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
			</script>
		</li>

		<li class="custom-width-padding-background">
			<div class="ui">
				<p>A message with custom width, padding and background</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">
			  $('.examples .custom-width-padding-background button').on('click', function() {
				    swal({
				      title: 'Custom width, padding, background.',
				      width: 600,
				      padding: 100,
				      background: '#fff url(https://bit.ly/1Nqn9HU)'
				    });
				  });
			</script>
		</li>

		<li class="ajax-request" id="ajax-request">
			<div class="ui">
				<p>Ajax request example</p>
				<button>Try me!</button>
			</div> 
			<script type="text/javascript">
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
			</script>
		</li>

		<li class="chaining-modals" id="chaining-modals">
			<div class="ui">
				<p>Chaining modals example</p>
				<button>Try me!</button>
			</div> 
			
			<script type="text/javascript">
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
				    });
				  });
			</script>
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
		<script type="text/javascript">
			 $('.modal-types button').on('click', function() {
			    var type = $(this).attr('class').slice(5);
			    swal(type + '!', '', type);
			  });
		</script>
	</table>









	<!-- Input Types -->
	<h3>Input Types</h3>
	<table class="modal-input-types">
		<tr id="input-field">
			<td><strong>text</strong></td>
			<td><button class="input-type-text">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="input-email">
			<td><strong>email</strong></td>
			<td><button class="input-type-email">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="input-password">
			<td><strong>password</strong></td>
			<td><button class="input-type-password">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr>
			<td><strong>textarea</strong></td>
			<td><button class="input-type-textarea">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="select-box">
			<td><strong>select</strong></td>
			<td><button class="input-type-select">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="radio-inputs">
			<td><strong>radio</strong></td>
			<td><button class="input-type-radio">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="checkbox">
			<td><strong>checkbox</strong></td>
			<td><button class="input-type-checkbox">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>

		<tr id="input-file">
			<td><strong>file</strong></td>
			<td><button class="input-type-file">Try me!</button></td>
			<script type="text/javascript">
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
			</script>
		</tr>
	</table>
	
	

	<div class="mobile-hidden">
		<table class="modal-input-types">
			<tr id="multiple-inputs">
				<td></td>
				<td><button class="input-type-multiple">Try me!</button></td>
			</tr>
			<script type="text/javascript">
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
			</script>
		</table>
	</div>
	<script>
</script>
</body>
</html>