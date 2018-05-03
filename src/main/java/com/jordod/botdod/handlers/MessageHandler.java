package com.jordod.botdod.handlers;

import com.jordod.botdod.commands.*;
import com.jordod.botdod.commands.warframe.Relics;
import com.jordod.botdod.utils.MessageUtils;
import com.vdurmont.emoji.EmojiManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.*;

public class MessageHandler {

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("ping", new Ping("Used to check if the bot is online"));
        commandMap.put("roll", new Roll("Rolls a random number use *roll **number*** to increase roll range"));
        commandMap.put("userinfo", new UserInfo("Get general info about a user"));
        commandMap.put("help", new Help("Get a list of all commands or info about a specific command"));
        commandMap.put("relics", new Relics("Image of Relic farming locations"));
    }

    public static Map<String, Command> getCommandMap() {
        return commandMap;
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
