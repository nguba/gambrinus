package me.nguba.gambrinus.cqrs;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum CqrsUtil
{
    ;

    public static void notNull(final Object object, final String msg)
    {
        Optional.ofNullable(object)
                .orElseThrow(() -> new UnsupportedOperationException(msg));
    }

}
