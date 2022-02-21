<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- 링크경로(Head포함) -->
<%@include file="../../module/link.jsp" %>

<body>

<div class="container-fluid">

<!-- meta_Header -->
<%@include file="../../module/meta_header.jsp" %>
<!-- header -->
<%@include file="../../module/header.jsp" %>
<!-- nav -->
<%@include file="../../module/nav.jsp" %>

</div>

<div class="page-contents p-3">

<!-- 페이지 위치정보(브레드 크럼사용) -->
<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Board</a></li>
    <li class="breadcrumb-item"><a href="#">List</a></li>
    <li class="breadcrumb-item active" aria-current="page">Post</li>
  </ol>
</nav>

<h2>글쓰기</h2>
<%@page import="vo.*" %>
<%
	MemberVo vo= (MemberVo)session.getAttribute("vo");
%>


<form method="post" action="/Board/post.do?flag=false" enctype="multipart/form-data">
<table class="table w-75">
	<tr>
		<td>아이디</td>
		<td><input name="email" class="form-control" value=<%=vo.getEmail() %> disabled></td>
	</tr>
	<tr>
		<td>제 목</td>
		<td><input name="subject" class="form-control"></td>
	</tr>
	<tr>
		<td>내 용</td>
		<td>
			<textarea name="content" rows="10" cols="50" class="form-control"></textarea>
		</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type="password" name="pwd" class="form-control"></td>
	</tr>
	<tr>
		<td>파일 업로드</td>
		<td>
			<input type="file" name="uploadfile" class="form-control">
		</td>
	</tr>
	<tr>
	
		<td colspan="2">
			<input type="submit" value="POST" class="btn btn-primary">
			<input type="reset" value="RESET" class="btn btn-primary">
			<input type="button" value="BACK" class="btn btn-secondary" onClick="history.back()">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>