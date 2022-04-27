package tugasjdbc;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.ResultSet;

public class GUILoginRegister extends JFrame {
    
    Connector conn = new Connector();
    
    // ===== Component ====
    
    JLabel ltitle = new JLabel("Login");
    JLabel ltitle2 = new JLabel("Register");
    JLabel lusername = new JLabel("Username");
    JLabel lusername2 = new JLabel("Username");
    JLabel lpassword = new JLabel("Password");
    JLabel lpassword2 = new JLabel("Password");
    
    JTextField fusername = new JTextField();
    JTextField fusername2 = new JTextField();
    JTextField fpassword = new JTextField();
    JTextField fpassword2 = new JTextField();
    
    JButton btnLogin = new JButton("Login");
    JButton btnReg = new JButton("Register");
    
    // ==== End Component ====

    public GUILoginRegister() {
    
        setTitle("Login Register System");
        setSize(800, 250);
        setLayout(null);
        
        //add component to frame
        add(ltitle);
        add(ltitle2);
        add(lusername);
        add(lusername2);
        add(lpassword);
        add(lpassword2);
        add(fusername);
        add(fusername2);
        add(fpassword);
        add(fpassword2);
        add(btnLogin);
        add(btnReg);
        
        //bounds
        ltitle.setBounds(195, 40, 200, 25);
        lusername.setBounds(30, 80, 100, 25);
        fusername.setBounds(130, 80, 200, 25);
        lpassword.setBounds(30, 110, 100, 25);
        fpassword.setBounds(130, 110, 200, 25);
        btnLogin.setBounds(170, 150, 80, 25);
        ltitle2.setBounds(595, 40, 200, 25);
        lusername2.setBounds(430, 80, 100, 25);
        fusername2.setBounds(530, 80, 200, 25);
        lpassword2.setBounds(430, 110, 100, 25);
        fpassword2.setBounds(530, 110, 200, 25);
        btnReg.setBounds(570, 150, 100, 25);
        
        btnReg.addActionListener((ActionEvent arg0) ->{
            try{
                if(!getFusername2().isEmpty() & !getFpassword2().isEmpty()){
                    String query = "INSERT INTO `users`(`username`, `password`) VALUES ('" + getFusername2() + "', '" + getFpassword2() + "')";
                    
                    conn.statement = conn.koneksi.createStatement();
                    conn.statement.executeUpdate(query);
                    
                    fusername2.setText("");
                    fpassword2.setText("");
                    
                    JOptionPane.showMessageDialog(null,"Berhasil Mendaftarkan User");
                }else{
                    JOptionPane.showMessageDialog(null,"Username dan Password Tidak Boleh Kosong");
                }
            }catch (HeadlessException | SQLException ex){
                if(ex.getMessage().contains("Duplicate entry")){
                    JOptionPane.showMessageDialog(null,"Username Sudah Digunakan");
                }
            }
        });
        
        btnLogin.addActionListener((ActionEvent arg0) -> {
            try {
                String query = "SELECT `username`, `password` FROM `users` WHERE `username` = '" + getFusername() + "'";
                
                conn.statement = conn.koneksi.createStatement();
                ResultSet resultSet = conn.statement.executeQuery(query);
                
                if(resultSet.next()){
                    if(resultSet.getString("password").equals(getFpassword())){
                        fusername.setText("");
                        fpassword.setText("");
                        JOptionPane.showMessageDialog(null,"Berhasil Login");
                    }else{
                        JOptionPane.showMessageDialog(null,"Password Salah");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Username Tidak Ditemukan");
                }
            } catch (HeadlessException | SQLException ex){
                System.out.println(ex.getMessage());
            }
        });
        
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getFusername() {
        return fusername.getText();
    }

    public String getFusername2() {
        return fusername2.getText();
    }

    public String getFpassword() {
        return fpassword.getText();
    }

    public String getFpassword2() {
        return fpassword2.getText();
    }
    
}
