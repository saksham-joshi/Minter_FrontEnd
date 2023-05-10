import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.io.File;
import java.util.Vector;

class Main extends Thread {
    JFrame fr;
    JFrame profile_frame;
    JInternalFrame contact_list;
    JTextArea chatbox;

    JTextArea msg_area;
    JTextArea tar;
    JLabel contact_bg;
    JMenu chatname;

    JButton send;
    JTextArea msgbox;
    JScrollPane textarea;

    JMenuBar menuBar;
    JMenu menu,about;
    JMenuItem bg_switch,about_dev;
    JMenu themes;
    JMenuItem dark_theme,light_theme;
    JMenuItem profile,add_new_contact;

    Font f1 = new Font("Acknowledgement",Font.PLAIN,15);
    Font f2 = new Font("Berlin Sans FB",Font.PLAIN,16);
    Font f3 = new Font("Impact",Font.PLAIN,45);
    Font f4 = new Font("Times New Roman",Font.BOLD,15);
    Font f5 = new Font("Century Gothic", Font.BOLD ,18);


    Color default_bg_color = new Color(0,255,255);
    Color violet = new Color(77,0,149);
    Color dark_green = new Color(0,50,0);
    Color msg_color = new Color(0,83,134,255);

    Dimension screen_dim;

    int temp=1;

    String Dev_message = "| Developed by : SAKSHAM JOSHI .\n| Twitter : @sakshamjoshi27 .\n| GitHub : saksham-joshi .\n| Linkedin : sakshamjoshi27 .\n| IDE used : VS Code .\n| Developed in : JAVA SWING.";
    static String user_name="";
    String profile_path="Images\\default_profile.png";

    Vector<String> contact_list_set = new Vector<>();

    int frame_width,frame_hig;

    Main(){

        // FRAME SETUP
        ImageIcon icon = new ImageIcon("Images\\chat_logo.png");
        screen_dim = Toolkit.getDefaultToolkit().getScreenSize();
        fr = new JFrame(" MINTER ");
        fr.setIconImage(icon.getImage());
        frame_width = (int)screen_dim.getWidth() - (int)screen_dim.getWidth()/4;
        frame_hig = (int)screen_dim.getHeight()-(int)screen_dim.getHeight()/10 ;
        fr.setSize(frame_width,frame_hig);
        fr.getContentPane().setBackground(default_bg_color);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setLayout(null);
        fr.setVisible(true);


        JLabel j1 = new JLabel(new ImageIcon("Images\\camsl_line.png"));
        j1.setBounds(frame_width/3, 0, 10, frame_hig);
        fr.add(j1);

        menu_bar_setup();

        message_side_setup();

    }

    private void new_contact_add(String cname){
        if (contact_list_set.contains(cname)){
            JOptionPane.showMessageDialog(null, "This Contact already exist","Already Exist",JOptionPane.CANCEL_OPTION);
            return;
        }
        contact_list_set.add(cname);
        Dimension d = new Dimension(frame_width/3-30, 50);
        JButton jb = new JButton(cname);
        jb.setBackground(new Color(0,128,128));
        jb.setForeground(Color.WHITE);
        jb.setBorder(new LineBorder(Color.WHITE ));
        jb.setSize(d);
        jb.setFont(f2);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //msg_side.setTitle(cname);
                // other works will be defined here !
                chatname.setText(cname);
                chatbox.setText("");
                msgbox.setText("");
            }
        });
        contact_list.add(jb);
        Color c=  contact_list.getBackground();
        contact_list.updateUI();
        contact_list.setBorder(null);
        contact_list.setBackground(c);
    }

    private void contact_frame_set(){
        contact_list = new JInternalFrame();      // for contact list side
        contact_list.setBackground(Color.ORANGE);
        contact_list.setBounds(0,25, (fr.getWidth()/3),fr.getHeight()-25);
        contact_list.setAutoscrolls(true);

        GridLayout g1 = new GridLayout(contact_list.getHeight()/50 , 1);
        g1.setVgap(10);

        contact_list.setBorder(null);
        contact_list.setLayout(g1);
        contact_list.setClosable(false);
        contact_list.setResizable(false);
        contact_list.setTitle("CHATS & CONTACTS"); 
        contact_list.setVisible(true);
        fr.add(contact_list);
    }

    private void message_side_setup(){

        JMenuBar chatbar = new JMenuBar();
        chatbar.setBounds((frame_width/3)+10, 0, frame_width-(frame_width/3) +10, 25);
        chatbar.setBorder(null);
        chatbar.setBackground(violet);
        chatbar.setForeground(Color.WHITE);
        fr.add(chatbar);

        chatname = new JMenu("");
        chatname.setBorder(null);
        chatname.setFont(f1);
        chatname.setForeground(Color.WHITE);
        chatbar.add(chatname);

        JMenuItem delete_chat = new JMenuItem("Delete Chat");
        delete_chat.setFont(f4);
        delete_chat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chatbox.setText("");
                msgbox.setText("");
            }
        });

        JMenuItem delete_contact = new JMenuItem("Delete Contact");
        delete_contact.setFont(f4);
        delete_contact.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                chatbox.setText("");
                msgbox.setText("");  
            }
        });
        chatname.add(delete_chat);
        chatname.add(delete_contact);

        chatbox = new JTextArea();
        chatbox.setBounds((frame_width/3)+10,25,frame_width-(frame_width/3) +10,frame_hig-130);
        chatbox.setFont(f5);
        chatbox.setBorder(null);
        chatbox.setBackground(default_bg_color);
        chatbox.setForeground(msg_color);
        chatbox.setEditable(false);
        chatbox.setVisible(true);
        chatbox.setText("");
        JScrollPane chat_scroll = new JScrollPane(chatbox);
        chat_scroll.setAutoscrolls(true);
        chat_scroll.setBorder(null);
        chat_scroll.setBounds(chatbox.getBounds());
        fr.add(chat_scroll);
    
        msgbox = new JTextArea();
        msgbox.setBounds((frame_width/3)+20, frame_hig-95 , chatbox.getWidth()-115 ,50);
        msgbox.setBackground(Color.WHITE);
        msgbox.setBorder(null);
        //msgbox.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED) , new BevelBorder(BevelBorder.LOWERED)));
        msgbox.setRows(2);
        msgbox.setFont(f2);
        textarea = new JScrollPane(msgbox);
        textarea.setAutoscrolls(true);
        textarea.setBounds(msgbox.getBounds());
        fr.add(textarea);

        send = new JButton(">");
        send.setBounds(msgbox.getWidth()+ msgbox.getX()+10,msgbox.getY(),50,50);
        send.setBackground(Color.GREEN);
        send.setForeground(Color.WHITE);
        send.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED) , new BevelBorder(BevelBorder.LOWERED)));
        send.setVisible(true);
        send.setFont(f3);
        send.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(msgbox.getText().strip().length() != 0 && chatname.getText().length() != 0 ){
                    chatbox.append(" |> You : "+msgbox.getText().strip()+"\n-------------------------------------------------------------\n");
                    msgbox.setText("");
                }
                else{
                    // send message code
                }
                
            }
        });
        fr.add(send);

    }

    private void menu_bar_setup(){
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0,(frame_width/3)+10,25);
        menuBar.setBackground(violet);
        menuBar.setBorder(null);
        fr.add(menuBar);

        contact_frame_set();

        menu = new JMenu("MENU");
        menu.setFont(f1);
        menu.setForeground(Color.WHITE);
        menuBar.add(menu);

        themes = new JMenu("THEMES");
        themes.setFont(f2);
        themes.setBackground(Color.WHITE);
        menu.add(themes);

        dark_theme = new JMenuItem("Dark");  
        dark_theme.setFont(f4);
        dark_theme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fr.getContentPane().setBackground(new Color(54,46,61));
                contact_list.setBackground(new Color(2,54,90));
                msgbox.setBackground(Color.LIGHT_GRAY);
                send.setBackground(Color.BLACK);

                chatbox.setBackground(new Color(54,46,61));
                chatbox.setForeground(Color.WHITE);
            }
        });
        themes.add(dark_theme);

        light_theme = new JMenuItem("Light");
        light_theme.setBackground(Color.WHITE);
        light_theme.setFont(f4);
        light_theme.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fr.getContentPane().setBackground(default_bg_color);
                contact_list.setBackground(Color.ORANGE);
                chatbox.setBackground(default_bg_color);
                chatbox.setForeground(Color.BLACK);
                msgbox.setBackground(Color.WHITE);
                send.setBackground(Color.GREEN);
            }
        });
        themes.add(light_theme);

        about = new JMenu("ABOUT");
        about.setForeground(Color.WHITE);
        about.setFont(f1);
        menuBar.add(about);

        about_dev = new JMenuItem("Developer");
        about_dev.setFont(f2);
        about_dev.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(fr,Dev_message,"ABOUT DEVELOPER",JOptionPane.PLAIN_MESSAGE);
            }
        });
        about.add(about_dev);

        add_new_contact = new JMenuItem("Add New Contact");
        add_new_contact.setFont(f2);
        add_new_contact.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String str = JOptionPane.showInputDialog(null, "Enter new Contact Name","Add New Contact",JOptionPane.QUESTION_MESSAGE);
                if(str.strip().length() == 0 ){
                    JOptionPane.showMessageDialog(null, "PLEASE ENTER SOME NAME !");
                }
                else{
                    new_contact_add(str);
                }
            }
        });
        menu.add(add_new_contact);

        profile = new JMenuItem("Profile");
        profile.setFont(f2);
        profile.setBackground(Color.WHITE);
        profile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                profile_setup();      
            }
        });
        menu.add(profile);    
    }

    private void profile_setup(){

        profile_frame = new JFrame();
        profile_frame.getContentPane().setBackground(fr.getBackground());
        profile_frame.setIconImage(fr.getIconImage());
        profile_frame.setSize(640,360);
        profile_frame.setLocation((int)screen_dim.getWidth()/2 - 320 ,(int)screen_dim.getHeight()/2 - 180);
        profile_frame.setResizable(false);
        profile_frame.setTitle("Your Profile");
        profile_frame.getContentPane().setBackground(Color.ORANGE);
        profile_frame.setLayout(null);
        profile_frame.setVisible(true);

        JTextField pname = new JTextField(20);
        pname.setColumns(20);
        pname.setBounds(195,265,250,50);
        pname.setEditable(false);
        pname.setFont(f2);
        pname.setText(user_name);
        pname.setBackground(Color.LIGHT_GRAY );
        pname.setHorizontalAlignment(JTextField.CENTER);
        profile_frame.add(pname);

        JButton change_name = new JButton("ChangeName");
        change_name.setBounds(75,265,100,50);
        change_name.setBorder(new BevelBorder(BevelBorder.RAISED));
        change_name.setBackground(violet);
        change_name.setForeground(Color.WHITE);
        change_name.setFont(f2);
        change_name.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(temp==1){
                    pname.setEditable(true);
                    change_name.setText("SAVE");
                    change_name.setBackground(Color.GREEN);
                    temp=2;
                }
                else{
                    if(pname.getText().length() > 20 || pname.getText().length()<3){
                        JOptionPane.showMessageDialog(null, "YOUR PROFILE NAME MUST HAVE LESS THAN 20 AND MORE THAN 3 WORDS !","ENter a Good Name",JOptionPane.PLAIN_MESSAGE);
                        pname.setText(System.getProperty("user.name"));
                        user_name=pname.getText();
                    }
                    else{
                        pname.setText(pname.getText());
                        user_name=pname.getText();
                    }
                    change_name.setText("ChangeName");
                    change_name.setBackground(violet);
                    
                    pname.setEditable(false); 
                    temp=1;
                }   
            }
        });
        profile_frame.add(change_name);


        JLabel dp = new JLabel(ResizeImage(profile_path,250,250));
        dp.setBounds(195 ,10,250,250);
        profile_frame.add(dp);

        JButton change_pic = new JButton("Change DP");
        change_pic.setBounds(profile_frame.getWidth()-120, 10, 100, 50);
        change_pic.setBorder(new BevelBorder(BevelBorder.RAISED));
        change_pic.setBackground(violet);
        change_pic.setForeground(Color.WHITE);
        change_pic.setFont(f2);
        change_pic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser j1 = new JFileChooser();
                j1.setDialogTitle("Choose Your Profile Picture");
                //j1.setVisible(true);
                j1.addChoosableFileFilter(new FileNameExtensionFilter("*.Images", "png","jpeg","gif","jpg"));
                
                int result = j1.showOpenDialog(null);

                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedImage = j1.getSelectedFile();
                    if(selectedImage.getName().endsWith(".png") || selectedImage.getName().endsWith(".jpg")){
                        String path = selectedImage.getAbsolutePath();
                        profile_path=path;
                        dp.setIcon(ResizeImage(path,250,250));
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"PLEASE SELECT PNG or JPG File ONLY !!","WRONG FILE SELECTED",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        profile_frame.add(change_pic);

        JButton remove_dp = new JButton("Remove DP");
        remove_dp.setBounds(profile_frame.getWidth()-120, 70, 100, 50);
        remove_dp.setBorder(new BevelBorder(BevelBorder.RAISED));
        remove_dp.setBackground(violet);
        remove_dp.setForeground(Color.WHITE);
        remove_dp.setFont(f2);
        remove_dp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dp.setIcon(ResizeImage("Images\\default_profile.png",250,250));
            }
        });
        profile_frame.add(remove_dp);    
    }

    // method to resize the image according to the given width and height
    private ImageIcon ResizeImage(String ImagePath,int width,int height)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        img = img.getScaledInstance(width,height, Image.SCALE_DEFAULT);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        splash_screen s1 = new splash_screen(d, new ImageIcon("Images\\Intro_pic.png"));
        s1.iterate();
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Main();
            }
        });
    }
}

class splash_screen {
    JWindow win;

    splash_screen(Dimension screen_dim,ImageIcon logo){
        try{
            win = new JWindow();
            win.setVisible(true);
            win.setSize(480, 270);
            win.setLocation((int)(screen_dim.getWidth()/2) - win.getWidth()/2,(int)(screen_dim.getHeight()/2) -win.getHeight()/2);
            win.setLayout(new FlowLayout());
            win.setVisible(false);
            JLabel l1 = new JLabel(logo );
            l1.setLocation(0, 0);
            win.add(l1);
        }
        catch(Exception e){

        } 
    }

    public void iterate(){
        int i=0;
        while(i<10){
            try{
                win.setVisible(true);
                Thread.sleep(100);
                i++;
            }
            catch(Exception e){
            }
        }
        win.setVisible(false);
        win=null;
    }
}

