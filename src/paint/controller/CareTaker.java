/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monkia
 */
public class CareTaker {
     private List<Memento> mementoList=new ArrayList<Memento>();
    public void add(Memento state,int index){
        if(index>19)
            mementoList.remove(0);
        mementoList.add(state);
    }
    public Memento get(int index)
    {if(index>19)
         return mementoList.get(19);
        return mementoList.get(index);
    }
}
