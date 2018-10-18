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
package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.process.ProcessValue;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A tank is a container in chemical engineering.
 * <p>
 * In the context of brewing, a tank manages a set of probes to monitor {@literal ProcessValue}
 * readings, and {@literal HeatExchanger} to control heating and/or cooling.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 * @see HeatExchanger
 * @see ProcessValue
 */
public final class Tank extends Aggregate<TankId>
{
    /**
     * Instantiates a new tank with that probeId
     *
     * @param id
     * @return a new instance
     */
    public static Tank with(final TankId id)
    {
        return new Tank(id);
    }

    private HeatExchanger cooler;

    private HeatExchanger heater;

    private final ConcurrentMap<ProbeId, Probe> probes = new ConcurrentHashMap<>();

    private Tank(final TankId id)
    {
        super(id);
    }

    /**
     * Configures a probe for usage within this tank.
     *
     * @param id
     * @param probe
     */
    public void add(final ProbeId id, final Probe probe)
    {
        probes.put(id, probe);
    }

    /**
     * Configures a heat exchanger for lowering the process value
     *
     * @param cooler
     */
    public void addCooler(final HeatExchanger cooler)
    {
        this.cooler = cooler;
    }

    /**
     * Configures a heat exchanger for raising the process value
     *
     * @param id
     * @param heatExchanger
     */
    public void addHeater(final HeatExchanger heater)
    {
        this.heater = heater;
    }

    /**
     * Obtains the configured heat exchanger for cooling
     *
     * @return an optinal instance of cooler
     */
    public Optional<HeatExchanger> cooler()
    {
        return Optional.ofNullable(cooler);
    }

    /**
     * Stop cooling the tank
     *
     * @throws HeatExchangerNotAvailable
     */
    public void coolerOff() throws HeatExchangerNotAvailable
    {
        Optional.ofNullable(cooler).orElseThrow(() -> HeatExchangerNotAvailable.on("cooling"))
                .off();
    }

    /**
     * Start cooling the tank
     *
     * @throws HeatExchangerNotAvailable
     */
    public void coolerOn() throws HeatExchangerNotAvailable
    {
        Optional.ofNullable(cooler).orElseThrow(() -> HeatExchangerNotAvailable.on("cooling")).on();
    }

    /**
     * obtains the configured heater
     *
     * @return an optinal instance of heater
     */
    public Optional<HeatExchanger> heater()
    {
        return Optional.ofNullable(heater);
    }

    /**
     * Stop heating the tank
     *
     * @throws HeatExchangerNotAvailable
     *
     */
    public void heaterOff() throws HeatExchangerNotAvailable
    {
        Optional.ofNullable(heater).orElseThrow(() -> HeatExchangerNotAvailable.on("heating"))
                .off();
    }

    /**
     * Start heating the tank
     *
     * @throws HeatExchangerNotAvailable
     */
    public void heaterOn() throws HeatExchangerNotAvailable
    {
        Optional.ofNullable(heater).orElseThrow(() -> HeatExchangerNotAvailable.on("heating")).on();
    }

    /**
     * Returns the {@link ProcessValue} of the requested probe.
     *
     * @param probeId
     * @return the temperature measured
     * @throws ProbeNotConfigured
     */
    public ProcessValue read(final ProbeId probeId) throws ProbeNotConfigured
    {
        final Optional<Probe> probe = Optional.ofNullable(probes.get(probeId));
        return probe.orElseThrow(() -> ProbeNotConfigured.on(probeId)).processValue();
    }

}
