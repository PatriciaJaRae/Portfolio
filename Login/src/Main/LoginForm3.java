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

public class LoginForm3 extends LoginForm2{

    static JFrame frame3;
    static JFrame frame4;
    static JFrame frame5;

    private static JLabel label8;
    private static JLabel label9;
    private static JLabel label10;
    private static JLabel label11;
    private static JLabel label12;
    private static JLabel label13;
    
    private static JButton forgotpassword;

    static TextField enterEmail;
    static TextField enterName;
    static JPasswordField newPassword;
    static JPasswordField newPassword2;

    static JButton buttonSignout;
    static JButton buttonShow;
    static JButton buttonReset;
    static JButton backtoLogin;

    static void WelcomePage(){
        frame3 = new JFrame("Login");
        frame3.setSize(800, 500);
        frame3.setLocation(400, 125);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.getContentPane().setLayout(null);
        frame3.setVisible(true);

        label8 = new JLabel("Welcome!");
            label8.setBounds(340, 100, 200, 100);
            label8.setFont(new Font("Arial", Font.PLAIN, 25));
            label8.setHorizontalTextPosition(JLabel.CENTER);

        label9 = new JLabel("Hello, " + name.getText());
            label9.setBounds(15, 5, 200, 25);
            label9.setFont(new Font("Serif", Font.BOLD, 15));
            label9.setVerticalTextPosition(JLabel.CENTER);

        buttonSignout = new JButton("Signout");
            buttonSignout.setBounds(15, 425, 80, 30);        

        frame3.add(label8);
        frame3.add(label9);
        frame3.add(buttonSignout);

        buttonSignout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame3.dispose();
                LoginForm2.UserLogin();
            }
        });
    }

    static void ForgotPassword(){
        frame4 = new JFrame("Forgot Password");
        frame4.setSize(500, 300);
        frame4.setLocation(500, 225);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.getContentPane().setLayout(null);
        frame4.setVisible(true);

        label10 = new JLabel("Email");
            label10.setBounds(70, 60, 80, 30);
            
        enterEmail = new TextField();
            enterEmail.setBounds(160, 65, 170, 21);

        label11 = new JLabel("Username");
            label11.setBounds(70, 92, 80, 30);

        enterName = new TextField();
            enterName.setBounds(160, 100, 170, 21);

        forgotpassword = new JButton("Forgot Password");
            forgotpassword.setBounds(170, 140, 145, 21);

        backtoLogin = new JButton("Go back");
            backtoLogin.setBounds(30, 215, 80, 21);

        frame4.add(enterEmail);
        frame4.add(enterName);
        frame4.add(label10);
        frame4.add(label11);
        frame4.add(forgotpassword);
        frame4.add(backtoLogin);

        forgotpassword.addActionListener(new ActionListener(){
            @SuppressWarnings({ "deprecation", "unlikely-arg-type" })
            public void actionPerformed(ActionEvent e){
                Connection connection;
                ResultSet resultset;
                ResultSet resultset2;

                PreparedStatement checkPS;
                PreparedStatement checkPS2;

                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql", "root", "!!81eirek@$!!");

                    checkPS = connection.prepareStatement("SELECT * FROM schema.logindatabase WHERE email = ?");
                    checkPS.setString(1, enterEmail.getText());
                    resultset = checkPS.executeQuery();

                    checkPS2 = connection.prepareStatement("SELECT * FROM schema.logindatabase WHERE username = ?");
                    checkPS2.setString(1, enterName.getText());
                    resultset2 = checkPS2.executeQuery();

                    if(!enterEmail.getText().isEmpty() && !enterName.getText().isEmpty()){
                
                        if(!resultset.isBeforeFirst() && !resultset2.isBeforeFirst()){
                            JOptionPane.showMessageDialog(null, "Account not found.");
                        }

                        else{
                            JOptionPane.showMessageDialog(null, "Account found!");
                            frame4.dispose();
                            resetPassword();
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

        backtoLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame4.dispose();
                UserLogin();
            }
        });
    }

    static void resetPassword(){
        frame5 = new JFrame("Forgot Password");
        frame5.setSize(500, 300);
        frame5.setLocation(500, 225);
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame5.getContentPane().setLayout(null);
        frame5.setVisible(true);
        
        label12 = new JLabel("New Password");
            label12.setBounds(50, 60, 90, 30);
            
        newPassword = new JPasswordField();
            newPassword.setBounds(200, 65, 170, 21);

        label13 = new JLabel("Confirm New Password");
            label13.setBounds(50, 92, 150, 30);

        newPassword2 = new JPasswordField();
            newPassword2.setBounds(200, 100, 170, 21);

        buttonShow = new JButton("Show");
            buttonShow.setBounds(380, 65, 70, 21);

        buttonReset = new JButton("Confirm");
            buttonReset.setBounds(245, 140, 80, 21);

        frame5.add(label12);
        frame5.add(label13);
        frame5.add(newPassword);
        frame5.add(newPassword2);
        frame5.add(buttonShow);
        frame5.add(buttonReset);

        buttonShow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(newPassword.getEchoChar() != '\u0000'){
                    newPassword.setEchoChar('\u0000');
                }
                
                else{
                    newPassword.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        buttonReset.addActionListener(new ActionListener(){
            @SuppressWarnings({ "deprecation", "unlikely-arg-type" })
            public void actionPerformed(ActionEvent e){
                Connection connection;
                ResultSet resultset;

                PreparedStatement psInsert;
                PreparedStatement psPassword;

                try{
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mysql", "root", "!!81eirek@$!!");
                    
                    psPassword = connection.prepareStatement("SELECT password FROM schema.logindatabase WHERE password = ?");
                    psPassword.setString(1, newPassword.getText());
                    resultset = psPassword.executeQuery();
                    
                        if(!newPassword.getText().isEmpty() && !newPassword2.getText().isEmpty()){

                            String checkpass = newPassword.getText();
                            if(checkpass.equals(newPassword2.getText())){

                                if(!resultset.isBeforeFirst()){
                                    psInsert = connection.prepareStatement("UPDATE schema.logindatabase SET password = ? WHERE username = ? AND email = ?"); 
                                    psInsert.setString(1, newPassword.getText());
                                    psInsert.setString(2, enterName.getText());
                                    psInsert.setString(3, enterEmail.getText());
                                    psInsert.executeUpdate();
    
                                    JOptionPane.showMessageDialog(null, "Your password has been reset!");
                                    frame5.dispose();
                                    UserLogin();
                                }
    
                                else{
                                    JOptionPane.showMessageDialog(null, "Your new password cannot be the same as your current password.");
                                    newPassword.setText("");
                                    newPassword2.setText("");
                                }
                            }

                            else{
                                JOptionPane.showMessageDialog(null, "Please match and confirm your new password.");
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
    }
}