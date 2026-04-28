public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    private int minusOne(int index){
        return (index-1+ items.length) % items.length;
    }

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    private void resize(int newSize){
        T[] newArray=(T[]) new Object[newSize];
        int curr = plusOne(nextFirst);
        for(int i=0;i < size; i++){
            newArray[i]= items[curr];
            curr=plusOne(curr);
        }
        items =newArray;
        nextFirst=newSize - 1;
        nextLast = size;
    }


    public ArrayDeque(){
        items =(T[]) new Object[8];
        size=0;
        nextFirst=4;
        nextLast=5;
    }
    public void addFirst(T item){
        if (size== items.length) {
            resize(size * 2);
        };
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size+=1;
    }
    public void addLast(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size+=1;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int curr = plusOne(nextFirst);
        for(int i=0;i<size;i++){
            System.out.print(items[curr]);
            System.out.print(" ");
            curr = plusOne(curr);
        }
        System.out.println();
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        int first=plusOne(nextFirst);
        T remove= items[first];
        items[first]=null;

        size-=1;
        nextFirst = first;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length/2);
        }
       return remove;
    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        int last =minusOne(nextLast);
        T remove= items[last];
        items[last]=null;
        nextLast=last;
        size-=1;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length/2);
        }

        return remove;
    }
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return items[(index+1+nextFirst) % items.length];
    }
}