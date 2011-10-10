use gold;

alter table provider add column salutation varchar(10);

alter table provider add column firstName varchar(20);

alter table provider add column mi varchar(2);

alter table provider add column lastName varchar(20);

# serviceType=Medical Service | Therapy Service
alter table provider add column serviceType varchar(40);

alter table provider change providerId credential varchar(20);

alter table provider add column providerId integer unique auto_increment;

alter table appointment drop foreign key appt_FK2;

alter table appointment add column providerId integer;

update appointment a, provider p set a.providerId = p.providerId where a.provider = p.name;

alter table appointment drop column provider;

alter table appointment change providerId provider integer;

alter table appointment add constraint appt_FK2 foreign key (provider) references provider(providerId);

alter table provider change name displayName varchar(60);

insert into sequenceGenerator (tableName, nextSeq) values ('provider', 28);

alter table appointment add column isNew bool;

update provider set firstName = 'Colleen', lastName = 'Krinard', salutation = '' where displayName = 'Colleen Krinard';

update provider set firstName = 'David', lastName = 'Behar',
salutation = '' where displayName = 'David Behar';

update provider set firstName = 'Angela', lastName = 'Callender',
salutation = 'Dr.' where displayName = 'Dr. Angela Callender';

update provider set firstName = '', lastName = 'Barker',
salutation = 'Dr.' where displayName = 'Dr. Barker';

update provider set firstName = 'Chandra', lastName = 'Ravi',
salutation = 'Dr.' where displayName = 'Dr. Chandra Ravi';

update provider set firstName = '', lastName = 'Colbert',
salutation = 'Dr.' where displayName = 'Dr. Colbert';

update provider set firstName = 'Del', lastName = 'Castillo',
salutation = 'Dr.' where displayName = 'Dr. Del Castillo';

update provider set firstName = '', lastName = 'Deshmukh',
salutation = 'Dr.' where displayName = 'Dr. Deshmukh';

update provider set firstName = '', lastName = 'Gordon',
salutation = 'Dr.' where displayName = 'Dr. Gordon';

update provider set firstName = '', lastName = 'Harris',
salutation = 'Dr.' where displayName = 'Dr. Harris';

update provider set firstName = '', lastName = 'Jones',
salutation = 'Dr.' where displayName = 'Dr. Jones';

update provider set firstName = '', lastName = 'Manjunath',
salutation = 'Dr.' where displayName = 'Dr. Manjunath';

update provider set firstName = 'Marina', lastName = 'Obolnikov',
salutation = 'Dr.' where displayName = 'Dr. Marina Obolnikov';

update provider set firstName = '', lastName = 'Murphy',
salutation = 'Dr.' where displayName = 'Dr. Murphy';

update provider set firstName = '', lastName = 'Ndlela',
salutation = 'Dr.' where displayName = 'Dr. Ndlela';

update provider set firstName = '', lastName = 'Schlies',
salutation = 'Dr.' where displayName = 'Dr. Schlies';

update provider set firstName = '', lastName = 'Sue',
salutation = 'Dr.' where displayName = 'Dr. Sue';

update provider set firstName = '', lastName = 'Tanagho',
salutation = 'Dr.' where displayName = 'Dr. Tanagho';

update provider set firstName = '', lastName = 'Tucker',
salutation = 'Dr.' where displayName = 'Dr. Tucker';

update provider set firstName = '', lastName = 'Gill',
salutation = 'Dr.' where displayName = 'Dr.Gill';

update provider set firstName = 'Lisa', lastName = 'Spendlove',
salutation = '' where displayName = 'Lisa Spendlove';

update provider set firstName = 'Liz', lastName = 'Varon',
salutation = '' where displayName = 'Liz Varon';

update provider set firstName = 'Mary Ann', lastName = 'Blackwall',
credential = 'RN' where displayName = 'Mary Ann Blackwall RN';

update provider set firstName = 'Mary', lastName = 'Redoutey',
salutation = '' where displayName = 'Mary Redoutey';

update provider set firstName = 'Omar', lastName = 'Bacabo',
salutation = '' where displayName = 'Omar Bacabo';

update provider set firstName = 'Sally', lastName = 'Girgis',
salutation = '' where displayName = 'Sally Girgis';

update provider set firstName = 'Tony', lastName = 'Amjadi',
salutation = '' where displayName = 'Tony Amjadi';

insert into version (schemaVersion, lastUpdated, description) values
(?, '', 'add new cols to provider, add isNew to appt');

commit;