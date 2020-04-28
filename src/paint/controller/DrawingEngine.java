/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import paint.model.Shape;

/**
 *
 * @author ayous
 */
public interface DrawingEngine {
    
    public void refresh();
    public void addShape(Shape shape);
    public void removeShape(Shape shape);
    public void updateShape(Shape oldShape,Shape newShape);
    public Shape[] getShapes();
    
  
    
    public java.util.List<Class<? extends Shape>> getSupportedShapes();
    
    
}
