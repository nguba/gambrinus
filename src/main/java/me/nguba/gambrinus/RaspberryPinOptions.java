package me.nguba.gambrinus;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ConfigurationProperties(prefix = "gambrinus.raspberry.pin")
public class RaspberryPinOptions
{
    private String mt;

    private String hlt;

    private String bk;

    public void setMt(final String mt)
    {
        this.mt = mt;
    }

    public void setHlt(final String hlt)
    {
        this.hlt = hlt;
    }

    public void setBk(final String bk)
    {
        this.bk = bk;
    }

    public String getMt()
    {
        return mt;
    }

    public String getHlt()
    {
        return hlt;
    }

    public String getBk()
    {
        return bk;
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
