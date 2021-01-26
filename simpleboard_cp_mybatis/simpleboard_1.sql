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