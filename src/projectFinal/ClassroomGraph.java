package projectFinal;
import java.awt.Graphics ;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.awt.Color ;
import java.awt.Container;
import javax.swing.*;
import javax.swing.JFrame ;
import javax.swing.JLabel ;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

class ClassroomGraph extends JPanel {
	
	Vector v;
	
	int step=XLINE/20; // x축 눈금 사이 간격
	int per;// y축 눈금 사이 간격
    
	
	private final static int PANEL_W=800;// 패널 너비
	private final static int PANEL_H=1200;// 패널 높이
    private static final int XLINE=500; // 막대그래프의 X축 길이
	private static final int YLINE=XLINE/3; // 막대그래프의 Y축 길이
	private static final int BETW=YLINE/3*5;// 두번 쨰 그래프의 높이
	private static final int MARGIN=100;// 패널들 합칠 때 레이아웃 맞출 떄 쓸 것임. 
	
	// 과목별 점수를 10점 단위로 분류해서 배열로 채워야 함.
	private int[] count_c= {0,0,0,0,0,0,0,0,0};// c언어 점수분류한 배열count_c
	private int[] count_j= {0,0,0,0,0,0,0,0,0};// java 점수 분류한 배열count_j
	private int[] count_p= {0,0,0,0,0,0,0,0,0};// python 점수 분류한 배열 count_p
	
	
	// 생성자
	ClassroomGraph(Vector v) {
    	 
    	this.v=v;
    	per=YLINE/v.size(); // y축 눈금 사이 간격
    	this.setBackground(Color.white);
       
		for(int i=0;i<v.size();i++) {
			
			// 벡터에서 학생 꺼내오기
			Student s=(Student)v.get(i);
			
			// 1. c언어 점수를 분류하여 배열에 넣는다.
			if(s.getScore_c()>=90) { count_c[0]++; }
			else if(s.getScore_c()>=80) { count_c[1]++; }
			else if(s.getScore_c()>=70) { count_c[2]++; }
			else if(s.getScore_c()>=60) { count_c[3]++; }
			else if(s.getScore_c()>=50) { count_c[4]++; }
			else if(s.getScore_c()>=40) { count_c[5]++; }
			else if(s.getScore_c()>=30) { count_c[6]++; }
			else if(s.getScore_c()>=20) { count_c[7]++; }
			else if(s.getScore_c()>=10) { count_c[8]++; }	
			// 2. java 점수를 분류하여 배열에 넣는다.
			if(s.getScore_java()>=90) { count_j[0]++; }
			else if(s.getScore_java()>=80) { count_j[1]++; }
			else if(s.getScore_java()>=70) { count_j[2]++; }
			else if(s.getScore_java()>=60) { count_j[3]++; }
			else if(s.getScore_java()>=50) { count_j[4]++; }
			else if(s.getScore_java()>=40) { count_j[5]++; }
			else if(s.getScore_java()>=30) { count_j[6]++; }
			else if(s.getScore_java()>=20) { count_j[7]++; }
			else if(s.getScore_java()>=10) { count_j[8]++; }
			// 3. python 점수를 분류하여 배열에 넣는다.
			if(s.getScore_python()>=90) { count_p[0]++; }
			else if(s.getScore_python()>=80) { count_p[1]++; }
			else if(s.getScore_python()>=70) { count_p[2]++; }
			else if(s.getScore_python()>=60) { count_p[3]++; }
			else if(s.getScore_python()>=50) { count_p[4]++; }
			else if(s.getScore_python()>=40) { count_p[5]++; }
			else if(s.getScore_python()>=30) { count_p[6]++; }
			else if(s.getScore_python()>=20) { count_p[7]++; }
			else if(s.getScore_python()>=10) { count_p[8]++; }
		}
	    
    }
	
    public void paintComponent(Graphics g) {
       super.paintComponents(g);
       g.setColor(Color.white) ;
       g.fillRect(0,0,PANEL_W,PANEL_H);
	   g.setColor(Color.black) ;
        
       int text=100;
       int text2=80;
       //___________ C 언 어 과목 그래프______________
       
       // x, y축의 의미를 알리는 string을 그림.
       g.drawString("[ c언어 ]", MARGIN, MARGIN+YLINE-text-100);
       g.drawString(" 학생 수 ", MARGIN-text2, MARGIN+YLINE-text/2-100);
       g.drawString(" 점수 ", MARGIN+XLINE+text2/2, MARGIN+YLINE+text2-60);
		
       // X축그리기
       g.drawLine(MARGIN,MARGIN+YLINE, MARGIN+XLINE,MARGIN+YLINE);
       // Y축그리기
       g.drawLine(MARGIN, MARGIN+YLINE, MARGIN, MARGIN);
       // y축 눈금그리기
       for(int i=0;i<v.size();i++) {
    	   if(i%10==0) {
    		   g.drawLine(MARGIN,MARGIN+YLINE-i*(per),MARGIN-step/5,MARGIN+YLINE-i*(per));
    		   g.drawString(i+"", MARGIN-step, MARGIN+YLINE-i*(per));
    	   }
       }
		
       int j=0;
       for(int i=0;i<18;i++) {
    	   // 0~18은 x축 점수를 표현함.
    	   g.drawLine(MARGIN+step*(i+1), (MARGIN+YLINE)+step/5,MARGIN+step*(i+1), MARGIN+YLINE);
    	   if(i<count_c.length) {
    		   // x축 눈금 그리기
    		   g.drawString(10*(i+1)+"",MARGIN+step*(i+j+1)+step/4,MARGIN+YLINE+step);
    		   j++;
    	   }
       }
       
       // 막대 그리기
       g.setColor(Color.pink);
       for(int i=0;i<9;i++) {
    	   g.fillRect(MARGIN+step*(i*2+1), MARGIN+YLINE-count_c[8-i]*per, step, count_c[8-i]*per);
       }
		
       g.setColor(Color.black);
       
       
       //___________ JAVA 언 어 과목 그래프______________ C언어 그래프보다 BETW만큼 떨어짐
       
       
       // x, y 축이 뭘 의미하는지 string을 그림.
       g.drawString("[ JAVA 언어 ]", MARGIN, MARGIN+YLINE+BETW-text-100);
       g.drawString(" 학생 수 ", MARGIN-text2, MARGIN+YLINE+BETW-text/2-100);  
       g.drawString(" 점수 ", MARGIN+XLINE+text2/2, MARGIN+YLINE+BETW+text2-60);
       // X축그리기
       g.drawLine(MARGIN,MARGIN+YLINE+BETW, MARGIN+XLINE,MARGIN+YLINE+BETW);
       // Y축그리기
       g.drawLine(MARGIN, MARGIN+YLINE+BETW, MARGIN, MARGIN+BETW);
       // y축 눈금그리기 : 지금은 벡터에 학생데이터가 없음. 그래서 나오지 못함.v.size()
       for(int i=0;i<40;i++) {
    	   if(i%10==0) {
    		   g.drawLine(MARGIN,MARGIN+YLINE-i*(per)+BETW,MARGIN-step/5,MARGIN+YLINE-i*(per)+BETW);
    		   g.drawString(i+"", MARGIN-step, MARGIN+YLINE-i*(per)+BETW);
    	   }
       }
		
       j=0;
       for(int i=0;i<18;i++) {
    	   // 0~18은 x축 점수를 표현함.
    	   g.drawLine(MARGIN+step*(i+1), (MARGIN+YLINE)+step/5+BETW,MARGIN+step*(i+1), MARGIN+YLINE+BETW);
    	   if(i<count_j.length) {
    		   // x축 눈금 그리기
    		   g.drawString(10*(i+1)+"",MARGIN+step*(i+j+1)+step/4,MARGIN+YLINE+step+BETW);
    		   j++;
    	   }
       }
       // 막대그리기
		g.setColor(Color.magenta);
		for(int i=0;i<9;i++) {
			g.fillRect(MARGIN+step*(i*2+1), MARGIN+YLINE-count_j[8-i]*per+BETW, step, count_j[8-i]*per);
		}
		//___________ python 언 어 과목 그래프______________ C언어 그래프보다 BETW만큼 떨어짐
		
        g.setColor(Color.black);
        // x, y축이 뭘 의미하는 지 string으로 그림
        g.drawString("[ PYTHON 언어 ]", MARGIN, MARGIN+YLINE+BETW*2-text-100);
        g.drawString(" 학생 수 ", MARGIN-text2, MARGIN+YLINE+BETW*2-text/2-100);   
        g.drawString(" 점수 ", MARGIN+XLINE+text2/2, MARGIN+YLINE+BETW*2-text2+50);
        
        // X축그리기
		g.drawLine(MARGIN,MARGIN+YLINE+BETW*2, MARGIN+XLINE,MARGIN+YLINE+BETW*2);
		// Y축그리기
		g.drawLine(MARGIN, MARGIN+YLINE+BETW*2, MARGIN, MARGIN+BETW*2);
		// y축 눈금그리기 : 지금은 벡터에 학생데이터가 없음. 그래서 나오지 못함.v.size()
		for(int i=0;i<40;i++) {
			if(i%10==0) {
				g.drawLine(MARGIN,MARGIN+YLINE-i*(per)+BETW*2,MARGIN-step/5,MARGIN+YLINE-i*(per)+BETW*2);
				g.drawString(i+"", MARGIN-step, MARGIN+YLINE-i*(per)+BETW*2);
			}
		}
		
		j=0;
		for(int i=0;i<18;i++) {
			// 0~18은 x축 점수를 표현함.
			g.drawLine(MARGIN+step*(i+1), (MARGIN+YLINE)+step/5+BETW*2,MARGIN+step*(i+1), MARGIN+YLINE+BETW*2);
			if(i<count_p.length) {
				// x축 눈금 그리기
				g.drawString(10*(i+1)+"",MARGIN+step*(i+j+1)+step/4,MARGIN+YLINE+step+BETW*2);
				j++;
			}
		}
		// 막대그리기
		g.setColor(Color.cyan);
		for(int i=0;i<9;i++) {
			g.fillRect(MARGIN+step*(i*2+1), MARGIN+YLINE-count_p[8-i]*per+BETW*2, step, count_p[8-i]*per);
		}
			
    }
    
  
  

}
