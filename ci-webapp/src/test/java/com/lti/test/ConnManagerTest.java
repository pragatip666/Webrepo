package com.lti.test;
import java.io.IOException;
import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import com.lti.dao.util.ConnManager;


public class ConnManagerTest {
	
	
	public void testConn() throws IOException {
		Connection conn =ConnManager.connect();
		assertNotNull(conn);
	}

}
