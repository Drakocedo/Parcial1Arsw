package edu.eci.arsw.moneylaundering;

import java.io.File;
import java.util.List;

public class MoneyLaunderingThread extends Thread {
	
	private TransactionReader transactionReader;
    private List<File> transactionFiles;
	
    public  MoneyLaunderingThread(List<File> file) {
    	transactionFiles=file;
    	transactionReader = new TransactionReader();
    }
    
	@Override
    public void run() {
		System.out.println("calculando");
		  for(File transactionFile : transactionFiles)
	        {            
	            List<Transaction> transactions = transactionReader.readTransactionsFromFile(transactionFile);
	            for(Transaction transaction : transactions)
	            {
	            	MoneyLaundering.transactionAnalyzer.addTransaction(transaction);
	            }
	            MoneyLaundering.amountOfFilesProcessed.incrementAndGet();
	            
	        }
	}

}
