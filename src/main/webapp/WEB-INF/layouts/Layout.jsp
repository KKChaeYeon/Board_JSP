<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 링크경로(Head포함) -->
<%@include file="../module/link.jsp" %>

<body>

<div class="container-fluid">

<!-- meta_Header -->
<%@include file="../module/meta_header.jsp" %>
<!-- header -->
<%@include file="../module/header.jsp" %>
<!-- nav -->
<%@include file="../module/nav.jsp" %>

<div id="page-content">

<!-- banner -->
<%
	String img1=request.getParameter("b1");
	String img2=request.getParameter("b2");
	String img3=request.getParameter("b3");
%>
<jsp:include page="../module/banner.jsp">
<jsp:param name="banner1" value="<%=img1 %>" />
<jsp:param name="banner2" value="<%=img2 %>" />
<jsp:param name="banner3" value="<%=img3 %>" />
</jsp:include>


<!-- ITEMS -->
<%@include file="../module/items.jsp" %>

<!-- TAB -->
<%@include file="../module/nav_tab.jsp" %>

</div>

<!-- Footer  -->


</div>

</body>
</html>