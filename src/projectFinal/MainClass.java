package projectFinal;
//스크롤바 확인 예제
import java.awt.*;
import java.awt.event.*;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class MainClass extends JPanel {

  JLabel label = new JLabel();

  public MainClass() {
    super(true);
    
    setLayout(new BorderLayout());
    
    JTextArea ta=new JTextArea();
    for(int i=0;i<100;i++)
    	ta.append(i+"\n");
    //JScrollPanel js=new JScrollPane
    //c.add
    
    JScrollPane js=new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	add(js);
    
	
	//0-4 디폴트
    JScrollBar hbar = js.getVerticalScrollBar();
    //hbar.setBlockIncrement(1);//이렇게 하면?
    hbar.setUnitIncrement(2); //이건뭐야?
    /*
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 300);

    
    

    hbar.addAdjustmentListener(new MyAdjustmentListener());
    vbar.addAdjustmentListener(new MyAdjustmentListener());

    add(hbar, BorderLayout.SOUTH);
    add(vbar, BorderLayout.EAST);
    add(label, BorderLayout.CENTER);
    */
  }

  class MyAdjustmentListener implements AdjustmentListener {
    public void adjustmentValueChanged(AdjustmentEvent e) {
      label.setText("    New Value is " + e.getValue() + "      ");
      repaint();
    }
  }

  public static void main(String s[]) {
    JFrame frame = new JFrame("Scroll Bar Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new MainClass());
    frame.setSize(200, 200);
    frame.setVisible(true);
  }
}