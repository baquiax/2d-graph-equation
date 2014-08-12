import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Graph {
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
	System.out.print("Tamaño de la grafica en PX: ");
	int size = Integer.parseInt(input.nextLine());
	System.out.println("Indique una lista de funciones a graficar. Escriba <graph now> para graficar.");
	input.useDelimiter("\n");
	ArrayList<String> equations = new ArrayList<String>();	
	while(true) {
	    String equation = input.next();
	    if (equation.trim().equals("graph now")) {
		break;
	    }
	    equations.add(equation);
	}

	JFrame frame = new JFrame("2D Graph");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GraphPane panel = new GraphPane(size, equations);
	frame.getContentPane().add(panel);
	frame.pack();
	frame.setVisible(true);
	frame.setBackground(Color.black);
    }
}