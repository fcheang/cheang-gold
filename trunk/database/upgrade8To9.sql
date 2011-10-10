use gold;

alter table insurance drop primary key;

alter table insurance add column insuranceId integer not null auto_increment primary key;

alter table insurance add column copayParity decimal(8, 2);

alter table insurance add column numAuthorizedVisitParity integer;

alter table insurance add column medicalId varchar(30);

alter table insurance add column medicalIssueDate date;

alter table insurance add column isLast bool default true;

alter table referral add column translationSvcNeeded bool default false;

alter table referral modify column presentingProblem varchar(400);

#alter table insurance add column eligEffDate date;

#alter table insurance add column eligTermDate date;

alter table insurance add column authorizationNumberParity varchar(30);

#alter table insurance add column notes varchar(200);

create index ins_ak1 on insurance (referralId);

insert into version (schemaVersion, lastUpdated, description) values
(9, '2007-2-21', 'added insuranceId, copayParity, numAuthoVisitParity to insurance table; nts to referral');