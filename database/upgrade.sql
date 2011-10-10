use gold

update referralStatus set status = 'Not Scheduled' where status = 'Waitlisted';

update referralStatus set status = 'Scheduled' where status = 'Active';

update referralStatus set status = 'Seen' where status = 'Completed';

alter table appointment change column physician provider varchar(60);

update referralStatus set removeDate = '2200-01-01' where removeDate = '2100-02-01'

commit;

