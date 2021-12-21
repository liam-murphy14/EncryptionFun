import java.util.Iterator;

/*
   public interface KMap provides the interface implemented in KeyMap
   created by Liam Murphy
*/

public interface KMap<Integer> extends Iterable<Integer>{
  boolean add(Integer key, Integer value);
  Integer get(Integer key);
  int size();
  String toString();
  Iterator<Integer> iterator();
}
