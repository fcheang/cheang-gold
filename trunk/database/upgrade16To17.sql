use gold;

alter table appointment add column isEvaluation boolean default false after type;

alter table appointment add column evaluationYear varchar(4) after isEvaluation;

alter table deletedAppointment add column isEvaluation boolean default false after type;

alter table deletedAppointment add column evaluationYear varchar(4) after isEvaluation;

alter table canceledAppointment add column isEvaluation boolean default false after type;

alter table canceledAppointment add column evaluationYear varchar(4) after isEvaluation;

alter table referral add column balanceNotes varchar(200) after balance;

delete from version where schemaVersion = 17;

insert into version (schemaVersion, lastUpdated, description) values (17, '2008-2-2', 'evaluation - take2');

commit;