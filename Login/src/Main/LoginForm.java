package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm implements ActionListener{

    private static JFrame frame1;

    private static JLabel label1;
    private static JLabel label2;
    private static JLabel label3;
    private static JLabel label4;

    private static JButton button1;
    private static JButton button2;
    private static JButton button3;

    static TextField email;
    static TextField SignUpName;
    static JPasswordField SignUpPassword;

    public static void main(String[] args){
        Signup();
    }
    
    static void Signup(){
        frame1 = new JFrame("Signup");
        frame1.setSize(500, 300);
        frame1.setLocation(500, 225);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().setLayout(null);
        frame1.setVisible(true);

        label1 = new JLabel("Email");
            label1.setBounds(80, 25, 60, 30);

        email = new TextField();
            email.setBounds(160, 30, 170, 21);

        label2 = new JLabel("Username");
            label2.setBounds(70, 60, 80, 30);
            
        SignUpName = new TextField();
            SignUpName.setBounds(160, 65, 170, 21);

        label3 = new JLabel("Password");
            label3.setBounds(70, 92, 80, 30);

        SignUpPassword = new JPasswordField();
            SignUpPassword.setBounds(160, 100, 170, 21);

        button1 = new JButton("Show");
            button1.setBounds(340, 100, 70, 21);

        button2 = new JButton("Create!");
            button2.setBounds(200, 140, 80, 21);

        label4 = new JLabel("Have an account already?");
            label4.setBounds(100, 190, 160, 30);

        button3 = new JButton("Login");
            button3.setBounds(265, 195, 70, 21);

        frame1.add(label1);
        frame1.add(label2);
        frame1.add(label3);
        frame1.add(label4);
        frame1.add(email);
        frame1.add(SignUpName);
        frame1.add(SignUpPassword);
        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);

        button1.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                if(SignUpPassword.getEchoChar() != '\u0000'){
                    SignUpPassword.setEchoChar('\u0000');
                }
                
                else{
                    SignUpPassword.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        button2.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
            public void actionPerformed (ActionEvent e){
                Connection connection;
                ResultSet resultset;
                ResultSet resultset2;

                PreparedStatement psInsert;
                PreparedStatement psCheck;
                PreparedStatement psCheck2;

                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql", "root", "!!81eirek@$!!");

                    psCheck = connection.prepareStatement("SELECT * FROM schema.logindatabase WHERE email = ?");
                    psCheck.setString(1, email.getText());
                    resultset = psCheck.executeQuery();
                    
                    psCheck2 = connection.prepareStatement("SELECT * FROM schema.logindatabase WHERE username = ?");
                    psCheck2.setString(1, SignUpName.getText());
                    resultset2 = psCheck2.executeQuery();
                    
                    

                    if(!SignUpName.getText().isEmpty() && !SignUpPassword.getText().isEmpty() && !email.getText().isEmpty()){
                
                        if(!resultset.isBeforeFirst()){
                        
                            if(!resultset2.isBeforeFirst()){
                                psInsert = connection.prepareStatement("INSERT INTO schema.logindatabase (username, password, email) VALUES (?, ?, ?)");
                                psInsert.setString(1, SignUpName.getText());
                                psInsert.setString(2, SignUpPassword.getText());
                                psInsert.setString(3, email.getText());
                                psInsert.executeUpdate();

                                JOptionPane.showMessageDialog(null, "Account has been created!");
                                
                                frame1.dispose();
                                LoginForm2.UserLogin();
                            }

                            else{
                                JOptionPane.showMessageDialog(null, "Username is taken!");

                                SignUpName.setText("");
                                SignUpPassword.setText("");
                            }
                        }

                        else{
                            JOptionPane.showMessageDialog(null, "Email is taken!");
                                email.setText("");
                        }
                    }

                    else{
                        JOptionPane.showMessageDialog(null, "Fill out completly!");
                    }   
                }
              
                catch(SQLException x){
                    JOptionPane.showMessageDialog(null, x);
                    x.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                frame1.dispose();
                LoginForm2.UserLogin();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}