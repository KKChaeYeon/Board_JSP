<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <div id=nav>

        <!--네비게이션 바 시작 -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <a class="navbar-brand me-5" href="/Home.do">HOME</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active  me-5" aria-current="page" href="#">회사 소개</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link active  me-5" aria-current="page" href="/Notice/list.do">공지사항</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link  me-5" href="/Board/list.do">자유게시판</a>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle  me-5" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    나의 정보
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="/MemberSearch.do">나의 정보 조회</a></li>
                    <li><a class="dropdown-item" href="#">나의 정보 수정</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#">회원 탈퇴</a></li>
                  </ul>
                </li>
              </ul>
              <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
              </form>
            </div>
          </div>
        </nav>
        <!-- 네비게이션 바 끝 -->
        </div>