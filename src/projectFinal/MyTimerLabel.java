package projectFinal;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class MyTimerLabel extends JLabel implements Runnable{
	
	int hour, minute, second;
	
	Calendar c;
	
	public MyTimerLabel() {
		new Thread(this).start();
		setFont(new Font("D2Coding",Font.BOLD,50));
	}
	
	@Override
	public void run() {
		
		while(true) {
			String date="";
			c=Calendar.getInstance();
			
			int hourOfDay=c.get(Calendar.HOUR_OF_DAY);
			if(hourOfDay<12) date+="오전";
			else date+="오후";
			
			hour=c.get(Calendar.HOUR);
			if(hour<10) date+="0"+hour+":";
			else date+=hour+":";
			
			minute=c.get(Calendar.MINUTE);
			if(minute<10) date+="0"+minute+":";
			else date+=minute+":";
			
			second=c.get(Calendar.SECOND);
			if(second<10) date+="0"+second;
			else date+=second;
			
			setText(date);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {return;}
		}
	}

}
