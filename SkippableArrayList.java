import java.util.ArrayList;
import java.util.Iterator;

public class SkippableArrayList<E> extends ArrayList<E> {


    public Iterator<E> iterator(Predicate<E> p, Either<E> f){
        return new Iterator<E>() {
            private Integer cnt=0;
            private SkippableArrayList<E> tmp=SkippableArrayList.this;
            @Override
            public boolean hasNext() {
                try{
                    return tmp.get(cnt+1)!=null;
                }catch (Exception e){
                    return false;
                }
            }

            @Override
            public E next() {
                E val=tmp.get(cnt++);
                if(p.apply(val)){
                    val= f.onSuccess(val);
                }else{
                    try{
                        f.onFailure(val);
                    }catch (Exception e){
                        System.out.println("Eccezzione fallimento");
                    }
                }
                return val;
            }
        };
    }


    public static void main(String[] args){
        ArrayList dst=new ArrayList();


        SkippableArrayList<Integer> src=new SkippableArrayList<>();
        for(int i=0;i<10;i++){
            src.add((int)(Math.random()*10));
        }


        class HetherInt implements Either<Integer>{
            @Override
            public Integer onSuccess(Integer integer) {
                return integer ;
            }

            @Override
            public void onFailure(Integer integer) throws Exception {
                throw new Exception("MINORE DI 5");
            }
        }

        for (Iterator<Integer> it = src.iterator((v) -> { return v > 5; }, new HetherInt()); it.hasNext(); ) {
            int x = it.next();
            System.out.println(x);
            if(x<=5){
                dst.add(x);
            }
        }

    }
}
