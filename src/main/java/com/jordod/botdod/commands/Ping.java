package com.jordod.botdod.commands;

import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;

import java.util.List;

public class Ping extends Command {

    public Ping(String description) {
        super(description);
    }

    @Override
    public void run(MentionEvent event, List<String> args) {
        MessageUtils.sendMessage(event.getChannel(), "pong");
    }
}
