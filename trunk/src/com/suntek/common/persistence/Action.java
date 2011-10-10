package com.suntek.common.persistence;

import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;


/**
 * Represents and Form action initiated by the JSP page
 */
public abstract class Action {

    public final static String NOT_SCHEDULED = "Not Scheduled";
    public final static String WAITLISTED = "Waitlist";
    public final static String SCHEDULED = "Scheduled";
    public final static String SEEN = "Seen";
    public final static String DECLINED = "Declined";
    public final static String NOT_SEEN = "Not Seen";

    protected Model model;

    // Admin
    public final String loginMainPage = "login.jsp";
    public final static String loginPage = "../login.jsp";
    public final String userAdminPage = "intakesystem/admin_index.jsp";
    public final String userInfoPage = "user_info.jsp";
    public final String mainMenuPage = "menu.jsp";

    // IntakeSystem
    public final String intake_indexPage = "intakesystem/index.jsp";
    public final String newReferralPage = "new_referral.jsp";
    public final String searchResultPage = "search_result.jsp";
    public final String reportResultPage = "report_result.jsp";
    public final String errorPage = "fail.jsp";
    public final String successPage = "success.jsp";
    public final String clinicInfoPage = "clinic_info.jsp";
    public final String providerInfoPage = "provider_info.jsp";
    public final String insuranceInfoPage = "insurance_info.jsp";
    public final String referralSummaryPage = "referral_summary.jsp";
    public final String referralDetailPage = "referral_detail.jsp";
    public final String newApptPage = "new_appt.jsp";
    public final String declineRefPage = "decline_referral.jsp";
    public final String completeRefPage = "complete_referral.jsp";
    public final String noshowsApptPage = "noshows.jsp";
    public final String findRefPage = "find_referral.jsp";
    public final String viewReferralPage = "view_referral.jsp";
    public final String editReferralPage = "edit_referral.jsp";
    public final String editApptPage = "edit_appt.jsp";
    public final String refHistoryPage = "referral_history.jsp";
    public final String waitlistRefPage = "waitlist_referral.jsp";
    public final String savedReportPage = "saved_report.jsp";
    public final String editUserPage = "edit_user.jsp";
    public final String logSummaryPage = "log_summary.jsp";
    public final String refCountReportInputPage = "ref_report_input.jsp";
    public final String viewProviderDetailPage = "view_provider_detail.jsp";

    // CheckIn System
    public final String selectClinicPage = "checkinsystem/select_clinic.jsp";
    public final String selectClinicAndApptDatePage = "select_clinic_date.jsp";
    public final String patientCheckInPage = "patient_checkin.jsp";
    public final String patientAuthenticationPage = "patient_authentication.jsp";
    public final String checkInSuccessPage = "checkin_success.jsp";
    public final String patientCheckInAdminPage = "patient_checkin_admin.jsp";
    public final String csaReferralDetailPage = "referral_detail.jsp";

    // attributes stored in the Session
    public final static String USER_ID = "userid";
    public final static String SESSION_USER = "sessionUser";
    public final static String REF = "ref";
    public final static String OLD_REF = "oldRef";
    public final static String REF_ID = "refId";
    public final static String CLINIC = "clinic";
    public final static String USER = "user";
    public final static String INS_PROVIDER = "insProvider";
    public final static String APPT = "appt";
    public final static String APPOINTMENT = "appointment";
    public final static String CAPABILITY = "capability";
    public final static String PROVIDER_ID = "providerId";

    public final static String edit_user_userId = "edit_user_userId";
    public final static String edit_user_password = "edit_user_password";
    public final static String edit_user_firstName = "edit_user_firstName";
    public final static String edit_user_lastName = "edit_user_lastName";
    public final static String edit_user_description = "edit_user_description";
    public final static String edit_user_roleList = "edit_user_roleList";

    // session variable for CheckIn system
    public final static String csClinicList = "csClinicList";
    public final static String csClinic = "csClinic";
    public final static String csRefreshRate = "csRefreshRate";
    public final static String csPatientCheckInTable = "csPatientCheckInTable";
    public final static String csRefId = "csRefId";
    public final static String csPatientName = "csPatientName";
    public final static String csProvider = "csProvider";
    public final static String csMyApptDate = "csMyApptDate";
    public final static String csMyApptTime = "csMyApptTime";
    public final static String csInvalidSSN = "csInvalidSSN";
    public final static String csMessage = "csMessage";
    public final static String csError = "csError";
    public final static String csApptDate = "csApptDate";

    // session variable for CheckIn System Admin
    public final static String csaClinicList = "csaClinicList";
    public final static String csaClinic = "csaClinic";
    public final static String csaApptDate = "csaApptDate";
    public final static String csaRefreshRate = "csaRefreshRate";
    public final static String csaPatientCheckInTable = "csaPatientCheckInTable";
    public final static String csaMonth = "csaMonth";
    public final static String csaDate = "csaDate";
    public final static String csaYear = "csaYear";

    // Module
    public final static String INTAKE_SYSTEM = "IntakeSystem";
    public final static String CHECKIN_SYSTEM = "CheckInSystem";

    // Permission
    public final static String REFERRAL_OBJ = "referral";
    public final static String AUDIT_REPORT_OBJ = "audit report";
    public final static String APPOINTMENT_OBJ = "appointment";
    public final static String PROVIDER_OBJ = "provider";

    public final static String READ = "read";
    public final static String CREATE = "create";
    public final static String UPDATE = "update";
    public final static String DELETE = "delete";

    // Role
    public final static String CREDENTIAL = "Credential";
    
    
    // Table
    public final static String logSheet = "logSheet";

    // Constants
    public final static String WORKED ="worked";
    public final static String BILLED = "billed";
    
    public Action(Model model){
	this.model = model;
    }

    public String getName(){
    	return this.getClass().getSimpleName()+".do";
    }

    public abstract String perform(HttpServletRequest req);

    public boolean hasPermission(HttpSession session, String obj, String perm){
        HashMap map = (HashMap)session.getAttribute(this.CAPABILITY);
        if (map != null){
            String val = (String)map.get(obj);
            if (val != null && val.equals(perm)){
                return true;
            }
        }
        return false;
    }

    public void debug(String msg){
        if (Model.debugOn){
            System.out.println("[Action]: " + msg);
        }
    }

    public String getCurrentTime(){
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        return "Report generated on: "+df.format(new Date());
    }

    protected String showRemoteUser(HttpSession session){
        boolean success = true;
        String errMsg = "";
        String message = "";
        String retPage = "";

        try{
            List users = model.getAllRemoteUser();
            if (users.size() == 0){
                message = "<b>No remote user found!</b>";
            }else{
                message = "<h3>"+users.size()+" remote users found:</h3><p>";
                message = message +
                    "<table border=1><tr>"+
                    "<th width=7%><strong>Action</strong></th>"+
                    "<th width=10%><strong>User Id</strong></th>"+
                    "</tr>";
                for (int i=0; i<users.size(); i++){
                    String u = (String)users.get(i);
                    message = message +
                        "<tr>"+
                        "<td width=7%><a href=\"DeleteRemoteUserAction.do?remoteUser="+u+"\">Delete</td>"+
                        "<td width=10%>"+u+"</td>"+
                        "</tr>";
                }
                message += "</table>";
            }
            retPage = "remote_user.jsp";
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem getting remote user!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

    protected String showTrustedIP(HttpSession session){
        boolean success = true;
        String errMsg = "";
        String message = "";
        String retPage = "";

        try{
            List hosts = model.getAllTrustedHost();
            if (hosts.size() == 0){
                message = "<b>No trusted IP found!</b>";
            }else{
                message = "<h3>"+hosts.size()+" trusted IP found:</h3><p>";
                message = message +
                    "<table border=1><tr>"+
                    "<th width=7%><strong>Action</strong></th>"+
                    "<th width=10%><strong>Trusted IP</strong></th>"+
                    "</tr>";
                for (int i=0; i<hosts.size(); i++){
                    String u = (String)hosts.get(i);
                    message = message +
                        "<tr>"+
                        "<td width=7%><a href=\"DeleteTrustedIPAction.do?trustedIP="+u+"\">Delete</td>"+
                        "<td width=10%>"+u+"</td>"+
                        "</tr>";
                }
                message += "</table>";
            }
            retPage = "trusted_ip.jsp";
        }catch(Throwable e){
            success = false;
            errMsg = e.getLocalizedMessage();
            retPage = errorPage;
        }
        if (success){
            session.setAttribute("message", message);
        }else{
            session.setAttribute("message", "<font color=\"red\"><b>Problem getting trusted IP!</b></font>");
            session.setAttribute("error", errMsg);
        }
        return retPage;
    }

}
