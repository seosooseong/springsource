<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- CSS only -->


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">공지사항</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="container">
				<div class="panel-heading">공지사항</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<form role="form" action="/comunity/noticeModify" method="get">


						<div class="form-group">
							<label>공지사항 번호</label> <input class="form-control" name='bno'
								value='<c:out value="${notice.bno }"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>제목</label> <input class="form-control" name='title' readonly="readonly"
								value='<c:out value="${notice.title }"/>'>
						</div>
						
						<div class="form-group">
							<label>작성자</label> <input class="form-control" name='writer'
								value='<c:out value="${notice.writer}"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out 
									value="${notice.content}" /></textarea>
						</div>

						

						<div class="form-group">
							<label>작성일</label> <input class="form-control" name='regDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${notice.regDate}" />'
								readonly="readonly">
						</div>



						<button type="submit" data-oper='noticeModify'
							class="btn btn-default">수정</button>
						<button type="submit" data-oper='noticeList' class="btn btn-info">목록보기</button>
					</form>


				</div>
				<!--  end panel-body -->
			</div>
		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
	$(document).ready(function() {

		var operForm = $("#operForm");

		$("button[data-oper='noticeModify']").on("click", function(e) {

			operForm.attr("action", "/comunity/noticeModify").submit();

		});

		$("button[data-oper='noticeList']").on("click", function(e) {

			operForm.find("#bno").remove();
			operForm.attr("action", "/comunity/noticeList")
			operForm.submit();
		});
	});
</script>
<%@include file="../includes/footer.jsp"%>