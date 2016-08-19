import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToeGUI {
	
	private static JLabel scoreO = new JLabel();
	private static JLabel scoreX = new JLabel();
	private static JLabel gameInterface;
	private static JButton buttons[][];
	private static int count;
	private static String playerTurn = "O";
		
	public static void main(String[] args) {
		JFrame frame = new JFrame("TicTacToe");
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel X = new JLabel();
		JLabel O = new JLabel();
		gameInterface = new JLabel();
		buttons = new JButton[3][3];
		
		for (int row = 0; row < buttons.length; row++) {
			for (int col = 0; col < buttons[row].length; col++) {
				buttons[row][col] = new JButton();
				buttons[row][col].setPreferredSize(new Dimension(150, 150));
				buttons[row][col].setActionCommand(row + "" + col);
				buttons[row][col].addActionListener(new ButtonFunction());
				panel.add(buttons[row][col]);
			}
		}
		
		panel.setLayout(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel2.setLayout(new GridLayout(3, 2));
		panel2.setBorder(BorderFactory.	createLoweredBevelBorder());
		panel2.setBackground(Color.WHITE);
		O.setIcon(new ImageIcon("o.png"));
		X.setIcon(new ImageIcon("x.png"));
		scoreO.setText("0");
		scoreO.setHorizontalAlignment(JLabel.CENTER);
		scoreO.setFont(new Font(null, Font.PLAIN, 36));
		panel2.add(O);
		panel2.add(scoreO);
		scoreX.setText("0");
		scoreX.setHorizontalAlignment(JLabel.CENTER);
		scoreX.setFont(new Font(null, Font.PLAIN, 36));
		panel2.add(X);
		panel2.add(scoreX);
		gameInterface.setText("Player " + playerTurn + "'s Turn!");
		gameInterface.setFont(new Font(null, Font.PLAIN, 18));
		gameInterface.setHorizontalAlignment(JLabel.CENTER);
		panel2.add(gameInterface);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.EAST);
		frame.pack();
	}
	
	private static class ButtonFunction implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String eventName = event.getActionCommand();
			int row = Character.getNumericValue(eventName.charAt(0));
			int col = Character.getNumericValue(eventName.charAt(1));
			
			//Set the button's icon depending the "playerTurn" 
			buttons[row][col].setIcon(new ImageIcon(playerTurn + ".png"));
			//Sets the actionCommand to x or o to check the winner
			buttons[row][col].setActionCommand(playerTurn);
			count++;
			
			if (count == 9) {
				boardReset(buttons);
				gameInterface.setText("Tie Game!");
				count = 0;
			} else if (winGame(buttons, playerTurn)) {
				//if game is won x's score or the o's score will increase by one
				if (playerTurn.equals("X")) {
					scoreX.setText(Integer.toString(Integer.parseInt(scoreX.getText()) + 1));
				} else {
					scoreO.setText(Integer.toString(Integer.parseInt(scoreO.getText()) + 1));
				}
				boardReset(buttons);
				gameInterface.setText("Player " + playerTurn + " wins!");
				count = 0;
			} else {
				playerTurn = (playerTurn.equals("O")) ? "X" : "O";
				gameInterface.setText("Player " + playerTurn + "'s Turn!");
			}
		}
		
		public static void boardReset(JButton[][] buttons) {
			// Makes every button to null(nothing)
			for (int i = 0; i < buttons.length; i++) {
				for (int a = 0; a < buttons[i].length; a++) {
					buttons[i][a].setIcon(null);
					buttons[i][a].setActionCommand(i + "" + a);
				}
			}
		}
		
		public static boolean winGame(JButton[][] buttons, String playerTurn) {
			
			// Checks the rows and columns
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i][0].getActionCommand().equals(playerTurn) && buttons[i][1].getActionCommand().equals(playerTurn) && buttons[i][2].getActionCommand().equals(playerTurn)) {
					return true;
				} else if (buttons[0][i].getActionCommand().equals(playerTurn) && buttons[1][i].getActionCommand().equals(playerTurn) && buttons[2][i].getActionCommand().equals(playerTurn)) {
					return true;
				}
			}
			
			// Checks the diagonals
			if (buttons[0][0].getActionCommand().equals(playerTurn) && buttons[1][1].getActionCommand().equals(playerTurn) && buttons[2][2].getActionCommand().equals(playerTurn)) {
				return true;
			} else if (buttons[0][2].getActionCommand().equals(playerTurn) && buttons[1][1].getActionCommand().equals(playerTurn) && buttons[2][0].getActionCommand().equals(playerTurn)) {
				return true;
			}
			return false;
		}
	}
}