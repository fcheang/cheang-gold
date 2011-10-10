alter table referralStatus add reasonCode varchar(50);

create table reasonCodeType (
  reasonCode varchar(50),
  constraint reasonCodeType_PK primary key (reasonCode)
);

insert into reasonCodeType (reasonCode) values ('New Evaluation');

insert into reasonCodeType (reasonCode) values ('Med Check');

insert into reasonCodeType (reasonCode) values ('Therapy');

insert into version (schemaVersion, lastUpdated, description) values
(4, '2005-07-28', 'added reasonCode');
