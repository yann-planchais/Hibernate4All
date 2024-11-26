create sequence Award_SEQ start 1 increment 50;
create table Award (id int8 not null, name varchar(255) not null, year smallint,movie_id int8, primary key (id));
alter table Award add constraint fk_award_moviewithdescription foreign key (movie_id) references MovieWithDescription;
