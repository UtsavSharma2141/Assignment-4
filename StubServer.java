import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Utsav Sharma
 */
public class StubServer {
   
    String help(String[] tokens){
        return "help command received.";
    }
    
    String count(String[] args){
        return "count command received.";
    }
    
    String time(String[] args){
        return "time command received.";
    }
    
    String file(String[] args){
        return "file command received.";
    }
    
    String dict(String[] args){
        return "dict command received.";
    }
   
   
    StubServer(InputStream in, PrintStream out) {
       while(true)
       {
          Scanner scanner=new Scanner(in);
          String line;
          String[] tokens;
          
          out.flush();
          while(scanner.hasNextLine()) {
              line=(scanner.nextLine());
              tokens=line.split(" ");
              switch(tokens[0].toLowerCase()){
                  case "help": out.println(help(tokens));
                      break;
                  case "count": out.println(count(tokens));
                      break;
                  case "time": out.println(time(tokens));
                      break;
                  case "file": out.println(file(tokens));
                      break;
                  case "dict": out.println(dict(tokens));
                      break;
                  case "exit": out.println("exiting, bye!!");
                               System.exit(0);
                  default: out.println("bad command: "+tokens[0]);
              }
              out.print("Next Command: ");
              out.flush();
          }
             
       }
    }
    public static void main(String[] args) {
    System.out.println("Enter a command: help, count, time, file, dict");
        
        new StubServer(System.in,System.out);
    }
   
}
