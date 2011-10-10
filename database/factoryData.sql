# Factory Data

insert into seqLock values ('appointment');

insert into seqLock values ('logsheet');

insert into seqLock values ('referral');

insert into role (name, description) values ('Manager', 'Role that can access audit report');

insert into role (name, description) values ('Administrator', 'Role that can create new user');

insert into role (name, description) values ('Receptionist', 'User that performs the day to day operation in a clinic');

insert into role (name, description) values ('Billing', 'Role that can add and remove outstanding balance');

insert into role (name, description) values ('Credential', 'Role that can modify doctor''s credential');

insert into capability (roleName, permission, object) values ('Manager', 'read', 'audit report');

insert into capability (roleName, permission, object) values ('Administrator', 'create', 'user');

insert into capability (roleName, permission, object) values ('Administrator', 'read', 'audit report');

insert into capability (roleName, permission, object) values ('Administrator', 'manage', 'remote user');

insert into capability (roleName, permission, object) values ('Administrator', 'manage', 'trusted ip');

insert into sequenceGenerator (tableName, nextSeq) values ('referral', 0);

insert into sequenceGenerator (tableName, nextSeq) values ('appointment', 0);

insert into sequenceGenerator (tableName, nextSeq) values ('logSheet', 0);

# Administrator/Administrator
insert into user (userId, password, roleName) values ('Administrator', 'vlzdCQ519B0epX0bvCUUZHV3hlYhesLAerlXbPJPGIU=', 'Administrator');

insert into userRole (userId, roleName) values ('Administrator', 'Administrator');

insert into userRole (userId, roleName) values ('Administrator', 'Credential');

insert into reasonCodeType (reasonCode) values ('New Evaluation');

insert into reasonCodeType (reasonCode) values ('Med Check');

insert into reasonCodeType (reasonCode) values ('Therapy');

insert into remoteUser values ('Administrator');

insert into trustedIP values ('127.0.0.1');

# standard holiday
insert into holiday values ('New Years Day', 0);

insert into holiday values ('Martin Luther King Day', 0);

insert into holiday values ('Presidents Day', 0);

insert into holiday values ('Memorial Day', 0);

insert into holiday values ('Independence Day', 0);

insert into holiday values ('Labor Day', 0);

insert into holiday values ('Columbus Day', 0);

insert into holiday values ('Veterans Day', 0);

insert into holiday values ('Thanksgiving Day', 0);

insert into holiday values ('Christmas Day', 0);


# version
insert into version (schemaVersion, lastUpdated, description) values
(1, '2005-01-16', 'added version table, added isChild column');

insert into version (schemaVersion, lastUpdated, description) values
(2, '2005-02-10', 'role based security, appt checkIn, normailze provider');

insert into version (schemaVersion, lastUpdated, description) values
(3, '2005-04-28', 'appointment scheduler, log sheet');

insert into version (schemaVersion, lastUpdated, description) values
(4, '2005-07-28', 'added reasonCode');

insert into version (schemaVersion, lastUpdated, description) values
(5, '2005-8-14', 'ip checking');

insert into version (schemaVersion, lastUpdated, description) values
(6, '2005-10-7', 'delete, modification auditing, reminder');

insert into version (schemaVersion, lastUpdated, description) values
(7, '2006-1-12', 'add new cols to provider, add isNew to appt');

insert into version (schemaVersion, lastUpdated, description) values
(8, '2007-1-6', 'added timesheet table');

insert into version (schemaVersion, lastUpdated, description) values
(9, '2007-2-17', 'added balance column to referral; added Billing role');

insert into version (schemaVersion, lastUpdated, description) values
(10, '2007-5-16', 'encrypt sensitive data');

insert into version (schemaVersion, lastUpdated, description) values
(11, '2007-8-31', 'add effDate, authNum parity, notes to appt');

insert into version (schemaVersion, lastUpdated, description) values
(12, '2007-9-8', 'holiday map');

insert into version (schemaVersion, lastUpdated, description) values
(13, '2007-9-18', 'standard holiday');

insert into version (schemaVersion, lastUpdated, description) values
(14, '2008-6-1', 'fix providerId; add provider credentials');

insert into version (schemaVersion, lastUpdated, description) values
(15, '2008-3-14', 'add appt language column');

insert into version (schemaVersion, lastUpdated, description) values 
(16, '2008-12-30', 'evaluation');

insert into version (schemaVersion, lastUpdated, description) values 
(17, '2008-2-2', 'evaluation - take2');

commit;