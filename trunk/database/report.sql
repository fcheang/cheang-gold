-- num of "alameda county" children's referrals from 7/1/2011 to now
-- 1632
select count(1)
from referral r, referralStatus rs
where r.isChild = 1
and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
and r.referralId = rs.referralId
and rs.createDate between '2011-07-01' and now()
and rs.status = 'Not Scheduled'
and r.referralId != -1

-- num of "alameda county" children's referrals who is also flagged as CFS (children)
-- from 7/1/2011 to now
-- 477
select count(1)
from referral r, referralStatus rs
where r.isChild = 1
and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
and r.referralId = rs.referralId
and rs.createDate between '2011-07-01' and now()
and rs.status = 'Not Scheduled';
and r.referralId != -1
and r.comments like '%CFS%'


-- num of "alameda county" children's appointments given from 7/1/2011 to 4/18/2012
-- 6507
select count(distinct a.apptId)
from referral r, appointment a, referralStatus rs
where r.isChild = 1
and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
and r.referralId = a.referralId
and a.appointmentDate between '2011-07-01' and now()
and r.referralId = rs.referralId
and a.referralId = rs.referralId
and a.apptId = rs.apptId
and a.referralId != -1
and rs.status = 'Scheduled'


-- num of "alameda county" children's no-shows from 7/1/2011 to 4/18/2012
-- 1632
select count(distinct a.apptId)
from referral r, appointment a, referralStatus rs
where r.isChild = 1
and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
and r.referralId = a.referralId
and a.appointmentDate between '2011-07-01' and now()
and r.referralId = rs.referralId
and a.referralId = rs.referralId
and a.apptId = rs.apptId
and a.referralId != -1
and rs.status = 'Not Seen'


-- average num days between referral to appointment date given for children
-- in Alameda County from 7/1/2011 to now
-- 18.8214
select AVG(numDays) from (
select DATEDIFF(min(rs2.createDate), rs1.createDate) numDays
from referral r, referralStatus rs1, referralStatus rs2
where rs1.status = 'Not Scheduled'
  and rs2.status = 'Scheduled'
  and rs1.referralId = rs2.referralId
  and rs1.referralId = r.referralId
  and rs2.createDate >= rs1.createDate
  and rs1.createDate between '2011-07-01' and now()
  and r.referralId != -1
  and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
  and r.isChild = 1
group by rs1.referralId, rs1.createDate
) rs


-- average num days between referral to appointment date given for All
-- in Alameda County from 7/1/2011 to now
-- 18.3452
select AVG(numDays) from (
select DATEDIFF(min(rs2.createDate), rs1.createDate) numDays
from referral r, referralStatus rs1, referralStatus rs2
where rs1.status = 'Not Scheduled'
  and rs2.status = 'Scheduled'
  and rs1.referralId = rs2.referralId
  and rs1.referralId = r.referralId
  and rs2.createDate >= rs1.createDate
  and rs1.createDate between '2011-07-01' and now()
  and r.referralId != -1
  and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
group by rs1.referralId, rs1.createDate
) rs

-- how many clients needed “interpreters” from 7/1/2011 to now
select count(1)
from referral r, referralStatus rs
where r.isChild = 0
and r.clinic in ('Oakland', 'Union City', 'Pleasanton')
and r.referralId = rs.referralId
and rs.createDate between '2011-07-01' and now()
and rs.status = 'Not Scheduled'
and r.referralId != -1
and r.translationSvcNeeded = 1

-- num walk in patient in the last 6 months that Dr. Reminajes saw
-- 125
select count(*)
from appointment where isWalkIn = 1
and appointmentDate between date_sub(now(), interval 6 month) and now()
and provider = 'Dr. Reminajes';

-- Zip Code Info for Contra Costa County & Antioch Clinics
select zipCode, count(*)
from referral r, referralStatus rs
where r.clinic in ('Contra Costa', 'Antioch')
and r.referralId = rs.referralId
and rs.createDate between date_sub(now(), interval 6 month) and now()
and rs.status = 'Not Scheduled'
and r.referralId != -1
group by zipCode
order by count(*) desc;

-- # of Appointments Scheduled for this 6 month period for Adult
select count(*) from appointment a, referral r
where a.clinicName = 'Contra Costa'
and a.appointmentDate between '2011-12-1' and '2012-5-30'
and a.referralId >= 0
and a.referralId = r.referralId
and r.isChild = 0;

-- # of new patients admitted for this 6 month period for Adult
select count(r.referralId) from referral r, referralStatus rs
where r.referralId = rs.referralId
and rs.status = 'Not Scheduled'
and rs.createDate between '2011-12-1' and '2012-5-30'
and rs.createDate < rs.removeDate
and r.clinic = 'Contra Costa'
and r.isChild = 0;

-- # of no show during this 6 month period for Adult
select count(r.referralId)
from referral r, referralStatus rs, appointment a
where r.referralId = rs.referralId
and rs.status = 'Not Seen'
and a.apptId = rs.apptId
and r.referralId = rs.referralId
and a.appointmentDate between '2012-1-1' and '2012-5-30'
and a.clinicName = 'Contra Costa'
and r.isChild = 0

