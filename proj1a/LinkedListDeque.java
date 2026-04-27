public class LinkedListDeque<T>{
	private class Node {
		public Node prev;
		public T item;
		public Node next;
		public Node(T i,Node p,Node n){
			item=i;
			prev=p;
			next=n;
		}
	}
	private Node sentinel;
	private int size;
	public LinkedListDeque(){
		sentinel=new Node(null,null,null);
		size=0;
		sentinel.next=sentinel;
		sentinel.prev=sentinel;
	}


	public void addFirst(T item){
		Node t=new Node(item,sentinel,sentinel.next);
		sentinel.next.prev=t;
		sentinel.next=t;
		size+=1;
	}
	public void addLast(T item){
		Node last=new Node(item,sentinel.prev,sentinel);
		sentinel.prev.next=last;
		sentinel.prev=last;
		size+=1;
	}
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		else{
			return false;
		}
	}
	public int size(){
		return size;
	}
	public void printDeque() {
		if(size==0){
			return;
		}
		Node temp = sentinel.next;
		while (true) {
			System.out.print(temp.item);
			temp=temp.next;
			if(temp==sentinel){
				System.out.println();
				return;
			}
			System.out.print(" ");
		}
	}
	public T removeFirst(){
		if(size==0){
			return null;
		}
		Node temp=sentinel.next;
		T t=temp.item;
		sentinel.next=sentinel.next.next;
		sentinel.next.prev=sentinel;

		temp.next=null;
		temp.prev=null;
		temp.item=null;
		size-=1;
		return t;
	}
	public T removeLast(){
		if(size==0){
			return null;
		}
		Node temp=sentinel.prev;
		T t=temp.item;
		sentinel.prev=sentinel.prev.prev;
		sentinel.prev.next=sentinel;

		temp.next=null;
		temp.prev=null;
		temp.item=null;
		size-=1;
		return t;
	}
	public T get(int index){
		if(index >= size) {
			return null;
		}
		Node t=sentinel.next;
		for(int i=0;i<index;i++){
			t=t.next;
		}
		return t.item;
	}
	public T getRecursive(int index){
		if(index>= size || index < 0){
			return null;
		}
		return recursiveHelp(sentinel.next,index);

	}
	private T recursiveHelp(Node t,int index){

		if(index==0){
			return t.item;
		}
		return recursiveHelp(t.next,index-1);
	}

}
