package com.cooleg.bot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;


public class ConfgLoader {

    File config = new File("config.txt");
    Path path = Paths.get("config.txt");
    InputStream internalConfig = getClass().getResourceAsStream("/config.txt");

    public void loadConfig() throws IOException, InterruptedException {
        if (!config.exists()) {
            Files.copy(internalConfig, path, StandardCopyOption.REPLACE_EXISTING);
            System.exit(0);
        } else {
            Properties props = new Properties();
            FileReader reader = new FileReader(config);
            props.load(reader);
            try {
                Bot.token = (String) props.get("token");
                Bot.channelnames = (String) props.get("channelnames");
                Bot.rolenames = (String) props.get("rolenames");
                Bot.message = (String) props.get("message");
//                Bot.nukeroles = (boolean) props.get("nukeroles");
//                Bot.nukeroles = (boolean) props.get("nukechannels");
            } catch (Exception e) {
                wait(5000);
                System.exit(0);
            }

        }
        return;
    }

}
