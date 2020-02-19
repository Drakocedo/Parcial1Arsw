package edu.eci.arsw.exams.moneylaunderingapi;


import edu.eci.arsw.exams.moneylaunderingapi.model.SuspectAccount;
import edu.eci.arsw.exams.moneylaunderingapi.service.MoneyLaunderingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;




import java.util.List;

@RestController
public class MoneyLaunderingController
{
	@Autowired
    @Qualifier("MoneyLaunderingService")	
    MoneyLaunderingService moneyLaunderingService;

    @RequestMapping( value = "/fraud-bank-accounts",method = RequestMethod.GET)
    public List<SuspectAccount> offendingAccountsGET() {
        return moneyLaunderingService.getSuspectAccounts();
    }

    @RequestMapping( value = "/fraud-bank-accounts",method = RequestMethod.POST)
    public List<SuspectAccount> offendingAccountsPOST() {
        return moneyLaunderingService.getSuspectAccounts();
    }
    
    
    @RequestMapping( value = "/fraud-bank-account/{accountId}",method = RequestMethod.GET)
    public SuspectAccount getAccount(@PathVariable("accountId") String accountId) {
          	
        return moneyLaunderingService.getAccountStatus(accountId);
           
            	  
    }
    
    @RequestMapping( value = "/fraud-bank-account/{accountId}",method = RequestMethod.PUT)
    public void updateAccount(@RequestBody SuspectAccount suspectAccount) {
          	
        moneyLaunderingService.updateAccountStatus(suspectAccount);
           
            	  
    }
    
    
    
 
}
