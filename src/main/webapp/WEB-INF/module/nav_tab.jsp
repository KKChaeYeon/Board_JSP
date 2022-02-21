<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

            <!-- 탭시작 -->
          <div id='page-tab' class='row mt-2'  style="height:300px;position:relative;left:0px;top:0px;" >
            <div class=col-6 style='border:1px solid gray;'>
              
              <ul class="nav nav-tabs">
                <li class="nav-item">
                  <a class="nav-link active" id=tab1 aria-current="page" href="javascript:func1()">Tab1</a>
             
                </li>
                <li class="nav-item">
                  <a class="nav-link"  id=tab2 href="javascript:func2()">Tab2</a>

                </li>
                <li class="nav-item">
                  <a class="nav-link" id=tab3 href="javascript:func3()">Tab3</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id=tab4  href="javascript:func4()">Tab4</a>
                </li>
              </ul>

              <!-- 테이블 기본 숨기기 스타일 시작 -->
              <style>
                #page-tab table{
                  position:absolute;
                  left:3%;  
                }
                #page-tab #tab2-tbl
                {
                  visibility: hidden;
                }
              </style>
              <!-- 테이블 기본 숨기기 스타일 끝 -->
              <div>
                <table id=tab1-tbl class='table' style='width:45%;'>
                  <tr>
                    <td>Tab1</td>
                    <td>Tab1</td>
                  </tr>
                </table>
                <table id=tab2-tbl  class='table'  style='width:45%;'>
                  <tr>
                    <td>Tab2</td>
                    <td>Tab2</td>
                  </tr>
                </table>
              </div>
              <!-- 탭 Active 이동시키기 시작 -->
              <script>
                var pre=$('#tab1');
                var pretbl=$('#tab1-tbl');
                function func1(){
                  pre.removeClass('active'); //이전 탭 active 제거
                  $('#tab1').addClass('active'); //현재 탭 active 추가
                  pre=$('#tab1');
                    // 테이블 이동
                  pretbl.css('visibility','hidden'); //이전 테이블 display none
                  $('#tab1-tbl').css('visibility','visible');//2테이블 display block    
                  pretbl=$('#tab1-tbl');
                }
                function func2(){
                  // 탭이동
                  pre.removeClass('active'); //이전 탭 active 제거
                  $('#tab2').addClass('active'); //현재 탭 active 추가
                  pre=$('#tab2');
                  // 테이블 이동
                  pretbl.css('visibility','hidden'); //이전 테이블 display none
                  $('#tab2-tbl').css('visibility','visible');//2테이블 display block
                  pretbl=$('#tab2-tbl');

                }
                function func3(){}
                function func4(){}
              </script>
              <!-- 탭 Active 이동시키기 끝 -->

            </div>
          </div>
          <!-- 탭끝 -->