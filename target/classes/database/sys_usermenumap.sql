CREATE TABLE `sys_usermenumap` (
                                   `ID` int NOT NULL AUTO_INCREMENT,
                                   `MenuCode` varchar(100) DEFAULT NULL,
                                   `UserCode` varchar(100) NOT NULL,
                                   PRIMARY KEY (`ID`),
                                   KEY `MenuCode` (`MenuCode`),
                                   KEY `FK_sys_usermenumap_sys_user` (`UserCode`),
                                   CONSTRAINT `FK__SysRoleFu__Funct__0D7A02861` FOREIGN KEY (`MenuCode`) REFERENCES `sys_menu` (`MenuCode`),
                                   CONSTRAINT `FK_sys_usermenumap_sys_user` FOREIGN KEY (`UserCode`) REFERENCES `sys_user` (`UserCode`)
) ENGINE=InnoDB AUTO_INCREMENT=13693 DEFAULT CHARSET=utf8mb3