package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

class OwfsRootTest extends SingleValueObjectFixture<File, OwfsRoot>
{
    @Test
    @DisplayName("is invalid if path doesn't exist")
    void pathNotExists()
    {
        assertThat(OwfsRoot.of("non-existent-path").isValid()).isFalse();
    }

    @Test
    @DisplayName("is invalid if path is empty")
    void pathNull()
    {
        assertThat(OwfsRoot.of("").isValid()).isFalse();
    }

    @Override
    OwfsRoot makeValueObject()
    {
        return OwfsRoot.of("owfs");
    }

    @Override
    void isValidWhenNotNull()
    {
        // disabled for this suite
    }

    @Test
    @DisplayName("is invalid if path doesn't exist")
    void isValidWhenPathExists()
    {
        assertThat(OwfsRoot.of("src/test/resources/owfs").isValid()).isTrue();
    }
}
