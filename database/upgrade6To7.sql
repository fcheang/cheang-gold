use gold;

drop table if exists timesheet;

create table timesheet (
  id int not null auto_increment,
  date datetime,
  clinic varchar(30),
  staff varchar(60),
  type varchar(30),
  totalHours decimal(6,2),
  constraint timesheet_PK primary key (id),
  constraint timesheet_AK unique (date, clinic, staff, type)
) engine = InnoDB;

insert into version (schemaVersion, lastUpdated, description)
values (7, '2007-1-6', 'added timesheet table');