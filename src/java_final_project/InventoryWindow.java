package java_final_project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class InventoryWindow extends JFrame{
	private JToolBar toolBar = new JToolBar("tool box");
	private JPanel panel = new JPanel();
	private Container c = getContentPane();
	public InventoryWindow() {
		// TODO Auto-generated constructor stub
		setTitle("InventoryManagement");
		setSize(1280,720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createToolBar();
		
		c.add(toolBar,BorderLayout.NORTH);
		c.add(panel,BorderLayout.CENTER);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	private void createToolBar() {
		JButton exitButton =new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				State.nowFrame = new MenuSelectWindow();
			}
		});
		JButton orderButton = new JButton("Order");
		orderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c.remove(panel);
				panel=new JPanel();
				panel.add(new OrderPanel(), BorderLayout.CENTER);
				c.add(panel, BorderLayout.CENTER);
				
				repaint();
				revalidate();
			}
		});
		JButton releaseButton = new JButton("Release");
		releaseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c.remove(panel);
				panel=new JPanel();
				panel.add(new ReleasePanel(), BorderLayout.CENTER);
				c.add(panel, BorderLayout.CENTER);
				
				repaint();
				revalidate();
			}
		});
		toolBar.add(exitButton);
		toolBar.addSeparator();
		toolBar.add(orderButton);
		toolBar.addSeparator();
		toolBar.add(releaseButton);
		toolBar.addSeparator();
	}
}
