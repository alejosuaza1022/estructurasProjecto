/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author asb1022
 */
public class Url implements Comparable<Url>{
    private String name;
    private ArrayList<KeyWord> keyWords;

    public Url(String name) {
        this.name = name;
        keyWords = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<KeyWord> getKeyWords() {
        return keyWords;
    }
    
    public void AddKeyWord(KeyWord keyword){
        keyWords.add(keyword);
    }

    @Override
    public int compareTo(Url o) {
        return name.compareTo(o.name);
    }
    
    public void deleteKeyword(KeyWord keyWord){
        keyWords.remove(keyWord);
    }
    
    
}
