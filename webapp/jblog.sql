create table users(
    userNo number,
    id varchar2(50) not null unique,
    userName varchar2(100) not null,
    password varchar2(50) not null,
    joinDate date not null,
    primary key(userNo)
);

select *
from users;

create table blog(
    id varchar2(50),
    blogTitle varchar2(200) not null,
    logoFile varchar2(200),
    primary key(id),
    constraint fk_blog foreign key (id)
    references users(id)
);

select *
from blog;

create table category(
    cateNo number,
    id varchar2(50),
    cateName varchar2(200) not null,
    description varchar2(500),
    regDate date not null,
    primary key (cateNo),
    constraint fk_category foreign key (id)
    references blog(id)
);

select *
from category;

create table post(
    postNo number,
    cateNo number,
    postTitle varchar2(300) not null,
    postContent varchar2(4000),
    regDate date not null,
    primary key(postNo),
    constraint fk_post foreign key (cateNo)
    references category(cateNo)
);

select *
from post;

create table comments(
    cmtNo number,
    postNo number,
    userNo number,
    cmtContent varchar2(1000) not null,
    regDate date not null,
    primary key(cmtNo),
    constraint fk_comments_p foreign key (postNo)
    references post(postNo),
    constraint fk_comments_u foreign key (userNo)
    references users(userNo)
);

select *
from comments;

sequence seq_users_no;
sequence seq_category_no;
sequence seq_post_no;
sequence seq_comments_no;


CREATE SEQUENCE seq_comments_no
INCREMENT BY 1
START WITH 1 
NOCACHE;


insert into users values(1, '123', '123', '123', sysdate);

insert into category values(1, '123', '123', '123', sysdate);