/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marcin
 */
public class Kmeans {
    private ArrayList<Vector> vectorList;
    private ArrayList<Cluster> clusterList;
    private ArrayList<Vector> testVectorList;
    private HashMap<String, Stat> stats;
    private KmeansListener listener;
    private int allSum;
    private int correctSum;
    public Kmeans(int clusterNum, int vectorSize, int maxParamNum){
        this.setClusterList(new ArrayList<>());
        this.setVectorList(new ArrayList<>());
        this.setTestVectorList(new ArrayList<>());
        this.setStats(new HashMap<>());
        for(int i = 0; i <= clusterNum - 1; i++){
            this.addCluster(new Cluster(vectorSize, maxParamNum));
        }
    }

    public ArrayList<Vector> getVectorList() {
        return vectorList;
    }

    public void setVectorList(ArrayList<Vector> vectorList) {
        this.vectorList = vectorList;
    }

    public ArrayList<Cluster> getClusterList() {
        return clusterList;
    }

    public void setClusterList(ArrayList<Cluster> clusterList) {
        this.clusterList = clusterList;
    }

    public ArrayList<Vector> getTestVectorList() {
        return testVectorList;
    }

    public void setTestVectorList(ArrayList<Vector> testVectorList) {
        this.testVectorList = testVectorList;
    }

    public HashMap<String, Stat> getStats() {
        return stats;
    }

    public void setStats(HashMap<String, Stat> stats) {
        this.stats = stats;
    }

    public KmeansListener getListener() {
        return listener;
    }

    public void setListener(KmeansListener listener) {
        this.listener = listener;
    }

    public int getAllSum() {
        return allSum;
    }

    public void setAllSum(int allSum) {
        this.allSum = allSum;
    }
    
    public void increaseAllSum(){
        this.setAllSum(this.getAllSum()+1);
    }

    public int getCorrectSum() {
        return correctSum;
    }

    public void setCorrectSum(int correctSum) {
        this.correctSum = correctSum;
    }
    
    public void increaseCorrectSum(){
        this.setCorrectSum(this.getCorrectSum()+1);
    }
    
    public void addCluster(Cluster c){
        if (!this.getClusterList().contains(c))
            this.getClusterList().add(c);
    }
    
    public void addVector(Vector v){
        if (!this.getVectorList().contains(v))
            this.getVectorList().add(v);
    }
    
    public void addTestVector(Vector v){
        if (!this.getTestVectorList().contains(v))
            this.getTestVectorList().add(v);
    }
    
    public void readVectors(DataReader reader, int learningLimit){
        int all = reader.getSize();
        for(int i = 0; i <= all - 1; i++){
            String line = reader.getLine(i);
            String tag = line.substring(0, 1);
            line = line.substring(2);
            Vector v = new Vector(line);
            v.setTag(tag);
            if(i < learningLimit)
                this.addVector(v);
            else
                this.addTestVector(v);
        }
    }
    
    public Cluster getClosestCluster(Vector v){
        Cluster currentClosestCluster = null;
        double currentClosestDistance = Integer.MAX_VALUE;
        for(Cluster c: this.getClusterList()){
            double distance = c.getClusterVector().getDistance(v);
            if (distance < currentClosestDistance){
                currentClosestDistance = distance;
                currentClosestCluster = c;
            }
        }
        return currentClosestCluster;
    }
    
    public void start(){
        boolean changesMade = false;
        do{
            changesMade = false;
            for(Vector v: this.getVectorList()){
                v.setCluster(this.getClosestCluster(v));
            }
            for(Cluster c: this.getClusterList()){
                if (c.getSize() > 0){
                    if (c.updateClusterVector())
                        changesMade = true;
                }
            }
        }while(changesMade);
        if (this.getListener() != null)
            this.getListener().onLearningEnd();
        for(Vector test: this.getTestVectorList()){
            Cluster c = this.classifyVector(test);
        }
    }
    
    public Cluster classifyVector(Vector v){
        Cluster c = this.getClosestCluster(v);
        if(this.getListener() != null)
            this.getListener().onClassifyDone(v.getTag(), c.getTag());
        if (!this.getStats().containsKey(v.getTag()))
            this.getStats().put(v.getTag(), new Stat(v.getTag()));
        Stat vectorStat = this.getStats().get(v.getTag());
        if (v.getTag().equals(c.getTag())){
            vectorStat.increaseCorrect();
            this.increaseCorrectSum();
        }
        vectorStat.increaseAll();
        this.increaseAllSum();
        return c;
    }

    
    
    
    
}
