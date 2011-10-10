use gold;

# fix provider table

# rename providerId to title
alter table provider change providerId title varchar(30);

# add id
alter table provider add column providerId integer unique auto_increment;

alter table insuranceProvider add column insuranceProviderId integer unique auto_increment;


# use InnoDB
ALTER TABLE provider ENGINE = InnoDB;

alter table appointment engine=InnoDB;

alter table insuranceProvider engine=InnoDB;


# fix PK, FK, Index
alter table provider drop primary key;

ALTER TABLE provider DROP INDEX providerId,
 ADD PRIMARY KEY  USING BTREE(providerId)
, ROW_FORMAT = DYNAMIC;

alter table provider drop index name;

ALTER TABLE provider ADD UNIQUE INDEX Index_1(name)
, ROW_FORMAT = DYNAMIC;


alter table insuranceProvider drop primary key;

ALTER TABLE insuranceProvider
 ADD PRIMARY KEY  USING BTREE(insuranceProviderId)
, ROW_FORMAT = DYNAMIC;

ALTER TABLE insuranceProvider ADD UNIQUE INDEX Index_1(name)
, ROW_FORMAT = DYNAMIC;

ALTER TABLE appointment DROP INDEX apptId,
 ADD PRIMARY KEY  USING BTREE(apptId)
, ROW_FORMAT = DYNAMIC;

ALTER TABLE appointment ADD INDEX Index_1 USING HASH(provider)
, ROW_FORMAT = DYNAMIC;

ALTER TABLE appointment ADD INDEX Index_2 USING BTREE(referralId)
, ROW_FORMAT = DYNAMIC;


# add credential table
CREATE TABLE credential (
  providerId integer not null,
  insuranceProviderId integer not null
) engine = InnoDB;

ALTER TABLE credential ADD PRIMARY KEY (providerId, insuranceProviderId)
, ROW_FORMAT = DYNAMIC;

ALTER TABLE credential ADD CONSTRAINT FK_credential_1 FOREIGN KEY FK_credential_1 (providerId)
    REFERENCES provider (providerId)
    ON DELETE CASCADE
    ON UPDATE CASCADE
, ROW_FORMAT = DYNAMIC;

ALTER TABLE credential ADD CONSTRAINT FK_credential_2 FOREIGN KEY FK_credential_2 (insuranceProviderId)
    REFERENCES insuranceProvider (insuranceProviderId)
    ON DELETE CASCADE
    ON UPDATE CASCADE
, ROW_FORMAT = DYNAMIC;



# credential check role
insert into role (name, description) values ('Credential', 'Role that can modify doctor''s credential');

ALTER TABLE user ENGINE = InnoDB;

ALTER TABLE role ENGINE = InnoDB;


# support multiple roles per user
CREATE TABLE userRole (
  userId VARCHAR(20) NOT NULL,
  roleName VARCHAR(30) NOT NULL,
  PRIMARY KEY (userId, roleName),
  CONSTRAINT FK_userRole_1 FOREIGN KEY FK_userRole_1 (userId)
    REFERENCES user (userId)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FK_userRole_2 FOREIGN KEY FK_userRole_2 (roleName)
    REFERENCES role (name)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
ENGINE = InnoDB
CHARACTER SET utf8 COLLATE utf8_general_ci;

insert into userRole (userId, roleName) (select userId, roleName from user);


# inc notes size
ALTER TABLE appointment MODIFY COLUMN notes VARCHAR(200);

ALTER TABLE deletedAppointment MODIFY COLUMN notes VARCHAR(200);

ALTER TABLE canceledAppointment MODIFY COLUMN notes VARCHAR(200);

ALTER TABLE referral MODIFY COLUMN reminder VARCHAR(200);


# exception log
#CREATE TABLE exceptionLog (
#  createDate DATETIME NOT NULL,
#  exception VARCHAR(5000) NOT NULL
#)
#ENGINE = InnoDB;


# hide doctor and patient
ALTER TABLE referral ADD COLUMN discharged BOOLEAN NOT NULL DEFAULT 0 AFTER translationSvcNeeded;

ALTER TABLE referral ADD COLUMN dischargeDate DATETIME AFTER discharged;

ALTER TABLE provider ADD COLUMN inactive BOOLEAN NOT NULL DEFAULT 0 AFTER providerId;

ALTER TABLE provider ADD COLUMN inactiveDate DATETIME AFTER inactive;


# referral date
ALTER TABLE insurance ADD COLUMN referralDate DATE AFTER authNumForMA;


# update schema version
insert into version (schemaVersion, lastUpdated, description) values
(14, '2008-6-1', 'fix providerId; add provider credentials; multiple roles; inc notes size');

commit;