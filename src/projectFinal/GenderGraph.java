package projectFinal;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class GenderGraph extends JPanel {
	
		Vector v;
		
		int mMany=0;//총 남학생수
		int wMany=0;// 총 여학생수
		private static final int XMARGIN=100;
		private static final int YMARGIN=XMARGIN/5*3;
		// 배치여백
		private final static int RAD=XMARGIN*2;
  	    private final static int DOLIM=XMARGIN*3+300;
  	    private final static int LAY=XMARGIN*3/8;
		
		
		// 패널 너비
		private final static int PANEL_W=800;
		// 패널 높이
		private final static int PANEL_H=1200;
		// 임의로 넣은 값. 학생 학점 벡터가 생기면 초기값은 0으로 둘것임 
		int[] Ccount_w= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 여성
		int[] Ccount_m= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 남성
		//JAVA언어
		int[] Jcount_w= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 여성
		int[] Jcount_m= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 남성
		//PYTHON언어
		int[] Pcount_w= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 여성
		int[] Pcount_m= {0,0,0,0};//4.0이상인 , 3.0이상인, 2.0이상인, 그 외인 남성		
		// C언어 여자 비율
		
		// C언어 남자 비율
		// Java 여자 비율
		// java 남자 비율
		// python 여자 비율
		// python 남자 비율
		
	    GenderGraph(Vector v){
	    	//C언어
	    	this.setBackground(Color.RED);
	    	//c.setBackground(Color.white);
	    	mMany=0;
			wMany=0;
	    	//this.c=c; 
	    	this.v=v;
	    	Student s;
	    	// v.ssize()로 바꿔야 함. 
	    	
	    	for(int i=0;i<v.size();i++) {
	    		
	    		s=(Student) v.get(i);
	    		
	    		if(s.gender.equals("여")) {
	    				wMany++;
	    				// 이 여학생의 c언어가 어느 학점에 속하는지
	    				if(s.getScore_c()>=80) {
	    					Ccount_w[0]++;
	    				}
	    				else if(s.getScore_c()>=60) {
	    					Ccount_w[1]++;
	    				}
	    				else if(s.getScore_c()>=40) {
	    					Ccount_w[2]++;
	    				}
	    				else {
	    					Ccount_w[3]++;
	    				}
	    				// 이 여학생의 java언어가 어느 학점에 속하는지
	    				if(s.getScore_java()>=80) {
	    					Jcount_w[0]++;
	    				}
	    				else if(s.getScore_java()>=60) {
	    					Jcount_w[1]++;
	    				}
	    				else if(s.getScore_java()>=40) {
	    					Jcount_w[2]++;
	    				}
	    				else {
	    					Jcount_w[3]++;
	    				}
	    				// 이 여학생의 PYHTON언어가 어느 학점에 속하는지
	    				if(s.getScore_python()>=80) {
	    					Pcount_w[0]++;
	    				}
	    				else if(s.getScore_python()>=60) {
	    					Pcount_w[1]++;
	    				}
	    				else if(s.getScore_python()>=40) {
	    					Pcount_w[2]++;
	    				}
	    				else {
	    					Pcount_w[3]++;
	    				}
	    				
	    			
	    		}
	    		else {
	    		mMany++;
	    			// 이남 학생의 c언어가 어느 학점에 속하는지
    				if(s.getScore_c()>=80) {
    					Ccount_m[0]++;
    				}
    				else if(s.getScore_c()>=60) {
    					Ccount_m[1]++;
    				}
    				else if(s.getScore_c()>=40) {
    					Ccount_m[2]++;
    				}
    				else {
    					Ccount_m[3]++;
    				}
    				// 이 남학생의 java언어가 어느 학점에 속하는지
    				if(s.getScore_java()>=80) {
    					Jcount_m[0]++;
    				}
    				else if(s.getScore_java()>=60) {
    					Jcount_m[1]++;
    				}
    				else if(s.getScore_java()>=40) {
    					Jcount_m[2]++;
    				}
    				else {
    					Jcount_m[3]++;
    				}
    				// 이 남학생의 PYHTON언어가 어느 학점에 속하는지
    				if(s.getScore_python()>=80) {
    					Pcount_m[0]++;
    				}
    				else if(s.getScore_python()>=60) {
    					Pcount_m[1]++;
    				}
    				else if(s.getScore_python()>=40) {
    					Pcount_m[2]++;
    				}
    				else {
    					Pcount_m[3]++;
    				}
    				
    			
    		}
    		
    		
	    		//
	    	
				
			}
	    	
	       // c.add("성별별",this);
		    setSize(300, 300) ;
		    setLocation(150, 200) ;  
		    
		    
	    }

	    int per=30;// drawString의 폰 트 크기
	    public void paintComponent(Graphics g) {
	       super.paintComponents(g);
	       g.setColor(Color.white) ;
	       g.fillRect(0,0,PANEL_W,PANEL_H);
	       g.setColor(Color.black);
	      
	       
	       // c언어 _________________________________________________________________________
	       g.setColor(Color.black);
	       g.drawString("[ c언어 ]", XMARGIN/5*3, YMARGIN);
	       
	       // 남a비율
	       g.drawString("(남)", XMARGIN*2-15, YMARGIN+XMARGIN*2+30);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,0,Ccount_m[0]*360/mMany);
	       int s=Ccount_m[0]*360/mMany;
	       
	    // 남b비율
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[1]*360/mMany);
	       s=s+Ccount_m[1]*360/mMany;
	    // 남c비율
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[2]*360/mMany);
	       s=s+Ccount_m[2]*360/mMany;
	    // 남 그외비율
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[3]*360/mMany);
	       
	       // 여
	       g.setColor(Color.black);
	       g.drawString("(여)", XMARGIN*5+30, YMARGIN+XMARGIN*2+30);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN,XMARGIN*2,XMARGIN*2,0,Ccount_w[0]*360/wMany);
	       s=Ccount_w[0]*360/wMany;
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_w[1]*360/wMany);
	       s=s+Ccount_w[1]*360/wMany;
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_w[2]*360/wMany);
	       s=s+Ccount_w[2]*360/wMany;
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_w[3]*360/wMany);
	
	       int jgan=50;
	       
	       
	       // JAVA언어____________________________________________________________________________
	       g.setColor(Color.black);
	       g.drawString("[ Java ]", XMARGIN/5*3, YMARGIN+per+XMARGIN*2+jgan);
	      
	       // 남
	       g.setColor(Color.black);
	       g.drawString("(남)", XMARGIN*2-15, YMARGIN+XMARGIN*5+15);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,0,Jcount_m[0]*360/mMany);
	       s=Jcount_m[0]*360/mMany;
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_m[1]*360/mMany);
	       s=s+Jcount_m[1]*360/mMany;
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_m[2]*360/mMany);
	       s=s+Jcount_m[2]*360/mMany;
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_m[3]*360/mMany);
	       
	       // 여
	       g.setColor(Color.black);
	       g.drawString("(여)", XMARGIN*5+30, YMARGIN+XMARGIN*5+15);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,0,Jcount_w[0]*360/wMany);
	       s=Jcount_w[0]*360/wMany;
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_w[1]*360/wMany);
	       s=s+Jcount_w[1]*360/wMany;
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_w[2]*360/wMany);
	       s=s+Jcount_w[2]*360/wMany;
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per+XMARGIN*2+jgan,XMARGIN*2,XMARGIN*2,s,Jcount_w[3]*360/wMany);
	      
	       jgan=jgan*2;
	       // python언어___________________________________________________________________________ 
	       g.setColor(Color.black);
	       g.drawString("[ Python ]", XMARGIN/5*3, YMARGIN+per*2+XMARGIN*4+jgan);
	       // 남
	       g.setColor(Color.black);
	       g.drawString("(남)", XMARGIN*2-15, YMARGIN+XMARGIN*8);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,0,Pcount_m[0]*360/mMany);
	       s=Pcount_m[0]*360/mMany;
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_m[1]*360/mMany);
	       s=s+Pcount_m[1]*360/mMany;
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_m[2]*360/mMany);
	       s=s+Pcount_m[2]*360/mMany;
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_m[3]*360/mMany);
	       
	       // 여
	       g.setColor(Color.black);
	       g.drawString("(여)", XMARGIN*5+30, YMARGIN+XMARGIN*8);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,0,Pcount_w[0]*360/wMany);
	       s=Pcount_w[0]*360/wMany;
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_w[1]*360/wMany);
	       s=s+Pcount_w[1]*360/wMany;
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_w[2]*360/wMany);
	       s=s+Pcount_w[2]*360/wMany;
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN+XMARGIN*3 +per,YMARGIN+per*2+XMARGIN*4+jgan,XMARGIN*2,XMARGIN*2,s,Pcount_w[3]*360/wMany);
	       
	       // 돌림판의 각 색이 무슨 학점을 나타내는 지
	       g.setColor(Color.red);
	       g.fillRect(LAY, DOLIM+100+RAD+30, RAD/2, 30);
	       g.setColor(Color.black);
	       g.drawString("A",LAY+RAD/2+30, DOLIM+100+RAD+45);
	       
	       g.setColor(Color.YELLOW);
	       g.fillRect(LAY+RAD/2+80, DOLIM+100+RAD+30, RAD/2, 30);
	       g.setColor(Color.black);
	       g.drawString("B",LAY+RAD/2+8+RAD/2+80, DOLIM+100+RAD+45);
	       
	       g.setColor(Color.GRAY);
	       g.fillRect(LAY+RAD/2+250, DOLIM+100+RAD+30, RAD/2, 30);
	       g.setColor(Color.black);
	       g.drawString("C",LAY+RAD/2+300+60, DOLIM+100+RAD+45);
	         
	       g.setColor(Color.PINK);
	       g.fillRect(LAY+RAD/2+430, DOLIM+100+RAD+30, RAD/2, 30);
	       g.setColor(Color.black);
	       g.drawString("F",LAY+RAD/2+550, DOLIM+100+RAD+45);
	    }
	    

}