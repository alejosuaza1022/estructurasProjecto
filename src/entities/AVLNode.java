/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asb1022
 */
public class AVLNode<T extends Comparable<T>> implements Comparable<AVLNode<T>> {

    public AVLNode<T> left;
    public AVLNode<T> right;
    public T item;
    private int height;

    
    public AVLNode(T item) {
        this.item = item;
        this.left = left;
        this.right = right;
        height = 1;
    }
    
    public int height(){
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return item.toString();
    }

    @Override
    public int compareTo(AVLNode<T> o) {
        return item.compareTo(o.item);
    }

}
