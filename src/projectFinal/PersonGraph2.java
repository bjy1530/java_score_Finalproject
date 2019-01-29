package projectFinal;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PersonGraph2 extends JPanel implements Runnable{
   
   // 레이아웃을 위한 상수
   // 정삼각형 형태를 만들기 위해 루트 3이 필요함.
   static double root3=Math.sqrt(3);
   // 삼각형의 높이길이
   private final static int G_HEIGHT=300;
   // 삼각형의 밑변길이
   private final static int G_WIDTH=(int) (G_HEIGHT*(2/3)*root3);
   // 돌림판 지름
   private final static int RAD=G_HEIGHT/3*2;
   // 돌림판 높이
   private final static int DOLIM=G_HEIGHT+300;
   // 돌림판 사이 간격
   private final static int BET=RAD/4*5;
   // 돌림판 가로 여백
   private final static int LAY=G_HEIGHT/8;
   private final static int PADD=G_HEIGHT/3*2;

   // 학생 멤버변수
   Student s;
   Vector<Student> v;
  
   // 정리 테이블
   DefaultTableModel defaultTableModel;
   // 학생이름 라벨
   JLabel st_name;
   // 학생의 학점
   String cgrade="0";
   String jgrade="0";
   String pgrade="0";
   
   // 벡터 내에서 몇번째 인덱스에 있는 학생임을 나타내는 변수. 초기값 : 0
   int student_index=0;
  
   // 삼각형 모양의 그래프 틀
   int[][] xs1;
   int[][] ys1;
   
   // 과목 점수 배열  
   int[]score_x;
   int[]score_y;
   
   // 돌림판의 각도 초기값
   int[]c_arc={0,90,180,270};
   int[]j_arc={0,90,180,270};
   int[]p_arc={0,90,180,270};
   
   // 스레드 변수 : 돌림판이 돌아가는 것처럼 보이게 해주는 스레드
   Thread th;
   Runnable firstPanel;
   // 생성자
   public PersonGraph2(Vector<Student> v) {
      this.v=v;
      this.s=v.get(student_index);
      
      // 스레드 실행
      th=new Thread(this);
      firstPanel=this;
      
      th.start();
     
      // 점수배열 데이터 입력
      score_x=new int[3];
      score_y=new int[3];
      // 중심좌표를 기준으로 각 과목의 점수의 그래프 위의 위치를 구한다.
      // 중심좌표 : (xs1[0][0],ys1[0][0]+G_HEIGHT*2/3)
      
      // 그래프 기본 틀 짜기
      xs1=new int[5][3]; // 5개의 삼각형이 그려지도록 함.
      ys1=new int[5][3];
      for(int k=0;k<5;k++) {
         xs1[k][0]=(int)((2/3+root3/3)*G_HEIGHT)+PADD; //일정함
         xs1[k][1]=(int)((2/3)*G_HEIGHT)+(int)(k*root3/15*G_HEIGHT)+PADD;
         xs1[k][2]=(int)((2/3+2*root3/3)*G_HEIGHT)-(int)(k*root3/15*G_HEIGHT)+PADD;
      
         ys1[k][0]=(int)((2/3)*G_HEIGHT)+k*(G_HEIGHT*2/15)+PADD;
         ys1[k][1]=(int)((4/3)*G_HEIGHT)-k*(G_HEIGHT/15)+PADD;
         ys1[k][2]=ys1[k][1];
         
      }
      
      // 이전 번째 학생의 정보를 보여주도록 하는 버튼 before
      JButton before=new JButton("이전 학생");
      // 다음 번째 학생의 정보를 보여주도록 하는 버튼 next
      JButton next=new JButton("다음 학생");
      // 앞뒤로 이동하는 버튼
      add(before);
      add(next);
      
      RemoteListener listener=new RemoteListener();
      before.addActionListener(listener);
      next.addActionListener(listener);
     
      st_name=new JLabel(s.name);
      st_name.setSize(100,100);
      st_name.setLocation(100,100);
      String columnNames[] =
    	  { " ","C언어", "JAVA", "PYTHON" };

      Object rowData[][] =
    	  {
    			  { "점수", s.getScore_c(), s.getScore_java(), s.getScore_python() },
    			 
    		 };

      //DefaultTableModel을 선언하고 데이터 담기
  	  defaultTableModel = new DefaultTableModel(rowData, columnNames);
  	  
  	  //JTable에 DefaultTableModel을 담기
  	  JTable jTable = new JTable(defaultTableModel);
  	  //JScrollPane에 JTable을 담기
  	  JScrollPane jScollPane = new JScrollPane(jTable); 
  	  this.setLayout(null);
  	  before.setSize(100,30);
  	  before.setLocation(200,10);
  	  next.setSize(100,30);
	  next.setLocation(310,10);
  	  st_name.setFont(new Font("돋움",Font.BOLD,30));
  	  jScollPane.setSize(300,80);
  	  jScollPane.setLocation(430,10);
      add(jScollPane);
      add(st_name);
   }
      

   class RemoteListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
         if((((JButton)e.getSource()).getText()).equals("이전 학생")){
            student_index--;
            if(student_index>=0) {
               s=(Student) v.get(student_index);
               st_name.setText(s.name);
               th.interrupt();
               th=new Thread(firstPanel);
               th.start();
              
              
               defaultTableModel.setValueAt(s.getScore_c(), 0, 1);
               defaultTableModel.setValueAt(s.getScore_java(), 0, 2);
               defaultTableModel.setValueAt(s.getScore_python(), 0, 3);

            }
            else{
            	student_index++;     
            	 
            	}
             
         }
         else {
            student_index++;
            if(student_index<v.size()) {// 40이 아니라 v.size()로 수정해야 함.
               s=(Student) v.get(student_index);
               st_name.setText(s.name);
               //repaint();
               th.interrupt();
               // 하나의 스레드는 두번 start할 수 없다.
               th=new Thread(firstPanel);
               th.start();
               // 테이블 내용 업데이트
               defaultTableModel.setValueAt(s.getScore_c(), 0, 1);
               defaultTableModel.setValueAt(s.getScore_java(), 0, 2);
               defaultTableModel.setValueAt(s.getScore_python(), 0, 3);
            }
            else {
            	student_index--;
            	}
         }
         
      }
   }
   
      
   
      

      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         
         // 삼각형 그래프 그리기
         // x좌표
         score_x[0]=xs1[0][0]; // 과목 c
         score_x[1]=xs1[0][0]-(int)(G_HEIGHT/3*root3)*(s.getScore_java())/100; // 과목 java
         score_x[2]=xs1[0][0]+(int)(G_HEIGHT/3*root3)*(s.getScore_python())/100; // 과목 python
         // y좌표
         score_y[0]=ys1[0][0]+G_HEIGHT*2/3-G_HEIGHT/3*2*(s.getScore_c())/100;
         score_y[1]=ys1[0][0]+G_HEIGHT*2/3+G_HEIGHT/3*(s.getScore_java())/100;
         score_y[2]=ys1[0][0]+G_HEIGHT*2/3+G_HEIGHT/3*(s.getScore_python())/100;
         
            
         // 그래프 기본틀 그리기
         // 삼각형 여러개 그리기
         g.setColor(Color.black);
         
         for(int i=0;i<5;i++) {
            g.drawPolygon(xs1[i],ys1[i],3);
         }
         g.setColor(Color.red);
         
         // 삼각형의 중심부와 삼각형의 꼭짓점 연결하기
         for(int i=0;i<3;i++) {
            g.drawLine(xs1[0][0],ys1[0][0]+G_HEIGHT*2/3, xs1[0][i], ys1[0][i]);  
         }
         // 색 변경 : 투명도 조절함.
         g.setColor(new Color(0f, 0f, 0f, 0.5f));
         
         // 과목별 점수로 폐다각형 그리기
         g.fillPolygon(score_x,score_y,3);
         
         // 각 꼭짓점 위에 과목 아이콘 이미지 넣기
         ImageIcon icon_java=new ImageIcon("images/java.png");
         ImageIcon icon_py=new ImageIcon("images/python.png");
         ImageIcon icon_c=new ImageIcon("images/visual-studio.png");
         Image img_java=icon_java.getImage();
         Image img_py=icon_py.getImage();
         Image img_c=icon_c.getImage();
         
         // 이미지 붙이기
         g.drawImage(img_c, xs1[0][0]-G_HEIGHT/6,ys1[0][0]-G_HEIGHT/5*2,G_HEIGHT/3,G_HEIGHT/3,this);
         g.drawImage(img_java, xs1[0][1]-G_HEIGHT/5*2,ys1[0][1]+G_HEIGHT/30,G_HEIGHT/3,G_HEIGHT/3,this);
         g.drawImage(img_py, xs1[0][2]+G_HEIGHT/10,ys1[0][2]+G_HEIGHT/100,G_HEIGHT/3,G_HEIGHT/3,this);
      
         // 과목 이름 적기
         g.drawString("C언어",xs1[0][0]-G_HEIGHT/30,ys1[0][0]-G_HEIGHT/20*9);
         g.drawString("JAVA",xs1[0][1]-G_HEIGHT/100*25 ,ys1[0][1]-G_HEIGHT/40);
         g.drawString("PYTHON",xs1[0][2]+G_HEIGHT/5,ys1[0][2]-G_HEIGHT/40);
         
         
         
         // 각 돌림판이 어떤 과목을 나타내는 지 적기
         g.setColor(Color.black);
         g.drawString("[ C언어 ]",LAY+RAD/3,DOLIM+70);
         g.drawString("[ JAVA ]",LAY+BET+RAD/2,DOLIM+70);
         g.drawString("[ PYTHON ]",LAY+BET*2+RAD/3,DOLIM+70);
         
        
        	
         
        
         // 돌림판 그리기
         g.setColor(Color.red);
         g.fillArc(LAY,DOLIM+100,RAD,RAD,c_arc[0],90);
         g.setColor(Color.orange);
         g.fillArc(LAY, DOLIM+100, RAD, RAD, c_arc[1], 90);
         g.setColor(Color.yellow);
         g.fillArc(LAY,DOLIM+100,RAD,RAD,c_arc[2],90);
         g.setColor(Color.green);
         g.fillArc(LAY, DOLIM+100, RAD, RAD, c_arc[3], 90);
         
         
         g.setColor(Color.red);
         g.fillArc(LAY+BET,DOLIM+100,RAD,RAD,j_arc[0],90);
         g.setColor(Color.orange);
         g.fillArc(LAY+BET, DOLIM+100, RAD, RAD, j_arc[1], 90);
         g.setColor(Color.yellow);
         g.fillArc(LAY+BET,DOLIM+100,RAD,RAD,j_arc[2],90);
         g.setColor(Color.green);
         g.fillArc(LAY+BET, DOLIM+100,RAD, RAD, j_arc[3], 90);
         
         
         g.setColor(Color.red);
         g.fillArc(LAY+BET*2,DOLIM+100,RAD,RAD,p_arc[0],90);
         g.setColor(Color.orange);
         g.fillArc(LAY+BET*2, DOLIM+100, RAD,RAD, p_arc[1], 90);
         g.setColor(Color.yellow);
         g.fillArc(LAY+BET*2,DOLIM+100,RAD,RAD,p_arc[2],90);
         g.setColor(Color.green);
         g.fillArc(LAY+BET*2,DOLIM+100, RAD, RAD, p_arc[3], 90);
         
         // 돌림판 작대기 다각형 배열
         int []stick_x1= {LAY+RAD,LAY+RAD+30,LAY+RAD-30};
         int []stick_y1= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         int []stick_x2= {LAY+RAD+BET,LAY+RAD+BET+30,LAY+RAD+BET-30};
         int []stick_y2= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         int []stick_x3= {LAY+RAD+BET*2,LAY+RAD+30+BET*2,LAY+RAD-30+BET*2};
         int []stick_y3= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         // 돌림판 작대기
         g.setColor(Color.gray);
         g.fillPolygon(stick_x1,stick_y1,3);
         g.fillPolygon(stick_x2,stick_y2,3);
         g.fillPolygon(stick_x3,stick_y3,3);
         
         
         // 돌림판의 각 색이 무슨 학점을 나타내는 지
         g.setColor(Color.red);
         g.fillRect(LAY, DOLIM+100+RAD+30, RAD/2, 30);
         g.setColor(Color.black);
         g.drawString("A",LAY+RAD/2+30, DOLIM+100+RAD+45);
         
         g.setColor(Color.ORANGE);
         g.fillRect(LAY+RAD/2+80, DOLIM+100+RAD+30, RAD/2, 30);
         g.setColor(Color.black);
         g.drawString("B",LAY+RAD/2+8+RAD/2+80, DOLIM+100+RAD+45);
         
         g.setColor(Color.YELLOW);
         g.fillRect(LAY+RAD/2+250, DOLIM+100+RAD+30, RAD/2, 30);
         g.setColor(Color.black);
         g.drawString("C",LAY+RAD/2+300+60, DOLIM+100+RAD+45);
         
         g.setColor(Color.GREEN);
         g.fillRect(LAY+RAD/2+430, DOLIM+100+RAD+30, RAD/2, 30);
         g.setColor(Color.black);
         g.drawString("F",LAY+RAD/2+550, DOLIM+100+RAD+45);
         
      }





      @Override
		public void run() {
			int round_c;
			int round_j;
			int round_p;//돌림판이 돌아가는 횟수
			
			
			// 각도 초기화
			for(int i=0;i<4;i++) {
				c_arc[i]=i*90;
				j_arc[i]=i*90;
				p_arc[i]=i*90;
				
			}
			
			if(s.getScore_c()>=80) {
				round_c=12;cgrade="A";
			}
			else if(s.getScore_c()>=60) {
				round_c=9;cgrade="B";
			}
			else if(s.getScore_c()>=40) {
				round_c=18;cgrade="C";
			}
			else {
				round_c=15;cgrade="F";
			}
			
			if(s.getScore_java()>=80) {
				round_j=12;jgrade="A";
			}
			else if(s.getScore_java()>=60) {
				round_j=9;jgrade="B";
			}
			else if(s.getScore_java()>=40) {
				round_j=18;jgrade="C";
			}
			else {
				round_j=15;jgrade="F";
			}
			
			
			if(s.getScore_python()>=80) {
				round_p=12;pgrade="A";
			}
			else if(s.getScore_python()>=60) {
				round_p=9;pgrade="B";
			}
			else if(s.getScore_python()>=40) {
				round_p=18;pgrade="C";
			}
			else {
				round_p=15;pgrade="F";
			}
			
			for(int i=0;i<round_c;i++) {
				repaint();
				for(int k=0;k<4;k++) {
					c_arc[k]=c_arc[k]+30;
					if(c_arc[k]>360) {
						c_arc[k]=c_arc[k]-360;
					}
				}
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {}
			}
			
			for(int i=0;i<round_j;i++) {
				repaint();
				for(int k=0;k<4;k++) {
					j_arc[k]=j_arc[k]+30;
					if(j_arc[k]>360) {
						j_arc[k]=j_arc[k]-360;
					}
				}
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {}
			}
			for(int i=0;i<round_p;i++) {
				repaint();
				for(int k=0;k<4;k++) {
					p_arc[k]=p_arc[k]+30;
					if(p_arc[k]>360) {
						p_arc[k]=p_arc[k]-360;
					}
				}
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {}
			}
			
				
			
			
			
			
				
		}
      
      
   
   
   }
