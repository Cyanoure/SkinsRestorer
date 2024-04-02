/*
 * SkinsRestorer
 * Copyright (C) 2024  SkinsRestorer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.skinsrestorer.bukkit.mappings;

import org.bukkit.entity.Player;

public class MappingReflection {
    public static <S> S getHandle(Player player, Class<S> serverPlayerClass) {
        try {
            return serverPlayerClass.cast(player.getClass().getMethod("getHandle").invoke(player));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to get handle of player", e);
        }
    }
}
