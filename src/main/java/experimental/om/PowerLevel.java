package experimental.om;

/**
 * Room power level value.
 * @see io.kamax.matrix.event._RoomPowerLevelsEvent
 */
public class PowerLevel {

    /**
     * The power level kind.
     */
    private Kind kind;
    
    /**
     * The power level value.
     */
    private double level;
    
    /**
     * Creates a new power level.
     * 
     * @param kind 
     *      the <em>kind</em> of power level
     * 
     * @param level
     *      the assigned power level
     *
     * @throws NullPointerException 
     *      if <em>kind</em> is {@code null}
     */
    protected PowerLevel(Kind kind, double level) {
        if ( kind == null ) {
            throw new NullPointerException("expected non-null: kind");
        }
        this.kind = kind;
        this.level = level;
    }
    
    /**
     * Creates a new power level.
     * 
     * @param kind 
     *      the <em>kind</em> of power level
     * 
     * @param level
     *      the assigned power level
     *
     * @throws NullPointerException 
     *      if <em>kind</em> is {@code null}
     * 
     * @return the power level
     */
    public static final PowerLevel of(Kind kind, double level) {
        if ( kind.hasKey() ) {
            throw new IllegalArgumentException("power-level requires a key: "+kind);
        }
        return new PowerLevel(kind, level);
    }
    
    /**
     * Creates a new power level.
     * 
     * @param kind 
     *      the <em>kind</em> of power level
     * 
     * @param key 
     *      the <em>key</em> of key 
     * 
     * @param level
     *      the assigned power level
     *
     * @throws NullPointerException 
     *      if <em>kind</em> or <em>key</em> is {@code null}
     *      
     * @return the power level
     */
    public static final PowerLevel of(Kind kind, String key, double level) {
        if ( kind == null ) {
            throw new NullPointerException("expected non-null: kind");
        }
        if ( key == null ) {
            throw new NullPointerException("expected non-null: key");
        }
        if ( ! kind.hasKey() ) {
            throw new IllegalArgumentException("power-level does not support the key property: "+kind);
        }
        return new PowerLevelWithKey(kind, key, level);
    }
    
    /**
     * Returns the optional power level key, or an empty string if this kind of power level has no key.
     *
     * <p>The key is defined for the following power level kind</p>:
     * <ul>
     *   <li>{@link Kind#NOTIFICATIONS}: notifications key</li>
     *   <li>{@link Kind#EVENTS}: key is a event type</li>
     *   <li>{@link Kind#USERS}:  key is a user_id</li>
     * </ul>
     *
     * @return the key
     */
    public String getKey() {
        return "";
    }
    
    /**
     * Returns the assigned power level.
     * @return level
     */
    public double getLevel() {
        return level;
    }
    
    /**
     * Returns the power level kind.
     * @return power level kind
     */
    public Kind getKind() {
        return kind;
    }
    
    /**
     * Returns a string representation.
     * @return string representation
     */
    @Override
    public String toString() {
        return getClass().getSimpleName()+"["
            +kind+"="+level
        +"]";
    }
    
    /**
     * Room power level with key.
     */
    public static class PowerLevelWithKey extends PowerLevel {
    
        private String key;
    
        protected PowerLevelWithKey(Kind kind, String key, double level) {
            super(kind, level);
            this.key = key != null ? key : "";
        }
        
        @Override
        public String getKey() {
            return key;
        }
        
        @Override
        public String toString() {
            return getClass().getSimpleName()+"["
                +getKind()+"=("+getKey()+","+getLevel()+")"
            +"]";
        }
        
    }
    
    /**
     * Power level kind.
     */
    public static enum Kind {

        BAN,
        INVITE,
        KICK,
        REDACT,
        EVENTS(true),
        EVENTS_DEFAULT,
        STATE_DEFAULT,
        USERS(true),
        USERS_DEFAULT,
        NOTIFICATIONS(true),
        
        ;

        private boolean hasKey;
        
        private Kind() {    
            this(false);
        }
        
        private Kind(boolean hasKey) {    
            this.hasKey = hasKey;
        }
        
        public boolean hasKey() {
            return hasKey;
        }
        
    }

}
