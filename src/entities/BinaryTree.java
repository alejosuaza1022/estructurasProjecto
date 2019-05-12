
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
    
    private AVLNode<T> root;

    private class AVLNode<T extends Comparable<T>> implements Comparable<AVLNode<T>> {

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

        @Override
        public String toString() {
            return item.toString();
        }

        @Override
        public int compareTo(AVLNode<T> o) {
           return item.compareTo(o.item);
          }

    }
   
   
    public void insert(T value){
        root  = root == null ? new AVLNode<>(value) : insert(root, value);

    }
    
     private AVLNode<T> insert(AVLNode<T> node, T value){
        if(node == null) return new AVLNode<>(value);
        else{
            //1. insertar recursivamente el nuevo nodo
            int k = value.compareTo(node.item);
            if(k<0){
                node.left = insert(node.left, value);
            }
            else if(k>0) {
                node.right = insert(node.right, value);
            }
            else{
                return root;
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            // 2. Balancear el camino por donde paso para insertarse
            int diff = diffHeight(node);
            if(diff < -1){
                if(diffHeight(node.right) > 0)node.right = rotateRight(node.right);
                //rotateLeft
                node  = rotateLeft(node);
                
            }
            else if(diff > 1){
                 if(diffHeight(node.left) < 0)node.left = rotateLeft(node.left);
                //rotateRight
                node = rotateRight(node);
            }
            return node;
        }
    }
     
     
    private int height(AVLNode<T> node){
        return node == null ? 0 : node.height;
        
    }
    
    
    
    private int diffHeight(AVLNode<T> node){
        return node == null ? 0 : height(node.left) -height(node.right);
    }
    
    
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
            //raiz
            AVLNode r = node.right;
            node.right = r.left;
            r.left = node;
            node.height = Math.max(height(node.left), height(node.right)) + 1 ;
            r.height = Math.max(height(r.left), height(r.right)) + 1;
            return r;

    } 

    private AVLNode<T> rotateRight(AVLNode<T> node) {
            //raiz
           AVLNode r = node.left; 
           node.left = r.right;
           r.right = node;
           node.height = Math.max(height(node.left), height(node.right)) +1;
           r.height = Math.max(height(r.left), height(r.right)) + 1;
           return r;

    }
  
    public ArrayList<T> getListCoincidence(String toSearch) {
        AVLNode <T> newRoot = getNodeCoincidence(toSearch, root);
        BinaryTree<T> subTree = new BinaryTree<>();
        subTree.root = newRoot;
        ArrayList<T> toReturn = new ArrayList<>();
        for(T item: subTree) {
            if(item.toString().startsWith(toSearch)) toReturn.add(item);
        }
        return toReturn;
    }
    
    private AVLNode<T> getNodeCoincidence(String toSearch, AVLNode<T> current) {
        if(current == null) return null;
        else if(current.item.toString().startsWith(toSearch)) return current;
        else {
            if(toSearch.compareTo(current.item.toString()) < 0) return getNodeCoincidence(toSearch, current.left);
            else return getNodeCoincidence(toSearch, current.right);
        }
    }

    private T getItem(T item, AVLNode<T> current){
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
  
    private AVLNode<T> find(T item,AVLNode<T> current){
        if(current == null)
            return null;
        else {
            if(item.compareTo(current.item)==0) return current;
            else if(item.compareTo(current.item)<0) return find(item, current.left);
            return find(item, current.right);
        }
    }
    
    
    public AVLNode<T> find(T item){
        return find(item, root);
    }
    
   
    private AVLNode<T>findParent(T toFind){
        AVLNode<T> node = find(toFind);
        return findParent(root, null, node);
    }
    
    private AVLNode<T>findParent(AVLNode<T> current,AVLNode<T> parent,AVLNode<T> toFind){
        if(current == null)
            return null;
        else
            if(toFind.item.compareTo(current.item)==0) return parent;
            else if(toFind.item.compareTo(current.item)<0)  return findParent(current.left, current, toFind);
            return findParent(current.right, current, toFind);
    }
    
    
    
    public AVLNode findSuccessor(T item){
        return findsuccessor(find(item).right, null);
    }
    
   private AVLNode<T> findsuccessor(AVLNode<T> current,AVLNode<T> parent){
        if(current == null)  return parent;
        else                 return findsuccessor(current.left, current);

    }
    
    public void delete(T item){
        deleteRecursivo(root, item);
    }
    private AVLNode<T> deleteRecursivo(AVLNode<T> node, T toDelete){
        if(root.item.compareTo(toDelete) == 0){
            node = findSuccessor(toDelete);
            AVLNode pare = findParent(node.item);
            pare.left = null;
            if(root.left != null)node.left = root.left;
            if(root.right != null)node.right = root.right;
            root = node;
            return node;
        }
        else{
              if (node == null)   return node;
              if (node.item.compareTo(toDelete) > 0)       node.left = deleteRecursivo(node.left, toDelete);
              else if (node.item.compareTo(toDelete) < 0)  node.right = deleteRecursivo(node.right, toDelete);
              else {
                  if(node.left == null)       return node.right;
                  else if(node.right == null) return node.left;
                  else{
                      node.item = (T)findSuccessor(node.right.item).item;
                      node.right = deleteRecursivo(node.right, node.item);
                  }
              }
              return node;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Queue<AVLNode<T>> list = new LinkedList<>();
            {list.add(root);}
            @Override
            public boolean hasNext() {
                return list.peek() != null;
            }

            @Override
            public T next() {
                AVLNode<T> node = list.remove();
                if(node.left != null)list.add(node.left);
                if(node.right != null)list.add(node.right);
                return node.item;
              }
        };

    }
    public String toStringPreOrder() {
        return toStringPreOrder(root);
    }

    private String toStringPreOrder(AVLNode<T> node) {
        if (node == null) {
            return "";
        }
        String r = "";
        r += node.item + " level " +node.height + " ";
        r += toStringPreOrder(node.left) + "";
        r += toStringPreOrder(node.right)+"";
        return r;
    }
      public String toStringInOrder() {
        return toStringInOrder(root);
    }

    private String toStringInOrder(AVLNode<T> node) {
        if (node == null) {
            return "";
        }
        String r = "";
        r += toStringInOrder(node.left) + "";
        r += node.item + " level " + node.height + " ";
        r += toStringInOrder(node.right)+"";
        return r  ;
    }

}
    
    
    
    
    
    
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
