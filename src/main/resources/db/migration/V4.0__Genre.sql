create sequence Genre_SEQ start 1 increment 50;
create table Genre (id int8 not null, name varchar(255), primary key (id));
create table Movie_genre (movie_id int8 not null, genre_id int8 not null, primary key (movie_id, genre_id));
alter table Movie_genre add constraint fk_moviegenre_genre foreign key (genre_id) references Genre;
alter table Movie_genre add constraint fk_moviegenre_moviewithdescription foreign key (movie_id) references MovieWithDescription;
