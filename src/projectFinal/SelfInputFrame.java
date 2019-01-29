package projectFinal;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SelfInputFrame extends JFrame{

	/*
	 * String name;
	String st_num;
    String gender;
    String score_java;
    String score_python;
    String score_c;
	 */
	String[] labelNames= {"이름","학번","성별(여/남)","자바점수","파이썬점수","씨점수"};
	JLabel[] labels=new JLabel[6];
	JTextField[] fields=new JTextField[6];
	JButton okButton=new JButton("확인");
	
	Student inputStudent;
	boolean inputFlag=false;
	
	public SelfInputFrame(ListBody body) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c=getContentPane();
		c.setLayout(new GridLayout(7,2));
		setLocation(200,0);
		
		for(int i=0;i<labels.length;i++) {
			labels[i]=new JLabel(labelNames[i]);
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			fields[i]=new JTextField(10);
			c.add(labels[i]);
			c.add(fields[i]);
		}
		c.add(new JLabel(""));
		c.add(okButton);
		
		pack();
		setVisible(true);
		
		//ok 버튼을 누르면 이벤트 발생
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//아닌데 유효성검사해야지.
				//이름은 괜찮. 학번,점수들 숫자인지 검사하고, 성별이 여,남 중 하나인지 검사하고
				int[] flags=new int[5];
				
				//"이름","학번","성별","자바점수","파이썬점수","씨점수"
				
				//성별
				String gender=fields[2].getText();
				if(gender.equals("여")||gender.equals("남")) {
					flags[0]=1;
				}else {
					//오류 알림창+삽입할 라벨에 붉은 표시..? 다시 클릭하면 하얀색으로 변하기
					//그리고 Student 객체가 생성되지 못하게 처리좀 해봐
					fields[2].setText("");
					JOptionPane.showMessageDialog(null,"여/남을 입력하세요");
				}
				
				//문자가 숫자로 못변하는 예외처리가 있을텐데..NumberFormatException!!
				
				//학번
				
				try{
					int suNum=Integer.parseInt(fields[1].getText());
					flags[1]=1;
				}catch(NumberFormatException error) {
					//알림창. 학번 잘못입력했다.
					fields[1].setText("");
					JOptionPane.showMessageDialog(null,"학번은 숫자입니다");
				}
				
				//자바
				try{
					int java=Integer.parseInt(fields[3].getText());
					flags[2]=1;
				}catch(NumberFormatException error) {
					//알림창. 자바 잘못입력했다.
					fields[3].setText("");
					JOptionPane.showMessageDialog(null,"점수는 숫자입니다");
				}
				
				//파이썬
				try{
					int python=Integer.parseInt(fields[4].getText());
					flags[3]=1;
				}catch(NumberFormatException error) {
					//알림창. 파이썬 잘못입력했다.
					fields[4].setText("");
					JOptionPane.showMessageDialog(null,"점수는 숫자입니다");
				}
				
				//씨
				try{
					int c=Integer.parseInt(fields[5].getText());
					flags[4]=1;
				}catch(NumberFormatException error) {
					//알림창. 씨 잘못입력했다.
					fields[5].setText("");
					JOptionPane.showMessageDialog(null,"점수는 숫자입니다");
				}
				
				//위의 처리를 무사히 마치면 그때 Student 객체 생성한다.
				boolean result=true;
				for(int i=0;i<flags.length;i++) {
					if(flags[i]!=1) result=false;
				}
				
				if(result==true) {
					//프레임의 라벨에서 가져온 정보로 학생 객체 하나 만든다.
					inputStudent=new Student(fields[0].getText(),fields[1].getText(),fields[2].getText(),
							fields[3].getText(),fields[4].getText(),fields[5].getText());
					
					body.appendStudent(inputStudent);//body에 보내기
					
					//setVisible(false);
					dispose();
				}

				
			}
		});
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelfInputFrame();
	}
*/
}
