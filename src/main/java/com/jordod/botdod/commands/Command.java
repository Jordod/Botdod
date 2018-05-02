package com.jordod.botdod.commands;

import java.util.List;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;

public interface Command {

    public void run(MentionEvent event, List<String> args);

}
