package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;




@Component("MoneyLaunderingService")
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {
	/*
 	List<SuspectAccount> sospechosos =  new ArrayList<SuspectAccount>();	        

		
	 public MoneyLaunderingServiceStub (){
	        SuspectAccount sa1 = new SuspectAccount();
	        sa1.setAccountId("juan10");
	        sa1.setAmountOfSmallTransactions(10000);
	        sospechosos.add(sa1);
	        SuspectAccount sa2 = new SuspectAccount();
	        sa2.setAccountId("david10");
	        sa2.setAmountOfSmallTransactions(2030);
	        sospechosos.add(sa2);
	        SuspectAccount sa3 = new SuspectAccount();
	        sa3.setAccountId("navarro10");
	        sa3.setAmountOfSmallTransactions(2144);
	        sospechosos.add(sa3);
	        SuspectAccount sa4 = new SuspectAccount();
	        sa4.setAccountId("jimenez10");
	        sa4.setAmountOfSmallTransactions(32140);
	        sospechosos.add(sa4);

	    }
	*/
	
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
