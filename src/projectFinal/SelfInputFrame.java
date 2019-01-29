package projectFinal;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SelfInputFrame extends JFrame{

	/*
	 * String name;
	String st_num;
    String gender;
    String score_java;
    String score_python;
    String score_c;
	 */
	String[] labelNames= {"�̸�","�й�","����(��/��)","�ڹ�����","���̽�����","������"};
	JLabel[] labels=new JLabel[6];
	JTextField[] fields=new JTextField[6];
	JButton okButton=new JButton("Ȯ��");
	
	Student inputStudent;
	boolean inputFlag=false;
	
	public SelfInputFrame(ListBody body) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new GridLayout(7,2));
		setLocation(200,0);
		
		for(int i=0;i<labels.length;i++) {
			labels[i]=new JLabel(labelNames[i]);
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			fields[i]=new JTextField(10);
			c.add(labels[i]);
			c.add(fields[i]);
		}
		c.add(new JLabel(""));
		c.add(okButton);
		
		pack();
		setVisible(true);
		
		//ok ��ư�� ������ �̺�Ʈ �߻�
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//�ƴѵ� ��ȿ���˻��ؾ���.
				//�̸��� ����. �й�,������ �������� �˻��ϰ�, ������ ��,�� �� �ϳ����� �˻��ϰ�
				int[] flags=new int[5];
				
				//"�̸�","�й�","����","�ڹ�����","���̽�����","������"
				
				//����
				String gender=fields[2].getText();
				if(gender.equals("��")||gender.equals("��")) {
					flags[0]=1;
				}else {
					//���� �˸�â+������ �󺧿� ���� ǥ��..? �ٽ� Ŭ���ϸ� �Ͼ������ ���ϱ�
					//�׸��� Student ��ü�� �������� ���ϰ� ó���� �غ�
					fields[2].setText("");
					JOptionPane.showMessageDialog(null,"��/���� �Է��ϼ���");
				}
				
				//���ڰ� ���ڷ� �����ϴ� ����ó���� �����ٵ�..NumberFormatException!!
				
				//�й�
				
				try{
					int suNum=Integer.parseInt(fields[1].getText());
					flags[1]=1;
				}catch(NumberFormatException error) {
					//�˸�â. �й� �߸��Է��ߴ�.
					fields[1].setText("");
					JOptionPane.showMessageDialog(null,"�й��� �����Դϴ�");
				}
				
				//�ڹ�
				try{
					int java=Integer.parseInt(fields[3].getText());
					flags[2]=1;
				}catch(NumberFormatException error) {
					//�˸�â. �ڹ� �߸��Է��ߴ�.
					fields[3].setText("");
					JOptionPane.showMessageDialog(null,"������ �����Դϴ�");
				}
				
				//���̽�
				try{
					int python=Integer.parseInt(fields[4].getText());
					flags[3]=1;
				}catch(NumberFormatException error) {
					//�˸�â. ���̽� �߸��Է��ߴ�.
					fields[4].setText("");
					JOptionPane.showMessageDialog(null,"������ �����Դϴ�");
				}
				
				//��
				try{
					int c=Integer.parseInt(fields[5].getText());
					flags[4]=1;
				}catch(NumberFormatException error) {
					//�˸�â. �� �߸��Է��ߴ�.
					fields[5].setText("");
					JOptionPane.showMessageDialog(null,"������ �����Դϴ�");
				}
				
				//���� ó���� ������ ��ġ�� �׶� Student ��ü �����Ѵ�.
				boolean result=true;
				for(int i=0;i<flags.length;i++) {
					if(flags[i]!=1) result=false;
				}
				
				if(result==true) {
					//�������� �󺧿��� ������ ������ �л� ��ü �ϳ� �����.
					inputStudent=new Student(fields[0].getText(),fields[1].getText(),fields[2].getText(),
							fields[3].getText(),fields[4].getText(),fields[5].getText());
					
					body.appendStudent(inputStudent);//body�� ������
					
					//setVisible(false);
					dispose();
				}

				
			}
		});
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelfInputFrame();
	}
*/
}
