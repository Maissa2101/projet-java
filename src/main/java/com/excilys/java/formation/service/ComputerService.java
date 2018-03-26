package com.excilys.java.formation.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.formation.entities.Computer;
import com.excilys.java.formation.persistence.ComputerDAO;
import com.excilys.java.formation.persistence.interfaceDAO.ComputerDAOInterface;

public enum ComputerService {

	INSTANCE;
	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	/**
	 * Method to show the list of computers
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 */
	public List<Computer> listComputers(int limit, int offset) throws ServiceException{
		ComputerDAO computers = ComputerDAO.INSTANCE;
		return computers.getListComputer(limit, offset);
	}

	/**
	 * Method to show the details of a computer given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public String computerDetails(long id) throws ServiceException, ValidatorException {
		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerValidator = ComputerValidator.INSTANCE;
		String rsult = null;

		try {
			if(computerValidator.idValidator(id)) {
				rsult = "\n"+ computers.getComputer(id) + "\n";
			}
		} catch (ValidatorException e) {
			rsult = "Problem in Computer Details";
			throw new ServiceException("ServiceException in computerDetails");
		}
		return rsult;

	}

	/**
	 * Method to create a computer
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void createComputer(String name, LocalDate time1, LocalDate time2, String manufacturer) throws ServiceException, ValidatorException {

		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerValidator = ComputerValidator.INSTANCE;
		CompanyValidator companyValidator = CompanyValidator.INSTANCE;

		try {
			if((computerValidator.nameValidator(name)) && (computerValidator.DateValidator(time1, time2)) && (companyValidator.idCompanyValidator(manufacturer))) {
				computers.createComputer(name, time1, time2, manufacturer);
			}	
		} catch (ValidatorException e) {
			logger.error("Problem in Create Computer");
			throw new ServiceException("ServiceException in createComputer");
		}	

	}

	/**
	 * Method to update a computer details given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void updateComputer(long idUpdate, String newName, LocalDate newDate, LocalDate newDate2 ) throws ServiceException, ValidatorException {
		ComputerDAO computers = ComputerDAO.INSTANCE;
		ComputerValidator computerValidator = ComputerValidator.INSTANCE;

		try {
			if((computerValidator.idValidator(idUpdate)) && (computerValidator.nameValidator(newName)) && computerValidator.DateValidator(newDate, newDate2) ) {
				computers.updateComputer(idUpdate, newName,newDate, newDate2);
			}
		} catch (ValidatorException e) {
			logger.info("Problem in Update Computer");
			throw new ServiceException("ServiceException in updateComputer");
		}	

	}

	/**
	 * Method to delete a computer given its ID
	 * @throws ClassNotFoundException when no definition for the class with the specified name could be found
	 * @throws SQLException in case of a database access error
	 * @throws ValidatorException 
	 */
	public void deleteComputer(long idDelete) throws ServiceException, ValidatorException {
		ComputerDAOInterface computers = ComputerDAO.INSTANCE;
		ComputerValidator computerValidator = ComputerValidator.INSTANCE;

		try {
			if(computerValidator.idValidator(idDelete)) {
				computers.deleteComputer(idDelete);
			}	
		} catch (ValidatorException e) {
			logger.info("Problem in Delete Computer");
			throw new ServiceException("ServiceException in deleteComputer");
		}	

	}

	/**
	 * Counts the number of computers in the DB
	 * @return total number of computers
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 * @throws DAOConfigurationException 
	 */
	public int count() throws ServiceException {
		return ComputerDAO.INSTANCE.count();
	}
}
