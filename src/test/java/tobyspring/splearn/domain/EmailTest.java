package tobyspring.splearn.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailTest {

    @Test
    @DisplayName("")
    void equality() {
        Email email = new Email("hsi@email.com");
        Email email2 = new Email("hsi@email.com");

        assertThat(email).isEqualTo(email2);
    }
}