use gold;

drop table if exists holiday;

CREATE TABLE holiday (
  name varchar(100) not null,
  isActive bool,
  constraint holiday_PK primary key (name)
) type = MyISAM;

insert into holiday values ('New Years Day', 0);

insert into holiday values ('Martin Luther King Day', 0);

insert into holiday values ('Presidents Day', 0);

insert into holiday values ('Memorial Day', 0);

insert into holiday values ('Independence Day', 0);

insert into holiday values ('Labor Day', 0);

insert into holiday values ('Columbus Day', 0);

insert into holiday values ('Veterans Day', 0);

insert into holiday values ('Thanksgiving Day', 0);

insert into holiday values ('Christmas Day', 0);

delete from version where schemaVersion = 13;

insert into version (schemaVersion, lastUpdated, description) values
(13, '2007-9-18', 'standard holiday');

commit;