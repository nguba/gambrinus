package me.nguba.gambrinus.cqrs.query;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class QueryProcessor
{
    private final ConcurrentHashMap<Object, QueryHandler<? extends Query, ? extends Result<?>>> handlers = new ConcurrentHashMap<>();

    public <H extends QueryHandler<? extends Query, ? extends Result<?>>> void register(final Class<? extends Query> key,
                                                                                        final H handler)
    {
        handlers.put(key, handler);
    }

    public boolean supports(final Class<? extends Query> key)
    {
        return handlers.get(key) != null;
    }

    @SuppressWarnings("unchecked")
    public <Q extends Query, R extends Result<?>> R process(final Q query) throws ValidationFailed
    {
        final QueryHandler<Query, ? extends Result<?>> handler = (QueryHandler<Query, ? extends Result<?>>) handlers
                .get(query.getClass());

        if (handler == null) {
            throw new UnsupportedOperationException(String
                    .format("No handler registered for query: %s", query.getClass()));
        }

        final Errors errors = Errors.empty();

        handler.validate(query, errors);

        errors.verify();

        return (R) handler.run(query);
    }
}
