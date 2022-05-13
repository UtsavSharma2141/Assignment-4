/**
 *
 * @author Utsav Sharma
 */  
import static java.lang.System.*;
import static java.lang.System.out;

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.System.out;
import java.util.Scanner;

public class Server
{
  public static void main(String[] args) { new Server(); }

  public Server() {
   int clientNo=1;
   try
    {
      //Create the Server Socket
      ServerSocket serverSocket=new ServerSocket(6000);
      System.out.println("Starting multi threaded server on port " + serverSocket.getLocalPort());
     
      while(clientNo<=3)
      {
        //Listen for a connection request - program waits for an incoming connection
        Socket socket = serverSocket.accept();
     
//Display client information
System.out.println("Starting thread for client " + clientNo + " at " + new Date());
InetAddress inetAddress = socket.getInetAddress();
System.out.println("Client " + clientNo + "'s hostname is "
     +  inetAddress.getHostName()+ ":" + socket.getPort()); //getPort() not in textbook
System.out.println("Client " + clientNo + "'s ipAddress is "
     +  inetAddress.getHostAddress());

        //Start a new thread
        HandleAClient task=new HandleAClient(socket);
        task.setName("Client# " +clientNo);
        task.start();

        clientNo++;  //increment clientNo for next client
       }
      }
      catch(IOException ex) { ex.printStackTrace(); }
     } //end of main
   
     //Inner class to handle a client
     //Define the thread class for handling a new connection
     class HandleAClient extends Thread {  
         private Socket socket;  //represents the connection to the client
       
         HandleAClient(Socket socket) {this.socket=socket; }
 
         //Run a thread
         //Note: The code inside this routine is taken as is from Server.java
         public void run()
         {
          try {
          //Create data input and output streams based on client socket info
          DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
          DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
          System.out.println(this.getName() + " is ready for your commands");
 Scanner scanner = new Scanner(System.in);
       while(true){
             
       
           
            String userInput = inputFromClient.readUTF();
       
            out.println("ResponseServer: " + userInput);
           

            if (userInput.toLowerCase().equals("time")) {
               out.println("time command received");
            }
            
            if (userInput.toLowerCase().equals("count")) {
               out.println("count command received");
            }
            if (userInput.toLowerCase().equals("file")) {
                out.println("file command received");
            }
            if (userInput.toLowerCase().equals("dict")) {
                out.println("dict command received");
            }
            if (userInput.toLowerCase().equals("help")) {
                out.println("help command received");
            }
       
       

       
    }
         } catch(IOException ex) { ex.printStackTrace(); }
         System.out.println(this.getName()+ " Thread goes bye-bye");
       }
         
         
         
  } //end of inner class HandleAClient
     
     
} //end of outer class MultiThreadServer

/*

outputToClient


*/


