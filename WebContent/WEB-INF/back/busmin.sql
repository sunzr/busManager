/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : busmin

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2017-09-10 23:03:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus` (
  `BusID` varchar(20) NOT NULL,
  `BusCardID` varchar(10) DEFAULT NULL,
  `VINNO` varchar(50) DEFAULT NULL,
  `TYPENO` int(10) DEFAULT NULL,
  `FactoryNo` int(10) DEFAULT NULL,
  `Output` decimal(10,2) DEFAULT NULL,
  `Seating` int(4) NOT NULL,
  `TotalMileage` int(10) DEFAULT NULL,
  `BusSize` varchar(50) DEFAULT NULL,
  `Oilwear` decimal(10,2) DEFAULT NULL,
  `WLoad` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`BusID`,`Seating`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus
-- ----------------------------

-- ----------------------------
-- Table structure for busdayinfo
-- ----------------------------
DROP TABLE IF EXISTS `busdayinfo`;
CREATE TABLE `busdayinfo` (
  `InfoNo` int(10) NOT NULL AUTO_INCREMENT,
  `BusID` varchar(20) DEFAULT NULL,
  `DriverID` varchar(20) DEFAULT NULL,
  `InfoDate` datetime DEFAULT NULL,
  `Income` decimal(12,2) DEFAULT NULL,
  `Mileage` int(10) DEFAULT NULL,
  `Oilwear` decimal(10,2) DEFAULT NULL,
  `InfoDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`InfoNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busdayinfo
-- ----------------------------

-- ----------------------------
-- Table structure for busdriver
-- ----------------------------
DROP TABLE IF EXISTS `busdriver`;
CREATE TABLE `busdriver` (
  `DriverID` varchar(20) NOT NULL,
  `Dname` varchar(20) DEFAULT NULL,
  `SEX` varchar(10) DEFAULT NULL,
  `AGE` int(2) DEFAULT NULL,
  `Birthday` datetime DEFAULT NULL,
  `DCARD` varchar(20) DEFAULT NULL,
  `DCODE` varchar(20) DEFAULT NULL,
  `JoinDate` datetime DEFAULT NULL,
  `LeaveDate` datetime DEFAULT NULL,
  `CardPhoto` longblob,
  `CardPhotoFileName` varchar(100) DEFAULT NULL,
  `CardPhotoContentType` varchar(100) DEFAULT NULL,
  `DStatus` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`DriverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busdriver
-- ----------------------------

-- ----------------------------
-- Table structure for busfactory
-- ----------------------------
DROP TABLE IF EXISTS `busfactory`;
CREATE TABLE `busfactory` (
  `FactoryNo` int(10) NOT NULL AUTO_INCREMENT,
  `FactoryName` varchar(50) NOT NULL,
  `FactoryDesc` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`FactoryNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busfactory
-- ----------------------------

-- ----------------------------
-- Table structure for busillegalfee
-- ----------------------------
DROP TABLE IF EXISTS `busillegalfee`;
CREATE TABLE `busillegalfee` (
  `feeNo` int(10) NOT NULL AUTO_INCREMENT,
  `busid` varchar(20) DEFAULT NULL,
  `driverid` varchar(20) DEFAULT NULL,
  `illegaltypeno` int(10) DEFAULT NULL,
  `illegaldate` datetime DEFAULT NULL,
  `paydate` datetime DEFAULT NULL,
  `payfee` int(10) DEFAULT NULL,
  `payscore` int(10) DEFAULT NULL,
  `feeDesc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`feeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busillegalfee
-- ----------------------------

-- ----------------------------
-- Table structure for buslaborcost
-- ----------------------------
DROP TABLE IF EXISTS `buslaborcost`;
CREATE TABLE `buslaborcost` (
  `COSTNO` int(10) NOT NULL AUTO_INCREMENT,
  `CostTypeNo` int(10) DEFAULT NULL,
  `BusID` varchar(20) DEFAULT NULL,
  `DriverID` varchar(20) DEFAULT NULL,
  `CostAmount` decimal(12,2) DEFAULT NULL,
  `CostDate` datetime DEFAULT NULL,
  `CostDesc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`COSTNO`),
  KEY `laborCost_costtype` (`CostTypeNo`),
  KEY `laborCost_bus` (`BusID`),
  KEY `laborCost_driver` (`DriverID`),
  CONSTRAINT `laborCost_bus` FOREIGN KEY (`BusID`) REFERENCES `bus` (`BusID`),
  CONSTRAINT `laborCost_costtype` FOREIGN KEY (`CostTypeNo`) REFERENCES `laborcosttype` (`TYPENO`),
  CONSTRAINT `laborCost_driver` FOREIGN KEY (`DriverID`) REFERENCES `busdriver` (`DriverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buslaborcost
-- ----------------------------

-- ----------------------------
-- Table structure for busoilinfo
-- ----------------------------
DROP TABLE IF EXISTS `busoilinfo`;
CREATE TABLE `busoilinfo` (
  `INFONO` int(10) NOT NULL AUTO_INCREMENT,
  `BUSID` varchar(20) DEFAULT NULL,
  `DriverID` varchar(20) DEFAULT NULL,
  `OilVolume` decimal(10,2) DEFAULT NULL,
  `OilFee` decimal(10,2) DEFAULT NULL,
  `InfoDate` datetime DEFAULT NULL,
  `BusMile` int(11) DEFAULT NULL,
  PRIMARY KEY (`INFONO`),
  KEY `busoil_bus` (`BUSID`),
  KEY `busoil_busdriver` (`DriverID`),
  CONSTRAINT `busoil_bus` FOREIGN KEY (`BUSID`) REFERENCES `bus` (`BusID`),
  CONSTRAINT `busoil_busdriver` FOREIGN KEY (`DriverID`) REFERENCES `busdriver` (`DriverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busoilinfo
-- ----------------------------

-- ----------------------------
-- Table structure for buspayfee
-- ----------------------------
DROP TABLE IF EXISTS `buspayfee`;
CREATE TABLE `buspayfee` (
  `payno` int(10) NOT NULL AUTO_INCREMENT,
  `paytypeno` int(10) DEFAULT NULL,
  `busid` varchar(20) DEFAULT NULL,
  `driverid` varchar(20) DEFAULT NULL,
  `payto` varchar(200) DEFAULT NULL,
  `payamount` int(10) DEFAULT NULL,
  `paydate` datetime DEFAULT NULL,
  `paydesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`payno`),
  KEY `buspay_paytype` (`paytypeno`),
  KEY `buspay_bus` (`busid`),
  KEY `buspay_driver` (`driverid`),
  CONSTRAINT `buspay_bus` FOREIGN KEY (`busid`) REFERENCES `bus` (`BusID`),
  CONSTRAINT `buspay_driver` FOREIGN KEY (`driverid`) REFERENCES `busdriver` (`DriverID`),
  CONSTRAINT `buspay_paytype` FOREIGN KEY (`paytypeno`) REFERENCES `paytype` (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buspayfee
-- ----------------------------

-- ----------------------------
-- Table structure for busserviceinfo
-- ----------------------------
DROP TABLE IF EXISTS `busserviceinfo`;
CREATE TABLE `busserviceinfo` (
  `ServiceNO` int(10) NOT NULL AUTO_INCREMENT,
  `ServiceTypeNo` int(10) DEFAULT NULL,
  `ProviderNo` int(10) DEFAULT NULL,
  `BusID` varchar(20) DEFAULT NULL,
  `DriverID` varchar(20) DEFAULT NULL,
  `ServiceDate` datetime DEFAULT NULL,
  `ServiceDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ServiceNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busserviceinfo
-- ----------------------------

-- ----------------------------
-- Table structure for busserviceinfodetail
-- ----------------------------
DROP TABLE IF EXISTS `busserviceinfodetail`;
CREATE TABLE `busserviceinfodetail` (
  `DetailNo` int(10) NOT NULL AUTO_INCREMENT,
  `ServiceNo` int(10) DEFAULT NULL,
  `Item` varchar(200) DEFAULT NULL,
  `ItemQTY` decimal(12,2) DEFAULT NULL,
  `itemUnitPrice` decimal(12,2) DEFAULT NULL,
  `ItemDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`DetailNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busserviceinfodetail
-- ----------------------------

-- ----------------------------
-- Table structure for bustype
-- ----------------------------
DROP TABLE IF EXISTS `bustype`;
CREATE TABLE `bustype` (
  `TYPENO` int(10) NOT NULL AUTO_INCREMENT,
  `TYPENAME` varchar(50) NOT NULL,
  PRIMARY KEY (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bustype
-- ----------------------------

-- ----------------------------
-- Table structure for functionuser
-- ----------------------------
DROP TABLE IF EXISTS `functionuser`;
CREATE TABLE `functionuser` (
  `FunNo` int(11) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL,
  KEY `FunNo` (`FunNo`),
  KEY `userid` (`userid`),
  CONSTRAINT `functionuser_ibfk_1` FOREIGN KEY (`FunNo`) REFERENCES `systemfunction` (`FUNNO`),
  CONSTRAINT `functionuser_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of functionuser
-- ----------------------------
INSERT INTO `functionuser` VALUES ('2', '111');
INSERT INTO `functionuser` VALUES ('2', '113');
INSERT INTO `functionuser` VALUES ('2', '114');
INSERT INTO `functionuser` VALUES ('2', '115');
INSERT INTO `functionuser` VALUES ('3', '111');
INSERT INTO `functionuser` VALUES ('3', '113');
INSERT INTO `functionuser` VALUES ('3', '114');
INSERT INTO `functionuser` VALUES ('3', '115');
INSERT INTO `functionuser` VALUES ('1', '111');
INSERT INTO `functionuser` VALUES ('1', '113');
INSERT INTO `functionuser` VALUES ('4', '111');
INSERT INTO `functionuser` VALUES ('8', '111');
INSERT INTO `functionuser` VALUES ('7', '111');
INSERT INTO `functionuser` VALUES ('6', '111');
INSERT INTO `functionuser` VALUES ('5', '111');
INSERT INTO `functionuser` VALUES ('1', '117');
INSERT INTO `functionuser` VALUES ('2', '117');

-- ----------------------------
-- Table structure for illegaltype
-- ----------------------------
DROP TABLE IF EXISTS `illegaltype`;
CREATE TABLE `illegaltype` (
  `TYPENO` int(10) NOT NULL AUTO_INCREMENT,
  `TYPENAME` varchar(100) DEFAULT NULL,
  `PayScore` int(4) DEFAULT NULL,
  `PayFee` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of illegaltype
-- ----------------------------

-- ----------------------------
-- Table structure for laborcosttype
-- ----------------------------
DROP TABLE IF EXISTS `laborcosttype`;
CREATE TABLE `laborcosttype` (
  `TYPENO` int(10) NOT NULL AUTO_INCREMENT,
  `TYPENAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of laborcosttype
-- ----------------------------

-- ----------------------------
-- Table structure for levelmanager
-- ----------------------------
DROP TABLE IF EXISTS `levelmanager`;
CREATE TABLE `levelmanager` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `lname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of levelmanager
-- ----------------------------
INSERT INTO `levelmanager` VALUES ('1', '用户管理模块');
INSERT INTO `levelmanager` VALUES ('3', '系统操作与配置');
INSERT INTO `levelmanager` VALUES ('4', '车辆日常业务管理');

-- ----------------------------
-- Table structure for paytype
-- ----------------------------
DROP TABLE IF EXISTS `paytype`;
CREATE TABLE `paytype` (
  `TYPENO` int(10) NOT NULL AUTO_INCREMENT,
  `TYPENAME` varchar(100) DEFAULT NULL,
  `PayFee` decimal(12,2) DEFAULT NULL,
  `PayTo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of paytype
-- ----------------------------

-- ----------------------------
-- Table structure for serviceprovider
-- ----------------------------
DROP TABLE IF EXISTS `serviceprovider`;
CREATE TABLE `serviceprovider` (
  `ProviderNo` int(10) NOT NULL AUTO_INCREMENT,
  `PName` varchar(50) DEFAULT NULL,
  `PContact` varchar(20) DEFAULT NULL,
  `PAddress` varchar(200) DEFAULT NULL,
  `PMobile` varchar(20) DEFAULT NULL,
  `PTel` varchar(20) DEFAULT NULL,
  `PDesc` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`ProviderNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serviceprovider
-- ----------------------------

-- ----------------------------
-- Table structure for servicetype
-- ----------------------------
DROP TABLE IF EXISTS `servicetype`;
CREATE TABLE `servicetype` (
  `TYPENO` int(10) NOT NULL AUTO_INCREMENT,
  `TYPENAME` varchar(50) NOT NULL,
  `TypeDesc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`TYPENO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of servicetype
-- ----------------------------

-- ----------------------------
-- Table structure for systemfunction
-- ----------------------------
DROP TABLE IF EXISTS `systemfunction`;
CREATE TABLE `systemfunction` (
  `FUNNO` int(11) NOT NULL AUTO_INCREMENT,
  `FUNNAME` varchar(50) DEFAULT NULL,
  `FUNURL` varchar(200) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  PRIMARY KEY (`FUNNO`),
  KEY `function_level` (`lid`),
  CONSTRAINT `function_level` FOREIGN KEY (`lid`) REFERENCES `levelmanager` (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemfunction
-- ----------------------------
INSERT INTO `systemfunction` VALUES ('1', '用户管理模块', 'userinfo/main.html', '3');
INSERT INTO `systemfunction` VALUES ('2', '模块管理', 'level/main.html', '3');
INSERT INTO `systemfunction` VALUES ('3', '权限管理', 'function/main.html', '3');
INSERT INTO `systemfunction` VALUES ('4', '车辆', 'car/main.html', '1');
INSERT INTO `systemfunction` VALUES ('5', '司机', 'driver/main.html', '1');
INSERT INTO `systemfunction` VALUES ('6', '维修单位', 'repair/main.html', '1');
INSERT INTO `systemfunction` VALUES ('7', '车辆日常运行', 'usually/main.html', '4');
INSERT INTO `systemfunction` VALUES ('8', '加油记录', 'record/main.html', '4');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `USERID` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('111', '123', '牛嘟嘟');
INSERT INTO `userinfo` VALUES ('113', '123456', '泣血之刃');
INSERT INTO `userinfo` VALUES ('114', '1002', '无尽战刃');
INSERT INTO `userinfo` VALUES ('115', '123', '破军');
INSERT INTO `userinfo` VALUES ('117', '12345', '末世');
