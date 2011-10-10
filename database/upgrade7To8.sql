use gold;

#drop table if exists deletedProvider;

#create table deletedProvider (
#  deletedBy varchar(20),
#  deleteDate datetime,
#  name varchar(60),
#  providerId varchar(60)
#) type = InnoDB;

#alter table insurance drop primary key;

#alter table insurance add column insuranceId integer not null auto_increment primary key;

#alter table insurance add column validStartDate datetime default '1900-1-1';

#alter table insurance add column validEndDate datetime default '2200-1-1';

#alter table insurance add column copayParity decimal(8, 2);

#alter table insurance add column numAuthorizedVisitParity integer;

alter table referral add column balance decimal(8,2) default 0;

insert into role (name, description) values ('Billing', 'Role that can add and remove outstanding balance');

insert into version (schemaVersion, lastUpdated, description) values
(8, '2007-2-17', 'added balance column to referral; added Billing role');