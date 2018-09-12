/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Marcin
 */
public class Cluster {
    private ArrayList<Vector> vectorList;
    private Vector clusterVector;
    public Cluster(int vectorSize, int maxParamVal){
        this.setVectorList(new ArrayList<>());
        this.setClusterVector(new Vector(vectorSize, maxParamVal));
    }

    public ArrayList<Vector> getVectorList() {
        return vectorList;
    }

    public void setVectorList(ArrayList<Vector> vectorList) {
        this.vectorList = vectorList;
    }
    
    public int getSize(){
        return this.getVectorList().size();
    }

    public Vector getClusterVector() {
        return clusterVector;
    }

    public void setClusterVector(Vector clusterVector) {
        this.clusterVector = clusterVector;
    }

    public String getTag() {
        String tag = null;
        if (this.getSize() == 0)
            return null;
        String[] tags = new String[this.getSize()];
        int i = 0;
        for(Vector v: this.getVectorList()){
            tags[i] = v.getTag();
            i++;
        }
        tag = Utils.mostCommon(Arrays.asList(tags));
        return tag;
    }
    
    public void addVector(Vector v){
        if (!this.getVectorList().contains(v)){
            this.getVectorList().add(v);
            v.setCluster(this);
        }
    }
    
    public void removeVector(Vector v){
        if (this.getVectorList().contains(v)){
            this.getVectorList().remove(v);
            v.setCluster(null);
        }
    }
    
    public boolean updateClusterVector(){
        boolean changesMade = false;
        Vector updatedVector = new Vector(this.getClusterVector().getSize());
        for (Vector v: this.getVectorList()){
            updatedVector.addVector(v);
        }
        updatedVector.divideBy(this.getSize());
        if(!this.getClusterVector().isEqual(updatedVector))
            changesMade = true;
        this.setClusterVector(updatedVector);
        return changesMade;
    }
    
    
}
