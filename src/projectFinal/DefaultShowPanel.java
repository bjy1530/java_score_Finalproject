package projectFinal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DefaultShowPanel extends JPanel{
	//기본 출력 패널의 구성컴포넌트

	JLabel[] components=new JLabel[Student.count];
	
	//버튼 레이아웃은 따로 지정하는게 좋을까..
	IndexPanel indexPanel=new IndexPanel();
	JButton delete=new JButton("삭제");
	JButton modify=new JButton("수정");
	
	int fontSize=14;
	
	//생성자
	public DefaultShowPanel() {
		setLayout(new GridLayout(1,Student.count+3));//이것도 상수로 바꿔야 겠다..!
		
		add(indexPanel);
		for(int i=0;i<Student.count;i++) {//6번
			//생성하고, 추가합니다. 변경은 따로
			components[i]=new JLabel();
			add(components[i]);
			components[i].setOpaque(true);//색변하게 할거다
			components[i].setFont(new Font("D2Coding",Font.PLAIN,fontSize));
		}

		modify.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		delete.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		
		add(modify);
		add(delete);
		
		int color=210;
		//패널의 체크박스가 선택되면, 행 하이라이트 되는 이벤트
		indexPanel.check.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {//선택되면 하이라이트
					for(int i=0;i<components.length;i++) {
						components[i].setBackground(new Color(color,color,color));
					}
				}else {//선택안되면 Color==null
					for(int i=0;i<components.length;i++) {
						components[i].setBackground(null);
					}
				}
			}
		});

	}
	class IndexPanel extends JPanel{
		JLabel index=new JLabel("");
		JCheckBox check=new JCheckBox();
		
		public IndexPanel() {
			setLayout(new GridLayout(1,2));
			index.setHorizontalAlignment(SwingConstants.RIGHT);
			index.setFont(new Font("D2Coding",Font.BOLD,12));
			add(index);
			add(check);
		}
	}
	
}
