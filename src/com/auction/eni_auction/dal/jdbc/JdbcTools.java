package com.auction.eni_auction.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.auction.eni_auction.dal.ConnectionProvider;

public class JdbcTools {
    static Connection connect() {
        Connection cnx = null;
        try {
            cnx = ConnectionProvider.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnx;
    }
}
