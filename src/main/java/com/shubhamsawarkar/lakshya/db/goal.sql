CREATE TABLE `goal` (
  `goal_id`         bigint NOT NULL AUTO_INCREMENT,
  `title`           varchar(255) NOT NULL,
  `description`     longtext,
  PRIMARY KEY       (`goal_id`)
);