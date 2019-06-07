create table member(
id varchar(30) not null ,
name varchar(30) not null ,
age varchar(5) not null,
tel varchar(20) not null
);


insert into member values('hong','jooahn','26','010-7878-9898');

select * from member;


delete from member;
drop table member;