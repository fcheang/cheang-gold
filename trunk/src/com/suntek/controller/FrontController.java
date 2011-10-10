package com.suntek.controller;

import com.suntek.checkinsystem.model.*;
import com.suntek.common.persistence.*;
import com.suntek.intakesystem.model.*;
import com.suntek.intakesystem.model.admin.AddRemoteUserAction;
import com.suntek.intakesystem.model.admin.AddTrustedIPAction;
import com.suntek.intakesystem.model.admin.DeleteRemoteUserAction;
import com.suntek.intakesystem.model.admin.DeleteTrustedIPAction;
import com.suntek.intakesystem.model.admin.DeleteUserAction;
import com.suntek.intakesystem.model.admin.ManageRemoteUserAction;
import com.suntek.intakesystem.model.admin.ManageTrustedIPAction;
import com.suntek.intakesystem.model.admin.NewUserAction;
import com.suntek.intakesystem.model.admin.ShowCreateUserAction;
import com.suntek.intakesystem.model.admin.ShowUpdateUserAction;
import com.suntek.intakesystem.model.admin.UpdateUserAction;
import com.suntek.intakesystem.model.admin.ViewAllUserAction;
import com.suntek.intakesystem.model.productivity.*;
import com.suntek.intakesystem.model.report.AuditReportAction;
import com.suntek.intakesystem.model.report.AvgWaitTime;
import com.suntek.intakesystem.model.report.CountRefByChildAdult;
import com.suntek.intakesystem.model.report.CountRefByInsurance;
import com.suntek.intakesystem.model.report.CountRefByService;
import com.suntek.intakesystem.model.report.CountRefByStatus;
import com.suntek.intakesystem.model.report.GenRefCountReportAction;
import com.suntek.intakesystem.model.report.ReportAction;
import com.suntek.intakesystem.model.report.SelectReportAction;
import com.suntek.intakesystem.model.report.ShowRefCountReportAction;
import com.suntek.intakesystem.model.report.ViewApptReportAction;
import com.suntek.intakesystem.model.report.WeeklyReportAction;
import com.suntek.intakesystem.model.tracking.*;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Controller in the MVC. This class will intercepts all the HTTP request
 * and redirects it to the Action Handler.
 */
public class FrontController extends HttpServlet {

    private HashMap actionMapping = null;
    private ServletContext ctx = null;
    private Model m = null;

    public void init(ServletConfig config){
        ctx = config.getServletContext();
        Model model = new Model(ctx.getInitParameter("dbDriver"),
                                ctx.getInitParameter("dbHost"),
                                ctx.getInitParameter("dbUsed"),
                                ctx.getInitParameter("dbUser"),
                                ctx.getInitParameter("dbPassword"),
                                ctx.getInitParameter("poolSize"));

        String isDebugOn = ctx.getInitParameter("debugOn");
        if (isDebugOn != null && isDebugOn.equalsIgnoreCase("true")){
            Model.debugOn = true;
        }
        m = model;

        // init Action Mapping
        actionMapping = new HashMap();

        Action action = null;

        action = new LoginAction(model);
        actionMapping.put(action.getName(), action);

        action = new LogoutAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowCreateUserAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new NewUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewAllUserAction(model);
        actionMapping.put(action.getName(), action);

        // Intake System

        action = new EnterReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewInsuranceProviderAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewClinicAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewAllClinicAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewAllInsuranceAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewAllWaitlistedReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new ScheduleApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeclineReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowupForApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewDeclineAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewCompleteAction(model);
        actionMapping.put(action.getName(), action);

        action = new FindReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new DisplayFindReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new ModifyReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new UpdateReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteClinicAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteInsProviderAction(model);
        actionMapping.put(action.getName(), action);

        action = new ConfirmAndScheduleApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new NoShowsForApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new NewNoShowsAction(model);
        actionMapping.put(action.getName(), action);

        action = new ModifyApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new UpdateApptAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowReferralHistoryAction(model);
        actionMapping.put(action.getName(), action);

        action = new SaveAndPutOnWaitlistAction(model);
        actionMapping.put(action.getName(), action);

        action = new WaitlistRefAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowUpdateUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new UpdateUserAction(model);
        actionMapping.put(action.getName(), action);                

        action = new ShowNewProviderPageAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new NewProviderAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewAllProviderAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteProviderAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewProviderDetailAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new SaveProviderAction(model);
        actionMapping.put(action.getName(), action);

        // Report Action

        action = new ReportAction(model);
        actionMapping.put(action.getName(), action);

        action = new WeeklyReportAction(model);
        actionMapping.put(action.getName(), action);

        action = new CountRefByInsurance(model);
        actionMapping.put(action.getName(), action);

        action = new CountRefByChildAdult(model);
        actionMapping.put(action.getName(), action);

        action = new CountRefByService(model);
        actionMapping.put(action.getName(), action);

        action = new AvgWaitTime(model);
        actionMapping.put(action.getName(), action);

        action = new CountRefByStatus(model);
        actionMapping.put(action.getName(), action);

        action = new ShowRefCountReportAction(model);
        actionMapping.put(action.getName(), action);

        action = new GenRefCountReportAction(model);
        actionMapping.put(action.getName(), action);

        // Tracking
        action = new EnterNewLogAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewLogsAction(model);
        actionMapping.put(action.getName(), action);
        

        // Staff Productivity
        
        action = new ShowEnterHoursWorkedAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new EnterHoursWorkedAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowViewHoursWorkedAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new ViewHoursWorkedAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new DeleteTimesheetAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new ShowEnterHoursBilledAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new EnterHoursBilledAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowViewHoursBilledAction(model);
        actionMapping.put(action.getName(), action);
        
        action = new ViewHoursBilledAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowBasicReportAction(model);
        actionMapping.put(action.getName(), action);        
        
        action = new ViewBasicReportAction(model);
        actionMapping.put(action.getName(), action);        

        
        // appointment day/week view
        action = new EnterApptReportAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewApptReportAction(model);
        actionMapping.put(action.getName(), action);

        
        // CheckIn System
        action = new ShowPatientCheckInAction(model);
        actionMapping.put(action.getName(), action);

        action = new SelectClinicAction(model);
        actionMapping.put(action.getName(), action);

        action = new PatientCheckInAction(model);
        actionMapping.put(action.getName(), action);

        action = new PatientVerificationAction(model);
        actionMapping.put(action.getName(), action);

        action = new ShowPatientCheckInForApptDateAction(model);
        actionMapping.put(action.getName(), action);

        action = new SelectClinicAndApptDateAction(model);
        actionMapping.put(action.getName(), action);

        action = new ViewReadOnlyReferralAction(model);
        actionMapping.put(action.getName(), action);

        action = new SelectReportAction(model);
        actionMapping.put(action.getName(), action);

        action = new AuditReportAction(model);
        actionMapping.put(action.getName(), action);

        // user admin

        action = new ManageRemoteUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new AddRemoteUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteRemoteUserAction(model);
        actionMapping.put(action.getName(), action);

        action = new ManageTrustedIPAction(model);
        actionMapping.put(action.getName(), action);

        action = new AddTrustedIPAction(model);
        actionMapping.put(action.getName(), action);

        action = new DeleteTrustedIPAction(model);
        actionMapping.put(action.getName(), action);
    }

    public void destroy(){
        actionMapping.clear();
        actionMapping = null;
        m.destroy();
        m = null;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse respond){
	    doPost(req, respond);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse respond){
        try{
            debug("calling doPost");
            //print_actionMappings();
            String op = getOperation(req.getRequestURL().toString());
            //debug("actionMapping.containsKey(" + op + ") : " + actionMapping.containsKey(op));
            //debug("(Action)actionMapping.get(" + op + ")");
            Action action = (Action)actionMapping.get(op);
            if ( actionRequiredLogin(action) && (!alreadyLoggedIn(req, respond)) ){
                //ctx.getRequestDispatcher(Action.loginPage).forward(req, respond);
                respond.sendRedirect(respond.encodeRedirectURL(Action.loginPage));
            }else{
                //debug("returned action: " + action);

                String result = action.perform(req);
                debug("returned result: " + result);
                if (result != null){
                    //debug("Forward to "+result);
                    //ctx.getRequestDispatcher(result).forward(req, respond);
                    debug("Redirect to "+result);
                    respond.sendRedirect(respond.encodeRedirectURL(result));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private boolean actionRequiredLogin(Action action){
        if ( action instanceof LoginAction ){
            return false;
        }else{
            return true;
        }
    }

    private boolean alreadyLoggedIn(HttpServletRequest req, HttpServletResponse respond){
        HttpSession session = req.getSession(true);
        if (session != null && session.getAttribute(Action.USER_ID) != null){
            return true;
        }else{
            session.setAttribute("loginMessage", "<font color=\"red\"><b>User logged out!</font>");
            return false;
        }
    }

    private String getOperation(String reqURL){
        //debug("ReqURL= "+reqURL);
        int lastSlash = reqURL.lastIndexOf("/");
        String opName = reqURL.substring(lastSlash+1);
        //debug("operation name= "+opName);
        return opName;
    }

    private void debug(String msg){
        if (Model.debugOn){
            System.out.println("[FrontController]: " + msg);
        }
    }

    private void print_actionMappings()
    {
	Set entrySet = actionMapping.entrySet();
	Iterator iter = entrySet.iterator();
	while(iter.hasNext()) {
	    System.out.println(iter.next());
	}
    }
}
