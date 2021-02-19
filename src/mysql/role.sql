CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO role (id, name)
VALUES (1, 'ADMIN');

INSERT INTO role (id, name)
VALUES (2, 'USER');
