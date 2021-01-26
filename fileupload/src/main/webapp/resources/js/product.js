/**
 *  form 안에 일반요소만 들어온 경우
 */

	$(function() {
		$("button").click(function(e) {
			e.preventDefault();//submit버튼 일단 막기
			
			//①일반방식으로 전송
			$.ajax({
				url: '/product',
				type: 'post',
				data: $("form").serialize(),
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