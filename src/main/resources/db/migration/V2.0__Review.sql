create sequence Review_SEQ start 1 increment 50;
create table Review (id int8 not null, author varchar(255), content varchar(255), movie_id int8, primary key (id));
alter table Review add constraint fk_review_moviewithdescription foreign key (movie_id) references MovieWithDescription;

