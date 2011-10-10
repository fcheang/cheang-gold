package com.suntek.common.persistence;

public class QueryString {

    // select query

    public static String checkLogin =
        "SELECT (1) FROM user WHERE userId = ? AND password = ?";

    public static String getCapability =
    	"select u.userId, c.object, c.permission "+
    	"from user u, capability c, userRole ur "+
    	"where ur.roleName = c.roleName and ur.userId = u.userId "+
    	"and u.userId = ?";    	

    public static String checkPermission =
        "SELECT 1 FROM acl WHERE userId = ? and module = ?";

    public static String isTrustedHost =
        "select 1 from trustedIP where host = ? or host = ?";

    public static String isRemoteUser =
        "select 1 from remoteUser where userId = ?";

    public static String getInsuranceProviderName =
        "select name from insuranceProvider order by name asc";

    public static String getAllInsuranceProvider =
        "select name, streetAddress, city, state, zipCode, phoneNumber1, phoneNumber2, faxNumber, notes " +
        "from insuranceProvider";

    public static String getAllCredentials =
        "select insuranceProviderId, name from insuranceProvider order by name asc";

    public static String getClinicName =
        "select name from clinic";

    public static String getNextSeq =
        "select nextSeq from SequenceGenerator where tableName = ?";

    public static String getAllClinic =
        "select name, streetAddress, city, state, zipCode, phoneNumber1, phoneNumber2, faxNumber, notes " +
        "from clinic";

    public static String getPatientLog =
        "select logId, clientName, initialCallDate, letterMailDate, firstCallDate, " +
        "secCallDate, madeContact, numOfAttempt, notes " +
        "from logSheet where logId >= ? and logId <= ? order by logId desc";

    public static String getTimesheetByDateAndType =
        "select id, date, clinic, staff, type, totalHours "+
        "from timesheet where date = ? and type = ?"; 
    
    public static String getAllProvider =
        "select providerId, name, title from provider order by name";
    
    public static String getProviderById =
    	"select providerId, name, title from provider where providerId = ?";
    
    public static String getCredentialForProvider =
    	"SELECT i.insuranceProviderId, i.name FROM credential c, insuranceProvider i "+
    	"where c.insuranceProviderId = i.insuranceProviderId "+
    	"and c.providerId = ? order by i.name asc";

    public static String getProviderIdByName =
    	"SELECT providerId from provider where name = ?";
    
    public static String getAllUser =
        "select userId, password, firstName, lastName, description from user order by userId";

    public static String getUserRole =
        "select roleName from userRole where userId = ?";
    
    public static String getAllRemoteUser =
        "select userId from remoteUser order by userId";

    public static String getAllTrustedHost =
        "select host from trustedIP order by host";

    public static String getUser =
        "select userId, password, firstName, lastName, description from user where userId = ?";

    public static String getAllNotScheduledReferral =
        "select r.referralId, rs.createDate, r.firstName, r.middleInitial, r.lastName, " +
        "r.clinic, r.isUrgent, r.needMedicalMgntSvc, r.needTherapy " +
        "from referral r, referralStatus rs " +
        "where r.referralId = rs.referralId and rs.createDate <= now() and rs.removeDate > now() " +
        "and rs.status = ? order by r.clinic, rs.createDate";

    public static String getAllWaitlistedReferral =
        "select r.referralId, rs.createDate, r.firstName, r.middleInitial, r.lastName, " +
        "r.clinic, r.isUrgent, r.needMedicalMgntSvc, r.needTherapy " +
        "from referral r, referralStatus rs " +
        "where r.referralId = rs.referralId and rs.createDate <= now() and rs.removeDate > now() " +
        "and rs.status = ? order by r.clinic, rs.createDate";

    // In the case of multiple insurance, only retrieve the last entered insurance of the referral
    public static String getReferral =
        "select r.referralId, rs.status, rs.createDate, r.firstName, r.middleInitial, " +
        "r.lastName, r.gender, r.ssn, r.birthDate, r.streetAddress, " +
        "r.apartmentNumber, r.city, r.state, r.zipCode, r.phoneNumber, " +
        "r.email, r.legalGardianFirstName, r.legalGardianMiddleInitial, r.legalGardianLastName, r.legalGardianPhoneNumber, " +
        "i.insuranceId, i.referralDate, i.eligEffDate, i.eligTermDate, i.insuranceCompany, i.memberId, i.copay, i.copayParity, i.phoneNumber, i.authNumForMD, i.authNumForMA, " +
        "i.numAuthVisitForMD, i.numAuthVisitForMA, i.notes, r.previousPsychiatrist, r.lastSeen, r.currentMedications, r.daysLeft, " +
        "r.previousMedications, r.previousDx, r.presentingProblem, r.needMedicalMgntSvc, r.needTherapy, " +
        "r.isUrgent, r.clinic, r.comments, i.medicalId, i.medicalIssueDate, r.translationSvcNeeded " +
        "from referral r, referralStatus rs, insurance i " +
        "where r.referralId = rs.referralId and r.referralId = i.referralId and i.isLast = 1 and r.referralId = ? " +
        "and rs.createDate <= now() and rs.removeDate > now() "+
        "order by rs.removeDate desc limit 1";

    public static String getReferralDate =
        "select createDate from referralStatus where status = 'Not Scheduled' and referralId = ?";

    public static String getRefHistory =
        "select createDate, removeDate, status, notes from referralStatus where referralId = ? order by createDate asc";

    public static String countRefByIns =
        "select insuranceCompany, count(*) from insurance i, referralStatus rs " +
        "where i.referralId = rs.referralId and i.isLast = 1 and rs.status = 'Not Scheduled' and " +
        "rs.createDate >= ? and rs.createDate < ? group by insuranceCompany";

    public static String countChildRef =
        "select count(*) from referral r, referralStatus rs where " +
        "r.referralId = rs.referralId and rs.createDate >= ? and rs.createDate < ? " +
        "and to_days(now()) - to_days(r.birthdate) < 6570 and rs.status = 'Not Scheduled'";

    public static String countAdultRef =
        "select count(*) from referral r, referralStatus rs where " +
        "r.referralId = rs.referralId and rs.createDate >= ? and rs.createDate < ? " +
        "and to_days(now()) - to_days(r.birthdate) >= 6570 and rs.status = 'Not Scheduled'";

    public static String countNeedMedicalSvcRef =
        "select count(*) from referral r, referralStatus rs where r.referralId = rs.referralId and " +
        "r.needMedicalMgntSvc = 1 " +
        "and rs.status='Not Scheduled' and rs.createDate >= ? and rs.createDate < ? ";

    public static String countNeedTherapyRef =
        "select count(*) from referral r, referralStatus rs where r.referralId = rs.referralId and " +
        "r.needTherapy = 1 " +
        "and rs.status='Not Scheduled' and rs.createDate >= ? and rs.createDate < ? ";

    public static String countRefByStatus =
        "select status, count(*) from referralStatus where createDate >= ? and createDate < ? group by status";

    public static String avgWaitDay =
        "select avg(to_days(rs2.createDate) - to_days(rs1.createDate)) " +
        "from referralStatus rs1, referralStatus rs2 " +
        "where rs1.referralId = rs2.referralId " +
        "and rs1.status = 'Not Scheduled' " +
        "and rs2.status = 'Scheduled' " +
        "and rs1.isActive = 1 " +
        "and rs2.isActive = 1 " +
        "and rs1.createDate >= ? and rs1.createDate < ? " +
        "and rs2.createDate >= ? and rs2.createDate < ? ";

    public static String getNewRefCountOrderByInsurance =
        "select " +
        "  i.insuranceCompany, r.isChild, r.isUrgent, r.referralId " +
        "from referral r, referralStatus rs, insurance i " +
        "where " +
        "  r.referralId = rs.referralId and " +
        "  r.referralId = i.referralId and " +
        "  i.isLast = 1 and "+
        "  r.clinic = ? and " +
        "  rs.createDate >= ? and " +
        "  rs.createDate < ? and " +
        "  rs.status = 'Not Scheduled' " +
        "order by i.insuranceCompany ";

    public static String getReferralCountByUserId =
    	"select userId, count(*) from referral r, referralStatus rs " +
    	"where r.referralId = rs.referralId and rs.createDate >= ? " +
    	"and rs.createDate < ? and rs.status = 'Not Scheduled' " +
    	"and rs.createDate < rs.removeDate "+
    	"group by userId";

    public static String getApptCountByUserId =
    	"select userId, count(*) from appointment a, referralStatus rs "+
    	"where a.apptId = rs.apptId and rs.createDate >= ? "+
    	"and rs.createDate < ? and rs.status = 'Scheduled' "+
    	"group by userId";

    public static String getAllReceptionistNames =
        "select userId from user order by userId";

    public static String getScheduledRef =
        "select " +
        "  i.insuranceCompany, r.referralId, rs.createDate " +
        "from referral r, referralStatus rs, insurance i " +
        "where " +
        "  r.referralId = rs.referralId and " +
        "  r.referralId = i.referralId and " +
        "  i.isLast = 1 and " +
        "  r.clinic = ? and " +
        "  rs.createDate >= ? and " +
        "  rs.createDate < ? and " +
        "  rs.status = 'Scheduled' " +
        "order by i.insuranceCompany, rs.createDate";

    public static String getWaitlistDate =
        "select createDate from referralStatus where referralId = ? and status = 'Waitlist' " +
        "and createDate < ? ";

    public static String getApptById =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, a.translationSvcNeeded, " +
        "a.collateralReceived, a.notes, rs.status, rs.notes, rs.reasonCode " +
        "from appointment a, referralStatus rs " +
        "where a.referralId = ? " +
        "and a.apptId = rs.apptId " +
        "and rs.removeDate = '2200-01-01' " +
        "order by rs.createDate";

    public static String getApptDayViewByProviderByClinic =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 "+
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.clinicName = ? " +
        "and a.provider = ? " +
        "order by a.appointmentDate, rs.createDate desc";

    public static String getApptDayViewByClinic =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.clinicName = ? " +
        "order by a.appointmentDate, a.clinicName, rs.createDate desc ";

    public static String getApptDayViewByProvider =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.provider = ? " +
        "order by a.appointmentDate, a.provider, rs.createDate desc ";

    public static String getApptDayView =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "order by a.appointmentDate, a.clinicName, a.provider, rs.createDate desc ";

    // by insurance
    
    public static String getApptDayViewByProviderByClinicByIns =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.clinicName = ? " +
        "and a.provider = ? ";
        //and (i.insuranceCompany = ? or ...)
        //order by a.appointmentDate, rs.createDate desc 

    public static String getApptDayViewByClinicByIns =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.clinicName = ? ";
        //and (i.insuranceCompany = ? or ...)         
        //"order by a.appointmentDate, a.clinicName, rs.createDate desc ";

    public static String getApptDayViewByProviderByIns =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "and a.provider = ? ";
        // and (i.insuranceCompany = ? or ...)
        //order by a.appointmentDate, a.provider, rs.createDate desc

    public static String getApptDayViewByIns =
        "select a.referralId, a.clinicName, a.appointmentDate, a.provider, " +
        "rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany, " +
        "i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId, " +
        "r.ssn, r.birthDate, "+
        "a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes, r.legalGardianPhoneNumber, "+
        "rs.status, rs.apptId, i.medicalId, i.medicalIssueDate, i.eligEffDate, i.eligTermDate, i.copayParity "+        
        "from appointment a, referralStatus rs, referral r, insurance i " +
        "where a.apptId = rs.apptId " +
        "and a.referralId = r.referralId " +
        "and a.apptId = rs.apptId " +
        "and r.referralId = i.referralId " +
        "and i.isLast = 1 " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? ";
        // and (i.insuranceCompany = ? or ...)        
        //"order by a.appointmentDate, a.clinicName, a.provider, rs.createDate desc ";
    
    public static String isNewPatient =
        "select 1 from referralStatus " +
        "where referralId = ? " +
        "and status = 'Scheduled' " +
        "and createDate < ? ";

    public static String getLastLogId =
        "select max(logId) from logSheet";

    // update query

    public static String updateSeq =
        "update SequenceGenerator set nextSeq = nextSeq + 1 where tableName = ?";

    public static String endDateReferralStatus =
        "update referralStatus set removeDate = ? where referralId = ? and removeDate = ?";

    // insert query

    public static String insertReferral =
        "insert into referral (" +
        "referralId, firstName, middleInitial, lastName, gender, " +
        "ssn, birthDate, streetAddress, apartmentNumber, city, " +
        "state, zipCode, phoneNumber, email, legalGardianFirstName, " +
        "legalGardianMiddleInitial, legalGardianLastName, legalGardianPhoneNumber, previousPsychiatrist, lastSeen, " +
        "currentMedications, daysLeft, previousMedications, previousDx, presentingProblem, " +
        "needMedicalMgntSvc, needTherapy, isUrgent, clinic, comments, translationSvcNeeded, isChild, userId ) " +
        "values (" +
        "?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, " +
        "?, ?, ?, ?, ?, ?, ?, ?)";

    public static String insertInsurance =
        "insert into insurance (referralId, referralDate, eligEffDate, eligTermDate, insuranceCompany, memberId, copay, copayParity, phoneNumber, " +
        "authNumForMD, authNumForMA, numAuthVisitForMD, numAuthVisitForMA, medicalId, medicalIssueDate, notes, isLast) " +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String insertReferralStatus =
        "insert into referralStatus (referralId, status, isActive, createDate, removeDate, notes) " +
        "values (?, ?, ?, ?, ?, ?)";

    public static String insertUser =
        "insert into user (userId, password, firstName, lastName, description) values (?, ?, ?, ?, ?)";

    public static String insertUserRole =
        "insert into userRole (userId, roleName) values (?, ?)";
    
    public static String insertInsuranceProvider =
        "insert into insuranceProvider (name, streetAddress, state, city, zipCode, phoneNumber1, " +
        "phoneNumber2, faxNumber, notes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String insertClinic =
        "insert into clinic (name, streetAddress, state, city, zipCode, phoneNumber1, phoneNumber2, " +
        "faxNumber, notes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String insertLog =
        "insert into logSheet (logId, clientName, initialCallDate, letterMailDate, firstCallDate, " +
        "secCallDate, madeContact, numOfAttempt, notes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String insertTimesheet =
        "insert into timeSheet (date, clinic, staff, type, totalHours) "+
        "values (?, ?, ?, ?, ?)";
    
    public static String insertProvider =
        "insert into provider (name, title) values (?, ?)";

    public static String insertTrustedIP =
        "insert into trustedIP (host) values (?)";

    public static String insertRemoteUser =
        "insert into remoteUser (userId) values (?)";

    public static String insertAppt =
        "insert into appointment (referralId, clinicName, appointmentDate, provider, translationSvcNeeded, " +
        "collateralReceived, notes, userId) values (?, ?, ?, ?, ?, ?, ?, ?)";

    // DELETE QUERY

    public static String deleteRef =
        "delete from referral where referralId = ?";

    public static String deleteRefIns =
        "delete from insurance where referralId = ?";

    public static String deleteRefStatus =
        "delete from referralStatus where referralId = ?";

    public static String deleteClinic =
        "delete from clinic where name = ?";

    public static String deleteProvider =
        "delete from provider where name = ?";

    public static String deleteRemoteUser =
        "delete from remoteUser where userId = ?";

    public static String deleteTrustedIP =
        "delete from trustedIP where host = ?";

    public static String deleteUser =
        "delete from user where userId = ?";

    public static String deleteInsProvider =
        "delete from insuranceProvider where name = ?";

    public static String deleteTimesheet =
        "delete from timesheet where id = ?";
    
    // CheckIn System

    public static String getPendingApptForToday =
        "select a.referralId, a.appointmentDate, r.firstName, r.lastName, a.provider " +
        "from appointment a, referral r " +
        "where a.referralId = r.referralId " +
        "and a.clinicName = ? " +
        "and a.appointmentDate >= ? and a.appointmentDate < ? " +
        "and ( isCheckedIn = 0 or isCheckedIn is null ) " +
        "order by a.appointmentDate";

    public static String verifyPatient =
        "select 1 from referral where referralId = ? and ssn like ?";

    public static String updateIsCheckedIn =
        "update appointment set isCheckedIn = 1 where referralId = ?";

    public static String updateCheckInTime =
        "update appointment set checkinTime = now() where referralId = ?";

    public static String getAllApptForToday =
        "select a.referralId, a.appointmentDate, r.firstName, r.lastName, a.provider, a.isCheckedIn, a.checkInTime " +
        "from appointment a, referral r " +
        "where a.referralId = r.referralId " +
        "and a.clinicName = ? " +
        "and a.appointmentDate >= ? and a.appointmentDate < ? " +
        "order by a.appointmentDate";

    public static String updateUser =
        "update user set password = ?, firstName = ?, lastName = ?, description = ? " +
        "where userId = ?";

    public static String deleteUserRole =
        "delete from userRole where userId = ?";
    
    public static String updateProviderName =
    	"update provider set name = ? where providerId = ?";
    
    public static String updateProviderTitle =
    	"update provider set title = ? where providerId = ?";
    
    public static String insertCredential =
    	"insert into credential (providerId, insuranceProviderId) values (?, ?)";    

    public static String deleteCredential =
    	"delete from credential where providerId = ? and insuranceProviderId = ?";    
    
    public static String getNoShowsSummary =
        "select a.clinicName, count(*) " +
        "from referralStatus rs, appointment a " +
        "where rs.status = 'Not Seen' " +
        "and rs.referralId = a.referralId " +
        "and rs.apptId = a.apptId " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "group by a.clinicName " +
        "order by a.clinicName";

    public static String getCanceledApptSummary =
        "select clinicName, count(*) " +
        "from canceledAppointment " +
        "where " +
        "appointmentDate >= ? " +
        "and appointmentDate < ? " +
        "group by clinicName " +
        "order by clinicName";

    public static String getNoShowsDetail =
        "select r.lastname, r.firstname, a.appointmentDate, a.clinicName, a.provider " +
        "from referralStatus rs, appointment a, referral r " +
        "where rs.status = 'Not Seen' " +
        "and rs.referralId = a.referralId " +
        "and r.referralId = a.referralId " +
        "and rs.apptId = a.apptId " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "order by appointmentDate";

    public static String getCanceledApptDetail =
        "select r.lastname, r.firstname, a.appointmentDate, a.clinicName, a.provider " +
        "from canceledAppointment a, referral r " +
        "where " +
        "r.referralId = a.referralId " +
        "and a.appointmentDate >= ? " +
        "and a.appointmentDate < ? " +
        "order by appointmentDate";

    public static String getNumAppt =
        "select count(*) from appointment where referralId = ?";
    
    public static String getReferralForRefCountReport =
    	"select r.referralId, r.birthDate, rs.createDate "+ 
    	"from referral r, referralStatus rs, insurance ins "+
    	"where r.referralId = rs.referralId "+
    	"and r.referralId = ins.referralId "+
    	"and ins.isLast = 1 "+
    	"and rs.createDate >= ? "+
    	"and rs.createDate < ? "+
    	"and rs.status = 'Not Scheduled' "+
    	"and ins.insuranceCompany = ? "+
    	"order by rs.createDate";

    public static String hasNoShowAfter =
    	"select 1 from referralStatus where referralId = ? "+
    	"and status = 'Not Seen' "+
    	"and createDate > ?";

    public static String getTimesheetForDate =
    	"select staff, clinic, totalHours from timesheet where date = ? and type = ?";
    
    public static String getNumApptsByDateByStatus =
    	"select provider, clinicName, count(*) "+
    	"from appointment a, referralstatus s "+
    	"where a.apptId = s.apptId "+
    	"and a.appointmentDate between ? and ? "+
    	"and status = ? "+
    	"group by provider, clinicName ";
    
    public static String getHoursScheduledByDate =
    	"select provider, clinicName, "+
    	"(time_to_sec(endTime) - time_to_sec(appointmentDate)) / 3600 as hoursScheduled "+
    	"from appointment a, referralstatus s "+
    	"where a.apptId = s.apptId "+
    	"and a.appointmentDate between ? and ? "+
    	"and status = 'Scheduled' "+
    	"order by provider, clinicName";    	
    
    public static String getAllRoleNames =
        "select name from role";
    
    public static String getApptCountForProvider =
    	"select count(*) from appointment where provider = ? and referralId != -1";
    
    public static String getNumInsuranceForProvider =
    	"select count(*) from insurance where insuranceCompany = ?";
    
    public static String getNumReferralForClinic =
    	"select count(*) from referral where clinic = ?";
    
    public static String getNumApptForClinic =
    	"select count(*) from appointment where clinicName = ?";        
    
}
