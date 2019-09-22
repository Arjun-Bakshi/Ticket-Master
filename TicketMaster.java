import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.program.*;

//Arjun Bakshi

public class TicketMaster extends ConsoleProgram {
	
	public static void main(String[] args) {
		
	}

	private static final long serialVersionUID = 1L;

	GCanvas canvas;
	JTextField numberField;
	char[] chArray;
	int[] randArray;
	GImage matrix = new GImage("Matrix.gif",0,0);
	int points = 0;
	GLabel numberImage;
	GLabel stringImage1;
	GLabel stringImage2;
	GLabel stringImage3;
	GLabel stringImage4;
	GLabel stringImage5;
	GLabel stringImage6;
	GLabel titleImage;
	GLabel pointsImage;
	GLabel pointsImage1;
	String strArray = "";
	String text;
	int random;
	private boolean executed = false;
	private boolean guessed = false;
	private boolean match1 = false;
	private boolean match2 = false;
	private boolean match3 = false;
	private boolean match4 = false;
	private boolean match5 = false;
	private boolean match6 = false;

	int a = 0;
	int b = 0;
	int c = 0;
	int d = 0;
	int f = 0;
	int g = 0;
	char ch1;
	char ch2;
	char ch3;
	char ch4;
	char ch5;
	char ch6;

	public void init(){
		setFont("TimesNewRoman");
		setSize(1500,750);
		GridLayout grid = new GridLayout(1,2);
		setLayout(grid);
		canvas = new GCanvas();
		add(canvas);
		canvas.add(matrix);
		add(new JButton("Start"), SOUTH);
		add(new JButton("Hint"), SOUTH);
		addActionListeners();
		numberField = new JTextField(6);
		add(new JLabel("Input"), SOUTH);
		add(numberField, SOUTH);
		numberField.addActionListener(this);
		println("Ticket Master");
		println("Start the game for the program to run. Your goal is to guess the randomly generated six digit number that the program generates.");
		println("You only have one chance to guess the number. After guessing, the number will be revealed and the next number will be generated.");
		println("You will be awarded points proportional to the number of digits correctly guessed. Points will be lost if no values match.");
		println("A hint will reveal a random number located at any index at the cost of fifty points. Digits range from numbers zero to nine");
		println();
	}
	/**
	 * Executes if any of the action events occur.
	 * @param ActionEvent e indicating that an action has occurred
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Start")){
			titleImage();
		}
		if(e.getActionCommand().equals("Hint")){
			//guessed = false, the user did not guess all values
			guessed = false;
			//executed = true, the hint was used
			executed = true;
			random();
			hint(a,b,c,d,f,g);
		}
		if (e.getSource() == numberField){
			//guessed = true, the user guessed all values
			guessed = true;
			canvas.add(matrix);
			scanTicket();
			printArray(a,b,c,d,f,g);
			compareArray(chArray, randArray);
			pointsImage(points);
			numberImage();
			stringImage(ch1,ch2,ch3,ch4,ch5,ch6);
			//executed = false, the hint was not used
			executed = false;
			match1 = false;
			match2 = false;
			match3 = false;
			match4 = false;
			match5 = false;
			match6 = false;
		}
	}
	/**
	 * Changes the numberField string into "text" string.
	 * Random method executed if hint is not pressed.
	 */
	private void scanTicket(){
		text = numberField.getText();
		//Number field text is converted into char 
		stringToChar(text);
		if(executed == false){
			random();
		}
	}
	/**
	 * Compares both arrays ASCII values to determine how many many elements match.
	 * Outputs a string informing the user of the result.
	 * @param points
	 * @param chArray - elements contain "text"
	 * @param randArray - elements contain six randomly generated values
	 */
	public void compareArray(char[] chArray, int[] randArray){
		int x = 0;
		for(int i = 0; i < 6; i++){
			//Compares the elements of the char and integer array
			//Char values are subtracted by 48 due to ASCII values being compared
			chArray[i] = (char) (chArray[i] - 48);
			if(chArray[i] == randArray[i]){
				//Checks how many elements match
				x++;
				if(i == 0 && chArray[i] == randArray[i]){
					match1 = true;
				}
				if(i == 1 && chArray[i] == randArray[i]){
					match2 = true;
				}
				if(i == 2 && chArray[i] == randArray[i]){
					match3 = true;
				}
				if(i == 3 && chArray[i] == randArray[i]){
					match4 = true;
				}
				if(i == 4 && chArray[i] == randArray[i]){
					match5 = true;
				}
				if(i == 5 && chArray[i] == randArray[i]){
					match6 = true;
				}
			}
		}
		//Assigns points according to how many values match
		if(x == 1){
			points = points + 10;
			println("                         One value matches                 " + "                   Points = " + points + " (ten points gained)");
		}
		else if(x == 2){
			points = points + 100;
			println("                         Two values match                 " + "                    Points = " + points + " (one hundred points gained)");
		}
		else if(x == 3){
			points = points + 1000;
			println("                         Three value match                 " + "                   Points = " + points + " (one thousand points gained)");	
		}
		else if(x == 4){
			points = points + 10000;
			println("                         Four values match                 " + "                   Points = " + points + " (ten thousand points gained)");	
		}
		else if(x == 5){
			points = points + 100000;
			println("                         Five values match                 " + "                   Points = " + points + " (one hundred thousand points gained)");	
		}
		else if(x == 6){
			points = points + 1000000;
			println("ALL SIX VALUES MATCH!!! CONGRATULATIONS!!!" + "               Points = " + points + " (one million points gained)");
		}else{
			points = points - 50;
			println("                         No values match                 " + "                      Points = " + points + " (fifty points lost)");
		}
		println("_______________________________________________________________________________________________________________________");
		println();
	}
	/**
	 * Outputs a char array of the user's input.
	 * The six values are assigned for identification purposes throughout the program.
	 * @param text - "text" is put into a char array
	 */
	public void stringToChar(String text){
		chArray = new char[6];
		//text converted into char array
		chArray = text.toCharArray();
		for(int i = 0; i < chArray.length; i++){  
			//User entered value outputted
			print(chArray[i]);  
			//Char values stored into the array
			if(i == 0){
				ch1 = chArray[i];
			}
			if(i == 1){
				ch2 = chArray[i];
			}
			if(i == 2){
				ch3 = chArray[i];
			}
			if(i == 3){
				ch4 = chArray[i];
			}
			if(i == 4){
				ch5 = chArray[i];
			}
			if(i == 5){
				ch6 = chArray[i];
			}
		}
		println("             User Entered Value");
	}
	/**
	 * Generates six random values from zero to nine and stores the values in randArray.
	 * The six values are assigned to alphabetical values for identification purposes throughout the program.
	 */
	public void random(){
		Random rand = new Random();
		randArray = new int[6];
		for (int i = 0; i < 6; i++){
			//Randomly generated number from zero to nine
			random = rand.nextInt(10);
			//Random values stored into the array
			randArray[i] = random;
			if(i == 0){
				a = random;
			}
			if(i == 1){
				b = random;
			}
			if(i == 2){
				c = random;
			}
			if(i == 3){
				d = random;
			}
			if(i == 4){
				f = random;
			}
			if(i == 5){
				g = random;
			}
			//Random values outputted
			//Prints out if the hint was not used.
			if (executed == false || guessed == true ){
				print(randArray[i]);
			}
		}
		//Prints out if the hint was not used.
		if (executed == false || guessed == true){
			println("             Randomly Generated Value");
		}
	}
	/**
	 * Displays a random element if the hint button is pressed.
	 * @param a - first randArray element
	 * @param b - second randArray element
	 * @param c - third randArray element
	 * @param d - fourth randArray element
	 * @param f - fifth randArray element
	 * @param g - sixth randArray element
	 */
	public void hint(int a, int b, int c, int d, int f, int g){
		//Hint can only be used if points are greater than or equal to one hundred points
		if(points >= 100){
			//One hundred points are lost due to the hint being used
			points = points - 100;
			Random rand = new Random();
			int x = rand.nextInt(6);
			//Displays one random value located within the randArray
			if(x == 0){
				println(a + " is located at index 0                                         Points = " + points + " (one hundred points lost)");
			}
			else if(x == 1){
				println(b + " is located at index 1                                         Points = " + points + " (one hundred points lost)");
			}
			else if(x == 2){
				println(c + " is located at index 2                                         Points = " + points + " (one hundred points lost)");
			}
			else if(x == 3){
				println(d + " is located at index 3                                         Points = " + points + " (one hundred points lost)");
			}
			else if(x == 4){
				println(f + " is located at index 4                                         Points = " + points + " (one hundred points lost)");
			}
			else if(x == 5){
				println(g + " is located at index 5                                         Points = " + points + " (one hundred points lost)");
			}
			executed = true;
			//Outputs if points are less than one hundred 
		}else{
			println("Insufficient Points");
			println();
		}
	}
	/**
	 * Outputs all randArray elements if the hint button is pressed.
	 * @param a - first randArray element
	 * @param b - second randArray element
	 * @param c - third randArray element
	 * @param d - fourth randArray element
	 * @param f - fifth randArray element
	 * @param g - sixth randArray element
	 */
	public void printArray(int a, int b, int c, int d, int f, int g){
		if(executed == true){
			print(a);
			print(b);
			print(c);
			print(d);
			print(f);
			print(g);
			println("             Randomly Generated Value");
		}
	}
	/**
	 *  Generates a GLabel(Ticket Master).
	 */
	public void titleImage(){
		int x = 75;
		int y = 100;
		//Title is displayed
		String title = "Ticket Master";
		titleImage = new GLabel(title,x,y);
		titleImage.setColor(Color.green);
		titleImage.setFont("Arial-100");
		canvas.add(titleImage);
	}
	/**
	 * Generates a GLabel(randArray elements).
	 */
	public void numberImage(){
		//Randomly generated values displayed on the canvas
		String strArray = String.valueOf(Arrays.toString(randArray));
		strArray = strArray.substring(1, strArray.length()-1).replace(",", "");
		numberImage = new GLabel(strArray,250,250 );
		numberImage.setColor(Color.green);
		numberImage.setFont("Arial-40");
		canvas.add(numberImage);
	}
	/**
	 * Generates a GLabel(chArray elements).
	 * Matches the color of the elements to green if the element matches and red if the element doesn't match.
	 * @param ch1 - first chArray element
	 * @param ch2 - second chArray element
	 * @param ch3 - third chArray element
	 * @param ch4 - fourth chArray element
	 * @param ch5 - fifth chArray element
	 * @param ch6 - sixth chArray element
	 */
	public void stringImage(char ch1, char ch2, char ch3, char ch4, char ch5, char ch6){
		//GLabels are assigned values of the chArray and given coordinates
		stringImage1 = new GLabel(String.valueOf(ch1),250,300 );
		stringImage2 = new GLabel(String.valueOf(ch2),283,300 );
		stringImage3 = new GLabel(String.valueOf(ch3),315,300 );
		stringImage4 = new GLabel(String.valueOf(ch4),347,300 );
		stringImage5 = new GLabel(String.valueOf(ch5),379,300 );
		stringImage6 = new GLabel(String.valueOf(ch6),411,300 );
		//Assigns the colors to each GLabel, either green or red
		if(match1 == true){
			stringImage1.setColor(Color.green);
		}if(match1 == false){
			stringImage1.setColor(Color.red);
		}if(match2 == true){
			stringImage2.setColor(Color.green);
		}if(match2 == false){
			stringImage2.setColor(Color.red);
		}if(match3 == true){
			stringImage3.setColor(Color.green);
		}if(match3 == false){
			stringImage3.setColor(Color.red);
		}if(match4 == true){
			stringImage4.setColor(Color.green);
		}if(match4 == false){
			stringImage4.setColor(Color.red);
		}if(match5 == true){
			stringImage5.setColor(Color.green);
		}if(match5 == false){
			stringImage5.setColor(Color.red);
		}if(match6 == true){
			stringImage6.setColor(Color.green);
		}if(match6 == false){
			stringImage6.setColor(Color.red);
		}
		//All GLabels are set with the same font
		stringImage1.setFont("Arial-40");
		stringImage2.setFont("Arial-40");
		stringImage3.setFont("Arial-40");
		stringImage4.setFont("Arial-40");
		stringImage5.setFont("Arial-40");
		stringImage6.setFont("Arial-40");
		////All GLabels are added to the canvas
		canvas.add(stringImage1);
		canvas.add(stringImage2);
		canvas.add(stringImage3);
		canvas.add(stringImage4);
		canvas.add(stringImage5);
		canvas.add(stringImage6);
	}
	/**
	 * Sets the color of the points to red if the points are lower than zero.
	 * Sets the color of the points to green if the points are greater than or equal to zero.
	 * @param points - points the user obtains throughout the program
	 */
	public void pointsImage(int points){
		int x = 10;
		int y = 30;
		//strPoints are given coordinates, a color, and a font
		String strPoints = "Points = ";
		pointsImage = new GLabel(strPoints,x,y);
		pointsImage.setColor(Color.green);
		pointsImage.setFont("Arial-30");
		//strPoints added to the canvas
		canvas.add(pointsImage);
		//points are given coordinates, a unique color, and a font
		pointsImage1 = new GLabel(String.valueOf(points),125,y);
		if(points >= 0){
			pointsImage1.setColor(Color.green);
		}if(points < 0){
			pointsImage1.setColor(Color.red);
		}
		pointsImage1.setFont("Arial-30");
		//points added to the canvas
		canvas.add(pointsImage1);
	}
}