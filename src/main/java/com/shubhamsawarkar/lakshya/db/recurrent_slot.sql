CREATE TABLE `recurrent_slot` (
  slot_id 					bigint NOT NULL AUTO_INCREMENT,
  `day` 				    tinyint NOT NULL,
  day_type                  enum('DAY_OF_THE_MONTH', 'DAY_OF_THE_WEEK'),
  start_time 				time NOT NULL,
  end_time					time NOT NULL,
  activity_id				bigint NOT NULL,
  is_active 				bit(1) NOT NULL DEFAULT 1,
  PRIMARY KEY 				(slot_id),
  KEY idx_slot_range		(`day`, start_time, end_time),
  CONSTRAINT recurrent_slot_activity_fk FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`)
);