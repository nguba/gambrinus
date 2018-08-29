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
package me.nguba.gambrinus.process;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TemperatureTest
{

    @Test
    @DisplayName("Celsius to Celsius")
    void convertCelsiusToCelsius()
    {
        assertThat(Temperature.celsius(103.23).toCelsius()).isEqualTo(Temperature.celsius(103.23));
    }

    @Test
    @DisplayName("Celsius to Farenheit")
    void convertCelsiusToFarenheit()
    {
        assertThat(Temperature.celsius(65.32).toFarenheit())
                .isEqualTo(Temperature.farenheit(149.57));
    }

    @Test
    @DisplayName("Celsius to Kelvin")
    void convertCelsiusToKelvin()
    {
        assertThat(Temperature.celsius(103.23).toKelvin()).isEqualTo(Temperature.kelvin(376.38));
    }

    @Test
    @DisplayName("Farenheit to Celsius")
    void convertFarenheitToCelsius()
    {
        assertThat(Temperature.farenheit(103.23).toCelsius()).isEqualTo(Temperature.celsius(39.57));
    }

    @Test
    @DisplayName("Farenheit to Kelvin")
    void convertFarenheitToKelvin()
    {
        assertThat(Temperature.farenheit(103.23).toKelvin()).isEqualTo(Temperature.kelvin(312.72));
    }

    @Test
    @DisplayName("Farenheit to Farenheit")
    void convertFarenheitToFarenheit()
    {
        assertThat(Temperature.farenheit(103.23).toFarenheit())
                .isEqualTo(Temperature.farenheit(103.23));
    }

    @Test
    @DisplayName("Kelvin to Celsius")
    void convertKelvinToCelsius()
    {
        assertThat(Temperature.kelvin(103.23).toCelsius()).isEqualTo(Temperature.celsius(-169.92));
    }

    @Test
    @DisplayName("Kelvin to Farenheit")
    void convertKelvinToFarenheit()
    {
        assertThat(Temperature.kelvin(103.23).toFarenheit())
                .isEqualTo(Temperature.farenheit(-273.86));
    }

    @Test
    @DisplayName("Kelvin to Kelvin")
    void convertKelvinToKelvin()
    {
        assertThat(Temperature.kelvin(103.23).toKelvin()).isEqualTo(Temperature.kelvin(103.23));
    }

    @Test
    @DisplayName("0 Celsius to Celsius")
    void convertZeroCelsiusToCelsius()
    {
        assertThat(Temperature.celsius(0).toCelsius()).isEqualTo(Temperature.celsius(0));
    }

    @Test
    @DisplayName("0 Celsius to Farenheit")
    void convertZeroCelsiusToFarenheit()
    {
        assertThat(Temperature.celsius(0).toFarenheit()).isEqualTo(Temperature.farenheit(32));
    }

    @Test
    @DisplayName("0 Farenheit to Celsius")
    void convertZeroFarenheitToCelsius()
    {
        assertThat(Temperature.farenheit(0).toCelsius()).isEqualTo(Temperature.celsius(-17.78));
    }

    @Test
    @DisplayName("0 Farenheit to Kelvin")
    void convertZeroFarenheitToKelvin()
    {
        assertThat(Temperature.farenheit(0).toKelvin()).isEqualTo(Temperature.kelvin(255.37));
    }

    @Test
    @DisplayName("0 Kelvin to Celsius")
    void convertZeroKelvinToCelsius()
    {
        assertThat(Temperature.kelvin(0).toCelsius()).isEqualTo(Temperature.celsius(-273.15));
    }

    @Test
    @DisplayName("0 Kelvin to Farenheit")
    void convertZeroKelvinToFarenheit()
    {
        assertThat(Temperature.kelvin(0).toFarenheit()).isEqualTo(Temperature.farenheit(-459.67));
    }

    @Test
    @DisplayName("0 Kelvin to Kelvin")
    void convertZeroKelvinToKelvin()
    {
        assertThat(Temperature.kelvin(0).toKelvin()).isEqualTo(Temperature.kelvin(0));
    }

    @Test
    void equalsContract()
    {
        EqualsVerifier.forClass(Temperature.class).verify();
    }

    @Test
    @DisplayName("Celsius string format")
    void toString_Celsius()
    {
        assertThat(Temperature.celsius(20.5).toString()).isEqualTo("20.5 (C)");
    }

    @Test
    @DisplayName("Farenheit string format")
    void toString_Farenheit()
    {
        assertThat(Temperature.farenheit(12.3).toString()).isEqualTo("12.3 (F)");
    }

    @Test
    @DisplayName("Kelvin string format")
    void toString_Kelvin()
    {
        assertThat(Temperature.kelvin(12.3).toString()).isEqualTo("12.3 (K)");
    }
}
