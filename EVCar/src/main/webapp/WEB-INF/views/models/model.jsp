<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/header.jsp" %>
     
<!-- Start Header -->
<div class="fables-header fables-after-overlay bg-rules">
    <div class="container"> 
         <h2 class="fables-page-title fables-second-border-color wow fadeInLeft" data-wow-duration="1.5s" style="margin-top: 90px">공지사항</h2>
    </div>
</div>  
<!-- /End Header -->
     
<!-- Start Breadcrumbs -->
<div class="fables-light-background-color">
    <div class="container"> 
        <nav aria-label="breadcrumb">
          <ol class="fables-breadcrumb breadcrumb px-0 py-3">
            <li class="breadcrumb-item"><a href="#" class="fables-second-text-color">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">공지사항</li>
            <li class="breadcrumb-item"><a href="#" class="fables-second-text-color">Q&A</a></li>
            <li class="breadcrumb-item"><a href="#" class="fables-second-text-color">관련 기사</a></li>
          </ol>
        </nav> 
    </div>
</div>
<!-- /End Breadcrumbs -->
     
<!-- Start page content -->  
       <div class="container">
        <!-- Main content -->
<section class="content">
    <div class="box box-primary">
        
        <div class="row">
            
            <div class="col-md-4 offset-md-4"><!--검색 들어갈 부분-->
                <form action="qList.do" method="post" id="search">
                    <select name="criteria" id="">
                        <option value="n" <c:out value="${empty info.search.criteria?'selected':''}" />>----</option>               
                        <option value="title" <c:out value="${info.search.criteria=='title'?'selected':''}" />>title    </option>           
                        <option value="content" <c:out value="${info.search.criteria=='content'?'selected':''}" />>content</option>             
                        <option value="name" <c:out value="${info.search.criteria=='name'?'selected':''}" />>name</option>  
                    </select>
                    <input type="text" name="keyword" value="${info.search.keyword}"/>
                    <input type="button" value="검색" class="btn btn-primary"/>           
                </form>
            </div>
        </div>
        <br>
        <table class="table">
            <tr>
                <th class='text-center' style='width:100px'>번호</th>
                <th class='text-center'>제목</th>
                <th class='text-center'>작성자</th>
                <th class='text-center'>날짜</th>
                <th class='text-center' style='width:100px'>조회수</th>
            </tr>
            <c:forEach var="vo" items="${list}">
            <tr><!-- 리스트 목록 보여주기 -->
                <td class='text-center'>${vo.bno}</td><!--번호-->
                <td><!--제목-->
                    <c:if test="${vo.re_lev!=0}">
                        <c:forEach begin="0" end="${vo.re_lev*1}">
                            &nbsp;
                        </c:forEach>
                    </c:if>
                    <a href="qHitUpdate.do?bno=${vo.bno}&page=${info.search.page}&criteria=${info.search.criteria}&keyword=${info.search.keyword}">${vo.title}</a></td>
                <td class='text-center'>${vo.name}</td><!--작성자-->
                <td class='text-center'>${vo.regdate}</td><!--날짜-->
                <td class='text-center'><span class="badge badge-pill badge-primary">${vo.readcount}</span></td>
            </tr>   
            </c:forEach>    
        </table>
        <div class="container">
            <div class="row  justify-content-md-center">
                <nav aria-label="Page navigation example">
                  <ul class="pagination"><!--하단의 페이지 나누기 부분-->
                    <c:if test="${info.prev}">
                        <li class="page-item">
                            <a href="qList.do?page=${info.search.page-1}&criteria=${info.search.criteria}&keyword=${info.search.keyword}" class="page-link">이전</a>
                        </li>                   
                    </c:if>
                    <c:forEach begin="${info.startPage}" end="${info.endPage}" var="idx">
                        <c:choose>
                            <c:when test="${idx==info.search.page}">
                                <li class="page-item active"><a class="page-link">${idx}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="qList.do?page=${idx}&criteria=${info.search.criteria}&keyword=${info.search.keyword}">${idx}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${info.next}">
                        <li class="page-item">
                            <a href="qList.do?page=${info.search.page+1}&criteria=${info.search.criteria}&keyword=${info.search.keyword}" class="page-link">다음</a>
                        </li>
                    </c:if>
                  </ul>
                </nav>                  
            </div>
        </div>
        <div style="height:20px"></div>
    </div>  
</section>
     </div>
<!-- /End page content -->
    
<!-- Start Footer 2 Background Image  -->
<%@include file="../includes/footer.jsp" %>   

