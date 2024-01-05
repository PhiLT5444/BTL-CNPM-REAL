-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: QLChungCu
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chuho`
--

DROP TABLE IF EXISTS `chuho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuho` (
  `idChuHo` int NOT NULL,
  `maHoKhau` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`idChuHo`,`maHoKhau`),
  KEY `maHoKhau` (`maHoKhau`),
  CONSTRAINT `chuho_ibfk_1` FOREIGN KEY (`idChuHo`) REFERENCES `nhankhau` (`id`),
  CONSTRAINT `chuho_ibfk_2` FOREIGN KEY (`maHoKhau`) REFERENCES `hokhau` (`ma_ho_khau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuho`
--

LOCK TABLES `chuho` WRITE;
/*!40000 ALTER TABLE `chuho` DISABLE KEYS */;
INSERT INTO `chuho` VALUES (2,'1'),(4,'2'),(4,'3');
/*!40000 ALTER TABLE `chuho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hokhau`
--

DROP TABLE IF EXISTS `hokhau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hokhau` (
  `ma_ho_khau` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `ma_khu_vuc` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `dia_chi` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ma_ho_khau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hokhau`
--

LOCK TABLES `hokhau` WRITE;
/*!40000 ALTER TABLE `hokhau` DISABLE KEYS */;
INSERT INTO `hokhau` VALUES ('1','1','1'),('2','2','2'),('3','3','3');
/*!40000 ALTER TABLE `hokhau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoanthu`
--

DROP TABLE IF EXISTS `khoanthu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoanthu` (
  `maKhoanThu` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `tenKhoanThu` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `soTien` float DEFAULT NULL,
  PRIMARY KEY (`maKhoanThu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoanthu`
--

LOCK TABLES `khoanthu` WRITE;
/*!40000 ALTER TABLE `khoanthu` DISABLE KEYS */;
INSERT INTO `khoanthu` VALUES ('KT001','Phí vệ sinh',1000),('KT002','Tiền thưởng',200),('KT003','Thu nhập bán hàng',700),('KT004','Thu nhập khác',300),('KT005','Phí dịch vụ chung cư',600);
/*!40000 ALTER TABLE `khoanthu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhankhau`
--

DROP TABLE IF EXISTS `nhankhau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhankhau` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hoVaTen` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `nguyenQuan` varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `danToc` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `soCMND` varchar(20) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `noiThuongTru` text COLLATE utf8mb3_unicode_ci,
  `trinhDoHocVan` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `ngheNghiep` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `gioiTinh` tinyint(1) DEFAULT NULL,
  `tonGiao` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `quocTich` varchar(100) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `diaChiHienTai` text COLLATE utf8mb3_unicode_ci,
  `noiLamViec` text COLLATE utf8mb3_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhankhau`
--

LOCK TABLES `nhankhau` WRITE;
/*!40000 ALTER TABLE `nhankhau` DISABLE KEYS */;
INSERT INTO `nhankhau` VALUES (2,'Nguyen Van A','2000-01-01','Hanoi','Kinh','123456789','Hanoi','Dai Hoc','Sinh Vien',1,'Phat Giao','Vietnam','Hanoi','Company A'),(3,'Tran Thi B','1995-05-15','Hue','Kinh','987654321','Hue','Cao Dang','Giao Vien',0,'Cong Giao','Vietnam','Hue','School B'),(4,'Le Van C','1988-12-30','Danang','Kinh','456789012','Danang','Trung Cap','Ky Thuat Vien',1,'Tin Lanh','Vietnam','Danang','Factory C'),(5,'Pham Thi D','1999-06-20','Quang Ninh','Kinh','567890123','Quang Ninh','Dai Hoc','Sinh Vien',0,'Hoi Giao','Vietnam','Quang Ninh','University D'),(6,'Hoang Van E','1992-03-05','Can Tho','Kinh','345678901','Can Tho','Cao Dang','Y Ta',1,'Tin Lanh','Vietnam','Can Tho','Hospital E'),(8,'Cao Minh Đức','2023-11-27','Hoằng Giang','Kinh','13123131','Hà Nội','6/12','Học sinh',1,'Không','Việt Nam','Hà Nội','Trường tiểu học Tây Mỗ'),(9,'Lê Tuấn Phi','2003-01-15','Hoằng Giang, Hoằng Hóa, Thanh Hóa','Kinh','038203011837','Thành phố Hà Nội','Tốt nghiệp 12/12','Cử nhân Công nghệ thông tin',1,'không','Việt Nam','Thành phố Hà Nội','Hà Nội');
/*!40000 ALTER TABLE `nhankhau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phuongtien`
--

DROP TABLE IF EXISTS `phuongtien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phuongtien` (
  `bienSoXe` varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  `nhanHieu` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `idChuSoHuu` int NOT NULL,
  `loaiXe` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`bienSoXe`),
  KEY `idChuSoHuu` (`idChuSoHuu`),
  CONSTRAINT `phuongtien_ibfk_1` FOREIGN KEY (`idChuSoHuu`) REFERENCES `nhankhau` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phuongtien`
--

LOCK TABLES `phuongtien` WRITE;
/*!40000 ALTER TABLE `phuongtien` DISABLE KEYS */;
INSERT INTO `phuongtien` VALUES ('0004','HuynDai',5,'Ô tô'),('0005','Mercedes S600',8,'Ô tô'),('36B8-67141','Honda Airblade',9,'Xe máy');
/*!40000 ALTER TABLE `phuongtien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quanhe`
--

DROP TABLE IF EXISTS `quanhe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quanhe` (
  `maHokhau` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `maNhanKhau` int NOT NULL,
  `quanHe` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`maHokhau`,`maNhanKhau`),
  KEY `maNhanKhau` (`maNhanKhau`),
  CONSTRAINT `quanhe_ibfk_1` FOREIGN KEY (`maHokhau`) REFERENCES `hokhau` (`ma_ho_khau`),
  CONSTRAINT `quanhe_ibfk_2` FOREIGN KEY (`maNhanKhau`) REFERENCES `nhankhau` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quanhe`
--

LOCK TABLES `quanhe` WRITE;
/*!40000 ALTER TABLE `quanhe` DISABLE KEYS */;
/*!40000 ALTER TABLE `quanhe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `tenTaiKhoan` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `matKhau` varchar(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`tenTaiKhoan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES ('Dat09','Dat09'),('PhiLT','admin');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thutien`
--

DROP TABLE IF EXISTS `thutien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thutien` (
  `maKhoanThu` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `IdNguoiNop` int NOT NULL,
  `soTienThu` float NOT NULL,
  `ngayThu` date DEFAULT NULL,
  PRIMARY KEY (`maKhoanThu`,`IdNguoiNop`),
  KEY `IdNguoiNop` (`IdNguoiNop`),
  CONSTRAINT `thutien_ibfk_1` FOREIGN KEY (`maKhoanThu`) REFERENCES `khoanthu` (`maKhoanThu`),
  CONSTRAINT `thutien_ibfk_2` FOREIGN KEY (`IdNguoiNop`) REFERENCES `nhankhau` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thutien`
--

LOCK TABLES `thutien` WRITE;
/*!40000 ALTER TABLE `thutien` DISABLE KEYS */;
INSERT INTO `thutien` VALUES ('KT002',3,50000,'2023-12-28'),('KT002',8,33333,'2023-12-04'),('KT003',8,700000,'2023-12-28'),('KT004',6,77777,'2023-11-26'),('KT005',9,500000,'2023-12-28');
/*!40000 ALTER TABLE `thutien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-29 15:56:13
