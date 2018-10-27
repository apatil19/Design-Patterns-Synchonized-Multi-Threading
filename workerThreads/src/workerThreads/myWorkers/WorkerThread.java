package workerThreads.myWorkers;

import workerThreads.util.FileProcessor;
import workerThreads.util.IsPrime;
import workerThreads.util.MyLogger;
import workerThreads.util.Results;

public class WorkerThread implements Runnable{

	
	private FileProcessor fp=null;
	private Results res= null;
	private IsPrime prime= null;
	
	public WorkerThread(FileProcessor fpIn, Results resIn, IsPrime primeIn ) 
	{
		MyLogger.writeOuput("Constructor of WorkerThread is called", MyLogger.DebugLevel.CONSTRUCTOR);
		
		this.fp= fpIn; 
		this.res=resIn;
		this.prime= primeIn;
		
	}

	@Override
	public void run() 
	{
		try
		{		
			MyLogger.writeOuput("run method of "+Thread.currentThread().getName()+" started",MyLogger.DebugLevel.RUN_METHOD);
			String currentline;
			while ((currentline = fp.readInputLine()) != null) 
			{
				int number = Integer.parseInt(currentline);
				if(prime.findPrime(number))
				{
					res.storeFinalResult(currentline);
				}
				try {
					Thread.sleep((int) (Math.random() * 200));
				} catch (InterruptedException e) {
					MyLogger.writeOuput("Exception occured while making thread sleep \n"+e.toString(), MyLogger.DebugLevel.NONE);
					System.exit(1);
				}
				
			}
		}
		catch(Exception e)
		{
			MyLogger.writeOuput("Exception occured in run Method \n"+e.toString(), MyLogger.DebugLevel.NONE);
			System.exit(1);
		}
		finally {}
	}

}