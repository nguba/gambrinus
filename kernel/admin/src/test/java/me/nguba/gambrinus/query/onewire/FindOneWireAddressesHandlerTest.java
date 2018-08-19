package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.onewire.OneWireAddress;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindOneWireAddressesHandlerTest
{
    private final FindOneWireAddressesHandler handler = new FindOneWireAddressesHandler();

    private final Errors errors = Errors.empty();

    @Test
    void validationFailure()
    {
        handler.validate(FindOneWireAddresses.on("unavailable"), errors);

        assertThrows(ValidationFailed.class, () -> errors.verify());
    }

    @Test
    void validation()
    {
        handler.validate(FindOneWireAddresses.on("src/test/resources/owfs"), errors);

        assertThat(errors.hasErrors()).isFalse();
    }

    @Test
    void emptyResultOnIOFailure()
    {
        FindOneWireAddressResult result = handler.run(FindOneWireAddresses.on("unavailable"));

        assertThat(result.getResult().get()).isEmpty();
    }

    @Test
    void result()
    {
        FindOneWireAddressResult result = handler
                .run(FindOneWireAddresses.on("src/test/resources/owfs"));

        assertThat(result.getResult().get()).containsOnly(OneWireAddress.of("28.273B5D070000"),
                                                          OneWireAddress.of("28.4BBB68080000"));
    }

}
