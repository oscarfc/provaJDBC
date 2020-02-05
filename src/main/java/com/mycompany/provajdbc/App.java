/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.provajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tss
 */
public class App {

    public static void main(String[] args) throws SQLException {
        Connection cn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/new_schema", "tss", "ghiglieno");

        System.out.println("connection ok");

        Statement stm = cn.createStatement();
        String insert = "insert into T_animali(id_animali, descrizione, specie, classe, ordine) values" + "(1, 'moscerino della frutta', 'D. melanoganster', 'insecta', 'diptera')";
//        stm.executeUpdate(insert);

        PreparedStatement ps = cn.prepareStatement("insert into T_animali(id_animali, descrizione, specie, classe, ordine) values(?,?,?,?,?)");
        ps.setInt(1, 2);
        ps.setString(2, "uomo");
        ps.setString(3, "Homo sapiens");
        ps.setString(4, "Mammalia");
        ps.setString(5, "Primates");
//        ps.execute();
        ps = cn.prepareStatement("select * from T_animali where id_animali = ?");
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("descrizione " + rs.getString("descrizione"));
            System.out.println("specie " + rs.getString("specie"));
            System.out.println("classe " + rs.getString("classe"));
        }
        cn.close();

    }

}
