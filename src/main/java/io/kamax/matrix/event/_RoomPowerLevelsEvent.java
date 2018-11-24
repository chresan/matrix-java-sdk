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

package io.kamax.matrix.event;

import java.util.Map;
import java8.util.Optional;
import experimental.om.PowerLevel;
import java.util.Map;

public interface _RoomPowerLevelsEvent extends _RoomEvent {


    // -------------------------------------------------------
    // NEW interface:
    //    simplification 
    // -------------------------------------------------------

    /**
     * Returns the power level (experimental).
     * 
     * @param kind
     *      Kind of power level. Power levels where {@link PowerLevel.Kind#hasKey()}
     *      is {@code true} return an empty optional.
     *
     * @return the power level, or an empty optional 
     *   if the power level was not persent in the message, the <em>kind</em> is null 
     *   or it's {@link PowerLevel.Kind#hasKey()} property is {@code true}.
     */
    default Optional<PowerLevel> get(PowerLevel.Kind kind) {
        return Optional.ofNullable(getPowerLevels().get(kind));
    }
    
    /**
     * Returns a map of simple power levels (experimental).
     * The map contains only power levels that have no key.
     * @return power levels
     */
    Map<PowerLevel.Kind, PowerLevel> getPowerLevels();
    
    /**
     * Returns a map of power levels with key (experimental).
     * 
     * @param kind 
     *      Kind of power level.
     *
     * @return power levels or an empty map if the kind has no key,
     *      was not present in the message or is {@code null}
     */
    Map<String, PowerLevel> getPowerLevelMap(PowerLevel.Kind kind);

    
    
    // -------------------------------------------------------
    // OLD interface
    // -------------------------------------------------------
    
    Optional<Double> getBan();

    Map<String, Double> getEvents();

    Optional<Double> getEventsDefault();

    Optional<Double> getInvite();

    Optional<Double> getKick();

    Optional<Double> getRedact();

    Optional<Double> getStateDefault();

    Map<String, Double> getUsers();

    Optional<Double> getUsersDefault();

}
