package lesson22.config;

import java.sql.Connection;

public interface Database {
    Connection getDBConnection();
}
