package com.geog.DAO;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Region;

import java.sql.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;

public class DAO {
	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);

	}//public SQLDAO() throws Exception {
	

	public ArrayList<Country> loadCountries() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery("SELECT * FROM COUNTRY");
		final ArrayList<Country> countries = new ArrayList<Country>();
	
		while (rs.next()) {
			final Country country = new Country();
			country.setCode(rs.getString("co_code"));
			country.setName(rs.getString("co_name"));
			country.setDetails(rs.getString("co_details"));
			countries.add(country);
		}
		return countries;
	}//add countries
	public void addCountry(Country country)throws Exception{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into country values (?, ?, ?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, country.getCode());
		stmt.setString(2, country.getName());
		stmt.setString(3, country.getDetails());

		stmt.execute();	
	}
	
	public void deleteCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		//ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from country where co_code=?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCode());
		myStmt.execute();
	}

	
	public void updateCountry(Country country) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "UPDATE COUNTRY SET co_name=?, co_details=? WHERE co_code=?";
		
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(3, country.getCode());
		myStmt.setString(1, country.getName());
		myStmt.setString(2, country.getDetails());
		myStmt.execute();		
		
		
	}
	
	public ArrayList<Region> loadRegion() throws SQLException{
		
		Connection conn = mysqlDS.getConnection();
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery("SELECT * FROM REGION");
		final ArrayList<Region> regions = new ArrayList<Region>();
		
		while(rs.next()){
			final Region region = new Region();
			region.setCode(rs.getString("co_code"));
			region.setRegCode(rs.getString("reg_code"));
			region.setRegDetails(rs.getString("reg_desc"));
			region.setRegName(rs.getString("reg_name"));
			regions.add(region);
		}
		return regions;
	}
	//add region
	public void addRegion (Region region) throws Exception{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into region values (?, ? , ?,?)";
		stmt =conn.prepareStatement(sql);
		stmt.setString(1, region.getCode());
		stmt.setString(2, region.getRegCode());
		stmt.setString(3, region.getRegName());
		stmt.setString(4, region.getRegDetails());
		stmt.execute();
	}
	
	public ArrayList<City> loadCity() throws SQLException{
		
		Connection conn = mysqlDS.getConnection();
		final Statement stmt = conn.createStatement();
		final ResultSet rs = stmt.executeQuery("SELECT * FROM city");
		final ArrayList<City> cities = new ArrayList<City>();
		
		
		while(rs.next()){
			
			final City city = new City();
			city.setCode(rs.getString("cty_code"));
			city.setAreaKm(rs.getDouble("areaKM"));
			city.setIsCoastal(rs.getBoolean("isCoastal"));
			city.setCountryCode(rs.getString("co_code"));
			city.setName(rs.getString("cty_name"));
			city.setPopulation(rs.getLong("population"));
			city.setRegCode(rs.getString("reg_code"));
			cities.add(city);
		}
		return cities;	
	}
	
	public void addCity (City city) throws Exception{
		
		Connection conn = mysqlDS.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into city values (?, ? , ? , ? , ? , ? , ? )";
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, city.getCode());
		stmt.setString(2, city.getCountryCode());
		stmt.setString(3, city.getRegCode());
		stmt.setString(4, city.getName());
		stmt.setFloat(5, city.getPopulation());
		stmt.setString(6, city.getIsCoastal() ? "true" : "false");
		stmt.setDouble(7, city.getAreaKm());
		stmt.execute();
		
		
	}
	
}//SQLDAO