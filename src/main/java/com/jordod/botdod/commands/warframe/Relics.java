package com.jordod.botdod.commands.warframe;

import com.jordod.botdod.commands.Command;
import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Relics extends Command {

    public Relics(String description) {
        super(description);
    }

    @Override
    public void run(MentionEvent event, List<String> args) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.withColor(rand.nextInt(128) + 127, rand.nextInt(128) + 127, rand.nextInt(128) + 127);
        builder.withTitle("Relic Farming Locations (Click to Zoom)");
        builder.withFooterText("Credit to Dave#5218 " + LocalDateTime.now().format(formatter));
        builder.withImage("http://i.imgur.com/oKeHR09.png");

        MessageUtils.sendEmbed(event.getChannel(), builder.build());

    }
}
