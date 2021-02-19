CREATE TABLE `userRole` (
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  CONSTRAINT `con_fk_user` FOREIGN KEY (`id_user`) REFERENCES doit.user(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `con_fk_role` FOREIGN KEY (`id_role`) REFERENCES doit.role(id)
		ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO userRole (id_user, id_role)
VALUES (1,1);