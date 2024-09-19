package java_final_project;

import java.awt.Frame;

import database.BoxUpdate;
import database.GetBoxInfo;
import database.User;

public class State {
	public static Frame nowFrame;
	public static User user = new User();
	public static BoxUpdate boxUpdate = new BoxUpdate();
	public static GetBoxInfo boxInfo = new GetBoxInfo(); 
}
