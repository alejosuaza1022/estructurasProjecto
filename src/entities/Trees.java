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
        KeyWords = new BinaryTree<>();
        url = new BinaryTree<>();
        listKeyWords = new ArrayList<>();
    }

    public static Trees getInstance() {
        if (InstanceTress == null)   InstanceTress = new Trees();
        return InstanceTress;
    }
    
    ///////////////
    /////////atributtes
    //////////////
    
    private BinaryTree<KeyWord> KeyWords;
    
    private ArrayList<KeyWord> listKeyWords;
    
    private BinaryTree<Url> url;
    
    ///////////////
    /////////getters and setters
    //////////////
    public BinaryTree<KeyWord> getKeyWordsTree() {
        return KeyWords;
    }

    public BinaryTree<Url> getUrlTree() {
        return url;
    }

    public ArrayList<Url> searchByKeyWord(String ... names){
        TreeSet<Url> toReturn = new TreeSet<>(); 
        for(String name : names) {
            KeyWord word = new KeyWord(name);
            word = KeyWords.getItem(word);
            toReturn.addAll(word.getUrls());
        }
        return new ArrayList<>(toReturn);
    }
    
    public ArrayList<Url> searchByUrl(String toSearch) {
        return url.getListCoincidence(toSearch);
    }
    
    public ArrayList<KeyWord> getKeyWordList() {
        return listKeyWords;
    }
}
