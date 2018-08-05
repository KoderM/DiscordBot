package bot.discord;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;

public class Main {
	
	public static JDA jda;
	
	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		builder.setToken("NDY3NTg3OTIzNjgwODIxMjY4.DisyuQ.1X_EUALSAVFLwBMUuse28mwItRA");
		builder.setAutoReconnect(true);
		builder.setStatus(OnlineStatus.ONLINE);
		
		
		try {
			jda = builder.buildBlocking();
			jda.addEventListener(new MessageListener());
			
		}catch(LoginException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
