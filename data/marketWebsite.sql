-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for marketwebsite
DROP DATABASE IF EXISTS `marketwebsite`;
CREATE DATABASE IF NOT EXISTS `marketwebsite` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `marketwebsite`;

-- Dumping structure for table marketwebsite.categories
DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table marketwebsite.categories: ~3 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
REPLACE INTO `categories` (`CategoryID`, `Name`) VALUES
	(1, 'Television'),
	(2, 'Computers'),
	(3, 'Smartphones');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table marketwebsite.order details
DROP TABLE IF EXISTS `order details`;
CREATE TABLE IF NOT EXISTS `order details` (
  `ProductID` int(11) NOT NULL,
  `OrderID` int(11) NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`ProductID`,`OrderID`),
  KEY `FK_order details_orders` (`OrderID`),
  CONSTRAINT `FK_order details_orders` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  CONSTRAINT `FK_order details_products` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table marketwebsite.order details: ~0 rows (approximately)
/*!40000 ALTER TABLE `order details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order details` ENABLE KEYS */;

-- Dumping structure for table marketwebsite.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `OrderDate` date NOT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `FK_orders_users` (`UserID`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table marketwebsite.orders: ~0 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table marketwebsite.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT '0',
  `Price` int(11) NOT NULL DEFAULT 0,
  `Description` varchar(50) NOT NULL DEFAULT '0',
  `ImageURL` varchar(100) NOT NULL DEFAULT '0',
  `CategoryID` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  CONSTRAINT `FK_products_categories` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table marketwebsite.products: ~0 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
REPLACE INTO `products` (`ProductID`, `Name`, `Price`, `Description`, `ImageURL`, `CategoryID`) VALUES
	(1, '75" Class Q9FN QLED Smart 4K UHD TV (2018)', 3999, 'Samsung smart TV', 'https://s7d2.scene7.com/is/image/SamsungUS/01_QN75Q9FNAFXZA?$default-jpg$', 1),
	(2, 'Samsung Smart TV 65\'\' 4K (2018)', 2500, 'Samsung smart tv', 'https://s7d2.scene7.com/is/image/SamsungUS/01_QN75Q9FNAFXZA?$default-jpg$', 1),
	(3, 'LG SIGNATURE OLED 4K TV - 77"', 3500, 'LG OLED SMART TV', 'https://www.lg.com/uk/images/tvs/md05810069/gallery/OLED77W7V_Gallery_Zoom_01.jpg', 1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table marketwebsite.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL DEFAULT '0',
  `LastName` varchar(50) NOT NULL DEFAULT '0',
  `Username` varchar(50) NOT NULL DEFAULT '0',
  `Password` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table marketwebsite.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;