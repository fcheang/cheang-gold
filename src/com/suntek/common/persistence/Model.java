package com.suntek.common.persistence;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.suntek.common.utils.*;


/**
 * Sigleton object which has implementation for all the methods
 */
public class Model {
	
	private static Logger logger = Logger.getLogger("com.suntek.common.persistence.Model");
	
    public static Date END_OF_TIME = null;
    public static DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    public static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

    private static Model model = null;
    private static long oneDayInMillis = 24 * 60 * 60 * 1000;

    public static boolean debugOn = false;
    public static DataSource ds = null;

    public static Model getInstance(){
        return model;
    }

    public Model(String dbDriver, String dbHost, String dbUsed, String dbUser, String dbPassword, String poolSizeStr){
        init();
        model = this;
    }

    private void init(){
        try{
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQLDB");
        }catch(Exception e){
            System.out.println("Error: problem initialize datasource!");
            e.printStackTrace();
        }
    }

    public void destroy(){
    }


    private void close(ResultSet rs){
	try{
	    if (rs != null){
		rs.close();
	    }
	}catch(SQLException e){
	}
    }

    private void close(Statement stmt){
	try{
	    if (stmt != null){
		stmt.close();
	    }
	}catch(SQLException e){
	}
    }

    private void setString(PreparedStatement pStmt, int param, String val)
	throws SQLException
    {
        logger.debug("setString("+param+", "+val+")");
        if (val != null){
            pStmt.setString(param, val);
        }else{
            pStmt.setNull(param, Types.VARCHAR);
        }
    }

    private void setDate(PreparedStatement pstmt, int param, java.util.Date date)
    throws SQLException
    {
        logger.debug("setTimestamp("+param+", "+date+")");
        if (date != null){
            pstmt.setTimestamp(param, new Timestamp(date.getTime()));
        }else{
            pstmt.setNull(param, Types.TIMESTAMP);
        }
    }

    private void setInt(PreparedStatement pstmt, int param, int val)
    throws SQLException
    {
        logger.debug("setInt("+param+", "+val+")");
        pstmt.setInt(param, val);
    }

    private void setBoolean(PreparedStatement pstmt, int param, boolean val)
    throws SQLException
    {
        logger.debug("setBoolean("+param+", "+val+")");
        pstmt.setBoolean(param, val);
    }

    private void setBigDecimal(PreparedStatement pstmt, int param, BigDecimal val)
    throws SQLException
    {
        logger.debug("setBigDecimal("+param+", "+val+")");
        if (val != null){
            pstmt.setBigDecimal(param, val);
        }else{
            pstmt.setNull(param, Types.NUMERIC);
        }
    }

    private void executeUpdate(PreparedStatement stmt)
    throws SQLException
    {
        logger.debug("executeUpdate");
        stmt.executeUpdate();
    }

    private ResultSet executeQuery(PreparedStatement stmt)
    throws SQLException
    {
        logger.debug("executeQuery");
        return stmt.executeQuery();
    }

    public void releaseConnection(Connection con){
        try{
            con.close();
        }catch(Exception e){
        }
    }

    // SELECT OPERATION

    public boolean login(String userId, String pswd){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        logger.debug("calling login("+userId+", "+pswd+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.checkLogin);
            setString(pstmt, 1, userId);
            setString(pstmt, 2, SecurityUtil.encrypt(pswd));
            rs = pstmt.executeQuery();
            if (rs.next()){
            return true;
            }else{
            return false;
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Problem checking login", e);
            return false;
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
    }

    public HashMap<String, String> getCapability(String userId){
        String sql = QueryString.getCapability;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        HashMap<String, String> map = new HashMap<String, String>();

        logger.debug("calling getCapability("+userId+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                //String id = rs.getString(1);
                String obj = rs.getString(2);
                String perm = rs.getString(3);
                map.put(obj, perm);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);        	
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return map;
    }

    /**
     * @return List of String objects
     */
    public List getAllReceptionistNames(){
        PreparedStatement pstmt = null;
        String sql = QueryString.getAllReceptionistNames;
        ResultSet rs = null;
        Connection con = null;
        List list = new ArrayList();

        logger.debug("calling getAllReceptionistNames()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                list.add( rs.getString(1) );
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return list;
    }

    /**
     * @return String->Integer of userId to referral count
     */
    public HashMap getReferralCountByUserId(Date esd, Date eed){
        PreparedStatement pstmt = null;
        String sql = QueryString.getReferralCountByUserId;
        ResultSet rs = null;
        Connection con = null;
        HashMap map = new HashMap();

        logger.debug("calling getReferralCountByUserId("+esd+", "+eed+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String userId = rs.getString(1);
                Integer count = new Integer(rs.getInt(2));
                map.put(userId, count);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return map;
    }

    /**
     * @return String->Integer of userId to referral count
     */
    public HashMap getApptCountByUserId(Date esd, Date eed){
        PreparedStatement pstmt = null;
        String sql = QueryString.getApptCountByUserId;
        ResultSet rs = null;
        Connection con = null;
        HashMap map = new HashMap();

        logger.debug("calling getApptCountByUserId("+esd+", "+eed+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String userId = rs.getString(1);
                Integer count = new Integer(rs.getInt(2));
                map.put(userId, count);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return map;
    }
    
    public int getApptCountForProvider(String providerName){
        PreparedStatement pstmt = null;
        String sql = QueryString.getApptCountForProvider;
        ResultSet rs = null;
        Connection con = null;
        int numAppt = 0;

        logger.debug("calling getApptCountForProvider("+providerName+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, providerName);
            rs = pstmt.executeQuery();
            if (rs.next()){
            	numAppt = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return numAppt;    	
    }

    public int getNumInsuranceForProvider(String insProvider){
        PreparedStatement pstmt = null;
        String sql = QueryString.getNumInsuranceForProvider;
        ResultSet rs = null;
        Connection con = null;
        int numIns = 0;

        logger.debug("calling getNumInsurance("+insProvider+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, insProvider);
            rs = pstmt.executeQuery();
            if (rs.next()){
            	numIns = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return numIns;    	
    }

    public int getNumReferralForClinic(String clinic){
        PreparedStatement pstmt = null;
        String sql = QueryString.getNumReferralForClinic;
        ResultSet rs = null;
        Connection con = null;
        int numRef = 0;

        logger.debug("calling getNumReferral("+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, clinic);
            rs = pstmt.executeQuery();
            if (rs.next()){
            	numRef = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return numRef;    	
    }    

    public int getNumApptForClinic(String clinic){
        PreparedStatement pstmt = null;
        String sql = QueryString.getNumApptForClinic;
        ResultSet rs = null;
        Connection con = null;
        int numAppt = 0;

        logger.debug("calling getNumApptForClinic("+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, clinic);
            rs = pstmt.executeQuery();
            if (rs.next()){
            	numAppt = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return numAppt;    	
    }    
    
    public boolean allowLogin(String ip){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String domain = getDomain(ip);
        logger.debug("calling allowLogin("+ip+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.isTrustedHost);
            setString(pstmt, 1, ip);
            setString(pstmt, 2, domain);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Problem in allowLogin", e);
            return false;
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
    }

    private String getDomain(String host){
        // domain is the first three section plus a star
        StringBuffer sb = new StringBuffer("");
        try{
            StringTokenizer stk = new StringTokenizer(host, ".");
            sb.append(stk.nextToken());
            sb.append(".");
            sb.append(stk.nextToken());
            sb.append(".");
            sb.append(stk.nextToken());
            sb.append(".");
            sb.append("*");
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public boolean isRemoteUser(String user){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        logger.debug("calling isRemoteUser("+user+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.isRemoteUser);
            setString(pstmt, 1, user);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
    }

    public boolean checkPermission(String userId, String module){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        logger.debug("calling checkPermission("+userId+", "+module+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.checkPermission);
            setString(pstmt, 1, userId);
            setString(pstmt, 2, module);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, null, e);
            return false;
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
    }

    public List<String> getInsuranceProviderName(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getInsuranceProviderName;
        List<String> retVal = new ArrayList<String>();
        logger.debug("calling getInsuranceProvider()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                retVal.add(rs.getString(1));
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;
    }

    public List getClinicName(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getClinicName;
        List retVal = new ArrayList();
        logger.debug("calling getClinicName()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                retVal.add(rs.getString(1));
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;
    }

    public Date getReferralDate(int refId){
        Date refDate = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getReferralDate;
        logger.debug("calling getReferralDate("+refId+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            rs = executeQuery(pstmt);
            if (rs.next()){
                refDate = rs.getDate(1);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return refDate;
    }

    public Referral getReferral(int refId){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getReferral;
        Referral ref = null;
        logger.debug("calling getReferral()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            rs = executeQuery(pstmt);

            if (rs.next()){
                ref = new Referral();
                ref.setRefId(rs.getInt(1));
                ref.setStatus(rs.getString(2));
                ref.setCreateDate(rs.getTimestamp(3));
                //ref.setFirstName(SecurityUtil.decrypt(rs.getString(4)));
                ref.setFirstName(rs.getString(4));
                ref.setMi(rs.getString(5));

                ref.setLastName(rs.getString(6));
                ref.setGender(rs.getString(7));
                ref.setSsn(SecurityUtil.decrypt(rs.getString(8)));
                ref.setDob(rs.getTimestamp(9));
                ref.setStreetAddress(rs.getString(10));

                ref.setApartmentNumber(rs.getString(11));
                ref.setCity(rs.getString(12));
                ref.setState(rs.getString(13));
                ref.setZipcode(rs.getString(14));
                ref.setDpn(rs.getString(15));

                ref.setEmail(rs.getString(16));
                ref.setLgFirstName(rs.getString(17));
                ref.setLgMi(rs.getString(18));
                ref.setLgLastName(rs.getString(19));
                ref.setLgpn(rs.getString(20));

                ref.setInsId(rs.getInt(21));
                ref.setRefDate(rs.getDate(22));
                ref.setEligEffDate(rs.getDate(23));
                ref.setEligTermDate(rs.getDate(24));
                ref.setInsurance(rs.getString(25));
                ref.setMemberId(rs.getString(26));
                ref.setCopayBD(rs.getBigDecimal(27));
                ref.setCopayParityBD(rs.getBigDecimal(28));
                ref.setInspn(rs.getString(29));
                ref.setAuthNumForMD(rs.getString(30));
                ref.setAuthNumForMA(rs.getString(31));

                ref.setNumAuthVisitForMD(rs.getInt(32));
                ref.setNumAuthVisitForMA(rs.getInt(33));
                ref.setInsNotes(rs.getString(34));
                ref.setPrevPsychiatrist(rs.getString(35));
                ref.setLastSeen(rs.getTimestamp(36));
                ref.setCurrMed(rs.getString(37));
                ref.setDaysLeft(rs.getInt(38));

                ref.setPrevMed(rs.getString(39));
                ref.setPrevDx(rs.getString(40));
                ref.setPp(rs.getString(41));
                ref.setMmNeeded(rs.getBoolean(42));
                ref.setTpNeeded(rs.getBoolean(43));

                ref.setUrgent(rs.getString(44));
                ref.setClinic(rs.getString(45));
                ref.setComments(rs.getString(46));
                
                ref.setMedicalId(rs.getString(47));
                ref.setMedicalIssueDate(rs.getTimestamp(48));
                ref.setNts(rs.getBoolean(49));
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        Date rd = this.getReferralDate(ref.getRefId());
        ref.setRd(rd);
        return ref;
    }

    public List<Referral> getReferralUsingSql(String sql){
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Referral> retVal = new ArrayList<Referral>();
        logger.debug("calling getReferralUsingSql()");
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            logger.debug("executing sql: "+sql);
            rs = stmt.executeQuery(sql);

            Referral ref = null;
            while (rs.next()){
                ref = new Referral();
                ref.setRefId(rs.getInt(1));
                ref.setStatus(rs.getString(2));
                //ref.setFirstName(SecurityUtil.decrypt(rs.getString(3)));
                ref.setFirstName(rs.getString(3));
                ref.setMi(rs.getString(4));
                ref.setLastName(rs.getString(5));
                ref.setClinic(rs.getString(6));
                ref.setUrgent(rs.getString(7));
                ref.setMmNeeded(rs.getBoolean(8));
                ref.setTpNeeded(rs.getBoolean(9));
                retVal.add(ref);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(stmt);
            releaseConnection(con);
        }
        return retVal;
    }

    public List getAllWaitlistedReferral(){

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getAllNotScheduledReferral;
        List retVal = new ArrayList();
        logger.debug("calling getAllWaitlistedReferral()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Action.WAITLISTED);
            rs = pstmt.executeQuery();

            Referral ref = null;
            while (rs.next()){
                ref = new Referral();
                ref.setRefId(rs.getInt(1));
                ref.setCreateDate(rs.getTimestamp(2));
                //ref.setFirstName(SecurityUtil.decrypt(rs.getString(3)));
                ref.setFirstName(rs.getString(3));
                ref.setMi(rs.getString(4));
                ref.setLastName(rs.getString(5));
                ref.setClinic(rs.getString(6));
                ref.setUrgent(rs.getString(7));
                ref.setMmNeeded(rs.getBoolean(8));
                ref.setTpNeeded(rs.getBoolean(9));
                retVal.add(ref);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;
    }

    public String getRefHistoryTable(int refId){
        String retVal = "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getRefHistory;
        logger.debug("calling getRefHistory()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            rs = executeQuery(pstmt);
            retVal += "<table border=1>"+
            "<tr><th width=20%>Effective Start Date</th>"+
            "<th width=20%>Effective End Date</th>"+
            "<th width=20%>Status</th>"+
            "<th width=40%>Notes</th></tr>";
            while (rs.next()){
                String notes = rs.getString(4);
                if (notes == null){
                    notes = " ";
                }
                retVal += "<tr>"+
                "<td width=20%>"+dtf.format(rs.getTimestamp(1))+"</td>"+
                "<td width=20%>"+dtf.format(rs.getTimestamp(2))+"</td>"+
                "<td width=20%>"+rs.getString(3)+"</td>"+
                "<td width=40%>"+notes+"</td>"+
                "</tr>";
            }
            retVal += "</table><p>";
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing "+sql, e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;

    }

    // INSERT OPERATION

    public void insertReferral(Referral ref){
        int refId = this.getNextSeq("referral");
        ref.setRefId(refId);
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        try{
            con = ds.getConnection();

            // insert referral
            pstmt1 = con.prepareStatement(QueryString.insertReferral);
            setInt(pstmt1, 1, ref.getRefId());
            //setString(pstmt1, 2, SecurityUtil.encrypt(ref.getFirstName()));
            setString(pstmt1, 2, ref.getFirstName());
            setString(pstmt1, 3, ref.getMi());
            setString(pstmt1, 4, ref.getLastName());
            setString(pstmt1, 5, ref.getGender());

            setString(pstmt1, 6, SecurityUtil.encrypt(ref.getSsnForDB()));
            setDate(pstmt1, 7, ref.getDobForDB());
            setString(pstmt1, 8, ref.getStreetAddress());
            setString(pstmt1, 9, ref.getApartmentNumber());
            setString(pstmt1, 10, ref.getCity());

            setString(pstmt1, 11, ref.getState());
            setString(pstmt1, 12, ref.getZipcode());
            setString(pstmt1, 13, ref.getDpnForDB());
            setString(pstmt1, 14, ref.getEmail());
            setString(pstmt1, 15, ref.getLgFirstName());

            setString(pstmt1, 16, ref.getLgMi());
            setString(pstmt1, 17, ref.getLgLastName());
            setString(pstmt1, 18, ref.getLgpnForDB());
            setString(pstmt1, 19, ref.getPrevPsychiatrist());
            setDate(pstmt1, 20, ref.getLastSeenForDB());

            setString(pstmt1, 21, ref.getCurrMed());
            setInt(pstmt1, 22, ref.getDaysLeft());
            setString(pstmt1, 23, ref.getPrevMed());
            setString(pstmt1, 24, ref.getPrevDx());
            setString(pstmt1, 25, ref.getPp());

            setBoolean(pstmt1, 26, ref.getMmNeeded());
            setBoolean(pstmt1, 27, ref.getTpNeeded());
            setString(pstmt1, 28, ref.getUrgent());
            setString(pstmt1, 29, ref.getClinic());
            setString(pstmt1, 30, ref.getComments());
            setBoolean(pstmt1, 31, ref.getNts());

            setBoolean(pstmt1, 32, ref.getAgeGroup().equalsIgnoreCase("Child"));
            setString(pstmt1, 33, ref.getUserId());

            executeUpdate(pstmt1);

            // insert insurance
            pstmt2 = con.prepareStatement(QueryString.insertInsurance);
            setInt(pstmt2, 1, refId);
            setDate(pstmt2, 2, ref.getRefDateForDB());
            setDate(pstmt2, 3, ref.getEligEffDateForDB());
            setDate(pstmt2, 4, ref.getEligTermDateForDB());
            setString(pstmt2, 5, ref.getInsurance());
            setString(pstmt2, 6, ref.getMemberId());
            setBigDecimal(pstmt2, 7, ref.getCopayForBD());
            setBigDecimal(pstmt2, 8, ref.getCopayParityForBD());            
            setString(pstmt2, 9, ref.getInspnForDB());
            setString(pstmt2, 10, ref.getAuthNumForMD());
            setString(pstmt2, 11, ref.getAuthNumForMA());
            setInt(pstmt2, 12, ref.getNumAuthVisitForMD());
            setInt(pstmt2, 13, ref.getNumAuthVisitForMA());
            setString(pstmt2, 14, ref.getMedicalId());
            setDate(pstmt2, 15, ref.getMedicalIssueDateForDB());
            setString(pstmt2, 16, ref.getInsNotes());
            setInt(pstmt2, 17, 1);
            executeUpdate(pstmt2);

            // insert referralStatus
            pstmt3 = con.prepareStatement(QueryString.insertReferralStatus);
            setInt(pstmt3, 1, refId);
            setString(pstmt3, 2, ref.getStatus());
            setBoolean(pstmt3, 3, ref.getIsActive());
            setDate(pstmt3, 4, ref.getRdForDB());
            setDate(pstmt3, 5, ref.getRemoveDateForDB());
            setString(pstmt3, 6, ref.getNotes());
            executeUpdate(pstmt3);



        }catch(SQLException e){
            try{
                con.rollback();
            }catch(SQLException ce){
            }
            logger.log(Level.ERROR, "Error: Problem executing query", e);
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt1);
            close(pstmt2);
            close(pstmt3);
            releaseConnection(con);
        }
    }

    public void insertUser(String userid, String password, String firstname,
        String lastname, String description, String[] roles)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertUser);
            setString(pstmt, 1, userid);
            setString(pstmt, 2, SecurityUtil.encrypt(password));
            setString(pstmt, 3, firstname);
            setString(pstmt, 4, lastname);
            setString(pstmt, 5, description);
            pstmt.executeUpdate();
            
            for (String r : roles){
	            pstmt2 = con.prepareStatement(QueryString.insertUserRole);
	            setString(pstmt2, 1, userid);
	            setString(pstmt2, 2, r);
	            pstmt2.executeUpdate();
	            close(pstmt2);
            }
        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing query", e);
        	throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }       

    public synchronized int getNextSeq(String tableName){
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        int nextSeq = 0;
        try{
            con = ds.getConnection();
            pstmt1 = con.prepareStatement(QueryString.updateSeq);
            setString(pstmt1, 1, tableName);
            pstmt1.executeUpdate();

            pstmt2 = con.prepareStatement(QueryString.getNextSeq);
            pstmt2.setString(1, tableName);
            ResultSet rs = pstmt2.executeQuery();
            if (rs.next()){
                nextSeq = rs.getInt(1);
            }

        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing query", e);        	
        }finally{
            close(pstmt1);
            close(pstmt2);
            releaseConnection(con);
        }
        return nextSeq;
    }

    public void insertInsuranceProvider (String name, String streetAddress,
        String state, String city, String zipcode, String phoneNumber1,
        String phoneNumber2, String faxNumber, String notes)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertInsuranceProvider);
            setString(pstmt, 1, name);
            setString(pstmt, 2, streetAddress);
            setString(pstmt, 3, state);
            setString(pstmt, 4, city);
            setString(pstmt, 5, zipcode);
            setString(pstmt, 6, phoneNumber1);
            setString(pstmt, 7, phoneNumber2);
            setString(pstmt, 8, faxNumber);
            setString(pstmt, 9, notes);
            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing query", e);        	
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void insertLog (int logId,
                           String clientName,
                           String initialCallDate,
                           String letterMailDate,
                           String firstCallDate,
                           String secCallDate,
                           boolean madeContact,
                           String numOfAttempt,
                           String notes       )
    {
        //logId, clientName, initialCallDate, letterMailDate, firstCallDate
        //secCallDate, madeContact, numOfAttempt, notes

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertLog);
            setInt(pstmt, 1, logId);
            setString(pstmt, 2, clientName);
            setString(pstmt, 3, initialCallDate);
            setString(pstmt, 4, letterMailDate);
            setString(pstmt, 5, firstCallDate);
            setString(pstmt, 6, secCallDate);
            setBoolean(pstmt, 7, madeContact);
            setString(pstmt, 8, numOfAttempt);
            setString(pstmt, 9, notes);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.log(Level.ERROR, "Error: Problem executing query", e);        	
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void insertTimesheet (
    		Date date,
    		String clinic,
            String provider,
            String type,
            BigDecimal totalHours)
    {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	try{
    		con = ds.getConnection();
    		pstmt = con.prepareStatement(QueryString.insertTimesheet);
    		setDate(pstmt, 1, date);
    		setString(pstmt, 2, clinic);
    		setString(pstmt, 3, provider);
    		setString(pstmt, 4, type);
    		setBigDecimal(pstmt, 5, totalHours);
    		executeUpdate(pstmt);
    	}catch(SQLException e){
    		logger.debug("Error: problem executing query: "+e.getMessage());
    		e.printStackTrace();
    		throw new RuntimeException(e.getMessage(), e);
    	}finally{
    		close(pstmt);
    		releaseConnection(con);
    	}
    }
    
    public void insertClinic (String name, String streetAddress,
        String state, String city, String zipcode, String phoneNumber1,
        String phoneNumber2, String faxNumber, String notes)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertClinic);
            setString(pstmt, 1, name);
            setString(pstmt, 2, streetAddress);
            setString(pstmt, 3, state);
            setString(pstmt, 4, city);
            setString(pstmt, 5, zipcode);
            setString(pstmt, 6, phoneNumber1);
            setString(pstmt, 7, phoneNumber2);
            setString(pstmt, 8, faxNumber);
            setString(pstmt, 9, notes);
            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    /**
     * @return List of Clinic objects
     */
    public List getAllClinic()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List clinics = new ArrayList();
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getAllClinic);
            String name;
            String street;
            String city;
            String state;
            String zipcode;
            String pn1;
            String pn2;
            String fax;
            String notes;
            while (rs.next()){
                name = rs.getString(1);
                street = rs.getString(2);
                city = rs.getString(3);
                state = rs.getString(4);
                zipcode = rs.getString(5);
                pn1 = rs.getString(6);
                pn2 = rs.getString(7);
                fax = rs.getString(8);
                notes = rs.getString(9);
                clinics.add(new Clinic(name, street, city, state, zipcode, pn1, pn2, fax, notes));
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return clinics;
    }

    /**
     * @return List of PatientLog objects
     */
    public List getPatientLog(int loLogId, int hiLogId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List logs = new ArrayList();
        PatientLog patLog = null;
        try{
            con = ds.getConnection();

            // logId, clientName, initialCallDate, letterMailDate, firstCallDate,
            // secCallDate, madeContact, numOfAttempt, notes

            pstmt = con.prepareStatement(QueryString.getPatientLog);
            setInt(pstmt, 1, loLogId);
            setInt(pstmt, 2, hiLogId);
            rs = executeQuery(pstmt);
            while (rs.next()){
                patLog = new PatientLog();
                patLog.logId = rs.getInt(1);
                patLog.clientName = rs.getString(2);
                patLog.iniCallDate = rs.getString(3);
                patLog.confirmSendDate = rs.getString(4);
                patLog.firstCallDate = rs.getString(5);
                patLog.secCallDate = rs.getString(6);
                patLog.contactClient = rs.getBoolean(7);
                patLog.numAttempts = rs.getString(8);
                patLog.notes = rs.getString(9);
                logs.add(patLog);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
        return logs;
    }

    public List getTimesheet(Date date, String type)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List list = new ArrayList();
        Timesheet ts = null;
        try{
            con = ds.getConnection();

            pstmt = con.prepareStatement(QueryString.getTimesheetByDateAndType);
            setDate(pstmt, 1, date);
            setString(pstmt, 2, type);
            
            rs = executeQuery(pstmt);
            while (rs.next()){
                ts = new Timesheet();
                ts.setId(rs.getInt(1));
                ts.setDate(rs.getDate(2));
                ts.setClinic(rs.getString(3));
                ts.setStaff(rs.getString(4));
                ts.setType(rs.getString(5));
                ts.setTotalHours(rs.getBigDecimal(6));
                list.add(ts);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
        return list;
    }
    
    public int getLastLogId(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int lastId = 0;
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getLastLogId);
            if (rs.next()){
                lastId = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return lastId;
    }

    /**
     * @return List of Provider objects
     */
    public List<Provider> getAllProvider()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Provider> providers = new ArrayList<Provider>();
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getAllProvider);
            int id;
            String name;
            String title;
            Provider p;
            while (rs.next()){
            	id = rs.getInt(1);
                name = rs.getString(2);
                title = rs.getString(3);
                p = new Provider(id, name, title);
                providers.add(p);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return providers;
    }

    public List<User> getAllUser()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        List<User> users = new ArrayList<User>();
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getAllUser);
            
            String name;
            String password;
            String firstname;
            String lastname;
            String description;
            List<String> roles;

            while (rs.next()){
                name = rs.getString(1);
                password = SecurityUtil.decrypt(rs.getString(2));
                firstname = rs.getString(3);
                lastname = rs.getString(4);
                description = rs.getString(5);
                User u = new User(name, password, firstname, lastname, description);
                
                stmt2 = con.prepareStatement(QueryString.getUserRole);
                stmt2.setString(1, name);
                rs2 = stmt2.executeQuery();
                roles = new ArrayList<String>();
                while (rs2.next()){
                	roles.add(rs2.getString(1));
                }
                u.roles = roles;
                users.add(u);
             
                close(stmt2);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return users;
    }

    public List getAllTrustedHost()
    {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List hosts = new ArrayList();
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getAllTrustedHost);
            String name;

            while (rs.next()){
                name = rs.getString(1);
                hosts.add(name);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return hosts;
    }

    public List getAllRemoteUser()
    {
        logger.debug("getAllRemoteUser");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        List users = new ArrayList();
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(QueryString.getAllRemoteUser);
            String name;

            while (rs.next()){
                name = rs.getString(1);
                users.add(name);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return users;
    }

    public User getUser(String aName)
    {
        logger.debug("getUser("+aName+")");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        User user = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getUser);
            setString(pstmt, 1, aName);
            rs = pstmt.executeQuery();
            String name;
            String password;
            String firstname;
            String lastname;
            String description;
            List<String> roles;

            if (rs.next()){
                name = rs.getString(1);
                password = SecurityUtil.decrypt(rs.getString(2));
                firstname = rs.getString(3);
                lastname = rs.getString(4);
                description = rs.getString(5);
                user = new User(name, password, firstname, lastname, description);

                pstmt2 = con.prepareStatement(QueryString.getUserRole);
                pstmt2.setString(1, aName);
                rs2 = pstmt2.executeQuery();
                roles = new ArrayList<String>();
                while (rs2.next()){
                	roles.add(rs2.getString(1));
                }
                
                user.roles = roles;
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            close(pstmt2);
            releaseConnection(con);
        }
        return user;
    }

    public List<InsuranceProvider> getAllInsuranceProvider(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getAllInsuranceProvider;
        List<InsuranceProvider> retVal = new ArrayList<InsuranceProvider>();
        logger.debug("calling getAllInsuranceProvider()");
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
            	InsuranceProvider obj = new InsuranceProvider(rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9));
                retVal.add(obj);
            }
        }catch(SQLException e){
            System.out.println("Error: Problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(stmt);
            releaseConnection(con);
        }
        return retVal;
    }

    public List<InsuranceProvider> getAllCredentials(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getAllCredentials;
        List<InsuranceProvider> retVal = new ArrayList<InsuranceProvider>();
        logger.debug("calling getAllCredentials()");
        try{
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
            	InsuranceProvider ip = new InsuranceProvider();
            	ip.id = rs.getInt(1);
            	ip.name = rs.getString(2);
            	retVal.add(ip);
            }
        }catch(SQLException e){
            System.out.println("Error: Problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(stmt);
            releaseConnection(con);
        }
        return retVal;
    }
    
    public static Date getEndOfTime(){
        if (END_OF_TIME == null){
            END_OF_TIME = (new GregorianCalendar(2200, 0, 1)).getTime();
        }
        return END_OF_TIME;
    }
    
    public static Date getDateForDB(int month, int day, int year){
        Date d = null;
        if (year != 0){
            d = new GregorianCalendar(year, month - 1, day).getTime();
        }
        return d;    	
    }
    
    public static int getMonth(Date date){
        int val = 0;
        // gc is zero based
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.MONTH);
        return val + 1;
    }

    public static int getDay(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.DATE);
        return val;
    }

    public static int getYear(Date date){
        int val = 0;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        val = cal.get(Calendar.YEAR);
        return val;
    }
    

    private void changeReferralStatus(Connection con, int refId, String newStatus, String notes)
        throws SQLException
    {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        Date now = new Date();
        try{
            pstmt1 = con.prepareStatement(QueryString.endDateReferralStatus);
            setDate(pstmt1, 1, now);
            setInt(pstmt1, 2, refId);
            setDate(pstmt1, 3, this.getEndOfTime());
            executeUpdate(pstmt1);

            pstmt2 = con.prepareStatement(QueryString.insertReferralStatus);
            setInt(pstmt2, 1, refId);
            setString(pstmt2, 2, newStatus);
            setBoolean(pstmt2, 3, true);
            setDate(pstmt2, 4, now);
            setDate(pstmt2, 5, this.getEndOfTime());
            setString(pstmt2, 6, notes);
            executeUpdate(pstmt2);
        }finally{
            close(pstmt1);
            close(pstmt2);
        }
    }

    public void scheduleAppt(Appointment appt){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertAppt);
            setInt(pstmt, 1, appt.getRefId());
            setString(pstmt, 2, appt.getClinic());
            setDate(pstmt, 3, appt.getApptDate());
            setString(pstmt, 4, appt.getProvider());
            setBoolean(pstmt, 5, appt.getNeedTranslationSvc());
            setBoolean(pstmt, 6, appt.getCollateralReceived());
            setString(pstmt, 7, appt.getNotes());
            setString(pstmt, 8, appt.getUserId());
            executeUpdate(pstmt);

            changeReferralStatus(con, appt.getRefId(), Action.SCHEDULED, null);


        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void declineReferral(int refId, String notes){
        Connection con = null;
        try{
            con = ds.getConnection();
            changeReferralStatus(con, refId, Action.DECLINED, notes);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }

    public void waitlistReferral(int refId, String notes){
        Connection con = null;
        try{
            con = ds.getConnection();
            changeReferralStatus(con, refId, Action.WAITLISTED, notes);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }

    public void noshowsAppt(int refId, String notes){
        Connection con = null;
        try{
            con = ds.getConnection();
            changeReferralStatus(con, refId, Action.NOT_SEEN, notes);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }


    public void completeAppt(int refId, String notes){
        Connection con = null;
        try{
            con = ds.getConnection();
            changeReferralStatus(con, refId, Action.SEEN, notes);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }

    public Map getRefByInsuranceCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map map = new TreeMap();
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countRefByIns);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);
            String ins;
            int count;

            while (rs.next()){
                ins = rs.getString(1);
                count = rs.getInt(2);
                map.put(ins, new Integer(count));
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return map;
    }

    public Map getRefByStatusCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map map = new TreeMap();
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countRefByStatus);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);
            String status;
            int count;

            while (rs.next()){
                status = rs.getString(1);
                count = rs.getInt(2);
                map.put(status, new Integer(count));
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return map;
    }

    public int getChildRefCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countChildRef);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);

            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return count;
    }

    public int getAdultRefCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countAdultRef);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);

            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return count;
    }

    public int getNeedMedicalSvcCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countNeedMedicalSvcRef);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);

            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return count;
    }

    public int getNeedTherapyCount(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.countNeedTherapyRef);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            rs = executeQuery(stmt);

            if (rs.next()){
                count = rs.getInt(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return count;
    }

    public BigDecimal getAvgWaitTime(Date esd, Date eed)
    {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BigDecimal waitDay = null;
        try{
            con = ds.getConnection();
            stmt = con.prepareStatement(QueryString.avgWaitDay);
            setDate(stmt, 1, esd);
            setDate(stmt, 2, eed);
            setDate(stmt, 3, esd);
            setDate(stmt, 4, eed);
            rs = executeQuery(stmt);

            if (rs.next()){
                waitDay = rs.getBigDecimal(1);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(stmt);
            releaseConnection(con);
        }
        return waitDay;
    }

    public String getSummaryReportTable(String clinic, Date esd, Date eed){
        logger.debug("calling getSummaryReportTable("+clinic+", "+esd+", "+eed+")");
        StringBuffer sb = new StringBuffer();
        Hashtable reportEntryTbl = new Hashtable();
        ReportEntry totalEntry = new ReportEntry();
        totalEntry.insurance = "Total";

        sb.append("<table border=1>\n");
        sb.append(ReportEntry.getHeader());

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ds.getConnection();
            // populate numRef, child, adult, numUrgent
            stmt = con.prepareStatement(QueryString.getNewRefCountOrderByInsurance);
            setString(stmt, 1, clinic);
            setDate(stmt, 2, esd);
            setDate(stmt, 3, eed);
            rs = executeQuery(stmt);

            String ins;
            boolean isChild;
            boolean isUrgent;
            ReportEntry entry;
            while (rs.next()){
                ins = rs.getString(1);
                isChild = rs.getBoolean(2);
                String isUrgentStr = rs.getString(3);
                if (isUrgentStr.equalsIgnoreCase("yes")){
                    isUrgent = true;
                }else{
                    isUrgent = false;
                }
                entry = (ReportEntry)reportEntryTbl.get(ins);
                if (entry == null){
                    entry = new ReportEntry();
                    entry.insurance = ins;
                    reportEntryTbl.put(ins, entry);
                }
                entry.numReferral += 1;
                totalEntry.numReferral += 1;
                if (isChild){
                    entry.numChild += 1;
                    totalEntry.numChild += 1;
                }else{
                    entry.numAdult += 1;
                    totalEntry.numAdult += 1;
                }
                if (isUrgent){
                    entry.numUrgentEval += 1;
                    totalEntry.numUrgentEval += 1;
                }
            }
            close(stmt);

            // populate waiting days
            stmt = con.prepareStatement(QueryString.getScheduledRef);
            setString(stmt, 1, clinic);
            setDate(stmt, 2, esd);
            setDate(stmt, 3, eed);

            rs = executeQuery(stmt);
            int refId;
            Date scheduleDate;
            Date waitlistDate;

            while (rs.next()){
                ins = rs.getString(1);
                refId = rs.getInt(2);
                scheduleDate = rs.getDate(3);
                waitlistDate = getWaitlistDate(refId, scheduleDate);
                if (waitlistDate != null){
                    int waitDays = (int)((scheduleDate.getTime() - waitlistDate.getTime()) / oneDayInMillis);
                    entry = (ReportEntry)reportEntryTbl.get(ins);
                    if (entry == null){
                        entry = new ReportEntry();
                        reportEntryTbl.put(ins, entry);
                    }
                    entry.totalWaitDays += waitDays;
                    entry.numScheduled += 1;
                    totalEntry.totalWaitDays += waitDays;
                    totalEntry.numScheduled += 1;
                }
            }
            close(stmt);

            List insList = new ArrayList();
            Enumeration keys = reportEntryTbl.keys();
            while (keys.hasMoreElements()){
                insList.add(keys.nextElement());
            }
            Collections.sort(insList);
            for (int i=0; i<insList.size(); i++){
                String insName = (String)insList.get(i);
                ReportEntry finalEntry = (ReportEntry)reportEntryTbl.get(insName);
                sb.append(finalEntry.getRow()).append("\n");
            }
            sb.append(totalEntry.getRow()).append("\n");
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
        sb.append("</table>");
        return sb.toString();
    }

    private Date getWaitlistDate(int refId, Date scheduleDate){
        logger.debug("calling getWaitlistDate("+refId+", "+scheduleDate+")");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getWaitlistDate;
        Date wlDate = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            setDate(pstmt, 2, scheduleDate);
            rs = executeQuery(pstmt);
            if (rs.next()){
                wlDate = rs.getDate(1);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return wlDate;

    }

    public List getApptById(int refId){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getApptById;
        List appts = new ArrayList();
        Appointment appt = null;
        logger.debug("calling getApptById("+refId+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new Appointment();
                appt.setRefId( rs.getInt(1) );
                appt.setClinic( rs.getString(2) );
                appt.setApptDate( rs.getTimestamp(3) );
                appt.setProvider( rs.getString(4) );
                appt.setNeedTranslationSvc( rs.getBoolean(5) );
                appt.setCollateralReceived( rs.getBoolean(6) );
                appt.setNotes( rs.getString(7) );
                appt.setStatus( rs.getString(8) );
                if (!appt.getStatus().equals(Action.SCHEDULED)){
                  appt.setNotes( rs.getString(9) );
                }
                appt.setReasonCode( rs.getString(10) );
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByProviderByClinic(Calendar apptDate, String provider, String clinic){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getApptDayViewByProviderByClinic;
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByProviderByClinic("+apptDate+", "+provider+", "+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, clinic);
            setString(pstmt, 4, provider);

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId

                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);

                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByProvider(Calendar apptDate, String provider){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getApptDayViewByProvider;
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByProvider("+apptDate+", "+provider+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, provider);

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
                
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByClinic(Calendar apptDate, String clinic){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getApptDayViewByClinic;
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByClinic("+apptDate+", "+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, clinic);

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));                
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
                
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayView(Calendar apptDate){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getApptDayView;
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayView("+apptDate+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
                
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    // by insurance
    
    private String getInsClause(String[] insList){
    	String clause = "and (";
    	for (int i=0; i<insList.length; i++){
    		if (i+1 == insList.length){
    			clause += "i.insuranceCompany = ? ";
    		}else{
    			clause += "i.insuranceCompany = ? or ";
    		}
    	}
    	clause += ") ";
    	return clause;
    }
    
    public List getApptDayViewByProviderByClinicByIns(Calendar apptDate, String provider, String clinic, String[] insList){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String insClause = getInsClause(insList);
        String sql = QueryString.getApptDayViewByProviderByClinicByIns + insClause + "order by a.appointmentDate, rs.createDate desc ";
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByProviderByClinicByIns("+apptDate+", "+provider+", "+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, clinic);
            setString(pstmt, 4, provider);
            int pos = 5;
            for (int i=0; i<insList.length; i++){
            	setString(pstmt, pos+i, insList[i]);
            }

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName, i.memberId
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
      
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByProviderByIns(Calendar apptDate, String provider, String[] insList){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String insClause = getInsClause(insList);        
        String sql = QueryString.getApptDayViewByProviderByIns + insClause + "order by a.appointmentDate, a.provider, i.insuranceCompany, rs.createDate desc ";
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByProviderByIns("+apptDate+", "+provider+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, provider);
            int pos = 4;
            for (int i=0; i<insList.length; i++){
            	setString(pstmt, pos+i, insList[i]);
            }

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);

                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByClinicByIns(Calendar apptDate, String clinic, String[] insList){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String insClause = getInsClause(insList);                
        String sql = QueryString.getApptDayViewByClinicByIns + insClause + "order by a.appointmentDate, a.clinicName, rs.createDate desc ";
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByClinicByIns("+apptDate+", "+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            setString(pstmt, 3, clinic);
            int pos = 4;
            for (int i=0; i<insList.length; i++){
            	setString(pstmt, pos+i, insList[i]);
            }
            
            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));                
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
                
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }

    public List getApptDayViewByIns(Calendar apptDate, String[] insList){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String insClause = getInsClause(insList);                        
        String sql = QueryString.getApptDayViewByIns + insClause + "order by a.appointmentDate, a.clinicName, a.provider, rs.createDate desc ";
        List appts = new ArrayList();
        ApptDayViewEntry appt = null;
        logger.debug("calling getApptDayViewByIns("+apptDate+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            Calendar nextDate = new GregorianCalendar();
            nextDate.set(apptDate.get(Calendar.YEAR),
                         apptDate.get(Calendar.MONTH),
                         apptDate.get(Calendar.DATE)+1,
                         0, 0, 0);
            nextDate.set(Calendar.MILLISECOND, 0);

            setDate(pstmt, 1, apptDate.getTime());
            setDate(pstmt, 2, nextDate.getTime());
            int pos = 3;
            for (int i=0; i<insList.length; i++){
            	setString(pstmt, pos+i, insList[i]);
            }            

            rs = executeQuery(pstmt);
            while (rs.next()){
                appt = new ApptDayViewEntry();

                //a.referralId, a.clinicName, a.appointmentDate, a.provider,
                //rs.createDate, r.lastName, r.firstName, r.phoneNumber, i.insuranceCompany,
                //i.copay, r.isChild, r.legalGardianLastName, r.legalGardianFirstName
                //r.ssn, r.birthDate
                //a.endTime, r.isUrgent, a.translationSvcNeeded, a.collateralReceived, a.notes
                //rs.status, rs.apptId
                
                appt.refId = rs.getInt(1);
                appt.clinic = rs.getString(2);
                appt.apptDate = rs.getTimestamp(3);
                appt.provider = rs.getString(4);
                appt.createDate = rs.getTimestamp(5);
                appt.lastName = rs.getString(6);
                //appt.firstName = SecurityUtil.decrypt(rs.getString(7));
                appt.firstName = rs.getString(7);
                appt.phoneNum = rs.getString(8);
                appt.ins = rs.getString(9);
                appt.copay = rs.getString(10);
                appt.isChild = rs.getBoolean(11);
                appt.lgLastName = rs.getString(12);
                appt.lgFirstName = rs.getString(13);
                appt.memberId = rs.getString(14);
                appt.setSsn(SecurityUtil.decrypt(rs.getString(15)));
                appt.setDob(rs.getDate(16));
                appt.setEndTime(rs.getTimestamp(17));
                appt.setIsUrgent(rs.getString(18));
                appt.setNeedTranSvc(rs.getBoolean(19));
                appt.setCollRcv(rs.getBoolean(20));
                appt.setApptNotes(rs.getString(21));
                appt.setLgPhoneNum(rs.getString(22));
                appt.setStatus(rs.getString(23));
                appt.apptId = rs.getInt(24);
                appt.setMedicalId(rs.getString(25));
                appt.setMedicalIssueDate(rs.getDate(26));
                appt.setEligEffDate(rs.getDate(27));
                appt.setEligTermDate(rs.getDate(28));
                appt.copayParity = rs.getString(29);
                
                appts.add(appt);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return appts;
    }
    
    
    public int getNumAppt(int refId){
        logger.debug("calling getNumAppt("+refId+")");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int numAppt = 0;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getNumAppt);
            setInt(pstmt, 1, refId);
            rs = pstmt.executeQuery();
            if (rs.next()){
                numAppt = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return numAppt;
    }

    public void deleteReferral(int refId){
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        String deleteStatus = QueryString.deleteRefStatus;
        String deleteIns = QueryString.deleteRefIns;
        String deleteMain = QueryString.deleteRef;
        logger.debug("calling deleteReferral("+refId+")");
        try{
            con = ds.getConnection();
            pstmt1 = con.prepareStatement(deleteStatus);
            setInt(pstmt1, 1, refId);
            executeUpdate(pstmt1);

            pstmt2 = con.prepareStatement(deleteIns);
            setInt(pstmt2, 1, refId);
            executeUpdate(pstmt2);

            pstmt3 = con.prepareStatement(deleteMain);
            setInt(pstmt3, 1, refId);
            executeUpdate(pstmt3);


        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt1);
            close(pstmt2);
            close(pstmt3);
            releaseConnection(con);
        }
    }

    public void updateAppt(Appointment oldAppt, Appointment newAppt){
        Connection con = null;
        String sql = null;
        PreparedStatement pstmt = null;

        logger.debug("calling updateAppt("+oldAppt+", "+newAppt+")");
        try{
            con = ds.getConnection();
            if (!fieldEquals(oldAppt.getClinic(), newAppt.getClinic())){
                sql = "update appointment set clinicName = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newAppt.getClinic());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }
            if (!oldAppt.getApptDate().equals(newAppt.getApptDate())){
                sql = "update appointment set appointmentDate = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setDate(pstmt, 1, newAppt.getApptDate());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldAppt.getProvider(), newAppt.getProvider())){
                sql = "update appointment set provider = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newAppt.getProvider());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldAppt.getNotes(), newAppt.getNotes())){
                sql = "update appointment set notes = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newAppt.getNotes());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }
            if (oldAppt.getNeedTranslationSvc() != newAppt.getNeedTranslationSvc()){
                sql = "update appointment set translationSvcNeeded = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setBoolean(pstmt, 1, newAppt.getNeedTranslationSvc());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }
            if (oldAppt.getCollateralReceived() != newAppt.getCollateralReceived()){
                sql = "update appointment set collateralReceived = ? where referralId = ?";
                pstmt = con.prepareStatement(sql);
                setBoolean(pstmt, 1, newAppt.getCollateralReceived());
                setInt(pstmt, 2, newAppt.getRefId());
                executeUpdate(pstmt);
            }


        }catch(SQLException e){
            try{
                con.rollback();
            }catch(SQLException ce){
            }
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }

    public void updateProviderName(int id, String name){
        logger.debug("updateProviderName("+id+", "+name+")");
        Connection con = null;
        String sql = QueryString.updateProviderName;
        PreparedStatement pstmt = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, name);
            setInt(pstmt, 2, id);
            executeUpdate(pstmt);
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }
    
    public void updateProviderTitle(int id, String title){
        logger.debug("updateProviderTitle("+id+", "+title+")");
        Connection con = null;
        String sql = QueryString.updateProviderTitle;
        PreparedStatement pstmt = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, title);
            setInt(pstmt, 2, id);
            executeUpdate(pstmt);
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }    

    public void insertCredential(int providerId, int insuranceProviderId){
        logger.debug("insertCredential("+providerId+", "+insuranceProviderId+")");
        Connection con = null;
        String sql = QueryString.insertCredential;
        PreparedStatement pstmt = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, providerId);
            setInt(pstmt, 2, insuranceProviderId);
            executeUpdate(pstmt);
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }    

    public void deleteCredential(int providerId, int insuranceProviderId){
        logger.debug("deleteCredential("+providerId+", "+insuranceProviderId+")");
        Connection con = null;
        String sql = QueryString.deleteCredential;
        PreparedStatement pstmt = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, providerId);
            setInt(pstmt, 2, insuranceProviderId);
            executeUpdate(pstmt);
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }    
    
    public void updateUser(String userId, String password, String firstName,
        String lastName, String desc, String[] roles)
    {
        logger.debug("updateUser("+userId+", "+password+", "+firstName+", "+lastName+", "+desc+", "+roles+")");
        Connection con = null;
        String sql = QueryString.updateUser;
        PreparedStatement pstmt = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, SecurityUtil.encrypt(password));
            setString(pstmt, 2, firstName);
            setString(pstmt, 3, lastName);
            setString(pstmt, 4, desc);
            setString(pstmt, 5, userId);
            executeUpdate(pstmt);
            close(pstmt);
            
            pstmt = con.prepareStatement(QueryString.deleteUserRole);
            setString(pstmt, 1, userId);
            executeUpdate(pstmt);
            close(pstmt);
            
            for (String r : roles){
            	pstmt = con.prepareStatement(QueryString.insertUserRole);
            	setString(pstmt, 1, userId);
            	setString(pstmt, 2, r);
            	executeUpdate(pstmt);
            	close(pstmt);
            }            
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void updateReferral(Referral oldRef, Referral newRef){
        Connection con = null;
        logger.debug("calling updateReferral("+oldRef+", "+newRef+")");
        try{
            con = ds.getConnection();

            String sql = null;
            PreparedStatement pstmt = null;

            if (oldRef.getRdMonth() != newRef.getRdMonth() ||
                oldRef.getRdDate() != newRef.getRdDate() ||
                oldRef.getRdYear() != newRef.getRdYear()){
                sql = "update referralStatus set createDate = ? where referralId = "+oldRef.getRefId()+
                    " and status = '"+Action.NOT_SCHEDULED+"'  and createDate between ? and ?";
                pstmt = con.prepareStatement(sql);
                setDate(pstmt, 1, newRef.getRdForDB());
                setDate(pstmt, 2, oldRef.getRdForDB());
                Date oldRefDayAfterRd = new GregorianCalendar(oldRef.getRdYear(), oldRef.getRdMonth() - 1, 
                		oldRef.getRdDate() + 1).getTime();
                setDate(pstmt, 3, oldRefDayAfterRd);
                executeUpdate(pstmt);
            }

            if (!fieldEquals(oldRef.getApartmentNumber(), newRef.getApartmentNumber())){
                sql = "update referral set apartmentNumber = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getApartmentNumber());
                executeUpdate(pstmt);
            }

            if (!fieldEquals(oldRef.getCity(), newRef.getCity())){
                sql = "update referral set city = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getCity());
                executeUpdate(pstmt);
            }

            if (!fieldEquals(oldRef.getClinic(), newRef.getClinic())){
                sql = "update referral set clinic = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getClinic());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getComments(), newRef.getComments())){
                sql = "update referral set comments = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getComments());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getCurrMed(), newRef.getCurrMed())){
                sql = "update referral set currentMedications = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getCurrMed());
                executeUpdate(pstmt);
            }
            if (oldRef.getDaysLeft() != newRef.getDaysLeft()){
                sql = "update referral set daysLeft = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setInt(pstmt, 1, newRef.getDaysLeft());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getDobForDB(), newRef.getDobForDB())){
                sql = "update referral set birthDate = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setDate(pstmt, 1, newRef.getDobForDB());
                executeUpdate(pstmt);

                if (newRef.getAgeGroup().equalsIgnoreCase("Child")){
                    sql = "update referral set isChild = 1 where referralId = "+oldRef.getRefId();
                }else{
                    sql = "update referral set isChild = 0 where referralId = "+oldRef.getRefId();
                }
                pstmt = con.prepareStatement(sql);
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getDpnForDB(), newRef.getDpnForDB())){
                sql = "update referral set phoneNumber = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getDpnForDB());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getEmail(), newRef.getEmail())){
                sql = "update referral set email = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getEmail());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getFirstName(), newRef.getFirstName())){
                sql = "update referral set firstName = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                //setString(pstmt, 1, SecurityUtil.encrypt(newRef.getFirstName()));
                setString(pstmt, 1, newRef.getFirstName());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getGender(), newRef.getGender())){
                sql = "update referral set gender = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getGender());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLastName(), newRef.getLastName())){
                sql = "update referral set lastName = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getLastName());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLastSeenForDB(), newRef.getLastSeenForDB())){
                sql = "update referral set lastSeen = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setDate(pstmt, 1, newRef.getLastSeenForDB());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLgFirstName(), newRef.getLgFirstName())){
                sql = "update referral set legalGardianFirstName = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getLgFirstName());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLgLastName(), newRef.getLgLastName())){
                sql = "update referral set legalGardianLastName = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getLgLastName());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLgMi(), newRef.getLgMi())){
                sql = "update referral set legalGardianMiddleInitial = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getLgMi());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getLgpnForDB(), newRef.getLgpnForDB())){
                sql = "update referral set legalGardianPhoneNumber = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getLgpnForDB());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getMi(), newRef.getMi())){
                sql = "update referral set middleInitial = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getMi());
                executeUpdate(pstmt);
            }
            if (oldRef.getMmNeeded() != newRef.getMmNeeded()){
                sql = "update referral set needMedicalMgntSvc = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setBoolean(pstmt, 1, newRef.getMmNeeded());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getPp(), newRef.getPp())){
                sql = "update referral set presentingProblem = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getPp());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getPrevDx(), newRef.getPrevDx())){
                sql = "update referral set previousDx = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getPrevDx());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getPrevMed(), newRef.getPrevMed())){
                sql = "update referral set previousMedications = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getPrevMed());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getPrevPsychiatrist(), newRef.getPrevPsychiatrist())){
                sql = "update referral set previousPsychiatrist = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getPrevPsychiatrist());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getSsnForDB(), newRef.getSsnForDB())){
                sql = "update referral set ssn = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, SecurityUtil.encrypt(newRef.getSsnForDB()));
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getState(), newRef.getState())){
                sql = "update referral set state = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getState());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getStreetAddress(), newRef.getStreetAddress())){
                sql = "update referral set streetAddress = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getStreetAddress());
                executeUpdate(pstmt);
            }
            if (oldRef.getTpNeeded() != newRef.getTpNeeded()){
                sql = "update referral set needTherapy = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setBoolean(pstmt, 1, newRef.getTpNeeded());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getUrgent(), newRef.getUrgent())){
                sql = "update referral set isUrgent = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getUrgent());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getZipcode(), newRef.getZipcode())){
                sql = "update referral set zipCode = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getZipcode());
                executeUpdate(pstmt);
            }
            if (oldRef.getNts() != newRef.getNts()){
                sql = "update referral set translationSvcNeeded = ? where referralId = "+oldRef.getRefId();
                pstmt = con.prepareStatement(sql);
                setBoolean(pstmt, 1, newRef.getNts());
                executeUpdate(pstmt);
            }            

            // Update Insurance
            if (!fieldEquals(oldRef.getRefDate(), newRef.getRefDate())){
                sql = "update insurance set referralDate = ? where insuranceId = "+newRef.getInsId();                
		        pstmt = con.prepareStatement(sql);
		        setDate(pstmt, 1, newRef.getRefDateForDB());
		        executeUpdate(pstmt);
            }            
            if (!fieldEquals(oldRef.getEligEffDate(), newRef.getEligEffDate())){
                sql = "update insurance set eligEffDate = ? where insuranceId = "+newRef.getInsId();                
		        pstmt = con.prepareStatement(sql);
		        setDate(pstmt, 1, newRef.getEligEffDateForDB());
		        executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getEligTermDate(), newRef.getEligTermDate())){
                sql = "update insurance set eligTermDate = ? where insuranceId = "+newRef.getInsId();                
		        pstmt = con.prepareStatement(sql);
		        setDate(pstmt, 1, newRef.getEligTermDateForDB());
		        executeUpdate(pstmt);
            }                        
            if (!fieldEquals(oldRef.getMemberId(), newRef.getMemberId())){
                sql = "update insurance set memberId = ? where insuranceId = "+newRef.getInsId();                
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getMemberId());
                executeUpdate(pstmt);
            }            
            if (!fieldEquals(oldRef.getMedicalId(), newRef.getMedicalId())){
                sql = "update insurance set medicalId = ? where referralId = "+oldRef.getRefId()+
					  " and insuranceId = "+newRef.getInsId();                
		        pstmt = con.prepareStatement(sql);
		        setString(pstmt, 1, newRef.getMedicalId());
		        executeUpdate(pstmt);
            }            
            if (oldRef.getNumAuthVisitForMD() != newRef.getNumAuthVisitForMD()){
                sql = "update insurance set numAuthVisitForMD = ? where insuranceId = "+newRef.getInsId();                
                pstmt = con.prepareStatement(sql);
                setInt(pstmt, 1, newRef.getNumAuthVisitForMD());
                executeUpdate(pstmt);
            }
            if (oldRef.getNumAuthVisitForMA() != newRef.getNumAuthVisitForMA()){
                sql = "update insurance set numAuthVisitForMA = ? where insuranceId = "+newRef.getInsId();                
                pstmt = con.prepareStatement(sql);
                setInt(pstmt, 1, newRef.getNumAuthVisitForMA());
                executeUpdate(pstmt);
            }                        
            if (!fieldEquals(oldRef.getAuthNumForMD(), newRef.getAuthNumForMD())){
                sql = "update insurance set authNumForMD = ? where insuranceId = "+oldRef.getInsId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getAuthNumForMD());
                executeUpdate(pstmt);
            }            
            if (!fieldEquals(oldRef.getAuthNumForMA(), newRef.getAuthNumForMA())){
                sql = "update insurance set authNumForMA = ? where insuranceId = "+oldRef.getInsId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getAuthNumForMA());
                executeUpdate(pstmt);
            }                        
            if (!fieldEquals(oldRef.getCopayForBD(), newRef.getCopayForBD())){
                sql = "update insurance set copay = ? where insuranceId = "+newRef.getInsId();
                pstmt = con.prepareStatement(sql);
                setBigDecimal(pstmt, 1, newRef.getCopayForBD());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getCopayParityForBD(), newRef.getCopayParityForBD())){
                sql = "update insurance set copayParity = ? where insuranceId = "+newRef.getInsId();
                pstmt = con.prepareStatement(sql);
                setBigDecimal(pstmt, 1, newRef.getCopayParityForBD());
                executeUpdate(pstmt);
            }  
            if (!fieldEquals(oldRef.getInspnForDB(), newRef.getInspnForDB())){
                sql = "update insurance set phoneNumber = ? where insuranceId = "+newRef.getInsId();
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getInspnForDB());
                executeUpdate(pstmt);
            }
            if (!fieldEquals(oldRef.getInsurance(), newRef.getInsurance())){
                sql = "update insurance set insuranceCompany = ? where insuranceId = "+newRef.getInsId();                
                pstmt = con.prepareStatement(sql);
                setString(pstmt, 1, newRef.getInsurance());
                executeUpdate(pstmt);
            }            
            if (!fieldEquals(oldRef.getMedicalIssueDateForDB(), newRef.getMedicalIssueDateForDB())){
                sql = "update insurance set medicalIssueDate = ? where insuranceId = "+newRef.getInsId();                
		        pstmt = con.prepareStatement(sql);
		        setDate(pstmt, 1, newRef.getMedicalIssueDateForDB());
		        executeUpdate(pstmt);
            }            
            
        }catch(SQLException e){
            try{
                con.rollback();
            }catch(SQLException ce){
            }
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            releaseConnection(con);
        }
    }

    public void deleteClinic(String clinic){
        Connection con = null;
        String sql = QueryString.deleteClinic;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteClinic("+clinic+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, clinic);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void deleteProvider(String provider){
        Connection con = null;
        String sql = QueryString.deleteProvider;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteProvider("+provider+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, provider);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void deleteRemoteUser(String u){
        Connection con = null;
        String sql = QueryString.deleteRemoteUser;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteRemoteUser("+u+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, u);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void deleteTrustedIP(String u){
        Connection con = null;
        String sql = QueryString.deleteTrustedIP;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteTrustedIP("+u+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, u);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void deleteUser(String id){
        Connection con = null;
        String sql = QueryString.deleteUser;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteUser("+id+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, id);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void deleteTimesheet(int id){
        Connection con = null;
        String sql = QueryString.deleteTimesheet;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteTimesheet("+id+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, id);
            executeUpdate(pstmt);
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }
    
    public void deleteInsProvider(String name){
        Connection con = null;
        String sql = QueryString.deleteInsProvider;
        PreparedStatement pstmt = null;
        logger.debug("calling deleteInsProvider("+name+")");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, name);
            executeUpdate(pstmt);

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    private boolean fieldEquals(Object oldField, Object newField){
        if (oldField == null && newField == null){
            return true;
        }
        if (oldField == null && newField != null){
            return false;
        }
        if (oldField != null && newField == null){
            return false;
        }
        if (oldField != null && newField != null){
            return oldField.equals(newField);
        }
        return false;
    }

    // CheckIn System
    public List getPendingApptEntry(String clinic, Date sd, Date ed){
        logger.debug("getPendingApptEntry("+clinic+", "+sd+", "+ed+")");
        List pendingApps = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getPendingApptForToday;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, clinic);
            setDate(pstmt, 2, sd);
            setDate(pstmt, 3, ed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ApptEntry ae = new ApptEntry();
                ae.referralId = rs.getInt(1);
                ae.apptDate = rs.getTimestamp(2);
                //ae.firstName = SecurityUtil.decrypt(rs.getString(3));
                ae.firstName = rs.getString(3);
                ae.lastName = rs.getString(4);
                ae.provider = rs.getString(5);
                pendingApps.add(ae);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return pendingApps;
    }

    public boolean verifyPatient(int refId, String lastFourSSN){
        logger.debug("verifyPatient("+refId+", "+lastFourSSN+")");
        if (lastFourSSN == null || lastFourSSN.equals(""))
            return false;

        boolean isValid = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.verifyPatient;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setInt(pstmt, 1, refId);
            setString(pstmt, 2, "%"+lastFourSSN);
            rs = pstmt.executeQuery();
            if (rs.next()){
                isValid = true;
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return isValid;
    }

    public void checkInPatient(int refId){
        logger.debug("checkInPatient("+refId+")");
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        Connection con = null;
        String sql = QueryString.updateIsCheckedIn;
        try{
            con = ds.getConnection();
            pstmt1 = con.prepareStatement(sql);
            setInt(pstmt1, 1, refId);
            executeUpdate(pstmt1);

            sql = QueryString.updateCheckInTime;
            pstmt2 = con.prepareStatement(sql);
            setInt(pstmt2, 1, refId);
            executeUpdate(pstmt2);


        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(pstmt1);
            close(pstmt2);
            releaseConnection(con);
        }
    }

    public List getAllApptEntry(String clinic, Date sd, Date ed){
        logger.debug("getAllApptEntry("+clinic+", "+sd+", "+ed+")");
        List apps = new ArrayList();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getAllApptForToday;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            setString(pstmt, 1, clinic);
            setDate(pstmt, 2, sd);
            setDate(pstmt, 3, ed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ApptEntry ae = new ApptEntry();
                ae.referralId = rs.getInt(1);
                ae.apptDate = rs.getTimestamp(2);
                //ae.firstName = SecurityUtil.decrypt(rs.getString(3));
                ae.firstName = rs.getString(3);
                ae.lastName = rs.getString(4);
                ae.provider = rs.getString(5);
                ae.isCheckedIn = rs.getBoolean(6);
                ae.checkInTime = rs.getTimestamp(7);
                apps.add(ae);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return apps;
    }

    public void insertProvider(String pname, String ptitle){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertProvider);
            setString(pstmt, 1, pname);
            setString(pstmt, 2, ptitle);
            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void insertRemoteUser(String u){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertRemoteUser);
            setString(pstmt, 1, u);
            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public void insertTrustedIP(String u){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.insertTrustedIP);
            setString(pstmt, 1, u);
            pstmt.executeUpdate();

        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }

    public boolean isNewPatient(int refId, Date createDate){
        boolean isNewPatient = false;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.isNewPatient);
            setInt(pstmt, 1, refId);
            setDate(pstmt, 2, createDate);
            rs = pstmt.executeQuery();
            if (rs.next()){
                isNewPatient = false;
            }else{
                isNewPatient = true;
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return isNewPatient;
    }

    public List getNoShowsSummary(Date esd, Date eed){
        List entries = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ClinicAndNumEntry entry = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getNoShowsSummary);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                entry = new ClinicAndNumEntry();
                entry.clinic = rs.getString(1);
                entry.numEntry = rs.getInt(2);
                entries.add(entry);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return entries;
    }

    public List getNoShowsDetail(Date esd, Date eed){
        List entries = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NoShowsEntry entry = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getNoShowsDetail);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                entry = new NoShowsEntry();
                //r.lastname, r.firstname, a.appointmentDate, a.clinicName, a.provider
                entry.lastName = rs.getString(1);
                //entry.firstName = SecurityUtil.decrypt(rs.getString(2));
                entry.firstName = rs.getString(2);
                entry.apptDate = rs.getTimestamp(3);
                entry.clinic = rs.getString(4);
                entry.provider = rs.getString(5);
                entries.add(entry);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return entries;
    }

    public List getCanceledApptSummary(Date esd, Date eed){
        List entries = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ClinicAndNumEntry entry = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getCanceledApptSummary);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                entry = new ClinicAndNumEntry();
                entry.clinic = rs.getString(1);
                entry.numEntry = rs.getInt(2);
                entries.add(entry);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return entries;
    }

    public List getCanceledApptDetail(Date esd, Date eed){
        List entries = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NoShowsEntry entry = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getCanceledApptDetail);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            rs = pstmt.executeQuery();
            while (rs.next()){
                entry = new NoShowsEntry();
                //r.lastname, r.firstname, a.appointmentDate, a.clinicName, a.provider
                entry.lastName = rs.getString(1);
                //entry.firstName = SecurityUtil.decrypt(rs.getString(2));
                entry.firstName = rs.getString(2);
                entry.apptDate = rs.getTimestamp(3);
                entry.clinic = rs.getString(4);
                entry.provider = rs.getString(5);
                entries.add(entry);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return entries;
    }
    
    public List getReferralForRefCountReport(Date esd, Date eed, String ins){
        List refs = new ArrayList();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Referral ref = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getReferralForRefCountReport);
            setDate(pstmt, 1, esd);
            setDate(pstmt, 2, eed);
            setString(pstmt, 3, ins);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ref = new Referral();
                ref.setRefId(rs.getInt(1));
                ref.setDob(rs.getTimestamp(2));
                ref.setCreateDate(rs.getTimestamp(3));
                refs.add(ref);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return refs;    	
    }
    
    public boolean hasNoShowAfter(int refId, Date createDate){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.hasNoShowAfter);
            setInt(pstmt, 1, refId);
            setDate(pstmt, 2, createDate);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }    	
    }

    public void getTotalHours(Map entriesMap, Date date, String type)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String staff = null;
        String clinic = null;
        String key = null;
        BigDecimal bd = null;
        ProductivityReportEntry entry = null;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(QueryString.getTimesheetForDate);
            setDate(pstmt, 1, date);
            setString(pstmt, 2, type); 
            
            rs = executeQuery(pstmt);
            while (rs.next()){
            	staff = rs.getString(1);
            	clinic = rs.getString(2);
            	key = getKey(staff, clinic);
            	bd = rs.getBigDecimal(3);
            	entry = (ProductivityReportEntry)entriesMap.get(key);
            	if (entry == null){
            		entry = new ProductivityReportEntry();
            		entry.setStaff(staff);
            		entry.setClinic(clinic);
            		entry.setDate(date);
            		entriesMap.put(key, entry);
            	}
            	if (type == Action.BILLED){
            		entry.addHoursBilled(bd);
            	}else{
            		entry.addHoursWorked(bd);
            	}
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }
    }
    
    public String getKey(String staff, String clinic){
    	return staff + "_" + clinic;
    }
    
    public void getNumAppts(Map entriesMap, Date apptDate, String status){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String staff = null;
        String clinic = null;
        String key = null;
        int numAppts = 0;
        ProductivityReportEntry entry = null;
        try{
            con = ds.getConnection();    
            pstmt = con.prepareStatement(QueryString.getNumApptsByDateByStatus);
            setDate(pstmt, 1, apptDate);
            setDate(pstmt, 2, Util.getNextDate(apptDate));
            setString(pstmt, 3, status);             
            rs = executeQuery(pstmt);
            
            while (rs.next()){
            	staff = rs.getString(1);
            	clinic = rs.getString(2);
            	numAppts = rs.getInt(3);
            	key = getKey(staff, clinic);
            	entry = (ProductivityReportEntry)entriesMap.get(key);
            	if (entry == null){
            		entry = new ProductivityReportEntry();
            		entry.setStaff(staff);
            		entry.setClinic(clinic);
            		entry.setDate(apptDate);
            		entriesMap.put(key, entry);
            	}
        		if (status.equals(Action.SCHEDULED)){
        			entry.addNumScheduled(numAppts);
        		}else if (status.equals(Action.SEEN)){
        			entry.addNumSeen(numAppts);
        		}            	
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }    	
    }
    
    public void getHoursScheduled(Map entriesMap , Date apptDate)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String provider = null;
        String clinic = null;
        BigDecimal hrsScheduled = null;
        ProductivityReportEntry entry = null;
        String key = null;
        try{
            con = ds.getConnection();            
            pstmt = con.prepareStatement(QueryString.getHoursScheduledByDate);
            setDate(pstmt, 1, apptDate);
            setDate(pstmt, 2, Util.getNextDate(apptDate));             
            rs = executeQuery(pstmt);
            
            while (rs.next()){
            	provider = rs.getString(1);
            	clinic = rs.getString(2);
            	key = getKey(provider, clinic);
            	entry = (ProductivityReportEntry)entriesMap.get(key);
            	if (entry == null){
            		entry = new ProductivityReportEntry();
            		entry.setStaff(provider);
            		entry.setClinic(clinic);
            		entry.setDate(apptDate);
            		entriesMap.put(key, entry);
            	}
            	hrsScheduled = rs.getBigDecimal(3);
            	entry.addHoursScheduled(hrsScheduled);
            }
        }catch(SQLException e){
            logger.debug("Error: problem executing query: "+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }finally{
            close(pstmt);
            releaseConnection(con);
        }    	
    }
    
    public List getProductivityReportEntries(Date date){
    	Map entriesMap = new HashMap();
    	getTotalHours(entriesMap, date, Action.WORKED);
    	getTotalHours(entriesMap, date, Action.BILLED);
    	getNumAppts(entriesMap, date, Action.SCHEDULED);
    	getNumAppts(entriesMap, date, Action.SEEN);
    	getHoursScheduled(entriesMap, date);
    	return getSortedList(entriesMap);
    }
    
    public List getSortedList(Map entriesMap){
    	Collection vals = entriesMap.values();
    	List list = new ArrayList(vals);
    	Collections.sort(list);
    	return list;
    }
    
    /*
    public ProductivityReportEntry getProductivityReportEntry(
    		Date date, String provider)
    {
    	ProductivityReportEntry entry = new ProductivityReportEntry();
    	int numScheduled = this.getNumAppts(date, provider, Action.SCHEDULED);
    	int numSeen = this.getNumAppts(date, provider, Action.SEEN);
    	BigDecimal hrsWorked = this.getTotalHoursByStaff(date, provider, Action.WORKED);
    	BigDecimal hrsBilled = this.getTotalHoursByStaff(date, provider, Action.BILLED);
    	BigDecimal hrsScheduled = this.getHoursScheduledByDateByProvider(date, provider);
    	entry.setDate(date);
    	entry.setStaff(provider);    	
    	entry.setHoursBilled(hrsBilled);
    	entry.setHoursWorked(hrsWorked);
    	entry.setHoursScheduled(hrsScheduled);
    	entry.setNumScheduled(numScheduled);
    	entry.setNumSeen(numSeen);
    	return entry;
    }
    */

    public List<String> getAllRoleNames(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getAllRoleNames;
        List<String> retVal = new ArrayList<String>();
        logger.debug("calling getAllRoleNames()");
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                retVal.add(rs.getString(1));
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;
    }
    
    public List<String> getCredentialsForProvider(int providerId){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getCredentialForProvider;
        List retVal = new ArrayList<String>();
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, providerId);
            rs = pstmt.executeQuery();
            while (rs.next()){
                retVal.add(rs.getString(1));
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return retVal;    	
    }
    
    public int getProviderIdByName(String pname){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        String sql = QueryString.getProviderIdByName;
        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pname);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql);
            e.printStackTrace();
        }finally{
            close(rs);
            close(pstmt);
            releaseConnection(con);
        }
        return -1;    	    	
    }
    
    public Provider getProviderById(int providerId){
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        Connection con = null;
        String sql1 = QueryString.getProviderById;
        String sql2 = QueryString.getCredentialForProvider;
        Provider p = null;
        try{
            con = ds.getConnection();
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setInt(1, providerId);
            rs1 = pstmt1.executeQuery();            
            if (rs1.next()){
            	p = new Provider();
            	p.setProviderId(rs1.getInt(1));
            	p.setName(rs1.getString(2));
            	p.setTitle(rs1.getString(3));

            	// get credential
            	pstmt2 = con.prepareStatement(sql2);
            	pstmt2.setInt(1, providerId);
            	rs2 = pstmt2.executeQuery();
            	List<InsuranceProvider> credentials = new ArrayList<InsuranceProvider>();
            	while (rs2.next()){
            		InsuranceProvider ip = new InsuranceProvider();
            		ip.id = rs2.getInt(1);
            		ip.name = rs2.getString(2);
            		credentials.add(ip);
            	}
            	p.setCredentials(credentials);
            }
        }catch(SQLException e){
            System.out.println("Error: problem executing: "+sql1);
            e.printStackTrace();
        }finally{
            close(rs1);
            close(rs2);
            close(pstmt1);
            close(pstmt2);
            releaseConnection(con);
        }
        return p;    	    	
    }
}
