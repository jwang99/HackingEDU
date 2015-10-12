import javax.swing.JApplet;

public class HelloWorldApplet2 extends JApplet {
   public void init() {  
      HelloWorldPanel content = new HelloWorldPanel();
      setContentPane(content);
   }
}
