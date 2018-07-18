
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GUIMakerClass{
        JFrame myFrame = new JFrame("Helmet Application");

        JLabel label = new JLabel();
       // label.setBounds(10,10,670,250);
        //creating buttons
        JButton connectbutton = new JButton();
        JButton acceptbutton = new JButton();
        JButton rejectbutton = new JButton();
        JPanel imagepanel = new JPanel();
        
        JPanel gridlabelpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        JButton btn1 = new JButton("First");
        JButton btn2 = new JButton("Second");
        JButton btn3 = new JButton("Third");
        JButton btn4 = new JButton("Fourth");
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Camera Number");
        JLabel lbl2 = new JLabel("New alerts");

        JLabel addImage = new JLabel();
         
        
        //panel1.setBorder(new TitledBorder("Panel1 bordr"));



        public void creatGUI(){
        connectbutton.setText("connect");   
        acceptbutton.setText("Accept");
        rejectbutton.setText("Reject");




        GUIListener myListener = new GUIListener();
        connectbutton.addActionListener(myListener);
        acceptbutton.addActionListener(myListener);
        rejectbutton.addActionListener(myListener);
         // added code
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl2.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        panel.add(lbl);
        panel.add(lbl2);
       
        gridlabelpanel.add(btn1);
        gridlabelpanel.add(btn2);
        gridlabelpanel.add(btn3);
        //gridlabelpanel.add(btn4);

        gridlabelpanel.setLayout(new GridBagLayout());


        imagepanel.add(connectbutton);
        imagepanel.add(label);
        imagepanel.setSize(800,500);
        

        buttonpanel.add(acceptbutton);
        buttonpanel.add(rejectbutton);
       

        myFrame.add(imagepanel);
        myFrame.add(buttonpanel);
        myFrame.add(panel);
        myFrame.add(gridlabelpanel);

        String[] choices = { "Camera 1", "Camera 2", "Camera 3", "Camera 4",
                         "CHOICE 5", "CHOICE 6" };

        final JComboBox<String> cb = new JComboBox<String>(choices);

        cb.setMaximumSize(cb.getPreferredSize()); // added code
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        panel.add(cb);


         String[] choices2 = { "1", "2", "3", "4",
                         "5", "6" };

        final JComboBox<String> cb2 = new JComboBox<String>(choices2);

        cb2.setMaximumSize(cb2.getPreferredSize()); // added code
        cb2.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        //cb.setVisible(true); // Not needed
        panel.add(cb2);


         
                    
                  
                
                

        //setting up the main frame
        myFrame.setSize(800,500); //width , height in pixels
        myFrame.setLocationRelativeTo(null); //if set to null, centers in the middle of the screen
        //adding layouts
        myFrame.setLayout(new GridLayout(1,2));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //states what happens on closing the frame
        myFrame.setVisible(true);  
        }

        public class GUIListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                if(e.getSource()==connectbutton){
                    JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              label.setIcon(ResizeImage(path));
          }
                }
                
                else if(e.getSource()==acceptbutton){
                    System.out.println("Accepted!");
                }
                else if(e.getSource()==rejectbutton){
                    System.out.println("Rejected!");
                }
            }
        } 
        public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }     
}
