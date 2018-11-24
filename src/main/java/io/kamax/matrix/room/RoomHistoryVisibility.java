/*
 * matrix-java-sdk - Matrix Client SDK for Java
 * Copyright (C) 2018 Arne Augenstein
 *
 * https://www.kamax.io/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package io.kamax.matrix.room;

import java.util.EnumSet;

/**
 * Room history visibility settings available in the specification.
 */
public enum RoomHistoryVisibility {

    Invited("invited"),

    Joined("joined"),

    Shared("shared"),

    WorldReadable("world_readable"),
    
    /**
     * Indicates that the value could not be mapped to an enum value.
     */
    Unknown("<unknown>");

    private String id;

    RoomHistoryVisibility(String id) {
        this.id = id;
    }

    /**
     * Get the Matrix value for this setting.
     * 
     * @return the value.
     */
    public String get() {
        return id;
    }

    public boolean is(String id) {
        return this.id.contentEquals(id);
    }
    
    
    public static RoomHistoryVisibility fromJson(String value) {
        if ( value == null ) {
            return Unknown;
        }
        for (RoomHistoryVisibility eval: EnumSet.allOf(RoomHistoryVisibility.class)) {
            if ( eval != Unknown && eval.get().equals(value) ) {
                return eval;
            }
        }
        return Unknown;
    }
    
}
