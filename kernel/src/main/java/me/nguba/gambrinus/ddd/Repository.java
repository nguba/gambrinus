/*
 * Copyright (C) 2018 Nicolai P. Guba This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program
 * is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.nguba.gambrinus.ddd;

import java.util.Optional;

/**
 * A repository performs the tasks of an intermediary between the domain model layers and data
 * mapping, acting in a similar way to a set of domain objects in memory.
 * <p>
 * Client objects declaratively build queries and send them to the repositories for answers.
 * Conceptually, a repository encapsulates a set of objects stored in the database and operations
 * that can be performed on them, providing a way that is closer to the persistence layer.
 * <p>
 * Repositories, also, support the purpose of separating, clearly and in one direction, the
 * dependency between the work domain and the data allocation or mapping.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface Repository<I, A extends Aggregate<I>>
{
    Optional<I> create(A aggregate);

    void delete(I identifier);

    Optional<A> read(I identifier);

    void update(A aggregate);
}
