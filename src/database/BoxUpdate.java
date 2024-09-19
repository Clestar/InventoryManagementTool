package database;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;

import java_final_project.State;

public class BoxUpdate {
	private Component[] boxes;
	public BoxUpdate(){}
	
	public void saveLayout(String old_name,String new_name) {
		String path = State.user.getPath();
		String doc="";
		try {
			File file = new File(path+State.user.getId()+"/layout/layout.txt");
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			 while ((line = reader.readLine()) != null) {
				 String boxinfo[]=line.split("\\|");
				 if(boxinfo[0].equals(old_name)) {
					 for(int i=1;i<boxinfo.length;i++) {
						 new_name+=("|"+boxinfo[i]);
					 }
					 doc+=new_name+"\r\n";
				 }
				 else
					 doc+=line+"\r\n";
			 }
			 reader.close();
			 FileWriter fw = new FileWriter(file);
			 BufferedWriter writer = new BufferedWriter(fw);
			 writer.write(doc);
			 writer.flush();
			 writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void saveLayout(Component[] components) {
		String path = State.user.getPath();
		try {
			FileWriter fw = new FileWriter(path+State.user.getId()+"/layout/layout.txt");
			BufferedWriter writer = new BufferedWriter(fw);
			
			for(int i=0; i<components.length; i++) {
				if(components[i] instanceof JButton ) {
					JButton tmpButton = (JButton) components[i];
					File file = new File(path+State.user.getId()+"/Boxes/"+tmpButton.getText()+".txt");
					if(!file.exists()) file.createNewFile();
					writer.write(tmpButton.getText()+"|"
					+tmpButton.getX()+"|"
					+tmpButton.getY()+"|"
					+(tmpButton.getX()+tmpButton.getWidth())+"|"
					+(tmpButton.getY()+tmpButton.getHeight())+"|");
					writer.newLine();
				}
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void deleteLayout(String name) {
		String path = State.user.getPath()+State.user.getId()+"/Boxes/"+name+".txt";
		File file = new File(path);
		file.delete();
		path = State.user.getPath()+State.user.getId()+"/layout/layout.txt";
		file = new File(path);
		String doc="";
		try {
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			 while ((line = reader.readLine()) != null) {
				 String boxinfo[]=line.split("\\|");
				 if(boxinfo[0].equals(name)) {
					 continue;
				 }
				 else {
					 doc+=line+"\r\n";
				 }
			 }
			 reader.close();
			 FileWriter fw = new FileWriter(file);
			 BufferedWriter writer = new BufferedWriter(fw);
			 writer.write(doc);
			 writer.flush();
			 writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void saveBox(String newName, JButton box) {
		String path = State.user.getPath()+State.user.getId()+"/Boxes/";
		File old_file = new File(path+box.getText()+".txt");
		File new_file = new File(path+newName+".txt");
		if(new_file.exists()) {
			showMessageDialog(null,"already exists");
		}
		else {
			if(old_file.renameTo(new_file)) {
				saveLayout(box.getText(), newName);
				box.setText(newName);
				showMessageDialog(null,"success");
			}
			else {
				showMessageDialog(null,"failed: error");
			}
		}
	}
	public void saveBox(String row, String name) {
		String path = State.user.getPath()+State.user.getId()+"/Boxes/";
		File file = new File(path+name);
		boolean flag=false;
		try{
			String[] lastInfo=State.boxInfo.getBoxInfo(name.substring(0,name.length()-4));
			for(int i=0;i<lastInfo.length;i++) {
				String tmp1[] = lastInfo[i].split("\\|");
				String tmp2[] = row.split("\\|");
				if(tmp1[0].equals(tmp2[0])) {
					int afterCount=Integer.valueOf(tmp1[2]) +  Integer.valueOf(tmp2[2]);
					tmp1[2]=Integer.toString(afterCount);
					String tmp3="";
					for(int j=0;j<tmp1.length;j++) {
						tmp3+=tmp1[j]+"|";
					}
					lastInfo[i]=tmp3;
					flag=true;
				}
			}
			if(flag) {
				FileWriter fw = new FileWriter(file);
	            BufferedWriter writer = new BufferedWriter(fw);
	            for(int i=0;i<lastInfo.length;i++) {
	            	writer.write(lastInfo[i]+"\r\n");
	            }
	            writer.flush();
	            writer.close();
				State.boxInfo.update();
				return;
			}
			if(!file.exists()) file.createNewFile();
		    FileWriter fw = new FileWriter(file,true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(row+"\r\n");
            writer.flush();
            writer.close();
            State.boxInfo.update();
	    } 
	        catch(Exception e){
		    e.getStackTrace();
		}   
	}
	public void releaseBox(String row, String name) {
		String path = State.user.getPath()+State.user.getId()+"/Boxes/";
		File file = new File(path+name);
		try{
			String[] lastInfo=State.boxInfo.getBoxInfo(name.substring(0,name.length()-4));
			for(int i=0;i<lastInfo.length;i++) {
				String tmp1[] = lastInfo[i].split("\\|");
				String tmp2[] = row.split("\\|");
				if(tmp1[0].equals(tmp2[0])) {
					if(tmp2[2].equals("0")) row="null";
					lastInfo[i]=row;
					break;
				}
			}
			FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            for(int i=0;i<lastInfo.length;i++) {
            	if(lastInfo[i].equals("null")) continue;
            	writer.write(lastInfo[i]+"\r\n");
            }
            writer.flush();
            writer.close();
			State.boxInfo.update();
	    } 
	    catch(Exception e){
		    e.getStackTrace();
		} 
	}
}
