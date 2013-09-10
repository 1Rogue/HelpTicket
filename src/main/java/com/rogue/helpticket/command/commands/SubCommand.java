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
package com.rogue.helpticket.command.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public interface SubCommand {

    /**
     * Executes a relevant command grabbed from the CommandHandler.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @param sender The command executor
     * @param args The command arguments
     * 
     * @return true on success, false if failed
     */
    public abstract boolean execute(CommandSender sender, String[] args);
    
    /**
     * Returns the name of the command, used for storing a hashmap of the
     * commands
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @return The command's name
     */
    public abstract String getName();
    
    /**
     * Returns a String array of help information. The first index will always
     * be command usage, and resulting lines will be help information.
     * 
     * @since 12.10.01
     * @version 12.10.01
     * 
     * @return Command help info
     */
    public abstract String[] getHelp();
    
}