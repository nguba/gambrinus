package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.support.SingleValueObject;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class SingleValueObjectFixture<I, V extends SingleValueObject<I>>
{  
    protected V getValueObject()
    {
        return valueObject;
    }

    private final V valueObject = makeValueObject();
    
    @Test
    @DisplayName("Is valid when not null")
    void isValidWhenNotNull()
    {
        assertThat(valueObject.isValid()).isTrue();
    }

    abstract V makeValueObject();

    @Test
    @DisplayName("toString() returns value as string")
    void toStringReturnsValueAsString()
    {
        assertThat(valueObject.toString()).isEqualTo(valueObject.getValue().toString());
    }

    @Test
    @DisplayName("equality contract is upheld")
    void equalityContract()
    {
        EqualsVerifier.forClass(valueObject.getClass()).usingGetClass().verify();
    }

}
