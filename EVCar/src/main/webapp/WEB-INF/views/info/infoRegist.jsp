<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 가격 단위 ,, -->
<%@include file="../includes/header.jsp" %>

<link rel="shortcut icon" href="#"> <!-- 파비콘 오류 제거 -->
<!-- css임시 -->
  <style>
    form {
      margin: 5px 5px;
      padding: 10px;
      width: 400px;
      display: inline-block;
     
    }
   </style>
   
<!-- Start Header -->
<div class="fables-header fables-after-overlay bg-rules">
    <div class="container"> 
         <h3 class="fables-page-title fables-second-border-color wow fadeInLeft" data-wow-duration="1.5s" style="margin-top: 90px">전기차</h3>
    </div>
</div>  
<!-- /End Header -->
     
<!-- /End Breadcrumbs -->
     
 <!-- 상단 안내표 위치 -->
    <div class="fables-light-background-color">
      <div class="container">
        <nav aria-label="breadcrumb">
          <ol class="fables-breadcrumb breadcrumb px-0 py-3">
            <li class="breadcrumb-item">
              <a href="#" class="fables-second-text-color">전기차 </a>
            </li>
            <li class="breadcrumb-item active" aria-current="page"> 국내운행 차량
            </li>
          </ol>
        </nav>
      </div>
    </div>
    <!-- /상단 안내표 -->

	<!-- 메인 컨텍츠 -->
	 <div class="container">
    	<form action="" method="post" role="form">
			<div class="form-group">
				<label for="exampleFormControlInput1">브랜드</label> <input
					type="text" class="form-control" id="exampleFormControlInput1"
					name="brand" placeholder="브랜드명">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">차량모델</label> <input
					type="text" class="form-control" id="exampleFormControlInput1"
					name="carname" placeholder="모델명">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">차량 영문명</label> <input
					type="text" class="form-control" id=""
					name="carname_en" placeholder="모델명를 입력해주세요">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">자동차규격</label> <input
					type="text" class="form-control" id=""
					name="cartype" placeholder="ex) 중형차">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">최저가</label> <input
					type="number" class="form-control" id=""
					name="rowprice" placeholder="최저가격">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">최고가</label> <input
					type="number" class="form-control" id=""
					name="highprice" placeholder="최고가격">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">출시연도</label> <input
					type="number" class="form-control" id=""
					name="releasedate" placeholder="ex) 2020 -숫자만 입력">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">연비</label> 
				<input type="number" max="100" min="0" step="0.1" class="form-control" id="" name="fuel" placeholder="ex) 15.2 -소수 첫째자리">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">주행거리</label> <input
					type="number" class="form-control" id=""
					name="mileage" placeholder="숫자만 입력">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1">충전방식</label> <input
					type="text" class="form-control" id=""
					name="chargetype" placeholder="ex) DC콤보">
			</div>
			<div class="form-group">
				<label for="exampleFormControlInput1" style="text-weight: bolder">제목</label>
				<input type="text" class="form-control"
					id="" name="title"
					placeholder="제목을 입력해주세요">
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">내용</label>
				<textarea class="form-control" id=""
					name="content" rows="3" placeholder="내용을 입력해주세요"></textarea>
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">작성</button>
				<button type="button" class="btn btn-light">목록</button>
			</div>
		</form>
 </div>
    <!-- /End-->

    
<!-- Start Footer 2 Background Image  -->
<%@include file="../includes/footer.jsp" %>   
