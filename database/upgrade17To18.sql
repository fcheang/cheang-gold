use gold;

alter table appointment add column isEligible varchar(3) not null default '' after evaluationYear;

alter table canceledAppointment add column isEligible varchar(3) not null default '' after evaluationYear;

alter table deletedAppointment add column isEligible varchar(3) not null default '' after evaluationYear;

alter table insurance MODIFY COLUMN medicalId VARCHAR(60);

insert into role (name, description) values ('EligibilityCheck', 'eligibility check');

insert into userRole (userId, roleName) values ('admin', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('cassandra', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('cheryl', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('Iesha', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('kendra', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('Longoria', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('savila', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('Geralyn', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('sherwin', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('Jennifer', 'EligibilityCheck');

insert into userRole (userId, roleName) values ('Administrator', 'EligibilityCheck');

delete from version where schemaVersion = 18;

insert into version (schemaVersion, lastUpdated, description) values (18, '2011-1-15', 'add isEligible column to appt');

commit;