package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java_final_project.State;

public class GetBoxInfo {
	private String boxInfo[][];
	private String boxName[];
	public GetBoxInfo() {}
	public void updateBoxInfo() {
		String path = State.user.getPath();
		try {
			File folder = new File(path+State.user.getId()+"/Boxes");
			if(!folder.exists()) folder.mkdir();
			File subfiles[] =folder.listFiles();
			boxName=new String[subfiles.length];
			for(int i=0; i<subfiles.length;i++) {
				String tmp = subfiles[i].getName();
				boxName[i]=tmp.substring(0,tmp.length()-4);
			}
			boxInfo=new String[subfiles.length][];
			for(int i=0; i<subfiles.length;i++) {
				File file = subfiles[i];
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				String tmp="";
				 while ((line = reader.readLine()) != null) {
					 tmp+=line+",";
				 }
				 reader.close();
				 String info[]=tmp.split(",");
				 boxInfo[i]=new String[info.length];
				 
				 for(int j=0;j<info.length;j++) {
					 boxInfo[i][j]=info[j];
				 }
			}
		} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	}
	public String[][] getBoxInfo(){
		return boxInfo;
	}
	public int getBoxIdx(String name) {
		for(int i=0;i<boxName.length;i++) {
			if(name.equals(boxName[i])) {
				return i;
			}
		}
		return -1;
	}
	public String[] getBoxInfo(String name) {
		int idx=getBoxIdx(name);
		String info[]=new String[boxInfo[idx].length];
		for(int i=0;i<(boxInfo[idx].length);i++) {
			info[i]=boxInfo[idx][i];
		}
		return info;
	}
	public String[] getboxName() {
		return boxName;
	}
	public void update() {
		String path = State.user.getPath();
		try {
			File folder = new File(path+State.user.getId()+"/Boxes");
			if(!folder.exists()) folder.mkdir();
			File subfiles[] =folder.listFiles();
			boxName=new String[subfiles.length];
			for(int i=0; i<subfiles.length;i++) {
				String tmp = subfiles[i].getName();
				boxName[i]=tmp.substring(0,tmp.length()-4);
			}
			boxInfo=new String[subfiles.length][];
			for(int i=0; i<subfiles.length;i++) {
				File file = subfiles[i];
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				String line;
				String tmp="";
				 while ((line = reader.readLine()) != null) {
					 tmp+=line+",";
				 }
				 reader.close();
				 String info[]=tmp.split(",");
				 boxInfo[i]=new String[info.length];
				 
				 for(int j=0;j<info.length;j++) {
					 boxInfo[i][j]=info[j];
				 }
			}
		} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
	}
}
	

