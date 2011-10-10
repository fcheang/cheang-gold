use gold;

alter table user modify password varchar(100);

alter table referral modify firstName varchar(100);

alter table referral modify ssn varchar(100);

delete from version where schemaVersion = 10;

create index RS_IDX_1 on referralStatus (apptId);

create index RS_IDX_2 on referralStatus (referralId);

insert into version (schemaVersion, lastUpdated, description) values
(10, '2007-5-16', 'encrypt sensitive data');

commit;