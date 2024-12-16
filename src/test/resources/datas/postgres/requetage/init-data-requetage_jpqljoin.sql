truncate table requetagejpqljoinmovie_requetagejpqljoingenre, RequetageJPQLJoinReview, RequetageJPQLJoinMovie, RequetageJPQLJoinGenre, movie_genre, movie_details,RequetageJPQLJoinAward;


insert into RequetageJPQLJoinMovie (name, certification, description, id) values ('Inception',1,'desc Inception', -1);
insert into RequetageJPQLJoinMovie (name, certification, description, id) values ('Memento', 2,'desc Memento', -2);

insert into RequetageJPQLJoinReview (id, movie_id, author, content) values (-1, -1 , 'max', 'super film de max' );
insert into RequetageJPQLJoinReview (id, movie_id, author, content) values (-2, -1 , 'tom', 'super film de tom' );

insert into RequetageJPQLJoinGenre (id, name) values (-1, 'Action');
insert into requetagejpqljoinmovie_requetagejpqljoingenre (movie_id, genre_id) values (-1, -1);

insert into RequetageJPQLJoinAward (name, annee, movie_id, id) values ('Best Cine', 2011,-1,-1);
insert into RequetageJPQLJoinAward (name, annee, movie_id, id) values ('Best Visual Effect', 2011,-1,-2);
insert into RequetageJPQLJoinAward (name, annee, movie_id, id) values ('Best Sound Editing', 2011,-1,-3);
insert into RequetageJPQLJoinAward (name, annee, movie_id, id) values ('Best Sound Mixing', 2011,-1,-4);


