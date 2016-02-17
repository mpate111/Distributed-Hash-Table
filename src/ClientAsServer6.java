//package peer;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Hashtable;

public class ClientAsServer6 implements Runnable{
	
	Socket client;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	static Hashtable<String,String> hashTableInfo = new Hashtable<String,String>();
	
	public ClientAsServer6(Socket client) throws IOException{
		this.client = client;
//		oout = new ObjectOutputStream(client.getOutputStream());
		oin = new ObjectInputStream(client.getInputStream());
		new Thread(this).start();		
	}
	
	
	
	/* Run method has an infinite loop and it keeps on running and listen to the connected client.
	 * Everytime the client connects it create a new thread for each new connections
	 * It listens to three commands put,get and del and performs the operation of adding, searching and deleting
   */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String command = null;
		Object obj1;
		while(true){
		try {
			obj1 = oin.readObject();
			command = (String) obj1;
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			if(command.equals("put")){
			Object obj2 = oin.readObject();
			String info = (String) obj2;
			String[] Infoarray = info.split(":");
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
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("One of the server went down-:");
			break;
		}
		}	
	}

}



