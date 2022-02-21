<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="resources/css/common.css" type="text/css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<%
		String MSG = (String)request.getAttribute("MSG");
		if(MSG==null){
			MSG="";
		}
	%>
	<script>
		var msg="<%=MSG%>";
		if(msg!="")
			alert(msg);
	</script>

	<div style="width:300px; height:300px; margin:150px auto; border:1px solid gray; text-align:center;">
	<h3 class="mb-2">LOGIN</h3>
	<form method="post" action="LoginProc.do">
		<div class="m-2">
			<input name="email" placeholder="example@example.com" class="form-control">
		</div>
		<div class="m-2">
			<input type="password" name="pwd" placeholder="Enter Password" class="form-control">
		</div>
		<div class="ms-2 me-2 mt-4 mb-4">
			<input type="checkbox" name="idchk" class="form-check-input"> 아이디 저장
			<input type="checkbox" name="pwdchk" class="form-check-input"> 패스워드 저장
		</div>
		<div class="m-2">
			<input type="submit" value="로그인" class="btn btn-primary w-100">
		</div>
		<div class="m-2">
			<a href="MemberJoin.jsp" class="btn btn-primary w-100">회원가입</a>
		</div>
	</form>
	</div>

</body>
</html>