package projectFinal;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.event.*;



public class mainFrame extends JFrame{

	//������ â ũ�� ����
	public int windowWidth;
	public int windowHeight;

	LeftP left;

	public mainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//������ ȭ�� ũ�⸦ ��Ÿ���� ��� ����+������ ũ�� ����
		int GAP=40;
		Dimension windowSize=Toolkit.getDefaultToolkit().getScreenSize();
		windowWidth=windowSize.width/3*2;
		windowHeight=windowSize.height-50;//������ ����
		setBounds(0, 0,windowWidth,windowHeight);//��üȭ�� ũ��
		windowWidth-=17; //��ũ������ ��ũ�ѹٸ� ���� ���� Ȯ��

		//�¿� ȭ�� �г�- ~�ȿ� ��ü���� ������ü�� ����.
		left=new LeftP();
		setContentPane(left);
		//JOptionPane.showMessageDialog(null,"�л� �����͸� �߰��ϼ���1");
		setVisible(true);
		JOptionPane.showMessageDialog(null,"�л� �����͸� �߰��ϼ���");
		
	}
	
	class LeftP extends JPanel{
		//mainFrame mf;

		//���� ���� �� ũ�� ����
		int ratio_heightTop=1;
		int ratio_heightBottom=9;
		public int bottomHeight;
		public int topHeight;

		JPanel leftTop;
		List leftBottom;

		public LeftP() {
			//this.mf=mf;

			//���� ũ�⼳��
			topHeight=windowHeight/(ratio_heightTop+ratio_heightBottom)*ratio_heightTop;
			bottomHeight=windowHeight-topHeight;
			
			leftBottom=new List(windowWidth, bottomHeight);

			setLayout(null);

			leftTop=new JPanel();//����â ��(�ΰ�����-�����Ӱ�)
			leftTop.setBounds(0,0,windowWidth,topHeight);//x��ǥ, y��ǥ, width, height

			leftTop.setLayout(null);
			MyTimerLabel mtl=new MyTimerLabel();
			mtl.setBounds(0,0,400,topHeight);
			leftTop.add(mtl);

			leftBottom.setBounds(0,topHeight,windowWidth,bottomHeight);//�����̰ž��ص� List���� ������

			add(leftTop);
			add(leftBottom);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainFrame();		//new Test();
	}

}
