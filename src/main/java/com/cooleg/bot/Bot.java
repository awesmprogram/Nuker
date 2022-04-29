package com.cooleg.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.io.IOException;

public class Bot {

    private static JDA jda;
    public static String token;
    public static String message;
    public static String rolenames;
    public static String channelnames;
    public static boolean nukeroles;
    public static boolean nukechannels;

    public static void shutDown() {
        System.out.println("Shutting Down...");
        JDA jda = Bot.getJda();
        jda.shutdown();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new ConfgLoader().loadConfig();
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        builder.addEventListeners(new EventListen());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("you."));
        try {
            jda = builder.build();
        } catch (Exception e) {
            System.out.println("Brokey :(");
            System.exit(0);
            return;
        }

    }

    public static JDA getJda() {
        return jda;
    }

}
