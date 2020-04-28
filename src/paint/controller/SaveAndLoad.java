/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.ArrayList;
import paint.model.shapes;

/**
 *
 * @author Monkia
 */
public interface SaveAndLoad {
      public void save();
      public ArrayList<shapes> load();

    
}
