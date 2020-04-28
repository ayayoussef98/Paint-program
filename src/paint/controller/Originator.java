/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.ArrayList;
import paint.model.Shape;
import paint.model.shapes;


/**
 *
 * @author Monkia
 */
public class Originator {
    private ArrayList<shapes> state;
    public void setState(ArrayList<shapes> state)
    {
        this.state=state;
    }

    public ArrayList<shapes> getState() {
        return state;
    }
    public Memento saveStateToMemento(){
        return new Memento(state);
    }
    public ArrayList<shapes> getStateFromMemento(Memento memento)
    {
        state=memento.getState();
        return state;
    }
}

    

