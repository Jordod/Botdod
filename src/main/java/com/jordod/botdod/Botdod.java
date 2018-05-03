package com.jordod.botdod;

import com.jordod.botdod.handlers.MessageHandler;
import com.jordod.botdod.handlers.ReadyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

public class Botdod {

    public static final Logger LOGGER = LoggerFactory.getLogger(Botdod.class);

    public static void main(String[] args) {
        IDiscordClient client = Botdod.createClient(args[0], true);
        EventDispatcher dispatcher = null;
        if (client != null) {
            dispatcher = client.getDispatcher();
            dispatcher.registerListener(new ReadyHandler());
            dispatcher.registerListener(new MessageHandler());
        }
    }

    private static IDiscordClient createClient(String token, boolean login) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        try {
            if (login) {
                return clientBuilder.login();
            } else {
                return clientBuilder.build();
            }
        } catch (DiscordException e) {
            e.getMessage();
            return null;
        }
    }

}
