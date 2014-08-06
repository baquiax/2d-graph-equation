import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import net.objecthunter.exp4j.*;

public class GraphPane extends JPanel{

	private int frameSize;
	private double maxAxisX;
	private String equation;

	public GraphPane (int frameSize, double xSize, String equation){
		this.frameSize = frameSize;
		this.maxAxisX = xSize;
		this.equation = equation;
		setPreferredSize(new Dimension(this.frameSize, this.frameSize));
	}
	
	public void paintComponent(Graphics g){
		g.drawLine(this.frameSize/2, 0, this.frameSize/2, this.frameSize); //Y Axis
		g.drawLine(0, this.frameSize/2, this.frameSize, this.frameSize/2); //X Axis

		double[] intervalAxis = this.getIntervals();
		int intervalSize = this.frameSize/(intervalAxis.length - 1);
		int x = 0 , y = this.frameSize/2, vA = intervalAxis.length - 1;		
		g.setFont(new Font("Sansserif", Font.PLAIN, this.frameSize/40));
		int origin = (int)(Math.round(intervalAxis.length/2.0) - 1);

		for(int i = 0; i < intervalAxis.length ; i++) {
			x = intervalSize * i;
			if (i == origin) {			
				vA--;
				continue;	
			}
			g.drawLine(x, y + 5, x, y-5); // | Intervals in X axis.			
			if(i != 5 && vA != 5) {
				if(i > origin)
					g.drawString(String.valueOf(intervalAxis[i]), x-frameSize/40, y+frameSize/21);
				else
					g.drawString(String.valueOf(intervalAxis[i]), x-frameSize/30, y+frameSize/21);

				if(vA > origin)
					g.drawString(String.valueOf(intervalAxis[i]), y-frameSize/13, x+frameSize/60);
				else
					g.drawString(String.valueOf(intervalAxis[i]), y-frameSize/12, x+frameSize/60);
			}
			g.drawLine(y+5, x, y-5, x); // ___ Intervals in Y axis
			vA--;
		}

		g.setColor(Color.RED);
		double min = (-maxAxisX), max = maxAxisX, ratio = frameSize/(max*2), fx;
        for(;min<=max;min+=0.025){
            //preventing round-off error
            min=Math.round(min*1000.0)/1000.0;
            fx=Math.round((min*min*min)*1000.0)/1000.0;

            try {
				Expression e = new ExpressionBuilder(this.equation)
					.variables("x")
					.build()
					.setVariable("x", min);
				fx = e.evaluate();
				if (!Double.isNaN(fx)) {
					System.out.println(min + " " + fx);
					g.drawLine((int)(frameSize/2+(ratio*min)), (int)(frameSize/2-(ratio*fx)),	
                    	(int)(frameSize/2+(ratio*min)), (int)(frameSize/2-(ratio*fx)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}            
        }
		//This is where I need help, I'm almost completely lost. The function has to be plotted in red	 	
	}
		
	private double[] getIntervals(){
		int nInt = 5;
		double increment = this.maxAxisX / nInt, currentInterval = -1 * (increment * nInt);
		double[] intervals = new double[(nInt * 2) + 1]; //Origin not paint
		int origin = ((int)Math.round(intervals.length/2.0)) - 1;		

		for(int i = 0; i < intervals.length ; i++){
			intervals[i] = Math.round(currentInterval * 100.0)/100.0;								
			currentInterval += increment;
			System.out.println(i + "> " + intervals[i]);
		}
		return intervals;
	}
}