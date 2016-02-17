//package peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client2 implements Runnable {
	static String key;
	static String value;
	static int index;
	ServerSocket serverSocket = null;
	Socket serverConnectionSocket = null;
	static Socket getSocket;
	static Socket getSocket1;
	static Socket getSocket2;
	static Socket getSocket3;
	static Socket getSocket4;
	static Socket getSocket5;
	static Socket getSocket6;
	static Socket getSocket7;
	static ObjectOutputStream obj;
	static ObjectOutputStream obj1;
	static ObjectOutputStream obj2;
	static ObjectOutputStream obj3;
	static ObjectOutputStream obj4;
	static ObjectOutputStream obj5;
	static ObjectOutputStream obj6;
	static ObjectOutputStream obj7;
	public Client2(int Port_no){
		try {
			serverSocket = new ServerSocket(Port_no);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The host port number is -:"+serverSocket.getLocalPort());
		new Thread(this).start();
	}
	
	
	public static void main(String args[]) throws Exception{
		
		ReadPropertyFile inp = new ReadPropertyFile();	
		new Client2(Integer.parseInt(inp.getServerPort2()));	
        SocketInit();
		chooseOption();
		
	}
	
	//To get the key and value from user when we select the put operation
	
	public static void getKeyValue(){
//		String key,value;
		System.out.println("Enter the key-: ");
		Scanner sc = new Scanner(System.in);
		key = sc.nextLine();
		System.out.println("Enter the respective value of the key-: ");
		Scanner sc1 = new Scanner(System.in);
		value = sc1.nextLine();		
	}
	
	/* This method SocketInit will set up the connections with all the eight servers  
	 * It will halt the execution and we have to start all the eight server and then 
	 * we press the return key to set up all the connections*/
	
	public static void SocketInit() throws Exception{
		ReadPropertyFile inp = new ReadPropertyFile();
		getSocket2 = new Socket(inp.getHost2(),Integer.parseInt(inp.getServerPort2()));
		System.out.println("After starting all the servers in different terminal press enter-:");
		Scanner halt = new Scanner(System.in);
		halt.nextLine();
		 getSocket1 = new Socket("localhost",8081);
		 getSocket = new Socket("localhost",8080);
		 getSocket3= new Socket("localhost",8083);
		 getSocket4= new Socket("localhost",8084);
		 getSocket5= new Socket("localhost",8085);
		 getSocket6= new Socket("localhost",8086);
		getSocket7= new Socket("localhost",8087);
		obj = new ObjectOutputStream(getSocket.getOutputStream());
		obj1 = new ObjectOutputStream(getSocket1.getOutputStream());
		obj2 = new ObjectOutputStream(getSocket2.getOutputStream());
		obj3 = new ObjectOutputStream(getSocket3.getOutputStream());
		obj4 = new ObjectOutputStream(getSocket4.getOutputStream());
		obj5 = new ObjectOutputStream(getSocket5.getOutputStream());
		obj6 = new ObjectOutputStream(getSocket6.getOutputStream());
		obj7 = new ObjectOutputStream(getSocket7.getOutputStream());
		
		Socket[] connections = {getSocket,getSocket1,getSocket2,getSocket3,getSocket4,getSocket5,getSocket6,getSocket7};
	}
	
	
	/* The chooseOption method implements switch to select either of 4 option
	 * 1- To perform  put operation
	 * 2- To perform get operation
	 * 3- To perform del operation
	 * 4- To perform the evaluation of put returns time taken to complete 100k operations  
	 * 5- To perform the evaluation of get returns time taken to complete 100k operations 
	 * 6- To perform the evaluation of del returns time taken to complete 100k operations  */
	
	public static void chooseOption() throws UnknownHostException, IOException, ClassNotFoundException{
	
		while(true){
		Socket delSocket;
		int choice = 0;
		
		System.out.println("Enter the respective value between 1-6");
		System.out.println("1. For performing put function..........");
		System.out.println("2. For performing get function..........");
		System.out.println("3. For performing del function..........");
//		System.out.println("4. evaluation of 100k put operation (Key is in range 300000-399999)");
//		System.out.println("5. evaluation of 100k get operation (Key is in range 300000-399999) (Make sure to perform 100K put operation first)");
//		System.out.println("6. evaluation of 100k del operation (Key is in range 300000-399999) (Make sure to perform 100K put operation first)");
		Scanner sc = new Scanner(System.in);
		try{
			choice = sc.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("You should enter the integer value");
			chooseOption();
		}
		System.out.println("You have selected : "+choice);
		
		switch(choice){
		
		case 1: 
//			
			
				
			
			int getServerIndex = putImplement();
			String sendInfo;
			sendInfo= key+":"+value;
			put(getServerIndex,sendInfo);
			break;
			
			
			
		
		case 2 : 	
			String searchName;
			Scanner sc2= new Scanner(System.in);
			System.out.println("Enter the key name you want to get value -:");
			searchName = sc2.nextLine();
			int getIndex = hashFunction(searchName);
			get(getIndex,searchName);
			break;
			
			
			
			
		case 3:
			Scanner deleteKey = new Scanner(System.in);
			System.out.println("Enter the key name you want ot delete-:");
			String name = deleteKey.nextLine();
			int delIndex = hashFunction(name);
			del(delIndex,name);
			break;
			
			
		case 44:
			String last = maulik("jay khodiyar");
			long startTime = System.currentTimeMillis();
			for (int i = 300000; i < 400000; i++) {
				
			
			String eval;
			int mike = hashFunction(Integer.toString(i));
//			String last = "jaybhavani";
			eval= i+":"+last;
			put(mike,eval);  
				
			}
			long stopTime =System.currentTimeMillis();
			long totalTime = stopTime-startTime;
			System.out.println("The total time to perform 100k put operations is -:"+totalTime);
			break;
			
		case 35:
			
			long startTime1 = System.currentTimeMillis();
			for (int j = 300000; j < 400000; j++) {
				String getValue;
				int indexEval = hashFunction(Integer.toString(j));
				searchName = Integer.toString(j);
				if(indexEval == 0)
				{	
				    obj.writeObject("get");
					obj.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}
				
				else if(indexEval == 1)
				{			
					obj1.writeObject("get");
					obj1.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket1.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				
				else if(indexEval == 2)
				{			
					obj2.writeObject("get");
					obj2.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket2.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				
				else if(indexEval == 3)
				{			
					obj3.writeObject("get");
					obj3.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket3.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				
				else if(indexEval == 4)
				{			
					obj4.writeObject("get");
					obj4.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket4.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				
				else if(indexEval == 5)
				{			
					obj5.writeObject("get");
					obj5.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket5.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				
				else if(indexEval == 6)
				{			
					obj6.writeObject("get");
					obj6.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket6.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	

				else if(indexEval == 7)
				{			
					obj7.writeObject("get");
					obj7.writeObject(searchName);
					ObjectInputStream oin1 = new ObjectInputStream(getSocket7.getInputStream());
					Object objValue = oin1.readObject();
					String serverValue = (String) objValue;
//					System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
					  
				}	
				else{
					System.out.println("The value entered is invalid");
					
				}
				
			}
			
			long stopTime1 =System.currentTimeMillis();
			long totalTime1 = stopTime1-startTime1;
			System.out.println("The total time to perform 100K get operations is -:"+totalTime1);
			break;
			
			
		case 64:
			long startTime2 = System.currentTimeMillis();
			for (int h = 300000; h < 400000; h++) {
				String getValue;
				int indexEval = hashFunction(Integer.toString(h));
				getValue = Integer.toString(h);
				del(indexEval,getValue);
				
			}
			long stopTime2 =System.currentTimeMillis();
			long totalTime2 = stopTime2-startTime2;
			
			
			System.out.println("The total time to perform 100k del operations is -:"+totalTime2);
			break;
			
			
		}
		}	
	}
	
	/* The put method takes the Index and the key value pairs that is to be sent to the respective server that 
	 * is decided by the index number that we get after hashing the function.
	 * It returns true if we are able to store the key-value pairs and false if we can'nt  */
	
	public static void put(int getServerIndex,String sendInfo) throws IOException, ClassNotFoundException
	{
		if(getServerIndex ==0)
		{
//			System.out.println("Connecting to Server-1 for storing the info-:");
			obj.writeObject("put");
			obj.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==1)
		{
//			System.out.println("Connecting to Server-2 for storing the info-:");
			obj1.writeObject("put");
			obj1.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket1.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==2)
		{
//			System.out.println("Connecting to Server-3 for storing the info-:");
			obj2.writeObject("put");
			obj2.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket2.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==3)
		{
//			System.out.println("Connecting to Server-4 for storing the info-:");
			obj3.writeObject("put");
			obj3.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket3.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==4)
		{
//			System.out.println("Connecting to Server-5 for storing the info-:");
			obj4.writeObject("put");
			obj4.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket4.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==5)
		{
//			System.out.println("Connecting to Server-6 for storing the info-:");
			obj5.writeObject("put");
			obj5.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket5.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==6)
		{
//			System.out.println("Connecting to Server-7 for storing the info-:");
			obj6.writeObject("put");
			obj6.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket6.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else if(getServerIndex ==7)
		{
//			System.out.println("Connecting to Server-7 for storing the info-:");
			obj7.writeObject("put");
			obj7.writeObject(sendInfo);
			ObjectInputStream oin = new ObjectInputStream(getSocket7.getInputStream());
		    Object flag = oin.readObject();
			String inp = (String) flag;
			System.out.println(flag);
			  
		}
		else{
			System.out.println("The value entered is invalid");
		}
		
	}
	
	/* The get method takes the index value that we get from hashfunction to connect to appropriate server to get
	 * value for respective key and the name of the key for which we need the value. It will connect to the server
	 * and will print the respective value of the key */
	
	public static void get(int getIndex, String searchName) throws IOException, ClassNotFoundException{
		if(getIndex == 0)
		{	
		    obj.writeObject("get");
			obj.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}
		
		else if(getIndex == 1)
		{			
			obj1.writeObject("get");
			obj1.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket1.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		
		else if(getIndex == 2)
		{			
			obj2.writeObject("get");
			obj2.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket2.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		
		else if(getIndex == 3)
		{			
			obj3.writeObject("get");
			obj3.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket3.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		
		else if(getIndex == 4)
		{			
			obj4.writeObject("get");
			obj4.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket4.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		
		else if(getIndex == 5)
		{			
			obj5.writeObject("get");
			obj5.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket5.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		
		else if(getIndex == 6)
		{			
			obj6.writeObject("get");
			obj6.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket6.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	

		else if(getIndex == 7)
		{			
			obj7.writeObject("get");
			obj7.writeObject(searchName);
			ObjectInputStream oin1 = new ObjectInputStream(getSocket7.getInputStream());
			Object objValue = oin1.readObject();
			String serverValue = (String) objValue;
			System.out.println("Key-:" + searchName + "  Value-: " +serverValue );
			  
		}	
		else{
			System.out.println("The value entered is invalid");
			
		}
	}
	
	
	/* This method performs the delete operation it will ask for the key whose respective value is to be deleted
	 * and then it will delete the key value pair from the hash table by connecting to the appropriate server  */
	
	
	public static void del(int delIndex,String name) throws IOException, ClassNotFoundException{
		
		if(delIndex==0){
//			System.out.println("Connecting to server-1");
			obj.writeObject("del");
			obj.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==1){
//			System.out.println("Connecting to server-2");
			obj1.writeObject("del");
			obj1.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket1.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==2){
//			System.out.println("Connecting to server-3");
			obj2.writeObject("del");
			obj2.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket2.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==3){
//			System.out.println("Connecting to server-4");
			obj3.writeObject("del");
			obj3.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket3.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==4){
//			System.out.println("Connecting to server-5");
			obj4.writeObject("del");
			obj4.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket4.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==5){
//			System.out.println("Connecting to server-6");
			obj5.writeObject("del");
			obj5.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket5.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		else if(delIndex==6){
//			System.out.println("Connecting to server-7");
			obj6.writeObject("del");
			obj6.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket6.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		else if(delIndex==7){
//			System.out.println("Connecting to server-8");
			obj7.writeObject("del");
			obj7.writeObject(name);
			ObjectInputStream oin3 = new ObjectInputStream(getSocket7.getInputStream());
			Object delobj = oin3.readObject();
			String flag = (String) delobj;
			System.out.println(flag);
			  
		}
		
		
		
	}
	
	public static int hashFunction(String s){
//		s=key;
		int leng = s.length();
		long h = 0;
		for (int i = 0; i <leng ; i++) {
			h = 32*h + s.charAt(i);
		}
//		System.out.println(h);
		index = (int) (h%8);
//		System.out.println(index);
		return (int) index;
	}
	
	
	
	public static int putImplement(){
		getKeyValue();
		return hashFunction(key);
//		System.out.println(hashFunction(key));
	}
	
	public static String maulik(String m){
		String fun = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String dun = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String lun = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String fung = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		
		return m+fun+lun+fung;
	}	
	
	
	
	

	/* Runnable method that implements the multithreading and it will create new thread for each connections
	 * and it calls the method ClientAsServer where we have written the server code for the client. It will
	 * check for the appropriate command and fuctions accordingly */


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Trying to connect......");
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
		try {
			
			serverConnectionSocket = serverSocket.accept();
//			System.out.println("Connection estabralished from"+serverConnectionSocket.getInetAddress()+"and port"+serverConnectionSocket.getLocalPort());
			new ClientAsServer2(serverConnectionSocket);
//			obj.fun();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}
	

}
