DROP TABLE IF EXISTS `version`;
DROP TABLE IF EXISTS `capability`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user`;
drop table if exists `userrole`;
DROP TABLE IF EXISTS `remoteuser`;
DROP TABLE IF EXISTS `sequencegenerator`;
DROP TABLE IF EXISTS `seqlock`;
DROP TABLE IF EXISTS `logsheet`;
DROP TABLE IF EXISTS `reasoncodetype`;
DROP TABLE IF EXISTS `appointment`;
DROP TABLE IF EXISTS `canceledappointment`;
DROP TABLE IF EXISTS `clinic`;
DROP TABLE IF EXISTS `credential`;
DROP TABLE IF EXISTS `deletedappointment`;
DROP TABLE IF EXISTS `deletedreferral`;
DROP TABLE IF EXISTS `holiday`;
DROP TABLE IF EXISTS `holidaymap`;
DROP TABLE IF EXISTS `trustedip`;
DROP TABLE IF EXISTS `timesheet`;
DROP TABLE IF EXISTS `insurance`;
DROP TABLE IF EXISTS `insuranceprovider`;
DROP TABLE IF EXISTS `provider`;
DROP TABLE IF EXISTS `referralstatus`;
DROP TABLE IF EXISTS `referral`;
DROP TABLE IF EXISTS `evaluation`;


CREATE TABLE  `appointment` (
  `referralId` int(11) DEFAULT NULL,
  `clinicName` varchar(20) DEFAULT NULL,
  `appointmentDate` datetime DEFAULT NULL,
  `provider` varchar(60) DEFAULT NULL,
  `translationSvcNeeded` tinyint(1) DEFAULT NULL,
  `language` varchar(20) default "",
  `collateralReceived` tinyint(1) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `isEvaluation` boolean default false,
  `evaluationYear` varchar(4),
  `apptId` int(11) NOT NULL DEFAULT '0',
  `modifyBy` varchar(20) DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`apptId`),
  KEY `Index_1` (`provider`),
  KEY `Index_2` (`referralId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE  `canceledappointment` (
  `cancelBy` varchar(20) DEFAULT NULL,
  `cancelDate` datetime DEFAULT NULL,
  `apptId` int(11) DEFAULT NULL,
  `referralId` int(11) DEFAULT NULL,
  `clinicName` varchar(20) DEFAULT NULL,
  `appointmentDate` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `provider` varchar(60) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `isEvaluation` boolean default false,
  `evaluationYear` varchar(4),  
  `translationSvcNeeded` tinyint(1) DEFAULT NULL,
  `collateralReceived` tinyint(1) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `capability` (
  `roleName` varchar(30) NOT NULL DEFAULT '',
  `permission` varchar(30) NOT NULL DEFAULT '',
  `object` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`roleName`,`permission`,`object`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `clinic` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `streetAddress` varchar(30) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `phoneNumber1` varchar(10) DEFAULT NULL,
  `phoneNumber2` varchar(10) DEFAULT NULL,
  `faxNumber` varchar(12) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `deletedappointment` (
  `deletedBy` varchar(20) DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `apptId` int(11) DEFAULT NULL,
  `referralId` int(11) DEFAULT NULL,
  `clinicName` varchar(20) DEFAULT NULL,
  `appointmentDate` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `provider` varchar(60) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `isEvaluation` boolean default false,
  `evaluationYear` varchar(4),  
  `translationSvcNeeded` tinyint(1) DEFAULT NULL,
  `collateralReceived` tinyint(1) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `deletedreferral` (
  `deletedBy` varchar(20) DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `referralId` int(11) DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `middleInitial` varchar(5) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `ssn` varchar(9) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `isChild` tinyint(1) DEFAULT NULL,
  `streetAddress` varchar(30) DEFAULT NULL,
  `apartmentNumber` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zipCode` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `legalGardianFirstName` varchar(30) DEFAULT NULL,
  `legalGardianLastName` varchar(30) DEFAULT NULL,
  `legalGardianMiddleInitial` varchar(5) DEFAULT NULL,
  `legalGardianPhoneNumber` varchar(10) DEFAULT NULL,
  `previousPsychiatrist` varchar(65) DEFAULT NULL,
  `lastSeen` date DEFAULT NULL,
  `currentMedications` varchar(100) DEFAULT NULL,
  `daysLeft` int(11) DEFAULT NULL,
  `previousMedications` varchar(100) DEFAULT NULL,
  `previousDx` varchar(100) DEFAULT NULL,
  `presentingProblem` varchar(200) DEFAULT NULL,
  `needMedicalMgntSvc` tinyint(1) DEFAULT NULL,
  `needTherapy` tinyint(1) DEFAULT NULL,
  `isUrgent` varchar(10) DEFAULT NULL,
  `clinic` varchar(30) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `insuranceCompany` varchar(30) DEFAULT NULL,
  `memberId` varchar(30) DEFAULT NULL,
  `copay` decimal(8,2) DEFAULT NULL,
  `insPhoneNumber` varchar(10) DEFAULT NULL,
  `authorizationNumber` varchar(30) DEFAULT NULL,
  `numAuthorizedVisit` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `holiday` (
  `name` varchar(100) NOT NULL DEFAULT '',
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `holidaymap` (
  `holidayMapId` int(11) NOT NULL AUTO_INCREMENT,
  `startDate` date NOT NULL DEFAULT '0000-00-00',
  `endDate` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`holidayMapId`),
  UNIQUE KEY `startDate` (`startDate`,`endDate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `insurance` (
  `referralId` int(11) NOT NULL DEFAULT '0',
  `insuranceCompany` varchar(30) NOT NULL DEFAULT '',
  `memberId` varchar(30) DEFAULT NULL,
  `copay` decimal(8,2) DEFAULT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `authNumForMD` varchar(30) DEFAULT NULL,
  `numAuthVisitForMD` int(11) DEFAULT NULL,
  `insuranceId` int(11) NOT NULL AUTO_INCREMENT,
  `copayParity` decimal(8,2) DEFAULT NULL,
  `numAuthVisitForMA` int(11) DEFAULT NULL,
  `medicalId` varchar(30) DEFAULT NULL,
  `medicalIssueDate` date DEFAULT NULL,
  `authNumForMA` varchar(30) DEFAULT NULL,
  `isLast` tinyint(1) DEFAULT '1',
  `eligEffDate` date DEFAULT NULL,
  `eligTermDate` date DEFAULT NULL,
  `notes` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`insuranceId`),
  KEY `ins_ak1` (`referralId`)
) ENGINE=MyISAM AUTO_INCREMENT=9341 DEFAULT CHARSET=utf8;


CREATE TABLE  `insuranceprovider` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `streetAddress` varchar(30) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `phoneNumber1` varchar(10) DEFAULT NULL,
  `phoneNumber2` varchar(10) DEFAULT NULL,
  `faxNumber` varchar(12) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `insuranceProviderId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`insuranceProviderId`),
  UNIQUE KEY `insuranceProviderId` (`insuranceProviderId`),
  UNIQUE KEY `Index_1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE  `logsheet` (
  `clientName` varchar(30) DEFAULT NULL,
  `initialCallDate` varchar(30) DEFAULT NULL,
  `letterMailDate` varchar(30) DEFAULT NULL,
  `firstCallDate` varchar(30) DEFAULT NULL,
  `secCallDate` varchar(30) DEFAULT NULL,
  `madeContact` tinyint(1) DEFAULT NULL,
  `numOfAttempt` varchar(5) DEFAULT NULL,
  `notes` varchar(50) DEFAULT NULL,
  `logId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`logId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `provider` (
  `name` varchar(60) NOT NULL DEFAULT '',
  `title` varchar(30) DEFAULT NULL,
  `providerId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`providerId`),
  UNIQUE KEY `Index_1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


CREATE TABLE  `reasoncodetype` (
  `reasonCode` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`reasonCode`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `referral` (
  `referralId` int(11) NOT NULL DEFAULT '0',
  `firstName` varchar(100) DEFAULT NULL,
  `middleInitial` varchar(5) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `ssn` varchar(100) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `streetAddress` varchar(30) DEFAULT NULL,
  `apartmentNumber` varchar(30) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `state` char(2) DEFAULT NULL,
  `zipCode` varchar(20) DEFAULT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `legalGardianFirstName` varchar(30) DEFAULT NULL,
  `legalGardianLastName` varchar(30) DEFAULT NULL,
  `legalGardianMiddleInitial` varchar(5) DEFAULT NULL,
  `legalGardianPhoneNumber` varchar(10) DEFAULT NULL,
  `previousPsychiatrist` varchar(65) DEFAULT NULL,
  `lastSeen` date DEFAULT NULL,
  `currentMedications` varchar(100) DEFAULT NULL,
  `daysLeft` int(11) DEFAULT NULL,
  `previousMedications` varchar(100) DEFAULT NULL,
  `previousDx` varchar(100) DEFAULT NULL,
  `presentingProblem` text,
  `needMedicalMgntSvc` tinyint(1) DEFAULT NULL,
  `needTherapy` tinyint(1) DEFAULT NULL,
  `isUrgent` varchar(10) DEFAULT NULL,
  `clinic` varchar(30) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `isChild` tinyint(1) DEFAULT NULL,
  `userId` varchar(20) DEFAULT NULL,
  `modifyBy` varchar(20) DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `reminder` varchar(100) DEFAULT NULL,
  `balance` decimal(8,2) DEFAULT '0.00',
  `balanceNotes` varchar(200),
  `translationSvcNeeded` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`referralId`),
  UNIQUE KEY `referral_AK1` (`lastName`,`firstName`,`birthDate`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `referralstatus` (
  `referralId` int(11) NOT NULL DEFAULT '0',
  `status` varchar(20) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `removeDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `notes` varchar(200) DEFAULT NULL,
  `apptId` int(11) DEFAULT NULL,
  `reasonCode` varchar(50) DEFAULT NULL,
  KEY `RS_IDX_1` (`apptId`),
  KEY `RS_IDX_2` (`referralId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `remoteuser` (
  `userId` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `role` (
  `name` varchar(30) NOT NULL DEFAULT '',
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  `seqlock` (
  `lockName` varchar(30) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `sequencegenerator` (
  `tableName` varchar(30) NOT NULL DEFAULT '',
  `nextSeq` int(11) DEFAULT NULL,
  PRIMARY KEY (`tableName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `timesheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `clinic` varchar(30) DEFAULT NULL,
  `staff` varchar(60) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `totalHours` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`,`clinic`,`staff`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5404 DEFAULT CHARSET=utf8;


CREATE TABLE  `trustedip` (
  `host` varchar(50) DEFAULT NULL,
  UNIQUE KEY `host` (`host`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


CREATE TABLE  `user` (
  `userId` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `roleName` varchar(30) DEFAULT 'Receptionist',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `userrole` (
  `userId` varchar(20) NOT NULL,
  `roleName` varchar(30) NOT NULL,
  PRIMARY KEY  (`userId`,`roleName`),
  KEY `FK_userRole_2` (`roleName`),
  CONSTRAINT `FK_userRole_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_userRole_2` FOREIGN KEY (`roleName`) REFERENCES `role` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `credential` (
  `providerId` int(11) NOT NULL,
  `insuranceProviderId` int(11) NOT NULL,
  PRIMARY KEY (`providerId`,`insuranceProviderId`),
  KEY `FK_credential_2` (`insuranceProviderId`),
  CONSTRAINT `FK_credential_2` FOREIGN KEY (`insuranceProviderId`) REFERENCES `insuranceprovider` (`insuranceProviderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_credential_1` FOREIGN KEY (`providerId`) REFERENCES `provider` (`providerId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

CREATE TABLE `evaluation` (
  `referralId` INTEGER UNSIGNED NOT NULL,
  `year` INTEGER UNSIGNED NOT NULL,
  `evalDate` DATETIME NOT NULL,
  `evalBy` VARCHAR(20) NOT NULL,
  `comment` VARCHAR(200),  
  PRIMARY KEY (`referralId`, `year`)
)
ENGINE = InnoDB;

CREATE TABLE  `version` (
  `schemaVersion` int(11) DEFAULT NULL,
  `lastUpdated` date DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

