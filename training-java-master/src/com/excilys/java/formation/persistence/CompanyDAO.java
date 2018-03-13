package com.excilys.java.formation.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.java.formation.mapper.Company;
import com.excilys.java.formation.mapper.CompanyMapper;

public class CompanyDAO {
	
	private static CompanyDAO companyDao;
	
	public CompanyDAO() {
		
	}
	
	/**
	 * 
	 * @return the companyDAO
	 */
	public static CompanyDAO getCompanyDAO() {
		if(companyDao == null)
			companyDao = new CompanyDAO();
		return companyDao;
	}
	
	/**
	 * Method to get the list of companies
	 * @return the list of companies
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Company> getListCompany() throws SQLException, ClassNotFoundException{
		
		Connection conn = SQLConnection.getConnection();
		conn.setAutoCommit(false);
		String query = "SELECT * FROM company;";
		PreparedStatement stmt =  conn.prepareStatement(query);
		ResultSet res = stmt.executeQuery(query);
		
		conn.commit();
		
		
		List<Company> l = CompanyMapper.getCompanyMapper().getListCompanyFromResultSet(res);
		stmt.close();
		return l;
	}

	}

   
	
	
	

