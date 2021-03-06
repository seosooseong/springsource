/**
 *  form 안에 input type='file' 들어온 경우
 */	

$(function() {
		$("button").click(function(e) {
			e.preventDefault();//submit버튼 일단 막기
			
			var formData = new FormData();
			
			formData.append("code",$('#code').val());
			formData.append("category",$('#category').val());
			formData.append("file",$('#file')[0].files[0]);
			
			
			//①일반방식으로 전송
			$.ajax({
				url: '/product',
				type: 'post',
				data: formData,
				contentType: false, //데이터를 query string형태로 보낼 것인지 결정(기본값: application/x-www-form-urlencoded)
				processData: false, //기본값은 application/x-www-form-urlencoded(파일 첨부이므로 ultiple)
				success: function(result) {
					 console.log(result);
				},
				error: function(xhr,txtStatus,error){
					 console.log(txtStatus,responseText);
				}
			})
			

			//②json 전송
			
			/*let form = {
				code : $('#code').val(),
				category : $('#category').val(),
				pname : $('#pname').val(),
				amount : $('#amount').val(),
				price : $('#price').val(),
				etc : $('#etc').val(),
			};
			
			$.ajax({
				url: '/product',
				type: 'post',
				contentType: 'application/json;charset=utf-8',
				data: JSON.stringify(form),
				success: function(result) {
					 console.log(result);
				},
				error: function(xhr,txtStatus,error){
					console.log(txtStatus,responseTxt);
				}
			})*/
			

		})
	})
