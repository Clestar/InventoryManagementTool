package java_final_project;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
public class Pallete extends JPanel{
	private int box_start_x,box_start_y,box_end_x,box_end_y;
	public Pallete(){
		
		String layoutPath = State.user.getPath()+State.user.getId()+"/Layout";
		File folder = new File(layoutPath);
		File file = new File(layoutPath+"/layout.txt");
		setLayout(null);
		if(!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			if(!file.exists()) file.createNewFile();
			FileReader fr = new FileReader(layoutPath+"/layout.txt");
	        BufferedReader reader = new BufferedReader(fr);
	        String line;
            while ((line = reader.readLine()) != null){
            	String coordinates[]=line.split("\\|");
            	add(loadBox(coordinates));	
            }
            reader.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	private JButton loadBox(String[] coordinates) {
		// TODO Auto-generated method stub
		JButton tmpButton =new JButton(coordinates[0]);
		tmpButton.setBounds(Integer.parseInt(coordinates[1]),
				Integer.parseInt(coordinates[2]),
				Integer.parseInt(coordinates[3])-Integer.parseInt(coordinates[1]),
				Integer.parseInt(coordinates[4])-Integer.parseInt(coordinates[2]));
		BoxEvent moveEvent = new BoxEvent(tmpButton);
		tmpButton.addMouseListener(moveEvent);
		tmpButton.addMouseMotionListener(moveEvent);
		return tmpButton;
	}
}
