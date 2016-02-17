//package peer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class ClientAsServer1 implements Runnable {
	
	Socket client;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	static Hashtable<String,String> hashTableInfo = new Hashtable<String,String>();
	BufferedReader in;
	
	public ClientAsServer1(Socket client) throws IOException{
		this.client = client;
		 in = new BufferedReader(new InputStreamReader(client.getInputStream()) );
		oin = new ObjectInputStream(client.getInputStream());
		new Thread(this).start();	

	}
	
	/* Run method has an infinite loop and it keeps on running and listen to the connected client.
	 * Everytime the client connects it create a new thread for each new connections
	 * It listens to three commands put,get and del and performs the operation of adding, searching and deleting
   */
	
	

	public void run() {
		
		String command = null;
		Object obj1;
		while(true){
		String[] Infoarray = null;
		try {
			obj1 = oin.readObject();
			command = (String) obj1;

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			if(command.equals("put")){
			Object obj2 = oin.readObject();
			String info = (String) obj2;
			Infoarray = info.split(":");
			hashTableInfo.put(Infoarray[0], Infoarray[1]);
			oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject("true");
			}
			
			else if(command.equals("get")){
				Object getObject = oin.readObject();
				String name = (String) getObject;
				String valueinfo =  hashTableInfo.get(name);
				ObjectOutputStream oout1 = new ObjectOutputStream(client.getOutputStream());
				if(valueinfo != null){
				oout1.writeObject(valueinfo);
				}
				else{
					oout1.writeObject("There is no such such key -::");
				}
			}
			
			else if(command.equals("del")){
				Object delObject = oin.readObject();
				String delname = (String) delObject;
				ObjectOutputStream oout3 = new ObjectOutputStream(client.getOutputStream());
				if(hashTableInfo.containsKey(delname) == true){
					hashTableInfo.remove(delname);
					oout3.writeObject("Success");
				}
				else{
					oout3.writeObject("Failure - No such key");
				}
			}
			
			else{
				System.out.println("The given command is not appropriate");
			}
			
			
		} catch (ClassNotFoundException | IOException e) {
			
			e.printStackTrace();
			System.err.println("One of the server went down-:");
			break;
		}
		}	
	}

}
