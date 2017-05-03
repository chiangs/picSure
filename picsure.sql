-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema picsure
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `picsure` ;

-- -----------------------------------------------------
-- Schema picsure
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `picsure` DEFAULT CHARACTER SET utf8 ;
USE `picsure` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(150) NOT NULL,
  `street2` VARCHAR(150) NULL,
  `city` VARCHAR(150) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `zip` INT(5) NOT NULL,
  `country` VARCHAR(45) NOT NULL DEFAULT 'United State of America',
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `address` (`id` ASC);


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `addressId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_store_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `store` (`id` ASC);

CREATE UNIQUE INDEX `phone_UNIQUE` ON `store` (`phone` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `store` (`email` ASC);

CREATE INDEX `fk_store_address1_idx` ON `store` (`addressId` ASC);


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fName` VARCHAR(45) NOT NULL,
  `lName` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `admin` TINYINT(1) NOT NULL DEFAULT 0,
  `addressId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_address1_idx` ON `user` (`addressId` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `user` (`username` ASC);

CREATE UNIQUE INDEX `phone_UNIQUE` ON `user` (`phone` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `user` (`email` ASC);


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `storeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inventory_store1`
    FOREIGN KEY (`storeId`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_inventory_store1_idx` ON `inventory` (`storeId` ASC);


-- -----------------------------------------------------
-- Table `equpiment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equpiment` ;

CREATE TABLE IF NOT EXISTS `equpiment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(150) NOT NULL,
  `type` VARCHAR(150) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `image` VARCHAR(255) NOT NULL,
  `rate` DOUBLE NOT NULL,
  `make` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `equpiment` (`model` ASC);


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservation_user1_idx` ON `reservation` (`userId` ASC);


-- -----------------------------------------------------
-- Table `cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cart` ;

CREATE TABLE IF NOT EXISTS `cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventoryId` INT NULL,
  `total` DOUBLE NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cart_user1_idx` ON `cart` (`userId` ASC);


-- -----------------------------------------------------
-- Table `inventoryItem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventoryItem` ;

CREATE TABLE IF NOT EXISTS `inventoryItem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `equpimentId` INT NOT NULL,
  `inventoryId` INT NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_inventoryItem_equpiment1`
    FOREIGN KEY (`equpimentId`)
    REFERENCES `equpiment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventoryItem_inventory1`
    FOREIGN KEY (`inventoryId`)
    REFERENCES `inventory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_inventoryItem_equpiment1_idx` ON `inventoryItem` (`equpimentId` ASC);

CREATE INDEX `fk_inventoryItem_inventory1_idx` ON `inventoryItem` (`inventoryId` ASC);


-- -----------------------------------------------------
-- Table `reservationItems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservationItems` ;

CREATE TABLE IF NOT EXISTS `reservationItems` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventoryItemId` INT NOT NULL,
  `reservationId` INT NOT NULL,
  `timeIn` DATETIME NOT NULL,
  `timeOut` DATETIME NOT NULL,
  `total` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservationItems_inventoryItem1`
    FOREIGN KEY (`inventoryItemId`)
    REFERENCES `inventoryItem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservationItems_reservation1`
    FOREIGN KEY (`reservationId`)
    REFERENCES `reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservationItems_inventoryItem1_idx` ON `reservationItems` (`inventoryItemId` ASC);

CREATE INDEX `fk_reservationItems_reservation1_idx` ON `reservationItems` (`reservationId` ASC);


-- -----------------------------------------------------
-- Table `cartItems`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cartItems` ;

CREATE TABLE IF NOT EXISTS `cartItems` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cartId` INT NOT NULL,
  `inventoryItemId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cartItems_cart1`
    FOREIGN KEY (`cartId`)
    REFERENCES `cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartItems_inventoryItem1`
    FOREIGN KEY (`inventoryItemId`)
    REFERENCES `inventoryItem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cartItems_cart1_idx` ON `cartItems` (`cartId` ASC);

CREATE INDEX `fk_cartItems_inventoryItem1_idx` ON `cartItems` (`inventoryItemId` ASC);


-- -----------------------------------------------------
-- Table `lister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lister` ;

CREATE TABLE IF NOT EXISTS `lister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `storeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lister_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lister_store1`
    FOREIGN KEY (`storeId`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_lister_user1_idx` ON `lister` (`userId` ASC);

CREATE INDEX `fk_lister_store1_idx` ON `lister` (`storeId` ASC);

SET SQL_MODE = '';
GRANT USAGE ON *.* TO picSureAdmin;
 DROP USER picSureAdmin;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'picSureAdmin' IDENTIFIED BY 'picSureAdmin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'picSureAdmin';
GRANT SELECT, INSERT, TRIGGER ON TABLE * TO 'picSureAdmin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
