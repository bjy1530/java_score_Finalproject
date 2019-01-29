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
	    pane.addTab("개인별",pg);
	    pane.addTab("과목별",cg);
	    if(vector.size()<=1) {System.out.println("성별 그래프를 보려면 남,여 각 한명 씩은 선택하셔야 합니다.");}
	    else { GenderGraph gg=new GenderGraph(vector);pane.addTab("성별별",gg);}
	    c.add(pane);
	      
	    setSize(800,1200);
	    setVisible(true);
	}
}
