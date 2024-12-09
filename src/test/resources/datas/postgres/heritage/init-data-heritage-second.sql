truncate table HeritageSecondeSolutionFilm, HeritageSecondeSolutionSerie, SecondReview;

insert into HeritageSecondeSolutionFilm (name, description, certification, id) values ('Inception','desc Inception',1, -1);

insert into HeritageSecondeSolutionSerie (name, description, nombreSaisons, id) values ('Stargate','avec Ricard Dean Anderson',13, -2);
insert into HeritageSecondeSolutionSerie (name, description, nombreSaisons, id) values ('Friends','super serie',9,  -3);


insert into SecondReview (id, HeritageSecondeSolutionFilm_id, author, content) values (-1, -1 , 'max', 'super film de max' );