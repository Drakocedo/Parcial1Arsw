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
	            	synchronized (MoneyLaundering.threadMonitor) {
	                    if (MoneyLaundering.pause) {
	                        try {
	                            //Pausa los hilos que estan en el monitor.
	                            MoneyLaundering.threadMonitor.wait();
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            	MoneyLaundering.transactionAnalyzer.addTransaction(transaction);
	            }
	            MoneyLaundering.amountOfFilesProcessed.incrementAndGet();
	            
	        }
	}

}
