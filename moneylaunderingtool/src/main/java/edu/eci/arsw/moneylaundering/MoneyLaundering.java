package edu.eci.arsw.moneylaundering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyLaundering 
{	
    public static TransactionAnalyzer transactionAnalyzer;
   
    public static Object threadMonitor = new Object();

    public static AtomicInteger amountOfFilesProcessed;
	private int amountOfFilesTotal = getTransactionFileList().size();
    static int procesos=5;
    public static boolean pause = false;
    static int archivos;
    public MoneyLaundering()
   
    {
        transactionAnalyzer = new TransactionAnalyzer();
       
        amountOfFilesProcessed = new AtomicInteger();
    }

    public void processTransactionData()
    
    {
        amountOfFilesProcessed.set(0);
        List<File> transactionFiles = getTransactionFileList();
        archivos =transactionFiles.size();
        int inicio =0;
        int parte = archivos/procesos;
        int fin=0;
        
        
        MoneyLaunderingThread hilos[] = new MoneyLaunderingThread[procesos];
        for (int i=0;i<procesos;i++) {
            fin= inicio+parte;
            
             //Si los hilos no se pueden dividir equitativamente 
            if (procesos==i+1 && fin<archivos) {
            	fin=archivos;
            }
            
            //System.out.println("inicio:"+inicio);

            //System.out.println("fin:"+fin);
            List<File> temporal = new ArrayList<>();
            for (int k=inicio;k<fin;k++) {
            	temporal.add(transactionFiles.get(k));
            	//System.out.println(k);
            }

            hilos[i]=new MoneyLaunderingThread(temporal);
            hilos[i].start();
            inicio=fin;
        }
        //List<File> transactionFiles = getTransactionFileList();

      
    }

    public List<String> getOffendingAccounts()
    {
        return transactionAnalyzer.listOffendingAccounts();
    }

    private static List<File> getTransactionFileList()
    {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }

    public static void main(String[] args)
    {
        MoneyLaundering moneyLaundering = new MoneyLaundering();
        
        moneyLaundering.processTransactionData();

           
        //Thread processingThread = new Thread(() -> moneyLaundering.processTransactionData());
        //processingThread.start();
        
                
        
        
        
        while(amountOfFilesProcessed.get()!=archivos)
        {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if(line.contains("exit")) {            	            
                break;
            }
            if(line.isEmpty()){
                if(pause==false){
                    System.out.println("Pausado");
                    String message = "Processed %d out of %d files.\nFound %d suspect accounts:\n%s";
                    List<String> offendingAccounts =new CopyOnWriteArrayList<>();
                    offendingAccounts = moneyLaundering.getOffendingAccounts();
                    String suspectAccounts = offendingAccounts.stream().reduce("", (s1, s2)-> s1 + "\n"+s2);            
                    message = String.format(message, moneyLaundering.amountOfFilesProcessed.get(), moneyLaundering.amountOfFilesTotal, offendingAccounts.size(), suspectAccounts);
                    System.out.println(message);
                    pause=true;
                }
                else if(pause==true){
                    System.out.println("Corriendo");
                    pause=false;
                    synchronized (threadMonitor) {
                        
                        threadMonitor.notifyAll();
                    }
                }
            }
            
        }

    }


}
