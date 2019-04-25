//Rene Gagnon
//260801777

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.graphics.GRect;
import acm.gui.*;
import acm.program.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.*;

@SuppressWarnings("serial")

public class CalcGUI extends GraphicsProgram  {
	
	private JTextField expField;
	private JTextField answerField;
	private JSlider precisionSlider;
	private JLabel slider;
	private JLabel sliderMin;
	private JLabel sliderMax;
	private JLabel sliderValue;
	private DecimalFormat df;
	private int CALC_WIDTH=500;
	private int CALC_HEIGHT=500;
	private int N_ROWS=4;
	private int N_COLUMNS=4;
	private int MAX_PRECISION=15;
	private int MIN_PRECISION=0;
	private int DEFAULT_PRECISION=8;
	private int BUTTON_WIDTH=(CALC_WIDTH/N_COLUMNS)-10;
	private int BUTTON_HEIGHT=(CALC_HEIGHT/N_ROWS)-10;
	private CalcAlgo calc; // variable use to hold the instance of the calculator algorithm
	
	public void init(){
		
		resize(CALC_WIDTH+10,CALC_HEIGHT+195);// make the window the right size
		
		// we declare the two text fields use by the calculator
		expField = new JTextField(getWidth()/15);
		answerField = new JTextField(getWidth()/15);
		answerField.setEditable(false);
		// we add them to the display
		add(new JLabel("Expression:"),NORTH);
		add(expField,NORTH);
		add(new JLabel("Answer:"),SOUTH);
		add(answerField,SOUTH);
		
		
		int counter=0;
		for(int j=0;j<N_ROWS;j++){//loops that creates the buttons in a grid pattern
			for(int i=0;i<N_COLUMNS;i++){
				
				add(newCalBtn(BtnLabel(counter++)),10+i*(CALC_WIDTH/N_COLUMNS),10+j*(CALC_HEIGHT/N_ROWS));
			
			}
		}
		
		slider=new JLabel("Precision slider");
		slider.setFont(new Font("Arial", Font.PLAIN,25));
		sliderMin=new JLabel("Min");
		sliderMin.setFont(new Font("Arial", Font.PLAIN,25));
		sliderMax=new JLabel("Max");
		sliderMax.setFont(new Font("Arial", Font.PLAIN,25));
		sliderValue=new JLabel(" 8 ");
		sliderValue.setFont(new Font("Arial", Font.PLAIN,25));
		
		add(slider,43,4.2*(CALC_HEIGHT/N_ROWS));
		add(sliderMin,20,4.7*(CALC_HEIGHT/N_ROWS));
		add(sliderMax,20+180,4.7*(CALC_HEIGHT/N_ROWS));
		add(sliderValue,31+180/2,4.7*(CALC_HEIGHT/N_ROWS));
		precisionSlider=new JSlider(MIN_PRECISION,MAX_PRECISION,DEFAULT_PRECISION);
		add(precisionSlider,30+0*(CALC_WIDTH/N_COLUMNS),4.5*(CALC_HEIGHT/N_ROWS));
		
		df=new DecimalFormat("#.##");// create an instance of the DecimalFormat Class
		
		// add left and right parenthesis buttons
		add(newCalBtn(BtnLabel(-1)),10+2*(CALC_WIDTH/N_COLUMNS),10+4*(CALC_HEIGHT/N_ROWS));
		add(newCalBtn(BtnLabel(-2)),10+3*(CALC_WIDTH/N_COLUMNS),10+4*(CALC_HEIGHT/N_ROWS));
		expField.addActionListener(this);
		precisionSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// we changed the value of label when the state of the slider changes
				sliderValue.setText(Integer.toString(precisionSlider.getValue()));
				
				calc=new CalcAlgo();
				String answer=calc.run(expField.getText());
				
				if(CalcAlgo.isTokenNumber(answer)){
				df.setMaximumFractionDigits(precisionSlider.getValue());
				String formated=df.format(Double.parseDouble(answer));
				answerField.setText(formated);
				}else{
					answerField.setText(answer);
				}
			}
		});
		
		
	}

	public void actionPerformed(ActionEvent e){
		// the programmed response when the enter key is pressed
		// identical to the equal button
		if(e.getSource()==expField){
			
			calc=new CalcAlgo();
			String answer=calc.run(expField.getText());
			
			if(CalcAlgo.isTokenNumber(answer)){
			df.setMaximumFractionDigits(precisionSlider.getValue());
			String formated=df.format(Double.parseDouble(answer));
			answerField.setText(formated);
			}else{
				answerField.setText(answer);
			}
		}
	}
	
	
	/**
	 * Method to create a JButton with the right dimensions and with the wanted behavior when pressed
	 * @param digit the label we want to put on the button
	 * @return a button 
	 */
	private JButton newCalBtn(String digit){
		
		JButton CalBtn=new JButton(digit);	
		CalBtn.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_WIDTH));
		CalBtn.setFont(new Font("Arial", Font.PLAIN,25));
		
		CalBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getActionCommand().equals("=")){//if equal pressed we compute the expression contained in the
					// expression textfield
					//println("equal button pressed");
					calc=new CalcAlgo();
					String answer=calc.run(expField.getText());
					
					if(CalcAlgo.isTokenNumber(answer)){
					df.setMaximumFractionDigits(precisionSlider.getValue());
					String formated=df.format(Double.parseDouble(answer));
					answerField.setText(formated);
					}else{
						answerField.setText(answer);
					}
					
				}else if(e.getActionCommand().equals("C")){// if C is pressed we clear both textfields
					expField.setText("");
					answerField.setText("");
				}else{
				// for the other buttons we simply add their value to the expression field
				expField.setText(expField.getText()+e.getActionCommand());
				
				}
			}
		});
		
		return CalBtn;
		
	}
	/**
	 * Method to label the buttons
	 * @param i at witch buttons we are up to
	 * @return the label of the button in a string
	 */
	private String BtnLabel(int i){
		
		String BtnLabel;
		
		switch(i){
		case 0:
			BtnLabel="7";
			break;
		case 1:
			BtnLabel="8";
			break;
		case 2:
			BtnLabel="9";
			break;
		case 3:
			BtnLabel="+";
			break;
		case 4:
			BtnLabel="4";
			break;
		case 5:
			BtnLabel="5";
			break;
		case 6:
			BtnLabel="6";
			break;
		case 7:
			BtnLabel="-";
			break;
		case 8:
			BtnLabel="1";
			break;
		case 9:
			BtnLabel="2";
			break;
		case 10:
			BtnLabel="3";
			break;
		case 11:
			BtnLabel="*";
			break;
		case 12:
			BtnLabel="C";
			break;
		case 13:
			BtnLabel="0";
			break;
		case 14:
			BtnLabel="=";
			break;
		case 15:
			BtnLabel="/";
			break;
		case -1:
			BtnLabel="(";
			break;
		case -2:
			BtnLabel=")";
			break;
		default:
			BtnLabel="error";
			break;
		}
		return BtnLabel;
	}
	
	
}
