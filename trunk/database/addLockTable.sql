use gold;

drop table if exists seqLock;

create table seqLock (lockName varchar(30));

insert into seqLock values ('appointment');

insert into seqLock values ('logsheet');

insert into seqLock values ('referral');

