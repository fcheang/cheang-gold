use gold;

alter table insurance add column eligEffDate date;

alter table insurance add column eligTermDate date;

alter table insurance add column notes varchar(250);

alter table insurance change numAuthorizedVisit numAuthVisitForMD integer;

alter table insurance change numAuthorizedVisitParity numAuthVisitForMA integer;

alter table insurance change authorizationNumber authNumForMD varchar(30);

alter table insurance change authorizationNumberParity authNumForMA varchar(30);

insert into version (schemaVersion, lastUpdated, description) values
(11, '2007-8-31', 'add effDate, authNum parity, notes to appt');

commit;