package java_final_project;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import database.GetBoxInfo;

public class OrderPanel extends JPanel{
	public OrderPanel() {
		String header[]= {"Id","Name","Count","Volume","Type","Expiration-Date"};
		DefaultTableModel model=new DefaultTableModel(header,0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		JTable table=new JTable(model);
		JScrollPane scrolledTable=new JScrollPane(table);
		String path = State.user.getPath()+"Inventory.txt";
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
		
		add(scrolledTable,BorderLayout.WEST);
		String header2[]= {"Id","Name","Count","Volume","Type","Expiration-Date","Box"};
		DefaultTableModel model2=new DefaultTableModel(header2,0){
			public boolean isCellEditable(int row, int column) {
				if(column==2||column==6)
					return true;
				else {
					return false;
				}
			};
		};
		JTable table2=new JTable(model2);
		JScrollPane scrolledTable2=new JScrollPane(table2);
		String boxes[]=State.boxInfo.getboxName();
		JComboBox<String> boxList = new JComboBox<String>(boxes);
		
		add(scrolledTable2,BorderLayout.EAST);
		JButton addButton = new JButton("ADD");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0; i<model2.getRowCount(); i++) {
				    String tmp = "";
				    for(int j=0; j<model2.getColumnCount()-1; j++) {
				    	 Object data = model2.getValueAt(i, j);
				    	 tmp += data + "|";
				    }
				    String box=table2.getValueAt(i, 6)+".txt";
				    State.boxUpdate.saveBox(tmp, box);
				}
				showMessageDialog(null,"success");
			    model2.setNumRows(0);
			}
		});
		add(addButton,BorderLayout.SOUTH);
		table.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
			    	// TODO Auto-generated method stub
			    	if(e.getClickCount()==2) {
			    		int row =table.getSelectedRow();
			    		String itemInfo[]=new String[7];
			    		for(int i=0;i<6;i++) {
			    			itemInfo[i]=(String)table.getValueAt(row, i);
			    		}
			    		model2.addRow(itemInfo);
			    		TableColumn column = table2.getColumnModel().getColumn(6);
			    		column.setCellEditor(new DefaultCellEditor(boxList)); 
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
	}
}