package projectFinal;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PersonGraph2 extends JPanel implements Runnable{
   
   // ���̾ƿ��� ���� ���
   // ���ﰢ�� ���¸� ����� ���� ��Ʈ 3�� �ʿ���.
   static double root3=Math.sqrt(3);
   // �ﰢ���� ���̱���
   private final static int G_HEIGHT=300;
   // �ﰢ���� �غ�����
   private final static int G_WIDTH=(int) (G_HEIGHT*(2/3)*root3);
   // ������ ����
   private final static int RAD=G_HEIGHT/3*2;
   // ������ ����
   private final static int DOLIM=G_HEIGHT+300;
   // ������ ���� ����
   private final static int BET=RAD/4*5;
   // ������ ���� ����
   private final static int LAY=G_HEIGHT/8;
   private final static int PADD=G_HEIGHT/3*2;

   // �л� �������
   Student s;
   Vector<Student> v;
  
   // ���� ���̺�
   DefaultTableModel defaultTableModel;
   // �л��̸� ��
   JLabel st_name;
   // �л��� ����
   String cgrade="0";
   String jgrade="0";
   String pgrade="0";
   
   // ���� ������ ���° �ε����� �ִ� �л����� ��Ÿ���� ����. �ʱⰪ : 0
   int student_index=0;
  
   // �ﰢ�� ����� �׷��� Ʋ
   int[][] xs1;
   int[][] ys1;
   
   // ���� ���� �迭  
   int[]score_x;
   int[]score_y;
   
   // �������� ���� �ʱⰪ
   int[]c_arc={0,90,180,270};
   int[]j_arc={0,90,180,270};
   int[]p_arc={0,90,180,270};
   
   // ������ ���� : �������� ���ư��� ��ó�� ���̰� ���ִ� ������
   Thread th;
   Runnable firstPanel;
   // ������
   public PersonGraph2(Vector<Student> v) {
      this.v=v;
      this.s=v.get(student_index);
      
      // ������ ����
      th=new Thread(this);
      firstPanel=this;
      
      th.start();
     
      // �����迭 ������ �Է�
      score_x=new int[3];
      score_y=new int[3];
      // �߽���ǥ�� �������� �� ������ ������ �׷��� ���� ��ġ�� ���Ѵ�.
      // �߽���ǥ : (xs1[0][0],ys1[0][0]+G_HEIGHT*2/3)
      
      // �׷��� �⺻ Ʋ ¥��
      xs1=new int[5][3]; // 5���� �ﰢ���� �׷������� ��.
      ys1=new int[5][3];
      for(int k=0;k<5;k++) {
         xs1[k][0]=(int)((2/3+root3/3)*G_HEIGHT)+PADD; //������
         xs1[k][1]=(int)((2/3)*G_HEIGHT)+(int)(k*root3/15*G_HEIGHT)+PADD;
         xs1[k][2]=(int)((2/3+2*root3/3)*G_HEIGHT)-(int)(k*root3/15*G_HEIGHT)+PADD;
      
         ys1[k][0]=(int)((2/3)*G_HEIGHT)+k*(G_HEIGHT*2/15)+PADD;
         ys1[k][1]=(int)((4/3)*G_HEIGHT)-k*(G_HEIGHT/15)+PADD;
         ys1[k][2]=ys1[k][1];
         
      }
      
      // ���� ��° �л��� ������ �����ֵ��� �ϴ� ��ư before
      JButton before=new JButton("���� �л�");
      // ���� ��° �л��� ������ �����ֵ��� �ϴ� ��ư next
      JButton next=new JButton("���� �л�");
      // �յڷ� �̵��ϴ� ��ư
      add(before);
      add(next);
      
      RemoteListener listener=new RemoteListener();
      before.addActionListener(listener);
      next.addActionListener(listener);
     
      st_name=new JLabel(s.name);
      st_name.setSize(100,100);
      st_name.setLocation(100,100);
      String columnNames[] =
    	  { " ","C���", "JAVA", "PYTHON" };

      Object rowData[][] =
    	  {
    			  { "����", s.getScore_c(), s.getScore_java(), s.getScore_python() },
    			 
    		 };

      //DefaultTableModel�� �����ϰ� ������ ���
  	  defaultTableModel = new DefaultTableModel(rowData, columnNames);
  	  
  	  //JTable�� DefaultTableModel�� ���
  	  JTable jTable = new JTable(defaultTableModel);
  	  //JScrollPane�� JTable�� ���
  	  JScrollPane jScollPane = new JScrollPane(jTable); 
  	  this.setLayout(null);
  	  before.setSize(100,30);
  	  before.setLocation(200,10);
  	  next.setSize(100,30);
	  next.setLocation(310,10);
  	  st_name.setFont(new Font("����",Font.BOLD,30));
  	  jScollPane.setSize(300,80);
  	  jScollPane.setLocation(430,10);
      add(jScollPane);
      add(st_name);
   }
      

   class RemoteListener implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
         if((((JButton)e.getSource()).getText()).equals("���� �л�")){
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
            if(student_index<v.size()) {// 40�� �ƴ϶� v.size()�� �����ؾ� ��.
               s=(Student) v.get(student_index);
               st_name.setText(s.name);
               //repaint();
               th.interrupt();
               // �ϳ��� ������� �ι� start�� �� ����.
               th=new Thread(firstPanel);
               th.start();
               // ���̺� ���� ������Ʈ
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
         
         
         // �ﰢ�� �׷��� �׸���
         // x��ǥ
         score_x[0]=xs1[0][0]; // ���� c
         score_x[1]=xs1[0][0]-(int)(G_HEIGHT/3*root3)*(s.getScore_java())/100; // ���� java
         score_x[2]=xs1[0][0]+(int)(G_HEIGHT/3*root3)*(s.getScore_python())/100; // ���� python
         // y��ǥ
         score_y[0]=ys1[0][0]+G_HEIGHT*2/3-G_HEIGHT/3*2*(s.getScore_c())/100;
         score_y[1]=ys1[0][0]+G_HEIGHT*2/3+G_HEIGHT/3*(s.getScore_java())/100;
         score_y[2]=ys1[0][0]+G_HEIGHT*2/3+G_HEIGHT/3*(s.getScore_python())/100;
         
            
         // �׷��� �⺻Ʋ �׸���
         // �ﰢ�� ������ �׸���
         g.setColor(Color.black);
         
         for(int i=0;i<5;i++) {
            g.drawPolygon(xs1[i],ys1[i],3);
         }
         g.setColor(Color.red);
         
         // �ﰢ���� �߽ɺο� �ﰢ���� ������ �����ϱ�
         for(int i=0;i<3;i++) {
            g.drawLine(xs1[0][0],ys1[0][0]+G_HEIGHT*2/3, xs1[0][i], ys1[0][i]);  
         }
         // �� ���� : ���� ������.
         g.setColor(new Color(0f, 0f, 0f, 0.5f));
         
         // ���� ������ ��ٰ��� �׸���
         g.fillPolygon(score_x,score_y,3);
         
         // �� ������ ���� ���� ������ �̹��� �ֱ�
         ImageIcon icon_java=new ImageIcon("images/java.png");
         ImageIcon icon_py=new ImageIcon("images/python.png");
         ImageIcon icon_c=new ImageIcon("images/visual-studio.png");
         Image img_java=icon_java.getImage();
         Image img_py=icon_py.getImage();
         Image img_c=icon_c.getImage();
         
         // �̹��� ���̱�
         g.drawImage(img_c, xs1[0][0]-G_HEIGHT/6,ys1[0][0]-G_HEIGHT/5*2,G_HEIGHT/3,G_HEIGHT/3,this);
         g.drawImage(img_java, xs1[0][1]-G_HEIGHT/5*2,ys1[0][1]+G_HEIGHT/30,G_HEIGHT/3,G_HEIGHT/3,this);
         g.drawImage(img_py, xs1[0][2]+G_HEIGHT/10,ys1[0][2]+G_HEIGHT/100,G_HEIGHT/3,G_HEIGHT/3,this);
      
         // ���� �̸� ����
         g.drawString("C���",xs1[0][0]-G_HEIGHT/30,ys1[0][0]-G_HEIGHT/20*9);
         g.drawString("JAVA",xs1[0][1]-G_HEIGHT/100*25 ,ys1[0][1]-G_HEIGHT/40);
         g.drawString("PYTHON",xs1[0][2]+G_HEIGHT/5,ys1[0][2]-G_HEIGHT/40);
         
         
         
         // �� �������� � ������ ��Ÿ���� �� ����
         g.setColor(Color.black);
         g.drawString("[ C��� ]",LAY+RAD/3,DOLIM+70);
         g.drawString("[ JAVA ]",LAY+BET+RAD/2,DOLIM+70);
         g.drawString("[ PYTHON ]",LAY+BET*2+RAD/3,DOLIM+70);
         
        
        	
         
        
         // ������ �׸���
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
         
         // ������ �۴�� �ٰ��� �迭
         int []stick_x1= {LAY+RAD,LAY+RAD+30,LAY+RAD-30};
         int []stick_y1= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         int []stick_x2= {LAY+RAD+BET,LAY+RAD+BET+30,LAY+RAD+BET-30};
         int []stick_y2= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         int []stick_x3= {LAY+RAD+BET*2,LAY+RAD+30+BET*2,LAY+RAD-30+BET*2};
         int []stick_y3= {DOLIM+100,DOLIM+100+30,DOLIM+100+60};
         // ������ �۴��
         g.setColor(Color.gray);
         g.fillPolygon(stick_x1,stick_y1,3);
         g.fillPolygon(stick_x2,stick_y2,3);
         g.fillPolygon(stick_x3,stick_y3,3);
         
         
         // �������� �� ���� ���� ������ ��Ÿ���� ��
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
			int round_p;//�������� ���ư��� Ƚ��
			
			
			// ���� �ʱ�ȭ
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
