package com.allpass.projectAAA;



import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DatabaseServer {




    private static final Logger logger = LoggerFactory.getLogger(DatabaseServer.class);

    public static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer().start(); // 关键代码
//                Class.forName ("org.h2.Driver");
//                Connection conn = DriverManager.getConnection ("jdbc:h2:~/h2database/demo", "george","ccif6967");
            if (h2Server.isRunning(true)) {
                logger.info("H2 server was started and is running.");
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }

}
