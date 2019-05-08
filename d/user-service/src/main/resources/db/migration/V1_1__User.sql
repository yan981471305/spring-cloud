DROP TABLE IF EXISTS article ;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` date NOT NULL,
  `name` varchar(16) NOT NULL,
  `age` varchar(16) DEFAULT NULL,
  `text` longtext NOT NULL,
  `title` varchar(255) NOT NULL,
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
