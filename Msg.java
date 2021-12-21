/* This file provides the interface for the Message class
   created by Liam Murphy
*/

public interface Msg{
  boolean determineEncoding();
  void setKey();
  void encode();
  void decode();
  String toString();
}
