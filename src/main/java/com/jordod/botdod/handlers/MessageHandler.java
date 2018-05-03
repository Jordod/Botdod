package com.jordod.botdod.handlers;

import com.jordod.botdod.commands.Command;
import com.jordod.botdod.commands.Roll;
import com.jordod.botdod.commands.UserInfo;
import com.jordod.botdod.utils.MessageUtils;
import com.vdurmont.emoji.EmojiManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.*;

public class MessageHandler {

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("ping", (event, args) -> {
            MessageUtils.sendMessage(event.getChannel(), "pong");
        });

        commandMap.put("roll", new Roll());
        commandMap.put("userinfo", new UserInfo());
    }

    @EventSubscriber
    public void onMention(MentionEvent event) {
        String[] args = event.getMessage().getContent().split(" ");

        if (args.length < 2) {
            IMessage message = event.getMessage();
            MessageUtils.react(message, EmojiManager.getForAlias("vulcan_salute"));
            return;
        }

        String commandStr = args[1];

        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(1);
        argsList.remove(0);

        if (commandMap.containsKey(commandStr)) {
            commandMap.get(commandStr).run(event, argsList);
        } else {
            MessageUtils.sendMessage(event.getChannel(), "Command not found");
        }
    }
}
