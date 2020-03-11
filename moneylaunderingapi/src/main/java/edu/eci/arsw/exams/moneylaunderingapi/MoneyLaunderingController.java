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
    public ResponseEntity<?> offendingAccountsGET() {
        
        try {
            List<SuspectAccount> data = moneyLaunderingService.getSuspectAccounts();
            return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
        } catch (Exception  ex) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( value = "/fraud-bank-accounts",method = RequestMethod.POST)
    public ResponseEntity<?> offendingAccountsPOST(@RequestBody SuspectAccount cuenta) {
    	 try {
             moneyLaunderingService.postSuspectAccounts(cuenta);
             List<SuspectAccount> data = moneyLaunderingService.getSuspectAccounts();
             return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
         } catch (Exception  ex) {
             Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
             return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
         }
       
    }
    
    
    @RequestMapping( value = "/fraud-bank-account/{accountId}",method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("accountId") String accountId) {
         try {
            SuspectAccount data = moneyLaunderingService.getAccountStatus(accountId);
            return new ResponseEntity<>(data,HttpStatus.ACCEPTED);
        } catch (Exception  ex) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }   
            	  
    }
    
    @RequestMapping( value = "/fraud-bank-account/{accountId}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@RequestBody SuspectAccount suspectAccount,@PathVariable("accountId") String accountId) {
    	try {
            moneyLaunderingService.updateAccountStatus(accountId,suspectAccount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(MoneyLaunderingController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);            
        }  	
           
            	  
    }
    
    
    
 
}
