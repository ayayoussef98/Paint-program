/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;

/**
 *
 * @author ayous
 */
public interface Shape {
    public void setPosition(java.awt.Point position);
    public java.awt.Point getPosition();
    
    //update shape's specific properties
    public void setProperties(java.util.Map<String, Double> properties);
    public java.util.Map<String, Double> getProperties();
    
    public void setColor(java.awt.Color color);
    public java.awt.Color getColor();
    
    public void setFillColor(java.awt.Color color);
    public java.awt.Color getFillColor();
    
    //redraw shape on canvas
    public void draw(java.awt.Graphics canvas);
    public void fillDraw(java.awt.Graphics canvas);
    
    //create a deep clone of the shape
    public Object clone(shapes s);
   

}
