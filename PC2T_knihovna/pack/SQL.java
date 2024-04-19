package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

	private Connection spojeni;
	
	public SQL() {
		super();
	}

	public boolean connect() {
		spojeni = null;
		try {
			spojeni = DriverManager.getConnection("jdbc:sqlite:Knihovna.db");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public void disconnect() {
		if (spojeni != null) {
			try {
				spojeni.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public boolean createTable() {
		if (spojeni == null)
			return false;
		String newTable = "CREATE TABLE IF NOT EXISTS knihyTable (" + "nazev varchar(2000) PRIMARY KEY,"
				+ "druh varchar(12)," + "autor varchar(200)," + "rok_vydani smallint, " + "stav varchar(12),"
				+ "vhodnost_nebo_zanr varchar(200)" + ");";
		try {
			Statement stmt = spojeni.createStatement();
			stmt.execute(newTable);
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	public boolean deleteTable() {
		if (spojeni == null)
			return false;
		String deleteTable = "DELETE FROM knihyTable";
		try {
			Statement stmt = spojeni.createStatement();
			stmt.execute(deleteTable);
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean insertRecord(String nazev, String druh, String autor, int rokVydani, String stav, String vhodnostORzanr) {
		String kniha = "INSERT INTO knihyTable(nazev,druh,autor,rok_vydani,stav,vhodnost_nebo_zanr) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = spojeni.prepareStatement(kniha);
			pstmt.setString(1, nazev);
			pstmt.setString(2, druh);
			pstmt.setString(3, autor);
			pstmt.setInt(4, rokVydani);
			pstmt.setString(5, stav);
			pstmt.setString(6, vhodnostORzanr);
			pstmt.executeUpdate();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public ResultSet selectAll() {
		String selectAll = "SELECT nazev,druh,autor,rok_vydani,stav,vhodnost_nebo_zanr FROM knihyTable";
		try {
			Statement stmt = spojeni.createStatement();
			stmt.close();
			return stmt.executeQuery(selectAll);
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	
}
