/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

/**
 *
 * @author Marcin
 */
public class Stat {
    private String tag;
    private int all;
    private int correct;
    public Stat(String tag){
        setTag(tag);
        setAll(0);
        setCorrect(0);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
    
    public void increaseAll(){
        this.setAll(this.getAll()+1);
    }
    
    public void increaseCorrect(){
        this.setCorrect(this.getCorrect()+1);
    }
    
    
}
