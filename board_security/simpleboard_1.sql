--user0 ~79, manager80~89, admin90~99
select * from spring_member;
select * from spring_member_auth;



-- 파일 첨부 테이블 생성
create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) not null,
	bno number(10)
);

--잘못 설정해서 삭제함.
--alter table spring_attach add constraint pk_attach primary key(bno);
--alter table spring_attach drop constraint pk_attach;

--기본키 설정
alter table spring_attach add constraint pk_attach primary key(uuid);

--bno값은 spring_board(bno) 기준
alter table spring_attach
add constraint fk_board_attach foreign key(bno) references spring_board(bno);



select * from spring_attach;


drop table spring_attach;
-- 목록에서 댓글수 보여주기
-- 댓글 수 를 저장할 컬럼 생성

alter table spring_board add(replycnt number default 0);

-- 이미 들어간 댓글수 삽입
update spring_board
set replycnt = (select count(rno) from spring_reply where spring_board.bno = spring_reply.bno);

select*from spring_board order by bno desc;






--댓글 테이블

create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, --댓글 글번호
	bno number(10,0) not null,                        --원본글 글번호
	reply varchar2(1000) not null, 					  --댓글
	replyer varchar2(50) not null, 					  --댓글 작성자
	replyDate date default sysdate,					  --댓글 작성일
	updateDate date default sysdate, 				  --댓글 수정일
	constraint fk_reply_board foreign key(bno) references spring_board(bno)); --외래키 설정


drop table spring_reply;
select * from spring_reply;

create sequence seq_reply;
drop sequence seq_reply;


-- 인덱스 생성
create index idx_reply on spring_reply(bno desc,rno asc);









--page 나누기 : rownum

select rownum, bno, title from spring_board where rownum <= 10;

--가장 최신글 10개 가져오기
select rownum, bno, title
from (select bno,title from spring_board order by bno desc)
where rownum<=10;


select /*+INDEX_DESC(spring_board pk_spring_board)*/rownum, bno, title
from spring_board
where rownum<=10;

--1페이지 -> 가장 최신글
select rn,bno, title
from (select rownum rn, bno, title
from (select bno,title from spring_board order by bno desc)
	 where rownum<=10)
where rn > 0;

--2페이지 -> 그 다음 최신글
select rn,bno, title
from (select rownum rn, bno, title
from (select bno,title from spring_board order by bno desc)
	 where rownum<=20)
where rn > 10;

---------
--1페이지 -> 가장 최신글

select rn,bno, title
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title
     from spring_board where rownum<=10)
where rn > 10;

--2페이지 -> 그 다음 최신글
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
      from spring_board where rownum<=20)
where rn > 10;


--페이지 번호가 넘어오는 경우
--1페이지 10,0  / 10(page번호*10),0(page번호 -1)*10 / 10(page번호*현재page 게시글수),0(page번호 -1)*현재page 게시글수
--2페이지 20,10 /

select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
      from spring_board where rownum<=20)
where rn > 10;

--더미 데이터 (페이지 나누기)
insert into spring_board(bno, title, content, writer)
(select seq_board.nextval,title, content, writer from spring_board);



--title 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
      from spring_board where (title like '%테스트%') and rownum<=10)
where rn > 0;

--title or content 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
      from spring_board 
      where (title like '%테스트%' or content like '%테스트%')
      and rownum<=10)
where rn > 0;

--title or content or writer 검색
select *
from (select /*+INDEX_DESC(spring_board pk_spring_board)*/
	  rownum rn, bno, title, writer, regdate, updatedate
      from spring_board 
      where (title like '%테스트%' or content like '%테스트%' or writer like '%서수성%')
      and rownum<=10)
where rn > 0;




select * from spring_board;
--------------------------------------------------------------------------------------

create table spring_board(
		bno number(10,0),
		title varchar2(200) not null,
		content varchar2(200) not null,
		writer varchar2(200) not null,
		regdate date default sysdate,
		updatedate date default sysdate
);

alter table spring_board add constraint pk_spring_board primary key(bno);
create sequence seq_board;

delete from spring_board where bno=5;
delete from spring_board where bno=6;
delete from spring_board where bno=7;
delete from spring_board where bno=8;
delete from spring_board where bno=2;
delete from spring_board where bno=4;
select * from spring_board;


select * from spring_board where >0 order by bno desc;


-------------------------------------------------------------------











