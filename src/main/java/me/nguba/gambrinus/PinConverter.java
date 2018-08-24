package me.nguba.gambrinus;

import me.nguba.gambrinus.raspberry.GPIO;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

/**
 *
 */
@ConfigurationPropertiesBinding
public class PinConverter implements Converter<String, GPIO>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);

    /*
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang. Object)
     */
    @Override
    public GPIO convert(final String source)
    {
        final Pin pin = RaspiPin.getPinByName(source);
        LOGGER.debug("Initialising {}", source);
        Assert.notNull(pin, "Unable to determing Raspberry Pin from: " + source);

        // GpioController gpio = GpioFactory.getInstance();
        // GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(pin, PinState.LOW);
        // outputPin.setShutdownOptions(true, PinState.LOW);
        return GPIO.from(pin);
    }
}
