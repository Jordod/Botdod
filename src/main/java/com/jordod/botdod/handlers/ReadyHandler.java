package com.jordod.botdod.handlers;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import static com.jordod.botdod.Botdod.LOGGER;

public class ReadyHandler {

    @SuppressWarnings("unused")
    @EventSubscriber
    public void onReady(ReadyEvent event) {
        LOGGER.info("Bot Ready");
    }

}
