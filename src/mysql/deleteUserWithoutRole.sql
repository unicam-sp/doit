# non posso eliminare un utente da user
# se prima non lo elimino anche dalle tabelle "esperto" e "progettista"

DELETE doit.esperto
FROM doit.esperto 
	JOIN doit.progettista ON esperto.id = progettista.id 
		JOIN user ON progettista.id = user.id
			LEFT JOIN user_role ON user.id = user_role.user_id
WHERE role_id IS NULL;

DELETE doit.user
FROM user LEFT JOIN user_role ON user.id = user_role.user_id
WHERE role_id IS NULL