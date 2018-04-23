package com.excilys.java.formation.mapper;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.hsqldb.persist.HsqlDatabaseProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.java.formation.entities.Company;
import com.excilys.java.formation.persistence.CompanyDAOSpring;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class CompanyRowMapperTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CompanyDAOSpring companyDAO;

	@Before
	public void init() throws IOException, SqlToolError, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		Connection connection = DataSourceUtils.getConnection(dataSource);
		InputStream inputStream = HsqlDatabaseProperties.class.getResourceAsStream("/TEST.sql");
		SqlFile sqlFile = new SqlFile(new InputStreamReader(inputStream), "init", System.out, "UTF-8", false,
				new File("."));
		sqlFile.setConnection(connection);
		sqlFile.execute();
	}

	@Test
	public void test() {
		Optional<Company> details = companyDAO.getCompany(5L);
		assertEquals("Tandy Corporation", details.get().getName());
	}

}