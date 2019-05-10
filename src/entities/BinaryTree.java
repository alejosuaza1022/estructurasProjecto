/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.text.Position;

/**
 *
 * @author asb1022
 */
public class BinaryTree<T extends Comparable<T>> implements Iterable<T>{
    
    private BinaryNode<T> root;

    private class BinaryNode<T extends Comparable<T>> implements Comparable<BinaryNode<T>> {

        public T item;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T item, BinaryNode<T> left, BinaryNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return item.toString();
        }

        @Override
        public int compareTo(BinaryNode<T> o) {
           return item.compareTo(o.item);
          }

    }
   
    private  void insert(BinaryNode<T> nodeCurrent, BinaryNode<T> nodeParent, T item){
     if(nodeCurrent == null){
            if(item.compareTo(nodeParent.item)<=0) nodeParent.left = new BinaryNode<>(item,null,null);
            else                                   nodeParent.right = new BinaryNode<>(item,null,null);
            return;
      }
     else if(nodeCurrent.item.compareTo(item) == 0) return;
     else{
             if(item.compareTo(nodeCurrent.item)<=0) insert(nodeCurrent.left, nodeCurrent, item);
             else                                    insert(nodeCurrent.right, nodeCurrent, item);
          
             return;
         }
    }
    
    public void insert(T item){
        if(root == null) root = new BinaryNode<>(item, null,null);
        else
        insert(root, null, item);
    }
    
    public ArrayList<T> getListCoincidence(String toSearch) {
        BinaryNode<T> newRoot = getNodeCoincidence(toSearch, root);
        BinaryTree<T> subTree = new BinaryTree<>();
        subTree.root = newRoot;
        ArrayList<T> toReturn = new ArrayList<>();
        for(T item: subTree) {
            if(item.toString().startsWith(toSearch)) toReturn.add(item);
        }
        return toReturn;
    }
    
    private BinaryNode<T> getNodeCoincidence(String toSearch, BinaryNode<T> current) {
        if(current == null) return null;
        else if(current.item.toString().startsWith(toSearch)) return current;
        else {
            if(toSearch.compareTo(current.item.toString()) < 0) return getNodeCoincidence(toSearch, current.left);
            else return getNodeCoincidence(toSearch, current.right);
        }
    }

    private T getItem(T item, BinaryNode<T> current){
        if(current == null)
            return null;
        else {
            if(item.compareTo(current.item) == 0) return current.item;
            else if(item.compareTo(current.item) < 0) return getItem(item, current.left);
            return getItem(item, current.right);
        }
    }
        public T getItem(T item){
        return getItem(item, root);
    }
  
    public String toStringPreOrder() {
        return toStringPreOrder(root);
    }

    private String toStringPreOrder(BinaryNode<T> node) {
        if (node == null) {
            return "";
        }
        String r = "";
        r += node.item + " ";
        r += toStringPreOrder(node.left) ;
        r += toStringPreOrder(node.right) ;
        return r;
    }

    public String toStringInOrder() {
        return toStringInOrder(root);
    }

    private String toStringInOrder(BinaryNode<T> node) {
        if (node == null) {
            return "";
        }
        String r = "";
       
        r += toStringInOrder(node.left) + "";
       
        r += node.item + " ";
        
        r += toStringInOrder(node.right)+"";
        
        return r;
    }

    public String toStringPosOrder() {
        return toStringPosOrder(root);
    }

    private String toStringPosOrder(BinaryNode<T> node) {
        if (node == null) {
            return "";
        }
        String r = "";
        r += toStringPosOrder(node.left);
        r += toStringPosOrder(node.right);
        r += node.item;
        return r;
    }
    
    public String toStringLevelOrder() {
        Queue c = new LinkedList();{{ c.add(root);}};
        return toStringLevelOrder(c);
    }

    private String toStringLevelOrder(Queue list) {
        if (list.isEmpty()) 
            return "";
        else {
            BinaryNode n = (BinaryNode) list.remove();
            if (n.left != null)  list.add(n.left);
            if (n.right != null) list.add(n.right);
            String r = n.item.toString();
            return r += toStringLevelOrder(list);
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Queue<BinaryNode<T>> list = new LinkedList<BinaryNode<T>>();
            {list.add(root);}
            @Override
            public boolean hasNext() {
                return list.peek() != null;
            }

            @Override
            public T next() {
              BinaryNode<T> node = list.remove();
                if(node.left != null)list.add(node.left);
                if(node.right != null)list.add(node.right);
                return node.item;
              }
        };

    }
}
    
    
    
    
    
    
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
