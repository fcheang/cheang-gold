use gold;

drop table if exists trustedIP;

drop table if exists remoteUser;

create table trustedIP (
  host varchar(50) unique
);

create table remoteUser (
  userId varchar(20)
);

delete from capability where roleName = 'Administrator' and permission = 'manage' and object = 'remote user';

delete from capability where roleName = 'Administrator' and permission = 'manage' and object = 'trusted ip';

insert into capability (roleName, permission, object) values ('Administrator', 'manage', 'remote user');

insert into capability (roleName, permission, object) values ('Administrator', 'manage', 'trusted ip');

delete from remoteUser where userId = 'Administrator';

insert into remoteUser values ('Administrator');

delete from trustedIP where host = '127.0.0.1';

insert into trustedIP values ('127.0.0.1');

alter table referral drop index referral_AK1;

create unique index referral_AK1 on referral (lastName, firstName, birthDate);

delete from version where schemaVersion = 5;

insert into version (schemaVersion, lastUpdated, description) values
(5, '2005-8-14', 'ip checking, fix dup patient, fix printing');


commit;