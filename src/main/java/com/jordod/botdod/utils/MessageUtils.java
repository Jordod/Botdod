package com.jordod.botdod.utils;

import com.vdurmont.emoji.Emoji;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

import static com.jordod.botdod.Botdod.LOGGER;

public class MessageUtils {

    public static void sendMessage(IChannel channel, String message) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                LOGGER.error("Message could not be sent with error: " + e.getMessage());
            }
        });
    }

    public static void sendEmbed(IChannel channel, EmbedObject emb) {
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(emb);
            } catch (DiscordException e) {
                LOGGER.error("Message could not be sent with error: " + e.getMessage());
            }
        });
    }

    public static void react(IMessage message, Emoji emoji) {
        RequestBuffer.request(() -> message.addReaction(emoji));
    }
}
