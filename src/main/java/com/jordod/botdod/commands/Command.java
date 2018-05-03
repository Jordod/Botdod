package com.jordod.botdod.commands;

import sx.blah.discord.handle.impl.events.guild.channel.message.MentionEvent;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@SuppressWarnings("WeakerAccess")
public abstract class Command {
    static final Random rand = new Random();
    static final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.UK).withZone(ZoneId.systemDefault());
    private final String description;

    public Command(String description) {
        this.description = description;
    }

    public abstract void run(MentionEvent event, List<String> args);

    public String getDescription() {
        return description;
    }
}
