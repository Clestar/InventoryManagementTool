package java_final_project;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class BoxWindow extends JDialog{
	private JButton save_button;
	private JButton box;
	private String box_name;
	public BoxWindow(String title, JButton button) {
		super(State.nowFrame,title,true);
		box=button;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(box.getX(),box.getY(),500,500);
		JPanel panel = new JPanel();
		JLabel boxname_label = new JLabel("Box name");
		JTextField boxname = new JTextField(title);
		save_button=new JButton("save");
		save_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				State.boxUpdate.saveBox(boxname.getText(),box);
				State.boxInfo.update();
			}
		});
		String header[]= {"Id","Name","Count","Volume","Type","Expiration-Date"};
		DefaultTableModel model=new DefaultTableModel(header,0);	
		JTable table=new JTable(model);
		JScrollPane scrolledTable=new JScrollPane(table);
		String path = State.user.getPath()+State.user.getId()+"/Boxes/"+title+".txt";
		File file = new File(path);
	
		try {
			if(!file.exists())
				file.createNewFile();
			else {
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				while ((line = reader.readLine()) != null){
	            	String productinfo[]=line.split("\\|");
	            		model.addRow(productinfo);
	            }
				 reader.close();
			}	
		}
	           
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		panel.add(boxname_label);
		panel.add(boxname);
		panel.add(save_button);
		panel.add(scrolledTable,BorderLayout.CENTER);
		add(panel);
	}
}
