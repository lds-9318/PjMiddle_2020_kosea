import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.tools.javac.Main;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;

public class Intropage {

	private JFrame frmLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intropage window = new Intropage();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Intropage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 300, 150);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);
		
		
		Member_List Ml = new Member_List();
		
		Ml.setVisible(false);
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				Ml.setVisible(true);
			}
		});
		btnNewButton.setBounds(126, 70, 137, 30);
		frmLogin.getContentPane().add(btnNewButton);
		
		Label label = new Label("\uAD70 \uC815\uBCF4 \uC800\uC7A5 \uD504\uB85C\uADF8\uB7A8");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Adobe ∏Ì¡∂ Std M", Font.PLAIN, 13));
		label.setBounds(10, 10, 173, 54);
		frmLogin.getContentPane().add(label);
		
		
		
	}
}
