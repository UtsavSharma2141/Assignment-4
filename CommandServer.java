/**
 *
 * @author Utsav Sharma
 */
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class CommandServer {

    String[] tokens;
    String thread;


    //file function
    static void file(String cmd, String cmdLine) throws IOException {
        String filename = cmdLine.substring(cmdLine.indexOf(" ") + 1);

        String name = filename;

        File testfile = new File(name);

        if (testfile.exists()) {
            System.out.println("File name :" + testfile.getName());
            System.out.println("File Size " + testfile.length());
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            System.out.println("Last access time : " + format.format(testfile.lastModified()));
            if (testfile.isFile()) {
                System.out.println(testfile.getName() + " is a File");
            } else {
                System.out.println(testfile.getName() + " is a Directory");
            }

            if (testfile.canRead()) {
                System.out.println(testfile.getName() + " is Readable");
            } else if (testfile.canWrite()) {
                System.out.println(testfile.getName() + " is Writable");
            } else if (testfile.canExecute()) {
                System.out.println(testfile.getName() + " is Executable");
            } else {
                System.out.println("NO permissions are given");

            }
        } else {
            System.out.println("file is not found");

        }
    }

    //dict function
    static void dict(String cmd, String cmdLine) throws IOException {
        String f_name = cmdLine.substring(cmdLine.indexOf(" ") + 1);
        String str1 = null;
        try {
            String string;
            String word = f_name;
            String dict = String.format("dict " + word);
            Process process = Runtime.getRuntime().exec(dict);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((string = buffer.readLine()) != null) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    //time function
    public static String time(String cmd) {
        System.out.flush();
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println("Connection Closed.");
        System.exit(0);
        return "time";
    }

    //help function
    public String help(String cmd, String cmdLine) throws IOException {

        String help = cmdLine.substring(cmdLine.indexOf(" ") + 1);

        if (help.equals("count")) {
            System.out.println("The count command is used to count the number of words in command line.");
        } else if (help.equals("dict")) {
            System.out.println("The dict command works like dictionary.It is used to find the meaning of the provided word. ");
        } else if (help.equals("time")) {
            System.out.println("The time command returns the current date and time in Japan.");
        } else if (help.equals("file")) {
            System.out.println("The file command reports the file information of the supplied file.");
        } else if (help.equals(null) || help.equals("help")) {
            System.out.println("help command gives the more information about the commands.");
        } else {
            System.out.println("Help not available for " + help + " command!");
        }

        return cmdLine;
    }

    //count function
    public int count(String cmd, String cmdLine) {
        if (cmdLine == null || cmdLine.isEmpty()) {
            return 0;
        }

        String[] words = cmdLine.split("\\s+");
        System.out.println("Number of words is: " + (words.length - 1));
        return words.length;
    }

    //exit function
    void exit() {
        System.out.println("Bye!!");
        System.exit(0);
    }

    public String interpret(String cmdLine) throws IOException {
        String[] tokens;
        String cmd;
        Scanner scanner = new Scanner(cmdLine);

        //Detect command. Since return is immediate, break not req'd
        if (scanner.hasNext()) {

            switch (cmd = scanner.next().toLowerCase()) {

                case "file":
                    file(cmd, cmdLine);
                    System.out.println("Enter a command: time, count, file, dict, help");
                    System.out.println("If you want to exit type 'exit'");
                    break;

                case "dict":
                    dict(cmd, cmdLine);
                    System.out.println("Enter a command: time, count, file, dict, help");
                    System.out.println("If you want to exit type 'exit'");
                    break;

                case "time":
                    time(cmd);
                    System.out.println("Enter a command: time, count, file, dict, help");
                    System.out.println("If you want to exit type 'exit'");

                    break;

                case "count":
                    count(cmd, cmdLine);
                    System.out.println("Enter a command: time, count, file, dict, help");
                    System.out.println("If you want to exit type 'exit'");
                    break;

                case "help":
                    help(cmd, cmdLine);

                    break;

                case "exit":
                    exit();

                default:
                    return "Command " + cmd + " not found\n"
                            + "Enter a command: time, count, file, dict, help";

            }
        }
        return "";
    }

    CommandServer(InputStream in, PrintStream out, String name) throws IOException {
        this.thread = name;
        System.out.format("Starting thread: %s\n", name);
        Scanner scanner = new Scanner(in);
        while (true) {
            while (scanner.hasNextLine()) {
                out.println(interpret(scanner.nextLine()));
            }
        }
    }

    public static void main(String[] args) throws IOException {
     
        System.out.println("Enter a command: time, count, file, dict, help");
        System.out.println("If you want to exit type 'exit'");
        new CommandServer(System.in, System.out, "Thread1");
        System.out.println(new Date()); 
          System.out.flush();

    }

}

