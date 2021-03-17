package eu.imposdev.nsfwspam;

import eu.imposdev.nsfwspam.api.RedditAPI;
import eu.imposdev.nsfwspam.util.Utils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.discordbots.api.client.DiscordBotListAPI;

import javax.security.auth.login.LoginException;
import java.util.concurrent.TimeUnit;

public class NSFWSpam extends Plugin {

    private static NSFWSpam instance;
    private DiscordBotListAPI discordBotListAPI;
    private ShardManager shardManager;

    @Override
    public void onEnable() {
        instance = this;

        RedditAPI.REDDIT_LIST.add("onlyfans4all");
        RedditAPI.REDDIT_LIST.add("GermansGoneWild");
        RedditAPI.REDDIT_LIST.add("porn");
        RedditAPI.REDDIT_LIST.add("tiktokporn");

        try {
            shardManager = DefaultShardManagerBuilder.createDefault("")
                    //.setShardsTotal(1)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .enableIntents(GatewayIntent.DIRECT_MESSAGE_REACTIONS)
                    .enableIntents(GatewayIntent.DIRECT_MESSAGES)
                    .enableIntents(GatewayIntent.GUILD_BANS)
                    .build();
        } catch (LoginException exc) {
            exc.printStackTrace();
        }

        Utils.changeActivity();

        ProxyServer.getInstance().getScheduler().schedule(this, () -> {
            Guild guild = shardManager.getGuildById("759741472245022760");
            if (guild != null) {
                Utils.NSFW = guild.getTextChannelById("798825615040970763");
                System.out.println("Guild and Channel found!");
                Utils.runRandomNude();
                if (Utils.NSFW == null) {
                    System.out.println("Channel is null!");
                }
            } else {
                System.out.println("Guild is null!");
            }
        }, 4L, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {

    }

    public static NSFWSpam getInstance() {
        return instance;
    }

    public DiscordBotListAPI getDiscordBotListAPI() {
        return discordBotListAPI;
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}
