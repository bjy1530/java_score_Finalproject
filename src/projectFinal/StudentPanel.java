package projectFinal;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StudentPanel extends JPanel{
	//교체할 패널 2개
	DefaultShowPanel defaultShowPanel=new DefaultShowPanel();
	ModifyPanel modifyPanel=new ModifyPanel();//수정을 위한 패널
	Student st;//학생 한명 정보
	int onePanelHeight=25;
	ListBody body;
	StudentPanel panel=this;
	
	public StudentPanel(int w, int h,Student st,ListBody body) {
		//초기 패널 화면 출력을 위해 패널 부착
		setLayout(null);
		this.st=st;
		setStudent();
		
		this.body=body;
		
		//패널수정하면 setStudent도 다시 설정해야 하겠지?
		
		
		//이벤트 달자
		LabelClickListener lcl=new LabelClickListener();
		ModifyListener ml=new ModifyListener();
		
		//가로세로 크기는? mainFrame에서 정하고 돌아와~
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
	private void setStudent() {//따로 설정한 이유는 수정하면 변경되기 때문입니다.
		String[] strs= {st.name,st.st_num,st.gender,st.score_java,st.score_python,st.score_c};
		for(int i=0;i<Student.count;i++) {//6번
			//defaultShowPanel.indexPanel.index.setText(Integer.toString(i));
			defaultShowPanel.components[i].setText(strs[i]);
			modifyPanel.components[i].setText(strs[i]);
		}
	}
	private void getStudent() {
		//멤버변수 Student st에 저장!
		st.name=modifyPanel.components[0].getText();
		st.st_num=modifyPanel.components[1].getText();
		st.gender=modifyPanel.components[2].getText();
		st.score_java=modifyPanel.components[3].getText();
		st.score_python=modifyPanel.components[4].getText();
		st.score_c=modifyPanel.components[5].getText();
	}
	
	//수정/삭제/확인 버튼 누르면 동작하는 이벤트
	class ModifyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn=(JButton)e.getSource();
			
			if(btn==defaultShowPanel.modify) {//수정버튼 누르면
				removeAll();
				//여기에 수정패널 텍스트필드 채워넣기!! 어 아니다 생성할 때 같이 해야지..
				//텍스트 필드에서 변경된 Student 정보 어떻게 가져오지..

				add(modifyPanel);
				repaint();
				revalidate();
				
				//frame.setContentPane(modifyPanel);
				//frame.repaint();
			}else if(btn==modifyPanel.ok) {//확인버튼 누르면
				removeAll();
				getStudent();//변경된 Student 정보 갱신
				setStudent();//하기 전에 텍스트필드에서 Student가져와야 한다.
				
				add(defaultShowPanel);//갱신된 패널 부착
				//frame.setContentPane(panel);
				repaint();
				revalidate();
			}else if(btn==defaultShowPanel.delete) {//삭제버튼 누르면
				//studentVector에서 삭제되고
				//studentLists에서도 삭제되고+인덱스 번호 바꾸고
				// * JOptionPane.showMessageDialog(null,"delete"); 
				body.deleteStudent(panel);//자기자신 건네주면 벡터에서 삭제가능한가? indexOf 메소드를 이용하면 가능할듯..
				//근데 중복되면 완전하진 못할걸
				
				//st, this
				
				
				
				//밖으로 인덱스번호 넘겨주는 메소드 정의하고 밖에서 호출?
				//안에서 인덱스번호 어떻게 알지..
			}
		}
	}

	//한 행 마우스 클릭하면 동작하는 이벤트
	class LabelClickListener extends MouseAdapter{
		boolean flag=true;
		@Override
		public void mouseClicked(MouseEvent e) {
		
			if(e.getClickCount()==2) {//더블클릭하면 새로운 프레임 생성
				new NewFrame();
			}
			//한번만 클릭하면  오른창에 시각자료
			//여기 구현해~
		}
	}
	
	//나중에 탭팬으로 변경해야 겠지!
	class NewFrame extends JFrame{
		public NewFrame() {
			setSize(200,200);
			setVisible(true);
		}
	}
	
	//***추가*** 텍스트 필드에서 ActionEvent 추가


	
}
