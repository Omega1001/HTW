package ueb20;

public class Main {

	public static void main(String[] args) {
		MyHeap<Integer> h = new MyHeap<>(12);
		h.add(5);
		h.offer(10);
		h.offer(20);
		h.add(2);
		
		System.out.println(h.remove());
		System.out.println(h.poll());
		System.out.println(h.peek());
		System.out.println(h.poll());
		System.out.println(h.remove());
		

	}

}
