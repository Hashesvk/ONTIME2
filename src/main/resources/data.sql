INSERT INTO "rol" (ID,
																				AUTHORIRY)
SELECT 1,
	'ROLE_JOVEN'
WHERE NOT EXISTS
		(SELECT ID
			FROM "rol" 
			WHERE ID = 1 );


INSERT INTO "rol" (ID,
																				AUTHORIRY)
SELECT 2,
	'ROLE_ADULTO'
WHERE NOT EXISTS
		(SELECT ID
			FROM "rol" 
			WHERE ID = 2 );