/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author asb1022
 */
public class Url implements Comparable<Url>{
    
    private final String url;
    private TreeSet<KeyWord> keyWords;

    public Url(String url) {
        this.url = url;
        keyWords = new TreeSet<>();
    }

    public ArrayList<KeyWord> getKeyWords() {
        return new ArrayList<>(keyWords);
    }
    
    public void AddKeyWord(KeyWord keyword){
        keyWords.add(keyword);
    }

    @Override
    public int compareTo(Url o) {
        return url.compareTo(o.url);
    }
    
    public void deleteKeyword(KeyWord keyWord){
        keyWords.remove(keyWord);
    }
    
    @Override
    public String toString() {
        return url;
    }
}
