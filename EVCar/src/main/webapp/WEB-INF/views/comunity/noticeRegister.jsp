<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS only -->


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Register</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="container" style="border-style:solid; border-width:1.5px">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item active" aria-current="page">공지사항 작성</li>
					</ol>
				</nav>
				<!-- /.panel-heading -->
				<div class="panel-body" >

					<form action="/comunity/noticeRegister" method="post">
						<div class="form-group">
							<label for="exampleFormControlInput1" style="text-weight: bolder">제목</label>
							<input type="text" class="form-control"
								id="exampleFormControlInput1" name="title"
								placeholder="제목을 입력해주세요">
						</div>
						<div class="form-group">
							<label for="exampleFormControlInput1">작성자</label> <input
								type="text" class="form-control" id="exampleFormControlInput1"
								name="writer" placeholder="작성자를 입력해주세요">
						</div>
						<div class="form-group">
							<label for="exampleFormControlTextarea1">내용</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								name="content" rows="3" placeholder="내용을 입력해주세요"></textarea>
						</div>
						<div>
							<button type="submit" class="btn btn-primary">작성</button>
							<button type="button" class="btn btn-light">공지사항 목록</button>
						</div>
					</form>

				</div>
			</div>
			<!--  end panel-body -->
		</div>
	</div>
</div>
<!-- <script type="text/javascript">
$(document).ready(function(){
	var operForm = $("#operForm");
	
	$("button[data-oper='list']").on("click", function(e){
		
		operForm.find("#bno").remove();
		operForm.attr("action","/comunity/noticeList")
		orerForm.submit();
	});
});
</script> -->
<%@include file="../includes/footer.jsp"%>