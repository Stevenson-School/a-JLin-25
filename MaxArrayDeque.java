import java.util.*;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T>comp;
    public MaxArrayDeque(){}
    public MaxArrayDeque(Comparator<T>c){
        comp=c;
    }
    public T max(){
        T mx=get(0);
        for(T i:arr){
            if(comp.compare(mx,i)<0){
                mx=i;
            }
        }
        return mx;
    }
    public T max(Comparator<T> cons){
        T mx=get(0);
        for(T i:arr){
            if(cons.compare(mx,i)<0){
                mx=i;
            }
        }
        return mx;
    }

    public static void main(String[] args) {
        MaxArrayDeque<Integer>m1=new MaxArrayDeque<>(new Comparator<Integer>(){
            public int compare(Integer a,Integer b){
                return a-b;
            }
        });
        m1.addFirst(1);
        m1.addFirst(5);
        m1.addFirst(3);
        System.out.println(m1.max());
    }
}
