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
package com.rogue.helpticket.data;

import com.rogue.helpticket.HelpTicket;
import com.rogue.helpticket.data.mysql.Data_MySQL;
import com.rogue.helpticket.data.sqlite.Data_SQLite;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public class DataManager {

    protected final HelpTicket plugin;
    protected DataHandler data;

    /**
     * The constuctor for DataManager.
     *
     * @since 12.10.01
     * @version 12.10.01
     *
     * @param plugin The main plugin instance
     * @param automatic Whether to automatically select and run necessary
     * operations
     */
    public DataManager(HelpTicket plugin, boolean automatic) {
        this.plugin = plugin;
        if (automatic) {
            startData();
        }
    }

    /**
     * Begins the process for selecting the data manager and starting necessary
     * processes.
     *
     * @since 12.10.01
     * @version 12.10.01
     */
    private void startData() {
        //this.select(plugin.getConfigurationLoader().getString("data.manager"));
        this.select("sqlite");
        this.setup();
    }

    /**
     * Selects the proper data manager to use, based on the configuration set by
     * the user. By default, it will use sqlite if the user enters something
     * that isn't compatible.
     *
     * @since 12.10.01
     * @version 12.10.01
     *
     * @param type The type of data manager to use
     */
    public void select(String type) {
        type = type.toLowerCase();
        if (type.equals("mysql")) {
            data = new Data_MySQL();
        } else {
            data = new Data_SQLite();
        }
    }

    /**
     * Runs the startup process for the data manager at hand.
     *
     * @since 12.10.01
     * @version 12.10.01
     */
    public void setup() {
        data.init();
        data.verifyFormat();
    }

    /**
     * Gets the interface in use for handling data. (MySQL, SQLite, or YAML)
     *
     * @since 12.10.01
     * @version 12.10.01
     *
     * @return Data Handler
     */
    public DataHandler getDataHandler() {
        return data;
    }

    /**
     * Starts the conversion process from one data type to another
     *
     * @since 12.10.01
     * @version 12.10.01
     *
     * @param newType The new data type (mysql, sqlite, or flatfile)
     * @param players Any players to notify after the completion
     */
    public void convertData(String newType, String... players) {
        data.startConversion(newType, players);
    }
}
