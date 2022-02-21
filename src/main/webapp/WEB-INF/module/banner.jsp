<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!-- 캐러셀 시작 -->
  <%
  	String img1 = request.getParameter("banner1");
  	String img2 = request.getParameter("banner2");
  	String img3 = request.getParameter("banner3");
  %>
          <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active " data-bs-interval="3000">
                <img src="resources/img/<%=img1 %>" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item"  data-bs-interval="3000">
                <img src="resources/img/<%=img2 %>" class="d-block w-100" alt="...">
              </div>
              <div class="carousel-item"  data-bs-interval="3000">
                <img src="resources/img/<%=img3 %>" class="d-block w-100" alt="...">
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>
          <!-- 캐러셀 끝 -->