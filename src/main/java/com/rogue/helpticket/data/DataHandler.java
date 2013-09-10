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

import java.util.List;
import java.util.Map;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public interface DataHandler {
    
    /**
     * Gets the name for the data type in use
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @return The data type name, all lowercase
     */
    public abstract String getName();
    
    /**
     * Gets an array of 6 tickets. The first index will be the total number of
     * pages returned from the query.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param status Whether the tickets to be viewed are open, closed, or both
     * @param page The page of tickets to view
     * @return An array of tickets of the requested kind
     */
    public abstract String[] getTickets(int status, int page);
    
    /**
     * A void method used to double-check that the files/sql databases are
     * correctly formatted, and will not cause errors in the future.
     * 
     * @since 12.10.01
     * @version 12.10.01
     */
    public abstract void verifyFormat();
    
    /**
     * Sets any necessary variables before dealing with data management.
     * 
     * @since 12.10.01
     * @version 12.10.01
     */
    public abstract void init();
    
    /**
     * Runs an asynchronous class that will get the entirety of the currently
     * used data storage method. This will in turn set a new runnable for
     * writing to the new form.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param newType The new type of data manager to use in String form
     * @param players Players to notify on conversion end
     */
    public abstract void startConversion(String newType, String... players);
    
    /**
     * Does any necessary operations before closing down, such as closing
     * sql connections, or stopping runnables.
     * 
     * @since 12.10.01
     * @version 12.10.01
     */
    public abstract void cleanup();
}
