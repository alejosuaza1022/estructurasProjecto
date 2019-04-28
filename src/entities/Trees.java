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
    
    public static Trees InstanceTress = null;

    private Trees() {
        KeyWords = new BinaryTree<>();
        url = new BinaryTree<>();
    }

    public Trees getInstance() {
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
    public BinaryTree<KeyWord> getKeyWords() {
        return KeyWords;
    }

    public BinaryTree<Url> getUrl() {
        return url;
    }

    public ArrayList<Url> searchByKeyWord(String ... names){
        TreeSet<Url> listRetorno = new TreeSet<>(); 
        for (int i = 0; i < names.length; i++) {
            String name = names [i];
            KeyWord word = new KeyWord(name);
            word = KeyWords.getItem(word);
            listRetorno.addAll(word.getUrls());
         }
        ArrayList<Url> list = new ArrayList<>(listRetorno);
        return list;
    }
   
    
    
}
