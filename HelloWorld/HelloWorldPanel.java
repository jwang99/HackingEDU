import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloWorldPanel extends JPanel {
   
   private String currentMessage = "Hello World!"; // Currently displayed message.
   private MessageDisplay displayPanel;  // The panel where the message is displayed.
   
   private class MessageDisplay extends JPanel {   // Defines the display panel.
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawString(currentMessage, 20, 30);
      }
   }
   
   private class ButtonHandler implements ActionListener {  // The event listener.
      public void actionPerformed(ActionEvent e) {
         if (currentMessage.equals("Hello World!"))
            currentMessage = "Goodbye World!";
         else
            currentMessage = "Hello World!";
         displayPanel.repaint(); // Paint display panel with new message.
      }
   }
   
   /**
    * The constructor creates the components that will be contained inside this
    * panel, and then adds those components to this panel.
    */
   public HelloWorldPanel() {

      displayPanel = new MessageDisplay();  // Create the display subpanel.

      JButton changeMessageButton = new JButton("Change Message"); // The button.
      ButtonHandler listener = new ButtonHandler();
      changeMessageButton.addActionListener(listener);

      setLayout(new BorderLayout());  // Set the layout manager for this panel.
      add(displayPanel, BorderLayout.CENTER);  // Add the display panel.
      add(changeMessageButton, BorderLayout.SOUTH);  // Add the button.

   }
   
}