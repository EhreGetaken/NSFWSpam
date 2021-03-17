package eu.imposdev.nsfwspam.util;

import eu.imposdev.nsfwspam.NSFWSpam;
import eu.imposdev.nsfwspam.api.RedditAPI;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.ProxyServer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static TextChannel NSFW;

    public static void runRandomNude() {
        ProxyServer.getInstance().getScheduler().schedule(NSFWSpam.getInstance(), () -> {
            if (NSFW != null) {
                //PICS_OF_MONKEYS.sendMessage(TenorAPI.returnRandomGIF("monkey", 10)).queue();
                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(RedditAPI.REDDIT_LIST.size());
                String reddit = RedditAPI.REDDIT_LIST.get(index);
                NSFW.sendMessage(RedditAPI.getContent(reddit)).queue(message -> {
                });
            }
        }, 0L, 1L, TimeUnit.MINUTES);
    }

    public static void changeActivity() {
        ProxyServer.getInstance().getScheduler().schedule(NSFWSpam.getInstance(), () -> {
            NSFWSpam.getInstance().getShardManager().setActivity(Activity.playing("with nudes"));
        }, 0L, 30L, TimeUnit.SECONDS);
    }

}
