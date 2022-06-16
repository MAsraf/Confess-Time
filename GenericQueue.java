/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confesstime;

/**
 *
 * @author DELL
 */
public class GenericQueue<E> {

    private java.util.LinkedList<E> list = new java.util.LinkedList<>();

    public void enqueue(E e) {
        list.addLast(e);
    }

    public E dequeue() {
        return list.removeFirst();
    }

    public int getSize() {
        return list.size();
    }
    
    //new method
    public E peek(){
        return (E) list.getFirst();
    }
    
    //new method
    public boolean isEmpty(){
        if (list.isEmpty()== true)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "Queue: " + list.toString();
    }
}
