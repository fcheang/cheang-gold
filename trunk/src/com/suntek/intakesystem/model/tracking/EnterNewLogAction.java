package com.suntek.intakesystem.model.tracking;

import com.suntek.common.persistence.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import com.suntek.common.persistence.Model;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class EnterNewLogAction
    extends Action {

  public EnterNewLogAction(Model m) {
      super(m);
  }

  public String perform(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      String retPage = null;
      String message = "";
      String errMsg = "";

      boolean success = true;
      retPage = super.successPage;

      try{
          String clientName = req.getParameter("clientName");
          String initialCallDate = req.getParameter("initialCallDate");
          String letterMailDate = req.getParameter("letterMailDate");
          String firstCallDate = req.getParameter("firstCallDate");
          String secCallDate = req.getParameter("secCallDate");
          String contactClient = req.getParameter("contactClient");
          boolean contactClientBool = false;
          if (contactClient != null && contactClient.equalsIgnoreCase("yes")){
              contactClientBool = true;
          }
          String numOfAttempt = req.getParameter("numOfAttempt");
          String notes = req.getParameter("notes");

          int logId = model.getNextSeq(logSheet);
          model.insertLog(logId, clientName, initialCallDate, letterMailDate, firstCallDate,
                          secCallDate, contactClientBool, numOfAttempt, notes);
          message = "<font color=\"blue\"><b>Tracking was added successfully!";
          success = true;
      }catch(Throwable e){
          e.printStackTrace();
          success = false;
          errMsg = e.getLocalizedMessage();
          retPage = errorPage;
      }
      if (success){
          session.setAttribute("message", message);
      }else{
          session.setAttribute("message", "<font color=\"red\"><b>Problem adding log!</b></font>");
          session.setAttribute("error", errMsg);
      }
      return retPage;
  }

  public String getName() {
      return "EnterNewLogAction.do";
  }

}
