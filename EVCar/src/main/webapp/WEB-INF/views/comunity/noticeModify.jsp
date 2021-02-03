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
		<h1 class="page-header">Board Modify</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="container">

				<div class="panel-heading">공지사항 수정</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<form role="form" action="/comunity/noticeModify" method="post">
						<%--  
        <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
        <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
	    <input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
		<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'> --%>


						<div class="form-group">
							<label>번호</label> <input class="form-control" name='bno'
								value='<c:out value="${notice.bno }"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>제목</label> <input class="form-control" name='title'
								value='<c:out value="${notice.title }"/>'>
						</div>
						
						<div class="form-group">
							<label>작성자</label> <input class="form-control" name='writer'
								value='<c:out value="${notice.writer}"/>' readonly="readonly">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name='content'><c:out
									value="${notice.content}" /></textarea>
						</div>

						

						<div class="form-group">
							<label>작성일</label> <input class="form-control" name='regDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${notice.regDate}" />'
								readonly="readonly">
						</div>



						<button type="submit" data-oper='noticeModify'
							class="btn btn-default">수정하기</button>
						<button type="submit" data-oper='noticeRemove'
							class="btn btn-danger">삭제</button>
						<button type="submit" data-oper='noticeList' class="btn btn-info">목록보기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {

				var formObj = $("form");

				$('button').on(
						"click",
						function(e) {

							e.preventDefault();

							var operation = $(this).data("oper");

							console.log(operation);

							if (operation === 'noticeRemove') {
								formObj
										.attr("action",
												"/comunity/noticeRemove");

							} else if (operation === 'noticeList') {
								//move to list
								formObj.attr("action", "/comunity/noticeList")
										.attr("method", "get");
								formObj.empty();
							}

							formObj.submit();
						});

			});
</script>
<%@include file="../includes/footer.jsp"%>