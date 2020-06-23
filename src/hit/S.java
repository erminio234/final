package hit;

import java.awt.Cursor; 
import java.awt.Image; 
import java.awt.Point; 
import java.awt.Toolkit; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.util.Random; 

import javax.swing.ImageIcon; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JMenu; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.Timer; 


public class S extends JFrame implements ActionListener,MouseListener{ 
boolean isOver=false;
private String dir="./pic/";
JLabel jlbMouse;
Timer timer;
Random random;
int delay=1100;
Toolkit tk; 
Image image; 
Cursor myCursor; 
JLabel showNum,currentGrade,hitNum; 
int showNumber=0,hitNumber=0,currentGrades=1; 

public S(){ 
super("打地鼠"); 
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
this.setSize(530, 365); 
this.setLocationRelativeTo(null);
setbackground();
this.getContentPane().setLayout(null);
tk = Toolkit.getDefaultToolkit(); 
image = tk.createImage(dir+"true.jpeg"); 
myCursor = tk.createCustomCursor(image, new Point(10,10), "xxx"); 
this.setCursor(myCursor); 

setMessage();
ImageIcon imageMouse = new ImageIcon(dir+"mouse.png"); 
jlbMouse = new JLabel(imageMouse); 
jlbMouse.setSize(80,80); 
this.getContentPane().add(jlbMouse); 
jlbMouse.setVisible(false); 
jlbMouse.addMouseListener(this);
timer = new Timer(delay,this); 
random = new Random(); 
timer.start(); 

addMenu();

this.setResizable(false);
this.setVisible(true); 
} 

private void addMenu() { 
JMenuBar menubar = new JMenuBar(); 
this.setJMenuBar(menubar); 
JMenu game = new JMenu("遊戲"); 
JMenuItem jitemNew = new JMenuItem("新遊戲"); 
jitemNew.setActionCommand("new"); 
jitemNew.addActionListener(this); 
JMenuItem jitemPause = new JMenuItem("暫停"); 
jitemPause.setActionCommand("pause"); 
jitemPause.addActionListener(this); 
JMenuItem jitemExit = new JMenuItem("退出"); 
jitemExit.setActionCommand("exit"); 
jitemExit.addActionListener(this); 
game.add(jitemNew); 
game.add(jitemPause); 
game.addSeparator();
game.add(jitemExit); 
menubar.add(game); 
} 

private void setbackground() { 
 

((JPanel)(this.getContentPane())).setOpaque(false); 
ImageIcon bgImage = new ImageIcon("pic/BG.png"); 
JLabel bgLabel = new JLabel(bgImage); 
bgLabel.setBounds(0, 25, bgImage.getIconWidth(), bgImage.getIconHeight()); 
this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); 

} 

private void setMessage() { 
JLabel lab1 = new JLabel();
lab1.setText("出現次數"); 
lab1.setBounds(8, 8, 92, 80); 
this.getContentPane().add(lab1); 
showNum = new JLabel("0"); 
showNum.setBounds(110, 8, 92, 80); 
this.getContentPane().add(showNum); 

JLabel lab2 = new JLabel();
lab2.setText("打中次數");
lab2.setBounds(148, 8, 92, 80); 
this.getContentPane().add(lab2); 
hitNum = new JLabel("0"); 
hitNum.setBounds(251, 8, 92, 80); 
this.getContentPane().add(hitNum); 

JLabel lab3 = new JLabel();
lab3.setText("分數");
lab3.setBounds(288, 8, 92, 80); 
this.getContentPane().add(lab3); 
currentGrade = new JLabel("1"); 
currentGrade.setBounds(391, 8, 92, 80); 
this.getContentPane().add(currentGrade); 
} 


public static void main(String[] args) { 
new S(); 
} 

public void actionPerformed(ActionEvent e) { 
if(e.getSource() instanceof JMenuItem){ 
menuItemFun(e); 
} 

int ran=random.nextInt(9);
ImageIcon imageMouse = new ImageIcon(dir+"mouse.png"); 
jlbMouse.setIcon(imageMouse); 
switch(ran){ 
case 0:jlbMouse.setLocation(65, 80);break; 
case 1:jlbMouse.setLocation(395, 230);break; 
case 2:jlbMouse.setLocation(230, 230);break; 
case 3:jlbMouse.setLocation(65, 230);break; 
case 4:jlbMouse.setLocation(395, 150);break; 
case 5:jlbMouse.setLocation(230, 150);break; 
case 6:jlbMouse.setLocation(65, 150);break; 
case 7:jlbMouse.setLocation(395, 80);break; 
case 8:jlbMouse.setLocation(230, 80);break; 
} 

jlbMouse.setVisible(true); 

showNumber++; 
showNum.setText(""+showNumber); 

if( !gamePlan() ){
timer.stop(); 
} 

} 

private void menuItemFun(ActionEvent e) { 
if (e.getActionCommand().equalsIgnoreCase("new")) {
timer.stop(); 
showNumber=0; 
hitNumber=0; 
currentGrades=1; 
delay=1000; 
isOver=false; 
showNum.setText(""+showNumber); 
hitNum.setText(""+hitNumber); 
currentGrade.setText(""+currentGrades); 
timer = new Timer(delay,this); 
timer.start(); 
} 
if (e.getActionCommand().equalsIgnoreCase("exit")) {
System.exit(EXIT_ON_CLOSE); 
} 

if (e.getActionCommand().equalsIgnoreCase("pause")) {
timer.stop(); 
JOptionPane.showMessageDialog(this, "繼續請按“確定”"); 
timer.start(); 
} 
} 

private boolean gamePlan() { 
if(showNumber-hitNumber > 8){ 
JOptionPane.showMessageDialog(this, "Game Over !"); 
isOver=true; 
return false; 
} 
if(hitNumber > 5){ 
hitNumber=0; 
showNumber=0; 
currentGrades++; 
if(delay>100){ 
delay-=50; 
}else if(delay>=500){ 
delay=500; 
} 
timer.setDelay(delay); 
hitNum.setText(""+hitNumber); 
showNum.setText(""+showNumber); 
currentGrade.setText(""+currentGrades); 
} 
return true; 
} 

public void mouseClicked(MouseEvent e) { 

} 

public void mousePressed(MouseEvent e) { 
if(isOver){ 
return ; 
} 
image = tk.createImage(dir+"true.jpeg"); 
myCursor = tk.createCustomCursor(image, new Point(10,10), "xxx"); 
this.setCursor(myCursor);
if(e.getSource()==jlbMouse){ 
ImageIcon imageIconHit = new ImageIcon(dir+"hit.png"); 
jlbMouse.setIcon(imageIconHit); 
jlbMouse.setVisible(true); 
} 

hitNumber++; 
hitNum.setText(""+hitNumber); 
} 

public void mouseReleased(MouseEvent e) { 
if(isOver){ 
return ; 
} 
image = tk.createImage(dir+"true.jpeg"); 
myCursor = tk.createCustomCursor(image, new Point(10,10), "xxx"); 
this.setCursor(myCursor); 

}
public void mouseEntered(MouseEvent e) { 

} 

public void mouseExited(MouseEvent e) { 

} 


} 

