package projectFinal;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class JLabelColorTest extends JFrame{

	public JLabelColorTest() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JTabbedPane pane=new JTabbedPane();
		//ListBody body=new ListBody(10,10);
	    //PersonGraph pg=new PersonGraph(body.studentVector);

	    Container c=getContentPane();
	    c.setLayout(new FlowLayout());
	    //pane.setSize(300,300);
	    //pane.addTab("a",pg);
	    //c.add(pane);
	    
	    JLabel l1=new JLabel("220");
	    l1.setOpaque(true);
	    l1.setBackground(new Color(220,220,220));
	    c.add(l1);
	    
	    JLabel l2=new JLabel("210");
	    l2.setOpaque(true);
	    l2.setBackground(new Color(210,210,210));
	    c.add(l2);
	    
	    JLabel l3=new JLabel("white");
	    l3.setOpaque(true);
	    l3.setBackground(Color.WHITE);
	    c.add(l3);
	      
	    setSize(300,300);
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		new JLabelColorTest();
	}

}
