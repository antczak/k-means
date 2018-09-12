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
public interface KmeansListener {
    public void onLearningEnd();
    public void onClassifyDone(String realTag, String tag);
}
