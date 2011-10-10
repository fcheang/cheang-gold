use gold;
drop table if exists canceledAppointment;

CREATE TABLE canceledAppointment (
  cancelBy varchar(20),
  cancelDate datetime,
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
