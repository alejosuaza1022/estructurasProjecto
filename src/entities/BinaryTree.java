
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

    private BinaryNode<T> find(T item,BinaryNode<T> current){
        if(current == null)
            return null;
        else {
            if(item.compareTo(current.item)==0) return current;
            else if(item.compareTo(current.item)<0) return find(item, current.left);
            return find(item, current.right);
        }
    }
    
    
    public BinaryNode<T> find(T item){
        return find(item, root);
    }
    
   
    private BinaryNode<T>findParent(BinaryNode<T> current,BinaryNode<T> parent,BinaryNode<T> toFind){
        if(current == null)
            return null;
        else
            if(toFind.item.compareTo(current.item)==0) return parent;
            else if(toFind.item.compareTo(current.item)<0)  return findParent(current.left, current, toFind);
            return findParent(current.right, current, toFind);
    }
    
    
    private BinaryNode<T>findParent(T toFind){
        BinaryNode<T> node = find(toFind);
        return findParent(root, null, node);
    }
    
   private BinaryNode<T> findsuccessor(BinaryNode<T> current,BinaryNode<T> parent){
        if(current == null)  return parent;
        else                 return findsuccessor(current.left, current);

    }
   
   
    public BinaryNode findSuccessor(T item){
        return findsuccessor(find(item).right, null);
    }
    public void delete(T item){
        deleteRecursivo(root, item);
    }
     private BinaryNode<T> deleteRecursivo(BinaryNode<T> node, T toDelete){
        if(root.item.compareTo(toDelete) == 0){
            node = findSuccessor(toDelete);
            BinaryNode pare = findParent(node.item);
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
    
    
    
    
    
    
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
