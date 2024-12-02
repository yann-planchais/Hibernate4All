SET REFERENTIAL_INTEGRITY FALSE;
truncate table Genre;
truncate table Review;
truncate table MovieWithDescription;
SET REFERENTIAL_INTEGRITY TRUE;

insert into MovieWithDescription (name, certification, description, id) values ('Inception',1,'desc Inception', -1);
insert into MovieWithDescription (name, certification, description, id) values ('Memento', 2,'desc Memento', -2);