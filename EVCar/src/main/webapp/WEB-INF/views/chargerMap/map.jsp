<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="/resources/css/chargerMapCss.css"/>
<%@include file="../includes/header.jsp" %>
<!-- Start Header -->
<div class="fables-header fables-after-overlay bg-rules">
    <div class="container"> 
         <h2 class="fables-page-title fables-second-border-color wow fadeInLeft" data-wow-duration="1.5s" style="margin-top: 90px">공지사항</h2>
    </div>
</div>  
<!-- /End Header -->
     
<!-- Start Breadcrumbs -->
<div class="fables-light-background-color">
    <div class="container"> 
        <nav aria-label="breadcrumb">
          <ol class="fables-breadcrumb breadcrumb px-0 py-3">
            <li class="breadcrumb-item"><a href="#" class="fables-second-text-color">Home</a></li>
            <li class="breadcrumb-item active" aria-current="page">전기차 충전소</li>
          </ol>
        </nav> 
    </div>
</div>
<!-- /End Breadcrumbs -->
     
<!-- Start page content -->  
       <div class="container">
        <!-- 여기가 매인컨택츠 컨테이너 -->
        <div><!--  -->
            <select id="area">
                <option>시도</option>
                <option>서울</option>
                <option>부산</option>
                <option>대구</option>
                <option>인천</option>
                <option>광주</option>
                <option>대전</option>
                <option>울산</option>
                <option>세종</option>
                <option>경기</option>
                <option>강원</option>
                <option>충청북도</option>
                <option>충청남도</option>
                <option>전라북도</option>
                <option>전라남도</option>
                <option>경상북도</option>
                <option>경상남도</option>
                <option>제주도</option>
            </select>
            <select id="area2">
	        	<option>시군구</option>
            </select>
	        <div style="float: right;">
	           <p style="float: left;">내 위치에서 찾기</p>
	            <div style="position: relative;float: right;width: 40px;height: 23px;margin-top: 2px;">
		            <label calss="switch">
		                <input type="checkbox" id="myAddr" style="margin-left: 10px;">
		                <span class="slider round"></span>
		            </label>	            
	            </div>   
	        </div> 
        </div>
        <div id="map" style="width:100%;height:800px;">
        </div>
     </div>
<!-- /End page content -->
    
<!-- Start Footer 2 Background Image  -->
<%@include file="../includes/footer.jsp" %>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=k7zd4tsqx0"></script>
<script>
var mapOptions = {//처음시작 지도 위치
        center: new naver.maps.LatLng(37.55656455180527, 126.97233558686575),
        zoom: 13
    };
var map = new naver.maps.Map('map', mapOptions);//지도구현
var myIp = "";
var myAddr = [];
var markers = [];
var bounds = map.getBounds(),
	southWest = bounds.getSW(),
	northEast = bounds.getNE();
var maxLat = northEast.y;
var maxLng = northEast.x;
var minLat = southWest.y;
var minLng = southWest.x;

$(function() {//내위치
    $.getJSON("https://api.ipify.org?format=jsonp&callback=?",
        function(data) {
            myIp = data.ip
        }
    );
    $.getJSON("http://ip-api.com/json/"+myIp,
        function(data) {
	    	myAddr.push(data.lat);
	    	myAddr.push(data.lon);
            console.log(myAddr);
        }
    );
    $("#myAddr").click(function() {
    	map.setCenter(new naver.maps.LatLng(myAddr[0], myAddr[1]));
	})
    
});
			 
 $(function() {//마커표시
	 
	
 });
$("#area").change(function() {
	
	var selectArea = $("#area").val();
	 
	var change;
    
	if(selectArea == "서울"){
		change = ["시군구","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"]
	}else if(selectArea == "부산"){
		change = ["시군구","강서구","금정구","기장군","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구"]	
	}else if(selectArea == "대구"){
		change = ["시군구","남구","달서구","달성구","동구","북구","서구","수성구","중구"]	
	}else if(selectArea == "인천"){
		change = ["시군구","강화군","계양구","남구","남동구","동구","부평구","서구","연수구","옹진군","중구"]	
	}else if(selectArea == "광주"){
		change = ["시군구","광산구","남구","동구","북구","서구"]	
	}else if(selectArea == "대전"){
		change = ["시군구","대덕구","동구","서구","유성구","중구"]
	}else if(selectArea == "울산"){
		change = ["시군구","남구","동구","북구","울주군","중구"]
	}else if(selectArea == "세종"){		
		change = ["시군구","세종시"]
	}else if(selectArea == "경기"){
		change = ["시군구","가평군","고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","수원시","시흥시","안산시","안성시","안양시","양주시","양평군"]
	}else if(selectArea == "강원"){
		change = ["시군구","강릉시","고성군","동해시","삼척시","속초시","양구군","양양군","영월군","원주시","인제군","정선군","철원군","춘천시","태백시","평창군","홍천군","화천군","횡성군"]
	}else if(selectArea == "충청북도"){
		change = ["시군구","괴산군","단양군","보은군","영동군","옥천군","음성군","제천시","증평군","진천군","청주시","충주시"]
	}else if(selectArea == "충청남도"){
		change = ["시군구","계룡시","공주시","금산군","논산시","당진시","보령시","부여군","서산시","서천군","아산시","예산군","천안시","청양군","태안군","홍성군"]
	}else if(selectArea == "전라북도"){
		change = ["시군구","고창군","군산시","김제시","남원시","무주군","부안군","순창군","완주군","익산시","임실군","장수군","전주시","정읍시","진안군"]
	}else if(selectArea == "전라남도"){
		change = ["시군구","강진구","고흥군","곡성군","광양시","구례군","나주시","담양군","목포시","무안군","보성군","순천시","신안군","여수시","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"]
	}else if(selectArea == "경상북도"){
		change = ["시군구","경산시","경주시","고령군","구미시","군위군","김천시","문경시","봉화군","상주시","성주군","안동시","영덕군","영양군","영주시","영천시","예천군","울진군","의성군","정도군","정송군","칠곡군","포항시","울릉도"]
	}else if(selectArea == "경상남도"){
		change = ["시군구","거제시","거창군","고성군","김해시","남해군","밀양시","사천시","산청군","양산시","의령군","진수시","창녕군","창원시","통영시","하동군","함안군","함양군","합천군"]
	}else if(selectArea == "제주도"){
		change = ["시군구","서귀포시","제주시"]
	}
	
	$("#area2").empty();
	
	for(var i in change){
		var option = $("<option>"+change[i]+"</option>");
        $("#area2").append(option);
	}

})

function updateMarkers(map, markers) {

    var mapBounds = map.getBounds();
    var marker, position;

    for (var i = 0; i < markers.length; i++) {

        marker = markers[i]
        position = marker.getPosition();

        if (mapBounds.hasLatLng(position)) {
            showMarker(map, marker);
        } else {
            hideMarker(map, marker);
        }
    }
}

function showMarker(map, marker) {

    if (marker.setMap()) return;
    marker.setMap(map);
}

function hideMarker(map, marker) {

    if (!marker.setMap()) return;
    marker.setMap(null);
}
function myIpMap(map,markers) {
	 naver.maps.Event.addListener(map, 'zoom_changed', function() {
         updateMarkers(map, markers);
         });

   naver.maps.Event.addListener(map, 'dragend', function() {
         updateMarkers(map, markers);
         });
}
function mapMarkers() {
	$.ajax({
		type:"post",
		url : "/chargerMap/getMapList",
		contentType:'application/json;charset=utf-8',
		data : JSON.stringify({max_lon:maxLng,max_lat:maxLat,min_lon:minLng,min_lat:minLat}),
		success : function(data) {
		  console.log(data);

		  $.each(data,function(idx,item){
			   var marker = new naver.maps.Marker({
				    position: new naver.maps.LatLng(item.lat, item.lon),
				    map: map
				    });
			   markers.push(marker);
			 //updateMarkers(map, markers)
				naver.maps.Event.addListener(marker, "click", function(e) {
					var contentString = "";
					contentString +='<div class="sub_group" style="width:450px">';
				    contentString +='<h4 style="text-align: center">상 세 정 보</h4>';
				    contentString +='<table class="table_02"><tbody>';
				    contentString +='<tr><th style="width: 100px">위 치</th><td colspan="3">'+item.place+'</td></tr>';
				    contentString +='<tr><th style="width: 100px">주 소</th><td colspan="3">'+item.addr+'</td></tr>';
				    contentString +='<tr><th style="width: 100px">급속충전기</th><td>'+item.f_char+'개</td><th style="width: 100px">완속충전기</th><td>'+item.s_char+'개</td></tr>';
				    contentString +='<tr><th style="width: 100px">충전가능차량</th><td colspan="3">'+item.sup_veh+'</td></tr>';
				    contentString +='</tbody></table></div>';
					var infowindow = new naver.maps.InfoWindow({
					    content: contentString
					});
				    if (infowindow.getMap()) {
				        infowindow.close();
				    } else {
				        infowindow.open(map, marker);
				    }
			   })
			
		 /*  naver.maps.Event.addListener(map, 'zoom_changed', function() {
			    updateMarkers(map, markers);
			    });

		  naver.maps.Event.addListener(map, 'dragend', function() {
			    updateMarkers(map, markers);
			    }); */
	})
}}) 

}//mapmarker
</script>   
