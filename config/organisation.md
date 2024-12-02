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
		