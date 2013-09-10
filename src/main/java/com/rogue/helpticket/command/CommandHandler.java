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
package com.rogue.helpticket.command;

import com.rogue.helpticket.HelpTicket;
import com.rogue.helpticket.command.commands.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public class CommandHandler implements CommandExecutor {

    private final HelpTicket plugin;
    private final Map<String, SubCommand> commands = new ConcurrentHashMap();

    public CommandHandler(HelpTicket plugin) {
        this.plugin = plugin;

        SubCommand[] cmds = new SubCommand[]{
            new ListCommand(this.plugin)
        };

        for (SubCommand cmd : cmds) {
            commands.put(cmd.getName(), cmd);
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String comandLabel, String[] args) {

        if (args.length < 1) {
            args = new String[]{"help"};
        }

        String[] newArgs = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            newArgs[i - 1] = args[i];
        }

        String command = args[0].toLowerCase();
        SubCommand executor = this.commands.get(command);
        if (executor != null) {
            if (sender.hasPermission("helpticket.cmd." + command)) {
                boolean success = executor.execute(sender, newArgs);
                if (!success) {
                    String[] help = executor.getHelp();
                    for (int i = 0; i < help.length; i++) {
                        if (i == 0) {
                            this.plugin.communicate(sender, "Usage: /ticket " + help[i]);
                        } else {
                            this.plugin.communicate(sender, help[i]);
                        }
                    }
                }
            } else {
                this.plugin.communicate(sender, "You do not have permission to do this!");
            }
        } else {
            this.plugin.communicate(sender, "Unknown command: " + command);
        }

        return false;
    }
}
