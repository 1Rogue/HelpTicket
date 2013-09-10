/*
 * Copyright (C) 2013 Spencer Alderman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rogue.helpticket.data.sqlite;

import com.rogue.helpticket.HelpTicket;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;

/**
 * Instantiable MySQL connector
 * 
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public class SQLite {
    
    private static int connections = 0; // unused
    private Connection con = null;
    private final HelpTicket plugin = HelpTicket.getPlugin();
    
    /**
     * Opens a connection to the SQLite database. Make sure to call
     * SQLite.close() after you are finished working with the database for your
     * segment of your code.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @return The Connection object
     * @throws SQLException 
     */
    public Connection open() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, "Error loading SQLite connection, disabling!", ex);
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
        con = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "users.db");
        return con;
    }

   /**
     * Checks if a table exists within the set database
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param tablename Name of the table to check for
     * @return true if exists, false otherwise
     * @throws SQLException 
     */
   public boolean checkTable(String tablename) throws SQLException {
        ResultSet count = query("SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='" + tablename + "'");
        int i = 0;
        if (count.next()) {
            i = count.getInt(1);
        }
        count.close();
        return (i == 1) ? true : false;
    }

    /**
     * Executes a query, but does not update any information nor lock the
     * database
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param query The string query to execute
     * @return A ResultSet from the query
     * @throws SQLException 
     */
    public ResultSet query(String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
    
    /**
     * Executes a query that can change values, and will lock the database for
     * the duration of the query
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param query The string query to execute
     * @return 0 for no returned results, or the number of returned rows
     * @throws SQLException 
     */
    public synchronized int update(String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(query);
    }
    
    /**
     * Closes the SQLite connection. Must be open first.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @throws SQLException 
     */
    public void close() throws SQLException {
        con.close();
    }
}
