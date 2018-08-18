package me.nguba.gambrinus.ddd.validation;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Reason extends SingleValueObject<String>{

    private Reason(String value)
    {
        super(value);
    }
    
    public static final Reason from(String reason) {
        return new Reason(reason);
    }
}