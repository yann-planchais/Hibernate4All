create sequence Movie_details_SEQ start 1 increment 50;
create table Movie_details (plot varchar(4000), movie_id int8 not null, primary key (movie_id));
alter table Movie_details add constraint fk_moviedetails_moviewithdescription foreign key (movie_id) references MovieWithDescription;
