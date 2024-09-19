package java_final_project;

import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginWindow extends JFrame{
	private JTextField input_id;
	private TextField input_pw;
	public LoginWindow() {
		setTitle("Login");
		setSize(400,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel id_label = new JLabel("ID");
		id_label.setBounds(30, 25, 57, 15);
		c.add(id_label);
		
		JLabel pw_label = new JLabel("PW");
		pw_label.setBounds(30, 70, 57, 15);
		c.add(pw_label);
		
		input_id = new JTextField();
		input_id.setBounds(108, 22, 116, 21);
		c.add(input_id);
		input_id.setColumns(10);
		input_pw = new TextField();
		input_pw.setBounds(108, 67, 116, 21);
		input_pw.setEchoChar('*');
		c.add(input_pw);
		
		JButton login_button = new JButton("Login");
		login_button.setBounds(265, 22, 75, 73);
		c.add(login_button);
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String messsage = State.user.login(input_id.getText(),input_pw.getText());
				if(messsage.equals("success")) {
					showMessageDialog(null,"success");
					State.boxInfo.updateBoxInfo();
					dispose();
					State.nowFrame = new MenuSelectWindow();
				}
				else {
					showMessageDialog(null,messsage);
					input_id.setText("");
					input_pw.setText("");
				}
			}
		});
		
		JLabel sign_up = new JLabel("Sign Up");
		sign_up.setBounds(265, 111, 57, 15);
		c.add(sign_up);
		sign_up.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String messsage = State.user.signUp(input_id.getText(),input_pw.getText());
				if(messsage.equals("success")) {
					showMessageDialog(null,"success");
					input_id.setText("");
					input_pw.setText("");
				}
				else {
					showMessageDialog(null,messsage);
					input_id.setText("");
					input_pw.setText("");
				}
			}
		});
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
