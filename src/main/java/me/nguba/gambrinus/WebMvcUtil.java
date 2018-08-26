/**
 * 
 */
package me.nguba.gambrinus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author nguba
 *
 */
public enum WebMvcUtil
{
  ;
  public static ResponseEntity<Object> created(final UriComponentsBuilder builder,
                                               Object... arguments)
  {
    return ResponseEntity.created(builder.buildAndExpand(arguments).toUri()).build();
  }
}
