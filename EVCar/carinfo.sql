
create table carinfo(
		bno number(10,0),
		brand VARCHAR2(30) not null,
		carname VARCHAR2(30) not null,
		carname_en VARCHAR2(30) not null,
		rowprice number(10,2) not null,
		highprice number(10,2) not null,
		releasedate number(10,2) not null,
		fuel number(10,2) not null,
		title nVARCHAR2(20) not null,
		content VARCHAR2(300) not null	
);


alter table carinfo add constraint carinfo_board primary key(bno);
--create sequence seq_board;

select * from carinfo;

insert into carinfo (bno,brand,carname,carname_en,
rowprice,highprice,releasedate,fuel,title,content) values 
(1,'현대','코나 일렉트릭','KONA Electric',46900000,48900000,2020,22.4,
'모빌리티 라이프의 즐거움','한 번 충전으로 406km를 달리다.');



