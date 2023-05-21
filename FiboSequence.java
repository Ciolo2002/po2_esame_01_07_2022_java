import java.util.ArrayList;
import java.util.Iterator;

public class FiboSequence implements Iterable<Integer>{
    private  Integer max_items;
    private static ArrayList<Integer> cache=new ArrayList<>();
    private Integer done_item=0;

    public FiboSequence(Number i){
        max_items=(Integer) i;
    }


   public Iterator<Integer> iterator() {
       return new Iterator<Integer>() {
           public boolean hasNext() {
               return done_item < max_items;
           }

           public Integer next() {
               if(cache.size()>=max_items){
                   done_item=max_items;
                   return cache.get(max_items);
               }
               for(int i=0;i<=max_items;i++){
                   cache.add(fibonacci(i));
               }
              return cache.get(max_items);
           }

           private Integer  fibonacci(Integer  n)  {
               done_item++;
               if(n == 0)
                   return 0;
               else if(n == 1)
                   return 1;
               else
                   return fibonacci(n - 1) + fibonacci(n - 2);
           }
       };
   }

    public static void main(String[] args) {
        for (int n : new FiboSequence(35)) {
            System.out.println(n);
        }
        for (int n : new FiboSequence(30)) {
            System.out.println(n);
        }

    }
}
