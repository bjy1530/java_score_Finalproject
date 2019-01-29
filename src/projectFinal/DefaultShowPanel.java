package projectFinal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class DefaultShowPanel extends JPanel{
	//�⺻ ��� �г��� ����������Ʈ

	JLabel[] components=new JLabel[Student.count];
	
	//��ư ���̾ƿ��� ���� �����ϴ°� ������..
	IndexPanel indexPanel=new IndexPanel();
	JButton delete=new JButton("����");
	JButton modify=new JButton("����");
	
	int fontSize=14;
	
	//������
	public DefaultShowPanel() {
		setLayout(new GridLayout(1,Student.count+3));//�̰͵� ����� �ٲ�� �ڴ�..!
		
		add(indexPanel);
		for(int i=0;i<Student.count;i++) {//6��
			//�����ϰ�, �߰��մϴ�. ������ ����
			components[i]=new JLabel();
			add(components[i]);
			components[i].setOpaque(true);//�����ϰ� �ҰŴ�
			components[i].setFont(new Font("D2Coding",Font.PLAIN,fontSize));
		}

		modify.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		delete.setFont(new Font("D2Coding",Font.BOLD,fontSize));
		
		add(modify);
		add(delete);
		
		int color=210;
		//�г��� üũ�ڽ��� ���õǸ�, �� ���̶���Ʈ �Ǵ� �̺�Ʈ
		indexPanel.check.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {//���õǸ� ���̶���Ʈ
					for(int i=0;i<components.length;i++) {
						components[i].setBackground(new Color(color,color,color));
					}
				}else {//���þȵǸ� Color==null
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
