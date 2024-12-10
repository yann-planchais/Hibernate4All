truncate table AbstraiteCleEtrangereProduction,HeritageCleEtrangereFilm, HeritageCleEtrangereSerie, HeritageCleEtrangereReview;

insert into AbstraiteCleEtrangereProduction (name, description, id) values ('Inception','desc Inception', -1);
insert into AbstraiteCleEtrangereProduction (name, description, id) values ('Friends','super serie', -2);
insert into AbstraiteCleEtrangereProduction (name, description, id) values ('Stargate','avec Richard Dean Anderson', -3);

insert into HeritageCleEtrangereFilm (certification, id) values (1, -1);
insert into HeritageCleEtrangereSerie (nombreSaisons, id) values (9,  -2);
insert into HeritageCleEtrangereSerie (nombreSaisons, id) values (13,  -3);


insert into  HeritageCleEtrangereReview (id, AbstraiteCleEtrangereProduction_id, author, content) values (-1, -1 , 'max', 'super film de max' );