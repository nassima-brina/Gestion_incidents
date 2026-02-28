/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidents.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class UserService {

    private Connection con;

    // 🔗 CONNEXION BASE
    public UserService() {
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gestion_incidents",
                "root",
                ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ INSERTION UTILISATEUR (INSCRIPTION)
    public void insert(String email, String password, String code) throws Exception {

        String sql = "INSERT INTO users (email, password, verification_code, verified) VALUES (?, ?, ?, false)";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, email.trim());
        pst.setString(2, password.trim());
        pst.setString(3, code.trim());

        pst.executeUpdate();
    }

    // ✅ VÉRIFIER CODE + ACTIVER DIRECTEMENT
    public boolean verifyAndActivate(String email, String code) throws Exception {

        String sql = "SELECT * FROM users WHERE email=? AND verification_code=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, email.trim());
        pst.setString(2, code.trim());

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            // Activer le compte
            String update = "UPDATE users SET verified=true WHERE email=?";
            PreparedStatement pst2 = con.prepareStatement(update);
            pst2.setString(1, email.trim());
            pst2.executeUpdate();

            return true;
        }

        return false;
    }

    // ✅ CONNEXION (LOGIN)
    public boolean login(String email, String password) throws Exception {

        String sql = "SELECT * FROM users WHERE email=? AND password=? AND verified=true";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, email.trim());
        pst.setString(2, password.trim());

        ResultSet rs = pst.executeQuery();

        return rs.next();
    }

    // ✅ METTRE À JOUR MOT DE PASSE
    public void updatePassword(String email, String newPassword) throws Exception {

        String sql = "UPDATE users SET password=? WHERE email=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, newPassword.trim());
        pst.setString(2, email.trim());

        pst.executeUpdate();
    }

   
}