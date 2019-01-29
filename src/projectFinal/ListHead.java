package projectFinal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;

public class ListHead extends JPanel{//�׸�

	//�Է� ����
	JLabel inputLabel=new JLabel("   �Է� :");
	String[] labels= {"����","�� ����","�����߰�","�����Է�"};
	JComboBox<String> inputMode=new JComboBox<>(labels);
	
	JButton[] buttonsTOP= {new JButton("file"),new JButton("show"),};
	
	JButton[] buttonsBOTTOM= {new JButton("�̸�"),new JButton("�й�"),new JButton("����"),
			new JButton("Java"),new JButton("Python"),new JButton("C"),};
	
	int fontSize=14;
	
	SelectAllPanel selectPanel=new SelectAllPanel();
	
	
	public ListHead() {
		setLayout(new GridLayout(2,Student.count+3));
		//1��. �Է¸��
		add(inputLabel);
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		add(inputMode);
		inputMode.setFont(new Font("D2Coding",Font.PLAIN,fontSize));
		
		
		for(int i=0;i<Student.count+2-2-1;i++)//����� �޲ٱ�
			add(new JLabel(""));
		//��ư ����
		for(int i=0;i<buttonsTOP.length;i++) {
			add(buttonsTOP[i]);
			buttonsTOP[i].setFont(new Font("D2Coding",Font.BOLD,fontSize));
		}
		//buttonsTOP[1].setFont(new Font("D2Coding",Font.BOLD,fontSize-2));//inputSelf�� �� �Ⱥ�����
		
		

		
		//2��. ����Ʈ �׸�
		add(selectPanel);
		
		for(int i=0;i<buttonsBOTTOM.length;i++) {
			add(buttonsBOTTOM[i]);
			buttonsBOTTOM[i].setFont(new Font("D2Coding",Font.BOLD,fontSize));
		}

		for(int i=0;i<Student.count+2-Student.count;i++)
			add(new JLabel(""));
	}
	
	//��ü���� �г�
	class SelectAllPanel extends JPanel{
		JLabel index=new JLabel("����");
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
