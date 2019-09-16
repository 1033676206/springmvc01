<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">

	var flag = false;

	function t(){
		var username = $("#uName").val();
		$.ajax({
			type : "POST",   // 请求方式
			url : "ajaxUsername.action", // 地址
			dataType : "text", // 返回参数类型
			data: {
				"username" : username
			},
			success : function(data){
				var mess = "";
				if(data == "NNN"){
					mess = "<font color='red'>用户名错误</font>";
					flag = false;
				}
				else{// data == "FFF"
					mess = "<font color='green'>用户名正确</font>";
				}

				$("#ppp").html(mess);
			},
			error : function(){
				alert("ajax请求失败");
			}
		});
	}

	function m(){
		var userN = $("#uName").val();
		var password = $("#uPass").val();
		$.ajax({
			type: "POST",
			url : "ajaxPassword.action",
			dataType: "text",
			data: {
				"username" : userN ,
				"password" : password
			},
			success : function(data){
				var y = "";
				if(data == "Yes")
					flag = true;
				else{
					y = "<font color='red'>用户名或密码错误</font>";
					flag = false;
				}

				$("#mmm").html(y);
			},
			error : function(){
				alert("ajax请求失败");
			}
			
		});
	}

	function ccc(){
		return flag;
	}

</script>
	

</head>
<body>
	<form action="${pageContext.request.contextPath }/userLogin.action" onsubmit="return ccc();">
		<input type="text" name="username" id="uName" placeholder="用户名" onblur="t()"> 
		<span id="ppp"></span>
		<br/>
		<input type="password" name="password" id="uPass" placeholder="密码" onblur="m()"><br/>
		<input type="submit" value="提交">
		<br/>
		<span id="mmm"></span>
	</form>
</body>
</html>