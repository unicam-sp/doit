INSERT INTO role (id, name) VALUES
	(0, 'ADMIN'),
    (1, 'MODERATORE'),
	(2, 'USER'),
	(3, 'PROPOSITORE'),
    (4, 'PROGETTISTA'),
    (5, 'RECRUITER'),
    (6, 'ESPERTO');
    
INSERT INTO `doit`.`user` (`id`, `password`, `username`) VALUES ('0', 'admin', 'admin');
INSERT INTO `doit`.`user_role` (`user_id`, `role_id`) VALUES ('0', '0');

