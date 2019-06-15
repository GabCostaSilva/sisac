-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: db_sisac_v1
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_alunos`
--

DROP TABLE IF EXISTS `tb_alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_alunos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `data_matricula` date DEFAULT NULL,
  `data_matricula_fim` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_alunos`
--

LOCK TABLES `tb_alunos` WRITE;
/*!40000 ALTER TABLE `tb_alunos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_alunos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_certificados`
--

DROP TABLE IF EXISTS `tb_certificados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_certificados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_aluno` int(11) DEFAULT NULL,
  `id_exame` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tb_certificados_id_aluno_uindex` (`id_aluno`),
  KEY `tb_certificados_tb_exames_id_fk` (`id_exame`),
  CONSTRAINT `tb_certificados_tb_alunos_id_fk` FOREIGN KEY (`id_aluno`) REFERENCES `tb_alunos` (`id`),
  CONSTRAINT `tb_certificados_tb_exames_id_fk` FOREIGN KEY (`id_exame`) REFERENCES `tb_exames` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_certificados`
--

LOCK TABLES `tb_certificados` WRITE;
/*!40000 ALTER TABLE `tb_certificados` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_certificados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_exames`
--

DROP TABLE IF EXISTS `tb_exames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_exames` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `custo` double DEFAULT NULL,
  `aprovado` tinyint(1) DEFAULT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_exames_tb_alunos_id_fk` (`id_aluno`),
  CONSTRAINT `tb_exames_tb_alunos_id_fk` FOREIGN KEY (`id_aluno`) REFERENCES `tb_alunos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_exames`
--

LOCK TABLES `tb_exames` WRITE;
/*!40000 ALTER TABLE `tb_exames` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_exames` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mensalidades`
--

DROP TABLE IF EXISTS `tb_mensalidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_mensalidades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `id_pagamento` int(11) DEFAULT NULL,
  `esta_paga` tinyint(1) DEFAULT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_mensalidades_tb_alunos_id_fk` (`id_aluno`),
  KEY `tb_mensalidades_tb_pagamentos_id_fk` (`id_pagamento`),
  CONSTRAINT `tb_mensalidades_tb_alunos_id_fk` FOREIGN KEY (`id_aluno`) REFERENCES `tb_alunos` (`id`),
  CONSTRAINT `tb_mensalidades_tb_pagamentos_id_fk` FOREIGN KEY (`id_pagamento`) REFERENCES `tb_pagamentos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mensalidades`
--

LOCK TABLES `tb_mensalidades` WRITE;
/*!40000 ALTER TABLE `tb_mensalidades` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_mensalidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamentos`
--

DROP TABLE IF EXISTS `tb_pagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  `data` date DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `id_aluno` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tb_pagamentos_tb_alunos_id_fk` (`id_aluno`),
  CONSTRAINT `tb_pagamentos_tb_alunos_id_fk` FOREIGN KEY (`id_aluno`) REFERENCES `tb_alunos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamentos`
--

LOCK TABLES `tb_pagamentos` WRITE;
/*!40000 ALTER TABLE `tb_pagamentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pagamentos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-15  1:03:36
