package java_final_project;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class BoxEvent extends MouseAdapter{
	private JButton button;
    private int x,y;
    private int start_x, start_y;
    private boolean can_move=false;
    private boolean can_resize=false;
    private int btn_width, btn_height;
    int cursor_type = Cursor.DEFAULT_CURSOR;
    public BoxEvent(JButton button) {
        this.button = button;
        this.btn_height=button.getHeight();
        this.btn_width=button.getWidth();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    	// TODO Auto-generated method stub
    	if(LayoutWindow.can_delete) {
    		State.boxUpdate.deleteLayout(button.getText());
    		LayoutWindow.can_delete=false;
    		State.nowFrame.dispose();
    		State.nowFrame = new LayoutWindow();
    	}
    	if(e.getClickCount()==2) {
    		BoxWindow boxWindow = new BoxWindow(button.getText(), button);
    		boxWindow.setVisible(true);
    	}
    }
    @Override
    public void mousePressed(MouseEvent e) {
        start_x=e.getXOnScreen();
        start_y=e.getYOnScreen();
        if(!can_resize)
        	can_move=true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    	int move_x = e.getXOnScreen()-start_x;
    	int move_y = e.getYOnScreen()-start_y;
    	if(can_move) {
    		x=button.getX()+move_x;
            y=button.getY()+move_y;
            button.setLocation(x, y);
            State.nowFrame.repaint();
            State.nowFrame.revalidate();
    	}
    	else if(can_resize) {
    		if(cursor_type == Cursor.NW_RESIZE_CURSOR) {
    			x=button.getX()+move_x;
                y=button.getY()+move_y;
                btn_height-=move_y;
                btn_width-=move_x;
    		}
    		else if (cursor_type == Cursor.NE_RESIZE_CURSOR) {
    			x=button.getX();
                y = button.getY() + move_y;
                btn_height -= move_y;
                btn_width += move_x;
            } else if (cursor_type == Cursor.SW_RESIZE_CURSOR) {
                x = button.getX() + move_x;
                y=button.getY();
                btn_height += move_y;
                btn_width -= move_x;
            } else if (cursor_type == Cursor.SE_RESIZE_CURSOR) {
            	x=button.getX();
            	y=button.getY();
                btn_height += move_y;
                btn_width += move_x;
            } else if (cursor_type == Cursor.N_RESIZE_CURSOR) {
            	x=button.getX();
                y = button.getY() + move_y;
                btn_height -= move_y;
            } else if (cursor_type == Cursor.S_RESIZE_CURSOR) {
            	x=button.getX();
            	y=button.getY();
                btn_height += move_y;
            } else if (cursor_type == Cursor.W_RESIZE_CURSOR) {
                x = button.getX() + move_x;
                y=button.getY();
                btn_width -= move_x;
            } else if (cursor_type == Cursor.E_RESIZE_CURSOR) {
            	x=button.getX();
            	y=button.getY();
                btn_width += move_x;
            }
            button.setBounds(x,y,btn_width,btn_height);	
            State.nowFrame.repaint();
            State.nowFrame.revalidate();
    	}
    	start_x=e.getXOnScreen();
        start_y=e.getYOnScreen();
    	
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    	// TODO Auto-generated method stub
    	if(can_move) can_move=false;
    }
   
    @Override
    public void mouseMoved(MouseEvent e) {
    	// TODO Auto-generated method stub
    	if(((e.getX()<5)&&(e.getY()<5))) {
    		cursor_type =Cursor.NW_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getX()+5>button.getWidth())&&(e.getY()+5>button.getHeight())) {
    		cursor_type =Cursor.SE_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getX()<5)&&(e.getY()+5>button.getHeight())) {
    		cursor_type =Cursor.SW_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getY()<5)&&(e.getX()+5>button.getWidth())) {
    		cursor_type =Cursor.NE_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getX()<5)) {
    		cursor_type =Cursor.W_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getX()+5>button.getWidth())) {
    		cursor_type =Cursor.E_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getY()<5)) {
    		cursor_type =Cursor.N_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else if((e.getY()+5>button.getHeight())) {
    		cursor_type =Cursor.S_RESIZE_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=true;
    	}
    	else {
    		cursor_type =Cursor.HAND_CURSOR;
    		button.setCursor(new Cursor(cursor_type));
    		can_resize=false;
		}
    }
}
