import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Helmetproject2  extends JFrame implements ActionListener{

private JPanel imagePanel;
private JPanel labelPanel;
public Helmetproject2 (){
    super();
    Container contentPane = getContentPane();
    setSize(2000,2000);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setTitle("Button Demo"); //Theme here
    contentPane.setBackground(Color.white);
    contentPane.setLayout(new BorderLayout());

    createButtons(contentPane);
    instruction(contentPane);
    createImageLabel(contentPane);
}

public void instruction(Container contentPane){
    JPanel instruction = new JPanel();
    JPanel panel = new JPanel();

    instruction.setLayout(new FlowLayout());
    instruction.setBackground(Color.white);
    Font fontType1 = new Font("Comic Sans MS", Font.BOLD, 40);
    JLabel instruction1 = new JLabel("Click on the button to view a"
            + " photo.");
    instruction1.setForeground(Color.black);
    instruction1.setFont(fontType1);
    instruction.add(instruction1);
    //contentPane.add(instruction, BorderLayout.SOUTH);
}

public void createButtons(Container contentPane){
    labelPanel = new JPanel();
    labelPanel.setBackground(Color.cyan);
    labelPanel.setLayout(new GridLayout(9,1));

    String[] imageLabel = new String[2];
    imageLabel[0] = "Image 1";
    imageLabel[1] = "Exit";

    Color[] color = new Color[2];
    color[0] = Color.cyan;
    color[1] = new Color(242, 121, 234);

    Font fontType = new Font("Times New Roman", Font.BOLD, 30);


    JButton[] button = new JButton[2];
    for (int i=0; i<button.length; i++)
    {
        button[i] = new JButton(imageLabel[i]);
        button[i].addActionListener(this);
        button[i].setBackground(color[0]);
        button[i].setFont(fontType);
        labelPanel.add(button[i]);
    }

    contentPane.add(labelPanel, BorderLayout.EAST);
}

public void createImageLabel(Container contentPane){
    imagePanel = new JPanel();
    imagePanel.setBackground(Color.white);
    contentPane.add(imagePanel, BorderLayout.CENTER);
}

public void actionPerformed(ActionEvent event){
    String actionCommand = event.getActionCommand();
    if(actionCommand.equals("Image 1")) {
    JLabel addImage = new JLabel();
    //URL url = getClass().getResource('https://goo.gl/images/JEyD46');
    //url = "aad.png";
    if (true) {
        ImageIcon image = new ImageIcon("123.jpg");
        addImage.setIcon(image);
        imagePanel.add(addImage);
        imagePanel.setBackground(Color.white);
        this.revalidate();
    }
}

    else if(actionCommand.equals("Exit"))
        System.exit(0);
    else System.out.println("Error in button interface.");

}

public static void main(String[] args)
{
    Helmetproject2  buttonGui= new Helmetproject2 ();
    buttonGui.setVisible(true);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code

    frame.add(panel);

    JLabel lbl = new JLabel("Select one of the possible choices and click OK");
    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
    //lbl.setVisible(true); // Not needed

    panel.add(lbl);

    String[] choices = { "CHOICE 1", "CHOICE 2", "CHOICE 3", "CHOICE 4",
                         "CHOICE 5", "CHOICE 6" };

    final JComboBox<String> cb = new JComboBox<String>(choices);

    cb.setMaximumSize(cb.getPreferredSize()); // added code
    cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
    //cb.setVisible(true); // Not needed
    panel.add(cb);

    JButton btn = new JButton("OK");
    btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
    panel.add(btn);

    frame.setVisible(true); // added code
}


}