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
public class KeyWord implements Comparable<KeyWord>{
    
    private String name;
    private TreeSet<Url> urls;

    public KeyWord(String name) {
        this.name = name;
        urls = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Url> getUrls() {
        return new ArrayList<>(urls);
    }
    
    public void addUrl(Url url){
        urls.add(url);
    }
    
    public void deleteUrl(Url url) {
        urls.remove(url);
    }

    @Override
    public int compareTo(KeyWord o) {
        return name.compareTo(o.name);
    }
     
    @Override
    public boolean equals(Object o) {
        if(o instanceof String) {
            return o.toString().equals(name);   
        }
            
        else if (o instanceof KeyWord) {
            return ((KeyWord) o).name.equals(name);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
