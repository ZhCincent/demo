<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>登录</title>
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/common.css" />
	</head>
	<body>
		<div class="wrap login_wrap">
			<div class="content">
				
				<div class="logo"></div>
				
				<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							登录
						</div>
						<form action="" method="post">
							
							<div class="form_text_ipt">
								<input name="username" type="text" placeholder="手机号">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_text_ipt">
								<input name="password" type="password" placeholder="密码">
							</div>
							<div class="ececk_warning"><span>数据不能为空</span></div>
							<div class="form_check_ipt">
								<div class="left check_left">
									<label><input name="" type="checkbox"> 记住密码</label>
								</div>
								<div class="right check_right">
									<a href="#">忘记密码</a>
								</div>
							</div>
							<div class="form_btn">
								<button type="button" id="save" onclick="la()">登录</button>
							</div>
							<div class="form_reg_btn">
								<span>还没有帐号？</span><a href="register.html">马上注册</a>
							</div>
						</form>
						<div class="other_login">
							<div class="left other_left">
								<span>其它登录方式</span>
							</div>
							<div class="right other_right">
								<a href="#">QQ登录</a>
								<a href="#">微信登录</a>
								<a href="#">微博登录</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/jquery.min.js" ></script>
		<script type="text/javascript" src="js/common.js" ></script>
		<script type="text/javascript">
		    function la(){
		    	if($("input[name='username']")[0].value==null||$("input[name='password']")[0].value==null){
		    		alert("用户名密码不能为空");
		    	}
		    	$.ajax({
		    		url:'/login',
		    		type:'post',
		    		async: false,
		    		data:{
		    			username:$("input[name='username']")[0].value,
		    			password:$("input[name='password']")[0].value
		    		},
		    		success:function(data){
		    			debugger;
		    			//alert(JSON.stringify(data));
		    			var index=data.indexOf(":true");
		    			if(index==-1){
		    				alert("用户名密码错误");
		    			}else{
		    				window.location.href = "/test";
		    			}
		    		}
		    	});
		    	
		    }
		</script>
	</body>
</html>