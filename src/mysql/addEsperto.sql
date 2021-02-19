INSERT INTO user (email, password, username)
VALUES ("email", "password", "username");

INSERT INTO progettista (id)
VALUES (2);

INSERT INTO esperto (id, nome, cognome)
VALUES (2, "Giacomo", "Rossi");

# SELECT * 
# FROM doit.esperto JOIN doit.progettista ON esperto.id = progettista.id JOIN user ON progettista.id = user.id