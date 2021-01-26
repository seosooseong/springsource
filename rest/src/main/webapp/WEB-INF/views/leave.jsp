<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<form id="leaveform" action="" method="post">
	<div class="card"  style="width: 40rem;margin:40px auto;">	
		<div class="card-header">
	    	<h4>회원탈퇴</h4>
	  	</div>
	 	<div class="card-body">	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="text" name="userid" id="userid" class="form-control" value="test10" readonly/>
			 		<small id="userid" class="text-info"></small>		          <!-- 세션에 담은값 가져오기 -->
				</div>
			</div>	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="password" name="password" id = "password" class="form-control" placeholder="비밀번호" autofocus="autofocus"/>
					<small id="password" class="text-info"></small>
				</div>	
			</div>				
			<div class="form-group text-center">		
				<button type="submit" class="btn btn-danger">탈퇴</button>
			    <button type="button" class="btn btn-secondary" id="leavecancel">취소</button>		
			</div>
		</div>
	</div>	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>
<script>
	<script>
	$(function(){
		$(".btn-danger").click(function(e){
			e.preventDefault();
			
			let param ={
					userid:$("userid").val(),
					password:$("password").val(),
			}

			$.ajax({
				url:'/remove',
				type:'delete',
				contentType:'application/json',
				data:JSON.stringify(param),
				success:function(data){
					if(data=='success'){
						alert("탈퇴성공");
						location.href="/";	
						}
					},
				error:function(xhr,txtStatus,error){
					$(".table").html(xhr,responseText);
				}
			})
		})
	})
	</script>	
</form>
