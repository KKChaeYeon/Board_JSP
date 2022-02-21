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


<%
	int totalRecode=0;		//전체 게시물의 수
	int numPerPage=10;		//페이지당 게시물 수
	int pagePerBlock=15;	//블럭당 페이지 수
	
	int totalPage=0;	//전체 페이지 수
	int totalBlock=0;	//전체 블럭 수 
	int nowPage=1;		//현재 읽고 있는 페이지
	int nowBlock=1;		//현재 읽고 있는 블럭
	
	int start=0;		//읽을 게시물 위치 (DB로 전달)
	int end=10;			//start로부터 총 읽을 게시물의 수(DB로 전달)
	
	//현재 페이지 받기(Controller에서 전달함)
	if(request.getParameter("nowPage")!=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	}
	
	//페이지 처리
	totalRecode = (int)request.getAttribute("tcnt");
	totalPage = (int)Math.ceil((double)totalRecode / numPerPage);	//소수점 이하는 올려서 처리하고, 버림으로 int 처리

	//블럭 처리
	totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
	nowBlock = (int)Math.ceil((double)nowPage/pagePerBlock);	
%>
<div class="page-contents p-3">

<!-- 페이지 위치정보(브레드 크럼사용) -->
<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Board</a></li>
    <li class="breadcrumb-item active" aria-current="page">List</li>
  </ol>
</nav>

<h2 class="mb-4">자유 게시판</h2>

<!-- 게시판 상단 헤더(현재페이지 / 전체페이지)  -->
<table class="w-75">
	<tr>
		<td>
			Page : <span style="color:red;"><%=nowPage %></span> / <%=totalPage %>Page
		</td>
	</tr>
</table>

<!-- 게시물 리스트 -->
<table class="table w-75">
	<!-- 게시물 열이름  -->
	<tr>
		<td>번 호</td>
		<td>제 목</td>
		<td>이 름</td>
		<td>날 짜</td>
		<td>조회수</td>
	</tr>
	
	<!-- 본문  -->
	<%@page import="java.util.*, vo.*" %>
		<%
		Vector<BoardVO> list = (Vector<BoardVO>)request.getAttribute("list");
		for(int i=0; i<list.size(); i++){
		%>
		<tr>
			<td><%=list.get(i).getNum() %></td>
			<!-- read를 위한 a태그 -->
			<td><a href="javascript:read('<%=list.get(i).getNum() %>')"><%=list.get(i).getSubject() %></a></td>	
			<td><%=list.get(i).getEmail() %></td>
			<td><%=list.get(i).getRegdate() %></td>
			<td><%=list.get(i).getCount() %></td>
		</tr>
		<%
		}
	%>
</table>


<!--페이징처리/글쓰기&처음으로 버튼  -->
<%

	int pageStart=(nowBlock-1)*pagePerBlock + 1;
	int pageEnd=((pageStart+pagePerBlock)<=totalPage)?(pageStart+pagePerBlock):totalPage+1;
	//1 ? 2 : 3  >> 1이 참이면 2, 1이 거짓이면 3..
%>

<script>
	function paging(page){
		
		/* 현재 페이지 폼에 저장 */
		document.readForm.nowPage.value=page;
		

		var numPerPage=<%=numPerPage%>
		document.readForm.start.value=(page*numPerPage)-numPerPage;
		document.readForm.end.value=numPerPage;
		document.readForm.action="/Board/list.do";
		document.readForm.submit();
	}
	
	function moveBlock(value){
		//alert("블럭 : " +value);
		var numPerPage= <%=numPerPage %>		//10
		var pagePerBlock= <%=pagePerBlock %>	//15
		
		/* 블럭이동 시, 표시할 첫번째 nowPage값을 계산해서 readForm에 저장 */
		document.readForm.nowPage.value=pagePerBlock*(value-1)+1;
		
		var page=pagePerBlock*(value-1)+1;
		document.readForm.start.value=(page*numPerPage)-numPerPage;
		document.readForm.end.value=numPerPage;
		document.readForm.action="/Board/list.do";
		document.readForm.submit();
	}
	
	function moveFirst(){
		document.readForm.nowPage.value=1;
		document.readForm.start.value=0;
		document.readForm.end.value=10;
		document.readForm.action="/Board/list.do";
		document.readForm.submit();
	}
	
	function read(num){
		document.readForm.num.value=num;
		document.readForm.nowPage.value='<%=nowPage %>';
		var numPerPage=<%=numPerPage %>
		var page=<%=nowPage%>
		document.readForm.start.value=(page*numPerPage)-numPerPage;
		document.readForm.end.value=numPerPage;
		document.readForm.action="/Board/read.do";
		document.readForm.submit();
	}
</script>

<!-- 페이징 관련 정보 전달 form -->
<form name="readForm" method="get">
	<input type="hidden" name="num">
	<input type="hidden" name="start">
	<input type="hidden" name="end">
	<input type="hidden" name="nowPage">
</form>
<!-- 페이징 관련 정보 전달 끝 -->


<table class="table w-75">
    <tr>
        <!--페이징처리-->
        <%
        	if(totalPage!=0)
        	{
        %>
        <td>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
            
            <!-- 이전 블럭으로 이동 -->
            <%
            	if(nowBlock > 1)
            	{
            %>
            <li class="page-item">
                <a class="page-link" href="javascript:moveBlock('<%=nowBlock-1 %>')" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <%
            	}
            %>
            
            <!-- 페이지 번호 처리(스크립틀릿 + 반복문) -->
            <%
            	for(int i=pageStart; i<pageEnd; i++){
            %>
            <li class="page-item"><a class="page-link" href="javascript:paging('<%=i %>')"><%=i %></a></li>
            <%
            	}
            %>
            
            <!-- 다음 블럭으로 이동 -->
            <%
            	if(totalBlock>nowBlock)
            	{
            %>
            <li class="page-item">
                <a class="page-link" href="javascript:moveBlock('<%=nowBlock+1 %>')" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <%
            	}
            %>
            </ul>
        </nav>
        </td>
        <%
        	}
        %>
        <!--글쓰기/처음으로 버튼  -->    
        <td align="right">
            <a href="/Board/post.do?flag=true" class="btn btn-primary">글쓰기</a>
            <a href="javascript:moveFirst()" class="btn btn-primary">처음으로</a>
        </td>
    </tr>

</table>
</div>
</body>
</html>