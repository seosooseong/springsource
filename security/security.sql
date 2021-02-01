--admin90 ROLE_MEMBER 권한 부여 210201
INSERT INTO spring_member_auth values('admin90','ROLE_MEMBER');

-- 스프링 시큐리티에서 remembr me 를 위해 기본적으로 구현하는 테이블

create table persistent_logins(
	username varchar2(64) not null,
	series varchar2(64) not null,
	token varchar2(64) not null,
	last_used timestamp not null
);


create table spring_member(
	userid varchar2(50) not null primary key,
	userpw varchar2(100) not null,
	username varchar2(100) not null,
	regdate date default sysdate,
	updatedate date default sysdate,
	enabled char(1) default '1'
	);

create table spring_member_auth(
	userid varchar2(50) not null,
	auth varchar2(50) not null,
	constraint fk_member_auth foreign key(userid) references spring_member(userid)
);

--테이블 삭제
drop table spring_member;
drop table spring_member_auth;



select * from spring_member;
select * from spring_member_auth;

 admin90 $2a$10$kianTlVrvxM8SxlYuS83veaYkIi/1l8wX5iEOb97SY.RJPjIJwMxW rhksflwk90 2021-01-29 12:42:04.0 2021-01-29 12:42:04.0 1

select * from spring_member where userid ='admin90';

select * from spring_member_auth where userid ='admin90';