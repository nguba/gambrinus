package me.nguba.gambrinus.cqrs.query;

import me.nguba.gambrinus.ddd.validation.Errors;

public interface QueryHandler<Q extends Query, R extends Result<?>>
{
    void validate(Q query, Errors errors);

    R run(Q query);
}
