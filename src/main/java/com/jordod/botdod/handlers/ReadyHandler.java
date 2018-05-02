package com.jordod.botdod.handlers;

import static com.jordod.botdod.Botdod.LOGGER;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class ReadyHandler {

    @EventSubscriber
    public void onReady(ReadyEvent event) {
        LOGGER.info("Bot Ready");
    }

}
