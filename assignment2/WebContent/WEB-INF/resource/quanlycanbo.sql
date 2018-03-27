-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: quanlycanbo
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `ChucVu`
--

DROP TABLE IF EXISTS `ChucVu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ChucVu` (
  `IdChucVu` int(11) NOT NULL,
  `ChucVu` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `PhuCap` double NOT NULL,
  `IdLoaiCanBo` int(11) NOT NULL,
  PRIMARY KEY (`IdChucVu`),
  KEY `fk_ChucVu_LoaiCanBo` (`IdLoaiCanBo`),
  CONSTRAINT `fk_ChucVu_LoaiCanBo` FOREIGN KEY (`IdLoaiCanBo`) REFERENCES `LoaiCanBo` (`IdLoaiCanBo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChucVu`
--

LOCK TABLES `ChucVu` WRITE;
/*!40000 ALTER TABLE `ChucVu` DISABLE KEYS */;
INSERT INTO `ChucVu` VALUES (1,'Cử nhân',300,1),(2,'Thạc sỹ',900,1),(3,'Tiến sỹ',2000,1),(4,'Trưởng phòng',1000,2),(5,'Phó phòng',600,2),(6,'Nhân viên',400,2),(7,'admin',1000,2);
/*!40000 ALTER TABLE `ChucVu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiangVien`
--

DROP TABLE IF EXISTS `GiangVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiangVien` (
  `IdGiangVien` int(11) NOT NULL AUTO_INCREMENT,
  `IdUser` int(11) NOT NULL,
  `IdKhoa` int(11) NOT NULL,
  `SoTietDay` int(11) NOT NULL,
  PRIMARY KEY (`IdGiangVien`,`IdUser`),
  KEY `fk_NhanVien_NguoiDung_idx` (`IdUser`),
  KEY `fk_GiangVien_Khoa_idx` (`IdKhoa`),
  CONSTRAINT `fk_GiangVien_Khoa` FOREIGN KEY (`IdKhoa`) REFERENCES `Khoa` (`IdKhoa`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_GiangVien_NguoiDung` FOREIGN KEY (`IdUser`) REFERENCES `NguoiDung` (`IdUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiangVien`
--

LOCK TABLES `GiangVien` WRITE;
/*!40000 ALTER TABLE `GiangVien` DISABLE KEYS */;
INSERT INTO `GiangVien` VALUES (1,4,1,10),(3,10,1,45),(7,1,4,2),(8,22,4,1);
/*!40000 ALTER TABLE `GiangVien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Khoa`
--

DROP TABLE IF EXISTS `Khoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Khoa` (
  `IdKhoa` int(11) NOT NULL AUTO_INCREMENT,
  `TenKhoa` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdKhoa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Khoa`
--

LOCK TABLES `Khoa` WRITE;
/*!40000 ALTER TABLE `Khoa` DISABLE KEYS */;
INSERT INTO `Khoa` VALUES (1,'Công nghệ thông tin'),(2,'Kiến trúc'),(3,'Hóa'),(4,'Điện tử viễn thông');
/*!40000 ALTER TABLE `Khoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaiCanBo`
--

DROP TABLE IF EXISTS `LoaiCanBo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoaiCanBo` (
  `IdLoaiCanBo` int(11) NOT NULL,
  `TenLoaiCanBo` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdLoaiCanBo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaiCanBo`
--

LOCK TABLES `LoaiCanBo` WRITE;
/*!40000 ALTER TABLE `LoaiCanBo` DISABLE KEYS */;
INSERT INTO `LoaiCanBo` VALUES (1,'Giảng viên'),(2,'Nhân viên');
/*!40000 ALTER TABLE `LoaiCanBo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoaiDangNhap`
--

DROP TABLE IF EXISTS `LoaiDangNhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoaiDangNhap` (
  `IdLoaiDangNhap` int(11) NOT NULL AUTO_INCREMENT,
  `TenLoaiDangNhap` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdLoaiDangNhap`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoaiDangNhap`
--

LOCK TABLES `LoaiDangNhap` WRITE;
/*!40000 ALTER TABLE `LoaiDangNhap` DISABLE KEYS */;
INSERT INTO `LoaiDangNhap` VALUES (1,'admin'),(2,'user');
/*!40000 ALTER TABLE `LoaiDangNhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Luong`
--

DROP TABLE IF EXISTS `Luong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Luong` (
  `IdLuong` int(11) NOT NULL AUTO_INCREMENT,
  `IdUser` int(11) NOT NULL,
  `idChucVu` int(11) NOT NULL,
  `HeSoLuong` float NOT NULL,
  PRIMARY KEY (`IdLuong`),
  KEY `fk_Luong_NguoiDung_idx` (`IdUser`),
  KEY `fk_Luong_ChucVu_idx` (`idChucVu`),
  CONSTRAINT `fk_Luong_ChucVu` FOREIGN KEY (`idChucVu`) REFERENCES `ChucVu` (`IdChucVu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Luong_NguoiDung` FOREIGN KEY (`IdUser`) REFERENCES `NguoiDung` (`IdUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Luong`
--

LOCK TABLES `Luong` WRITE;
/*!40000 ALTER TABLE `Luong` DISABLE KEYS */;
INSERT INTO `Luong` VALUES (1,1,7,0.1),(2,4,1,0.5),(3,5,6,0.7),(7,6,5,0.6),(8,10,2,0.3),(16,1,1,2),(19,1,4,5),(20,22,1,1);
/*!40000 ALTER TABLE `Luong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NguoiDung`
--

DROP TABLE IF EXISTS `NguoiDung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NguoiDung` (
  `IdUser` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Ho` varchar(45) CHARACTER SET utf8 NOT NULL,
  `Ten` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `NamSinh` int(11) NOT NULL,
  `DiaChi` varchar(45) CHARACTER SET utf8 NOT NULL,
  `IdLoaiDangNhap` int(11) NOT NULL,
  `IdLoaiCanBo` int(11) NOT NULL,
  PRIMARY KEY (`IdUser`),
  KEY `fk_NguoiDung_LoaiDangNhap_idx` (`IdLoaiDangNhap`),
  KEY `fk_NguoiDung_LoaiCanBo_idx` (`IdLoaiCanBo`),
  CONSTRAINT `fk_NguoiDung_LoaiCanBo` FOREIGN KEY (`IdLoaiCanBo`) REFERENCES `LoaiCanBo` (`IdLoaiCanBo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_NguoiDung_LoaiDangNhap` FOREIGN KEY (`IdLoaiDangNhap`) REFERENCES `LoaiDangNhap` (`IdLoaiDangNhap`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NguoiDung`
--

LOCK TABLES `NguoiDung` WRITE;
/*!40000 ALTER TABLE `NguoiDung` DISABLE KEYS */;
INSERT INTO `NguoiDung` VALUES (1,'admin','c33367701511b4f6020ec61ded352059','Nguyễn Thị Thanh ','Tuyền',1996,'Quảng Nam',1,2),(4,'tuyen','827ccb0eea8a706c4c34a16891f84e7b','Thanh','Tuyen',1997,'Đà Nẵng',2,1),(5,'dung','827ccb0eea8a706c4c34a16891f84e7b','Nguyễn Quốc ','Dũng',1997,'Quảng Nam',2,2),(6,'user1','202cb962ac59075b964b07152d234b70','Chí ','Phèo',2017,'Đã thay đổi địa chỉ',2,2),(10,'Java12','74be16979710d4c4e7c6647856088456','Nguyen Nguyen','Nguyen',1988,' Nguyen Nguyen',2,1),(22,'onkeyup','4981f7953076a451f328d0b878a4a423','','onkeyup',2017,'onkeyup',2,1);
/*!40000 ALTER TABLE `NguoiDung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NhanVien`
--

DROP TABLE IF EXISTS `NhanVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NhanVien` (
  `IdNhanVien` int(11) NOT NULL AUTO_INCREMENT,
  `IdUser` int(11) NOT NULL,
  `IdPhongBan` int(11) NOT NULL,
  `SoNgayCong` int(11) NOT NULL,
  PRIMARY KEY (`IdNhanVien`),
  KEY `fk_NhanVien_NguoDung_idx` (`IdUser`),
  KEY `fk_NhanVien_PhongBan_idx` (`IdPhongBan`),
  CONSTRAINT `fk_NhanVien_NguoDung` FOREIGN KEY (`IdUser`) REFERENCES `NguoiDung` (`IdUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_NhanVien_PhongBan` FOREIGN KEY (`IdPhongBan`) REFERENCES `PhongBan` (`IdPhongBan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NhanVien`
--

LOCK TABLES `NhanVien` WRITE;
/*!40000 ALTER TABLE `NhanVien` DISABLE KEYS */;
INSERT INTO `NhanVien` VALUES (1,1,2,25),(2,5,2,30),(3,6,2,10),(6,1,3,5);
/*!40000 ALTER TABLE `NhanVien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PhongBan`
--

DROP TABLE IF EXISTS `PhongBan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PhongBan` (
  `IdPhongBan` int(11) NOT NULL AUTO_INCREMENT,
  `TenPhongBan` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdPhongBan`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PhongBan`
--

LOCK TABLES `PhongBan` WRITE;
/*!40000 ALTER TABLE `PhongBan` DISABLE KEYS */;
INSERT INTO `PhongBan` VALUES (1,'Phòng hành chính'),(2,'Phòng nhân sự'),(3,'Phòng kế hoạch tài chính'),(4,'Phòng đào tạo');
/*!40000 ALTER TABLE `PhongBan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-12 20:54:17
