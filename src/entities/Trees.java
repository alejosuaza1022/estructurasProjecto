/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author asb1022
 */
public class Trees {
    
    
    ///////////////
    /////////singleton 
    //////////////
    
    private static Trees InstanceTress = null;

    private Trees() {
        keyWords = new BinaryTree<>();
        urls = new BinaryTree<>();
        listKeyWords = new TreeSet<>();
    }

    public static Trees getInstance() {
        if (InstanceTress == null)   InstanceTress = new Trees();
        return InstanceTress;
    }
    
    ///////////////
    /////////atributtes
    //////////////
    
    private BinaryTree<KeyWord> keyWords;
    
    private TreeSet<KeyWord> listKeyWords;
    
    private BinaryTree<Url> urls;
    
    ///////////////
    /////////getters and setters
    //////////////
    public BinaryTree<KeyWord> getKeyWordsTree() {
        return keyWords;
    }

    public BinaryTree<Url> getUrlTree() {
        return urls;
    }

    public ArrayList<Url> searchByKeyWord(String ... names){
        TreeSet<Url> toReturn = new TreeSet<>(); 
        for(String name : names) {
            if(listKeyWords.contains(new KeyWord(name))) {
                KeyWord word = new KeyWord(name);
                word = keyWords.getItem(word);
                toReturn.addAll(word.getUrls());
            }
        }
        return new ArrayList<>(toReturn);
    }
    
    public ArrayList<Url> searchByUrl(String toSearch) {
        return urls.getListCoincidence(toSearch);
    }
    
    public ArrayList<KeyWord> getKeyWordList() {
        return new ArrayList<>(listKeyWords);
    }
    
    public void addKeyWord(KeyWord k) {
        if(listKeyWords.add(k));        keyWords.insert(k);

    }
   
}
