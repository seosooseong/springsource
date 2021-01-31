/**
 * list.jsp에서 사용하는 스크립트
 */

$(function(){
	
	
	//호출
	checkModal(result);
	
	//history 재지정
	
	history.replaceState({}, null,null);
	
	
	//modal창 띄우기
	function checkModal(result){
		if(result =='' ||history.state){
			return;
		}
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+result+" 번이 등록되었습니다.");
		}
		
		$("#myModal").modal("show"); //띄우기
	}
	//actionForm 가져오기 : 페이지 이동 사용
	var actionForm = $("#actionForm");
	
	
	//페이지 번호 클릭시 동작
	$(".paginate_button a").click(function(e){
		//a태크 기능 중지
		e.preventDefault();
		//pageNum의 값을 사용자가 입력한 값으로 변경  , this = paginate_button a
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));

		actionForm.submit();
	})
	
	//amount 값 변경
	$(".form-control").change(function(){
		//amount의 값을 사용자가 입력한 값으로 변경
		actionForm.find("input[name='amount']").val($(this).val());
		actionForm.submit();
	})
	
	//제목 클릭시 동작 (현재 글, 정보, 페이지,amount...)
	$(".move").click(function(e){
		e.preventDefault();
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr("action","read"); //  /board/read
		actionForm.submit();
	})
	
	//검색시 동작
	$(".btn-default").click(function(){
		//serchForm 가져온 후 type과 keyword가 비어 있는지 확인
		var searchForm = $("#serchForm");
		
		var type =$("select[name='type']").val();
		var keyword =$("input[name='keyword']").val();
		
		//비어 있으면 메세지 띄어준 후 리턴.
		if(type===''){
			alert("검색기준을 입력해주세요");
			return false;
		}else if(keyword===''){
			alert("검색어를 입력해주세요");
			return false;
		}
		
		//검색시 첫 페이지를 보여주기 위해 페이지 번호를 1로 바꿔준다.
		searchForm.find("input[name='pageNum']").val("1");
		searchForm.submit();
	})
})












