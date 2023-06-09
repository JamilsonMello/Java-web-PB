-- MySQL Script generated by MySQL Workbench
-- Fri Mar 31 21:04:41 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cadastro_basico_jamilson
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cadastro_basico_jamilson
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cadastro_basico_jamilson` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cadastro_basico_jamilson` ;

-- -----------------------------------------------------
-- Table `cadastro_basico_jamilson`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cadastro_basico_jamilson`.`projects` ;

CREATE TABLE IF NOT EXISTS `cadastro_basico_jamilson`.`projects` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `date` DATETIME(6) NOT NULL,
  `description` VARCHAR(30) NULL DEFAULT NULL,
  `title` VARCHAR(30) NULL DEFAULT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cadastro_basico_jamilson`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cadastro_basico_jamilson`.`users` ;

CREATE TABLE IF NOT EXISTS `cadastro_basico_jamilson`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(30) NULL DEFAULT NULL,
  `last_name` VARCHAR(30) NULL DEFAULT NULL,
  `password` VARCHAR(128) NULL DEFAULT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cadastro_basico_jamilson`.`tasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cadastro_basico_jamilson`.`tasks` ;

CREATE TABLE IF NOT EXISTS `cadastro_basico_jamilson`.`tasks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `task_name` VARCHAR(30) NULL DEFAULT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  `project_id` BIGINT NULL DEFAULT NULL,
  `user_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKsfhn82y57i3k9uxww1s007acc` (`project_id` ASC) VISIBLE,
  INDEX `FK6s1ob9k4ihi75xbxe2w0ylsdh` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK6s1ob9k4ihi75xbxe2w0ylsdh`
    FOREIGN KEY (`user_id`)
    REFERENCES `cadastro_basico_jamilson`.`users` (`id`),
  CONSTRAINT `FKsfhn82y57i3k9uxww1s007acc`
    FOREIGN KEY (`project_id`)
    REFERENCES `cadastro_basico_jamilson`.`projects` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cadastro_basico_jamilson`.`user_projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cadastro_basico_jamilson`.`user_projects` ;

CREATE TABLE IF NOT EXISTS `cadastro_basico_jamilson`.`user_projects` (
  `user_id` BIGINT NOT NULL,
  `project_id` BIGINT NOT NULL,
  INDEX `FKof7c4wufgerxtl9moqol6c516` (`project_id` ASC) VISIBLE,
  INDEX `FKr25ilmlcm8ugp8i3rogl6jp0l` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FKof7c4wufgerxtl9moqol6c516`
    FOREIGN KEY (`project_id`)
    REFERENCES `cadastro_basico_jamilson`.`projects` (`id`),
  CONSTRAINT `FKr25ilmlcm8ugp8i3rogl6jp0l`
    FOREIGN KEY (`user_id`)
    REFERENCES `cadastro_basico_jamilson`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cadastro_basico_jamilson`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cadastro_basico_jamilson`.`usuario` ;

CREATE TABLE IF NOT EXISTS `cadastro_basico_jamilson`.`usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
