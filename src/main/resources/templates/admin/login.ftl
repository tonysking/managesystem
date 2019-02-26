<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title> - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="${ctx!}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-12">
            	<#if message?exists >
	            	<div class="alert alert-danger">
	                    ${message!}
	                </div>
                </#if>
                <#--<form id="frm" method="post" onsubmit="return false" action="##" method="post">-->
                <form id="frm" method="post"  action="${ctx!}/admin/login" method="post">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到H+后台主题UI框架</p>
                    <input type="text" class="form-control uname" name="username" id="username" placeholder="用户名" />
                    <input type="password" class="form-control pword m-b" name="password" id="password"  placeholder="密码" />
                    <a href="" class="forget">忘记密码了？</a>账号:admin 密码:123456
                    <#--<button type="submit" onclick="login()" class="btn btn-success btn-block">登录</button>-->
                    <button type="submit"  class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; SPPan
            </div>
        </div>
    </div>
    
     <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
	<script type="text/javascript">
        // function login() {
        //     $.ajax({
        //         //几个参数需要注意一下
        //         type: "POST",//方法类型
        //         dataType: "json",//预期服务器返回的数据类型
        //         url: "/admin/login" ,//url
        //         data: $('#frm').serialize(),
        //         success: function (result) {
        //             console.log(result);//打印服务端返回的数据(调试用)
        //             if (result.code == 500) {
        //                 alert(result.msg);
        //             }
        //         }
        //     });
        // };

    $().ready(function() {


    	// 在键盘按下并释放及提交后验证提交表单
    	  $("#frm").validate({
    	    rules: {
    	      username: {
    	        required: true,
    	        minlength: 2
    	      },
    	      password: {
    	        required: true,
    	        minlength: 5
    	      }
    	    },
    	    messages: {
    	      username: {
    	        required: "请输入用户名",
    	        minlength: "用户名必需至少由两个字母组成"
    	      },
    	      password: {
    	        required: "请输入密码",
    	        minlength: "密码长度不能小于 6 个字母"
    	      }
    	    },
    	    submitHandler:function(form){
                form.submit();
            } 
    	});
    });
    
    </script>
</body>

</html>
