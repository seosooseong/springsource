
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
(1,'기아','니로 EV','Niro EV','소형차',47800000,49800000,2019,5.3,385,'DC콤보',
'세상에 없던 SUV의 시작','니로가 이룬 또 한번의 혁신. 니로 EV가 스마트하게 앞서갑니다');









