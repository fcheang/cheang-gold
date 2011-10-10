CREATE TABLE version (
  schemaVersion integer,
  lastUpdated date,
  description varchar(100)
);

alter table referral add isChild bool;

update referral set isChild = 0;

update referral set isChild = 1 where to_days(now()) - to_days(birthdate) < 6570;

insert into version (schemaVersion, lastUpdated, description) values
(1, '2005-01-16', 'added version table, added isChild column');

commit;