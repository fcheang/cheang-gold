use gold;

alter table appointment add column language varchar(20) default "" after translationSvcNeeded;

insert into version (schemaVersion, lastUpdated, description) values (15, '2008-3-14', 'add appt language column');

commit;