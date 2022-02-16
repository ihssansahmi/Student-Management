package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import dao.Etudiant;

 public class BaseD {
	//======================
	public Connection connection() {
		try {
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      System.out.println("Driver O.K.");

		      String url = "jdbc:mysql://localhost:3308/ecole";
		      String user = "root";
		      String passwd = "";

		      Connection con = DriverManager.getConnection(url, user, passwd);
		      System.out.println("Connexion effective !");
		      
		      return con;
		} catch (Exception e) {
			e.printStackTrace();
			 System.out.println("Connexion Echouee !");
			return null;
		}
	}
	//======================
	public void update(Connection con,Etudiant e) {
		try {
		String sql="UPDATE `etudiant` SET `nom`='"+e.getNom()+"',`prenom`='"+e.getPrenom()+"',`filiere`='"+e.getFiliere()+"' WHERE id='"+e.getId()+"'";
		Statement stm= con.createStatement();
		int rs= stm.executeUpdate(sql);
		} catch (Exception e2) {
			System.out.println("erreur dans modification");
			e2.printStackTrace();
		}
	}
	//======================
	public ResultSet select(Connection con) {
		try {

			String sql="SELECT * from etudiant";
			Statement stm= con.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			return rs;
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("erreur dans selection");
		return null;
	}
		
	}
	public boolean selectUser(Connection con,String username,String password) {
		try {
			
			String sql="SELECT `username`, `password` FROM `user` WHERE `username`='"+username+"'AND `password`='"+password+"'";
			Statement stm= con.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("erreur dans selection user");
		return false;
	}
	}
	
	//======================
	public void insert(Connection con, Etudiant E1) {
			try {
				String SQL="INSERT INTO etudiant (nom,prenom,filiere) VALUES ('"+		
						 E1.getNom()+"','"+E1.getPrenom()+"','"+E1.getFiliere()
						 +"')";
				Statement stm= con.createStatement();
				int rs2=stm.executeUpdate(SQL);
				} catch (SQLException e) {
					System.out.println("erreur dans insertion");
			}
		
	}
}
