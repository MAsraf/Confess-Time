/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asraf
 */
public class PostNode<E> {
    E post;
    PostNode<E> nextNode;
    PostNode<E> head = null;
    PostNode<E> tail = null;
    PostNode<E> iterate;
    
    static int size;
    
    public PostNode(){
        post = null;
        nextNode = null;
        size = 0;
    }
    
    public PostNode(E nPost){
        post = nPost;
    }
    
    public int getSize(){
        return size;
    }
    
    public void  addFirst(E post){
        PostNode<E> newNode = new PostNode<>(post);
        newNode.nextNode = head;
        head = newNode;
        iterate = head;
//        size++;
        if(tail == null)
            tail = head;
    }
    
    public void add(E post){
        if(head == null){
            addFirst(post);
        }
        else if(tail == null){
            head = tail = new PostNode<>(post);
        }
        else{
            tail.nextNode = new PostNode<>(post);
            tail = tail.nextNode;
        }
        size++;
    }
    
    public E removeFirst(){
        if(size == 0) return null;
        else{
            PostNode<E> temp = head;
            head = head.nextNode;
            size--;
            if(head == null) tail = null;
            return temp.post;
        }
    }
    
    public E removeLast(){
        if(size == 0) return null;
        else if(size == 1){
            PostNode<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.post;
        }
        else{
            PostNode<E> current = head;
            for(int i = 0; i < size - 2; i++)
                current = current.nextNode;
            PostNode<E> temp = tail;
            tail = current;
            tail.nextNode = null;
            size--;
            return temp.post;
        }
    }
    
    public E remove(int index){
        if(index < 0 || index >= size) return null;
        else if(index == 0) return removeFirst();
        else if(index == size - 1) return removeLast();
        else{
            PostNode<E> previous = head;
            for(int i = 1; i < index; i++){
                previous = previous.nextNode;
            }
            PostNode<E> current = previous.nextNode;
            previous.nextNode = current.nextNode;
            size--;
            return current.post;
        }
    }
    
    public void currentPost(){
        System.out.println(iterate.post);
    }
    
    public void nextPost(){
        
        if(iterate != null){
            iterate = iterate.nextNode;
        }
    }
}
