CREATE TABLE `activity` (
  `activity_id`         bigint NOT NULL AUTO_INCREMENT,
  `goal_id`             bigint DEFAULT NULL,
  `category`            enum('SLEEPING','EATING','ON_DUTY','LEARNING','PROJECT_WORK') NOT NULL,
  `title`               varchar(255) NOT NULL,
  `description`         longtext,
  `progress`            smallint NOT NULL DEFAULT '0',
  `length`              smallint NOT NULL DEFAULT '100',
  PRIMARY KEY           (`activity_id`),
  KEY `goal_id`         (`goal_id`),
  CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal` (`goal_id`)
);