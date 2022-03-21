package it.polito.tdp.librettovoti.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvaVoti {
	
	public void aggiungiVoto(String nome, int punti) {
		String url="jdbc:mysql://localhost:3306/libretto?user=root&password=rootroot";
		try {
			Connection conn = DriverManager.getConnection(url);
			String sql = "INSERT INTO voti (nome, voto) VALUES (?, ?);";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nome);
			st.setInt(2, punti);
			
			int res = st.executeUpdate(); //ATTENZIONE: Non si deve inserire sql tra parentesi
			st.close(); //Quando lo statement non serve più si può chiuderlo
			
			if(res==1) {
				System.out.println("Dato inserito correttamente");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
	}

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/libretto?user=root&password=rootroot";
		
		ProvaVoti provaVoti=new ProvaVoti();
		provaVoti.aggiungiVoto("Tdp",30);
		
		try {
			//Per aprire la connessione
			Connection conn = DriverManager.getConnection(url);
			
			//Per creare delle query
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM voti";
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) {
				String nome=res.getString("nome");
				int voto=res.getInt("voto");
				System.out.println(nome+" "+voto);
			}
			st.close(); //Quando lo statement non serve più si può chiuderlo
			
			
			// Per chiudere la connessione
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
