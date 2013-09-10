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

import com.rogue.helpticket.data.DataHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public class Data_SQLite implements DataHandler {

    public String getName() {
        return "sqlite";
    }
    
    public String[] getTickets(int status, int page) {
        String[] back = new String[7];
        SQLite db = new SQLite();
        String whereclause = "";
        page = page/6;
        if (status < 2) {
            if (status == 1) {
                whereclause = "WHERE `status`=1 ";
            } else {
                whereclause = "WHERE `status`=0 ";
            }
        }
        try {
            db.open();
            ResultSet pages = db.query("SELECT COUNT(*) FROM `helpticket` " + whereclause);
            ResultSet rs = db.query("SELECT * FROM `helpticket` " + whereclause + "LIMIT 6, " + page);
            // check if the resultset was empty / null  ||  'catch' if it does
            
        } catch (SQLException ex) {
            Logger.getLogger(Data_SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return back;
    }

    public void verifyFormat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void startConversion(String newType, String... players) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
