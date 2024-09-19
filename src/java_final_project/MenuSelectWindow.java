package java_final_project;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuSelectWindow extends JFrame{
	public MenuSelectWindow() {
		setTitle("MenuSelect");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		JButton goToLayoutButton = new JButton("Layout Management");
		goToLayoutButton.setBounds(50,50,400,100);
		goToLayoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				State.nowFrame = new LayoutWindow();
			}
		});
		c.add(goToLayoutButton);
		JButton goToInventoryButton = new JButton("Inventory Management");
		goToInventoryButton.setBounds(50,200,400,100);
		goToInventoryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				State.nowFrame = new InventoryWindow();
			}
		});
		c.add(goToInventoryButton);
		JButton logOutButton=new JButton("LogOut");
		logOutButton.setBounds(150,350,200,100);
		c.add(logOutButton);
		logOutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				State.user.logOut();
				showMessageDialog(null,"Log out is completed");
				dispose();
				new LoginWindow();
			}
		});
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
