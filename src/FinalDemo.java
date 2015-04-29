import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.*;

public class FinalDemo extends WindowAdapter implements ActionListener{
        
    
	JFrame frame;
	JTextField field;
	JButton ce, plus, minus, mul, div, equ, point, sqrt,inv, pn, xy, sin, cos, fac,cbrt,seeya,save;
	JButton b[] = new JButton[10];
	double sum = 0, getValue;
	int i = 0, j = 0, p = 0;//i is the number on the number button, j is the number after the positive and negative sign
	double t;
	int l, action;//l is the length of button
	JDialog about;
	final int slength = 30;//the largest length would print out
        
        String fileName = "text.txt";
	
	public void run(){
		frame = new JFrame("LOL Calculator");
		frame.setSize(800,560);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.cyan);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setResizable(false);
		field = new JTextField(34);//the largest number
		field.setText("0");
		field.setHorizontalAlignment(SwingConstants.RIGHT);//number from right
		field.setEditable(false);
		
		Panel LeftPanel = new Panel();
		LeftPanel.setLayout(new GridLayout(4,3,3,3));
		
		Panel RightPanel = new Panel();
		RightPanel.setLayout(new GridLayout(4,3,3,3));
		
		for(l = 9; l >= 0; l--){//make new number and put the number on it
			b[l] = new JButton(String.valueOf(l));
			b[l].setForeground(Color.BLUE);
			LeftPanel.add(b[l]);
			b[l].addActionListener(this);
		}
		
		plus = new JButton("+");
		plus.addActionListener(this);
		minus = new JButton("-");
		minus.addActionListener(this);
		mul = new JButton("*");
		mul.addActionListener(this);
		cbrt = new JButton("x^(1/3)");
		cbrt.addActionListener(this);
		div = new JButton("/");
		div.addActionListener(this);
		xy = new JButton("x^y");
		xy.addActionListener(this);
		cos = new JButton("cos");
		cos.addActionListener(this);
		sin = new JButton("sin");
		sin.addActionListener(this);
		sqrt = new JButton("Sqrt");
		sqrt.addActionListener(this);
		ce = new JButton("CE");
		ce.addActionListener(this);
		equ = new JButton("=");
		equ.addActionListener(this);
		inv = new JButton("1/x");
		inv.addActionListener(this);
		point = new JButton(".");
		point.addActionListener(this);
		pn = new JButton("+/-");
		pn.addActionListener(this);
		fac = new JButton("!");
		fac.addActionListener(this);
		seeya = new JButton("BYEBYE!");
		seeya.addActionListener(this);
                save = new JButton("Save");
                save.addActionListener(this);
		
		RightPanel.add(div);
		RightPanel.add(sqrt);
		RightPanel.add(fac);
		RightPanel.add(mul);
		RightPanel.add(cbrt);
		RightPanel.add(sin);
		RightPanel.add(inv);
		RightPanel.add(minus);
		RightPanel.add(cos);
		RightPanel.add(ce);
		RightPanel.add(plus);
		RightPanel.add(xy);
		RightPanel.add(equ);
		RightPanel.add(seeya);
                RightPanel.add(save);
		
		LeftPanel.add(pn);//positive and negative
		LeftPanel.add(point);
		
		Panel NorthPanel = new Panel();
		NorthPanel.setLayout(new GridLayout(1,2,5,6));
		NorthPanel.add(LeftPanel);
		NorthPanel.add(RightPanel);
		
		frame.add(field);
		frame .add(NorthPanel);
		frame.addWindowListener(this);//frame listener (for the implements ActionListener)
		
		frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e){
		getValue = Double.valueOf(field.getText()).doubleValue();
		
		for(l = 0; l < 10; l++){//number from 0 - 9
			if (e.getSource() == b[l]){
				
				if(i==0)
					field.setText("");
				String s = field.getText();
				if(s.length() < slength)
					field.setText(field.getText() + e.getActionCommand());
				if(e.getSource() == b[0] && getValue == 0 && p==0)
					field.setText(e.getActionCommand());
				i++;
			}
			
		}
		
	
	
	if(e.getSource() == plus){
			if(j == 0){
				sum = getValue;
			}else if(action == 1){
				sum += getValue;
			}
			
			setSum();
			j++;//no more use if you click the plus button
			p=0;//can add the decimal number, if p = 1, then you cannot add the decimal
			i=0;//the number button can activaite it
			action = 1;
	   }
	
	else if(e.getSource() == minus){
		if(j == 0){
			sum = getValue;
		}else if(action == 2){
			sum -= getValue;
		}
		
		setSum();
		j++;
		p=0;
		i=0;
		action=2;
		
	}
	
	else if(e.getSource() == mul){
		if (j == 0){
			sum = getValue;
		}else if(action == 3){
			sum *= getValue;
		}
		setSum();
		j++;
		p=0;
		i=0;
		action =3;
	}
	
	else if(e.getSource() == div){
		try{
			
                    sum /= getValue;
                    setSum();
                    j++;
                    p=0;
                    i=0;
                    action = 4; 
		}catch(ArithmeticException ex){
                    System.out.println("GGWP!");
                    System.exit(0);
		}
	}
	
	else if(e.getSource() ==xy){
		if(j==0)
                    sum = getValue;
		else if(action == 5)
                    sum = Math.pow(sum, getValue);
		setSum();
		j++;
		i=0;
		action = 5;
	}
	
	else if(e.getSource() == equ){
		switch(action){
		case 1:
			field.setText(String.valueOf(sum += getValue));
			break;
		case 2:
			field.setText(String.valueOf(sum -= getValue));
			break;
		case 3:
			field.setText(String.valueOf(sum *= getValue));
			break;
		case 4:
			field.setText(String.valueOf(sum /= getValue));
			break;
		case 6:
			field.setText(String.valueOf(sum = Math.pow(sum, getValue)));
			break;
		}
		
		setSum();
		i=0;
		j=0;
		action = 0;
	}
	
	if(e.getSource() == cos){
		sum = Math.cos(getValue);
		setSum();
		i=0;
	}
	
	if(e.getSource() == sin){
		sum = Math.sin(getValue);
		setSum();
		i=0;
	}
	
	else if(e.getSource() == sqrt){
		sum = Math.sqrt(getValue);
		setSum();
		i=0;
	}
	
	else if(e.getSource() == cbrt){
		sum = Math.pow(getValue,1.0/3);
		setSum();
		i=0;
	}
	
	else if(e.getSource() == ce){
		i = 0;
		j = 0;
		p = 0;
		sum = 0;
		action = 0;
		field.setText("0");
	}
	
	else if(e.getSource() == inv){
		sum = 1/getValue;
		setSum();
		i = 0;
	}
	
	if (e.getSource() == fac){
		if(getValue == 0){
			sum =1;
		}
		else if(getValue > 0){
			sum = 1;
			for(t = getValue;t>1;t--){
				sum = sum * t;
			}
		}
		else if(getValue < 0){
			sum = 0;
		}
	setSum();
	i = 0;
	}
	
	else if(e.getSource() == point){
		if(p == 0)
			field.setText(field.getText()+e.getActionCommand());
		p = 1;
	}
	
	else if(e.getSource() == pn){
		String s = field.getText();
		char a = s.charAt(0);
		if(a == '-'){
			field.setText("");
			for(l = 1; l<s.length(); l++){
				field.setText(field.getText() + s.charAt(l));
			}
		}else if (getValue != 0){
			field.setText("-" + s);
		}
	}
	
        else if (e.getSource() == save){
		
		save.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                        java.io.File file;
                        file = new java.io.File("text.txt");

                        java.io.PrintWriter output;
                        try {
                            output = new java.io.PrintWriter(file);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
                        }catch (NullPointerException e2){
                            System.out.println("Check File Name");
			}			
			
		}
                }
		);
	}
        
	else if (e.getSource() == seeya){
		
		seeya.addActionListener(new ActionListener()
                    {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            JOptionPane.showMessageDialog(null,"Thank You For Using LOL Calculator! SEE YOU! ");
                            System.exit(0);
                        }			
			
                    }
                );
	}
}
	
	public void windowClosing(WindowEvent e){
		if(e.getSource() == about)
                    about.setVisible(true);
		else if(e.getSource() == frame)
			System.exit(0);
	}
	
	public void setSum(){
		field.setText(String.valueOf(sum));//show the result on the field
		String s = field.getText();//take the word from the text
		char a = s.charAt((s.length() - 1));//a is the content that the length of s - 1
		char b = s.charAt((s.length() - 2));//b is the content that the length of s - 2
		if(a == '0' && b =='.'){//determine whether it is a int. If it is int, delete the decimal point and "0"
			field.setText(String.valueOf(Math.round(sum)));//return the closest number in long form
			
		}
	}	
}