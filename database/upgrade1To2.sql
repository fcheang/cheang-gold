use gold;

# used in checkinSystem

alter table appointment add isCheckedIn bool default 0;

alter table appointment add checkinTime datetime;


# normalize provider

drop table if exists provider;

create table provider (
  name varchar(60) unique,
  providerId varchar(30),
  constraint provider_PK primary key (name)
);


# cleanup provider in appointment table

update appointment set provider = 'Dr. Jones' where provider like '%jones';

update appointment set provider = 'Dr. Colbert' where provider like '%Colbett';

update appointment set provider = 'Dr. Colbert' where provider like '%colbert';

update appointment set provider = 'Dr. Manujanth' where provider like '%Manjunath';

update appointment set provider = 'Dr. Manujanth' where provider like '%Manuanth';

update appointment set provider = 'Dr. Manujanth' where provider like '%Maujanth';

update appointment set provider = 'Unknown' where provider is null;

insert into provider (name) select distinct provider from appointment;

alter table appointment add constraint appt_FK3 foreign key (provider) references provider (name);


# remove obsolete tables

drop table if exists acl;

drop table if exists savedReport;

drop table if exists savedQuery;


# Role based security

drop table if exists role;

create table role (
  name varchar(30),
  description varchar(100),
  constraint role_PK primary key (name)
);

insert into role (name, description) values ('Manager', 'Role that can access audit report');

insert into role (name, description) values ('Administrator', 'Role that can create new user');

insert into role (name, description) values ('Receptionist', 'User that performs the day to day operation in a clinic');

drop table if exists capability;

create table capability (
  roleName varchar(30),
  permission varchar(30),
  object varchar(30),
  constraint capability_PK primary key (roleName, permission, object)
);

insert into capability (roleName, permission, object) values ('Manager', 'read', 'audit report');

insert into capability (roleName, permission, object) values ('Administrator', 'create', 'user');

insert into capability (roleName, permission, object) values ('Administrator', 'read', 'audit report');

alter table user add roleName varchar(30) default 'Receptionist';

alter table user add constraint user_FK foreign key (roleName) references role(name);

update user set roleName = 'Administrator' where userId = 'Administrator';

insert into user (userId, password, roleName) values ('manager', 'manager', 'Manager');

# auditing

alter table referral add userId varchar(20);

update referral set userId = 'Unknown';

alter table appointment add userId varchar(20);

update appointment set userId = 'Unknown';

# schema version

delete from version where schemaVersion = 2;

insert into version (schemaVersion, lastUpdated, description) values
(2, '2005-02-10', 'role based security, appt checkIn, normailze provider');

commit;
