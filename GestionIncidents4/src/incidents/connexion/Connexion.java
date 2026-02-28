/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidents.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author X1 YOGA
 */
public class Connexion {
    
    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost:3306/gestion_incidents";
    private static Connection connexion;
    
    static {
        try {
            // Driver MySQL (version récente)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(url, login, password);
            System.out.println("Connexion établie avec succès !");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver introuvable");
        } catch (SQLException ex) {
            System.out.println("Impossible d'établir la connexion : " + ex.getMessage());
        }
    }

    public static Connection getConnexion() {
        return connexion;
    }
    
    public static void main(String[] args) {
        getConnexion();
    }
}