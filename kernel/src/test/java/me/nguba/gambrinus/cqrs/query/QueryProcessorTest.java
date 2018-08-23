package me.nguba.gambrinus.cqrs.query;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class QueryProcessorTest
        implements Query, Result<String>, QueryHandler<QueryProcessorTest, QueryProcessorTest>
{
    private final AtomicBoolean hasValidated = new AtomicBoolean(false);

    @Test
    void executeNullHandler()
    {
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                                                                     () -> QueryProcessor
                                                                             .query(this, null));

        assertThat(exception).hasMessage("Handler cannot be null");
    }
    
    @Test
    void executeNullQuery()
    {
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                                                                     () -> QueryProcessor
                                                                             .query(null, this));

        assertThat(exception).hasMessage("Query cannot be null");
    }

    @Test
    void runsValidation() throws Exception
    {
        query();

        assertThat(hasValidated.get()).isTrue();
    }

    private QueryProcessorTest query() throws ValidationFailed
    {
        return QueryProcessor.query(this, this);
    }

    @Test
    void returnsResult() throws Exception
    {
        final QueryProcessorTest execute = query();
        assertThat(execute).isInstanceOf(getClass());
    }

    @Override
    public void validate(final QueryProcessorTest query, final Errors errors)
    {
        hasValidated.getAndSet(true);
    }

    @Override
    public QueryProcessorTest query(final QueryProcessorTest query)
    {
        return this;
    }

    @Override
    public Optional<String> getResult()
    {
        return Optional.of("result");
    }
}
