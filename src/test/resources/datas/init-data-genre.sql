SET REFERENTIAL_INTEGRITY FALSE;
truncate table Genre;
truncate table Movie_Genre;
truncate table MovieWithDescription;
SET REFERENTIAL_INTEGRITY TRUE;

insert into MovieWithDescription (name, certification, description, id) values ('Inception',1,'desc Inception', -1);
insert into MovieWithDescription (name, certification, description, id) values ('Memento', 2,'desc Memento', -2);


insert into Genre (id, name) values (-1, 'Action');