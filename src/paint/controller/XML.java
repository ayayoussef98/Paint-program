/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.controller;

import java.util.ArrayList;
import paint.model.shapes;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.Line;
import paint.model.Rectangle;
import paint.model.Square;
import paint.model.Triangle;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import paint.model.shapes;
import paint.view.DrawingPanel;

/**
 *
 * @author Monkia
 */
public class XML implements SaveAndLoad {

    DrawingPanel dps;
    
    @Override
    public void save()
    {
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
//        }
        org.w3c.dom.Document d= db.newDocument();
        Element root = d.createElement("List");
        d.appendChild(root);
        for(int i=0; i<dps.shapes.size();i++){
        Element e= d.createElement("Shape");
        root.appendChild(e);
        Attr attr= d.createAttribute("Name");
        attr.setValue(dps.shapes.get(i).getClass().getSimpleName());
        e.setAttributeNode(attr);
        
        Element x= d.createElement("X-Coordinates");
        x.appendChild(d.createTextNode(Double.toString(dps.shapes.get(i).getPoint().getX())));
        e.appendChild(x);
        
        Element y= d.createElement("Y-Coordinates");
        y.appendChild(d.createTextNode(Double.toString(dps.shapes.get(i).getPoint().getY())));
        e.appendChild(y);
        
        Element bColor= d.createElement("BlueColor");
   bColor.appendChild(d.createTextNode(Double.toString(dps.shapes.get(i).getColor().getBlue())));
        e.appendChild(bColor);
        
            Element rColor= d.createElement("RedColor");
   rColor.appendChild(d.createTextNode(Double.toString(dps.shapes.get(i).getColor().getRed())));
        e.appendChild(rColor);
        
            Element gColor= d.createElement("GreenColor");
   gColor.appendChild(d.createTextNode(Double.toString(dps.shapes.get(i).getColor().getGreen())));
        e.appendChild(gColor);
        
        Element fill=d.createElement("Fill");
        fill.appendChild(d.createTextNode(Boolean.toString(dps.shapes.get(i).isFill())));
        e.appendChild(fill);
        
        Map<String,Double> properties= dps.shapes.get(i).getProperties();
        
        if (dps.shapes.get(i) instanceof Square)
        {
            Square sq= new Square();
            sq=(Square)dps.shapes.get(i);
            Element side= d.createElement("Side");
            side.appendChild(d.createTextNode(Integer.toString(sq.getProperties().get("Side").intValue())));
            e.appendChild(side);
            
        }
         if (dps.shapes.get(i) instanceof Rectangle)
        {
            Rectangle rt = new Rectangle();
            rt=(Rectangle)dps.shapes.get(i);
            Element width= d.createElement("Width");
            width.appendChild(d.createTextNode(Integer.toString(rt.getProperties().get("Width").intValue())));
            Element length= d.createElement("Length");
            length.appendChild(d.createTextNode(Integer.toString(rt.getProperties().get("Length").intValue())));
            e.appendChild(width);
            e.appendChild(length);
            
        }
           if (dps.shapes.get(i) instanceof Circle)
        {
            Circle ci = new Circle();
            ci=(Circle)dps.shapes.get(i);
            Element rad= d.createElement("Radius");
            rad.appendChild(d.createTextNode(Integer.toString(ci.getProperties().get("Radius").intValue())));  
            e.appendChild(rad);
        }
             if (dps.shapes.get(i) instanceof Ellipse)
        {
            Ellipse el = new Ellipse();
            el=(Ellipse)dps.shapes.get(i);
            Element width= d.createElement("Width");
            width.appendChild(d.createTextNode(Integer.toString(el.getProperties().get("Width").intValue())));
            Element length= d.createElement("Length");
            length.appendChild(d.createTextNode(Integer.toString(el.getProperties().get("Length").intValue())));
            e.appendChild(width);
            e.appendChild(length);
        }
             if(dps.shapes.get(i) instanceof Line)
             {
                 Line L= new Line();
                 L=(Line)dps.shapes.get(i);
                 Element secPointX = d.createElement("SecondPointX");
                 secPointX.appendChild(d.createTextNode(Integer.toString((int) L.getProperties().get("Second Point X").intValue())));
                 Element secPointY= d.createElement("SecondPointY");
                 secPointY.appendChild(d.createTextNode(Integer.toString((int)L.getProperties().get("Second Point Y").intValue())));
                 e.appendChild(secPointX);
                 e.appendChild(secPointY);
                 
                 
             }
             if(dps.shapes.get(i) instanceof Triangle)
             {
                 Triangle tr= new Triangle();
                 tr=(Triangle)dps.shapes.get(i);
                 
                 Element secPX= d.createElement("SecondPointX");
                 secPX.appendChild(d.createTextNode(Integer.toString((int) tr.getProperties().get("Second Point X").intValue())));
                 Element secPY= d.createElement("SecondPointY");
                 secPY.appendChild(d.createTextNode(Integer.toString((int) tr.getProperties().get("Second Point Y").intValue())));
                 Element thirdPX= d.createElement("ThirdPointX");
                 thirdPX.appendChild(d.createTextNode(Integer.toString((int) tr.getProperties().get("Third Point X").intValue())));
                 Element thirdPY= d.createElement("ThirdPointY");
                 thirdPY.appendChild(d.createTextNode(Integer.toString((int) tr.getProperties().get("Third Point X").intValue())));
                 e.appendChild(secPX);
                 e.appendChild(secPY);
                 e.appendChild(thirdPX);
                 e.appendChild(thirdPY);
                 
             }
            
        
        
        
    }
    
     TransformerFactory tf= TransformerFactory.newInstance();
        Transformer t= tf.newTransformer(); 
        DOMSource s= new DOMSource(d);
        
        FileWriter fos= new FileWriter("try.xml");
        StreamResult sr= new StreamResult(fos);
        t.transform(s, sr);
    } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    
    
    
     protected Map<String, Double> prop;
    
    
    @Override
    public ArrayList<shapes> load()
    {
        
        
        try {
            File f = new File("try.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("List");
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            ArrayList<shapes> xmlArray= new ArrayList<>();
            Point pt;
            double x;
            double y;
            
            
            
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
                Node nNode = nList.item(temp);
                prop = new HashMap<>();
                
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                
                
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    for(int i=0;i<nNode.getChildNodes().getLength();i++)
                    {
                        Node n =nNode.getChildNodes().item(i);
                        Element eElement = (Element) n;
                        String shapeName= eElement.getAttributes().getNamedItem("Name").getNodeValue().toString();
                        x= Double.parseDouble(eElement.getElementsByTagName("X-Coordinates").item(temp).getTextContent());
                        y= Double.parseDouble(eElement.getElementsByTagName("Y-Coordinates").item(temp).getTextContent());
                        
                        pt= new Point((int)x, (int)y);
                        // System.out.println(x);
                        if(shapeName.equalsIgnoreCase("Rectangle"))
                        {
                            Rectangle rt= new Rectangle();
                            Double width =Double.parseDouble(eElement.getElementsByTagName("Width").item(temp).getTextContent());
                            Double length= Double.parseDouble(eElement.getElementsByTagName("Length").item(temp).getTextContent());
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);                           
                            rt.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            rt.setColor(color);
                            rt.setFillColor(color);
                            prop.put("Width", width);
                            prop.put("Length", length);
                            rt.setPoint(pt);
                            rt.setPosition(pt);
                            rt.setProperties(prop);
                            xmlArray.add(rt);
                        }                       
                        else if(shapeName.equalsIgnoreCase("Square"))
                        {
                            Square sq= new Square();
                            Double sde= Double.parseDouble(eElement.getElementsByTagName("Side").item(temp).getTextContent());
                            prop.put("Side", sde);
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);  
                            sq.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            sq.setColor(color);
                            sq.setFillColor(color);
                            sq.setPoint(pt);
                            sq.setPosition(pt);
                            sq.setProperties(prop);
                            xmlArray.add(sq);
                            
                        }
                        else if(shapeName.equalsIgnoreCase("Ellipse"))
                        {
                            Ellipse el= new Ellipse();
                            Double width =Double.parseDouble(eElement.getElementsByTagName("Width").item(temp).getTextContent());
                            Double length= Double.parseDouble(eElement.getElementsByTagName("Length").item(temp).getTextContent());
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);                           
                            el.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            el.setColor(color);
                            el.setFillColor(color);
                            prop.put("Width", width);
                            prop.put("Length", length);
                            el.setPoint(pt);
                            el.setPosition(pt);
                            el.setProperties(prop);
                            xmlArray.add(el);                         
                        }
                        else if(shapeName.equalsIgnoreCase("Circle"))
                        {
                            Circle c= new Circle();
                            Double radius= Double.parseDouble(eElement.getElementsByTagName("Radius").item(temp).getTextContent());
                            prop.put("Radius", radius);
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);                           
                            c.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            c.setColor(color);
                            c.setFillColor(color);
                            c.setPoint(pt);
                            c.setPosition(pt);
                            c.setProperties(prop);
                            xmlArray.add(c);
                        }
                        else if(shapeName.equalsIgnoreCase("Line"))
                        {
                            Line l= new Line();
                            int secPX= (int) Double.parseDouble(eElement.getElementsByTagName("SecondPointX").item(temp).getTextContent());
                            int secPY= (int)Double.parseDouble(eElement.getElementsByTagName("SecondPointY").item(temp).getTextContent());
                            Point secP= new Point(secPX, secPY);
                            l.setPoint(pt);
                            l.setPosition(pt);
                            
                            Map<String, Double> m =new HashMap<>();
                m.put("Second Point X",(double)secPX);
                m.put("Second Point Y",(double)secPY);
                l.setProperties(m);
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);                           
                            l.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            l.setColor(color);
                            l.setFillColor(color);
                            xmlArray.add(l); 
                        }
                        else if(shapeName.equalsIgnoreCase("Triangle"))
                        {
                            Triangle tr= new Triangle();
                            int secPX= (int) Double.parseDouble(eElement.getElementsByTagName("SecondPointX").item(temp).getTextContent());
                            int secPY= (int)Double.parseDouble(eElement.getElementsByTagName("SecondPointY").item(temp).getTextContent());
                            int thPX= (int) Double.parseDouble(eElement.getElementsByTagName("ThirdPointX").item(temp).getTextContent());
                            int thPY= (int)Double.parseDouble(eElement.getElementsByTagName("ThirdPointY").item(temp).getTextContent());
                            Point secP= new Point(secPX, secPY);
                            Point thP= new Point(thPX, thPY);
                            tr.setPoint(pt);
                            tr.setPosition(pt);
                           
                            Map<String, Double> m =new HashMap<>();
                m.put("Second Point X",(double)secPX);
                m.put("Second Point Y",(double)secPY);
                m.put("Third Point X",(double)thPX);
                m.put("Third Point Y",(double)thPY);
                tr.setProperties(m);
                            int red= (int)Double.parseDouble(eElement.getElementsByTagName("RedColor").item(temp).getTextContent());
                            int green= (int)Double.parseDouble(eElement.getElementsByTagName("GreenColor").item(temp).getTextContent());
                            int blue=  (int)Double.parseDouble(eElement.getElementsByTagName("BlueColor").item(temp).getTextContent()); 
                            Color color;
                            color = new Color(red, green, blue);                           
                            tr.setFill(Boolean.parseBoolean(eElement.getElementsByTagName("Fill").item(temp).getTextContent()));
                            tr.setColor(color);
                            tr.setFillColor(color);
                            xmlArray.add(tr);  
                        }
                    }
                    
                }
                
            }
            return xmlArray;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
}

    

    
}
