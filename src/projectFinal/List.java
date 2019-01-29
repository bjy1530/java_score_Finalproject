package projectFinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;


class List extends JPanel implements ActionListener,MouseListener{
	
	//세로 비율 및 크기 설정
	int ratio_heightTop=1;
	int ratio_heightBottom=9;
	
	ListHead head=new ListHead();
	ListBody body;
	int ratioTop=1;
	int ratioBottom=9;
	
	SelfInputFrame inputFrame;
	
	JScrollPane js;
	
	boolean[] ascendFlag= {false,false,false,false,false,false,};
	
	boolean selectedCheckFlag=true; //false로 설정하면 처음에 true로 출력된다.
	//행이 처음 출력할 때 선택된 상태일거니까
	//처음 눌렀을 때는 선택이 해제되어야 한다. 그러면 true로 초기화한다.
	
	public List(int w,int h) {

		setLayout(null);
		
		//inputFrame.setVisible(false);

		int ht=h/(ratio_heightTop+ratio_heightBottom)*ratio_heightTop;
		body=new ListBody(w,h-ht);//부모의 크기 가져다준다. 왜?
		

		
		head.setBounds(0,0,w-10,ht);
		//정렬 이벤트		
		for(int i=0;i<head.buttonsBOTTOM.length;i++) {
			head.buttonsBOTTOM[i].addActionListener(this);
		}

		//버튼에 따라 라벨 색변하는 이벤트
		for(int i=0;i<head.buttonsBOTTOM.length;i++) {
			head.buttonsBOTTOM[i].addMouseListener(this);
		}

		
		
		add(head);
		js=new JScrollPane(body, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		js.setBounds(0,ht,w,h-ht);
		js.setViewportView(body);
		//스크롤바 조정할거야
		JScrollBar hbar = js.getVerticalScrollBar();
	    hbar.setUnitIncrement(body.onePanelHeight*2);//이렇게 하면? 엥안되는데. add하기 전으로 조절해봤어
	    //됐다됐다 이걸로 해야하는거였어 setBlockIncrement이게 아니라

		add(js);
		

		
		//show 버튼 누르면 프레임 생성
		head.buttonsTOP[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//inputFrame=new SelfInputFrame();
				Vector<Student> vector=body.getCheckedStudents();
				//지연쓰 그래프에 보내기 (vector)인자로 넘겨주기
				if(vector.size()==0) {
					System.out.println("벡터사이즈가 0");
				}else {
					new Test(vector);
				}
			}
		});
		//콤보박스 클릭하면 파일 입출력! +출력은 버튼으로?
		head.inputMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> combo=(JComboBox<String>)e.getSource();
				int index=combo.getSelectedIndex();
				
				String pathFullName="";
				//0:선택, 1:새파일, 2:파일추가, 3:직접입력
				if(index==1) {
					//repaint();//새파일 했는데도 안지워져서 삽입해봤다//아래붙였다가 위에 붙여봤다
					//revalidate();
					JFileChooser chooser=new JFileChooser();
					int ret=chooser.showOpenDialog(null);
					if(ret==JFileChooser.APPROVE_OPTION) {
						//선택한 파일 경로명 얻어온다.
						pathFullName=chooser.getSelectedFile().getPath();
						//btn.setText(pathFullName);
						body.removeAll();
						body.insertFileToList(pathFullName);
					}
					


				}else if(index==2) {
					
					JFileChooser chooser=new JFileChooser();
					int ret=chooser.showOpenDialog(null);
					if(ret==JFileChooser.APPROVE_OPTION) {
						//선택한 파일 경로명 얻어온다.
						pathFullName=chooser.getSelectedFile().getPath();
						//btn.setText(pathFullName);
						body.appendFileToList(pathFullName);
					}

					
					
					//repaint();
					//revalidate();
				}else if(index==3) {
					inputFrame=new SelfInputFrame(body);
					//inputFrame.setVisible(true);
					
					//body.appendStudent(inputFrame);//Student 인스턴스 전달한다.
				}
				combo.setSelectedIndex(0);//? 다시 인덱스0으로 돌아오는거 맞아? 예스예ㅔ
			}
		});
		//전체선택 이벤트
		//행의 전체 체크박스가 선택된다.
		//토글 기능 추가바람.
		//익명 리스너로 작성하고 옮길까 말까.. 나중에 한꺼번에 생각해보자.
		//잠깐, 익명 리스너는 이벤트가 발생할 때마다 새로 생성되는거 아닌가? 그럼 플래그때문에라도 옮겨야한다.
		//옮기면 body에 접근못해.. 우째
		//CheckBoxSelectedListener checkListener=new CheckBoxSelectedListener();
		head.selectPanel.check.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				selectedCheckFlag=!selectedCheckFlag;
				//행의 벡터에서 처리해야 하므로 :body로 보낸다. body의 메소드 호출
				body.setListCheckBox(selectedCheckFlag);
			}
		});
		//file버튼 누르면 파일출력
		head.buttonsTOP[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pathFullName="";
				JFileChooser chooser=new JFileChooser();
				int ret=chooser.showOpenDialog(null);
				if(ret==JFileChooser.APPROVE_OPTION) {
					//선택한 파일 경로명 얻어온다.
					pathFullName=chooser.getSelectedFile().getPath();
					//btn.setText(pathFullName);

				}
				body.printToFile(pathFullName);
				
				JOptionPane.showMessageDialog(null,"File created!"); 
			}
		});
		
	}
	//head와 body 사이의 이벤트?
	//1. head의 항목이름을 클릭하면 body 정렬ㅇㅇ
	//2. head의 입력모드 클릭하면 리스트 추가
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton btn=(JButton)e.getSource();
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="이름";
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="학번";
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="성별";
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="자바";
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="파이썬";
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="씨";
		}
		//이제 standard로 메소드를 호출하면 된다!
		body.setLabelColor(standard);
	}
	@Override public void mouseExited(MouseEvent e) {
		JButton btn=(JButton)e.getSource();
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="이름";
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="학번";
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="성별";
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="자바";
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="파이썬";
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="씨";
		}
		//이제 standard로 메소드를 호출하면 된다!
		body.setLabelColorNULL(standard);
	}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	
	@Override //정렬 이벤트
	public void actionPerformed(ActionEvent e) {
		boolean flag=true;
		//right.showStudentVector(left.leftBottom.body.studentVector);
		//Vector<Student>를 보낸다.
		//굳이 보낼 필요가 없다!
		//그냥 body안에서 정렬하면 된다!
		//body에 String 전달한다.
		JButton btn=(JButton)e.getSource();
		int color=253;
		String standard="";
		if(btn==head.buttonsBOTTOM[0]) {
			standard="이름";
			ascendFlag[0]=!ascendFlag[0];
			flag=ascendFlag[0];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[1]){
			standard="학번";
			ascendFlag[1]=!ascendFlag[1];
			flag=ascendFlag[1];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[2]){
			standard="성별";
			ascendFlag[2]=!ascendFlag[2];
			flag=ascendFlag[2];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[3]){
			standard="자바";
			ascendFlag[3]=!ascendFlag[3];
			flag=ascendFlag[3];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[4]){
			standard="파이썬";
			ascendFlag[4]=!ascendFlag[4];
			flag=ascendFlag[4];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}else if(btn==head.buttonsBOTTOM[5]){
			standard="씨";
			ascendFlag[5]=!ascendFlag[5];
			flag=ascendFlag[5];
			//오름차, 내림차 알림 색
			if(flag==true) {
				btn.setBackground(new Color(color,color,color));
			}else btn.setBackground(null);
		}
		body.sortStudent(standard,flag);
	}
}
