package java_final_project;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ReleasePanel extends JPanel{
	private JPanel panel;
	private DefaultTableModel model;
	private int idx=0;
	public ReleasePanel() {
		String boxes[]=State.boxInfo.getboxName();
		JComboBox<String> boxList = new JComboBox<String>(boxes);
		panel=new JPanel();
		String header[]= {"Id","Name","Count","Volume","Type","Expiration-Date"};
		model=new DefaultTableModel(header,0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		JTable table=new JTable(model);
		JScrollPane scrolledTable=new JScrollPane(table);
		create_table(boxes[idx]);
		panel.add(scrolledTable,BorderLayout.WEST);
		String header2[]= {"Id","Name","Count","Volume","Type","Expiration-Date"};
		DefaultTableModel model2=new DefaultTableModel(header2,0){
			public boolean isCellEditable(int row, int column) {
				if(column==2)
					return true;
				else {
					return false;
				}
			};
		};
		JTable table2=new JTable(model2);
		JScrollPane scrolledTable2=new JScrollPane(table2);
		panel.add(scrolledTable2,BorderLayout.EAST);
		JButton releaseButton = new JButton("RELEASE");
		releaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0; i<model2.getRowCount(); i++) {
				    String tmp = "";
				    for(int j=0; j<model2.getColumnCount(); j++) {
				    	 Object data = model2.getValueAt(i, j);
				    	 tmp += data + "|";
				    }
				    
				    State.boxUpdate.releaseBox(tmp, boxes[idx]+".txt");
				}
				showMessageDialog(null,"success");
			    model2.setNumRows(0);
			    create_table(boxes[idx]);
                add(panel,BorderLayout.CENTER);
                repaint();
                revalidate();
			}
		});
		panel.add(releaseButton,BorderLayout.SOUTH);
		table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			    	// TODO Auto-generated method stub
			    	if(e.getClickCount()==2) {
			    		int row =table.getSelectedRow();
			    		String itemInfo[]=new String[6];
			    		for(int i=0;i<6;i++) {
			    			itemInfo[i]=(String)table.getValueAt(row, i);
			    		}
			    		model2.addRow(itemInfo);
			    	}
			    }
		});
		table2.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			    	// TODO Auto-generated method stub
			    	if(e.getClickCount()==2) {
			    		int row =table2.getSelectedRow();
			    		model2.removeRow(row);
			    	}
			    }
		});
		boxList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> cb = (JComboBox)e.getSource();
                idx = cb.getSelectedIndex();
                create_table(boxes[idx]);
                add(panel,BorderLayout.CENTER);
                repaint();
                revalidate();
			}
		});
		add(boxList, BorderLayout.SOUTH);
		add(panel,BorderLayout.CENTER);
	}
	public void create_table(String name) {
		String path = State.user.getPath()+State.user.getId()+"/Boxes/"+name+".txt";
		File file = new File(path);
		model.setNumRows(0);
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
	}
}
