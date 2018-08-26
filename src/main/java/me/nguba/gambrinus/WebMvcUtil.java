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
                                               String path,
                                               Object... arguments)
  {
    return ResponseEntity.created(builder.path(path)
        .buildAndExpand(arguments).toUri()).build();
  }
}
