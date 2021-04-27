/* dropped huidige db en alle data daar in! verwijderd ook de user */
drop database if exists nerdygadgets_1;
DROP USER IF EXISTS 'MonitoringsApplicatie' @'localhost';
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
/* database maken en gebruiken */
CREATE DATABASE IF NOT EXISTS `nerdygadgets_1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
/* componenten tabel maken en vullen met data van leverancier */
CREATE TABLE `nerdygadgets_1`.`componenten` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hostname` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `prijs` DOUBLE NOT NULL,
  `beschikbaarheid` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
);
use nerdygadgets_1;
INSERT INTO componenten (hostname, type, prijs, beschikbaarheid)
VALUES ("pfSense", "firewall", 2000.00, 99.999),
  (
    "DBloadbalancer",
    "loadbalancer",
    2000.00,
    99.999
  ),
  ("HAL9001DB", "DBserver", 5100.00, 90),
  ("HAL9002DB", "DBserver", 7700.00, 95),
  ("HAL9003DB", "DBserver", 12200.00, 98),
  ("HAL9001W", "Webserver", 2200.00, 80),
  ("HAL9002W", "Webserver", 3200.00, 90),
  ("HAL9003W", "Webserver", 5100.00, 95);
/* gebruiker aanmaken, met select en insert rechten */
CREATE USER 'MonitoringsApplicatie' @'localhost' IDENTIFIED BY 'VeiligWachtwoord';
GRANT SELECT,
  INSERT,
  DELETE ON nerdygadgets_1.* TO 'MonitoringsApplicatie' @'localhost';
CREATE TABLE `nerdygadgets_1`.`configuratie` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `datum` date NOT NULL,
  `beschikbaarheidspercentage` double NOT NULL,
  `naam` varchar(45) NOT NULL,
  `prijs` double NOT NULL
)
