-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paymentsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `paymentsdb` ;

-- -----------------------------------------------------
-- Schema paymentsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymentsdb` DEFAULT CHARACTER SET utf8 ;
USE `paymentsdb` ;

-- -----------------------------------------------------
-- Table `paymentsdb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`role` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`user` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `role_id` INT NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `is_blocked` TINYINT(1) NOT NULL DEFAULT 0,
  `locale_name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`, `role_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  INDEX `fk_users_roles1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `paymentsdb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`account` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(30) NOT NULL DEFAULT '',
  `name` VARCHAR(255) NOT NULL,
  `balance` DECIMAL(13,2) NOT NULL DEFAULT 0,
  `creation_date` DATETIME NOT NULL DEFAULT NOW(),
  `is_blocked` TINYINT(1) NOT NULL DEFAULT 0,
  `user_id` INT NOT NULL,
  `unblock_request` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_account_user1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  CONSTRAINT `fk_account_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `paymentsdb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`payment_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`payment_status` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`payment_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`payment` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(30) NOT NULL DEFAULT '',
  `amount` DECIMAL(13,2) NOT NULL,
  `description` VARCHAR(255) NULL,
  `creation_date` DATETIME NOT NULL DEFAULT NOW(),
  `sent_date` DATETIME NULL,
  `account_id_from` INT NOT NULL,
  `account_id_to` INT NOT NULL,
  `payment_status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `payment_status_id`),
  INDEX `fk_payments_accounts1_idx` (`account_id_from` ASC) VISIBLE,
  INDEX `fk_payment_payment_status1_idx` (`payment_status_id` ASC) VISIBLE,
  INDEX `fk_payment_account1_idx` (`account_id_to` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  CONSTRAINT `fk_payments_accounts1`
    FOREIGN KEY (`account_id_from`)
    REFERENCES `paymentsdb`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_payment_status1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `paymentsdb`.`payment_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_account1`
    FOREIGN KEY (`account_id_to`)
    REFERENCES `paymentsdb`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`language` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(2) NOT NULL,
  `full_name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `short_name_UNIQUE` (`short_name` ASC) VISIBLE,
  UNIQUE INDEX `full_name_UNIQUE` (`full_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`status_has_language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`status_has_language` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`status_has_language` (
  `payment_status_id` INT NOT NULL,
  `language_id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`payment_status_id`, `language_id`),
  INDEX `fk_payment_statuses_has_languages_languages1_idx` (`language_id` ASC) VISIBLE,
  INDEX `fk_payment_statuses_has_languages_payment_statuses1_idx` (`payment_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_statuses_has_languages_payment_statuses1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `paymentsdb`.`payment_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_statuses_has_languages_languages1`
    FOREIGN KEY (`language_id`)
    REFERENCES `paymentsdb`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`credit_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`credit_card` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`credit_card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(30) NOT NULL DEFAULT '0000',
  `limit` DECIMAL(13,2) NOT NULL DEFAULT 0.0,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  INDEX `fk_credit_card_account1_idx` (`account_id` ASC) VISIBLE,
  CONSTRAINT `fk_credit_card_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `paymentsdb`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`counter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`counter` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`counter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

USE `paymentsdb`;

DELIMITER $$

USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`account_BEFORE_INSERT` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`account_BEFORE_INSERT` BEFORE INSERT ON `account` FOR EACH ROW
BEGIN
	DECLARE string_date CHAR(12);	
    SET NEW.creation_date = NOW();
    SET string_date = DATE_FORMAT(NEW.creation_date, '%d%m%y%H%i%S');
    SET NEW.number = CONCAT(string_date, (SELECT `value` FROM counter));	
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`account_BEFORE_UPDATE` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`account_BEFORE_UPDATE` BEFORE UPDATE ON `account` FOR EACH ROW
BEGIN
	SET NEW.creation_date = OLD.creation_date;
    SET NEW.`number` = OLD.`number`;
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`payment_BEFORE_INSERT` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`payment_BEFORE_INSERT` BEFORE INSERT ON `payment` FOR EACH ROW
BEGIN
	DECLARE string_date CHAR(12);
	SET NEW.creation_date = NOW();
    SET string_date = DATE_FORMAT(NEW.creation_date, '%d%m%y%H%i%S');
    SET NEW.number = CONCAT(string_date, (SELECT `value` FROM counter));
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`payment_BEFORE_UPDATE` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`payment_BEFORE_UPDATE` BEFORE UPDATE ON `payment` FOR EACH ROW
BEGIN
	SET NEW.creation_date = OLD.creation_date;
    SET NEW.`number` = OLD.`number`;
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`credit_card_BEFORE_INSERT` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`credit_card_BEFORE_INSERT` BEFORE INSERT ON `credit_card` FOR EACH ROW
BEGIN
	DECLARE string_date CHAR(12);	
    DECLARE `date` DATETIME;	
    SET `date` = NOW();
    SET string_date = DATE_FORMAT(`date`, '%d%m%y%H%i%S');
    SET NEW.number = CONCAT(string_date, (SELECT `value` FROM counter));	
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`credit_card_BEFORE_UPDATE` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`credit_card_BEFORE_UPDATE` BEFORE UPDATE ON `credit_card` FOR EACH ROW
BEGIN
	SET NEW.`number` = OLD.`number`;
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`counter_AFTER_INSERT_account` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`counter_AFTER_INSERT_account` AFTER INSERT ON `account` FOR EACH ROW
BEGIN	
	UPDATE `paymentsdb`.`counter` SET `value` = `value` + 1 WHERE id=1;
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`counter_AFTER_INSERT_payment` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`counter_AFTER_INSERT_payment` AFTER INSERT ON `payment` FOR EACH ROW
BEGIN
	UPDATE `paymentsdb`.`counter` SET `value` = `value` + 1 WHERE id=1;
END$$


USE `paymentsdb`$$
DROP TRIGGER IF EXISTS `paymentsdb`.`counter_AFTER_INSERT_credit_card` $$
USE `paymentsdb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `paymentsdb`.`counter_AFTER_INSERT_credit_card` AFTER INSERT ON `credit_card` FOR EACH ROW
BEGIN
	UPDATE `paymentsdb`.`counter` SET `value` = `value` + 1 WHERE id=1;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `paymentsdb`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`role` (`id`, `name`) VALUES (2, 'admin');
INSERT INTO `paymentsdb`.`role` (`id`, `name`) VALUES (1, 'client');

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (1, 'test', 'test', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (2, 'test1', 'test1', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (3, 'test2', 'test2', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (4, 'test3', 'test3', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (5, 'test4', 'test4', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (6, 'test5', 'test5', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (7, 'test6', 'test6', 1, 'Test', 'Tets', 'test@test', '12334', 0, 'en');
INSERT INTO `paymentsdb`.`user` (`id`, `login`, `password`, `role_id`, `first_name`, `last_name`, `email`, `phone_number`, `is_blocked`, `locale_name`) VALUES (8, 'admin', 'admin', 2, 'admin', 'admin', 'admin@admin', '12334', 0, 'en');

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (1, DEFAULT, 'account1', , DEFAULT, DEFAULT, 1, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (2, DEFAULT, 'account2', , DEFAULT, DEFAULT, 2, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (3, DEFAULT, 'account3', , DEFAULT, DEFAULT, 3, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (4, DEFAULT, 'account12', , DEFAULT, DEFAULT, 1, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (5, DEFAULT, 'account13', , DEFAULT, DEFAULT, 1, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (6, DEFAULT, 'account4', , DEFAULT, DEFAULT, 4, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (7, DEFAULT, 'account5', , DEFAULT, DEFAULT, 5, NULL);
INSERT INTO `paymentsdb`.`account` (`id`, `number`, `name`, `balance`, `creation_date`, `is_blocked`, `user_id`, `unblock_request`) VALUES (8, DEFAULT, 'account6', , DEFAULT, DEFAULT, 6, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`payment_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`payment_status` (`id`) VALUES (1);
INSERT INTO `paymentsdb`.`payment_status` (`id`) VALUES (2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (1, DEFAULT, 100, NULL, DEFAULT, NULL, 1, 2, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (2, DEFAULT, 100, NULL, DEFAULT, NULL, 3, 4, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (3, DEFAULT, 100, NULL, DEFAULT, NULL, 5, 6, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (4, DEFAULT, 100, NULL, DEFAULT, NULL, 7, 8, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (5, DEFAULT, 100, NULL, DEFAULT, NULL, 8, 1, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (6, DEFAULT, 100, NULL, DEFAULT, NULL, 1, 2, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (7, DEFAULT, 100, NULL, DEFAULT, NULL, 3, 4, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (8, DEFAULT, 100, NULL, DEFAULT, NULL, 5, 6, 1);
INSERT INTO `paymentsdb`.`payment` (`id`, `number`, `amount`, `description`, `creation_date`, `sent_date`, `account_id_from`, `account_id_to`, `payment_status_id`) VALUES (9, DEFAULT, 100, NULL, DEFAULT, NULL, 7, 8, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`language`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`language` (`id`, `short_name`, `full_name`) VALUES (1, 'uk', 'Ukrainian');
INSERT INTO `paymentsdb`.`language` (`id`, `short_name`, `full_name`) VALUES (2, 'en', 'English');

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`status_has_language`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`status_has_language` (`payment_status_id`, `language_id`, `name`) VALUES (1, 1, 'підготовлений');
INSERT INTO `paymentsdb`.`status_has_language` (`payment_status_id`, `language_id`, `name`) VALUES (2, 1, 'відправлений');
INSERT INTO `paymentsdb`.`status_has_language` (`payment_status_id`, `language_id`, `name`) VALUES (1, 2, 'prepared');
INSERT INTO `paymentsdb`.`status_has_language` (`payment_status_id`, `language_id`, `name`) VALUES (2, 2, 'sent');

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`credit_card`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (1, DEFAULT, 1000, 1);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (2, DEFAULT, 1000, 2);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (3, DEFAULT, 1000, 3);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (4, DEFAULT, 1000, 4);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (5, DEFAULT, 1000, 5);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (6, DEFAULT, 1000, 6);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (7, DEFAULT, 1000, 7);
INSERT INTO `paymentsdb`.`credit_card` (`id`, `number`, `limit`, `account_id`) VALUES (8, DEFAULT, 1000, 8);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentsdb`.`counter`
-- -----------------------------------------------------
START TRANSACTION;
USE `paymentsdb`;
INSERT INTO `paymentsdb`.`counter` (`id`, `value`) VALUES (DEFAULT, 0);

COMMIT;

