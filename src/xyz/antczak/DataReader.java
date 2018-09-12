/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcin
 */
public class DataReader {
    private ArrayList<String> data;
    public DataReader(File file){
        this.setData(new ArrayList<>());
        this.readData(file);
    }
    
    public void readData(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                this.addData(line);
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<String> getData() {
        return data;
    }

    private void setData(ArrayList<String> data) {
        this.data = data;
    }
    
    public void addData(String line){
        this.getData().add(line);
    }
    
    public int getSize(){
        return this.getData().size();
    }
    
    public String getLine(int i){
        return this.getData().get(i);
    }
    
    
}
