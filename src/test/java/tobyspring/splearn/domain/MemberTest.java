package tobyspring.splearn.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {
    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
        member = Member.create(new MemberCreateRequest("toby@splearn.app", "Toby", "secret"), passwordEncoder);
    }

    @Test
    @DisplayName("")
    void createMember() {
        // when // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    @DisplayName("")
    void activate() {
        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    @DisplayName("")
    void activateFail() {
        // when
        member.activate();

        // then
        assertThatThrownBy(member::activate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("")
    void deactivate() {
        // given
        member.activate();

        // when
        member.deactivate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    @DisplayName("")
    void deactivateFail() {
        // given

        // when // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);

        // given
        member.activate();
        member.deactivate();

        // when // then
        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("")
    void verifyPassword() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    @DisplayName("")
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("Toby");

        member.changeNickname("Charlie");

        assertThat(member.getNickname()).isEqualTo("Charlie");
    }

    @Test
    @DisplayName("")
    void changePassword() {
        member.changePassword("verysecret", passwordEncoder);

        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }

    @Test
    @DisplayName("")
    void isActive() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    @DisplayName("")
    void invalidEmail() {
        assertThatThrownBy(() -> {
            Member.create(new MemberCreateRequest("invalid email", "Toby", "secret"), passwordEncoder);
        }).isInstanceOf(IllegalArgumentException.class);

        Member.create(new MemberCreateRequest("hsi@email.com", "Toby", "secret"), passwordEncoder);
    }
}