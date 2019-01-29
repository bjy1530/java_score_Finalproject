package projectFinal;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.event.*;



public class mainFrame extends JFrame{

	//프레임 창 크기 설정
	public int windowWidth;
	public int windowHeight;

	LeftP left;

	public mainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//프레임 화면 크기를 나타내는 상수 조정+프레임 크기 설정
		int GAP=40;
		Dimension windowSize=Toolkit.getDefaultToolkit().getScreenSize();
		windowWidth=windowSize.width/3*2;
		windowHeight=windowSize.height-50;//시작줄 여분
		setBounds(0, 0,windowWidth,windowHeight);//전체화면 크기
		windowWidth-=17; //스크롤팬의 스크롤바를 위한 여백 확보

		//좌우 화면 패널- ~안에 구체적인 구현객체로 구성.
		left=new LeftP();
		setContentPane(left);
		//JOptionPane.showMessageDialog(null,"학생 데이터를 추가하세요1");
		setVisible(true);
		JOptionPane.showMessageDialog(null,"학생 데이터를 추가하세요");
		
	}
	
	class LeftP extends JPanel{
		//mainFrame mf;

		//세로 비율 및 크기 설정
		int ratio_heightTop=1;
		int ratio_heightBottom=9;
		public int bottomHeight;
		public int topHeight;

		JPanel leftTop;
		List leftBottom;

		public LeftP() {
			//this.mf=mf;

			//세로 크기설정
			topHeight=windowHeight/(ratio_heightTop+ratio_heightBottom)*ratio_heightTop;
			bottomHeight=windowHeight-topHeight;
			
			leftBottom=new List(windowWidth, bottomHeight);

			setLayout(null);

			leftTop=new JPanel();//왼쪽창 위(부가정보-자유롭게)
			leftTop.setBounds(0,0,windowWidth,topHeight);//x좌표, y좌표, width, height

			leftTop.setLayout(null);
			MyTimerLabel mtl=new MyTimerLabel();
			mtl.setBounds(0,0,400,topHeight);
			leftTop.add(mtl);

			leftBottom.setBounds(0,topHeight,windowWidth,bottomHeight);//굳이이거안해도 List에서 했을걸

			add(leftTop);
			add(leftBottom);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainFrame();		//new Test();
	}

}
