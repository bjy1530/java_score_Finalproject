package projectFinal;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StudentPanel extends JPanel{
	//��ü�� �г� 2��
	DefaultShowPanel defaultShowPanel=new DefaultShowPanel();
	ModifyPanel modifyPanel=new ModifyPanel();//������ ���� �г�
	Student st;//�л� �Ѹ� ����
	int onePanelHeight=25;
	ListBody body;
	StudentPanel panel=this;
	
	public StudentPanel(int w, int h,Student st,ListBody body) {
		//�ʱ� �г� ȭ�� ����� ���� �г� ����
		setLayout(null);
		this.st=st;
		setStudent();
		
		this.body=body;
		
		//�гμ����ϸ� setStudent�� �ٽ� �����ؾ� �ϰ���?
		
		
		//�̺�Ʈ ����
		LabelClickListener lcl=new LabelClickListener();
		ModifyListener ml=new ModifyListener();
		
		//���μ��� ũ���? mainFrame���� ���ϰ� ���ƿ�~
		setSize(w,onePanelHeight);
		defaultShowPanel.setBounds(0,0,w,onePanelHeight);
		defaultShowPanel.delete.addActionListener(ml);
		defaultShowPanel.modify.addActionListener(ml);
		//defaultShowPanel.addMouseListener(lcl);
		for(int i=0;i<Student.count;i++) {
			defaultShowPanel.components[i].addMouseListener(lcl);
		}
		modifyPanel.setBounds(0,0,w,onePanelHeight);
		modifyPanel.ok.addActionListener(ml);
		for(int i=0;i<Student.count;i++) {
			modifyPanel.components[i].addMouseListener(lcl);
		}
		add(defaultShowPanel);
	}
	/*	JLabel nameLabel;
	JLabel genderLabel;
	JLabel stNumLabel;
	JLabel scoreJavaLabel;
	JLabel scorePythonLabel;
	JLabel scoreCLabel;*/
	private void setStudent() {//���� ������ ������ �����ϸ� ����Ǳ� �����Դϴ�.
		String[] strs= {st.name,st.st_num,st.gender,st.score_java,st.score_python,st.score_c};
		for(int i=0;i<Student.count;i++) {//6��
			//defaultShowPanel.indexPanel.index.setText(Integer.toString(i));
			defaultShowPanel.components[i].setText(strs[i]);
			modifyPanel.components[i].setText(strs[i]);
		}
	}
	private void getStudent() {
		//������� Student st�� ����!
		st.name=modifyPanel.components[0].getText();
		st.st_num=modifyPanel.components[1].getText();
		st.gender=modifyPanel.components[2].getText();
		st.score_java=modifyPanel.components[3].getText();
		st.score_python=modifyPanel.components[4].getText();
		st.score_c=modifyPanel.components[5].getText();
	}
	
	//����/����/Ȯ�� ��ư ������ �����ϴ� �̺�Ʈ
	class ModifyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn=(JButton)e.getSource();
			
			if(btn==defaultShowPanel.modify) {//������ư ������
				removeAll();
				//���⿡ �����г� �ؽ�Ʈ�ʵ� ä���ֱ�!! �� �ƴϴ� ������ �� ���� �ؾ���..
				//�ؽ�Ʈ �ʵ忡�� ����� Student ���� ��� ��������..

				add(modifyPanel);
				repaint();
				revalidate();
				
				//frame.setContentPane(modifyPanel);
				//frame.repaint();
			}else if(btn==modifyPanel.ok) {//Ȯ�ι�ư ������
				removeAll();
				getStudent();//����� Student ���� ����
				setStudent();//�ϱ� ���� �ؽ�Ʈ�ʵ忡�� Student�����;� �Ѵ�.
				
				add(defaultShowPanel);//���ŵ� �г� ����
				//frame.setContentPane(panel);
				repaint();
				revalidate();
			}else if(btn==defaultShowPanel.delete) {//������ư ������
				//studentVector���� �����ǰ�
				//studentLists������ �����ǰ�+�ε��� ��ȣ �ٲٰ�
				// * JOptionPane.showMessageDialog(null,"delete"); 
				body.deleteStudent(panel);//�ڱ��ڽ� �ǳ��ָ� ���Ϳ��� ���������Ѱ�? indexOf �޼ҵ带 �̿��ϸ� �����ҵ�..
				//�ٵ� �ߺ��Ǹ� �������� ���Ұ�
				
				//st, this
				
				
				
				//������ �ε�����ȣ �Ѱ��ִ� �޼ҵ� �����ϰ� �ۿ��� ȣ��?
				//�ȿ��� �ε�����ȣ ��� ����..
			}
		}
	}

	//�� �� ���콺 Ŭ���ϸ� �����ϴ� �̺�Ʈ
	class LabelClickListener extends MouseAdapter{
		boolean flag=true;
		@Override
		public void mouseClicked(MouseEvent e) {
		
			if(e.getClickCount()==2) {//����Ŭ���ϸ� ���ο� ������ ����
				new NewFrame();
			}
			//�ѹ��� Ŭ���ϸ�  ����â�� �ð��ڷ�
			//���� ������~
		}
	}
	
	//���߿� �������� �����ؾ� ����!
	class NewFrame extends JFrame{
		public NewFrame() {
			setSize(200,200);
			setVisible(true);
		}
	}
	
	//***�߰�*** �ؽ�Ʈ �ʵ忡�� ActionEvent �߰�


	
}
