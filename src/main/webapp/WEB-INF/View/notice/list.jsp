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
<div class="page-contents">
<h2>공지사항(Notice)</h2>

<!-- ROLE ADMIN만 접근 가능 -->
<a href="/Notice/post.do">글쓰기</a>

</div>
</body>
</html>