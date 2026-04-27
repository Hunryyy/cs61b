public class ArrayDeque<T>{
    private int size;
    private T[] array;
    private int nextFirst;
    private int nextLast;

    private int minusOne(int index){
        return (index-1+array.length) % array.length;
    }

    private int plusOne(int index){
        return (index + 1) % array.length;
    }

    private void resize(int newSize){
        T[] newArray=(T[]) new Object[newSize];
        int curr = plusOne(nextFirst);
        for(int i=0;i < size; i++){
            newArray[i]=array[curr];
            curr=plusOne(curr);
        }
        array=newArray;
        nextFirst=newSize - 1;
        nextLast = newSize;
    }
    public ArrayDeque(){
        array=(T[]) new Object[8];
        size=8;
        nextFirst=4;
        nextLast=5;
    }
    public void addFirst(T item){
        if (size==array.length) {
            resize(size * 2);
        };
        array[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size+=1;
    }
    public void addLast(T item){
        if(size == array.length){
            resize(size * 2);
        }
        array[nextLast] = item;
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
            System.out.print(array[curr]);
            System.out.print(" ");
            curr = plusOne(curr);
        }
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        int first=plusOne(nextFirst);
        T remove=array[first];
        array[first]=null;

        if (array.length >= 16 && size < array.length / 4) {
            resize(array.length/2);
        }
        return remove;
    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        int last =minusOne(nextLast);
        T remove=array[last];
        array[last]=null;

        if (array.length >= 16 && size < array.length / 4) {
            resize(array.length/2);
        }
        return remove;
    }
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        return array[(index+1+nextFirst) % array.length];
    }
}