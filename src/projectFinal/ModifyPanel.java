package projectFinal;

import java.awt.*;

import javax.swing.*;

public class ModifyPanel extends JPanel {
	
	JButton ok=new JButton("확인");
	
	JTextField[] components=new JTextField[Student.count];
	
	int fontSize=14;
	
	public ModifyPanel() {
		setLayout(new GridLayout(1,Student.count+3));
		
		add(new JLabel(""));
		for(int i=0;i<Student.count;i++) {//6번
			//생성하고 추가합니다.
			components[i]=new JTextField(10);
			add(components[i]);
			components[i].setFont(new Font("D2Coding",Font.PLAIN,fontSize));
		}

		add(new JLabel(""));
		add(ok);
		
		ok.setFont(new Font("D2Coding",Font.BOLD,fontSize));
	}
	
}
