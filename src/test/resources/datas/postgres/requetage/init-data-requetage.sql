truncate table RequetageReview, RequetageMovieWithDescription, RequetageGenre, movie_genre;


insert into RequetageMovieWithDescription (name, certification, description, id) values ('Inception',1,'desc Inception', -1);
insert into RequetageMovieWithDescription (name, certification, description, id) values ('Memento', 2,'desc Memento', -2);

insert into RequetageReview (id, movie_id, author, content) values (-1, -1 , 'max', 'super film de max' );
insert into RequetageReview (id, movie_id, author, content) values (-2, -1 , 'tom', 'super film de tom' );

insert into RequetageGenre (id, name) values (-1, 'Action');