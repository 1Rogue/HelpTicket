package com.rogue.helpticket.cmds.helpticket;

import org.bukkit.entity.Player;

import com.rogue.deityapi.api.DeityCommandReceiver;
import com.rogue.helpticket.HelpTicketLanguageHelper;
import com.rogue.helpticket.HelpTicketMain;
import com.rogue.helpticket.obj.PlayerSession;

public class TicketSelectCommand extends DeityCommandReceiver {
    public boolean onConsoleRunCommand(String[] args) {
        return false;
    }
    
    public boolean onPlayerRunCommand(Player player, String[] args) {
        if (args.length < 1) { return false; }
        int ticketId;
        try {
            ticketId = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            ticketId = -1;
        }
        PlayerSession session = PlayerSession.addPlayerSession(player.getName(), ticketId);
        if (session.getTicket() == null) {
            PlayerSession.removePlayerSession(player.getName());
            HelpTicketMain.replaceAndSend(player, HelpTicketLanguageHelper.TICKET_SELECT_FAIL, session.getTicket());
            return true;
        }
        if ((!player.getName().equalsIgnoreCase(session.getTicket().getOwner())) && (!session.getTicket().getOwner().equalsIgnoreCase(player.getName())) && (!HelpTicketMain.isAdmin(player))) {
            PlayerSession.removePlayerSession(player.getName());
            HelpTicketMain.replaceAndSend(player, HelpTicketLanguageHelper.TICKET_SELECT_FAIL, session.getTicket());
            return true;
        }
        HelpTicketMain.replaceAndSend(player, HelpTicketLanguageHelper.TICKET_SELECT_SUCCESS, session.getTicket());
        return true;
    }
}