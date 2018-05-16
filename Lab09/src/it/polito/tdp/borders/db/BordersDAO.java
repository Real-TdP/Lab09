package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.BorderIdMap;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;

public class BordersDAO {

	public List<Country> loadAllCountries(CountryIdMap cIdMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getInt("ccode"),rs.getString("StateAbb"),rs.getString("StateNme"));
				result.add(cIdMap.get(c));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno,BorderIdMap bIdMap,CountryIdMap cIdMap) {

		String sql ="SELECT state1no, state2no,year FROM contiguity WHERE year<=?  AND conttype=1";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
			int cont=0;
			while (rs.next()) {
				int id1= rs.getInt("state1no");
				int id2= rs.getInt("state2no");
				int year= rs.getInt("year");
				Border b = new Border(cont,cIdMap.getCountry(id1),cIdMap.getCountry(id2),year);
				cIdMap.getCountry(id1).getBorders().add(cIdMap.getCountry(id2));
				cIdMap.getCountry(id2).getBorders().add(cIdMap.getCountry(id1));
				result.add(bIdMap.get(b));
				cont++;
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
