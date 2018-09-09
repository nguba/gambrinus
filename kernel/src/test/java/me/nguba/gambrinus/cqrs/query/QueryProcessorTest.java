/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.cqrs.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

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

    @Override
    public Optional<String> getResult()
    {
        return Optional.of("result");
    }

    private QueryProcessorTest query() throws ValidationFailed
    {
        return QueryProcessor.query(this, this);
    }

    @Override
    public QueryProcessorTest query(final QueryProcessorTest query)
    {
        return this;
    }

    @Test
    void returnsResult() throws Exception
    {
        final QueryProcessorTest execute = query();
        assertThat(execute).isInstanceOf(getClass());
    }

    @Test
    void runsValidation() throws Exception
    {
        query();

        assertThat(hasValidated.get()).isTrue();
    }

    @Override
    public void validate(final QueryProcessorTest query, final Errors errors)
    {
        hasValidated.getAndSet(true);
    }
}
