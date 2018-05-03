package com.jordod.botdod.commands;

import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.RequestBuffer;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Clear extends Command {

    public Clear(String description) {
        super(description);
    }

    /**
     * Requires MANAGE_MESSAGES
     */

    @Override
    public void run(MentionEvent event, List<String> args) {
        EnumSet<Permissions> userPerms = event.getAuthor().getPermissionsForGuild(event.getGuild());
        if(userPerms.contains(Permissions.MANAGE_MESSAGES)) {
            RequestBuffer.request(() -> {
                event.getChannel().bulkDelete(Arrays.asList(event.getChannel().getMessageHistory(args.isEmpty() ? 101 : Integer.valueOf(args.get(0)) + 1).asArray()));
            });
        } else {
            MessageUtils.sendMessage(event.getChannel(), "You do not have the required permissions to clear messages");
        }
    }
}
