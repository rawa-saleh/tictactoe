/*
 * TicTacToe.java
 * February 19, 2015
 * Basic TicTacToe game.
 */

// TODO create an array of buttons rather than one by one.

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class TicTacToe extends JFrame  
							implements ActionListener
{
	
	public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    
    private String whoseTurn;
    private String whoWon;
    private Boolean winner;
    private int totalTurns;
    private JLabel message;
    
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    
    public static void main (String[] args)
    {
    	TicTacToe gui = new TicTacToe();
    	gui.setVisible(true);
    }
    
    public TicTacToe()
    {
    	super("TicTacToe");
    	setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Set up label on top (north) for messages.
        message = new JLabel("Welcome to TicTacToe.");
        add(message, BorderLayout.NORTH);
        
        //Set up panel of buttons in the center.
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBackground(Color.RED);
        
        //9 buttons for 9 spaces in the game.
        //Each button's Action Command is an int from 1 to 9.
        button1 = new JButton(" "); 
        button1.addActionListener(this);
        button1.setActionCommand("1");
        gamePanel.add(button1);
        
        button2 = new JButton(" "); 
        button2.addActionListener(this);
        button2.setActionCommand("2");
        gamePanel.add(button2);
        
        button3 = new JButton(" "); 
        button3.addActionListener(this);
        button3.setActionCommand("3");
        gamePanel.add(button3);
        
        button4 = new JButton(" "); 
        button4.addActionListener(this);
        button4.setActionCommand("4");
        gamePanel.add(button4);
        
        button5 = new JButton(" "); 
        button5.addActionListener(this);
        button5.setActionCommand("5");
        gamePanel.add(button5);
        
        button6 = new JButton(" "); 
        button6.addActionListener(this);
        button6.setActionCommand("6");
        gamePanel.add(button6);
        
        button7 = new JButton(" "); 
        button7.addActionListener(this);
        button7.setActionCommand("7");
        gamePanel.add(button7);
        
        button8 = new JButton(" "); 
        button8.addActionListener(this);
        button8.setActionCommand("8");
        gamePanel.add(button8);
        
        button9 = new JButton(" "); 
        button9.addActionListener(this);
        button9.setActionCommand("9");
        gamePanel.add(button9);
        
        add(gamePanel, BorderLayout.CENTER);
        
        //Panel with Reset button on the bottom (South).
        JPanel bottomPanel = new JPanel( );
        bottomPanel.setLayout(new FlowLayout( ));
        bottomPanel.setBackground(Color.LIGHT_GRAY); 
        
        JButton resetButton = new JButton("Reset"); 
        resetButton.addActionListener(this);
        bottomPanel.add(resetButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        //(Messy) Random number to pick between X and O to begin
        Random rand = new Random();
        int randomNum = rand.nextInt((2 - 1) + 1) + 1;
        if (randomNum == 1)
        	whoseTurn = "X";
        else if (randomNum == 2)
        	whoseTurn = "O";
        else
        	whoseTurn = "Error: Random number failed.";
        message.setText(message.getText() + " It's " + whoseTurn + "'s turn.");
        
        //Initialize game variables.
        totalTurns = 0;
        whoWon = " ";
        winner = false;
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String actionCommand = e.getActionCommand( );
    	
    	//If the Reset button has been hit.
    	if(actionCommand == "Reset")
    	{
    		resetGame();
    	}
    	
    	else //If one of the 9 game space buttons have been hit.
    	{
    		//Change button label.
    		Boolean buttonSuccess;
    		buttonSuccess = setButton(actionCommand);
    	
    		if (buttonSuccess == true)
    		{
    			totalTurns++;
    			checkWin();
    	
    			//If there is a winner (end of game)
    			if (winner == true)
    			{
    				message.setText(whoWon + " wins! Click Reset for a new game.");
    			}
    			//If there isn't a winner, and there are empty game spaces (next turn)
    			else if (winner == false && totalTurns < 9)
    			{
    				//Switch turns.
    				if (whoseTurn == "O")
    					whoseTurn = "X";
    				else if (whoseTurn == "X")
    					whoseTurn = "O";
    		
    				message.setText("It's " + whoseTurn + "'s turn.");
    			}
    			//If there isn't a winner and the number of turns has been 9 (tie)
    			else if (winner == false && totalTurns == 9)
    			{
    				message.setText("It's a tie. Game over.");
    			}
    		}
    		
    		else if (buttonSuccess == false && winner == false && totalTurns < 9)
        		message.setText("Can't do that! Try again, " + whoseTurn + ".");
    	}
    	
    	
    	
    }
    
    /*
     * Method that sets the hit button to
     * X or O depending on whose turn it is.
  	 * Does not do anything if the button
  	 * has been hit and labeled already.
     * Returns a Boolean success if
     * the new button label change is successful.
     */
    private Boolean setButton (String whichButton)
    {
    	Boolean success = true;
    	if (winner == false && totalTurns < 9)
    	{
	    	if (whichButton == "1" && button1.getText() == " ")
	    		button1.setText(whoseTurn);
	    	else if (whichButton == "2" && button2.getText() == " ")
	    		button2.setText(whoseTurn);
	    	else if (whichButton == "3" && button3.getText() == " ")
	    		button3.setText(whoseTurn);
	    	else if (whichButton == "4" && button4.getText() == " ")
	    		button4.setText(whoseTurn);
	    	else if (whichButton == "5" && button5.getText() == " ")
	    		button5.setText(whoseTurn);
	    	else if (whichButton == "6" && button6.getText() == " ")
	    		button6.setText(whoseTurn);
	    	else if (whichButton == "7" && button7.getText() == " ")
	    		button7.setText(whoseTurn);
	    	else if (whichButton == "8" && button8.getText() == " ")
	    		button8.setText(whoseTurn);
	    	else if (whichButton == "9" && button9.getText() == " ")
	    		button9.setText(whoseTurn);
	    	else
	    		success = false;
    	}
    	return success;
    }
    
    private void checkWin()
    {
    	if (button5.getText() != " ")
    	{
    		if (button1.getText() == button5.getText() && button5.getText() == button9.getText())
    			whoWon = button5.getText();
    		else if (button3.getText() == button5.getText() && button5.getText() == button7.getText())
    			whoWon = button5.getText();
    		else if (button2.getText() == button5.getText() && button5.getText() == button8.getText())
    			whoWon = button5.getText();
    		else if (button4.getText() == button5.getText() && button5.getText() == button6.getText())
    			whoWon = button5.getText();
    	}
    	
    	if (button1.getText() != " ")
    	{
    		if (button1.getText() == button4.getText() && button4.getText() == button7.getText())
    			whoWon = button1.getText();
    		else if (button1.getText() == button2.getText() && button2.getText() == button3.getText())
    			whoWon = button1.getText();
    	}
    	
    	if (button9.getText() != " ")
    	{
    		if (button3.getText() == button6.getText() && button6.getText() == button9.getText())
    			whoWon = button9.getText();
    		else if (button7.getText() == button8.getText() && button8.getText() == button9.getText())
    			whoWon = button9.getText();
    	}
    	
    	if (whoWon != " ")
    		winner = true;
    }
    
    private void resetGame()
    {
    	//(Messy) Random number to pick between X and O to begin
        Random rand = new Random();
        int randomNum = rand.nextInt((2 - 1) + 1) + 1;
        if (randomNum == 1)
        	whoseTurn = "X";
        else if (randomNum == 2)
        	whoseTurn = "O";
        else
        	whoseTurn = "Error: Random number failed.";
        message.setText("New game. It's " + whoseTurn + "'s turn.");
        
        //Reset game variables.
        totalTurns = 0;
        whoWon = " ";
        winner = false;
        
        //Reset buttons.
        button1.setText(" ");
        button2.setText(" ");
        button3.setText(" ");
        button4.setText(" ");
        button5.setText(" ");
        button6.setText(" ");
        button7.setText(" ");
        button8.setText(" ");
        button9.setText(" ");
        
    }
    
    
	

}
