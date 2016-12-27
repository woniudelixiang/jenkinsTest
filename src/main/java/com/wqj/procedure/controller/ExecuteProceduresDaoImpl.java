package com.wqj.procedure.controller;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wqj.procedure.MyPreparedStatementCallback;


public class ExecuteProceduresDaoImpl extends JdbcDaoSupport{  
	
@SuppressWarnings({ "unchecked", "rawtypes" })
public  Object  Call_pr(final String[] parm) {  
   // String procedureSql = "CALL pr()";  
    String procedureSql = "{?=call pr()}";  
        return (Object) getJdbcTemplate().execute(procedureSql, new CallableStatementCallback() {

			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.registerOutParameter(1, Types.INTEGER);
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				
				
				return "111111";
			}
		});

    }

@Test
public  void templete() {
	//new ExecuteProceduresDaoImpl().Call_pr(null);
	 String callString = ""; 
	 MyPreparedStatementCallback mpsc = new MyPreparedStatementCallback(null);
	 Object obj = getJdbcTemplate().execute(callString,mpsc);
	System.out.println(obj==null);
	
}
  
}  
