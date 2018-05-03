package com.jordod.botdod.commands;

import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;

import java.util.List;
import java.util.Random;

public class Roll extends Command {

    public Roll(String description) {
        super(description);
    }

    @Override
    public void run(MentionEvent event, List<String> args) {
        Random rand = new Random();
        int number = rand.nextInt(100);

        if (!args.isEmpty()) {
            number = rand.nextInt(Integer.valueOf(args.get(0)));
        }

        MessageUtils.sendMessage(event.getChannel(), event.getAuthor() + " rolled " + number);
    }
}
