package com.jordod.botdod.commands;

import com.jordod.botdod.utils.MessageUtils;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class UserInfo implements Command {

    private final Random rand = new Random();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.UK).withZone(ZoneId.systemDefault());

    @Override
    public void run(MentionEvent event, List<String> args) {
        IUser user = null;

        if (args.isEmpty()) {
            user = event.getAuthor();
        } else {
            if (args.get(0).startsWith("<@")) {
                for (IShard shard : event.getClient().getShards()) {
                    user = shard.fetchUser(Long.valueOf(args.get(0).substring(2, args.get(0).length() - 1)));
                }
            }
        }

        if (!(user == null)) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.withColor(rand.nextInt(128) + 127, rand.nextInt(128) + 127, rand.nextInt(128) + 127);

            builder.withThumbnail(user.getAvatarURL());
            builder.withTitle(user.getName() + "#" + user.getDiscriminator());

            builder.appendField("Server Nickname", user.getDisplayName(event.getGuild()), true);
            builder.appendField("Discord Join Date", formatter.format(user.getCreationDate()), true);
            
            builder.withFooterText(LocalDateTime.now().format(formatter));

            MessageUtils.sendEmbed(event.getChannel(), builder.build());
        } else {
            MessageUtils.sendMessage(event.getChannel(), "Can't find user");
        }

    }

}
