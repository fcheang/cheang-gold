use gold;

drop table if exists holidaymap;

CREATE TABLE holidaymap (
  holidayMapId integer not null auto_increment,
  startDate date not null,
  endDate date,
  description varchar(100),
  constraint holidaymap_PK primary key (holidayMapId),
  constraint holidaymap_AK unique (startDate, endDate)
) type = MyISAM;

insert into version (schemaVersion, lastUpdated, description) values
(12, '2007-9-8', 'holiday map');

commit;