use gold;

ALTER TABLE `appointment` ADD COLUMN `authNum` VARCHAR(20) AFTER `isWalkIn`;

ALTER TABLE `appointment` ADD COLUMN `countyNum` VARCHAR(20) AFTER `authNum`;

ALTER TABLE `canceledappointment` ADD COLUMN `authNum` VARCHAR(20) AFTER `isWalkIn`;

ALTER TABLE `canceledappointment` ADD COLUMN `countyNum` VARCHAR(20) AFTER `authNum`;

ALTER TABLE `deletedappointment` ADD COLUMN `authNum` VARCHAR(20) AFTER `isWalkIn`;

ALTER TABLE `deletedappointment` ADD COLUMN `countyNum` VARCHAR(20) AFTER `authNum`;
 
delete from version where schemaVersion = 21;

insert into version (schemaVersion, lastUpdated, description) values (21, '2011-10-23', 'add authNum and countyNum for walk-in');

commit;