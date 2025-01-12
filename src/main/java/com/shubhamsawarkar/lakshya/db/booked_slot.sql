CREATE TABLE `booked_slot` (
  slot_id 					bigint NOT NULL AUTO_INCREMENT,
  `date` 					date NOT NULL,
  start_time 				time NOT NULL,
  end_time					time NOT NULL,
  activity_id				bigint NOT NULL,
  progress_from 			smallint NOT NULL,
  progress_to				smallint NOT NULL,
  is_finalized 				bit(1) NOT NULL DEFAULT 0,
  PRIMARY KEY 				(slot_id),
  KEY activity_id 			(activity_id),
  KEY idx_slot_range		(`date`, start_time, end_time),
  CONSTRAINT booked_slot_activity_fk FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`)
);