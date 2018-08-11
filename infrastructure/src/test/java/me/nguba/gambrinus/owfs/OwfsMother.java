package me.nguba.gambrinus.owfs;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMother
{
    private OwfsMother()
    {
        super();
    }

    public static OwfsRoot root()
    {
        return OwfsRoot.of("src/test/resources/owfs");
    }

    public static OwfsAddress address()
    {
        return OwfsAddress.of("28.4BBB68080000");
    }
}
