<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 파비콘 오류 제거 -->
<link rel="shortcut icon" href="#"> 
<!-- css -->
<link rel="stylesheet" href="/resources/css/mycss.css" />
</head>
<body>
	<h1>Uload Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" id="" multiple />
	</div>
	<button>upload</button>
	<div class="uploadResult">
		<ul></ul>
	</div>
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>
	</div>
	
	
	
	
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>	

<script>
//폼 사용안함. type="file"을 보내주면 되기 때문에
$(function() {
	$("button").click(function() {
		console.log("업로드 호출...");
		
		var inputFile = $("input[name='uploadFile']");
		console.log(inputFile);		
		
		//첨부파일목록
		var files = inputFile[0].files;
		
		//<form/> 태그 대체 / Ajax로 데이터를 쉽게 전송할 수 있게 해줌.
		var formData = new FormData();
		
		//객체 안에 요소 추가
		for(var i=0; i<files.length; i++){
			formData.append("uploadFile",files[i]); // 키값 : uploadFile
						//컨트롤러 와 이름 맞추기 MultipartFile[] uploadFile
		}
		
/* 		for(let key of formData.keys()){
			console.log(key);
		}
		
		for(let key of formData.values()){
			console.log(value);
		} */
		
		$.ajax({
			url:'/uploadAjax',
			type: 'post',
			processData:false, //데이터를 query string형태로 보낼 것인지 결정(기본값: application/x-www-form-urlencoded)
			contentType:false, //기본값은 application/x-www-form-urlencoded (파일 첨부이므로 ultiple)
			data:formData,
			success:function(result){
				console.log(result);
				showUploadedFile(result);
			},
			error:function(xhr,status,error){
				console.log(status);
			}
		})
	})
	
	function showUploadedFile(uploadResultArr){
		//결과를 보여줄 영역 가져오기
		var uploadResult = $(".uploadResult ul");
		var str="";
		
		$(uploadResultArr).each(function(idx,obj){
			if(obj.image){ //이미지라면? 파일이름.
					
			//썸네일 이미지 경로 (한글 인코딩 => (encodeURIComponent)을 하고 보내야한다.)
			var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
			//원본이미지 경로
			var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str+="<li>";
			str+="<a href=\"javascript:showImage(\'"+originPath+"\')\">";
			str+="<img src='/display?fileName="+fileCallPath+"'>"+obj.fileName+"</a>";
			str+="<span data-file='"+fileCallPath+"' data-type='image'> X </span>";
			str+="</li>";
	
			}else{	
				//일반파일 경로 2021\01\21\ uuid_원본파일명.txt
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
				str+="<li><a href='/download?fileName="+fileCallPath+"'>";
				//이미지가 아니라면? attach.png 나온다.
				str+="<img src='/resources/img/attach.png'/>"+obj.fileName+"</a>"
				str+="<span data-file='"+fileCallPath+"'data-type='file'> X </span>";
				str+="</li>";
			}
		});
		uploadResult.append(str);
	}
	
	// 크게 열린 이미지를 클릭시 0으로 줄이기
	$(".bigPictureWrapper").click(function() {
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		setTimeout(function() {
			$(".bigPictureWrapper").hide();
		},1000);
	})
	
	//x 버튼 클릭 - 이벤트 위임
	$(".uploadResult").on("click","span",function(){
		
		//해당 파일 경로 가져오기
		var targetFile=$(this).data("file");
		
		//파일 타입 가져오기
		var type=$(this).data("type");
		
		//span 태그가 속한 부모 태그 가져오기
		var targetLi=$(this).closest("li");
		
		
		//서버 폴더에서 제거
		$.ajax({
			url: '/deleteFile',
			type:'post',
			data:{
				fileName:targetFile,
				type:type
			},
			success:function(result){
				console.log(result);
			//화면 목록에서 제거
				targetLi.remove();
			}
		})
	})
})


function showImage(fileCallPath) {
	$(".bigPictureWrapper").css("display","flex").show();
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
				   	.animate({width:'100%', height:'100%'},1000);
	
}
</script>
</body>
</html>


