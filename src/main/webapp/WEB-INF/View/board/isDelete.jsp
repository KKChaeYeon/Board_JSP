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

<form method="post" action="/Board/deleteReq.do">
	<table class="table w-75" style="margin:200px auto">
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="pwd" class="form-control" placeholder="Insert Password"></td>
			<td colspan="2"><input type=submit value=확인 class="btn btn-primary"></td>
		</tr>
	</table>
	<input type="hidden" name="nowPage" value="<%=request.getParameter("nowPage") %>">
	<input type="hidden" name="start" value="<%=request.getParameter("start") %>">
	<input type="hidden" name="end" value="<%=request.getParameter("end") %>">
</form>









</body>
</html>