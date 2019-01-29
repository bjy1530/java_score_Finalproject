package projectFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;


class List extends JPanel implements ActionListener,MouseListener{
	
	//���� ���� �� ũ�� ����
	int ratio_heightTop=1;
	int ratio_heightBottom=9;
	
	ListHead head=new ListHead();
	ListBody body;
	int ratioTop=1;
	int ratioBottom=9;
	
	SelfInputFrame inputFrame;
	
	JScrollPane js;
	
	boolean[] ascendFlag= {false,false,false,false,false,false,};
	
	boolean selectedCheckFlag=true; //false�� �����ϸ� ó���� true�� ��µȴ�.
	//���� ó�� ����� �� ���õ� �����ϰŴϱ�
	//ó�� ������ ���� ������ �����Ǿ�� �Ѵ�. �׷��� true�� �ʱ�ȭ�Ѵ�.
	
	public List(int w,int h) {

		setLayout(null);
		
		//inputFrame.setVisible(false);

		int ht=h/(ratio_heightTop+ratio_heightBottom)*ratio_heightTop;
		body=new ListBody(w,h-ht);//�θ��� ũ�� �������ش�. ��?
		

		
		head.setBounds(0,0,w-10,ht);
		//���� �̺�Ʈ		
		for(int i=0;i<head.buttonsBOTTOM.length;i++) {
			head.buttonsBOTTOM[i].addActionListener(this);
		}

		//��ư�� ���� �� �����ϴ� �̺�Ʈ
		for(int i=0;i<head.buttonsBOTTOM.length;i++) {
			head.buttonsBOTTOM[i].addMouseListener(this);
		}

		
		
		add(head);
		js=new JScrollPane(body, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setBounds(0,ht,w,h-ht);
		js.setViewportView(body);
		//��ũ�ѹ� �����Ұž�
		JScrollBar hbar = js.getVerticalScrollBar();
	    hbar.setUnitIncrement(body.onePanelHeight*2);//�̷��� �ϸ�? ���ȵǴµ�. add�ϱ� ������ �����غþ�
	    //�ƴٵƴ� �̰ɷ� �ؾ��ϴ°ſ��� setBlockIncrement�̰� �ƴ϶�

		add(js);
		

		
		//show ��ư ������ ������ ����
		head.buttonsTOP[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//inputFrame=new SelfInputFrame();
				Vector<Student> vector=body.getCheckedStudents();
				//������ �׷����� ������ (vector)���ڷ� �Ѱ��ֱ�
				if(vector.size()==0) {
					System.out.println("���ͻ���� 0");
				}else {
					new Test(vector);
				}
			}
		});
		//�޺��ڽ� Ŭ���ϸ� ���� �����! +����� ��ư����?
		head.inputMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo=(JComboBox<String>)e.getSource();
				int index=combo.getSelectedIndex();
				
				String pathFullName="";
				//0:����, 1:������, 2:�����߰�, 3:�����Է�
				if(index==1) {
					//repaint();//������ �ߴµ��� ���������� �����غô�//�Ʒ��ٿ��ٰ� ���� �ٿ��ô�
					//revalidate();
					JFileChooser chooser=new JFileChooser();
					int ret=chooser.showOpenDialog(null);
					if(ret==JFileChooser.APPROVE_OPTION) {
						//������ ���� ��θ� ���´�.
						pathFullName=chooser.getSelectedFile().getPath();
						//btn.setText(pathFullName);
						body.removeAll();
						body.insertFileToList(pathFullName);
					}
					


				}else if(index==2) {
					
					JFileChooser chooser=new JFileChooser();
					int ret=chooser.showOpenDialog(null);
					if(ret==JFileChooser.APPROVE_OPTION) {
						//������ ���� ��θ� ���´�.
						pathFullName=chooser.getSelectedFile().getPath();
						//btn.setText(pathFullName);
						body.appendFileToList(pathFullName);
					}

					
					
					//repaint();
					//revalidate();
				}else if(index==3) {
					inputFrame=new SelfInputFrame(body);
					//inputFrame.setVisible(true);
					
					//body.appendStudent(inputFrame);//Student �ν��Ͻ� �����Ѵ�.
				}
				combo.setSelectedIndex(0);//? �ٽ� �ε���0���� ���ƿ��°� �¾�? ��������
			}
		});
		//��ü���� �̺�Ʈ
		//���� ��ü üũ�ڽ��� ���õȴ�.
		//��� ��� �߰��ٶ�.
		//�͸� �����ʷ� �ۼ��ϰ� �ű�� ����.. ���߿� �Ѳ����� �����غ���.
		//���, �͸� �����ʴ� �̺�Ʈ�� �߻��� ������ ���� �����Ǵ°� �ƴѰ�? �׷� �÷��׶������� �Űܾ��Ѵ�.
		//�ű�� body�� ���ٸ���.. ��°
		//CheckBoxSelectedListener checkListener=new CheckBoxSelectedListener();
		head.selectPanel.check.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				selectedCheckFlag=!selectedCheckFlag;
				//���� ���Ϳ��� ó���ؾ� �ϹǷ� :body�� ������. body�� �޼ҵ� ȣ��
				body.setListCheckBox(selectedCheckFlag);
			}
		});
		//file��ư ������ �������
		head.buttonsTOP[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pathFullName="";
				JFileChooser chooser=new JFileChooser();
				int ret=chooser.showOpenDialog(null);
				if(ret==JFileChooser.APPROVE_OPTION) {
					//������ ���� ��θ� ���´�.
					pathFullName=chooser.getSelectedFile().getPath();
					//btn.setText(pathFullName);

				}
				body.printToFile(pathFullName);
				
				JOptionPane.showMessageDialog(null,"File created!"); 
			}
		});
		
	}
	//head�� body ������ �̺�Ʈ?
	//1. head�� �׸��̸��� Ŭ���ϸ� body ���Ĥ���
	//2. head�� �Է¸�� Ŭ���ϸ� ����Ʈ �߰�
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton btn=(JButton)e.getSource();
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="�̸�";
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="�й�";
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="����";
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="�ڹ�";
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="���̽�";
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="��";
		}
		//���� standard�� �޼ҵ带 ȣ���ϸ� �ȴ�!
		body.setLabelColor(standard);
	}
	@Override public void mouseExited(MouseEvent e) {
		JButton btn=(JButton)e.getSource();
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="�̸�";
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="�й�";
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="����";
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="�ڹ�";
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="���̽�";
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="��";
		}
		//���� standard�� �޼ҵ带 ȣ���ϸ� �ȴ�!
		body.setLabelColorNULL(standard);
	}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	
	@Override //���� �̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		boolean flag=true;
		//right.showStudentVector(left.leftBottom.body.studentVector);
		//Vector<Student>�� ������.
		//���� ���� �ʿ䰡 ����!
		//�׳� body�ȿ��� �����ϸ� �ȴ�!
		//body�� String �����Ѵ�.
		JButton btn=(JButton)e.getSource();
		int color=253;
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="�̸�";
			ascendFlag[0]=!ascendFlag[0];
			flag=ascendFlag[0];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="�й�";
			ascendFlag[1]=!ascendFlag[1];
			flag=ascendFlag[1];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="����";
			ascendFlag[2]=!ascendFlag[2];
			flag=ascendFlag[2];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="�ڹ�";
			ascendFlag[3]=!ascendFlag[3];
			flag=ascendFlag[3];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="���̽�";
			ascendFlag[4]=!ascendFlag[4];
			flag=ascendFlag[4];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="��";
			ascendFlag[5]=!ascendFlag[5];
			flag=ascendFlag[5];
			//������, ������ �˸� ��
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}
		body.sortStudent(standard,flag);
	}
}
