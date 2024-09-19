package java_final_project;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.JPopupMenu.Separator;

import database.User;

public class LayoutWindow extends JFrame{
	private JToolBar toolBar = new JToolBar("tool box");
	private JPanel pallete = new Pallete();
	int start_x,start_y,end_x,end_y;
	boolean can_draw=false;
	public static boolean can_delete=false;
	public LayoutWindow() {
		setTitle("Layout");
		setSize(1280,720);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		createToolBar();
		c.add(toolBar,BorderLayout.NORTH);
		c.add(pallete,BorderLayout.CENTER);
		
		pallete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				start_x=0; start_y=0; end_x=0; end_y=0;
				start_x = e.getX();
				start_y = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(can_draw) {
					if(!(start_x==end_x&&start_y==end_y)) {
						end_x=e.getX();
						end_y=e.getY();
						int coordinate[]= {start_x,start_y,end_x,end_y};
						JButton tmpButton = new JButton(Integer.toString(coordinate[0]));
						tmpButton.setBounds(coordinate[0],
											coordinate[1],
											coordinate[2]-coordinate[0],
											coordinate[3]-coordinate[1]);
						BoxEvent moveEvent = new BoxEvent(tmpButton);
						tmpButton.addMouseListener(moveEvent);
						tmpButton.addMouseMotionListener(moveEvent);
						pallete.add(tmpButton);
						pallete.revalidate();
						pallete.repaint();
					}
					Component components[] = pallete.getComponents();
					State.boxUpdate.saveLayout(components);
				}
			}
		});
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
		JButton createButton = new JButton("New");
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method )stub
				if(can_delete) can_delete=false;
				if(can_draw) {
					can_draw=false;
					createButton.setBorderPainted(false);
				}
				else {
					can_draw=true;
					createButton.setBorderPainted(true);
				}
			}
		});
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Component components[] = pallete.getComponents();
				State.boxUpdate.saveLayout(components);
				State.boxInfo.update();
			}
		});
		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(can_draw) can_draw=false;
				if(can_delete) can_delete=false;
				else can_delete=true;
			}
		});
		toolBar.add(exitButton);
		toolBar.addSeparator();
		toolBar.add(createButton);
		toolBar.addSeparator();
		toolBar.add(saveButton);
		toolBar.addSeparator();
		toolBar.add(deleteButton);
	}
}
