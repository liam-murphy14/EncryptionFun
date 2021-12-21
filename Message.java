import java.util.Scanner;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

/* Message is a public class that creates an object from a string to decode
   or encode
   created by Liam Murphy
*/

public class Message implements Msg{
  /* instance variables */
  private String text;
  private int length;
  private boolean encoded;
  private KeyMap<Integer> key;
  /* Message constructor */
  public Message(String text){
    this.text = text;
    this.length = text.length();
    this.encoded = false;
    this.key = null;
  }
  /* determineEncoding takes in unit and determines wheter the message is
     currently encoded
  */
  public boolean determineEncoding(){
    String[] words = text.split(" ");
    HashSet<String> dictionary = new HashSet<String>();
    try{
      File f = new File("dictionary.txt");
      Scanner inDict = new Scanner(f);
      while(inDict.hasNext()){
        String s = inDict.next();
      }
      inDict.close();
    }
    catch(FileNotFoundException e){
      System.out.println("You seem to be missing the dictionary text file");
    }
    int tallyWords = 0;
    for (int i = 0; i < words.length; i++){
      String s = words[i].toLowerCase();
      if(dictionary.contains(s)){
        tallyWords++;
      }
    }
    double percentWords = ((double) tallyWords) / ((double) (words.length));
    if(percentWords >= 0.1){
      encoded = false;
      return encoded;
    }
    else{
      encoded = true;
      return encoded;
    }
  }
  /* setKey finds the key.txt file in the current directory and uses it to
     make a KeyMap to encode and decode the Message
  */
  public void setKey(){
    key = new KeyMap<Integer>();
    try{
      File f = new File("key.txt");
      Scanner inFile = new Scanner(f);
      while (inFile.hasNext()){
        String line = inFile.next();
        String [] keyVal = line.split("=");
        Integer addKey = new valueOf(Integer.parseInt(keyVal[0]));
        Integer addVal = new valueOf(Integer.parseInt(keyVal[1]));
        key.add(addKey, addVal);
      }
      inFile.close();
    }
    catch(FileNotFoundException e){
      System.out.println("You don't seem to have a key file");
    }
  }
  /* encode codes the message to make it unreadable */
  public void encode(){
    char [] chars = text.toCharArray();
    for (int i = 0; i < length; i++){
      char unencoded = chars[i];
      Integer unencodedValue = new valueOf((int) unencoded);
      int encodedValue = (key.get(unencodedValue)).intValue();
      chars[i] = (char) encodedValue;
    }
    String encodedText = new String(chars);
    text = encodedText;
    encoded = true;
  }
  /* decode translates the message back to plain english */
  public void decode(){
    char [] chars = text.toCharArray();
    for (int i = 0; i < length; i++){
      char encoded = chars[i];
      Integer encodedValue = new valueOf((int) encoded);
      int unencodedValue = 0;
        for(int j = 32; j < 127; j++){
          Integer k = new valueOf(j);
          Integer val = key.get(k);
          if(val.equals(encodedValue)){
            unencodedValue = j;
          }
        }
      chars[i] = (char) unencodedValue;
    }
    String unencodedText = new String(chars);
    text = unencodedText;
    encoded = false;
  }
  /* toString method */
  public String toString(){
    return text;
  }
}
