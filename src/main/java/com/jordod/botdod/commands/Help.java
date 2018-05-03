package com.jordod.botdod.commands;

import com.jordod.botdod.handlers.MessageHandler;
import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Help extends Command {

    private final Map<String, Command> commands;

    public Help(String description) {
        super(description);
        commands = MessageHandler.getCommandMap();
    }

    @Override
    public void run(MentionEvent event, List<String> args) {
        EmbedBuilder builder = new EmbedBuilder();

        builder.withFooterText(LocalDateTime.now().format(formatter));
        builder.withColor(rand.nextInt(128) + 127, rand.nextInt(128) + 127, rand.nextInt(128) + 127);

        if (args.isEmpty()) {
            builder.withTitle("List of all commands");
            builder.withDescription("Type *@Botdod help **commandname*** for more info on a command");

            for (Map.Entry<String, Command> commandEntry : commands.entrySet()) {
                builder.appendDescription("\n" + commandEntry.getKey());
            }
        } else {
            if (commands.containsKey(args.get(0))) {
                final Command command = commands.get(args.get(0));
                builder.withTitle("Help for " + args.get(0));
                builder.withDescription(command.getDescription());
            } else {
                MessageUtils.sendMessage(event.getChannel(), "Command not found");
                return;
            }
        }

        MessageUtils.sendEmbed(event.getChannel(), builder.build());
    }
}
