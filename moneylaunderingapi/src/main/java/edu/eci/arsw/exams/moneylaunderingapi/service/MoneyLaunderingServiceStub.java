package edu.eci.arsw.exams.moneylaunderingapi.service;

import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;




@Component("MoneyLaunderingService")
public class MoneyLaunderingServiceStub implements MoneyLaunderingService {
	
 	 
	List<SuspectAccount> sospechosos;
		
	 public MoneyLaunderingServiceStub (){
	 		sospechosos = new CopyOnWriteArrayList<>();
	        SuspectAccount sa1 = new SuspectAccount();
	        sa1.setAccountId("77");
	        sa1.setAmountOfSmallTransactions(10004);
	        sospechosos.add(sa1);
	        SuspectAccount sa2 = new SuspectAccount();
	        sa2.setAccountId("774");
	        sa2.setAmountOfSmallTransactions(20530);
	        sospechosos.add(sa2);
	        SuspectAccount sa3 = new SuspectAccount();
	        sa3.setAccountId("65");
	        sa3.setAmountOfSmallTransactions(21464);
	        sospechosos.add(sa3);
	        SuspectAccount sa4 = new SuspectAccount();
	        sa4.setAccountId("13");
	        sa4.setAmountOfSmallTransactions(32180);
	        sospechosos.add(sa4);

	    }
	
	
    @Override
    public void updateAccountStatus(String accountId, SuspectAccount suspectAccount) {
   
        	
            for (SuspectAccount p:sospechosos){
                if(p.getAccountId().equals(accountId)){
                	sospechosos.remove(p);
                	sospechosos.add(suspectAccount);
                }
            }
            
           
    }
    

    @Override
    public SuspectAccount getAccountStatus(String accountId) {
    	
           
            	SuspectAccount sa = null;
                for (SuspectAccount p:sospechosos){
                    if(p.accountId.equals(accountId)){
                        sa=p;
                    }
                }
               
                return sa;
    
    }
            

    @Override
    public List<SuspectAccount> getSuspectAccounts() {
        return sospechosos;
    }


	


	@Override
	public void postSuspectAccounts(SuspectAccount cuenta) {
   	 	 boolean flag =true;

		 for (SuspectAccount p:sospechosos){
             if(p.getAccountId().equals(cuenta.getAccountId())){
            	 p.setAmountOfSmallTransactions(p.getAmountOfSmallTransactions()+1);
            	 flag = false;
            	
             }
            	 
        }
        if (flag) {
        	cuenta.setAmountOfSmallTransactions(cuenta.getAmountOfSmallTransactions()+1);
        	sospechosos.add(cuenta);
        }
        
	}
}
