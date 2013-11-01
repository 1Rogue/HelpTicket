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
package com.rogue.helpticket;

import com.rogue.helpticket.command.CommandHandler;
import com.rogue.helpticket.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @since 12.10.01
 * @author 1Rogue
 * @version 12.10.01
 */
public final class HelpTicket extends JavaPlugin {
    
    private CommandHandler commands;
    private DataManager data;
    
    @Override
    public void onLoad() {
        
    }
    
    @Override
    public void onEnable() {
        
        this.data = new DataManager(this, true);
        
        this.commands = new CommandHandler(this);
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    public static HelpTicket getPlugin() {
        return (HelpTicket)Bukkit.getPluginManager().getPlugin("HelpTicket");
    }
    
    public CommandHandler getCommandHandler() {
        return this.commands;
    }
    
    public void communicate(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&bHelpTicket&9] &b" + message));
    }

}
