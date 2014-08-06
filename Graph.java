import javax.swing.*;
import java.util.Scanner;
import javax.swing.border.*;
public class Graph {
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
	System.out.print("Tamaño de la grafica en PX: ");
	int size = Integer.parseInt(input.nextLine());
	System.out.print("Valor maximo en las ordenadas: ");
	double value = input.nextDouble();
	System.out.print("Ecuación de \"y\" a graficar : ");
	String equation = input.next();
	input.nextLine();
	JFrame frame = new JFrame("2D Graph");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	GraphPane panel = new GraphPane(size, value, equation);
	frame.getContentPane().add(panel);
	
	((JComponent)frame.getContentPane()).setBorder(new EmptyBorder(40, 40, 40, 40) );  
	frame.pack();
	frame.setVisible(true);
    }
}