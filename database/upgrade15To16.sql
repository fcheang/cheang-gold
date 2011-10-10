use gold;

DROP TABLE IF EXISTS `evaluation`;

CREATE TABLE `evaluation` (
  `referralId` INTEGER UNSIGNED NOT NULL,
  `year` INTEGER UNSIGNED NOT NULL,
  `checkoffDate` DATETIME NOT NULL,
  `checkoffBy` VARCHAR(20) NOT NULL,
  `notes` VARCHAR(200),
  PRIMARY KEY (`referralId`, `year`)
)
ENGINE = InnoDB;


delete from version where schemaVersion = 16;

insert into version (schemaVersion, lastUpdated, description) values (16, '2008-12-30', 'evaluation');

commit;