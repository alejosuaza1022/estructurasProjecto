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
public class KeyWord implements Comparable<KeyWord>{
    private String name;
    private ArrayList<Url> Urls;

    public KeyWord(String name) {
        this.name = name;
        Urls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Url> getUrls() {
        return Urls;
    }
    
    public void AddUrl(Url url){
        Urls.add(url);
    }

    @Override
    public int compareTo(KeyWord o) {
        return name.compareTo(o.name);
    }
     
    
}
