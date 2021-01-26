<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="#">
<!-- 파비콘 오류 제거 -->
</head>
<body>
	<div class="container">
		<h3>도서정보</h3>
		<button type="button" id="all">전체 도서 정보</button>
		<button type="button" id="get">특정 도서 정보</button>
		<button type="button" id="remove">도서 정보 삭제</button>
		<button type="button" id="update">전체 도서 수정</button>
		<button type="button" id="input">전체 도서 입력</button>

		<div id="result">
			<table class="table"></table>
		</div>
	</div>
	<script>
$(function() {
	$("#all").click(function(){
	$.getJSON({
		url:'/list',
		success:function(data){
			console.log(data);
			
			let str ="";
			$(data).each(function(idx,item) {
				str+="<tr><td>";
				str+=item.code+"</td><td>"+item.title+"</td>";
				str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";
			})
			$(".table").html(str);
			}
		})
	})

	$("#get").click(function() {
	$.getJSON({
			url:'/4',
			success:function(item){
			console.log(item);
			
			let str ="";
				str+="<tr><td>";
				str+=item.code+"</td><td>"+item.title+"</td>";
				str+="<td>"+item.writer+"</td>"+"<td>"+item.price+"</td></tr>";
			$(".table").html(str);
			}
		})
	})

	$("#remove").click(function(){
		$.ajax({
			url:'/3',
			type:'delete',
			success:function(data){
				$(".table").html(data);
			},
			error:function(xhr,txtStatus,error){
				$(".table").html(xhr,responseText);
			}
		})
	})
	$("#update").click(function(){
		//코드, 가격
		let param={
				code : 4,
				price: 45454
		}
		$.ajax({
			url:"/update",
			type: "put", // or patch
			contentType:"application/json",
			data: JSON.stringify(param),
			success:function(data){
				$(".table").html(data);
			},
			error:function(xhr,txtStatus,error){
				$(".table").html(xhr,responseText);
			}
		})
		
	})
	$("#input").click(function(){
		let param={
				code : 3,
				title: 'rest 입문',
				writer: '서수성',
				price: 33333
		}
		$.ajax({
			url:"/new",
			type: "post", // or patch
			contentType:"application/json",
			data: JSON.stringify(param),
			success:function(data){
				$(".table").html(data);
			},
			error:function(xhr,txtStatus,error){
				$(".table").html(xhr,responseText);
			}
		})
	})

})
</script>
</body>
</html>














