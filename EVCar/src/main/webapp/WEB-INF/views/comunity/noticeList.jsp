<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- Start Header -->
<div class="fables-header fables-after-overlay bg-rules">
	<div class="container">
		<h2
			class="fables-page-title fables-second-border-color wow fadeInLeft"
			data-wow-duration="1.5s" style="margin-top: 90px">공지사항</h2>
	</div>
</div>
<!-- /End Header -->

<!-- Start Breadcrumbs -->
<div class="fables-light-background-color">
	<div class="container">
		<nav aria-label="breadcrumb">
			<ol class="fables-breadcrumb breadcrumb px-0 py-3">
				<li class="breadcrumb-item"><a href="#"
					class="fables-second-text-color">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page"
					style="font-weight: bolder">공지사항</li>
				<li class="breadcrumb-item"><a href="#"
					class="fables-second-text-color">Q&A</a></li>
				<li class="breadcrumb-item"><a href="#"
					class="fables-second-text-color">관련 기사</a></li>
			</ol>
		</nav>
	</div>
</div>
<!-- /EndBreadcrumbs -->

<!-- Start page content -->
<div class="container" style="margin-bottom: 50px">
	<!-- Main content -->
	<section class="content">
		<div class="box box-primary">

			<div class="row">
				<div class="col-md-4 offset-md-4">
					<!--검색 들어갈 부분-->
					<form action="qnotice.do" method="post" id="search">
						<select name="criteria" id="">
							<option value="n"
								<c:out value="${empty info.search.criteria?'selected':''}" />>----</option>
							<option value="title"
								<c:out value="${info.search.criteria=='title'?'selected':''}" />>title
							</option>
							<option value="content"
								<c:out value="${info.search.criteria=='content'?'selected':''}" />>content</option>
							<option value="name"
								<c:out value="${info.search.criteria=='name'?'selected':''}" />>name</option>
						</select> <input type="text" name="keyword" value="${info.search.keyword}" />
						<input type="button" value="검색" class="btn btn-primary" />
					</form>
				</div>
			</div>
			<br>
			<div class="register">
				<button type="button" class="btn btn-primary" id="regBtn"
					style="float: right; margin-bottom: 3px">글 작성</button>
			</div>
			<table class="table" style="text-align:center">
				<thead>
					<tr>
						<th class='text-center'>번호</th>
						<th class='text-center'>제목</th>
						<th class='text-center'>작성자</th>
						<th class='text-center'>작성일</th>
						<th class='text-center'>수정일</th>
						<th class='text-center'>조회수</th>
					</tr>

					<c:forEach var="notice" items="${notice}">
						<tr class="contentTr">
							<td><a
								href='/comunity/noticeGet?bno=<c:out value="${notice.bno}"/>'><c:out
										value="${notice.bno}" /></a></td>
							<td><c:out value="${notice.title}" /></td>
							<td><c:out value="${notice.writer}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${notice.regDate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${notice.updateDate}" /></td>
							<td><c:out value="${notice.readCnt}" /></td>
						</tr>
					</c:forEach>
				</thead>
			</table>
			<div class="text-center" style="text-align:center">
            	<ul class="pagination" style="display:inline-block">
					<c:if test="${pageMaker.prev}">
						<li class="page-item"><a
							href="${pageMaker.startPage -1}" class="page-link">이전</a></li>
					</c:if>

					<c:forEach var="num" begin="${pageMaker.startPage}"	end="${pageMaker.endPage}">
						<li
							class="page-item ${pageMaker.cri.pageNum == num ?'active':''} " style="float:left; margin-top:10px;">
							<a href="${num}" class="page-link">${num}</a>
						</li>
					</c:forEach>

					<c:if test="${pageMaker.next}">
						<li class="page-item" ><a 
							href="${pageMaker.endPage +1 }" class="page-link">다음</a></li>
					</c:if>
				</ul>
				<form id="actionForm" action="/comunity/noticeList" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}">
				</form>
				<div class="modal" id="myModal" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">공지사항</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p>처리가 완료되었습니다.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">확인</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div style="height: 20px"></div>
</div>
<!-- /End page content -->

<!-- Start Footer 2 Background Image  -->
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var result = '<c:out value="${result}"/>';

						checkModal(result);

						history.replaceState({}, null, null);

						function checkModal(result) {

							if (result === '' || history.state) {
								return;
							}

							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ " 번이 등록되었습니다.");
							}

							$("#myModal").modal("show");
						}

						$("#regBtn").on("click", function() {

							self.location = "/comunity/noticeRegister";

						});

						var actionForm = $("#actionForm");

						$(".page-item a").on(
								"click",
								function(e) {

									e.preventDefault();

									console.log('click');

									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						$(".move")
								.on(
										"click",
										function(e) {

											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/comunity/noticeGet");
											actionForm.submit();

										});

						var searchForm = $("#searchForm");

						$("#searchForm button").on(
								"click",
								function(e) {

									if (!searchForm.find("option:selected")
											.val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									}

									searchForm.find("input[name='pageNum']")
											.val("1");
									e.preventDefault();

									searchForm.submit();

								});

					});
</script>
<%@include file="../includes/footer.jsp"%>
