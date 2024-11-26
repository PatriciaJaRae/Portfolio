package Main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm2 extends LoginForm{

        static JFrame frame2;

        private static JLabel label5;
        private static JLabel label6;
        private static JLabel label7;
        
        static TextField name;
        static JPasswordField password;
        
        private static JButton button3;
        private static JButton button4;
        private static JButton button5;
        private static JButton button6;

        static String p;
        static String n;
    
    static void UserLogin(){
        frame2 = new JFrame("Login");
        frame2.setSize(500, 300);
        frame2.setLocation(500, 225);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setLayout(null);
        frame2.setVisible(true);
        
        label5 = new JLabel("Username");
            label5.setBounds(70, 52, 80, 30);

        name = new TextField();
            name.setBounds(160, 60, 170, 21);

        label6 = new JLabel("Password");
            label6.setBounds(70, 92, 80, 30);

        password = new JPasswordField();
            password.setBounds(160, 100, 170, 23);

        button3 = new JButton("Show");
            button3.setBounds(340, 100, 70, 21);

        button4 = new JButton("Login");
            button4.setBounds(250, 140, 80, 21);

        button5 = new JButton("Forgot?");
            button5.setBounds(160, 140, 80, 21);

        label7 = new JLabel("Don't have an account?");
            label7.setBounds(120, 190, 160, 30);

        button6 = new JButton("Signup");
            button6.setBounds(265, 195, 85, 21);
        
        frame2.add(label5);
        frame2.add(label6);
        frame2.add(label7);
        frame2.add(name);
        frame2.add(password);
        frame2.add(button3);
        frame2.add(button4);
        frame2.add(button5);
        frame2.add(button6);

        button3.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                if(password.getEchoChar() != '\u0000'){
                    password.setEchoChar('\u0000');
                }
                
                else{
                    password.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        button4.addActionListener(new ActionListener(){
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e){
                Connection connection;
                ResultSet resultset;

                PreparedStatement preparedstatement;

                String passRetrieved;

                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql", "root", "!!81eirek@$!!");

                    preparedstatement = connection.prepareStatement("SELECT password FROM schema.logindatabase WHERE username = ?");
                    preparedstatement.setString(1, name.getText());

                    resultset = preparedstatement.executeQuery();
                    
                    if(!name.getText().isEmpty() && !password.getText().isEmpty()){
                        
                        if(!resultset.isBeforeFirst()){
                            JOptionPane.showMessageDialog(null, "Unknown username");
                        }

                        else{

                            while(resultset.next()){
                                passRetrieved = resultset.getString("password");

                                if(passRetrieved.equals(password.getText())){
                                    JOptionPane.showMessageDialog(null, "Login Sucsessful!");
                                    
                                    frame2.dispose();
                                    LoginForm3.WelcomePage();
                                }

                                else{
                                    JOptionPane.showMessageDialog(null, "Incorrect password");
                                    password.setText("");
                                }
                            }
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

        button5.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                frame2.dispose();
                LoginForm3.ForgotPassword();
            }
        });

        button6.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                frame2.dispose();
                Signup();
            }
        });
    }
}