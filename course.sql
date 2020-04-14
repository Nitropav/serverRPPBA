-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.13 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.5.0.5332
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


CREATE DATABASE IF NOT EXISTS `course` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `course`;

CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `password` (`password`),
  KEY `FK_clients_users` (`login`),
  KEY `name` (`name`),
  KEY `surname` (`surname`),
  CONSTRAINT `FK_clients_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `name`, `surname`, `login`, `password`) VALUES
	(1, 'Konstantin', 'Zarutskiy', 'user1', 'user1'),
	(2, 'Vasya', 'Bogomolov', 'user2', 'user2'),
	(3, 'Masha', 'Vasilieva', 'user3', 'user3'),
	(4, 'Fedor', 'Bondarchyk', 'user4', 'user4');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `geely_models` (
  `model` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `geely_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `geely_models` DISABLE KEYS */;
INSERT INTO `geely_models` (`model`, `price`) VALUES
	('ATLAS', 25000),
	('EMGRAND 7', 12000),
	('EMGRAND GT', 24000),
	('EMGRAND X7', 15000);
/*!40000 ALTER TABLE `geely_models` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `haval_models` (
  `model` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `haval_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `haval_models` DISABLE KEYS */;
INSERT INTO `haval_models` (`model`, `price`) VALUES
	('6 COUPE', 30000),
	('H2', 20000),
	('H6', 25000),
	('H9', 27000);
/*!40000 ALTER TABLE `haval_models` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `insurance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `insurance` DISABLE KEYS */;
INSERT INTO `insurance` (`id`, `type`, `price`) VALUES
	(1, 'standard 15 day', 30),
	(2, 'casco 1 year', 1000),
	(3, 'standard 30 days', 50);
/*!40000 ALTER TABLE `insurance` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `make` (
  `make` varchar(20) NOT NULL,
  PRIMARY KEY (`make`),
  UNIQUE KEY `make` (`make`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `make` DISABLE KEYS */;
INSERT INTO `make` (`make`) VALUES
	('GEELY'),
	('HAVAL'),
	('RAVON');
/*!40000 ALTER TABLE `make` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `managers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `login` (`login`),
  KEY `password` (`password`),
  CONSTRAINT `FK_managers_users` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_managers_users_2` FOREIGN KEY (`login`) REFERENCES `users` (`login`),
  CONSTRAINT `FK_managers_users_3` FOREIGN KEY (`password`) REFERENCES `users` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` (`id`, `name`, `surname`, `login`, `password`) VALUES
	(1, 'Ivan', 'Ivanov', 'man1', 'man1'),
	(2, 'Petr', 'Petrov', 'man2', 'man2');
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `order` (
  `number` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_accepted` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `insurance` varchar(20) NOT NULL DEFAULT '0',
  `country` varchar(20) NOT NULL DEFAULT '0',
  `color` varchar(20) NOT NULL DEFAULT '0',
  `make` varchar(20) NOT NULL,
  `year` year(4) NOT NULL,
  `order_completed` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `model` varchar(20) NOT NULL,
  `price` int(11) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  PRIMARY KEY (`number`),
  KEY `FK_order_make` (`make`),
  KEY `model` (`model`),
  KEY `name` (`name`),
  KEY `surname` (`surname`),
  KEY `insurance` (`insurance`),
  CONSTRAINT `FK_order_clients` FOREIGN KEY (`name`) REFERENCES `clients` (`name`),
  CONSTRAINT `FK_order_clients_2` FOREIGN KEY (`surname`) REFERENCES `clients` (`surname`),
  CONSTRAINT `FK_order_insurance` FOREIGN KEY (`insurance`) REFERENCES `insurance` (`type`) ON UPDATE CASCADE,
  CONSTRAINT `FK_order_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`number`, `order_accepted`, `insurance`, `country`, `color`, `make`, `year`, `order_completed`, `model`, `price`, `name`, `surname`) VALUES
	(9, 0, 'standard 15 day', 'China', 'white', 'RAVON', '2016', 0, 'R2', 4800, 'Konstantin', 'Zarutskiy'),
	(11, 0, 'standard 15 day', 'Belarus', 'red', 'GEELY', '2016', 0, 'EMGRAND X7', 12000, 'Vasya', 'Bogomolov'),
	(13, 0, 'casco 1 year', 'Belarus', 'black', 'GEELY', '2018', 0, 'EMGRAND GT', 24000, 'Konstantin', 'Zarutskiy'),
	(15, 1, 'casco 1 year', 'China', 'RED', 'HAVAL', '2019', 0, '6 COUPE', 30500, 'Masha', 'Vasilieva');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `ravon_models` (
  `model` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE KEY `ravon_models_model_uindex` (`model`),
  KEY `model` (`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `ravon_models` DISABLE KEYS */;
INSERT INTO `ravon_models` (`model`, `price`) VALUES
	('R2', 4800),
	('R3', 5000),
	('R4', 5200);
/*!40000 ALTER TABLE `ravon_models` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country` varchar(20) NOT NULL DEFAULT '0',
  `make` varchar(20) NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `make` (`make`),
  KEY `name` (`name`),
  CONSTRAINT `FK_supplier_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`id`, `country`, `make`, `name`) VALUES
	(1, 'Russia', 'HAVAL', 'HavalRussia'),
	(3, 'China', 'HAVAL', 'HavalChina'),
	(4, 'Belarus', 'GEELY', 'BelGeely'),
	(5, 'China', 'GEELY', 'GeelyChina'),
	(6, 'China', 'RAVON', 'RavonChina'),
	(7, 'Russia', 'RAVON', 'RavonRussia');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `supplier_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `make` varchar(20) NOT NULL DEFAULT '0',
  `supplier` varchar(20) NOT NULL DEFAULT '0',
  `model` varchar(20) NOT NULL DEFAULT '0',
  `quantity` int(10) unsigned NOT NULL DEFAULT '0',
  `color` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_supplier_order_make` (`make`),
  KEY `FK_supplier_order_ravon_models` (`model`),
  KEY `FK_supplier_order_supplier` (`supplier`),
  CONSTRAINT `FK_supplier_order_make` FOREIGN KEY (`make`) REFERENCES `make` (`make`),
  CONSTRAINT `FK_supplier_order_supplier` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `supplier_order` DISABLE KEYS */;
INSERT INTO `supplier_order` (`id`, `make`, `supplier`, `model`, `quantity`, `color`) VALUES
	(4, 'HAVAL', 'HavalRussia', '6 COUPE', 1, 'BLACK');
/*!40000 ALTER TABLE `supplier_order` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_type` varchar(10) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `account_type`, `login`, `password`) VALUES
	(1, 'admin', 'admin', 'admin'),
	(2, 'client', 'user1', 'user1'),
	(3, 'manager', 'man1', 'man1'),
	(4, 'manager', 'man2', 'man2'),
	(6, 'client', 'user2', 'user2'),
	(7, 'client', 'user3', 'user3'),
	(8, 'client', 'user4', 'user4'),
	(9, 'client', 'user5', 'user5');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;