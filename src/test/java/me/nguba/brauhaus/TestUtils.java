package me.nguba.brauhaus;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class TestUtils {

  public static final String readFile(final String path) throws IOException {

    final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    try (InputStream stream = classloader.getResourceAsStream(path)) {
      final StringWriter writer = new StringWriter();
      IOUtils.copy(stream, writer, StandardCharsets.UTF_8);
      return writer.toString();
    }
  }
}
