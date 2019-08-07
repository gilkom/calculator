import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calculator{
	String sb = "";
	boolean isSign = true;
	boolean signCount = false;
	Double expr1 = null;
	Double expr2 = null;
	
	public static void main(String[] args) {
		new Calculator();
}
	
	
	Calculator(){
		
		
		final JFrame f = new JFrame("Calculator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTextField screen = new JTextField(20);
		screen.setEditable(false);
		screen.setFont(new Font("SERIF",Font.BOLD, 15));
		JPanel north = new JPanel();
		north.add(screen);	
		JPanel south = new JPanel();
		south.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 5));
		south.setLayout(new GridLayout(6,3));


		JButton one = new JButton(new AbstractAction("1") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "1";
				screen.setText(sb);
			}
		});
		JButton two = new JButton(new AbstractAction("2") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "2";
				screen.setText(sb);
			}
		});
		JButton three = new JButton(new AbstractAction("3") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "3";
				screen.setText(sb);
			}
		});
		JButton four = new JButton(new AbstractAction("4") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "4";
				screen.setText(sb);
			}
		});
		JButton five = new JButton(new AbstractAction("5") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "5";
				screen.setText(sb);
			}
		});
		JButton six = new JButton(new AbstractAction("6") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "6";
				screen.setText(sb);
			}
		});
		JButton seven = new JButton(new AbstractAction("7") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "7";
				screen.setText(sb);
			}
		});
		JButton eight = new JButton(new AbstractAction("8") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "8";
				screen.setText(sb);
			}
		});
		JButton nine = new JButton(new AbstractAction("9") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "9";
				screen.setText(sb);
			}
		});
		JButton zero = new JButton(new AbstractAction("0") {
			public void actionPerformed(ActionEvent e) {
				isSign = false;
				sb = sb + "0";
				screen.setText(sb);
				
			}
		});
		JButton dot = new JButton(new AbstractAction(".") {
			public void actionPerformed(ActionEvent e) {
				sb = checkIfEmptyDot(sb);
				if(checkForDots(sb)) {
				isSign = false;
				sb = sb + ".";
				}
				screen.setText(sb);
				
			}
		});
		JButton plus = new JButton(new AbstractAction("+") {
			
			public void actionPerformed(ActionEvent e) {
				//checking if the pattern of the equation looks like (number)(+-*/)(number)
				if(checkPattern(sb) <2) {
				//isSign == true == +-*/ 
				//isSign == false == 123456789
				//replacing the old sign with the new one:
				if(isSign == true) {
					String tempSb = sb;
					sb = "";
					for(int i = 0;i < (tempSb.length()-1);i++)
						sb = sb + tempSb.charAt(i);
					if(sb.length()> 0)sb = sb + "+";
					screen.setText(sb);
				}else{
				//adding a new sign if the last char in the string is numeric
				sb = sb + "+";
				screen.setText(sb);
				isSign = true;}
				//if there are two expressions in String it calculates the result
				}else {
					sb = String.valueOf(calculate(sb)) + "+";
					isSign = true;
					screen.setText(sb);
				}
				
			}
		});
		JButton minus = new JButton(new AbstractAction("-") {
			
			public void actionPerformed(ActionEvent e) {
				//adding minus if String is empty
				if(sb.length() == 0) {
					sb = "-";
					isSign = true;
					screen.setText(sb);
				}else {
				if(checkPattern(sb) <2) {
					if(isSign == true) {
						String tempSb = sb;
						sb = "";
						for(int i = 0;i < (tempSb.length()-1);i++)
							sb = sb + tempSb.charAt(i);
						if(sb.length()> 0)sb = sb + "-";
						screen.setText(sb);
					}else{
						sb = sb + "-";
						screen.setText(sb);
						isSign = true;}
				}else {
					sb = String.valueOf(calculate(sb)) + "-";
					isSign = true;
					screen.setText(sb);
					}
				}}
		});
		JButton times = new JButton(new AbstractAction("*") {
			
			public void actionPerformed(ActionEvent e) {
				if(checkPattern(sb) <2) {
				if(isSign == true) {
					String tempSb = sb;
					sb = "";
					for(int i = 0;i < (tempSb.length()-1);i++)
						sb = sb + tempSb.charAt(i);
					if(sb.length()> 0)sb = sb + "*";
					screen.setText(sb);
				}else{
				sb = sb + "*";
				screen.setText(sb);
				isSign = true;}
				}else {
					sb = String.valueOf(calculate(sb)) + "*";
					isSign = true;
					screen.setText(sb);
				}
				}
		});
		JButton divide = new JButton(new AbstractAction("/") {
			public void actionPerformed(ActionEvent e) {
				if(checkPattern(sb) <2) {
					if(isSign == true) {
						String tempSb = sb;
						sb = "";
						for(int i = 0;i < (tempSb.length()-1);i++)
							sb = sb + tempSb.charAt(i);
						if(sb.length()> 0)sb = sb + "/";
						screen.setText(sb);
					}else{
					sb = sb + "/";
					screen.setText(sb);
					isSign = true;}
				}else {
					sb = String.valueOf(calculate(sb)) + "/";
					isSign = true;
					screen.setText(sb);
				}
				}
		});
		JButton equals = new JButton(new AbstractAction("=") {
			public void actionPerformed(ActionEvent e) {
				if(checkPattern(sb) < 2) {
			}else {
				sb = String.valueOf(calculate(sb));
				screen.setText(sb);
			}
			}
		});
		
		JButton delete = new JButton(new AbstractAction("C") {
			public void actionPerformed(ActionEvent e) {
				isSign = true;
				sb = "";
				screen.setText(sb);
				
			}
		});

		
		south.add(one);
		south.add(two);
		south.add(three);
		south.add(four);
		south.add(five);
		south.add(six);
		south.add(seven);
		south.add(eight);
		south.add(nine);
		south.add(zero);
		south.add(plus);
		south.add(minus);
		south.add(times);
		south.add(divide);
		south.add(equals);
		south.add(delete);
		south.add(dot);
		
		
		f.add(north, BorderLayout.NORTH);
		f.add(south, BorderLayout.SOUTH);
		
	

	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			f.pack();
			f.setLocationRelativeTo(null);
			f.setVisible(true);
		}
	});
}
/*checking when string  with calculation is empty and wish to put a dot,
 * function prints 0 before dot. also after a sign like +/-*.      */
	public String checkIfEmptyDot(String t) {
		char lastSign = ' ';
		if(t.length()>0) {
			lastSign = t.charAt(t.length()-1);
		}
		if (t.length() == 0) {
			t = "0";
			return t;
		}
		else if(lastSign == '+' || lastSign == '-' ||
				lastSign == '*' || lastSign == '/') {
			t= t + "0";
			return t;
		}else
			return t;

	}
	
	
//if function gives false then you can't put dot, else you can
	public boolean checkForDots(String t) {
		Pattern s = Pattern.compile("[-*+/]");
		Matcher sm = s.matcher(t);
		int sign;
		if(sm.find()== true) {
			sign = 2;
			Pattern p = Pattern.compile("(\\.)");
			Matcher m = p.matcher(t);
			int count = 0;
			while (m.find() == true) {
				count++;
			}
			if(count == 2) return false;
			else return true;
		}else {
			sign = 1;
			Pattern p = Pattern.compile("(\\.)");
			Matcher m = p.matcher(t);
			int count = 0;
			while (m.find() == true) {
				count++;
			}
			if(count == 1) return false;
			else return true;
		}
	}

	public int checkPattern(String t) {
		Pattern p = Pattern.compile("(\\d+\\.?)+");
		Matcher m = p.matcher(t);
		int counter = 0;
		while(m.find()) {
			counter++ ;
		}
		return counter;
	}
	public double calculate(String t) {
		Pattern p = Pattern.compile("(\\d+\\.?)+");
		Matcher m = p.matcher(t);
		m.find();
		expr1 = Double.parseDouble(m.group());
		m.find();
		expr2 = Double.parseDouble(m.group());
		
		String sign1 = "";
		String sign2 = "";
		Pattern s = Pattern.compile("[-+*/]");
		Matcher sm = s.matcher(t);
		while(sm.find()) {
			sign2 = sign1;
			sign1 = sm.group();
		}

		double result = 0;
		if(sign2 == "") {
			switch(sign1) {
			case "+":{result = expr1 + expr2;break;}
			case "-":{result = expr1 - expr2;break;}
			case "*":{result = expr1 * expr2;break;}
			case "/":{result = expr1 / expr2;break;}
			}
		}else {
			switch(sign1) {
			case "+":{result = (-expr1) + expr2;break;}
			case "-":{result = (-expr1) - expr2;break;}
			case "*":{result = (-expr1) * expr2;break;}
			case "/":{result = (-expr1) / expr2;break;}
			}
		}
		return result;
	}
}
