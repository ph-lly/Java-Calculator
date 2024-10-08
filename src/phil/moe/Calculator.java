package phil.moe;
import javax.imageio.ImageIO;
/**
 * 
 * Calculator program
 * @author Phillip
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Calculator implements ActionListener{

	JFrame frame;
	JTextField textField;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton addButton, subButton, multButton, divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	
	Font myFont = new Font("", Font.BOLD, 30);
	
	double num2=0,result=0;
	Double num1 = 0.0;
	char operator;
	
	
	public Calculator(){
		frame = new JFrame("Phil's Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,550);
		frame.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(50,25,300,50);
		textField.setFont(myFont);
		textField.setEditable(false); //stops user from typing textbox
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		multButton = new JButton("x");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		negButton = new JButton("Neg");

		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = multButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		for(int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		negButton.setBounds(50,430,100,50);
		delButton.setBounds(150,430,100,50);
		clrButton.setBounds(250,430,100,50);
		
		panel = new JPanel();
		panel.setBounds(50,100,300,300);
		
		panel.setLayout(new GridLayout(4,4,10,10));
		
		// adding number buttons, first row
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		
		//second row
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		
		//third row
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(multButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		frame.setResizable(false);
		frame.add(panel);
		frame.add(negButton);
		frame.add(delButton);
		frame.add(clrButton);
		frame.add(textField);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); //center when open
		/**
		try {
			BufferedImage myPicture = ImageIO.read(this.getClass().getResource("calc.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		**/
	}

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}

	@Override
	//Calculator functionality
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
		}
		if(e.getSource()== decButton && !(textField.getText().contains(".")))  {		
			textField.setText(textField.getText().concat("."));
		}
		if(e.getSource()== addButton)  {
			num1 = Double.parseDouble(textField.getText());
			operator = '+';
			textField.setText("");
		}
		if(e.getSource()== subButton)  {
			num1 = Double.parseDouble(textField.getText());
			operator = '-';
			textField.setText("");
		}
		if(e.getSource()== multButton)  {
			num1 = Double.parseDouble(textField.getText());
			operator = 'x';
			textField.setText("");
		}
		if(e.getSource()== divButton)  {
			num1 = Double.parseDouble(textField.getText());
			operator = '/';
			textField.setText("");
		}
		if(e.getSource()== equButton)  {
			num2 = Double.parseDouble(textField.getText());
			
			switch(operator) {
			
			case '+':
				result = num1+num2;
				break;
			case '-':
				result = num1-num2;
				break;
			case 'x':
				result = num1*num2;
				break;
			case '/':
				result = num1/num2;
				break;
			}
			textField.setText(String.valueOf(result));
			num1 = result; //continue if we add more numbers
		}
		if(e.getSource() == clrButton) {
			textField.setText("");
		}
		if(e.getSource() == delButton) {
			String string = textField.getText();
			textField.setText("");
			for(int i = 0; i < string.length() - 1; i++) {
				textField.setText(textField.getText()+string.charAt(i)); 
			}
		}
		if(e.getSource() == negButton) {
			double temp = Double.parseDouble(textField.getText());
			temp *= -1;
			textField.setText(String.valueOf(temp));
		}
	}

}
