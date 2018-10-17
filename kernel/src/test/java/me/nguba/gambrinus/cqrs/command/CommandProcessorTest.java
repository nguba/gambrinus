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
package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest implements Command, CommandHandler<CommandProcessorTest>
{
    private final AtomicBoolean executed = new AtomicBoolean();

    private final AtomicBoolean validated = new AtomicBoolean();

    @Override
    public void changeStateFor(final CommandProcessorTest command)
    {
        executed.getAndSet(true);
    }

    @Test
    @DisplayName("Executes registered mutator")
    void execute() throws Exception
    {
        assertFalse(executed.get());

        process();

        assertTrue(executed.get());
    }

    /**
     * @throws ValidationFailed
     */
    private void process() throws ValidationFailed
    {
        CommandProcessor.from(this, this).mutate();
    }

    @Test
    @DisplayName("Error on null command")
    void processNullCommand() throws Exception
    {
        assertFalse(executed.get());

        assertThrows(UnsupportedOperationException.class,
                     () -> CommandProcessor.from(null, this).mutate());
    }

    @Test
    @DisplayName("Error on null mutator")
    void processNullMutator() throws Exception
    {
        assertFalse(executed.get());

        assertThrows(UnsupportedOperationException.class,
                     () -> CommandProcessor.from(this, null).mutate());
    }

    @Override
    public void validate(final CommandProcessorTest command, final Errors errors)
    {
        validated.getAndSet(true);
    }

    @Test
    @DisplayName("Executes validator when requested")
    void validator() throws Exception
    {
        assertFalse(validated.get());

        process();

        assertTrue(validated.get());
    }
}
