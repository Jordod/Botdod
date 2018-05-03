package com.jordod.botdod.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;

import java.util.List;

public interface Command {

    void run(MentionEvent event, List<String> args);

}
