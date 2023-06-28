/*
 * SkinsRestorer
 *
 * Copyright (C) 2023 SkinsRestorer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package net.skinsrestorer.velocity.wrapper;

import ch.jalu.configme.SettingsManager;
import com.google.gson.JsonElement;
import com.velocitypowered.api.command.CommandSource;
import lombok.experimental.SuperBuilder;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.skinsrestorer.shared.config.MessageConfig;
import net.skinsrestorer.shared.subjects.SRCommandSender;
import net.skinsrestorer.shared.subjects.messages.Message;
import net.skinsrestorer.shared.subjects.messages.SkinsRestorerLocale;
import net.skinsrestorer.shared.subjects.permissions.Permission;

import java.util.Locale;

@SuperBuilder
public class WrapperCommandSender implements SRCommandSender {
    private final SettingsManager settings;
    private final SkinsRestorerLocale locale;
    private final CommandSource sender;
    private final GsonComponentSerializer serializer = GsonComponentSerializer.gson();

    @Override
    public Locale getLocale() {
        return settings.getProperty(MessageConfig.LOCALE);
    }

    @Override
    public void sendMessage(JsonElement message) {
        sender.sendMessage(serializer.deserializeFromTree(message));
    }

    @Override
    public void sendMessage(Message key, Object... args) {
        sendMessage(locale.getMessage(this, key, args));
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return permission.checkPermission(settings, sender::hasPermission);
    }
}