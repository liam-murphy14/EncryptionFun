import java.util.Iterator;
import java.util.NoSuchElementException;

/*
   public class KeyMap constructs a linked key/value pair set, with no
   duplicates and a restricted list of methods
   created by Liam Murphy
*/

public class KeyMap<Integer> implements KMap<Integer>{
  /* private inner class Node for each element of the linked chain */
  private class Node{
    /* Node instance variables */
    private Integer key;
    private Integer value;
    private Node next;
    /* Node constructor */
    public Node(Integer key, Integer value){
      this.key = key;
      this.value = value;
      this.next = null;
    }
    /* Node toString method */
    public String toString(){
      String s = key + "=" + value;
      return s;
    }
  }
  /* KeyMap instance variables */
  private Node head;
  private int size;
  /* KeyMap constructor */
  public KeyMap(){
    this.head = null;
    this.size = 0;
  }
  /* add takes in a key and a value and adds them if they do not already exist,
     returning true upon a successful add and false otherwise */
  public boolean add(Integer key, Integer value){
    Node current = head;
    while(current != null){
      if(key.equals(current.key)){
        return false;
      }
      current = current.next;
    }
    Node newNode = new Node(key, value);
    newNode.next = head;
    head = newNode;
    size++;
    return true;
  }
  /* get takes in a key and searches the KeyMap for this key, returning the
     corresponding value if found, and null otherwise */
  public Integer get(Integer key){
    Node current = head;
    while(current != null){
      if(key.equals(current.key)){
        return current.value;
      }
      current = current.next;
    }
    return null;
  }
  /* size takes in unit and returns the size */
  public int size(){
    return size;
  }
  /* KeyMap toString method */
  public String toString(){
    Node current = head;
    String s = "";
    while (current != null){
      s = s + (current.toString()) + "\n";
      current = current.next;
    }
    return s;
  }
  /* iterator returns a new KeyIterator */
  public Iterator<Integer> iterator(){
    return new KeyIterator();
  }
  /* private inner class Iterator */
  private class KeyIterator implements Iterator<Integer>{
    /* Iterator instance variables */
    private Node on;
    private int count;
    /* Iterator constructor */
    public KeyIterator(){
      this.on = head;
      this.count = 0;
    }
    /* hasNext takes in unit and returns true if there is another element */
    public boolean hasNext(){
      boolean b = (on != null);
      return b;
    }
    /* next takes in unit and returns the element at the on position */
    public Integer next(){
      if (hasNext()){
        Integer key = on.key;
        on = on.next;
        count++;
        return key;
      }
      else{
        throw new NoSuchElementException("How did you get here ??");
      }
    }
    /* Unsupported method: remove() */
    public void remove(){
      throw new UnsupportedOperationException("How did you try to use this ??");
    }
  }
}
