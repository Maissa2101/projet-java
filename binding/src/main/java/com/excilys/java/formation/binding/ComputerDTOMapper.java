package com.excilys.java.formation.binding;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.excilys.java.formation.dto.ComputerDTO;
import com.excilys.java.formation.entities.Company;
import com.excilys.java.formation.entities.Computer;

@Component
public class ComputerDTOMapper {

	private Logger logger = LoggerFactory.getLogger(ComputerDTOMapper.class);
	private static final String NULL ="";

	/**
	 * Method to convert computer to a computerDTO
	 * @param computer the computer to convert
	 * @return computerDTO 
	 */
	public ComputerDTO getComputerDTOFromComputer(Computer computer) {
		String introduced = null;
		String discontinued = null;
		String manufacturer = null;
		if(computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued().toString();
		}
		if(computer.getIntroduced() != null) {
			introduced = computer.getIntroduced().toString();
		}
		long id = computer.getId();
		String name = computer.getName();
		if(computer.getManufacturer() != null) {
			manufacturer = String.valueOf(computer.getManufacturer().getName());
		}
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setIntroduced(introduced);
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setId(id);
		computerDTO.setName(name);
		computerDTO.setManufacturer(manufacturer);
		return computerDTO;    
	}

	/**
	 * Method to convert computerDTO to a computer
	 * @param computerDTO the computerDTO to convert
	 * @return computer
	 */
	public Computer getComputerFromComputerDTO(ComputerDTO computerDTO) {
		LocalDate introduced = null; 
		LocalDate discontinued = null;
		Company company = new Company();
		try{
			if(!computerDTO.getIntroduced().equals(NULL)) {
				introduced = LocalDate.parse(computerDTO.getIntroduced()); 
			}
			if (!computerDTO.getDiscontinued().equals(NULL)) {
				discontinued = LocalDate.parse(computerDTO.getDiscontinued());
			}
		}
		catch(DateTimeParseException e) {
			logger.debug("Problem in Parser", e);
		}
		long id = computerDTO.getId();
		String name = computerDTO.getName();
		String manufacturer = computerDTO.getManufacturer();
		Computer computer = new Computer();
		computer.setIntroduced(introduced);
		computer.setDisconnected(discontinued);
		computer.setId(id);
		computer.setName(name);
		if(!StringUtils.isEmpty(manufacturer)) {
			company.setId(Integer.parseInt(manufacturer));
		} else {
			company = null;
		}
		computer.setManufacturer(company);
		return computer; 
	}
}
