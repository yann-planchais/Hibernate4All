create sequence MovieWithDescription_SEQ start 1 increment 50;
create table MovieWithDescription (id int8 not null, certification int4, description varchar(255), name varchar(255) not null, primary key (id));
