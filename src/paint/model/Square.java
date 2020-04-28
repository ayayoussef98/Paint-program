/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ayous
 */
public class Square extends shapes implements Shape {

  // protected Point point;
    protected Map<String, Double> prop;
    //protected Color color;
    protected Color fillcolor;
  public Square(Point point, boolean fill, Color color) {
        super(point, fill, color);
    }

    
    public Square(){
        prop= new HashMap<>();
        prop.put("Side", 0.0);
       //this.fill=fill;
    }

      @Override
    public void setPosition(Point position) {
     point=position;
    }

    @Override
    public Point getPosition() {
     return point;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
     prop=properties;
    }

    @Override
    public Map<String, Double> getProperties() {
     return prop;
    }

    @Override
    public void setColor(Color color) {
      this.color=color;
    }

    @Override
    public Color getColor() {
     return color;
    }

    @Override
    public void setFillColor(Color color) {
      fillcolor=color;
    }

    @Override
    public Color getFillColor() {
      return fillcolor;
    }
     public boolean inShape(double x, double y) {
        Rectangle2D.Double s = new Rectangle2D.Double(point.getX(), point.getY(), prop.get("Side").intValue(), (int) prop.get("Side").intValue());

//Shape rect=(Shape) new Rectangle2D.Double(point.getX(),point.getY(),prop.get("Width").intValue(),(int) prop.get("Length").intValue());
        if (s.contains(x, y)) {
            return true;
        }

        return false;
    }


    @Override
    public void draw(Graphics canvas) {
        ((Graphics2D) canvas).setColor(getFillColor());
        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(getColor());
        ((Graphics2D) canvas).drawRect((int) point.getX(),(int) point.getY(),(int) prop.get("Side").intValue(),(int) prop.get("Side").intValue());
    }

    @Override
    public void fillDraw(Graphics canvas) {
        ((Graphics2D) canvas).setColor(getFillColor());
        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(getColor());
        ((Graphics2D) canvas).fillRect((int) point.getX(),(int) point.getY(),(int) prop.get("Side").intValue(),(int) prop.get("Side").intValue());
       
    }
   
       public Object clone(shapes s){
        shapes r = new Square();
        r.setColor(s.getColor());
        Point newPoint=new Point((int)s.getPoint().getX()+50,(int)s.getPoint().getY()+50);
        r.setFill(s.isFill());
        r.setFillColor(s.getFillColor());
        r.setPoint(newPoint);
         Map<String, Double> newprop = new HashMap<>();
        newprop.put("Side",(double) s.getProperties().get("Side").intValue());
        r.setProperties(newprop);
        return r;        
     }
         
     }

