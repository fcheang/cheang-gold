use gold;

alter table appointment add modifyBy varchar(20);

alter table appointment add modificationDate datetime;

alter table referral add modifyBy varchar(20);

alter table referral add modificationDate datetime;

drop table if exists deletedReferral;

CREATE TABLE deletedReferral (
  deletedBy varchar(20),
  deleteDate datetime,      
  referralId integer, 
  firstName varchar(30),
  middleInitial varchar(5),
  lastName varchar(30),
  gender varchar(10),
  ssn varchar(9),
  birthDate date,
  isChild bool,
  streetAddress varchar(30),
  apartmentNumber varchar(30),
  city varchar(20),
  state varchar(2),
  zipCode varchar(20),
  phoneNumber varchar(10),
  email varchar(30),
  legalGardianFirstName varchar(30),
  legalGardianLastName varchar(30),
  legalGardianMiddleInitial varchar(5),
  legalGardianPhoneNumber varchar(10),
  previousPsychiatrist varchar(65),
  lastSeen date,
  currentMedications varchar(100),
  daysLeft integer,
  previousMedications varchar(100),
  previousDx varchar(100),
  presentingProblem varchar(200),
  needMedicalMgntSvc bool,
  needTherapy bool,
  isUrgent varchar(10),
  clinic varchar(30),
  comments varchar(200),
  userId varchar(20),
  insuranceCompany varchar(30),
  memberId varchar(30),
  copay decimal(8, 2),
  insPhoneNumber varchar(10),
  authorizationNumber varchar(30),
  numAuthorizedVisit integer
);

drop table if exists deletedAppointment;

CREATE TABLE deletedAppointment (
  deletedBy varchar(20),
  deleteDate datetime,
  apptId integer,
  referralId integer,
  clinicName varchar(20),
  appointmentDate datetime,
  endTime datetime,
  provider varchar(60),
  type varchar(20),
  translationSvcNeeded bool,
  collateralReceived bool,
  notes varchar(100),
  userId varchar(20)
);

alter table referral add reminder varchar(100);

insert into sequenceGenerator values ('logSheet', 0);

insert into version (schemaVersion, lastUpdated, description) values
(6, '2005-10-7', 'delete, modification auditing, reminder');
