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

package io.kamax.matrix.json.event;

import com.google.gson.JsonObject;

import io.kamax.matrix.event._RoomPowerLevelsEvent;
import io.kamax.matrix.json.GsonUtil;
import io.kamax.matrix.json.MatrixJsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java8.util.Optional;

import experimental.om.PowerLevel;
import experimental.om.PowerLevel.Kind;

public class MatrixJsonRoomPowerLevelsEvent extends MatrixJsonRoomEvent implements _RoomPowerLevelsEvent {

    private Content content;

    public MatrixJsonRoomPowerLevelsEvent(JsonObject obj) {
        super(obj);

        content = new Content(getObj("content"));
    }

    
    private Optional<Double> getAsDouble(Kind kind) {
        return get(kind).map(PowerLevel::getLevel);
    }
    
    @Override
    public Map<PowerLevel.Kind, PowerLevel> getPowerLevels() {
        return content.simpleLevelMap;
    }
    
    @Override
    public Map<String, PowerLevel> getPowerLevelMap(PowerLevel.Kind kind) {
        if ( kind == null ) {
            return Collections.emptyMap();
        }
        switch (kind) {
            case EVENTS: return content.eventsMap;
            case USERS: return content.usersMap;
            case NOTIFICATIONS: return content.notificationsMap;
            default: return Collections.emptyMap();
        }
    }
    
    private Map<String, Double> mapToOld(Map<String, PowerLevel> map) {
        return map.values().stream().collect(
            Collectors.toMap(PowerLevel::getKey, PowerLevel::getLevel)
        );
    }

    @Override
    public Map<String, Double> getEvents() {
        return mapToOld(content.eventsMap);
    }

    @Override
    public Map<String, Double> getUsers() {
        return mapToOld(content.usersMap);
    }

    @Override
    public Optional<Double> getBan() {
        return getAsDouble(Kind.BAN);
    }

    @Override
    public Optional<Double> getEventsDefault() {
        return getAsDouble(Kind.EVENTS_DEFAULT);
    }

    @Override
    public Optional<Double> getInvite() {
        return getAsDouble(Kind.INVITE);
    }

    @Override
    public Optional<Double> getKick() {
        return getAsDouble(Kind.KICK);
    }

    @Override
    public Optional<Double> getRedact() {
        return getAsDouble(Kind.REDACT);
    }

    @Override
    public Optional<Double> getStateDefault() {
        return getAsDouble(Kind.STATE_DEFAULT);
    }

    @Override
    public Optional<Double> getUsersDefault() {
        return getAsDouble(Kind.USERS_DEFAULT);
    }

    private class Content extends MatrixJsonObject {

        private Map<String, Double> events = new HashMap<>();
        private Map<String, Double> users = new HashMap<>();
        private Map<PowerLevel.Kind,PowerLevel> simpleLevelMap;
        private Map<String, PowerLevel> eventsMap;
        private Map<String, PowerLevel> usersMap;
        private Map<String, PowerLevel> notificationsMap;

        Content(JsonObject obj) {
            super(obj);
            simpleLevelMap  = new HashMap<>();
            
            addPowerLevel(Kind.BAN,             "ban");
            addPowerLevel(Kind.EVENTS_DEFAULT,  "events_default");
            addPowerLevel(Kind.INVITE,          "invite");
            addPowerLevel(Kind.KICK,            "kick");
            addPowerLevel(Kind.REDACT,          "redact");
            addPowerLevel(Kind.STATE_DEFAULT,   "state_default");
            addPowerLevel(Kind.USERS_DEFAULT,   "users_default");
            
            this.eventsMap =
                GsonUtil.findObj(obj, "events").map(
                    it -> asPowerLevelMap(Kind.EVENTS, it))
                    .orElse(Collections.emptyMap());
            this.usersMap =
                GsonUtil.findObj(obj, "users").map(
                    it -> asPowerLevelMap(Kind.USERS, it))
                    .orElse(Collections.emptyMap());
            this.notificationsMap =
                GsonUtil.findObj(obj, "notifications").map(
                    it -> asPowerLevelMap(Kind.NOTIFICATIONS, it))
                    .orElse(Collections.emptyMap());
        }
        
        private Map<String, PowerLevel> asPowerLevelMap(Kind kind, JsonObject obj) {
            return obj
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                    it -> it.getKey(),
                    it -> PowerLevel.of(
                            kind, 
                            it.getKey(), 
                            it.getValue().getAsDouble()
                        )
                ));
        }
        
        private void addPowerLevel(PowerLevel.Kind kind, String name ) {
            Double value = getDoubleIfPresent(name);
            if ( value != null ) {
                simpleLevelMap.put(kind, PowerLevel.of(kind, value));
            }
        }


    }

}
