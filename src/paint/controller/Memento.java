/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import paint.model.Shape;
import paint.model.shapes;

/**
 *
 * @author Reem Mohamed Ebeid
 */
public class Memento {
    //LinkedList<shapes> state;
    private ArrayList<shapes> state;
    
    
    public Memento(ArrayList<shapes> state){
        this.state=state;
    }

    
    public ArrayList<shapes> getState(){
        return this.state;
    }
}
