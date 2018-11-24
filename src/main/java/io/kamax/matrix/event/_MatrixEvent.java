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
 *
 */

package io.kamax.matrix.event;

import com.google.gson.JsonObject;
import experimental.event.EventType;

public interface _MatrixEvent {

    /**
     * Returns the event type enum symbol, or {@link EventType#UNDEFINED} if
     *   the json type could not be mapped to an enum value.
     * @return event type
     */
    default EventType getEventType() {
        return EventType.fromJson(getType());
    }

    String getType();

    JsonObject getJson();

}
