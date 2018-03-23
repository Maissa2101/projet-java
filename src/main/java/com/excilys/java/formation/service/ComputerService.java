package com.excilys.java.formation.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.formation.entities.Computer;
import com.excilys.java.formation.interfaceDAO.ComputerDAOInterface;
import com.excilys.java.formation.persistence.ComputerDAO;
import com.excilys.java.formation.persistence.DAOConfigurationException;
import com.excilys.java.formation.persistence.DAOException;

public enum ComputerService {

	INSTANCE;
	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	/**
	 * Method to show the list of computers
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 */
	public List<Computer> listComputers(int limit, int offset) throws DAOException{
		ComputerDAO computers = ComputerDAO.INSTANCE;
		return computers.getListComputer(limit, offset);
	}

	/**
	 * Method to show the details of a computer given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public String computerDetails(Long id) throws DAOException, ValidatorException {
		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerV = ComputerValidator.INSTANCE;
		String rsult = null;

		try {
			if(computerV.idValidator(id)) {
				rsult = "\n"+ computers.getComputer(id) + "\n";
			}
		} catch (ValidatorException | ClassNotFoundException | SQLException e) {
			rsult = "Problem in Computer Details";
		}
		return rsult;

	}

	/**
	 * Method to create a computer
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void createComputer(String name, LocalDate tm1, LocalDate tm2, String manufacturer) throws DAOException, ValidatorException {

		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerV = ComputerValidator.INSTANCE;
		CompanyValidator companyV = CompanyValidator.INSTANCE;

		try {
			if((computerV.nameValidator(name)) && (computerV.DateValidator(tm1, tm2)) && (companyV.idCompanyValidator(manufacturer))) {
				computers.createComputer(name, tm1, tm2, manufacturer);
			}	
		} catch (ValidatorException | ClassNotFoundException | SQLException e) {
			logger.error("Problem in Create Computer");
		}	

	}

	/**
	 * Method to update a computer details given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void updateComputer(Long id_update, String new_name, LocalDate new_date, LocalDate new_date2 ) throws DAOException, ValidatorException {
		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerV = ComputerValidator.INSTANCE;

		try {
			if((computerV.idValidator(id_update)) && (computerV.nameValidator(new_name)) && computerV.DateValidator(new_date, new_date2) ) {
				computers.updateComputer(id_update, new_name,new_date, new_date2);
			}
		} catch (ValidatorException | ClassNotFoundException | SQLException e) {
			logger.info("Problem in Update Computer");
		}	

	}

	/**
	 * Method to delete a computer given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void deleteComputer(Long id_delete) throws DAOException, ValidatorException {
		ComputerDAOInterface computers = ComputerDAO.INSTANCE;
		ComputerValidator computerV = ComputerValidator.INSTANCE;

		try {
			if(computerV.idValidator(id_delete)) {
				computers.deleteComputer(id_delete);
			}	
		} catch (ValidatorException | ClassNotFoundException | SQLException e) {
			logger.info("Problem in Delete Computer");
		}	

	}

	/**
	 * Counts the number of computers in the DB
	 * @return total number of computers
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws DAOConfigurationException 
	 */
	public int count() throws DAOConfigurationException {
		return ComputerDAO.INSTANCE.count();
	}
}
