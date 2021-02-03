
create table carinfo(
		bno number primary key,
		brand VARCHAR2(30) not null,	   --브랜드
		carname VARCHAR2(30) not null,	   --차명
		carname_en VARCHAR2(30) not null,  --차명영어
		cartype VARCHAR2(30) not null,	   --종류
		rowprice number(10,2) not null,	   --최저가
		highprice number(10,2) not null,   --최고가
		releasedate number(10,2) not null, --출시일
		fuel number(10,2) not null,        --연비 km/kWh
		mileage number(10,2) not null,     --주행가능거리
		chargetype VARCHAR2(30) not null,  --급속충전타입
		title VARCHAR2(100) not null, 	   --타이틀
		content VARCHAR2(500) not null	   --내용,제원
);

create sequence carinfo_seq;

drop sequence carinfo_seq;
drop table carinfo;

select * from carinfo;

insert into carinfo (bno,brand,carname,carname_en,cartype,
rowprice,highprice,releasedate,fuel,mileage,chargetype, title,content) values 
(carinfo_seq.nextval,'현대','코나 일렉트릭','KONA Electric','소형차',46900000,48900000,2020,5.6,406,'DC콤보',
'모빌리티 라이프의 즐거움','한 번 충전으로...');

insert into carinfo (bno,brand,carname,carname_en,cartype,
rowprice,highprice,releasedate,fuel,mileage,chargetype, title,content) values 
(carinfo_seq.nextval,'현대','포터 일렉트닉','PorterEV','중형차',40600000,42740000,2020,3.1,211,'DC콤보',
'새로운 친환경 EV 트럭','도심에서 탁월한 운송능력...');

insert into carinfo (bno,brand,carname,carname_en,cartype,
rowprice,highprice,releasedate,fuel,mileage,chargetype, title,content) values 
(carinfo_seq.nextval,'현대','아이오닉','Ioniq','중형차',41400000,44400000,2019,22.4,271,'DC콤보',
'새로운 생각이 가치를 창조한다','국내 첫 전기차...');








