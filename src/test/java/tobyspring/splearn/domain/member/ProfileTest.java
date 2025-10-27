package tobyspring.splearn.domain.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ProfileTest {

    @Test
    void profile() {
        new Profile("seoul");
        new Profile("seoul1");
        new Profile("123456");
    }

    @Test
    void profileFail() {
        assertThatThrownBy(() -> new Profile("asdasdasdasdasdasdadasdasdasdasdasdasdasd"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Profile("A"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Profile("한글"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void url() {
        Profile profile = new Profile("seoul");

        assertThat(profile.url()).isEqualTo("@seoul");
    }
}