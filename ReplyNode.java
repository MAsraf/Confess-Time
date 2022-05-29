package Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asraf
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/*Implement Tree data structure to create a hiearchy of post

*/
public class ReplyNode<T> implements Iterable<ReplyNode<T>> {

    public T data;
    public ReplyNode<T> parent;
    public List<ReplyNode<T>> children;
    
    /**
     * Returns true if the node is a root.
     * @return true, if this node is a root
     */
    public boolean isRoot() {
	return parent == null;
    }
    /**
     * Returns true if the node is a leaf.
     * @return true, if this node is a leaf.
     */
    public boolean isLeaf() {
	return children.size() == 0;
    }

    private List<ReplyNode<T>> elementsIndex;

    public ReplyNode(T data) {
	this.data = data;
	this.children = new LinkedList<ReplyNode<T>>();
	this.elementsIndex = new LinkedList<ReplyNode<T>>();
	this.elementsIndex.add(this);
    }
    
    /**
     * Adds a child node to a parent node.
     * @param child Generic type 
     * @return the child node
     */
    public ReplyNode<T> addChild(T child) {
	ReplyNode<T> childNode = new ReplyNode<>(child);
	childNode.parent = this;
	this.children.add(childNode);
	this.registerChildForSearch(childNode);
	return childNode;
    }
    
    /**
     * Returns the level of the node resides in.
     * @return  int value of the level the node is in
     */
    public int getLevel() {
	if (this.isRoot())
            return 0;
	else
            return parent.getLevel() + 1;
    }

    private void registerChildForSearch(ReplyNode<T> node) {
	elementsIndex.add(node);
	if (parent != null)
            parent.registerChildForSearch(node);
    }

    public ReplyNode<T> findTreeNode(Comparable<T> cmp) {
	for (ReplyNode<T> element : this.elementsIndex) {
            T elData = element.data;
            if (cmp.compareTo(elData) == 0)
		return element;
            }
        return null;
	}

    @Override
    public String toString() {
	return data != null ? data.toString() : "[data null]";
    }

    @Override
    public Iterator<ReplyNode<T>> iterator() {
	ReplyNodeIter<T> iter = new ReplyNodeIter<>(this);
	return iter;
    }

}
