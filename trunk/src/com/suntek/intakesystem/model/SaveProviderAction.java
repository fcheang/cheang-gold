package com.suntek.intakesystem.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.suntek.common.persistence.Action;
import com.suntek.common.persistence.InsuranceProvider;
import com.suntek.common.persistence.Model;
import com.suntek.common.persistence.Provider;

public class SaveProviderAction extends Action {

	public SaveProviderAction(Model m){
		super(m);
	}
	
	@Override
	public String perform(HttpServletRequest req) {
        HttpSession session = req.getSession(false);		
		boolean success = true; 
		String name = null;
		try{
			int id = Integer.parseInt(req.getParameter("providerId"));
			name = req.getParameter("providerName");
			String title = req.getParameter("providerTitle");
			
			Provider oldP = model.getProviderById(id);
			
			if (!isSameValue(name, oldP.getName())){
				// check FK constraint
				if (model.getApptCountForProvider(oldP.getName()) > 0){
					success = false;
	        		String errMsg = "Updating provider name is not allowed because provider has appointments scheduled!";
	                session.setAttribute("message", "<font color=\"red\"><b>Problem modifying Provider <em>"+
	                		name+"</em></b></font>");
	                session.setAttribute("error", errMsg);
	                return super.errorPage;
				}
				model.updateProviderName(id, name);
			}
			if (!isSameValue(title, oldP.getTitle())){
				model.updateProviderTitle(id, title);
			}
			List<InsuranceProvider> oldCredentials = oldP.getCredentials();
			List<InsuranceProvider> allCredentials = model.getAllCredentials();
			for (InsuranceProvider c : allCredentials){
				String checked = req.getParameter(c.name);
				if (checked != null){
					if (!oldCredentials.remove(c)){
						// does not exist, insert new credential
						model.insertCredential(id, c.id);
					}
				}
			}
			// checked credentials are removed, what's left is he
			// unchecked credentials, remove them
			for (InsuranceProvider c : oldCredentials){
				model.deleteCredential(id, c.id);
			}
		}catch(Exception e){
			success = false;
			session.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		
		if (success){
			session.setAttribute("message", "Provider "+name+" was updated.");
			return super.successPage;
		}else{
			session.setAttribute("message", "Problem updating provider "+name+"!");
			return super.errorPage;
		}
	}
	
	private boolean isSameValue(String v1, String v2){
		if (v1 == null && v2 == null){
			return true;
		}
		if (v1 != null && v2 == null){
			return false;
		}
		if (v1 == null && v2 != null){
			return false;
		}
		if (v1 != null && v2 != null){
			if (v1.equals(v2)){
				return true;
			}
		}
		return false;
	}

}
