/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import me.nguba.gambrinus.raspberry.GPIO;

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
