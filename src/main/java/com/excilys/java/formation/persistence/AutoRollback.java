package com.excilys.java.formation.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoRollback implements AutoCloseable {

    private Connection conn;
    private boolean committed;
    Logger logger = LoggerFactory.getLogger(AutoRollback.class);
    
    public AutoRollback(Connection conn) throws SQLException {
        this.conn = conn;        
    }

    public void commit() throws SQLException {
        conn.commit();
        committed = true;
    }

    @Override
    public void close() throws SQLException {
        if(!committed) {
            conn.rollback();
        }
    }

}