/**
 *
 * @author Utsav Sharma
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Client2 {

    public static void main(String[] args) {

        //Initialization
        System.out.println("Usage: java CircleClient [host] [port]");
        System.out.println("       default host: localhost  port: 6000");
        String hostname = args.length > 0 ? args[0] : "localhost";  
        int  port= args.length>1 ? Integer.parseInt(args[1]) : 6000;    
        String command;
        double number;
        //int  nBytes,  result;
       
        Socket socket = null;
        Scanner userInput=new Scanner(System.in);
       
        try {
            socket = new Socket(hostname, port);
         
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
         
         number=0;
          while(true)
           {  
            //Request and response
            System.out.print("Enter a Command: ");
            command = userInput.nextLine();
            out.writeUTF(command);

            System.out.println("Response from server is " + in.readUTF());
           }            
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
             
                try {
                    socket.close();
                } catch (IOException ex) {
                      ex.printStackTrace();
                }
            }
        }
   
}
