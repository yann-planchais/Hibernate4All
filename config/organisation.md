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