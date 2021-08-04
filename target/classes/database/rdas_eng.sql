

DROP TABLE IF EXISTS `equ_alarmrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_alarmrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` varchar(50) NOT NULL,
  `DataType_ID` int(11) NOT NULL,
  `AlarmType` int(11) NOT NULL,
  `AlarmTime` datetime NOT NULL,
  `ConfirmTime` datetime DEFAULT NULL,
  `UserCode` varchar(100) DEFAULT NULL,
  `Recovery` bit(1) NOT NULL DEFAULT b'0',
  `RecoveryTime` datetime DEFAULT NULL,
  `Reason` text,
  `Des` text,
  PRIMARY KEY (`AlarmType`,`DataType_ID`,`Equipment_ID`),
  KEY `ID` (`ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  CONSTRAINT `FK_equ_AlarmRecord_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_battery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_battery` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Equipment_ID` varchar(50) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `ProductDate` datetime DEFAULT NULL,
  `QualityPeriod` varchar(10) DEFAULT NULL,
  `InitialPower` varchar(10) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `AlarmLimit` double DEFAULT NULL,
  `CurrentValue` double DEFAULT NULL,
  `Unit_ID` int(11) DEFAULT NULL,
  `Company` varchar(50) DEFAULT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `equ_command`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_command` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` varchar(50) NOT NULL,
  `CommandType` int(11) NOT NULL,
  `SettingTime` datetime NOT NULL,
  `Command` longtext,
  `UserCode` varchar(100) NOT NULL,
  `Status` int(11) NOT NULL DEFAULT '1',
  `SendTime` datetime DEFAULT NULL,
  `SendNum` int(11) DEFAULT '0',
  `ResponseTime` datetime DEFAULT NULL,
  `ResponseMessage` varchar(50) DEFAULT NULL,
  `CheckTime` datetime DEFAULT NULL,
  `CheckUserCode` varchar(100) DEFAULT NULL,
  `Des` text,
  `CheckDes` text,
  PRIMARY KEY (`ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  CONSTRAINT `FK_equ_Command_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `equ_datatype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_datatype` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `IfAccumulate` bit(1) NOT NULL DEFAULT b'0',
  `IfAlarm` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipment` (
  `ID` varchar(50) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `EquipmentType_ID` int(11) NOT NULL DEFAULT '1',
  `Manufacturer_ID` int(11) NOT NULL DEFAULT '1',
  `Status` bit(1) NOT NULL DEFAULT b'0',
  `UploadNum` int(11) NOT NULL DEFAULT '0',
  `Address` longblob,
  `SetupTime` date DEFAULT NULL,
  `SetupPerson` varchar(20) DEFAULT NULL,
  `Model` varchar(50) DEFAULT NULL,
  `FirstUploadTime` datetime DEFAULT NULL,
  `LastUploadTime` datetime DEFAULT NULL,
  `LastCollectTime` datetime DEFAULT NULL,
  `IPAddress` varchar(15) DEFAULT NULL,
  `Port` int(11) DEFAULT NULL,
  `Longitude` double DEFAULT NULL,
  `Latitude` double DEFAULT NULL,
  `Version` varchar(5) DEFAULT NULL,
  `Manager` varchar(20) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `PowerType` int(11) DEFAULT '0',
  `PicPath` varchar(200) DEFAULT NULL,
  `FilePath` varchar(200) DEFAULT NULL,
  `Des` text,
  `SetupAddress` text,
  `OffLineTimeLimit` int(11) NOT NULL DEFAULT '0',
  `ConnectServerPort` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentgroup` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `ParentID` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentgroup_equipment_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentgroup_equipment_map` (
  `EquipmentGroup_ID` int(11) NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`Equipment_ID`,`EquipmentGroup_ID`),
  KEY `EquipmentGroup_ID` (`EquipmentGroup_ID`),
  CONSTRAINT `FK_equ_EquipmentGroup_Equipment_Map_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_EquipmentGroup_Equipment_Map_equ_EquipmentGroup` FOREIGN KEY (`EquipmentGroup_ID`) REFERENCES `equ_equipmentgroup` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentpara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentpara` (
  `Equipment_ID` varchar(50) NOT NULL,
  `Para_ID` int(11) NOT NULL,
  `ParaValue` text NOT NULL,
  `UploadTime` datetime NOT NULL,
  `Unit_ID` int(11) DEFAULT NULL,
  `UpLimit` double DEFAULT NULL,
  `DownLimit` double DEFAULT NULL,
  PRIMARY KEY (`Equipment_ID`,`Para_ID`),
  KEY `Para_ID` (`Para_ID`),
  CONSTRAINT `FK_equ_EquipmentPara_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_EquipmentPara_equ_Para` FOREIGN KEY (`Para_ID`) REFERENCES `equ_para` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentparasettingrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentparasettingrecord` (
  `ID` int(11) NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `Para` longtext NOT NULL,
  `Des` text,
  `SettingTime` datetime NOT NULL,
  `UserCode` varchar(100) NOT NULL,
  `SendTime` datetime DEFAULT NULL,
  `SendNum` int(11) NOT NULL DEFAULT '0',
  `SuccessFlag` bit(1) NOT NULL,
  `ResponseTime` datetime DEFAULT NULL,
  `ResponseMessage` varchar(50) DEFAULT NULL,
  `CheckTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK_equ_EquipmentParaSettingRecord_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_EquipmentParaSettingRecord_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentparavaluerange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentparavaluerange` (
  `Equipment_ID` varchar(50) NOT NULL,
  `Para_ID` int(11) NOT NULL,
  `ParaValue_ID` int(11) NOT NULL,
  PRIMARY KEY (`Equipment_ID`,`Para_ID`,`ParaValue_ID`),
  KEY `Para_ID` (`Para_ID`),
  CONSTRAINT `FK_equ_EquipmentParaValueRange_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_EquipmentParaValueRange_equ_Para` FOREIGN KEY (`Para_ID`) REFERENCES `equ_para` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmentrealdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmentrealdata` (
  `Equipment_ID` varchar(50) NOT NULL,
  `DataType_ID` int(11) NOT NULL,
  `Value` double NOT NULL,
  `Unit_ID` int(11) NOT NULL,
  `Position` int(11) NOT NULL,
  `IfRecord` bit(1) NOT NULL,
  `IfCurve` bit(1) NOT NULL,
  `IfAlarm` bit(1) NOT NULL,
  `IfAccumulate` bit(1) NOT NULL,
  `UpLimit` double DEFAULT '0',
  `DownLimit` double DEFAULT '0',
  `LastUpdateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`DataType_ID`,`Equipment_ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  CONSTRAINT `FK_equ_EquipmentRealData_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_equipmenttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_equipmenttype` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `OffLineLimit` int(11) NOT NULL DEFAULT '30',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_manufacturer` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Contact` varchar(20) DEFAULT NULL,
  `Telephone` varchar(20) DEFAULT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_para`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_para` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `ParaGroup_ID` int(11) NOT NULL,
  `Type` int(11) NOT NULL,
  `ReadOnly` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`),
  KEY `ParaGroup_ID` (`ParaGroup_ID`),
  CONSTRAINT `FK_equ_Para_equ_ParaGroup` FOREIGN KEY (`ParaGroup_ID`) REFERENCES `equ_paragroup` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_paragroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_paragroup` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_paravalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_paravalue` (
  `ID` int(11) NOT NULL,
  `Para_ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`,`Para_ID`),
  KEY `Para_ID` (`Para_ID`),
  CONSTRAINT `FK_equ_ParaValue_equ_Para` FOREIGN KEY (`Para_ID`) REFERENCES `equ_para` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_responserecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_responserecord` (
  `CollectTime` datetime NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `ResponseTime` datetime NOT NULL,
  `ResponesType` varchar(10) NOT NULL,
  `IPAddress` varchar(15) NOT NULL,
  `Port` int(11) NOT NULL,
  `sendData` varchar(100) NOT NULL,
  PRIMARY KEY (`CollectTime`,`Equipment_ID`,`ResponseTime`),
  KEY `Equipment_ID` (`Equipment_ID`),
  CONSTRAINT `FK_equ_ResponseRecord_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_server` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `IPAddress` varchar(15) DEFAULT NULL,
  `Port` int(11) DEFAULT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_server_equipment_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_server_equipment_map` (
  `Server_ID` int(11) NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `LastDownloadDataTime` datetime DEFAULT NULL,
  `DataPacket` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Equipment_ID`,`Server_ID`),
  KEY `Server_ID` (`Server_ID`),
  CONSTRAINT `FK_equ_Server_Equipment_Map_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_Server_Equipment_Map_equ_Server` FOREIGN KEY (`Server_ID`) REFERENCES `equ_server` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_sim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_sim` (
  `ID` varchar(20) NOT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `CreatePerson` varchar(100) NOT NULL,
  `ActivateTime` date DEFAULT NULL,
  `Status` varchar(2) DEFAULT NULL,
  `StatusLastUpdateTime` datetime DEFAULT NULL,
  `SIMPackage_ID` int(11) NOT NULL,
  `Equipment_ID` varchar(50) DEFAULT NULL,
  `Des` text,
  `SIMAPP_ID` varchar(50) NOT NULL,
  `Balance` double DEFAULT NULL,
  `BalanceLastUpdateTime` datetime DEFAULT NULL,
  `Enable` bit(1) NOT NULL DEFAULT b'1',
  `ReadNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SIMAPP_ID` (`SIMAPP_ID`),
  KEY `SIMPackage_ID` (`SIMPackage_ID`),
  CONSTRAINT `FK_equ_SIM_equ_SIMAPP` FOREIGN KEY (`SIMAPP_ID`) REFERENCES `equ_simapp` (`ID`),
  CONSTRAINT `FK_equ_SIM_equ_SIMPackage` FOREIGN KEY (`SIMPackage_ID`) REFERENCES `equ_simpackage` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_simapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_simapp` (
  `ID` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_simbalancehistoryrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_simbalancehistoryrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SIM_ID` varchar(20) NOT NULL,
  `ReadTime` datetime NOT NULL,
  `Balance` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `SIM_ID` (`SIM_ID`),
  CONSTRAINT `FK_equ_SIMBalanceHistoryRecord_equ_SIM` FOREIGN KEY (`SIM_ID`) REFERENCES `equ_sim` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=939 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_simpackage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_simpackage` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Term` int(11) NOT NULL,
  `StartFlag` bit(1) NOT NULL,
  `AlarmValue` double NOT NULL,
  `AlarmType` bit(1) NOT NULL,
  `Price` double DEFAULT NULL,
  `TotalFlow` double DEFAULT NULL,
  `OverFlowPrice` double DEFAULT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_unit` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `equ_updateprogramrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_updateprogramrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` varchar(50) NOT NULL,
  `Vision` varchar(50) NOT NULL,
  `FilePath` text NOT NULL,
  `FileLength` int(11) NOT NULL,
  `SettingTime` datetime NOT NULL,
  `UserCode` varchar(100) NOT NULL,
  `Status` int(11) NOT NULL,
  `ResponseTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK_equ_UpdateProgramRecord_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_equ_UpdateProgramRecord_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `equ_user_equipmentgroup_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equ_user_equipmentgroup_map` (
  `UserCode` varchar(100) NOT NULL,
  `EquipmentGroup_ID` int(11) NOT NULL,
  PRIMARY KEY (`EquipmentGroup_ID`,`UserCode`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK_equ_User_EquipmentGroup_Map_equ_EquipmentGroup` FOREIGN KEY (`EquipmentGroup_ID`) REFERENCES `equ_equipmentgroup` (`ID`),
  CONSTRAINT `FK_equ_User_EquipmentGroup_Map_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_advertisement` (
  `ID` int(11) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Message` text NOT NULL,
  `Visible` bit(1) NOT NULL DEFAULT b'1',
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_appapi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_appapi` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Path` varchar(100) NOT NULL,
  `Verify` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_button` (
  `ButtonCode` varchar(20) NOT NULL,
  `ButtonName` varchar(20) DEFAULT NULL,
  `ButtonSeq` int(11) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `ButtonIcon` varchar(50) DEFAULT NULL,
  `CreatePerson` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(20) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ButtonCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_code` (
  `Code` varchar(100) NOT NULL,
  `Value` varchar(200) DEFAULT NULL,
  `Text` varchar(200) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `Seq` varchar(10) DEFAULT NULL,
  `IsEnable` bit(1) DEFAULT NULL,
  `IsDefault` bit(1) DEFAULT NULL,
  `Description` text,
  `CodeTypeName` varchar(200) DEFAULT NULL,
  `CodeType` varchar(100) DEFAULT NULL,
  `CreatePerson` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(20) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Code`),
  KEY `CodeType` (`CodeType`),
  CONSTRAINT `FK__SysCode__TypeCod__5CA1C101` FOREIGN KEY (`CodeType`) REFERENCES `sys_codetype` (`CodeType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_codetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_codetype` (
  `CodeType` varchar(100) NOT NULL,
  `CodeTypeName` varchar(200) DEFAULT NULL,
  `Description` text,
  `Seq` varchar(10) DEFAULT NULL,
  `CreatePerson` varchar(20) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(20) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CodeType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserCode` varchar(100) DEFAULT NULL,
  `UserName` varchar(100) DEFAULT NULL,
  `Position` text,
  `Target` varchar(255) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Message` longtext,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=376 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_loginhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_loginhistory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserCode` varchar(100) DEFAULT NULL,
  `UserName` varchar(200) DEFAULT NULL,
  `HostName` varchar(200) DEFAULT NULL,
  `HostIP` varchar(50) DEFAULT NULL,
  `LoginCity` varchar(200) DEFAULT NULL,
  `LoginDate` datetime DEFAULT NULL,
  `LogoutDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1976 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_loginpagerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_loginpagerecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserCode` varchar(100) NOT NULL,
  `MenuCode` varchar(100) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MenuCode` (`MenuCode`),
  CONSTRAINT `FK_sys_loginPageRecord_sys_menu` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `MenuCode` varchar(100) NOT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `MenuName` varchar(200) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `IconClass` varchar(50) DEFAULT NULL,
  `IconURL` varchar(200) DEFAULT NULL,
  `MenuSeq` varchar(10) DEFAULT NULL,
  `Description` text,
  `IsVisible` bit(1) DEFAULT NULL,
  `IsEnable` bit(1) DEFAULT NULL,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`MenuCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_menubuttonmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menubuttonmap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MenuCode` varchar(100) DEFAULT NULL,
  `ButtonCode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ButtonCode` (`ButtonCode`),
  KEY `MenuCode` (`MenuCode`),
  CONSTRAINT `FK__sys_menuB__Butto__16CE6296` FOREIGN KEY (`ButtonCode`) REFERENCES `sys_button` (`ButtonCode`),
  CONSTRAINT `FK__sys_menuB__MenuC__12FDD1B2` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_menucolumns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menucolumns` (
  `MenuCode` varchar(100) NOT NULL,
  `Sequence` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Visible` bit(1) NOT NULL DEFAULT b'1',
  `Frozen` bit(1) NOT NULL DEFAULT b'0',
  `Align` varchar(20) NOT NULL DEFAULT 'N''center''',
  `Sort` bit(1) DEFAULT b'1',
  `GroupName` varchar(50) DEFAULT NULL,
  `DisplayName` varchar(50) NOT NULL,
  `Width` int(11) NOT NULL DEFAULT '80',
  `Field` varchar(50) NOT NULL,
  `Formatter` varchar(50) DEFAULT NULL,
  `Editor` varchar(100) DEFAULT NULL,
  `Hidden` bit(1) DEFAULT b'0',
  `Required` bit(1) NOT NULL DEFAULT b'0',
  KEY `MenuCode` (`MenuCode`),
  CONSTRAINT `FK_sys_MenuColumns_sys_menu` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_organize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_organize` (
  `OrganizeCode` varchar(100) NOT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `OrganizeSeq` varchar(10) DEFAULT NULL,
  `OrganizeName` varchar(200) DEFAULT NULL,
  `Description` text,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`OrganizeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_organizerolemap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_organizerolemap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrganizeCode` varchar(100) NOT NULL,
  `RoleCode` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `OrganizeCode` (`OrganizeCode`),
  KEY `RoleCode` (`RoleCode`),
  CONSTRAINT `R_38` FOREIGN KEY (`OrganizeCode`) REFERENCES `sys_organize` (`OrganizeCode`),
  CONSTRAINT `R_39` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `sys_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_parameter` (
  `ParamCode` varchar(100) NOT NULL,
  `ParamValue` varchar(200) DEFAULT NULL,
  `IsUserEditable` bit(1) DEFAULT NULL,
  `Description` text,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ParamCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `PermissionCode` varchar(100) NOT NULL,
  `PermissionName` varchar(200) DEFAULT NULL,
  `ParentCode` varchar(100) DEFAULT NULL,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`PermissionCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `RoleCode` varchar(100) NOT NULL,
  `RoleSeq` varchar(10) DEFAULT NULL,
  `RoleName` varchar(200) DEFAULT NULL,
  `Description` text,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_rolemenubuttonmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_rolemenubuttonmap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleCode` varchar(100) DEFAULT NULL,
  `MenuCode` varchar(100) DEFAULT NULL,
  `ButtonCode` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ButtonCode` (`ButtonCode`),
  KEY `MenuCode` (`MenuCode`),
  KEY `RoleCode` (`RoleCode`),
  CONSTRAINT `FK__sys_roleM__Butto__27F8EE98` FOREIGN KEY (`ButtonCode`) REFERENCES `sys_button` (`ButtonCode`),
  CONSTRAINT `FK__sys_roleM__MenuC__28ED12D1` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
  CONSTRAINT `FK__sys_roleM__RoleC__29E1370A` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_rolemenucolumnmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_rolemenucolumnmap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleCode` varchar(100) DEFAULT NULL,
  `MenuCode` varchar(100) DEFAULT NULL,
  `IsReject` bit(1) DEFAULT NULL,
  `FieldName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MenuCode` (`MenuCode`),
  KEY `RoleCode` (`RoleCode`),
  CONSTRAINT `FK__sys_roleC__MenuC__36470DEF` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
  CONSTRAINT `FK__sys_roleC__RoleC__3552E9B6` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_rolemenumap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_rolemenumap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleCode` varchar(100) DEFAULT NULL,
  `MenuCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MenuCode` (`MenuCode`),
  KEY `RoleCode` (`RoleCode`),
  CONSTRAINT `FK__SysRoleFu__Funct__0D7A0286` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
  CONSTRAINT `FK__SysRoleFu__RoleC__0C85DE4D` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=13691 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_rolepermissionmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_rolepermissionmap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoleCode` varchar(100) DEFAULT NULL,
  `PermissionCode` varchar(100) DEFAULT NULL,
  `IsDefault` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PermissionCode` (`PermissionCode`),
  KEY `RoleCode` (`RoleCode`),
  CONSTRAINT `FK__sys_roleA__AuthC__2EA5EC27` FOREIGN KEY (`PermissionCode`) REFERENCES `sys_permission` (`PermissionCode`),
  CONSTRAINT `FK__sys_roleA__RoleC__2F9A1060` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_systemalarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_systemalarm` (
  `ReceiveSoftRecordTime` datetime NOT NULL,
  `FlowSoftRecordTime` datetime NOT NULL,
  `CDiskSpace` double NOT NULL,
  `DataBaseDiskSpace` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `UserCode` varchar(100) NOT NULL,
  `UserSeq` varchar(10) DEFAULT NULL,
  `UserName` varchar(200) DEFAULT NULL,
  `Description` text,
  `Password` varchar(200) DEFAULT NULL,
  `RoleName` text,
  `OrganizeName` text,
  `ConfigJSON` text,
  `IsEnable` bit(1) DEFAULT NULL,
  `LoginCount` int(11) DEFAULT NULL,
  `LastLoginDate` datetime DEFAULT NULL,
  `CreatePerson` varchar(100) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `UpdatePerson` varchar(100) DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_user_appapi_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_appapi_map` (
  `AppApi_ID` int(11) NOT NULL,
  `UserCode` varchar(100) NOT NULL,
  KEY `AppApi_ID` (`AppApi_ID`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK_sys_User_AppApi_Map_sys_AppApi` FOREIGN KEY (`AppApi_ID`) REFERENCES `sys_appapi` (`ID`),
  CONSTRAINT `FK_sys_User_AppApi_Map_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_usermenucolumnsetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_usermenucolumnsetting` (
  `MenuCode` varchar(100) NOT NULL,
  `UserCode` varchar(100) NOT NULL,
  `Sequence` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Visible` bit(1) NOT NULL,
  `Frozen` bit(1) NOT NULL,
  `Align` varchar(20) NOT NULL,
  `Sort` bit(1) DEFAULT NULL,
  `GroupName` varchar(50) DEFAULT NULL,
  `DisplayName` varchar(50) NOT NULL,
  `Width` int(11) NOT NULL,
  `Field` varchar(50) NOT NULL,
  `Formatter` varchar(50) DEFAULT NULL,
  `Editor` varchar(100) DEFAULT NULL,
  `Hidden` bit(1) DEFAULT NULL,
  `Required` bit(1) NOT NULL,
  KEY `MenuCode` (`MenuCode`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK_sys_UserMenuColumnSetting_sys_menu` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
  CONSTRAINT `FK_sys_UserMenuColumnSetting_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_userorganizemap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_userorganizemap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OrganizeCode` varchar(100) DEFAULT NULL,
  `UserCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `OrganizeCode` (`OrganizeCode`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK__SysUserOr__Organ__0E6E26BF` FOREIGN KEY (`OrganizeCode`) REFERENCES `sys_organize` (`OrganizeCode`),
  CONSTRAINT `FK__SysUserOr__UserC__0F624AF8` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB AUTO_INCREMENT=542 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_userrolemap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_userrolemap` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserCode` varchar(100) DEFAULT NULL,
  `RoleCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `RoleCode` (`RoleCode`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK__SysUserRo__RoleC__114A936A` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`),
  CONSTRAINT `FK__SysUserRo__UserC__10566F31` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB AUTO_INCREMENT=418 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sys_usersetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_usersetting` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserCode` varchar(100) DEFAULT NULL,
  `SettingCode` varchar(100) DEFAULT NULL,
  `SettingName` varchar(200) DEFAULT NULL,
  `SettingValue` varchar(100) DEFAULT NULL,
  `Description` text,
  PRIMARY KEY (`ID`),
  KEY `UserCode` (`UserCode`),
  CONSTRAINT `FK__sys_userS__UserC__32767D0B` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `sysdiagrams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysdiagrams` (
  `name` varchar(128) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `diagram_id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `definition` longblob,
  PRIMARY KEY (`diagram_id`),
  UNIQUE KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_alarmpara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_alarmpara` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `AlarmType_ID` int(11) NOT NULL,
  `ParaValue` double NOT NULL,
  `Des` text,
  PRIMARY KEY (`ID`),
  KEY `AlarmType_ID` (`AlarmType_ID`),
  CONSTRAINT `FK_wat_AlarmPara_wat_AlarmType` FOREIGN KEY (`AlarmType_ID`) REFERENCES `wat_alarmtype` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wat_alarmrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_alarmrecord` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` varchar(50) NOT NULL,
  `AlarmType_ID` int(11) NOT NULL,
  `AlarmTime` datetime NOT NULL,
  `ConfirmTime` datetime DEFAULT NULL,
  `UserCode` varchar(100) DEFAULT NULL,
  `Recovery` bit(1) NOT NULL DEFAULT b'0',
  `RecoveryTime` datetime DEFAULT NULL,
  `Reason` text,
  `Des` text,
  PRIMARY KEY (`ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  KEY `AlarmType_ID` (`AlarmType_ID`),
  CONSTRAINT `FK_wat_AlarmRecord_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_wat_AlarmRecord_wat_AlarmType` FOREIGN KEY (`AlarmType_ID`) REFERENCES `wat_alarmtype` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wat_alarmtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_alarmtype` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Des` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_alarmtype_flow_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_alarmtype_flow_map` (
  `AlarmType_ID` int(11) NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  PRIMARY KEY (`AlarmType_ID`,`Equipment_ID`),
  KEY `Equipment_ID` (`Equipment_ID`),
  CONSTRAINT `FK_wat_AlarmType_Flow_Map_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`),
  CONSTRAINT `FK_wat_AlarmType_Flow_Map_wat_AlarmType` FOREIGN KEY (`AlarmType_ID`) REFERENCES `wat_alarmtype` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_caliber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_caliber` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_classify` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_dayaccumulate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_dayaccumulate` (
  `DayTime` date NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `BeforeYesterdayAccmulate` double NOT NULL DEFAULT '0',
  `LastAccumulate` double NOT NULL DEFAULT '0',
  `Accumulate` double NOT NULL DEFAULT '0',
  `SevenDaysAccumulate` double NOT NULL DEFAULT '0',
  `LastYearAccumulate` double NOT NULL DEFAULT '0',
  `AvgFlow` double NOT NULL DEFAULT '0',
  `LastDaySameTimeAvgFlow` double NOT NULL DEFAULT '0',
  `RelativeRatio` double NOT NULL DEFAULT '0',
  `IncreaseNum` int(11) NOT NULL DEFAULT '0',
  `DecreaseNum` int(11) NOT NULL DEFAULT '0',
  `ZeroNum` int(11) NOT NULL DEFAULT '0',
  `HourMaxAccumulate` double NOT NULL DEFAULT '0',
  `HourMaxTime` datetime DEFAULT NULL,
  `HourMinAccumulate` double NOT NULL DEFAULT '0',
  `HourMinTime` datetime DEFAULT NULL,
  `PeakAccumulate` double NOT NULL DEFAULT '0',
  `ValleyAccumulate` double NOT NULL DEFAULT '0',
  `NormalAccumulate` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`DayTime`,`Equipment_ID`),
  KEY `IX_wat_DayAccumulate` (`DayTime`),
  KEY `IX_wat_DayAccumulate_1` (`Equipment_ID`),
  CONSTRAINT `FK_wat_DayAccumulate_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_flow` (
  `Equipment_ID` varchar(50) NOT NULL,
  `Caliber_ID` int(11) DEFAULT '1',
  `Classify_ID` int(11) DEFAULT '1',
  `Model` varchar(50) DEFAULT NULL,
  `ClientName` varchar(50) DEFAULT NULL,
  `BookNo` varchar(50) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Areas` double DEFAULT NULL,
  `Persons` double DEFAULT NULL,
  `Category` int(11) NOT NULL DEFAULT '0',
  `Status` bit(1) NOT NULL DEFAULT b'0',
  `PositiveTotalAccumulate` double NOT NULL DEFAULT '0',
  `NegativeTotalAccumulate` double NOT NULL DEFAULT '0',
  `TotalAccumulate` double NOT NULL DEFAULT '0',
  `FlowType_ID` int(11) DEFAULT '1',
  `StoreMode` varchar(50) DEFAULT NULL,
  `SiteNo` varchar(20) DEFAULT NULL,
  `Des` text,
  `AvgHourAccumulateT0` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT1` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT2` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT3` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT4` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT5` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT6` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT7` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT8` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT9` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT10` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT11` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT12` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT13` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT14` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT15` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT16` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT17` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT18` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT19` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT20` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT21` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT22` double NOT NULL DEFAULT '0',
  `AvgHourAccumulateT23` double NOT NULL DEFAULT '0',
  `AvgHourNumT0` double NOT NULL DEFAULT '0',
  `AvgHourNumT1` double NOT NULL DEFAULT '0',
  `AvgHourNumT2` double NOT NULL DEFAULT '0',
  `AvgHourNumT3` double NOT NULL DEFAULT '0',
  `AvgHourNumT4` double NOT NULL DEFAULT '0',
  `AvgHourNumT5` double NOT NULL DEFAULT '0',
  `AvgHourNumT6` double NOT NULL DEFAULT '0',
  `AvgHourNumT7` double NOT NULL DEFAULT '0',
  `AvgHourNumT8` double NOT NULL DEFAULT '0',
  `AvgHourNumT9` double NOT NULL DEFAULT '0',
  `AvgHourNumT10` double NOT NULL DEFAULT '0',
  `AvgHourNumT11` double NOT NULL DEFAULT '0',
  `AvgHourNumT12` double NOT NULL DEFAULT '0',
  `AvgHourNumT13` double NOT NULL DEFAULT '0',
  `AvgHourNumT14` double NOT NULL DEFAULT '0',
  `AvgHourNumT15` double NOT NULL DEFAULT '0',
  `AvgHourNumT16` double NOT NULL DEFAULT '0',
  `AvgHourNumT17` double NOT NULL DEFAULT '0',
  `AvgHourNumT18` double NOT NULL DEFAULT '0',
  `AvgHourNumT19` double NOT NULL DEFAULT '0',
  `AvgHourNumT20` double NOT NULL DEFAULT '0',
  `AvgHourNumT21` double NOT NULL DEFAULT '0',
  `AvgHourNumT22` double NOT NULL DEFAULT '0',
  `AvgHourNumT23` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT1` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT2` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT3` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT4` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT5` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT6` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT7` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT8` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT9` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT10` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT11` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT12` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT13` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT14` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT15` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT16` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT17` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT18` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT19` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT20` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT21` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT22` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT23` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT24` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT25` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT26` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT27` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT28` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT29` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT30` double NOT NULL DEFAULT '0',
  `AvgDayAccumulateT31` double NOT NULL DEFAULT '0',
  `AvgDayNumT1` double NOT NULL DEFAULT '0',
  `AvgDayNumT2` double NOT NULL DEFAULT '0',
  `AvgDayNumT3` double NOT NULL DEFAULT '0',
  `AvgDayNumT4` double NOT NULL DEFAULT '0',
  `AvgDayNumT5` double NOT NULL DEFAULT '0',
  `AvgDayNumT6` double NOT NULL DEFAULT '0',
  `AvgDayNumT7` double NOT NULL DEFAULT '0',
  `AvgDayNumT8` double NOT NULL DEFAULT '0',
  `AvgDayNumT9` double NOT NULL DEFAULT '0',
  `AvgDayNumT10` double NOT NULL DEFAULT '0',
  `AvgDayNumT11` double NOT NULL DEFAULT '0',
  `AvgDayNumT12` double NOT NULL DEFAULT '0',
  `AvgDayNumT13` double NOT NULL DEFAULT '0',
  `AvgDayNumT14` double NOT NULL DEFAULT '0',
  `AvgDayNumT15` double NOT NULL DEFAULT '0',
  `AvgDayNumT16` double NOT NULL DEFAULT '0',
  `AvgDayNumT17` double NOT NULL DEFAULT '0',
  `AvgDayNumT18` double NOT NULL DEFAULT '0',
  `AvgDayNumT19` double NOT NULL DEFAULT '0',
  `AvgDayNumT20` double NOT NULL DEFAULT '0',
  `AvgDayNumT21` double NOT NULL DEFAULT '0',
  `AvgDayNumT22` double NOT NULL DEFAULT '0',
  `AvgDayNumT23` double NOT NULL DEFAULT '0',
  `AvgDayNumT24` double NOT NULL DEFAULT '0',
  `AvgDayNumT25` double NOT NULL DEFAULT '0',
  `AvgDayNumT26` double NOT NULL DEFAULT '0',
  `AvgDayNumT27` double NOT NULL DEFAULT '0',
  `AvgDayNumT28` double NOT NULL DEFAULT '0',
  `AvgDayNumT29` double NOT NULL DEFAULT '0',
  `AvgDayNumT30` double NOT NULL DEFAULT '0',
  `AvgDayNumT31` double NOT NULL DEFAULT '0',
  `Enable` bit(1) NOT NULL DEFAULT b'1',
  `LastTime` datetime DEFAULT NULL,
  `LastPositiveTotalAccumulate` double NOT NULL DEFAULT '0',
  `LastNegativeTotalAccumulate` double NOT NULL DEFAULT '0',
  `LastHourTime` datetime DEFAULT NULL,
  `LastHourAccumulate` double NOT NULL DEFAULT '0',
  `LastDayTime` datetime DEFAULT NULL,
  `LastDayAccumulate` double NOT NULL DEFAULT '0',
  `LastWeekTime` datetime DEFAULT NULL,
  `LastWeekAccumulate` double NOT NULL DEFAULT '0',
  `LastMonthTime` datetime DEFAULT NULL,
  `LastMonthAccumulate` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`Equipment_ID`),
  CONSTRAINT `FK_wat_Flow_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_flowtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_flowtype` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `LLFlow` double DEFAULT NULL,
  `LFlow` double DEFAULT NULL,
  `HFlow` double DEFAULT NULL,
  `HHFlow` double DEFAULT NULL,
  `Des` text,
  `PressureLoss` double DEFAULT NULL,
  `MaxV` double DEFAULT NULL,
  `Manufacturer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wat_houraccumulate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_houraccumulate` (
  `HourTime` datetime NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `Accumulate` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`Equipment_ID`,`HourTime`),
  KEY `IX_wat_HourAccumulate` (`HourTime`),
  KEY `IX_wat_HourAccumulate_1` (`Equipment_ID`),
  CONSTRAINT `FK_wat_HourAccumulate_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wat_monthaccumulate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_monthaccumulate` (
  `MonthTime` date NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `LastAccumulate` double NOT NULL DEFAULT '0',
  `Accumulate` double NOT NULL DEFAULT '0',
  `LastMonthSameDateAccumulate` double NOT NULL DEFAULT '0',
  `LastYearAccumulate` double NOT NULL DEFAULT '0',
  `AvgFlow` double NOT NULL DEFAULT '0',
  `LastMonthAvgFlow` double NOT NULL DEFAULT '0',
  `DayMaxAccumulate` double NOT NULL DEFAULT '0',
  `DayMaxTime` date DEFAULT NULL,
  `DayMinAccumulate` double NOT NULL DEFAULT '0',
  `DayMinTime` date DEFAULT NULL,
  PRIMARY KEY (`Equipment_ID`,`MonthTime`),
  KEY `IX_wat_MonthAccumulate` (`MonthTime`),
  KEY `IX_wat_MonthAccumulate_1` (`Equipment_ID`),
  CONSTRAINT `FK_wat_MonthAccumulate_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `wat_weekaccumulate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wat_weekaccumulate` (
  `WeekTime` date NOT NULL,
  `Equipment_ID` varchar(50) NOT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `LastAccumulate` double NOT NULL DEFAULT '0',
  `Accumulate` double NOT NULL DEFAULT '0',
  `LastWeekSameDateAccumulate` double NOT NULL DEFAULT '0',
  `LastYearAccumulate` double NOT NULL DEFAULT '0',
  `AvgFlow` double NOT NULL DEFAULT '0',
  `DayMaxAccumulate` double NOT NULL DEFAULT '0',
  `DayMaxTime` datetime DEFAULT NULL,
  `DayMinAccumulate` double NOT NULL DEFAULT '0',
  `DayMinTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Equipment_ID`,`WeekTime`),
  KEY `IX_wat_WeekAccumulate` (`WeekTime`),
  KEY `IX_wat_WeekAccumulate_1` (`Equipment_ID`),
  CONSTRAINT `FK_wat_WeekAccumulate_equ_Equipment` FOREIGN KEY (`Equipment_ID`) REFERENCES `equ_equipment` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
