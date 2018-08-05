package bot.discord;

import league.LeagueAPI;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

	LeagueAPI test = new LeagueAPI();
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
    	MessageChannel channel = event.getChannel();
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                                    event.getMessage().getContentDisplay());
        }
        else
        {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                        event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                        event.getMessage().getContentDisplay());
            
            
            if(event.getMessage().getContentDisplay().equals("yo whatup")) {
            	channel.sendMessage("Ay holmes, whats crackin").queue();
            }else if(event.getAuthor().getName().equals("Koder")){
            	try {
            		channel.sendMessage(test.getChampionMasteries("KoderMagic")).queue();
            	}catch(Exception e) {
            		System.out.println("Error");
            		e.printStackTrace();
            	}
            }
        }
    }
}
