package paint.controller;




import java.util.ArrayList;
import java.util.Stack;
import paint.controller.CareTaker;
import paint.controller.Originator;
import paint.model.shapes;
import paint.view.DrawingPanel;

/**
 *
 */
public class UndoRedo implements Command {
    Originator originator =new Originator();
    CareTaker caretaker=new CareTaker();
        //public ArrayList<shapes> shapes = new ArrayList<>();

     public Stack<shapes> Redo= new Stack<shapes>();
     public Stack<shapes> Drawings= new Stack<shapes>();
      DrawingPanel dp;
     
    
    
    public void initiateStack(){
       for(int i=0;i<dp.shapes.size();i++)
        {Drawings.push(dp.shapes.get(i));
           
        }

    }
    @Override
    public void Undo(){
        
       shapes sh= Drawings.pop();
        Redo.push(sh);
        dp.shapes.remove(sh);
        
    }
    @Override
    public void Redo(){
        Drawings.push( Redo.pop());
        dp.shapes.add( Drawings.peek());
    }

    
    
}
