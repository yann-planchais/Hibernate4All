Organisation du cours et du projet 

1] Partie 1 : hibernate et ses états 
	transcient/managé/détaché/supprimé
	=> 	On s'appuie sur les objets Movie
	
2] Partie 2: partie dirty checking
   => On s'appoue sur les objets MovieWithDescription
   
3] Partie 3 : Mapping
	- explication du hashcode & Equals 
		https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
		https://docs.jboss.org/hibernate/stable/core.old/reference/fr/html/persistent-classes-equalshashcode.html
		quand clé fonctionnelle : on utilise la clé fonctionnelle comme hashcode et equals 
			ex : name pour la classe Genre
		quand pas de clé : on utilise id et/ou les différents champs de l'entité 
			ex : movieWithDescription

	- List/Set
		1) version hibernate < 5.0.8 => bannir les listes car bug
		2) 	lors d'un ajout, avec un Set il doit recuperer tous les objets pour vérifier l'unicité
		  avec une liste, il ajoute simplement.
		3) relation manyToMany => mieux Set car avec une liste, lors d'une suppression il supprime tout et réinsert les objets
		
	- Association Ternaire 
		Movie 1-----n movie_actor n-----1 Actor
		                 character
		 * liaison oneToMany dans les classes Movie et Actor
		 * classe MovieActor avec classe interne MovieActorId pour la cléId
		 * embeddedId pour la clé primaire de cette table movie_actor
4] HERITAGE
	1) 1 table par classe concrete => une table FILM et une table SERIE
	  - classe abtraite Watchable contenant name & description
		@MappedSuperclass 
	  - classe concrete HeritageConcreteFilm & HeritageConcreteSerie 
	  - @GeneratedId dans chaque classe concrete
	  
	  Inconvenient : 
	  	- 1 select par classe concrete
	  	- pas d'association dans la classe abstraite
	  Avantage : simple à mettre en oeuvre
	  
	2) garde même schema BDD que solution 1 
		- dans classe abstraite : @GeneratedId & @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
			=> attention à modifier dans le .sql l'id des inserts pour les classes concretes : 
				sinon collision car l'id est porté par la classe abstraite 
		Avantage:
			- requete plus simple car la classe abstraite est une entité
			- on peut demander dans la requête les associations
			- un seul select avec union au lieu de 2 selects distincts
		Inconvénient
			- select un peu compliqué à debugger (rajoute des null as colonne_non_existante && union de x select selon nb classes concretes)	
		
		 