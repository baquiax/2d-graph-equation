import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
public class Graph {
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
	System.out.print("Tamaño de la grafica en PX: ");
	int size = Integer.parseInt(input.nextLine());
	System.out.print("Valor maximo en las ordenadas: ");
	double value = input.nextDouble();
	System.out.println("Indique una lista de funciones a graficar. Escriba <graph now> para graficar.");
	input.useDelimiter("\n");
	ArrayList<String> equations = new ArrayList<String>();	
	while(true) {
	    System.out.print("y = ");
	    String equation = input.next();
	    if (equation.trim().equals("graph now")) {
		break;
	    }
	    equations.add(equation);
	}

	JFrame frame = new JFrame("2D Graph");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GraphPane panel = new GraphPane(size, value, equations);
	frame.getContentPane().add(panel);
	
	((JComponent)frame.getContentPane()).setBorder(new EmptyBorder(40, 40, 40, 40) );  
	frame.pack();
	frame.setVisible(true);
    }
}