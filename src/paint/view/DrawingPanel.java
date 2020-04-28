/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.view;
import paint.model.Line;
import paint.model.Ellipse;
import paint.model.Square;
import paint.model.Circle;
import paint.model.Shape;
import paint.model.Triangle;
import paint.model.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import paint.controller.DrawingEngine;
import paint.model.ShapeFactory;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import javax.swing.JOptionPane;
import paint.controller.CareTaker;
import paint.controller.Memento;
import paint.controller.Originator;
import paint.model.shapes;


/**
 *
 * @author ayous
 */
public class DrawingPanel extends JPanel implements DrawingEngine, MouseListener, MouseMotionListener {

    protected static shapes currentShape;
    private Double newLength;
    private Double newWidth;
    ArrayList<shapes>states=new ArrayList<>();
   Originator org=new Originator();
   
   CareTaker ct=new CareTaker();
   
    public Double getNewLength() {
        return newLength;
    }

    public void setNewLength(Double newLength) {
        this.newLength = newLength;
    }

    public Double getNewWidth() {
        return newWidth;
    }

    public void setNewWidth(Double newWidth) {
        this.newWidth = newWidth;
    }
    
    
    
   

    public DrawingPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public static ArrayList<shapes> shapes = new ArrayList<>();
    Resize r;
    

    
    
    //MEMENTO RELATED//////////////
    public ArrayList<Memento> memento  = new ArrayList<>();
    int index;
    

   

    public void setShapes(ArrayList<shapes> shapes) {
        this.shapes = shapes;
    }
    
    private int mode = 0;
    private String type;
    Point firstPoint;
    Point secondPoint;
    Line s;
    Graphics g;
    Boolean isFilled;
    //Triangle t= new Triangle(firstPoint,false,Color.BLACK);
    private Color select = Color.BLACK;

    protected Map<String, Double> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSelect(Color select) {
        this.select = select;
    }

    public Color getSelect() {
        return select;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).fill == true) {
                shapes.get(i).fillDraw(g);
            } else {
                shapes.get(i).draw(g);
            }
        }
    }

    @Override
    public void refresh() {
        repaint();

    }

    @Override
    public void addShape(Shape shape) {

       shapes.add((shapes) shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public void updateShape(Shape oldShape, Shape newShape) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Point selectedPoint = new Point();
        selectedPoint.x = e.getX();
        selectedPoint.y = e.getY();
        int i;
        if(mode==9)
        {
            repaint();
        }
//          if(currentShape == null&&mode==8){
//            JOptionPane.showMessageDialog(null, "No Shape was selected!");
//        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ShapeFactory sf = ShapeFactory.getInstance();
        Shape ss = sf.getShape(type);
        if (mode == 0) {
            return;
             
        }
        if (isFilled == true) {
            if (firstPoint == null) {
                firstPoint = new Point(e.getX(), e.getY());
            }
//            if(mode==0){
//                Joption
//                return;
//            } 
            if (mode == 1) {
                Circle c = (Circle) ss;
                c.setPosition(firstPoint);
                c.setFill(true);
                c.setColor(select);
                c.setFillColor(select);
                shapes.add(c);
            }
            if (mode == 2) {
                Ellipse ell = (Ellipse) ss;
                //Ellipse ell = new Ellipse(0,0,firstPoint,true,select);
                ell.setPosition(firstPoint);
                ell.setFill(true);
                ell.setColor(select);
                ell.setFillColor(select);
                shapes.add(ell);
            }

            if (mode == 3) {
                //Rectangle r = new Rectangle(0,0,firstPoint,true,select);
                Rectangle r = (Rectangle) ss;
                r.setFill(true);
                r.setFillColor(select);
                r.setPosition(firstPoint);
                r.setColor(select);
                shapes.add(r);
                states.add(r);
                org.setState(states);
                
            }
            if (mode == 4) {
                //Square s = new Square(0,firstPoint,true,select);
                Square s = (Square) ss;
                s.setPosition(firstPoint);
                s.setFill(true);
                s.setColor(select);
                s.setFillColor(select);
                shapes.add(s);
            }
            if (mode == 5) {
                // Line l= new Line(firstPoint, firstPoint, true, select);
                Line l = (Line) ss;
                l.setPosition(firstPoint);
                //l.fill=true;
                l.setFillColor(select);
                l.setColor(select);
                // l.setSecPoint(firstPoint);
                shapes.add(l);
            }
            if (mode == 6) {
                //Triangle t= new Triangle(firstPoint,firstPoint,firstPoint,true,select);
                Triangle t = (Triangle) ss;
                t.setPosition(firstPoint);
                t.setFill(true);
                t.setColor(select);
                t.setFillColor(select);
                 Map<String, Double> m =new HashMap<>();
            m.put("Second Point X",firstPoint.getX());
             m.put("Second Point Y",firstPoint.getY());
              m.put("Third Point X",firstPoint.getX());
               m.put("Third Point Y",firstPoint.getY());
               t.setProperties(m);
             
                shapes.add(t);
            } else if (mode == 7) {
                firstPoint = new Point(e.getX(), e.getY());
                for (int i = 0; i < shapes.size(); i++) {

                    if (shapes.get(i).inShape(e.getX(), e.getY())) {
                        System.out.println("ayaa" + i);
                        currentShape = shapes.get(i);
                        break;
                    }

                }
               
        

            }

            // Rectangle2D.Double r= new Rectangle2D.Double(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
        } else if (isFilled == false) {
            if (firstPoint == null) {
                firstPoint = new Point(e.getX(), e.getY());
            }
            if (mode == 1) {
                Circle c = (Circle) ss;
                c.setPosition(firstPoint);
                c.setFill(false);
                c.setColor(select);
                shapes.add(c);
            }
            if (mode == 2) {
                Ellipse ell = (Ellipse) ss;
                ell.setPosition(firstPoint);
                ell.setFill(false);
                ell.setColor(select);
                shapes.add(ell);
            }

            if (mode == 3) {
                Rectangle r = (Rectangle) ss;
                r.setFill(false);
                r.setPosition(firstPoint);
                r.setColor(select);
                shapes.add(r);
            }
            if (mode == 4) {
                //Square s = new Square(0,firstPoint,true,select);
                Square s = (Square) ss;
                s.setPosition(firstPoint);
                s.setFill(false);
                s.setColor(select);
                shapes.add(s);
            }
            if (mode == 5) {
                // Line l= new Line(firstPoint, firstPoint, true, select);
                Line l = (Line) ss;
                l.setPosition(firstPoint);
                l.setFill(false);
                l.setColor(select);
                // l.setSecPoint(firstPoint);
                shapes.add(l);
            }
            if (mode == 6) {
                //Triangle t= new Triangle(firstPoint,firstPoint,firstPoint,true,select);
                Triangle t = (Triangle) ss;
                t.setPosition(firstPoint);
                t.setFill(false);
                t.setColor(select);
                Map<String, Double> a =new HashMap<>();
            a.put("Second Point X",firstPoint.getX());
             a.put("Second Point Y",firstPoint.getY());
              a.put("Third Point X",firstPoint.getX());
               a.put("Third Point Y",firstPoint.getY());
               t.setProperties(a);
             
                shapes.add(t);
            }
            if (mode == 7) {
                firstPoint = new Point(e.getX(), e.getY());
                for (int i = 0; i < shapes.size(); i++) {

                    if (shapes.get(i).inShape(e.getX(), e.getY())) {
                        //System.out.println("ayaa" + i);
                        currentShape = shapes.get(i);
                        break;
                    }
                } }
            if( mode==9){
                resizeShape();
                repaint();
            }
           
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
        //mode= 0;
        firstPoint = null;
        secondPoint = null;
        if(mode==9){
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        properties = new HashMap<>();

        if (mode == 1) {
            secondPoint = new Point(e.getX(), e.getY());
            Circle c = (Circle) shapes.get(shapes.size() - 1);
            double rad1 = (int) Math.abs((secondPoint.getX() - firstPoint.getX()));
            double rad2 = (int) Math.abs((secondPoint.getY() - firstPoint.getY()));
            rad1 = Math.min(rad1, rad2);
            int x;
            if (firstPoint.getX() > secondPoint.getX()) {
                x = (int) (firstPoint.getX() - rad1);
            } else {
                x = (int) firstPoint.getX();
            }
            int y;
            if (firstPoint.getY() > secondPoint.getY()) {
                y = (int) (firstPoint.getY() - rad1);
            } else {
                y = (int) firstPoint.getY();
            }

            Point temp = new Point(x, y);
            properties.put("Radius", rad1);
            c.setPosition(temp);
            c.setProperties(properties);
            repaint();
        }
        if (mode == 2) {
            Ellipse ell = (Ellipse) shapes.get(shapes.size() - 1);
            secondPoint = new Point(e.getX(), e.getY());
            int p1 = (int) Math.min(firstPoint.getX(), secondPoint.getX());
            int p2 = (int) Math.min(firstPoint.getY(), secondPoint.getY());
            Point firstTemp = new Point(p1, p2);
            if (secondPoint.getX() <= firstPoint.getX() && secondPoint.getY() <= firstPoint.getY()) {
                ell.setPosition(secondPoint);
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                ell.setProperties(properties);
                repaint();
            } else if (secondPoint.getX() >= firstPoint.getX() && secondPoint.getY() <= firstPoint.getY()) {
                ell.setPosition(firstTemp);
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                ell.setProperties(properties);
                repaint();
            } else if (secondPoint.getX() <= firstPoint.getX() && secondPoint.getY() >= firstPoint.getY()) {
                ell.setPosition(firstTemp);
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                ell.setProperties(properties);
                repaint();
            } else {
                properties.put("Width", Math.abs(secondPoint.getX() - firstPoint.getX()));
                properties.put("Length", Math.abs(secondPoint.getY() - firstPoint.getY()));
                ell.setProperties(properties);
                repaint();
            }

            repaint();
        }

        if (mode == 3) {
            Rectangle r = (Rectangle) shapes.get(shapes.size() - 1);
            secondPoint = new Point(e.getX(), e.getY());
            int p1 = (int) Math.min(firstPoint.getX(), secondPoint.getX());
            int p2 = (int) Math.min(firstPoint.getY(), secondPoint.getY());
            Point firstTemp = new Point(p1, p2);
            if (secondPoint.getX() <= firstPoint.getX() && secondPoint.getY() <= firstPoint.getY()) {
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                r.setPosition(secondPoint);
                r.setProperties(properties);

                repaint();
            } else if (secondPoint.getX() >= firstPoint.getX() && secondPoint.getY() <= firstPoint.getY()) {
                r.setProperties(properties);
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                r.setPosition(firstTemp);
                repaint();

            } else if (secondPoint.getX() <= firstPoint.getX() && secondPoint.getY() >= firstPoint.getY()) {
                r.setProperties(properties);
                properties.put("Width", Math.abs(firstPoint.getX() - secondPoint.getX()));
                properties.put("Length", Math.abs(firstPoint.getY() - secondPoint.getY()));
                r.setPosition(firstTemp);
                repaint();
            } else {
                r.setPosition(firstTemp);
                properties.put("Width", Math.abs(secondPoint.getX() - firstPoint.getX()));
                properties.put("Length", Math.abs(secondPoint.getY() - firstPoint.getY()));
                r.setProperties(properties);

                repaint();
            }

        }
        if (mode == 4) {
            Square s = (Square) shapes.get(shapes.size() - 1);
            secondPoint = new Point(e.getX(), e.getY());
            double p1 = (int) Math.abs(firstPoint.getX() - secondPoint.getX());

            double p2 = (int) Math.abs(firstPoint.getY() - secondPoint.getY());
            p1 = Math.min(p1, p2);
            int x;
            if (firstPoint.getX() > secondPoint.getX()) {
                x = (int) (firstPoint.getX() - p1);
            } else {
                x = (int) firstPoint.getX();
            }
            int y;
            if (firstPoint.getY() > secondPoint.getY()) {
                y = (int) (firstPoint.getY() - p1);
            } else {
                y = (int) firstPoint.getY();
            }

            Point temp = new Point(x, y);
            s.setPosition(temp);
            properties.put("Side", p1);
            s.setProperties(properties);
            repaint();
        }

        if (mode == 5) {
            Line l = (Line) shapes.get(shapes.size() - 1);
            //l.setSecondPoint(new Point(e.getX(), e.getY()));
            properties.put("Second Point X", (double)e.getX());
            properties.put("Second Point Y", (double)e.getY());
            l.setProperties(properties);

            repaint();
        }
        if (mode == 6) {
            Triangle t = (Triangle) shapes.get(shapes.size() - 1);
            Map<String, Double> m =new HashMap<>();
            m.put("Second Point X",(double)e.getX());
             m.put("Second Point Y",(double)e.getY());
              m.put("Third Point X",firstPoint.getX());
               m.put("Third Point Y",(double)e.getY());
               t.setProperties(m);
             

            int firstx = (int) (firstPoint.getX() + (e.getX() - firstPoint.getX()) / 2);
            int firsty = (int) firstPoint.getY();
            t.setPosition(new Point(firstx, firsty));
            repaint();
        }
         if (mode == 8) {
            int x2=0;
            int y2=0;
          
            if (currentShape != null) {
                if (currentShape instanceof Line){
                    Line l=(Line) currentShape;
                    
                    int dy=(int) Math.abs(l.getPoint().getY()-l.getProperties().get("Second Point Y").intValue());
                    int dx=(int) Math.abs(l.getPoint().getX()-l.getProperties().get("Second Point X").intValue());
                    l.setPoint(new Point(e.getX(),e.getY()));
                     Map<String, Double> m =new HashMap<>();
                  m.put("Second Point X",(double)e.getX()+dx);
                   m.put("Second Point Y",(double)e.getY()+dy);
                   l.setProperties(m);
                    //l.setSecondPoint(new Point((int)e.getX()+dx,(int)e.getY()+dy));
                }
                if(currentShape instanceof Triangle){
                    Triangle t=(Triangle)currentShape;
                    int dx=(int)t.getProperties().get("Second Point X").intValue()-(int)t.getPoint().getX();
                    int dy=(int) t.getProperties().get("Second Point Y").intValue()-(int) t.getPoint().getY();
                    t.setPoint(new Point(e.getX(),e.getY()));
                     Map<String, Double> m =new HashMap<>();
                  m.put("Second Point X",t.getPoint().getX()+dx);
                   m.put("Second Point Y",t.getPoint().getY()+dy);
                   m.put("Third Point X",t.getPoint().getX()-dx);
                   m.put("Third Point Y",t.getPoint().getY()+dy);
                   t.setProperties(m);

                    
                }
                else
                {
                   x2=e.getX();
                   y2 = e.getY();
                   currentShape.setPoint(new Point(x2,y2));
                }
                repaint();
        }}}
        public void resizeShape()
    {
        properties = new HashMap<>();
        if(currentShape == null){
            JOptionPane.showMessageDialog(null, "No Shape was selected!");
        }
        else if (currentShape instanceof Rectangle)
       { 
          Rectangle rect= new Rectangle();
          rect= (Rectangle)currentShape;
          properties.put("Width", newWidth);
          properties.put("Length", newLength);
          rect.setProperties(properties);        
          repaint();
       }
        else if (currentShape instanceof Square){
            Square sq= new Square();
            sq=(Square)currentShape;
            if(!Objects.equals(newLength, newWidth))
                JOptionPane.showMessageDialog(null, "Error! Square must have the same side dimensions.");
            else
            {
            properties.put("Side", newLength);
            properties.put("Side", newWidth);
            sq.setProperties(properties);
            repaint();
            } 
        }
        else if( currentShape instanceof Circle)
        {
            Circle c= new Circle();
            c= (Circle)currentShape;
            if(!Objects.equals(newLength, newWidth))
                JOptionPane.showMessageDialog(null, "Error! Circle must have the same radius dimensions.");
            else
            {
                properties.put("Radius", newLength);
                properties.put("Radius", newWidth);
                c.setProperties(properties);
                repaint();
            }
        }
        else if(currentShape instanceof Ellipse)
        {
            Ellipse ep=new Ellipse();
            ep=(Ellipse) currentShape;
          properties.put("Width", newWidth);
          properties.put("Length", newLength);
          ep.setProperties(properties);
                repaint();
            
        }
        else if(currentShape instanceof Line){
           Line ln=new Line();
           ln=(Line) currentShape;
            Map<String, Double> a =new HashMap<>();
                  a.put("Second Point X",(double)ln.getPoint().getX()+newWidth);
                   a.put("Second Point Y",(double)ln.getPoint().getY()+newLength);
                   ln.setProperties(a);
                   repaint();
        }
        else if(currentShape instanceof Triangle)
        {
            Triangle tri= new Triangle();
            tri= (Triangle)currentShape;
            Map<String, Double> m =new HashMap<>();
                  m.put("Second Point X",tri.getPoint().getX()+0.5*newWidth);
                   m.put("Second Point Y",tri.getPoint().getY()+newLength);
                   m.put("Third Point X",tri.getPoint().getX()-0.5*newWidth);
                   m.put("Third Point Y",tri.getPoint().getY()+newLength);
                   tri.setProperties(m);
                   repaint();
  
        }
     }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape[] getShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    void copy()
    {
        if(currentShape instanceof Rectangle){
          Rectangle rect=new Rectangle();
            shapes.add((shapes) rect.clone(currentShape));
                repaint();
        }
        else if(currentShape instanceof Circle)
        {
           Circle c=new Circle();
            shapes.add((shapes) c.clone(currentShape));
                repaint();
        }
        else if(currentShape instanceof Ellipse)
        {
           Ellipse ell=new Ellipse();
            shapes.add((shapes) ell.clone(currentShape));
                repaint();
        }
        else if(currentShape instanceof Square)
        {
           Square sq=new Square();
            shapes.add((shapes) sq.clone(currentShape));
                repaint();
        }
        else if(currentShape instanceof Line)
        {
           Line l=new Line();
            shapes.add((shapes) l.clone(currentShape));
                repaint();
        }
        else if(currentShape instanceof Triangle)
        {
           Triangle t=new Triangle ();
            shapes.add((shapes) t.clone(currentShape));
                repaint();
        }
               
               
    }

}
