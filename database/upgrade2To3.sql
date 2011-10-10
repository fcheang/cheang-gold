use gold;

alter table appointment add endTime datetime;

alter table appointment add type varchar(20);

alter table appointment drop column checkinTime;

alter table appointment drop column isCheckedIn;

update appointment set type = 'Appointment';

update appointment set endTime = date_add(appointmentDate, interval 30 minute);

alter table appointment drop primary key;

alter table appointment drop foreign key appt_FK1;

alter table appointment add column apptId integer unique auto_increment;

alter table appointment modify apptId integer not null;

insert into sequenceGenerator (tableName, nextSeq) values ('appointment', 900);

insert into sequenceGenerator (tableName, nextSeq) values ('logSheet', 0);

alter table referralStatus add apptId integer;

update referralStatus rs, appointment a set rs.apptId = a.apptId where rs.referralId = a.referralId and rs.status = 'Scheduled';

alter table referralStatus drop primary key;

create table logSheet (logId integer, clientName varchar(30), initialCallDate varchar(30), letterMailDate varchar(30), firstCallDate varchar(30), secCallDate varchar(30), madeContact bool, numOfAttempt varchar(5), notes varchar(50), constraint logSheet_PK primary key (logId));

insert into version (schemaVersion, lastUpdated, description) values
(3, '2005-04-28', 'appointment scheduler, log sheet');

update referralStatus rs, appointment a set rs.apptId = a.apptId where rs.referralId = a.referralId and (rs.status = 'Not Seen' or rs.status = 'Seen');

commit;
