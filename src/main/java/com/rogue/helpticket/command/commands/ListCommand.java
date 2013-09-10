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

import com.rogue.helpticket.HelpTicket;
import org.bukkit.command.CommandSender;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public class ListCommand implements SubCommand {
    
    private final HelpTicket plugin;
    
    public ListCommand(HelpTicket plugin) {
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            int page = 0;
            if (args.length > 1) {
                page = Integer.parseInt(args[1]);
            }
            int status = 0;
            if (args[0].equalsIgnoreCase("closed")) {
                status = 1;
            } else if (args[0].equalsIgnoreCase("all")) {
                status = 2;
            }
            //List<Ticket> tickets = this.plugin.getDataManager().getDataHandler().getTickets(status, page);
        }
        return false;
    }

    public String getName() {
        return "list";
    }

    public String[] getHelp() {
        return new String[]{
            "list <open/closed/all> [page-number]",
            "Lists tickets stored within the database"
        };
    }
    
}
