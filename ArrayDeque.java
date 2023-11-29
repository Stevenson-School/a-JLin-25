import java.util.*;
public class ArrayDeque<T> implements Iterable<T>{
    T[] arr;
    int first,last,size;//idx of first and last
    public ArrayDeque(){
        arr=(T[])new Object[8];
        size=first=last=0;
    }
    private void resize(int s){
        T[] temp=(T[])new Object[s];
        for(int i=1;i<=size;i++){
            temp[i]=get(i-1);
        }
        arr=temp;
        first=0;
        last=size;
    }
    public void addFirst(T item){
        first+=arr.length;
        if(size==arr.length){
            resize(size*3/2);
        }
        arr[(first%=arr.length)]=item;
        first--;
        size++;
    }
    public void addLast(T item){
        last++;
        if(size==arr.length){
            resize(size*3/2);
        }
        arr[last%=arr.length]=item;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        if(size>0)size--;
        else return null;
        first++;
        T f=arr[((first+arr.length)%arr.length)];
        arr[first%=arr.length]=null;
        return f;
    }
    public T removeLast(){//need to debug because last was pointing to the second last element
        if(size>0)size--;
        else return null;
        last+=arr.length;
        T l=arr[last%arr.length];
        arr[last%=arr.length]=null;
        last--;
        return l;
    }
    public T get(int idx){
        return arr[(first+idx+arr.length+1)%arr.length];
    }
    @Override
    public String toString(){
        StringBuilder x=new StringBuilder("{");
        for(int cnt=0,i=first+1;cnt<size;cnt++,i++){
            x.append(arr[i%=arr.length]).append(", ");
        }
        x.replace(x.length()-2,x.length(),"}");
        return x.toString();
    }
    @Override
    public boolean equals(Object other){
        if(other==null)return false;
        if (other instanceof ArrayDeque ad) {
            if (ad.size() != this.size()) return false;
            boolean[] vis = new boolean[this.size()];
            for (int i = 0; i < ad.size(); i++) {
                boolean flag = false;
                for (int j = 0; j < this.size(); j++) {
                    if (vis[j]) continue;
                    if (ad.get(i) == this.get(j)) {
                        flag = true;
                        vis[j] = true;
                        break;
                    }
                }
                if (!flag) return false;
            }
            return true;
        }
        return false;
    }
    private class ADIterator implements Iterator<T>{
        int idx;
        public boolean hasNext(){
            return idx<size;
        }
        public T next(){
            return get(idx++);
        }
    }
    public Iterator<T>iterator(){
        return new ADIterator();
    }

    public boolean contains(T x){
        for(T i:arr){
            if(i==x)return true;
        }
        return false;
    }

    public static void main(String[] args){
        ArrayDeque<Integer>a1=new ArrayDeque<>();
//        for(int i=0;i<16;i++){
//            a1.addFirst(i);
//        }
        a1.addFirst(1);
        a1.addFirst(1);
        a1.addFirst(2);
        a1.addFirst(3);
        System.out.println(a1);
        ArrayDeque<Integer>a2=new ArrayDeque<>();
        a2.addFirst(1);
        a2.addFirst(3);
        a2.addFirst(2);
        a2.addFirst(2);
        System.out.println(a1.equals(a2));
        for(Integer i:a1){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Is in ad?: "+a1.contains(4));
    }
}
