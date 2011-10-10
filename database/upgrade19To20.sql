use gold;

ALTER TABLE `appointment` ADD COLUMN `isWalkIn` TINYINT DEFAULT 0 AFTER `modificationDate`;

ALTER TABLE `canceledappointment` ADD COLUMN `isWalkIn` TINYINT DEFAULT 0 AFTER `userId`;

ALTER TABLE `canceledappointment` ADD COLUMN `isWnTwentyFourHrs` TINYINT DEFAULT 0 AFTER `isWalkIn`;

ALTER TABLE `canceledappointment` ADD COLUMN `isCancelByPatient` TINYINT DEFAULT 0 AFTER `isWnTwentyFourHrs`;

ALTER TABLE `canceledappointment` ADD COLUMN `isCancelByClinic` TINYINT DEFAULT 0 AFTER `isCancelByPatient`;

ALTER TABLE `canceledappointment` ADD COLUMN `language` VARCHAR(20) AFTER `translationSvcNeeded`;

ALTER TABLE `deletedappointment` ADD COLUMN `isWalkIn` TINYINT DEFAULT 0 AFTER `userId`;

CREATE TABLE cancelByPatientReasonCode (
  id integer NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

insert into cancelByPatientReasonCode values (1, 'Patient has no transportation');

insert into cancelByPatientReasonCode values (2, 'Patient forgot about appointment');

insert into cancelByPatientReasonCode values (3, 'Patient reports that they are sick');

insert into cancelByPatientReasonCode values (4, 'Patient has no childcare coverage');

insert into cancelByPatientReasonCode values (5, 'Other');

CREATE TABLE cancelByClinicReasonCode (
  id integer not null,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = InnoDB;

insert into cancelByClinicReasonCode values (1, 'MD/NP called in sick');

insert into cancelByClinicReasonCode values (2, 'MD/NP had an emergency');

insert into cancelByClinicReasonCode values (3, 'MD/NP on vacation');

insert into cancelByClinicReasonCode values (4, 'MD/NP requested the change');

insert into cancelByClinicReasonCode values (5, 'Clinic behind schedule');

insert into cancelByClinicReasonCode values (6, 'Other');

ALTER TABLE `canceledappointment` ADD COLUMN `cancelReason` VARCHAR(100) AFTER `language`,
 ADD COLUMN `cancelOtherReason` VARCHAR(200) AFTER `cancelReason`;
 
delete from version where schemaVersion = 20;

insert into version (schemaVersion, lastUpdated, description) values (20, '2011-6-15', 'fields for cancel appointment');

commit;