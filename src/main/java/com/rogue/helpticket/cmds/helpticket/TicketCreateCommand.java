package com.rogue.helpticket.cmds.helpticket;

import org.bukkit.entity.Player;

import com.rogue.deityapi.DeityAPI;
import com.rogue.deityapi.api.DeityCommandReceiver;
import com.rogue.helpticket.HelpTicketConfigHelper;
import com.rogue.helpticket.HelpTicketLanguageHelper;
import com.rogue.helpticket.HelpTicketMain;
import com.rogue.helpticket.enums.OpenStatusType;
import com.rogue.helpticket.obj.PlayerSession;
import com.rogue.helpticket.obj.Ticket;
import com.rogue.helpticket.obj.TicketManager;

public class TicketCreateCommand extends DeityCommandReceiver {
    public boolean onConsoleRunCommand(String[] args) {
        return false;
    }
    
    public boolean onPlayerRunCommand(Player player, String[] args) {
        if (args.length < 1) return false;
        String info = DeityAPI.getAPI().getUtilAPI().getStringUtils().join(args);
        new CreateTicket(player, info);
        return true;
    }
    
    public class CreateTicket implements Runnable {
        private Player player;
        private String info;
        
        public CreateTicket(Player player, String info) {
            this.player = player;
            this.info = info;
            HelpTicketMain.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(HelpTicketMain.plugin, this);
        }
        
        public void run() {
            if (TicketManager.getAllTicketsFromPlayer(player.getName(), OpenStatusType.OPEN).size() >= HelpTicketMain.plugin.config
                    .getInt(HelpTicketConfigHelper.MAX_TICKETS_OPEN)) {
                HelpTicketMain.plugin.chat.sendPlayerMessage(player,
                        HelpTicketMain.plugin.language.getNode(HelpTicketLanguageHelper.TICKET_CREATE_MAX));
                return;
            }
            
            Ticket ticket = TicketManager.getTicketFromPlayer(this.player.getName(), this.info);
            if (ticket == null) {
                ticket = TicketManager.addNewTicket(this.player.getName(), this.player.getLocation(), this.info);
                PlayerSession.addPlayerSession(player.getName(), ticket.getId());
                HelpTicketMain.replaceAndSend(this.player, HelpTicketLanguageHelper.TICKET_CREATE_SUCCESS, ticket);
                for (Player p : HelpTicketMain.plugin.getServer().getOnlinePlayers()) {
                    if (HelpTicketMain.isAdmin(p) && !this.player.getName().equalsIgnoreCase(p.getName())) {
                        HelpTicketMain.replaceAndSend(p, HelpTicketLanguageHelper.TICKET_CREATE_STAFF, ticket);
                    }
                }
            } else {
                HelpTicketMain.replaceAndSend(this.player, HelpTicketLanguageHelper.TICKET_CREATE_FAIL, ticket);
            }
        }
    }
}