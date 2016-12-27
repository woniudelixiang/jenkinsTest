package com.wqj.procedure;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;

public class MyPreparedStatementCallback implements CallableStatementCallback<Object>{
	private String[] parm;
	public MyPreparedStatementCallback(String[] parm) {
		super();
		this.parm = parm;
	}
	
	@Override
	public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
		 int j = 2;  
         cs.registerOutParameter(1, Types.INTEGER);  
         if (parm != null) {  
             for (int i = 0; i < parm.length; i++) {  
                 cs.setString(j, parm[i]);  
                 ++j;  
             }  
         }  
         if (cs.execute()) {  
             ResultSet rs = cs.getResultSet();  
             while (rs.next()) {  
                 rs.getString(1);  
                 rs.getString(2);  
                 rs.getString(3);  
             }  
             return null;  
         } else {  
             return cs.getString(1);  
         }  
	}

}
