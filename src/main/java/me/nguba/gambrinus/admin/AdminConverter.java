package me.nguba.gambrinus.admin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminConverter
{
    private final ConfigurableConversionService conversionsService;

    public AdminConverter(final ConfigurableConversionService conversionsService)
    {
        this.conversionsService = conversionsService;
    }

    public void addConverter(final Converter<?, ?> converter)
    {
        conversionsService.addConverter(converter);
    }

    public boolean canConvert(final Class<?> sourceType, final Class<?> targetType)
    {
        return conversionsService.canConvert(sourceType, targetType);
    }

    public <T> T convert(final Object source, final Class<T> targetType)
    {
        return conversionsService.convert(source, targetType);
    }

}
