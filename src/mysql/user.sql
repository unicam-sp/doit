CREATE TABLE `user` (
  `id` int AUTO_INCREMENT NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(255) NOT NULL DEFAULT '^[^@]+@[^.]+[.][^.]{2,}([.][^.]{2,})*$',
  PRIMARY KEY (`id`)
);

INSERT INTO user (username, password, email)
VALUES ('admin', 'admin', 'email@gg.com');