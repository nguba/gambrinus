package me.nguba.gambrinus.cqrs.query;

import me.nguba.gambrinus.ddd.validation.Errors;

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
    private final QueryProcessor processor = new QueryProcessor();

    private final AtomicBoolean hasValidated = new AtomicBoolean(false);

    @Test
    void registerHandler()
    {
        assertThat(processor.supports(QueryProcessorTest.class)).isFalse();

        register();

        assertThat(processor.supports(QueryProcessorTest.class)).isTrue();
    }

    private void register()
    {
        processor.register(getClass(), this);
    }

    @Test
    void executeNoHandlerRegistered()
    {
        final UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                                                                     () -> processor.execute(this));

        assertThat(exception).hasMessageStartingWith("No handler registered for query: ");
    }

    @Test
    void runsValidation() throws Exception
    {
        register();

        processor.execute(this);

        assertThat(hasValidated.get()).isTrue();
    }

    @Test
    void returnsResult() throws Exception
    {

        register();

        final QueryProcessorTest execute = processor.execute(this);
        assertThat(execute).isInstanceOf(getClass());
    }

    @Override
    public void validate(final QueryProcessorTest query, final Errors errors)
    {
        hasValidated.getAndSet(true);
    }

    @Override
    public QueryProcessorTest run(final QueryProcessorTest query)
    {
        return this;
    }

    @Override
    public Optional<String> getResult()
    {
        return Optional.of("result");
    }
}
