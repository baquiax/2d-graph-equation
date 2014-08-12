import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.Random;

public class GraphPane extends JPanel{
    
    private int frameSize;
    private ArrayList<String> equations;
    
    public GraphPane (int frameSize, ArrayList<String> equations){
	super();
	this.frameSize = frameSize;
	this.equations = equations;
	setPreferredSize(new Dimension(this.frameSize, this.frameSize));
    }
    
    public void paintComponent(Graphics g){
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, frameSize, frameSize);
	
	int middleSize = this.frameSize/2;
	
	g.setColor(Color.WHITE);
	g.drawLine(middleSize, 0, middleSize, this.frameSize); //Y Axis
	g.drawLine(0, middleSize, this.frameSize, middleSize); //X Axis		

	System.out.println("INIT GRAPH...");
	for(String equation: equations) {
	    System.out.println("[EQ] => " + equation);
	    Random r = new Random();
	    Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
	    g.setColor(randomColor);

	    //Split implicit equation by =.  e.g y + x  = xy + 1 => [y + x, xy + 1]
	    String[] eParts =  equation.split("=");
	    
	    //Left to rigth.
	    int size = 1;
	    for (int xAxis = 0 ; xAxis < this.frameSize; xAxis += size) {
		for (int yAxis = 0; yAxis < middleSize; yAxis += size) {
		    double firstValue, secondValue;
		    Expression e;
		    
		    //From origin to the bottom.
		    try {
			e = new ExpressionBuilder(eParts[0])
			    .variables("x", "y")
			    .build()
			    .setVariable("x", xAxis - middleSize)
			    .setVariable("y", yAxis);
			
			firstValue = e.evaluate();

			e = new ExpressionBuilder(eParts[1])
			    .variables("x", "y")
			    .build()
			    .setVariable("x", xAxis - middleSize)
			    .setVariable("y", yAxis);
			
			secondValue = e.evaluate();


			if (!Double.isNaN(firstValue) && !Double.isNaN(secondValue) && firstValue == secondValue) {
			    System.out.println("[1:2] >> f(" + xAxis + ", " + yAxis + "); " + firstValue + " = " + secondValue );
			    g.drawLine(xAxis, middleSize + yAxis,	
				       xAxis, middleSize + yAxis);
			}
		    } catch (Exception ex) {

		    }
		    
		    //From origin to the top.
		    try {
			e = new ExpressionBuilder(eParts[0])
			    .variables("x", "y")
			    .build()
			    .setVariable("x", xAxis - middleSize)
			    .setVariable("y", yAxis * -1);			
			
			firstValue = e.evaluate();

			e = new ExpressionBuilder(eParts[1])
			    .variables("x", "y")
			    .build()
			    .setVariable("x", xAxis - middleSize)
			    .setVariable("y", yAxis * -1);

			secondValue = e.evaluate();
			if (!Double.isNaN(firstValue) && !Double.isNaN(secondValue) && firstValue == secondValue) {
			    System.out.println("[1:2] >> f(" + xAxis + ", " + (middleSize - yAxis) + "); " + firstValue + " = " + secondValue );
			    g.drawLine(xAxis, middleSize - yAxis,	
				       xAxis, middleSize - yAxis);
			}
			
		    } catch (Exception ex) {

		    }    
		}
	    }
	}
	System.out.println("END GRAPH...");
    }    
}