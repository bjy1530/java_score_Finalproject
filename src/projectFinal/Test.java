package projectFinal;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;


public class Test extends JFrame{
	
	public Test(Vector<Student> vector) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane pane=new JTabbedPane();
		ListBody body=new ListBody(10,10);
	    PersonGraph2 pg=new PersonGraph2(vector);
	    ClassroomGraph cg=new ClassroomGraph(vector);

	    
	    Container c=getContentPane();
	    c.setLayout(null);
	    pane.setSize(800,1200);
	    pane.addTab("���κ�",pg);
	    pane.addTab("����",cg);
	    if(vector.size()<=1) {System.out.println("���� �׷����� ������ ��,�� �� �Ѹ� ���� �����ϼž� �մϴ�.");}
	    else { GenderGraph gg=new GenderGraph(vector);pane.addTab("������",gg);}
	    c.add(pane);
	      
	    setSize(800,1200);
	    setVisible(true);
	}
}
