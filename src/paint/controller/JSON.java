/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.Square;
import paint.model.Triangle;
import paint.model.shapes;
import paint.view.DrawingPanel;

/**
 *

 */
public class JSON implements SaveAndLoad{
    DrawingPanel x;
  JSONArray array= new JSONArray();
      public ArrayList<shapes> shapearr = new ArrayList<>();

  
    @Override
    public void save() {
        
     JSONObject shape = new JSONObject();
    //JSONArray array= new JSONArray();
     
     for(int i=0;i<x.shapes.size();i++) 
     {
         JSONObject obj = new JSONObject();
         obj.put("Type",""+x.shapes.get(i).getClass().getSimpleName());
         obj.put("X Coordinate", ""+x.shapes.get(i).getPoint().getX());
         obj.put("Y Coordinate", ""+x.shapes.get(i).getPoint().getY());
         obj.put("Blue Color", ""+x.shapes.get(i).getColor().getBlue());
         obj.put("Red Color", ""+x.shapes.get(i).getColor().getRed());
         obj.put("Green Color", ""+x.shapes.get(i).getColor().getGreen());
         obj.put("Fill",""+x.shapes.get(i).isFill());
         Map<String,Double> properties= x.shapes.get(i).getProperties();
         Set set= properties.entrySet();
         Iterator iterator=set.iterator();
          while(iterator.hasNext())
          {
              Map.Entry mapEntry=(Map.Entry)iterator.next();
              obj.put(mapEntry.getKey(), ""+mapEntry.getValue());
          }
         
     array.add(obj);
         System.out.println("\n");
     }
     shape.put("Shape", array);
         try{
             File f=new File("testt.json");
             FileWriter fw=new FileWriter(f);
             
         fw.write(shape.toJSONString());
         fw.flush();
         }
         catch(IOException E){
             System.out.println(E);
         }  
     }



    @Override
    public ArrayList<shapes> load()
    {
       
     JSONParser parser= new JSONParser();
     try{
         Object obj= parser.parse(new FileReader("testt.json"));
         JSONObject jsonObj= (JSONObject) obj;
    
         JSONArray shapesarray= (JSONArray) jsonObj.get("Shape");

         for (int i = 0; i < shapesarray.size(); i++) {
             JSONObject js=(JSONObject) shapesarray.get(i);
            if(js.get("Type").equals("Circle"))
            {
                Circle c = new Circle();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                c.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Radius",Double.parseDouble(js.get("Radius").toString()));
                c.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                c.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 c.setFill(true);
                c.setFillColor(t);
                }
                shapearr.add(c);
            
         }
           else if(js.get("Type").equals("Rectangle"))
            {
                Rectangle r= new Rectangle();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                r.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Length",Double.parseDouble(js.get("Length").toString()));
                properties.put("Width",Double.parseDouble(js.get("Width").toString()));
                r.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                r.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 r.setFill(true);
                r.setFillColor(t);
                }
                shapearr.add(r);
            }
           else if(js.get("Type").equals("Ellipse"))
            {
                Ellipse ell= new Ellipse();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                ell.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Length",Double.parseDouble(js.get("Length").toString()));
                properties.put("Width",Double.parseDouble(js.get("Width").toString()));
                ell.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                ell.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 ell.setFill(true);
                ell.setFillColor(t);
                }
                shapearr.add(ell);
            }
            
            else if(js.get("Type").equals("Square"))
            {
                Square sq= new Square();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                sq.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Side",Double.parseDouble(js.get("Side").toString()));
                sq.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                sq.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 sq.setFill(true);
                sq.setFillColor(t);
                }
                shapearr.add(sq);
            }
            else if(js.get("Type").equals("Line"))
            {
                Line l= new Line();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                l.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Second Point X",Double.parseDouble(js.get("Second Point X").toString()));
                properties.put("Second Point Y",Double.parseDouble(js.get("Second Point Y").toString()));
                l.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                l.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 l.setFill(true);
                l.setFillColor(t);
                }
                shapearr.add(l);
            }
            else if(js.get("Type").equals("Triangle"))
            {
                Triangle tr= new Triangle();
                Point s = new Point((int)Double.parseDouble(js.get("X Coordinate").toString()),(int)Double.parseDouble(js.get("Y Coordinate").toString()));
                tr.setPoint(s);
                Map<String, Double> properties =new HashMap<>();
                properties.put("Second Point X",Double.parseDouble(js.get("Second Point X").toString()));
                properties.put("Second Point Y",Double.parseDouble(js.get("Second Point Y").toString()));
                 properties.put("Third Point X",Double.parseDouble(js.get("Third Point X").toString()));
                properties.put("Third Point Y",Double.parseDouble(js.get("Third Point Y").toString()));
                tr.setProperties(properties);
                Color t=new Color(Integer.parseInt(js.get("Red Color").toString()),Integer.parseInt(js.get("Green Color").toString()),Integer.parseInt(js.get("Blue Color").toString()));
                tr.setColor(t);
                 String b=  js.get("Fill").toString();
                if(b.equals("true")){
                 tr.setFill(true);
                tr.setFillColor(t);
                }
                shapearr.add(tr);
            }
            
           

     }}
     catch(Exception e){
         
     }
     return shapearr;
    }

   
    
}
