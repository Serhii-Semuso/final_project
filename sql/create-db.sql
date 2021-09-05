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
  `name` VARCHAR(20) NOT NULL DEFAULT 'client',
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
  `password` VARCHAR(32) NOT NULL,
  `role_id` INT NOT NULL,
  `first_name` VARCHAR(20) NULL,
  `last_name` VARCHAR(20) NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone_number` VARCHAR(15) NULL,
  `is_blocked` TINYINT(1) NOT NULL DEFAULT 0,
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
-- Table `paymentsdb`.`credit_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`credit_card` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`credit_card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` CHAR(16) NOT NULL,
  `limit` DECIMAL(13,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymentsdb`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentsdb`.`account` ;

CREATE TABLE IF NOT EXISTS `paymentsdb`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `balance` DECIMAL(13,2) NOT NULL,
  `creation_date` DATETIME NOT NULL,
  `is_blocked` TINYINT(1) NOT NULL DEFAULT 0,
  `credit_card_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `unblock_request` TINYINT(1) NULL,
  PRIMARY KEY (`id`, `credit_card_id`),
  INDEX `fk_accounts_credit_cards1_idx` (`credit_card_id` ASC) VISIBLE,
  INDEX `fk_account_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_credit_cards1`
    FOREIGN KEY (`credit_card_id`)
    REFERENCES `paymentsdb`.`credit_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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
  `amount` DECIMAL(13,2) NOT NULL,
  `destination_account_number` INT NOT NULL,
  `description` VARCHAR(255) NULL,
  `creation_date` DATETIME NOT NULL,
  `sent_date` DATETIME NOT NULL,
  `account_id` INT NOT NULL,
  `payment_status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `payment_status_id`),
  INDEX `fk_payments_accounts1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_payment_payment_status1_idx` (`payment_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_payments_accounts1`
    FOREIGN KEY (`account_id`)
    REFERENCES `paymentsdb`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_payment_status1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `paymentsdb`.`payment_status` (`id`)
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
  `languages_id` INT NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`payment_status_id`, `languages_id`),
  INDEX `fk_payment_statuses_has_languages_languages1_idx` (`languages_id` ASC) VISIBLE,
  INDEX `fk_payment_statuses_has_languages_payment_statuses1_idx` (`payment_status_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_statuses_has_languages_payment_statuses1`
    FOREIGN KEY (`payment_status_id`)
    REFERENCES `paymentsdb`.`payment_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_statuses_has_languages_languages1`
    FOREIGN KEY (`languages_id`)
    REFERENCES `paymentsdb`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
