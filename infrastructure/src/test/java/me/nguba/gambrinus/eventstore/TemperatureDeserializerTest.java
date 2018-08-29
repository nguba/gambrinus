package me.nguba.gambrinus.eventstore;

import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class TemperatureDeserializerTest
{
    private final EventSerializerService serializer  = EventSerializerService.flatFormat();
    private final Temperature            temperature = Temperature.celsius(10);

    @Test
    void canSerialize() throws Exception
    {
        String json = serialize(temperature);
        assertThat(json).contains(":10.0", "CELSIUS");
    }

    @Test
    void canDeserialize() throws Exception
    {
        String json = serialize(temperature);
        
        ValueObjectEvent restore = serializer.restore(json, ValueObjectEvent.class);
        assertThat(restore).isEqualToComparingFieldByField(ValueObjectEvent.from(temperature));
    }

    private String serialize(Temperature valueObject) throws IOException
    {
        ValueObjectEvent event = ValueObjectEvent.from(valueObject);
        String json = serializer.transform(event);
        System.out.println(json);
        return json;
    }
}
