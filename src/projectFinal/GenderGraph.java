package projectFinal;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class GenderGraph extends JPanel {
	
		Vector v;
		
		int mMany=0;//�� ���л���
		int wMany=0;// �� ���л���
		private static final int XMARGIN=100;
		private static final int YMARGIN=XMARGIN/5*3;
		// ��ġ����
		private final static int RAD=XMARGIN*2;
  	    private final static int DOLIM=XMARGIN*3+300;
  	    private final static int LAY=XMARGIN*3/8;
		
		
		// �г� �ʺ�
		private final static int PANEL_W=800;
		// �г� ����
		private final static int PANEL_H=1200;
		// ���Ƿ� ���� ��. �л� ���� ���Ͱ� ����� �ʱⰪ�� 0���� �Ѱ��� 
		int[] Ccount_w= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����
		int[] Ccount_m= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����
		//JAVA���
		int[] Jcount_w= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����
		int[] Jcount_m= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����
		//PYTHON���
		int[] Pcount_w= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����
		int[] Pcount_m= {0,0,0,0};//4.0�̻��� , 3.0�̻���, 2.0�̻���, �� ���� ����		
		// C��� ���� ����
		
		// C��� ���� ����
		// Java ���� ����
		// java ���� ����
		// python ���� ����
		// python ���� ����
		
	    GenderGraph(Vector v){
	    	//C���
	    	this.setBackground(Color.RED);
	    	//c.setBackground(Color.white);
	    	mMany=0;
			wMany=0;
	    	//this.c=c; 
	    	this.v=v;
	    	Student s;
	    	// v.ssize()�� �ٲ�� ��. 
	    	
	    	for(int i=0;i<v.size();i++) {
	    		
	    		s=(Student) v.get(i);
	    		
	    		if(s.gender.equals("��")) {
	    				wMany++;
	    				// �� ���л��� c�� ��� ������ ���ϴ���
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
	    				// �� ���л��� java�� ��� ������ ���ϴ���
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
	    				// �� ���л��� PYHTON�� ��� ������ ���ϴ���
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
	    			// �̳� �л��� c�� ��� ������ ���ϴ���
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
    				// �� ���л��� java�� ��� ������ ���ϴ���
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
    				// �� ���л��� PYHTON�� ��� ������ ���ϴ���
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
	    	
	       // c.add("������",this);
		    setSize(300, 300) ;
		    setLocation(150, 200) ;  
		    
		    
	    }

	    int per=30;// drawString�� �� Ʈ ũ��
	    public void paintComponent(Graphics g) {
	       super.paintComponents(g);
	       g.setColor(Color.white) ;
	       g.fillRect(0,0,PANEL_W,PANEL_H);
	       g.setColor(Color.black);
	      
	       
	       // c��� _________________________________________________________________________
	       g.setColor(Color.black);
	       g.drawString("[ c��� ]", XMARGIN/5*3, YMARGIN);
	       
	       // ��a����
	       g.drawString("(��)", XMARGIN*2-15, YMARGIN+XMARGIN*2+30);
	       g.setColor(Color.red);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,0,Ccount_m[0]*360/mMany);
	       int s=Ccount_m[0]*360/mMany;
	       
	    // ��b����
	       g.setColor(Color.yellow);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[1]*360/mMany);
	       s=s+Ccount_m[1]*360/mMany;
	    // ��c����
	       g.setColor(Color.gray);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[2]*360/mMany);
	       s=s+Ccount_m[2]*360/mMany;
	    // �� �׿ܺ���
	       g.setColor(Color.pink);
	       g.fillArc(XMARGIN,YMARGIN,XMARGIN*2,XMARGIN*2,s,Ccount_m[3]*360/mMany);
	       
	       // ��
	       g.setColor(Color.black);
	       g.drawString("(��)", XMARGIN*5+30, YMARGIN+XMARGIN*2+30);
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
	       
	       
	       // JAVA���____________________________________________________________________________
	       g.setColor(Color.black);
	       g.drawString("[ Java ]", XMARGIN/5*3, YMARGIN+per+XMARGIN*2+jgan);
	      
	       // ��
	       g.setColor(Color.black);
	       g.drawString("(��)", XMARGIN*2-15, YMARGIN+XMARGIN*5+15);
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
	       
	       // ��
	       g.setColor(Color.black);
	       g.drawString("(��)", XMARGIN*5+30, YMARGIN+XMARGIN*5+15);
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
	       // python���___________________________________________________________________________ 
	       g.setColor(Color.black);
	       g.drawString("[ Python ]", XMARGIN/5*3, YMARGIN+per*2+XMARGIN*4+jgan);
	       // ��
	       g.setColor(Color.black);
	       g.drawString("(��)", XMARGIN*2-15, YMARGIN+XMARGIN*8);
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
	       
	       // ��
	       g.setColor(Color.black);
	       g.drawString("(��)", XMARGIN*5+30, YMARGIN+XMARGIN*8);
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
	       
	       // �������� �� ���� ���� ������ ��Ÿ���� ��
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