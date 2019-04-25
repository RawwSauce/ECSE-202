/*
 * File: HitchhikerButton.java
 * ---------------------------
 * This program illustrates using buttons on the program border.
 */


import acm.graphics.GOval;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program puts up a button on the screen, which triggers a
 * message inspired by Douglas Adams's novel.
 */
public class HitchhikerButton extends GraphicsProgram {

/* Initialize the user-interface buttons */
	public void init() {
		add(new JButton("Red"), SOUTH);
		addActionListeners();
	}

/* Respond to a button action */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Red")) {
			add(new JButton("Please do not press this button again."),NORTH);
			add(new GOval(100,100),100,100);
		}
	}

}
