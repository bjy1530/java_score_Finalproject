package projectFinal;

import java.awt.*;
import javax.swing.*;

//import project.Student;

import java.awt.event.*;

import java.io.*;
import java.util.*;

public class ListBody extends JPanel{//�л���
	//Vector<Student> students;//�л� �ؽ�Ʈ ������ �޾ƿԴ�. ���⼭ Panel �����߰���?
	
	//**�ӽ�**
	Vector<Student> studentVector=new Vector<>();
	Vector<StudentPanel> studentLists=new Vector<>();
	int onePanelHeight=25; //����Ʈ �ϳ��� ���� ũ��
	int width;
	
	Dimension dd;
	
	//�ӽ÷� �����. �л� ����Ʈ
	public ListBody(int w, int h) {//h�� ���� �Ƚ��. ���ʿ��ϸ� ������
		//�θ� ������Ʈ List���� ListBody�� ũ�⸦ �����ؼ� ListBody�� ũ�⸦ �� ������ �ʿ䰡 ����.
		//�׷��� ListBody�ȿ� �� ������Ʈ�����״� ũ�Ⱑ �� �ʿ��ϳ�.. �����;� �ڴ�.
		//ListBody ��ġ������?
		w-=10; //��ũ������ ��ũ�ѹٸ� ���� ����Ȯ��
		//h-=10; //��ũ�ѹٿ� ����� ũ�⸦ ���߱����� ����
		width=w;
		setLayout(null);


	}
	
	public void appendStudent(Student student) {
		int i=studentVector.size();//size()��ġ�� ���ο� ���� �߰��ɰŴ�.
		studentVector.add(student);
		//studentLists.add
		
		StudentPanel st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);//�߰��� ���ҷ� �ุ���
		st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));
		st.defaultShowPanel.indexPanel.check.setSelected(true);		
		//MyActionListener my=new MyActionListener(i);
		//st.defaultShowPanel.delete.addActionListener(my);
		studentLists.add(st);//���Ϳ� ����
		

		studentLists.get(i).setLocation(0,i*onePanelHeight);
		add(studentLists.get(i));
		
		//body ũ�⵵ ���� �����ؾ� �Ѵ�.
		dd=new Dimension(width,25*studentVector.size()+10);//�θ� �ڽ� �����̳� ũ����߱�.����
		//1000�� �ִ�� �غ����� 1000����, ����� �������� �ٲ� ���� �ְڴ�.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	//üũ�ڽ� ��ü����/��ü�������� ����
	public void setListCheckBox(boolean flag){
		//flag�� true�̸� ��ü ����
		for(int i=0;i<studentLists.size();i++) {//���� ũ�⸸ŭ �ݺ�
			studentLists.get(i).defaultShowPanel.indexPanel.check.setSelected(flag);
		}
	}
	public Vector<Student> getCheckedStudents(){
		//������ ����ϱ� ���� üũ�� �ุ ���� ���Ϳ� �����Ѵ�.
		Vector<Student> tempVector=new Vector<>();
		
		for(int i=0;i<studentLists.size();i++) {//���� ũ�⸸ŭ �ݺ�
			if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()==true) {//üũ�� �����̸�
				tempVector.add(studentVector.get(i));//tempVector�� �Űܴ�´�.
			}
		}
		
		return tempVector;
	}
	
	public void printToFile(String path) {
		
		//������ ����ϱ� ���� üũ�� �ุ ���� ���Ϳ� �����Ѵ�.
		Vector<Student> tempVector=getCheckedStudents();

		   
		   //Vector<Student> studentVector=new Vector<>();
	      // ���� ó���Ҷ��� ���� ó��

		try {

			   FileWriter fw=new FileWriter(path);
			   Student student;
			   String buffer="";
			   for(int i=0;i<tempVector.size();i++) {
				   student=tempVector.get(i);//��ü�ϳ� �����ͼ�
	        	 
				   buffer+=student.name+" ";
				   buffer+=student.st_num+" ";
				   buffer+=student.gender+" ";
				   buffer+=student.score_java+" ";
				   buffer+=student.score_python+" ";
				   buffer+=student.score_c;
				   //System.out.println(i+" "+buffer); //����� �׽�Ʈ����
				   //�� �� ����Ѵ�.
				   fw.write(buffer+"\r\n");//write�� �� ����� ���� �ٱⰡ �ȴ�.
				   buffer="";//buffer �ʱ�ȭ
			   }

			   fw.close();

		   }catch(IOException e) {
			   System.out.println("������ �߰ߵ��� �ʾҽ��ϴ�.");
		   }
		
		tempVector=null;//tempVector �ʱ�ȭ
		   
	}



	
	
	
	//�޺��ڽ� �ε���1. �� ���� �Է� //�޼��� ���ڷ� ���� �̸� �Է¹��� ��!!
	public void insertFileToList(String path) {
		newFile(path);//Vector<Student>, Vector<StudentPanel> �Ѵ� ����� �� ���ͷ� �����Ѵ�.
		//�׷� newFile�� ����� �������� ? �ű�� studentVector�� ó���ϰ� ���ͼ� �гθ���Ʈ �����Ұž�
		
		//newFile()�� ���P�ǰ� Vector<Student>�� ���ŵ� ����
		
		studentLists.removeAllElements();//�� �г� �����͵� ��� �����Ѵ�.
		//repaint();//�ܻ��� ���Ƽ� �����غô�
		
		StudentPanel st;
		
		//Vector<Student>�� ����Ʈ �� ���� �����Ѵ�. 
		//Vector<StudentPanel> studentLists�� �����Ѵ�.
		//MyActionListener my;
		for(int i=0;i<studentVector.size();i++) {//���� ũ�⸸ŭ �ݺ�
			st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);
			st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//�󺧿� ��ȣ�ٳ�?����
			st.defaultShowPanel.indexPanel.check.setSelected(true);
			//my=;
			//st.defaultShowPanel.delete.addActionListener(new MyActionListener(i));
			
			studentLists.add(st);//���Ϳ� ����
			
		}
		
		//Vector<StudentPanel>�� ListBody�� ���δ�.
		//System.out.println(studentLists.get(0).getHeight()); //�� ��µȴ�!����
		for(int i=0;i<studentLists.size();i++) {
			//studentLists�� ��ҵ��� ũ�Ⱑ �������ִ�.
			//ListBody�� ������ �� ��ġ�� ����ϸ� �ȴ�.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			//System.out.println(i*onePanelHeight);//����� ����Ʈ �� �ȴ�.
			add(studentLists.get(i));//�ȳ�Ÿ����. �ֱ׷���..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		
		//body ũ�⵵ ���� �����ؾ� �Ѵ�.
		dd=new Dimension(width,25*studentVector.size()+10);//�θ� �ڽ� �����̳� ũ����߱�.����
		//1000�� �ִ�� �غ����� 1000����, ����� �������� �ٲ� ���� �ְڴ�.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	//�޺��ڽ� �ε���2. ���� �߰�
	public void appendFileToList(String path) {
		int beforeSize=appendFile(path);//���Ϳ� ��������� �Ѵ�. �޺��ڽ� ������ �����ؾ� ����
		
		//Vector<StudentPanel>�� �̾���̱� ���ؼ�
		//���� ���� ũ�⸦ �޾ƿðž�!!
		//���� ���Ϳ����� (ũ��-1) �ε������� �����Ͱ� ����� ���´ϱ�
		//(ũ��)�ε������� �����͸� �߰��ϸ� �ǰڴ�.
		//�׷���.. studentVectorũ��� studentsList ũ��� �����ϱ� ���ʿ� �޾ƿ� �ʿ����
		//studentsList ũ����� �̾�ٿ��� ���������ٵ� �ٺ�
		
		//������� �л� ������ �ϼ��� ����
		
		//���Ӱ� ����Ʈ�� ������ �ϴϱ� studentLists�� ����� �ǰ���...?
		//studentLists.removeAllElements();//�״ϱ� �̰� ���� �ʿ䰡 ���ٰ� ������ �̾�ٿ��� ��������
		
		StudentPanel st;
		//MyActionListener my;
		//������ ����Ʈ �� �྿ �����.
		for(int i=beforeSize;i<studentVector.size();i++) {//���� ũ�⸸ŭ �ݺ�
			st=new StudentPanel(width,onePanelHeight,studentVector.get(i),this);
			st.defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//�󺧿� ��ȣ�ٳ�?����
			st.defaultShowPanel.indexPanel.check.setSelected(true);
			studentLists.add(st);//���Ϳ� ����
			//my=;
			//st.defaultShowPanel.delete.addActionListener(new MyActionListener(i));
			
		}
		
		//���� ����Ʈ �гο� ���δ�.
		for(int i=beforeSize;i<studentLists.size();i++) {
			//studentLists�� ��ҵ��� ũ�Ⱑ �������ִ�.
			//ListBody�� ������ �� ��ġ�� ����ϸ� �ȴ�.
			
			//�̾�ٿ��� �ϹǷ� ��ġ�� ����� �ʿ䰡 �ִ�.
			//���� �ε����� ���������Ƿ� ���⼭ ���� ������ �ʿ�� ���ڴ�.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			
			add(studentLists.get(i));
			
		}
		//���� �����Ͱ� �Ⱥ��̴� ������ ����. �̺�Ʈ�� �߻��� �� �����͸� �� ������ �׷���
		
		//body ũ�⵵ ���� �����ؾ� �Ѵ�.
		dd=new Dimension(width,25*studentVector.size()+10);//�θ� �ڽ� �����̳� ũ����߱�.����
		//1000�� �ִ�� �غ����� 1000����, ����� �������� �ٲ� ���� �ְڴ�.
		setPreferredSize(dd);
		
		repaint();
		revalidate();
	}
	
	
	
	
	public int appendFile(String path) {//���Ϳ� �����ϴ� ���۱���!!!
		//addAll(Collection
		//�ӽ� ���� ����� ���� ���Ϳ� �̾�ٿ��� �Ѵ�. �߾� �׳� �߰�����..
		//Vector<Student> tempVector=new Vector<>();
		
		Student student;
		
		int beforeSize=studentVector.size();
		
		// ���� ó���Ҷ��� ���� ó��
		try {
		  // ���Ϸ� ���� �Է��� ����.
		  //File f=new File("list.txt");//���⼭ ������ �����ؾ� ����!
			File f=new File(path);
			Scanner s=new Scanner(f);//new Scanner(System.in)���
		  
		  // ���Ͽ� �Է��ϱ�
		  //PrintWriter pw=new PrintWriter("out.txt");
		  //FileWriter fw=new FileWriter("out2.txt",true);
		  // ����Ʈ �����ʹ� ����Ʈ�� ���µ� ���� �����ʹ� wirte�� ����.
		  // �ι�° ���ڰ� true�� ���� �̹� ������ �����ϸ� �ű⿡ ���ٿ� ���ڴ�!!

			while(s.hasNext()) {
			  
				student=new Student();
		 	
				student.name=s.next(); // String �� ���� ��  next
				student.st_num=s.next();
				student.gender=s.next();
				student.score_java=s.next();// ���� ���� �� nextInt
				student.score_python=s.next();// ���� ���� �� nextInt
				student.score_c=s.next();// ���� ���� �� nextInt
		     
				studentVector.add(student);//�̾���̱�
		      //Ȥ�ø𸣴� tempVector �����.
		      //tempVector.removeAllElements();
			}
			s.close();
			}catch(IOException e) {
		  
				System.out.println("������ �߰ߵ��� �ʾҽ��ϴ�.");
			}

		//studentVector�� tempVector �̾���δ�.
		//studentVector.addAll(tempVector);
		/*for(int i=0;i<tempVector.size();i++) {
			studentVector.add(tempVector.get(i));
		}*/
		
		return beforeSize;
	}
	
	public void newFile(String path) {//���Ϳ� �����ϴ� ���۱���!!!
		//Vector<Student>, Vector<StudentPanel> �Ѵ� ����� �� ���ͷ� �����Ѵ�. ���� ����µ����� ����? �̾���̴� ���۵� ��������
		//removeAllElements()
		studentVector.removeAllElements();//�л� ������ ��� �����Ѵ�.		
		
		Student student;
		
		// ���� ó���Ҷ��� ���� ó��
		try {
		  // ���Ϸ� ���� �Է��� ����.
			File f=new File(path);//���⼭ ������ �����ؾ� ����!
			Scanner s=new Scanner(f);//new Scanner(System.in)���
		  
		  // ���Ͽ� �Է��ϱ�
		  //PrintWriter pw=new PrintWriter("out.txt");
		  //FileWriter fw=new FileWriter("out2.txt",true);
		  // ����Ʈ �����ʹ� ����Ʈ�� ���µ� ���� �����ʹ� wirte�� ����.
		  // �ι�° ���ڰ� true�� ���� �̹� ������ �����ϸ� �ű⿡ ���ٿ� ���ڴ�!!

			while(s.hasNext()) {
			  
				student=new Student();
		 	
				student.name=s.next(); // String �� ���� ��  next
				student.st_num=s.next();
				student.gender=s.next();
				student.score_java=s.next();// ���� ���� �� nextInt
				student.score_python=s.next();// ���� ���� �� nextInt
				student.score_c=s.next();// ���� ���� �� nextInt
		     
				studentVector.add(student);
			}
			s.close();
		}catch(IOException e) {
		  
			System.out.println("������ �߰ߵ��� �ʾҽ��ϴ�.");
		}

		
	}
	
	
	
	
	
	
	
	
	
	//���콺�� �׸��ư���� �ö󰡸� �� ���� ���Ѵ�. 
	//�ٵ� ���õ� ���µ� �����ϰ� �;�
	public void setLabelColor(String standard) {
		
		int color=235;
		
		if(standard=="�̸�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[0].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[0].setBackground(Color.WHITE);
				
			}
		}else if(standard=="�й�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[1].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[1].setBackground(Color.WHITE);
				
			}
		}else if(standard=="����") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[2].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[2].setBackground(Color.WHITE);
				
			}
		}else if(standard=="�ڹ�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[3].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[3].setBackground(Color.WHITE);
				
			}
		}else if(standard=="���̽�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[4].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[4].setBackground(Color.WHITE);
				
			}
		}else if(standard=="��") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {//���õ� ���¶��
					studentLists.get(i).defaultShowPanel.components[5].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[5].setBackground(Color.WHITE);
				
			}
		}
	}
	//���콺�� �׸��ư�� ������ ���� ������� ���ƿ´�.
	public void setLabelColorNULL(String standard) {
		//���� ����°� ������, ���� üũ�ڽ��� ���õ� ���¶�� Color(220,220,220)���� ���ƿ;� �Ѵ�.
		//�� �ະ�� �˻��ؾ� �ϴϱ� for �����ȿ� ���ư���.
		int color=210;
		
		if(standard=="�̸�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[0].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[0].setBackground(null);
				
			}
		}else if(standard=="�й�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[1].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[1].setBackground(null);
				
			}
		}else if(standard=="����") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[2].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[2].setBackground(null);
				
			}
		}else if(standard=="�ڹ�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[3].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[3].setBackground(null);
				
			}
		}else if(standard=="���̽�") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[4].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[4].setBackground(null);
				
			}
		}else if(standard=="��") {
			for(int i=0;i<studentVector.size();i++) {
				if(studentLists.get(i).defaultShowPanel.indexPanel.check.isSelected()) {
					studentLists.get(i).defaultShowPanel.components[5].setBackground(new Color(color,color,color));
				}else studentLists.get(i).defaultShowPanel.components[5].setBackground(null);
				
			}
		}
	}
	//�׸��ư ������ �����Ѵ�.
	public void sortStudent(String standard,boolean ascendFlag) {
		//�� ���غ��� ����
		if(standard=="�̸�") {
			//����
			
			if(ascendFlag==true) {//������
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

		}else if(standard=="�й�") {

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
		}else if(standard=="����") {

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
		}else if(standard=="�ڹ�") {

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
		}else if(standard=="���̽�") {

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
		}else if(standard=="��") {

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
		//studentLists�� ���� ���������ϱ�
		//�����ʿ�� ����
		//��ġ�� �ٽ� �����ϸ� �Ǵ°� �ƴѰ�?
		//�غ���.

		//ȭ���� ��������!
		removeAll();//ȭ�� ����°� �������.
		//ȭ�鿡 ���
		for(int i=0;i<studentLists.size();i++) {
			//studentLists�� ��ҵ��� ũ�Ⱑ �������ִ�.
			//ListBody�� ������ �� ��ġ�� ����ϸ� �ȴ�.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			studentLists.get(i).defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//�󺧿� ��ȣ�ٳ�?����
			//System.out.println(i*onePanelHeight);//����� ����Ʈ �� �ȴ�.
			add(studentLists.get(i));//�ȳ�Ÿ����. �ֱ׷���..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		repaint();
		revalidate();
		//�Ѽ��� �� ���ĵǴµ�, JScollPane ��ũ���ϸ� �ٽ� ���ƿ´�. �̰� ����..;;
	}
	
	public void deleteStudent(StudentPanel panel) {
		
		studentVector.remove(panel.st);
		studentLists.remove(panel);
		
		removeAll();
		
		//ȭ�鿡 ���
		for(int i=0;i<studentLists.size();i++) {
			//studentLists�� ��ҵ��� ũ�Ⱑ �������ִ�.
			//ListBody�� ������ �� ��ġ�� ����ϸ� �ȴ�.
			studentLists.get(i).setLocation(0,i*onePanelHeight);
			studentLists.get(i).defaultShowPanel.indexPanel.index.setText(Integer.toString(i+1));//�󺧿� ��ȣ�ٳ�?����
			//System.out.println(i*onePanelHeight);//����� ����Ʈ �� �ȴ�.
			add(studentLists.get(i));//�ȳ�Ÿ����. �ֱ׷���..
			//new Frame().getContentPane().add(studentLists.get(i));
		}
		repaint();
		revalidate();
	}
	
	
}
