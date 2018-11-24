package experimental.event;

import java.util.Map;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * Symbolic event type {@link io.kamax.matrix.event._MatrixEvent}.
 * TODO add missing event types
 */
public enum EventType {

    /**
     * Event type {@code org.matrix.room.preview_urls}.
     */
    ORG_MATRIX_ROOM_PREVIEW_URLS
                        ("org.matrix.room.preview_urls"),
    
    /**
     * Event type {@code m.room.message}.
     */
    M_ROOM_MESSAGE      ("m.room.message"),
    
    /**
     * Event type {@code m.room.message.feedback}.
     */
    M_ROOM_MESSAGE_FEEDBACK("m.room.message.feedback"),
    
    /**
     * Event type {@code m.room.name}.
     */
    M_ROOM_NAME         ("m.room.name"),
    
    /**
     * Event type {@code m.room.member}.
     */
    M_ROOM_MEMBER       ("m.room.member"),
    
    /**
     * Event type {@code m.room.create}.
     */
    M_ROOM_CREATE       ("m.room.create"),
    
    /**
     * Event type {@code m.room.topic}.
     */
    M_ROOM_TOPIC        ("m.room.topic"),
    
    /**
     * Event type {@code m.room.power_levels}.
     */
    M_ROOM_POWER_LEVELS ("m.room.power_levels"),
    
    /**
     * Event type {@code m.room.aliases}.
     */
    M_ROOM_ALIASES      ("m.room.aliases"),
    
    /**
     * Event type {@code m.room.join_rule}.
     */
    M_ROOM_JOIN_RULES   ("m.room.join_rules"),
    
    /**
     * Event type {@code m.room.canonical_alias}.
     */
    M_ROOM_CANONICAL_ALIAS
                        ("m.room.canonical_alias"),
                        
    /**
     * Event type {@code m.room.history_visibility}.
     */
    M_ROOM_HISTORY_VISIBILITY
                        ("m.room.history_visibility"),
                        
    /**
     * Event type {@code m.room.avatar}.
     */
    M_ROOM_AVATAR       ("m.room.avatar"),
    
    /**
     * Event type {@code m.room.pinned_events}.
     */
    M_ROOM_PINNED_EVENTS
                        ("m.room.pinned_events"),
                        
    /**
     * Event type {@code m.room.guest_access}.
     */
    M_ROOM_GUEST_ACCESS
                        ("m.room.guest_access"),

    /*
     * Inofficial sync event type.
     */
    //SYNC("<sync>"),
    
    /**
     * Used if the json type string can't be mapped to an enum value.
     * The event type is undefined by this enum, but can be valid.
     */
    UNDEFINED("<undefined>")
    ;
    
    /**
     * Maps the json type to an enum value.
     */
    private static final Map<String, EventType> jsonToEnumMap = new HashMap<>();
    
    /**
     * Initializer
     */
    static {
        initializeJsonToEnumMap();
    }
    
    /**
     * Initialize the {@code jsonToEnumMap}.
     */
    private static void initializeJsonToEnumMap() {
        for (EventType type: EnumSet.allOf(EventType.class)) {
            if (type.isKnown()) {
                jsonToEnumMap.put(type.getEventName(), type);
            }
        }
    }

    private String name;
    
    /**
     * Creates the event type enum with the 
     */
    private EventType(String name) {
        this.name = name;
    }
    
    /**
     * Returns {@code true} if this enum has a corresponding json event type, otherwise {@code false}.
     * @return {@code true} if this enum has a corresponding json event type, otherwise {@code false}.
     */
    public boolean isKnown() {
        return (this != UNDEFINED);
    }
    
    /**
     * Returns the enum value corresponding to the json type of a matrix message, or {@link #UNDEFINED}
     *  if there is no corresponding enum or the specified <em>type</em> is {@code null}.
     * 
     * @param type
     *      the type, can be {@code null}
     * 
     * @return the enum corresponding to the matrix message type
     */
    public static EventType fromJson(String type) {
        return jsonToEnumMap.getOrDefault​(type, UNDEFINED);
    }
    
    /**
     * Returns the name of the event.
     * <p><strong>Note</strong>: 
     *    The event name is a valid matrix message type name if and only if
     *    {@link #isKnown()} returns {@code true}.</p>
     * @return the name of the event
     */
    public String getEventName() {
        return name;
    }


}
