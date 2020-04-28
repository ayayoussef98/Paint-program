/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint.model;


/**
 *
 * @author Reem Mohamed Ebeid
 */
public class ShapeFactory {
    
private static ShapeFactory Factory;

    private ShapeFactory() {
    }
    public static ShapeFactory getInstance(){
        if(Factory==null)
            Factory=new ShapeFactory();
        return Factory;
    }
    
    @SuppressWarnings("empty-statement")
       public Shape getShape(String shapeName)
    {
        try{
        if(shapeName.equalsIgnoreCase("Circle"))
            return new Circle();
        else if(shapeName.equalsIgnoreCase("Ellipse"))
            return new Ellipse();
        else if(shapeName.equalsIgnoreCase("Rectangle"))
            return new Rectangle();
        else if(shapeName.equalsIgnoreCase("Square"))
            return new Square();
        else if(shapeName.equalsIgnoreCase("Line"))
            return new Line();
        else if(shapeName.equalsIgnoreCase("Triangle"))
            return new Triangle();
        else
        
            return null;}
        catch (NullPointerException e){
            
        }
        return null;
        
                
    }
}
