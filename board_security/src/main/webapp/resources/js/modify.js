/**
 * 
 */
$(function() {
		var form =$("#myform");
		
		$("button").click(function(e){
			e.preventDefault(); //서브밋 기능 막기.
			
			var oper = $(this).data("oper");
			console.log(oper);
			
			if(oper=='remove'){
				form.attr("action","remove");
				
			//수정은 기존 상단의 form을 가지고 간다.	
			}else if(oper =='modify'){ 
				form=$("form[role='form']");
				var str="";
				
				//첨부 파일 영역의 정보수집
				$(".uploadResult ul li").each(function(idx,obj){
					var job = $(obj);
					str+="<input type='hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
					str+="<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
					str+="<input type='hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
					str+="<input type='hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
				})
				console.log(str);
				
				form.append(str);
				
			}else if(oper =='list'){
					form.attr("action","list")
					.attr("method","get");
				
				//form 안의 bno삭제하기
				form.find("input[bno='bno']").remove();
			}
			form.submit();
		})
	})



//첨부물 
$(document).ready(function(){
	
	var uploadResult = $(".uploadResult ul");
	
	//모든 데이타를 제이슨으로
	$.getJSON({
		url:'getAttachList',
		data: {
			bno: bnoVal
		},
		success:function(data){
			console.log(data);
			
			var str = "";
			
			$(data).each(function(idx,obj){
				if(obj.fileType){
					//썸네일 이미지 경로
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName;
					str+=" <button type='button' class='btn btn-warning btn-circle' data-file='";
					str+=fileCallPath+"' data-type='i'>";
					str+="<i class='fa fa-times'></i>"
					str+="</button>";
					str+="</div></li>";
				}else{
					//일반파일 경로 2021\01\21\ uuid_원본파일명.txt
					var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
					
					str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
					str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
					str+="<a href='/download?fileName="+fileCallPath+"'>";
					str+="<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
					str+="<button type='button' class='btn btn-warning btn-circle' data-file='";
					str+=fileCallPath+"' data-type='file'>";
					str+="<i class='fa fa-times'></i>"
					str+="</button>";
					str+="</div></li>";
				}
			})
			uploadResult.html(str); //넣어준다.
		}
	}) //getJSON 종료
	
	//이미지 클릭 -> 원본이미지 보여주기, 일반파일 다운로드
	$(".uploadResult").on("click","li",function(){
		var liObj = $(this);
		
		var path = encodeURIComponent(liObj.data("path")+"\\"+liObj.data("uuid")+"_"+liObj.data("filename"));
		
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
		}else{
			location.href ="/download?fileName="+path;
		}
	})
})

// 크게 열린 이미지를 클릭시 0으로 줄이기
$(".bigPictureWrapper").click(function() {
	$(".bigPicture").animate({width:'0%', height:'0%'},1000);
	setTimeout(function() {
		$(".bigPictureWrapper").hide();
	},1000);
}) // 이미지 종료


//x 버튼 클릭 - 이벤트 위임
	$(".uploadResult").on("click","button",function(e){
		
		//.이벤트 전파 막기!
		e.stopPropagation();
		
		
		if(confirm("정말로 파일을 삭제하시겠습니까?")){
			//span 태그가 속한 부모 태그 가져오기
			var targetLi=$(this).closest("li");
			//화면 목록에서 제거
			targetLi.remove();
		}
		
		
	}) //x버튼 종료 (이벤트 위임)
	
	//파일버튼이 클릭되어 변화가 일어나는 경우
	//현재 목록의 파일을 서버로 보내서 저장하기
	$("input[type='file']").change(function() {
		console.log("업로드 호출...");

		var inputFile = $("input[name='uploadFile']");
		console.log(inputFile);

		//첨부파일목록
		var files = inputFile[0].files;

		//<form/> 태그 대체 / Ajax로 데이터를 쉽게 전송할 수 있게 해줌.
		var formData = new FormData();

		//객체 안에 요소 추가
		for (var i = 0; i < files.length; i++) {
			formData.append("uploadFile", files[i]); // 키값 : uploadFile
			
		}

		
		$.ajax({
			url: '/uploadAjax',
			type: 'post',
			processData: false, //데이터를 query string형태로 보낼 것인지 결정(기본값: application/x-www-form-urlencoded)
			contentType: false, //기본값은 application/x-www-form-urlencoded (파일 첨부이므로 ultiple)
			data: formData,
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			success: function(result) {
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val(""); //파일을 올리면 기존 파일은 날림.
			},
			error: function(xhr, status, error) {
				console.log(status);
			}
		})
	})
		function showUploadedFile(uploadResultArr){
		//결과를 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str="";
		
		$(uploadResultArr).each(function(idx,obj){
			if(obj.fileType){ //이미지라면? 파일이름.
					
			//썸네일 이미지 경로 (한글 인코딩 => (encodeURIComponent)을 하고 보내야한다.)
			var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			//원본이미지 경로
			var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
			str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str+="<a href=\"javascript:showImage(\'"+originPath+"\')\">";
			str+="<img src='/display?fileName="+fileCallPath+"'><div>"+obj.fileName+"</a>";
			str+=" <button type='button' class='btn btn-warning btn-circle' data-file='";
			str+=fileCallPath+"' data-type='i'>";
			str+="<i class='fa fa-times'></i>"
			str+="</button>";
			str+="</div></li>";
	
			}else{	
			//일반파일 경로 2021\01\21\ uuid_원본파일명.txt
			var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
			str+="<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
			str+="data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str+="<a href='/download?fileName="+fileCallPath+"'>";
			str+="<img src='/resources/img/attach.png'><div>"+obj.fileName+"</a>";
			str+="<button type='button' class='btn btn-warning btn-circle' data-file='";
			str+=fileCallPath+"' data-type='file'>";
			str+="<i class='fa fa-times'></i>"
			str+="</button>";
			str+="</div></li>";
			}
		});
		uploadResult.append(str);
	} //showUploadedFile end

	
	

//이미지 크게 열기
function showImage(fileCallPath) {
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
				   	.animate({width:'100%', height:'100%'},1000);
	
}	