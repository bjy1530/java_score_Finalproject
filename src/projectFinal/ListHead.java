package projectFinal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;

public class ListHead extends JPanel{//항목

	//입력 구역
	JLabel inputLabel=new JLabel("   입력 :");
	String[] labels= {"선택","새 파일","파일추가","직접입력"};
	JComboBox<String> inputMode=new JComboBox<>(labels);
	
	JButton[] buttonsTOP= {new JButton("file"),new JButton("show"),};
	
	JButton[] buttonsBOTTOM= {new JButton("이름"),new JButton("학번"),new JButton("성별"),
			new JButton("Java"),new JButton("Python"),new JButton("C"),};
	
	int fontSize=14;
	
	SelectAllPanel selectPanel=new SelectAllPanel();
	
	
	public ListHead() {
		setLayout(new GridLayout(2,Student.count+3));
		//1행. 입력모드
		add(inputLabel);
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		add(inputMode);
		inputMode.setFont(new Font("D2Coding",Font.PLAIN,fontSize));
		
		
		for(int i=0;i<Student.count+2-2-1;i++)//빈공간 메꾸기
			add(new JLabel(""));
		//버튼 삽입
		for(int i=0;i<buttonsTOP.length;i++) {
			add(buttonsTOP[i]);
			buttonsTOP[i].setFont(new Font("D2Coding",Font.BOLD,fontSize));
		}
		//buttonsTOP[1].setFont(new Font("D2Coding",Font.BOLD,fontSize-2));//inputSelf가 다 안보여서
		
		

		
		//2행. 리스트 항목
		add(selectPanel);
		
		for(int i=0;i<buttonsBOTTOM.length;i++) {
			add(buttonsBOTTOM[i]);
			buttonsBOTTOM[i].setFont(new Font("D2Coding",Font.BOLD,fontSize));
		}

		for(int i=0;i<Student.count+2-Student.count;i++)
			add(new JLabel(""));
	}
	
	//전체선택 패널
	class SelectAllPanel extends JPanel{
		JLabel index=new JLabel("선택");
		JCheckBox check=new JCheckBox();
		
		public SelectAllPanel() {
			setLayout(new GridLayout(1,2));
			index.setHorizontalAlignment(SwingConstants.RIGHT);
			index.setFont(new Font("D2Coding",Font.BOLD,12));
			check.setSelected(true);
			add(index);
			add(check);
		}
	}
}
