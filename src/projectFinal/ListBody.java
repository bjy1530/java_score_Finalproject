package projectFinal;

import java.awt.*;
import javax.swing.*;

//import project.Student;

import java.awt.event.*;

import java.io.*;
import java.util.*;

public class ListBody extends JPanel{//학생들
	//Vector<Student> students;//학생 텍스트 정보만 받아왔다. 여기서 Panel 만들어야겠지?
	
	//**임시**
	Vector<Student> studentVector=new Vector<>();
	Vector<StudentPanel> studentLists=new Vector<>();
	int onePanelHeight=25; //리스트 하나당 세로 크기
	int width;
	
	Dimension dd;
	
	//임시로 만든다. 학생 리스트
	public ListBody(int w, int h) {//h는 아직 안썼다. 불필요하면 지워라
		//부모 컴포넌트 List에서 ListBody의 크기를 지정해서 ListBody는 크기를 또 지정할 필요가 없다.
		//그런데 ListBody안에 들어갈 컴포넌트들한테는 크기가 또 필요하네.. 가져와야 겠다.
		//ListBody 배치관리자?
		w-=10; //스크롤팬의 스크롤바를 위한 여백확보
		//h-=10; //스크롤바와 페널의 크기를 맞추기위한 여백
		width=w;
		setLayout(null);


	}
	
	public void appendStudent(Student student) {
		int i=studentVector.size();//size()위치에 새로운 행이 추가될거다.
		studentVector.add(student);
		//studentLists.add
		
		StudentPanel st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);//추가한 원소로 행만들기
		st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));
		st.defaultShowPanel.indexPanel.check.setSelected(true);		
		//MyActionListener my=new MyActionListener(i);
		//st.defaultShowPanel.delete.addActionListener(my);
		studentLists.add(st);//벡터에 저장
		

		studentLists.get(i).setLocation(0,i*onePanelHeight);
		add(studentLists.get(i));
		
		//body 크기도 같이 변경해야 한다.
		dd=new Dimension(width,25*studentVector.size()+10);//부모 자식 컨테이너 크기맞추기.오케
		//1000개 최대로 해보려고 1000이지, 사용자 설정으로 바꿀 수도 있겠다.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	//체크박스 전체선택/전체선택해제 동작
	public void setListCheckBox(boolean flag){
		//flag가 true이면 전체 선택
		for(int i=0;i<studentLists.size();i++) {//벡터 크기만큼 반복
			studentLists.get(i).defaultShowPanel.indexPanel.check.setSelected(flag);
		}
	}
	public Vector<Student> getCheckedStudents(){
		//파일을 출력하기 전에 체크된 행만 따로 벡터에 복사한다.
		Vector<Student> tempVector=new Vector<>();
		
		for(int i=0;i<studentLists.size();i++) {//벡터 크기만큼 반복
			if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()==true) {//체크된 상태이면
				tempVector.add(studentVector.get(i));//tempVector에 옮겨담는다.
			}
		}
		
		return tempVector;
	}
	
	public void printToFile(String path) {
		
		//파일을 출력하기 전에 체크된 행만 따로 벡터에 복사한다.
		Vector<Student> tempVector=getCheckedStudents();

		   
		   //Vector<Student> studentVector=new Vector<>();
	      // 파일 처리할때는 예외 처리

		try {

			   FileWriter fw=new FileWriter(path);
			   Student student;
			   String buffer="";
			   for(int i=0;i<tempVector.size();i++) {
				   student=tempVector.get(i);//객체하나 가져와서
	        	 
				   buffer+=student.name+" ";
				   buffer+=student.st_num+" ";
				   buffer+=student.gender+" ";
				   buffer+=student.score_java+" ";
				   buffer+=student.score_python+" ";
				   buffer+=student.score_c;
				   //System.out.println(i+" "+buffer); //디버그 테스트ㅇㅇ
				   //한 줄 출력한다.
				   fw.write(buffer+"\r\n");//write는 를 해줘야 한줄 뛰기가 된다.
				   buffer="";//buffer 초기화
			   }

			   fw.close();

		   }catch(IOException e) {
			   System.out.println("파일이 발견되지 않았습니다.");
		   }
		
		tempVector=null;//tempVector 초기화
		   
	}



	
	
	
	//콤보박스 인덱스1. 새 파일 입력 //메서드 인자로 파일 이름 입력받을 것!!
	public void insertFileToList(String path) {
		newFile(path);//Vector<Student>, Vector<StudentPanel> 둘다 지우고 새 벡터로 동작한다.
		//그럼 newFile이 종료된 이유에는 ? 거기는 studentVector만 처리하고 나와서 패널리스트 정리할거야
		
		//newFile()이 실핻되고 Vector<Student>가 갱신된 상태
		
		studentLists.removeAllElements();//행 패널 데이터도 모두 삭제한다.
		//repaint();//잔상이 남아서 삽입해봤다
		
		StudentPanel st;
		
		//Vector<Student>로 리스트 한 행을 구성한다. 
		//Vector<StudentPanel> studentLists에 저장한다.
		//MyActionListener my;
		for(int i=0;i<studentVector.size();i++) {//벡터 크기만큼 반복
			st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);
			st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//라벨에 번호붙나?ㅇㅇ
			st.defaultShowPanel.indexPanel.check.setSelected(true);
			//my=;
			//st.defaultShowPanel.delete.addActionListener(new MyActionListener(i));
			
			studentLists.add(st);//벡터에 저장
			
		}
		
		//Vector<StudentPanel>를 ListBody에 붙인다.
		//System.out.println(studentLists.get(0).getHeight()); //잘 출력된다!ㅇㅇ
		for(int i=0;i<studentLists.size();i++) {
			//studentLists의 요소들은 크기가 정해져있다.
			//ListBody에 삽입할 때 위치만 고려하면 된다.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			//System.out.println(i*onePanelHeight);//디버그 프린트 잘 된다.
			add(studentLists.get(i));//안나타난다. 왜그럴까..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		
		//body 크기도 같이 변경해야 한다.
		dd=new Dimension(width,25*studentVector.size()+10);//부모 자식 컨테이너 크기맞추기.오케
		//1000개 최대로 해보려고 1000이지, 사용자 설정으로 바꿀 수도 있겠다.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	//콤보박스 인덱스2. 파일 추가
	public void appendFileToList(String path) {
		int beforeSize=appendFile(path);//벡터에 저장까지만 한다. 콤보박스 누르면 실행해야 겠지
		
		//Vector<StudentPanel>에 이어붙이기 위해서
		//이전 벡터 크기를 받아올거야!!
		//이전 벡터에서는 (크기-1) 인덱스까지 데이터가 저장된 상태니까
		//(크기)인덱스부터 데이터를 추가하면 되겠다.
		//그런데.. studentVector크기랑 studentsList 크기랑 같으니까 애초에 받아올 필요없이
		//studentsList 크기부터 이어붙여도 가능했을텐데 바보
		
		//여기까지 학생 데이터 완성된 상태
		
		//새롭게 리스트를 만들어야 하니까 studentLists다 지우면 되겠지...?
		//studentLists.removeAllElements();//그니까 이걸 지울 필요가 없다고 왜지원 이어붙여야 빨리되지
		
		StudentPanel st;
		//MyActionListener my;
		//루프로 리스트 한 행씩 만든다.
		for(int i=beforeSize;i<studentVector.size();i++) {//벡터 크기만큼 반복
			st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);
			st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//라벨에 번호붙나?ㅇㅇ
			st.defaultShowPanel.indexPanel.check.setSelected(true);
			studentLists.add(st);//벡터에 저장
			//my=;
			//st.defaultShowPanel.delete.addActionListener(new MyActionListener(i));
			
		}
		
		//만든 리스트 패널에 붙인다.
		for(int i=beforeSize;i<studentLists.size();i++) {
			//studentLists의 요소들은 크기가 정해져있다.
			//ListBody에 삽입할 때 위치만 고려하면 된다.
			
			//이어붙여야 하므로 위치를 계산할 필요가 있다.
			//루프 인덱스를 수정했으므로 여기서 따로 수정할 필요는 없겠다.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			
			add(studentLists.get(i));
			
		}
		//이전 데이터가 안보이는 이유가 뭘까. 이벤트가 발생할 때 데이터를 다 지워서 그런가
		
		//body 크기도 같이 변경해야 한다.
		dd=new Dimension(width,25*studentVector.size()+10);//부모 자식 컨테이너 크기맞추기.오케
		//1000개 최대로 해보려고 1000이지, 사용자 설정으로 바꿀 수도 있겠다.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	
	
	
	
	public int appendFile(String path) {//벡터에 저장하는 동작까지!!!
		//addAll(Collection
		//임시 벡터 만들고 기존 벡터에 이어붙여야 한다. 야야 그냥 추가하자..
		//Vector<Student> tempVector=new Vector<>();
		
		Student student;
		
		int beforeSize=studentVector.size();
		
		// 파일 처리할때는 예외 처리
		try {
		  // 파일로 부터 입력을 받음.
		  //File f=new File("list.txt");//여기서 파일을 선택해야 겠지!
			File f=new File(path);
			Scanner s=new Scanner(f);//new Scanner(System.in)대신
		  
		  // 파일에 입력하기
		  //PrintWriter pw=new PrintWriter("out.txt");
		  //FileWriter fw=new FileWriter("out2.txt",true);
		  // 프린트 라이터는 프린트를 쓰는데 파일 라이터는 wirte를 쓴다.
		  // 두번째 인자가 true인 경우는 이미 파일이 존재하면 거기에 덧붙여 쓰겠다!!

			while(s.hasNext()) {
			  
				student=new Student();
		 	
				student.name=s.next(); // String 을 읽을 땐  next
				student.st_num=s.next();
				student.gender=s.next();
				student.score_java=s.next();// 정수 읽을 땐 nextInt
				student.score_python=s.next();// 정수 읽을 땐 nextInt
				student.score_c=s.next();// 정수 읽을 땐 nextInt
		     
				studentVector.add(student);//이어붙이기
		      //혹시모르니 tempVector 지운다.
		      //tempVector.removeAllElements();
			}
			s.close();
			}catch(IOException e) {
		  
				System.out.println("파일이 발견되지 않았습니다.");
			}

		//studentVector에 tempVector 이어붙인다.
		//studentVector.addAll(tempVector);
		/*for(int i=0;i<tempVector.size();i++) {
			studentVector.add(tempVector.get(i));
		}*/
		
		return beforeSize;
	}
	
	public void newFile(String path) {//벡터에 저장하는 동작까지!!!
		//Vector<Student>, Vector<StudentPanel> 둘다 지우고 새 벡터로 동작한다. 벡터 지우는동작이 뭐야? 이어붙이는 동작도 가져오기
		//removeAllElements()
		studentVector.removeAllElements();//학생 데이터 모두 삭제한다.		
		
		Student student;
		
		// 파일 처리할때는 예외 처리
		try {
		  // 파일로 부터 입력을 받음.
			File f=new File(path);//여기서 파일을 선택해야 겠지!
			Scanner s=new Scanner(f);//new Scanner(System.in)대신
		  
		  // 파일에 입력하기
		  //PrintWriter pw=new PrintWriter("out.txt");
		  //FileWriter fw=new FileWriter("out2.txt",true);
		  // 프린트 라이터는 프린트를 쓰는데 파일 라이터는 wirte를 쓴다.
		  // 두번째 인자가 true인 경우는 이미 파일이 존재하면 거기에 덧붙여 쓰겠다!!

			while(s.hasNext()) {
			  
				student=new Student();
		 	
				student.name=s.next(); // String 을 읽을 땐  next
				student.st_num=s.next();
				student.gender=s.next();
				student.score_java=s.next();// 정수 읽을 땐 nextInt
				student.score_python=s.next();// 정수 읽을 땐 nextInt
				student.score_c=s.next();// 정수 읽을 땐 nextInt
		     
				studentVector.add(student);
			}
			s.close();
		}catch(IOException e) {
		  
			System.out.println("파일이 발견되지 않았습니다.");
		}

		
	}
	
	
	
	
	
	
	
	
	
	//마우스가 항목버튼위로 올라가면 라벨 색이 변한다. 
	//근데 선택된 상태도 구분하고 싶어
	public void setLabelColor(String standard) {
		
		int color=235;
		
		if(standard=="이름") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[0].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[0].setBackground(Color.WHITE);
				
			}
		}else if(standard=="학번") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[1].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[1].setBackground(Color.WHITE);
				
			}
		}else if(standard=="성별") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[2].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[2].setBackground(Color.WHITE);
				
			}
		}else if(standard=="자바") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[3].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[3].setBackground(Color.WHITE);
				
			}
		}else if(standard=="파이썬") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[4].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[4].setBackground(Color.WHITE);
				
			}
		}else if(standard=="씨") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//선택된 상태라면
					studentLists.get(i).defaultShowPanel.components[5].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[5].setBackground(Color.WHITE);
				
			}
		}
	}
	//마우스가 항목버튼을 나가면 색이 원래대로 돌아온다.
	public void setLabelColorNULL(String standard) {
		//색을 지우는건 좋은데, 만약 체크박스가 선택된 상태라면 Color(220,220,220)으로 돌아와야 한다.
		//각 행별로 검사해야 하니까 for 루프안에 들어아겠지.
		int color=210;
		
		if(standard=="이름") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[0].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[0].setBackground(null);
				
			}
		}else if(standard=="학번") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[1].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[1].setBackground(null);
				
			}
		}else if(standard=="성별") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[2].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[2].setBackground(null);
				
			}
		}else if(standard=="자바") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[3].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[3].setBackground(null);
				
			}
		}else if(standard=="파이썬") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[4].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[4].setBackground(null);
				
			}
		}else if(standard=="씨") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[5].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[5].setBackground(null);
				
			}
		}
	}
	//항목버튼 누르면 정렬한다.
	public void sortStudent(String standard,boolean ascendFlag) {
		//각 기준별로 정렬
		if(standard=="이름") {
			//정렬
			
			if(ascendFlag==true) {//오름차
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getName().compareTo(st2.getName())>0)
							return 1;
						else if(st1.getName().compareTo(st2.getName())==0)
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						String str1=sp1.defaultShowPanel.components[0].getText();
						String str2=sp2.defaultShowPanel.components[0].getText();
						
						if(str1.compareTo(str2)>0)
							return 1;
						else if(str1.compareTo(str2)==0)
							return 0;
						else return -1;
					}
				});
				
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getName().compareTo(st2.getName())>0)
							return -1;
						else if(st1.getName().compareTo(st2.getName())==0)
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						String str1=sp1.defaultShowPanel.components[0].getText();
						String str2=sp2.defaultShowPanel.components[0].getText();
						
						if(str1.compareTo(str2)>0)
							return -1;
						else if(str1.compareTo(str2)==0)
							return 0;
						else return 1;
					}
				});
			}

		}else if(standard=="학번") {

			if(ascendFlag==true) {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getSt_num()>st2.getSt_num())
							return 1;
						else if(st1.getSt_num()==st2.getSt_num())
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[1].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[1].getText());
						
						if(spInt1>spInt2)
							return 1;
						else if(spInt1==spInt2)
							return 0;
						else return -1;
					}
				});
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getSt_num()>st2.getSt_num())
							return -1;
						else if(st1.getSt_num()==st2.getSt_num())
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[1].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[1].getText());
						
						if(spInt1>spInt2)
							return -1;
						else if(spInt1==spInt2)
							return 0;
						else return 1;
					}
				});
			}
		}else if(standard=="성별") {

			if(ascendFlag==true) {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getGender().compareTo(st2.getGender())>0)
							return 1;
						else if(st1.getGender().compareTo(st2.getGender())==0)
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						String str1=sp1.defaultShowPanel.components[2].getText();
						String str2=sp2.defaultShowPanel.components[2].getText();
						
						if(str1.compareTo(str2)>0)
							return 1;
						else if(str1.compareTo(str2)==0)
							return 0;
						else return -1;
					}
				});
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getGender().compareTo(st2.getGender())>0)
							return -1;
						else if(st1.getGender().compareTo(st2.getGender())==0)
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						String str1=sp1.defaultShowPanel.components[2].getText();
						String str2=sp2.defaultShowPanel.components[2].getText();
						
						if(str1.compareTo(str2)>0)
							return -1;
						else if(str1.compareTo(str2)==0)
							return 0;
						else return 1;
					}
				});
			}
		}else if(standard=="자바") {

			if(ascendFlag==true) {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_java()>st2.getScore_java())
							return 1;
						else if(st1.getScore_java()==st2.getScore_java())
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[3].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[3].getText());
						
						if(spInt1>spInt2)
							return 1;
						else if(spInt1==spInt2)
							return 0;
						else return -1;
					}
				});
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_java()>st2.getScore_java())
							return -1;
						else if(st1.getScore_java()==st2.getScore_java())
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[3].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[3].getText());
						
						if(spInt1>spInt2)
							return -1;
						else if(spInt1==spInt2)
							return 0;
						else return 1;
					}
				});
			}
		}else if(standard=="파이썬") {

			if(ascendFlag==true) {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_python()>st2.getScore_python())
							return 1;
						else if(st1.getScore_python()==st2.getScore_python())
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[4].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[4].getText());
						
						if(spInt1>spInt2)
							return 1;
						else if(spInt1==spInt2)
							return 0;
						else return -1;
					}
				});
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_python()>st2.getScore_python())
							return -1;
						else if(st1.getScore_python()==st2.getScore_python())
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[4].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[4].getText());
						
						if(spInt1>spInt2)
							return -1;
						else if(spInt1==spInt2)
							return 0;
						else return 1;
					}
				});
			}
		}else if(standard=="씨") {

			if(ascendFlag==true) {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_c()>st2.getScore_c())
							return 1;
						else if(st1.getScore_c()==st2.getScore_c())
							return 0;
						else return -1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[5].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[5].getText());
						
						if(spInt1>spInt2)
							return 1;
						else if(spInt1==spInt2)
							return 0;
						else return -1;
					}
				});
			}else {
				Collections.sort(studentVector,new Comparator<Student>() {
					@Override
					public int compare(Student st1, Student st2) {
						if(st1.getScore_c()>st2.getScore_c())
							return -1;
						else if(st1.getScore_c()==st2.getScore_c())
							return 0;
						else return 1;
					}
				});
				Collections.sort(studentLists,new Comparator<StudentPanel>() {
					@Override
					public int compare(StudentPanel sp1, StudentPanel sp2) {
						int spInt1=Integer.parseInt(sp1.defaultShowPanel.components[5].getText());
						int spInt2=Integer.parseInt(sp2.defaultShowPanel.components[5].getText());
						
						if(spInt1>spInt2)
							return -1;
						else if(spInt1==spInt2)
							return 0;
						else return 1;
					}
				});
			}
		}
		//studentLists도 같이 정렬했으니까
		//지울필요는 없고
		//위치만 다시 조정하면 되는거 아닌가?
		//해보자.

		//화면은 안지웠지!
		removeAll();//화면 지우는건 상관없다.
		//화면에 출력
		for(int i=0;i<studentLists.size();i++) {
			//studentLists의 요소들은 크기가 정해져있다.
			//ListBody에 삽입할 때 위치만 고려하면 된다.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			studentLists.get(i).defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//라벨에 번호붙나?ㅇㅇ
			//System.out.println(i*onePanelHeight);//디버그 프린트 잘 된다.
			add(studentLists.get(i));//안나타난다. 왜그럴까..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		repaint();
		revalidate();
		//한순간 잘 정렬되는데, JScollPane 스크롤하면 다시 돌아온다. 이게 웬일..;;
	}
	
	public void deleteStudent(StudentPanel panel) {
		
		studentVector.remove(panel.st);
		studentLists.remove(panel);
		
		removeAll();
		
		//화면에 출력
		for(int i=0;i<studentLists.size();i++) {
			//studentLists의 요소들은 크기가 정해져있다.
			//ListBody에 삽입할 때 위치만 고려하면 된다.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			studentLists.get(i).defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//라벨에 번호붙나?ㅇㅇ
			//System.out.println(i*onePanelHeight);//디버그 프린트 잘 된다.
			add(studentLists.get(i));//안나타난다. 왜그럴까..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		repaint();
		revalidate();
	}
	
	
}
