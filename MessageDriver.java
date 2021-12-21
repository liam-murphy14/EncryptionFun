import java.util.Scanner;
import java.io.IOException;

/* This is the driver class for the Message object. It will handle all the
   encoding and decoding, along with creating and sending the Messages
   Created by Liam Murphy
*/

public class MessageDriver{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.println("What would you like to do ??");
    System.out.println("(press e then enter to encode OR d then enter to decode)");
    for(int i = 0; i < 1; i++){
      String inp = input.next();
      input.nextLine();
      if(inp.equals("e")){
        boolean b = sendEncodedMessage(input);
        if(b){
          System.out.println("Your message has been sent !!");
        }
        else{
          System.out.println("Something seems to have gone wrong :(");
          System.out.println("Close the program and try again");
        }
      }
      else if(inp.equals("d")){
        System.out.println("Copy and paste the whole message, then press enter");
        String text = input.nextLine();
        text = text.trim();
        String s = returnDecodedMessage(text);
        System.out.println("Here's the message: " + s);
      }
      else{
        System.out.println("You entered something other than e or d, try again");
        i--;
      }
    }
  }
  public static boolean sendEncodedMessage(Scanner input){
    System.out.println("Type the message you would like to encode, then press enter: ");
    String txt = input.nextLine();
    txt = txt.trim();
    Message message = new Message(txt);
    message.setKey();
    message.encode();
    boolean b = message.determineEncoding();
    if(b){
      String send = message.toString();
      System.out.println("Who would you like to send it to ??");
      String number = input.next();
      String command = "osascript sendMessage.applescript " +
                        number + " " + send;
      try{
        Process proc = Runtime.getRuntime().exec(command);
      }
      catch(IOException e){
        System.out.println("Oops, something went wrong :(");
      }
      return b;
    }
    else{
      return b;
    }
  }
  public static String returnDecodedMessage(String text){
    Message message = new Message(text);
    message.setKey();
    message.decode();
    String mess = message.toString();
    return mess;
  }
}
