package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;




@Component("MoneyLaunderingService")
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {
	
	
	List<SuspectAccount> sospechosos =  new ArrayList<SuspectAccount>();
	
    @Override
    public void updateAccountStatus(SuspectAccount suspectAccount) {
    	synchronized (sospechosos) {
        	SuspectAccount sa = null;
            for (SuspectAccount p:sospechosos){
                if(p.accountId.equals(suspectAccount.accountId)){
                	sospechosos.remove(p);
                	sospechosos.add(suspectAccount);
                }
            }
            
           
}
    }

    @Override
    public SuspectAccount getAccountStatus(String accountId) {
    	
            synchronized (sospechosos) {
            	SuspectAccount sa = null;
                for (SuspectAccount p:sospechosos){
                    if(p.accountId.equals(accountId)){
                        sa=p;
                    }
                }
               
                return sa;
    }
    }
            

    @Override
    public List<SuspectAccount> getSuspectAccounts() {
        return sospechosos;
    }
}
