create sequence seq_board;

drop table notice_board;

create table notice_board (
	bno NUMBER(10,0),
	title VARCHAR2(100) not null,
	writer VARCHAR2(30) not null,
	content VARCHAR2(2000) not null,
	regDate DATE default sysdate,
	updateDate DATE default sysdate,
	readCnt NUMBER DEFAULT 0
);
	
alter table notice_board add constraint pk_board primary key(bno);

select * from notice_board;

--더미값
insert into notice_board (bno, title, writer, content)
values(seq_board.nextval, '공지사항 제목', '공지사항 작성자', '공지사항 내용');