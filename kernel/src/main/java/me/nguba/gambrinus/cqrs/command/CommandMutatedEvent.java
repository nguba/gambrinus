package me.nguba.gambrinus.cqrs.command;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandMutatedEvent implements MutatedEvent<Command>
{
    private final Command entity;

    private CommandMutatedEvent(final Command entity)
    {
        this.entity = entity;
    }

    public static CommandMutatedEvent from(final Command entity)
    {
        return new CommandMutatedEvent(entity);
    }

    @Override
    public Command getEntity()
    {
        return entity;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("CommandMutatedEvent [entity=").append(entity).append("]");
        return builder.toString();
    }

}
