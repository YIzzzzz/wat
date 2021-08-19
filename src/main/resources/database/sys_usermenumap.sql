CREATE TABLE `sys_usermenumap` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `RoleCode` varchar(100) DEFAULT NULL,
  `MenuCode` varchar(100) DEFAULT NULL,
  `UserCode` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MenuCode` (`MenuCode`),
  KEY `RoleCode` (`RoleCode`),
  KEY `FK_sys_usermenumap_sys_user` (`UserCode`),
  CONSTRAINT `FK__SysRoleFu__Funct__0D7A02861` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
  CONSTRAINT `FK__SysRoleFu__RoleC__0C85DE4D1` FOREIGN KEY (`RoleCode`) REFERENCES `sys_role` (`RoleCode`),
  CONSTRAINT `FK_sys_usermenumap_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB AUTO_INCREMENT=13693 DEFAULT CHARSET=utf8mb3