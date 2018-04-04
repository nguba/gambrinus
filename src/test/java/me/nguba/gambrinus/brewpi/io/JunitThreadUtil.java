package me.nguba.gambrinus.brewpi.io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class JunitThreadUtil {

  private JunitThreadUtil() {
  }

  static void delay(long millis) throws InterruptedException {
    final long startTime = System.nanoTime();
    final long ns = millis * 1000 * 1000;
    for (;;) {
      if (millis > 0L) {
        Thread.sleep(millis);
      } else {
        Thread.yield();
      }
      final long d = ns - (System.nanoTime() - startTime);
      if (d > 0L) {
        millis = d / (1000 * 1000);
      } else {
        break;
      }
    }
  }

  static void assertThreadStaysAlive(final Thread thread) {
    try {
      delay(50);
      assertTrue(thread.isAlive());
    } catch (@SuppressWarnings("unused") final InterruptedException ie) {
      fail("Unexpected InterruptedException");
    }
  }

}
