truncate table Review, MovieWithDescription, movie_details, genre, movie_genre;


insert into MovieWithDescription (name, certification, description, id) values ('Inception',1,'desc Inception', -1);
insert into MovieWithDescription (name, certification, description, id) values ('Memento', 2,'desc Memento', -2);

insert into Review (id, movie_id, author, content) values (-1, -1 , 'max', 'super film de max' );
insert into Review (id, movie_id, author, content) values (-2, -1 , 'tom', 'super film de tom' );
