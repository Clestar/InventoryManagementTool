package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class User {
	private String id;
	private String password;
	private String path="./db/";
	public User() {	}
	public User(String id,String password) {
		this.id =id;
		this.password=password;
	}
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String signUp(String id, String pw) {
		File folder = new File(path+id);
		File file = new File(path+"UserList.txt");
		String message = "";
		if(!folder.exists()) {
			try{
				if(!file.exists()) file.createNewFile();
			    folder.mkdir();
			    FileWriter fw = new FileWriter(file,true);
	            BufferedWriter writer = new BufferedWriter(fw);
	            writer.write(id+"|"+pw+"\r\n");
	            writer.flush();
	            writer.close();
	            message="success";
	            return message;
		    } 
		        catch(Exception e){
			    e.getStackTrace();
			    message="error";
			    return message;
			}   
		}
		else {
			message="already exist";
			return message;
		}
	}
	public String login(String id, String pw) {
		File folder = new File(path+id);
		File file = new File(path+"UserList.txt");
		String message = "";
		if(folder.exists()) {
			try{
				if(!file.exists()) file.createNewFile();
			    FileReader fr = new FileReader(file);
	            BufferedReader reader = new BufferedReader(fr);
	            String line;
	            while ((line = reader.readLine()) != null){
	            	String userinfo[]=line.split("\\|");
	            	if(userinfo[0].equals(id)) {
	            		if(userinfo[1].equals(pw)) {
	            			message="success";
	            			this.id=id;
	            			this.password=pw;
	            		}
		            		
	            		break;
	            	}
	            }
	            reader.close();
		    } 
		        catch(Exception e){
			    e.getStackTrace();
			    message="Error";
			    return message;
			}   
		}
		else {
			message="Wrong Id";
			return message;
		}
		if(message.equals("success"))
			return message;
		else {
			message="Wrong Password";
			return message;
		}
		
	}
	public String getPath() {
		return path;
	}
	public void logOut() {
		this.id="";
		this.password="";
	}
}
