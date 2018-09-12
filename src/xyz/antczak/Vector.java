/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.antczak;

import java.util.Random;

/**
 *
 * @author Marcin
 */
public class Vector {
    private double[] params;
    private String tag;
    private Cluster cluster;
    private static DistanceMethod distanceMethod;
    public Vector(int size){
        this.setParams(new double[size]);
    }
    public Vector(String data){
        this.setParams(data);
    }
    public Vector(int size, int maxVal){
        this.setRandomParams(size, maxVal);
    }

    public double[] getParams() {
        return params;
    }
    
    public double getParam(int index){
        return this.getParams()[index];
    }

    public void setParams(double[] params) {
        this.params = params;
    }    
    
    public void setParams(String params) {
        String[] dataArray = params.split(",");
        this.params = new double[dataArray.length];
        for(int i = 0; i <= dataArray.length - 1; i++){
            this.params[i] = Double.parseDouble(dataArray[i]);
        }
    }
    
    public void setParam(int index, double value){
        this.getParams()[index] = value;
    }

    public static DistanceMethod getDistanceMethod() {
        return distanceMethod;
    }

    public static void setDistanceMethod(DistanceMethod distanceMethod) {
        Vector.distanceMethod = distanceMethod;
    }
    
    

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        if (this.cluster != null)
            this.cluster.removeVector(this);
        if(cluster != null)
            cluster.addVector(this);
        this.cluster = cluster;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
  
    public int getSize(){
        return getParams().length;
    }
    
    public double getDistance(Vector v){
        if (this.getSize() != v.getSize())
            return 0;
        int sum = 0;
        if (Vector.getDistanceMethod() == null){
            for(int i = 0; i <= this.getSize() - 1; i++){
                sum += Math.pow(this.getParam(i) - v.getParam(i), 2);
            }
            return Math.sqrt(sum);
        }
        return Vector.getDistanceMethod().calculateDistance(this, v);
    }
    
    public void setRandomParams(int num, int max){
        double[] params = new double[num];
        Random rand = new Random(Double.doubleToLongBits(Math.random()));
        for(int i = 0; i <= num - 1; i++){
            params[i] = rand.nextInt(max + 1);
        }
        this.setParams(params);
    }
    
    public void addVector(Vector v){
        for(int i = 0; i <= this.getSize() - 1; i++){
            this.setParam(i, this.getParam(i) + v.getParam(i));
        }
    }
    
    public void divideBy(double value){
        for(int i = 0; i <= this.getSize() - 1; i++){
            this.setParam(i, this.getParam(i) / value);
        }    
    }
    
    public boolean isEqual(Vector v){
        for(int i = 0; i <= this.getSize() - 1; i++){
            if(this.getParam(i) != v.getParam(i))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String params = "";
        for(double d: this.getParams()){
            params += d+",";
        }
        return "Vector{" + "params=" + params + '}';
    }
    
    
}
