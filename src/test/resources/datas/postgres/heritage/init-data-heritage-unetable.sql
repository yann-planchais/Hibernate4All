truncate table AbstraiteUneSeuleTableProduction, HeritageUneSeuleTableReview;

insert into AbstraiteUneSeuleTableProduction (bd_type, name, description, certification, id) values ('film','Inception','desc Inception',1, -1);

insert into AbstraiteUneSeuleTableProduction (bd_type,name, description, nombreSaisons, id) values ('serie','Stargate','avec Richard Dean Anderson',13, -2);
insert into AbstraiteUneSeuleTableProduction (bd_type,name, description, nombreSaisons, id) values ('serie','Friends','super serie',9,  -3);


insert into HeritageUneSeuleTableReview (id, AbstraiteUneSeuleTableProduction_id, author, content) values (-1, -1 , 'max', 'super film de max' );