package me.nguba.gambrinus;

import org.springframework.boot.context.properties.ConfigurationProperties;

import me.nguba.gambrinus.raspberry.GPIO;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ConfigurationProperties(prefix = "gambrinus.raspberry.pin")
public class RaspberryPinOptions
{
  private GPIO mt;

  private GPIO hlt;

  private GPIO bk;

  public GPIO getMt()
  {
    return mt;
  }

  public void setMt(final GPIO mt)
  {
    this.mt = mt;
  }

  public GPIO getHlt()
  {
    return hlt;
  }

  public void setHlt(final GPIO hlt)
  {
    this.hlt = hlt;
  }

  public GPIO getBk()
  {
    return bk;
  }

  public void setBk(final GPIO bk)
  {
    this.bk = bk;
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("RaspberryPinOptions [mt=").append(mt).append(", hlt=").append(hlt)
        .append(", bk=").append(bk).append("]");
    return builder.toString();
  }
}
