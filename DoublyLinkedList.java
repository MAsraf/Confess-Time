package confesstime;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    public class PostNode<E> {

        E post;
        PostNode<E> next;
        PostNode<E> prev;

        public PostNode(E post, PostNode next, PostNode prev) {
            this.post = post;
            this.next = next;
            this.prev = prev;
        }

        public PostNode(E post) {
            this(post, null, null);
        }
    }
    PostNode<E> head;
    private PostNode<E> tail;
    PostNode<E> iterate;                                                        //Used when iterating in DoublyLinkedList, Tester2 class
    private int size;

    public DoublyLinkedList() {
        size = 0;
        this.head = null;
        this.tail = null;
    }

    public int Size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E element) {
        //create object tmp and set pointer of the new node
        PostNode<E> tmp = new PostNode(element, head, null);
        //set head.prev of current head to be linked to the new node
        if (head != null) {
            head.prev = tmp;
        }
        head = tmp; //now tmp become head
        iterate = head;
        //if no tail, then tmp set to be a tail
        if (tail == null) {
            tail = tmp;
        }
        size++;//increase number of node
    }

    public void add(E element) {        //implementing addLast method
        //create object tmp and set pointer of the previous node
        PostNode<E> tmp = new PostNode(element, null, tail);
        //set tail.next point to object tmp
        if (tail != null) {
            tail.next = tmp;
        }
        //now tmp become tail
        tail = tmp;
        //if no head, then tmp set to be a head
        if (head == null) {
            head = tmp;
            iterate = head;
        }
        size++;//increase number of node
    }

//    public void iterateForward(){
//         
//        System.out.println("iterating forward..");
//        PostNode<E> tmp = head;
//        while(tmp != null){
//            System.out.print(tmp.post);
//              System.out.print(" ");
//            tmp = tmp.next;
//        }
//    }
//    public void iterateBackward(){
//         
//        System.out.println("iterating backward..");
//        PostNode<E> tmp = tail;
//        while(tmp != null){
//            System.out.println(tmp.post);
//            tmp = tmp.prev;
//        }
//    }
//    public E next() {
//        if (current != null){
//        E tmp = (E) current.post;
//        current = current.next; 
//        return tmp;
//        }else{
//        return null;
//        }
//    }
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        //copy head to node tmp
        PostNode<E> tmp = head;
        //head.next become a head
        head = head.next;
        //set pointer of prev of new head to be null
        head.prev = null;
        //reduce number of node
        size--;
        return tmp.post;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        //copy tail to node tmp
        PostNode<E> tmp = tail;
        //tail.prev become a tail
        tail = tail.prev;
        //set pointer of next of new tail to be null
        tail.next = null;
        //reduce number of node
        size--;
        return tmp.post;
    }

    public void add(int index, E element) {
        //index can only be 0 ~ size()
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            add(element);
        } else {
            /* set from head and begin traverse
                        stop on required position */
            PostNode<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            /* create object insert and set pointer of the next pointer
                        to the temp node and also set pointer of the prev pointer
                        to the temp.prev node
             */
            PostNode<E> insert = new PostNode(element, temp, temp.prev);
            //set pointer 'next' of the node temp.prev to new node insert
            temp.prev.next = insert;
            //set pointer 'prev' of the node temp to new node insert
            temp.prev = insert;
            size++;
        }
    }   //might not be used

    public E remove(int index) {
        E element = null;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            element = removeFirst();
        } else if (index == size - 1) {
            element = removeLast();
        } else {
            PostNode<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            element = temp.post;
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
            temp.next = null;
            temp.prev = null;
            size--;
        }
        return element;
    }

    public void clear() {
        PostNode<E> temp = head;
        while (temp != null) {
            temp = head.next;
            temp.prev = temp.next = null;
            head = temp;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the current post in the iteration.
     *
     * @return current Confession Post data in the iteration
     */
    public E returnCurrentPost() {
//        try{
        if (iterate != null) {
            return iterate.post;
        }
//        }catch (NullPointerException e){
//            System.out.println("End of List...");
//        }
        return null;
    }

    /**
     * Sets iterate value as the first Confession Post in the iteration.
     */
    public void firstPost() {
        iterate = head;
    }

    /**
     * Sets iterate value as the last Confession Post in the iteration.
     */
    public void lastPost() {
        iterate = tail;
    }

    /**
     * Sets iterate value as the next Confession Post in the iteration.
     */
    public void nextPost() {
        if (iterate != null) {
            iterate = iterate.next;
        }
    }

    /**
     * Sets iterate value as the previous Confession Post in the iteration.
     */
    public void previousPost() {
        if (iterate != null) {
            iterate = iterate.prev;
        }
    }

}
