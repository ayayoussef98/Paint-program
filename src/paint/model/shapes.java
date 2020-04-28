/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Reem Mohamed Ebeid
 */
public abstract class shapes implements Shape {
    public Point point;
    public boolean fill;
    public Color color;
    private String shapeName;

    public shapes() {
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    

    public shapes(Point point, boolean fill, Color color) {
        this.point = point;
        this.fill = fill;
        this.color = color;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() {
        return shapeName;
    }
    
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    public abstract boolean inShape(double x, double y); 
       public Object clone(shapes s){
        return null;
     }
}

    

