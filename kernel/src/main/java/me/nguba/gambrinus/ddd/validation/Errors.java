package me.nguba.gambrinus.ddd.validation;

import me.nguba.gambrinus.ddd.ValueObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Errors implements ValueObject
{
    private final Set<Reason> errors = new HashSet<Reason>();

    public static Errors empty()
    {
        return new Errors();
    }

    public void verify() throws ValidationFailed
    {
        if (!errors.isEmpty()) {
            throw new ValidationFailed(this);
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((errors == null) ? 0 : errors.hashCode());
        return result;
    }

    public boolean has(final Reason reason)
    {
        return errors.contains(reason);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Errors other = (Errors) obj;
        if (errors == null) {
            if (other.errors != null) {
                return false;
            }
        } else if (!errors.equals(other.errors)) {
            return false;
        }
        return true;
    }

    public void add(final Reason reason)
    {
        errors.add(reason);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (Reason reason : errors) {
            builder.append(reason).append("\n");
        }
        return builder.toString();
    }

    public boolean hasErrors()
    {
        return !errors.isEmpty();
    }
}
