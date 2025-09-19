package tobyspring.splearn.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("")
    void createMember() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");

        // when // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    @DisplayName("")
    void constructorNullCheck() {
        // given // when // then
        assertThatThrownBy(() -> new Member("toby@splearn.app", null, "secret"))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("")
    void activate() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");

        // when
        member.activate();

        // then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    @DisplayName("")
    void activateFail() {
        // given
        var member = new Member("toby@splearn.app", "Toby", "secret");

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
        var member = new Member("toby@splearn.app", "Toby", "secret");
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
        var member = new Member("toby@splearn.app", "Toby", "secret");

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
}