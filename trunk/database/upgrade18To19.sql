use gold;

#drop table Employee;
#drop table Worksheet;

CREATE TABLE `Employee` (
  `empId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `middleName` VARCHAR(45),
  `title` VARCHAR(45),
  `isActive` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (`empId`),
  UNIQUE INDEX `AK_Employee_1`(`firstName`, `lastName`)
)
ENGINE = InnoDB;

CREATE TABLE `Worksheet` (
  `empId` INTEGER UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `clinic` VARCHAR(30) NOT NULL,
  `hrs_worked` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `county_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo_seen` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `county_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `county_other_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `ccc_other_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `hmo_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `other_face_min` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_schedule` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_noshow` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_cancel` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_new` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `num_dropin` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`empId`, `date`, `clinic`),
  CONSTRAINT `FK_Worksheet_1` FOREIGN KEY `FK_Worksheet_1` (`empId`)
    REFERENCES `employee` (`empId`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
ENGINE = InnoDB;


delete from version where schemaVersion = 19;

insert into version (schemaVersion, lastUpdated, description) values (19, '2011-4-1', 'Daily Checkoff sheet');

commit;