package me.nguba.gambrinus.cqrs.query;

import me.nguba.gambrinus.cqrs.CqrsUtil;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class QueryProcessor
{
  private QueryProcessor()
  {
    super();
  }

  public static <Q extends Query, R extends Result<?>> R query(final Q query,
                                                               final QueryHandler<Q, R> handler)
      throws ValidationFailed
  {
    CqrsUtil.notNull(handler, "Handler cannot be null");

    CqrsUtil.notNull(query, "Query cannot be null");

    validate(query, handler);

    return handler.query(query);
  }

  private static <Q extends Query, R extends Result<?>> void validate(final Q query,
                                                                      final QueryHandler<Q, R> handler)
      throws ValidationFailed
  {
    final Errors errors = Errors.empty();

    handler.validate(query, errors);

    errors.verify();
  }

}
