-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.24 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-10-30 16:57:26
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for horarios
DROP DATABASE IF EXISTS `horarios`;
CREATE DATABASE IF NOT EXISTS `horarios` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `horarios`;


-- Dumping structure for table horarios.curso
DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `cur_iId` int(11) NOT NULL AUTO_INCREMENT,
  `cur_iNumCreditos` int(11) DEFAULT NULL,
  `cur_vCiclo` varchar(255) DEFAULT NULL,
  `cur_vCodigo` varchar(255) DEFAULT NULL,
  `cur_vNombre` varchar(255) DEFAULT NULL,
  `pla_iId` int(11) DEFAULT NULL,
  `pre_iId` int(11) DEFAULT NULL,
  PRIMARY KEY (`cur_iId`),
  KEY `FK5AA009C3009B8B7` (`pre_iId`),
  KEY `FK5AA009C77E4CAAF` (`pla_iId`),
  CONSTRAINT `FK5AA009C3009B8B7` FOREIGN KEY (`pre_iId`) REFERENCES `prerequisitos` (`pre_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.curso: ~15 rows (approximately)
DELETE FROM `curso`;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`cur_iId`, `cur_iNumCreditos`, `cur_vCiclo`, `cur_vCodigo`, `cur_vNombre`, `pla_iId`, `pre_iId`) VALUES
	(1, 4, 'I', '2020101', 'Introduccion a la Teoria General de Sistemas', 1, NULL),
	(2, 4, 'I', '2020102', 'Introduccion a la Computacion e Ingenieria de Software', 1, NULL),
	(3, 4, 'I', '2020103', 'Calculo I', 1, NULL),
	(4, 4, 'I', '2020104', 'Matematica Basica I', 1, NULL),
	(5, 2, 'I', '2020105', 'Comunicacion y Dinamica de Grupo', 1, NULL),
	(6, 3, 'I', '2020106', 'Idioma Extranjero I', 1, NULL),
	(7, 4, 'II', '2020201', 'Programacion I', 1, NULL),
	(8, 3, 'II', '2020202', 'Matematicas Discretas', 1, NULL),
	(9, 4, 'II', '2020203', 'Calculo II', 1, NULL),
	(10, 4, 'II', '2020204', 'Matematica Basica II', 1, NULL),
	(11, 4, 'II', '2020205', 'Fisica I', 1, NULL),
	(12, 3, 'II', '2020206', 'Idioma Extranjero II', 1, NULL),
	(13, 3, 'III', '2020301', 'Analisis de Algoritmos', 1, NULL),
	(14, 4, 'III', '2020302', 'Estructura de Datos II', 1, NULL),
	(15, 4, 'III', '2020303', 'ProgramacionII', 1, NULL);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


-- Dumping structure for table horarios.curso_tipo_dictado
DROP TABLE IF EXISTS `curso_tipo_dictado`;
CREATE TABLE IF NOT EXISTS `curso_tipo_dictado` (
  `ctd_iId` int(11) NOT NULL AUTO_INCREMENT,
  `ctd_iHoras` int(11) DEFAULT NULL,
  `cur_iId` int(11) NOT NULL,
  `tid_iId` int(11) NOT NULL,
  PRIMARY KEY (`ctd_iId`),
  KEY `FKCBCB15EEED7198BD` (`cur_iId`),
  KEY `FKCBCB15EE79916D52` (`tid_iId`),
  CONSTRAINT `FKCBCB15EE79916D52` FOREIGN KEY (`tid_iId`) REFERENCES `tipo_dictado` (`tid_iId`),
  CONSTRAINT `FKCBCB15EEED7198BD` FOREIGN KEY (`cur_iId`) REFERENCES `curso` (`cur_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.curso_tipo_dictado: ~25 rows (approximately)
DELETE FROM `curso_tipo_dictado`;
/*!40000 ALTER TABLE `curso_tipo_dictado` DISABLE KEYS */;
INSERT INTO `curso_tipo_dictado` (`ctd_iId`, `ctd_iHoras`, `cur_iId`, `tid_iId`) VALUES
	(1, 2, 1, 1),
	(2, 2, 1, 2),
	(3, 3, 2, 1),
	(4, 2, 2, 3),
	(5, 3, 3, 1),
	(6, 2, 3, 2),
	(7, 3, 4, 1),
	(8, 2, 4, 2),
	(9, 1, 5, 1),
	(10, 2, 5, 2),
	(11, 6, 6, 2),
	(12, 3, 7, 1),
	(13, 2, 7, 3),
	(14, 2, 8, 1),
	(15, 2, 8, 2),
	(16, 3, 9, 1),
	(17, 2, 9, 2),
	(18, 3, 10, 1),
	(19, 2, 10, 2),
	(20, 3, 11, 1),
	(21, 2, 11, 2),
	(22, 6, 12, 2),
	(23, 4, 13, 1),
	(24, 3, 14, 2),
	(25, 3, 15, 3);
/*!40000 ALTER TABLE `curso_tipo_dictado` ENABLE KEYS */;


-- Dumping structure for table horarios.grupo
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE IF NOT EXISTS `grupo` (
  `gru_iId` int(11) NOT NULL AUTO_INCREMENT,
  `gru_iMaxAlumnos` int(11) DEFAULT NULL,
  `gru_iNumGrupo` int(11) DEFAULT NULL,
  `scu_iId` int(11) DEFAULT NULL,
  PRIMARY KEY (`gru_iId`),
  KEY `FK5E10C698304004` (`scu_iId`),
  CONSTRAINT `FK5E10C698304004` FOREIGN KEY (`scu_iId`) REFERENCES `semestre_curso` (`scu_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.grupo: ~6 rows (approximately)
DELETE FROM `grupo`;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`gru_iId`, `gru_iMaxAlumnos`, `gru_iNumGrupo`, `scu_iId`) VALUES
	(30, 35, 1, 22),
	(31, 35, 2, 22),
	(32, 60, 1, 23),
	(33, 60, 1, 25),
	(34, 30, 2, 23),
	(35, 30, 1, 24);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;


-- Dumping structure for table horarios.grupo_tipo_dictado
DROP TABLE IF EXISTS `grupo_tipo_dictado`;
CREATE TABLE IF NOT EXISTS `grupo_tipo_dictado` (
  `gtd_iId` int(11) NOT NULL AUTO_INCREMENT,
  `gtd_bAprobado` bit(1) DEFAULT NULL,
  `ctd_iId` int(11) DEFAULT NULL,
  `gru_iId` int(11) DEFAULT NULL,
  PRIMARY KEY (`gtd_iId`),
  KEY `FK83385F41F0225F62` (`ctd_iId`),
  KEY `FK83385F41BC4D4E34` (`gru_iId`),
  CONSTRAINT `FK83385F41BC4D4E34` FOREIGN KEY (`gru_iId`) REFERENCES `grupo` (`gru_iId`),
  CONSTRAINT `FK83385F41F0225F62` FOREIGN KEY (`ctd_iId`) REFERENCES `curso_tipo_dictado` (`ctd_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.grupo_tipo_dictado: ~12 rows (approximately)
DELETE FROM `grupo_tipo_dictado`;
/*!40000 ALTER TABLE `grupo_tipo_dictado` DISABLE KEYS */;
INSERT INTO `grupo_tipo_dictado` (`gtd_iId`, `gtd_bAprobado`, `ctd_iId`, `gru_iId`) VALUES
	(43, b'00000000', 1, 30),
	(44, b'00000000', 2, 30),
	(45, b'00000000', 1, 31),
	(46, b'00000000', 2, 31),
	(47, b'00000000', 5, 32),
	(48, b'00000000', 6, 32),
	(49, b'00000000', 7, 33),
	(50, b'00000000', 8, 33),
	(51, b'00000000', 5, 34),
	(52, b'00000000', 6, 34),
	(53, b'00000000', 12, 35),
	(54, b'00000000', 13, 35);
/*!40000 ALTER TABLE `grupo_tipo_dictado` ENABLE KEYS */;


-- Dumping structure for table horarios.historial
DROP TABLE IF EXISTS `historial`;
CREATE TABLE IF NOT EXISTS `historial` (
  `his_iId` int(11) NOT NULL AUTO_INCREMENT,
  `his_sdescripcion` varchar(255) DEFAULT NULL,
  `his_iIdciclo` varchar(255) DEFAULT NULL,
  `his_iIdgrupo` int(11) DEFAULT NULL,
  PRIMARY KEY (`his_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.historial: ~48 rows (approximately)
DELETE FROM `historial`;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` (`his_iId`, `his_sdescripcion`, `his_iIdciclo`, `his_iIdgrupo`) VALUES
	(1, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(2, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(3, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(4, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(5, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(6, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(7, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(8, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(9, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(10, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(11, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(12, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(13, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(14, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(15, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(16, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(17, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(18, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(19, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(20, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(21, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(22, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(23, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(24, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(25, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(26, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(27, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(28, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(29, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(30, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(31, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(32, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(33, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(34, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(35, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(36, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(37, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(38, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(39, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(40, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(41, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(42, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(43, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(44, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(45, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(46, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(47, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1),
	(48, 'Se ha seleccionado el horario ciclo : I grupo : 1', 'I', 1);
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;


-- Dumping structure for table horarios.horario
DROP TABLE IF EXISTS `horario`;
CREATE TABLE IF NOT EXISTS `horario` (
  `hor_iId` int(11) NOT NULL AUTO_INCREMENT,
  `hor_tFin` int(11) DEFAULT NULL,
  `hor_tIni` int(11) DEFAULT NULL,
  `hor_vDia` varchar(255) DEFAULT NULL,
  `gtd_iId` int(11) DEFAULT NULL,
  PRIMARY KEY (`hor_iId`),
  KEY `FK416647E21680CE79` (`gtd_iId`),
  CONSTRAINT `FK416647E21680CE79` FOREIGN KEY (`gtd_iId`) REFERENCES `grupo_tipo_dictado` (`gtd_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.horario: ~9 rows (approximately)
DELETE FROM `horario`;
/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` (`hor_iId`, `hor_tFin`, `hor_tIni`, `hor_vDia`, `gtd_iId`) VALUES
	(14, 10, 8, 'martes', 43),
	(15, 13, 11, 'miercoles', 44),
	(16, 10, 8, 'lunes', 45),
	(17, 24, 21, 'miercoles', 49),
	(18, 23, 21, 'martes', 50),
	(19, 16, 13, 'miercoles', 51),
	(20, 11, 8, 'lunes', 47),
	(21, 12, 10, 'jueves', 48),
	(22, 11, 9, 'martes', 52);
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;


-- Dumping structure for table horarios.plan_estudios
DROP TABLE IF EXISTS `plan_estudios`;
CREATE TABLE IF NOT EXISTS `plan_estudios` (
  `pla_iId` int(11) NOT NULL AUTO_INCREMENT,
  `pla_iAnio` int(11) DEFAULT NULL,
  PRIMARY KEY (`pla_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.plan_estudios: ~1 rows (approximately)
DELETE FROM `plan_estudios`;
/*!40000 ALTER TABLE `plan_estudios` DISABLE KEYS */;
INSERT INTO `plan_estudios` (`pla_iId`, `pla_iAnio`) VALUES
	(1, 2009);
/*!40000 ALTER TABLE `plan_estudios` ENABLE KEYS */;


-- Dumping structure for table horarios.prerequisitos
DROP TABLE IF EXISTS `prerequisitos`;
CREATE TABLE IF NOT EXISTS `prerequisitos` (
  `pre_iId` int(11) NOT NULL AUTO_INCREMENT,
  `cur_iId` int(11) DEFAULT NULL,
  `curso` tinyblob,
  PRIMARY KEY (`pre_iId`),
  KEY `FK8DADF5B3ED7198BD` (`cur_iId`),
  CONSTRAINT `FK8DADF5B3ED7198BD` FOREIGN KEY (`cur_iId`) REFERENCES `curso` (`cur_iId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.prerequisitos: ~0 rows (approximately)
DELETE FROM `prerequisitos`;
/*!40000 ALTER TABLE `prerequisitos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prerequisitos` ENABLE KEYS */;


-- Dumping structure for table horarios.semestre_academico
DROP TABLE IF EXISTS `semestre_academico`;
CREATE TABLE IF NOT EXISTS `semestre_academico` (
  `sem_iId` int(11) NOT NULL AUTO_INCREMENT,
  `sem_dFechaFin` date DEFAULT NULL,
  `sem_dFechaIni` date DEFAULT NULL,
  PRIMARY KEY (`sem_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.semestre_academico: ~1 rows (approximately)
DELETE FROM `semestre_academico`;
/*!40000 ALTER TABLE `semestre_academico` DISABLE KEYS */;
INSERT INTO `semestre_academico` (`sem_iId`, `sem_dFechaFin`, `sem_dFechaIni`) VALUES
	(1, '3912-08-18', '3912-04-19');
/*!40000 ALTER TABLE `semestre_academico` ENABLE KEYS */;


-- Dumping structure for table horarios.semestre_curso
DROP TABLE IF EXISTS `semestre_curso`;
CREATE TABLE IF NOT EXISTS `semestre_curso` (
  `scu_iId` int(11) NOT NULL AUTO_INCREMENT,
  `cur_iId` int(11) DEFAULT NULL,
  `sem_iId` int(11) DEFAULT NULL,
  `scu_numAlum` int(11) DEFAULT NULL,
  PRIMARY KEY (`scu_iId`),
  KEY `FK4A12EFDB428B49E6` (`sem_iId`),
  KEY `FK4A12EFDBED7198BD` (`cur_iId`),
  CONSTRAINT `FK4A12EFDB428B49E6` FOREIGN KEY (`sem_iId`) REFERENCES `semestre_academico` (`sem_iId`),
  CONSTRAINT `FK4A12EFDBED7198BD` FOREIGN KEY (`cur_iId`) REFERENCES `curso` (`cur_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.semestre_curso: ~4 rows (approximately)
DELETE FROM `semestre_curso`;
/*!40000 ALTER TABLE `semestre_curso` DISABLE KEYS */;
INSERT INTO `semestre_curso` (`scu_iId`, `cur_iId`, `sem_iId`, `scu_numAlum`) VALUES
	(22, 1, 1, 70),
	(23, 3, 1, 120),
	(24, 7, 1, 120),
	(25, 4, 1, 120);
/*!40000 ALTER TABLE `semestre_curso` ENABLE KEYS */;


-- Dumping structure for table horarios.tipo_dictado
DROP TABLE IF EXISTS `tipo_dictado`;
CREATE TABLE IF NOT EXISTS `tipo_dictado` (
  `tid_iId` int(11) NOT NULL AUTO_INCREMENT,
  `tid_vCodigo` varchar(255) DEFAULT NULL,
  `tid_vNombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.tipo_dictado: ~3 rows (approximately)
DELETE FROM `tipo_dictado`;
/*!40000 ALTER TABLE `tipo_dictado` DISABLE KEYS */;
INSERT INTO `tipo_dictado` (`tid_iId`, `tid_vCodigo`, `tid_vNombre`) VALUES
	(1, '1', 'Teoria'),
	(2, '2', 'Practica'),
	(3, '3', 'Laboratorio');
/*!40000 ALTER TABLE `tipo_dictado` ENABLE KEYS */;


-- Dumping structure for table horarios.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `use_iId` int(11) NOT NULL AUTO_INCREMENT,
  `use_password` varchar(255) DEFAULT NULL,
  `use_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`use_iId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table horarios.user: ~1 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`use_iId`, `use_password`, `use_username`) VALUES
	(1, 'nora', 'nora');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
historial