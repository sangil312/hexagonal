//package tobyspring.splearn.application.provided;
//
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.test.util.ReflectionTestUtils;
//import tobyspring.splearn.application.member.MemberModifyService;
//import tobyspring.splearn.application.required.EmailSender;
//import tobyspring.splearn.application.required.MemberRepository;
//import tobyspring.splearn.domain.shared.Email;
//import tobyspring.splearn.domain.member.Member;
//import tobyspring.splearn.domain.member.MemberFixture;
//import tobyspring.splearn.domain.member.MemberStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//class MemberRegisterManualTest {
//
//    @Test
//    void registerTestStub() {
//        MemberModifyService register = new MemberModifyService(
//                new MemberRepositoryStub(), new EmailSenderStub(), MemberFixture.createPasswordEncoder());
//
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//    }
//
//    @Test
//    void registerTestMock() {
//        EmailSenderMock emailSenderMock = new EmailSenderMock();
//        MemberModifyService register = new MemberModifyService(
//                new MemberRepositoryStub(), emailSenderMock, MemberFixture.createPasswordEncoder());
//
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        assertThat(emailSenderMock.getTos()).hasSize(1);
//        assertThat(emailSenderMock.getTos().getFirst()).isEqualTo(member.getEmail());
//    }
//
//    @Test
//    void registerTestMockito() {
//        EmailSender emailSenderMock = Mockito.mock(EmailSender.class);
//        MemberModifyService register = new MemberModifyService(
//                new MemberRepositoryStub(), emailSenderMock, MemberFixture.createPasswordEncoder());
//
//        Member member = register.register(MemberFixture.createMemberRegisterRequest());
//
//        assertThat(member.getId()).isNotNull();
//        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
//
//        Mockito.verify(emailSenderMock).send(eq(member.getEmail()), any(), any());
//    }
//
//    static class MemberRepositoryStub implements MemberRepository {
//        @Override
//        public Member save(Member member) {
//            ReflectionTestUtils.setField(member, "id", 1L);
//            return member;
//        }
//
//        @Override
//        public Optional<Member> findByEmail(Email email) {
//            return Optional.empty();
//        }
//
//        @Override
//        public Optional<Member> findById(Long memberId) {
//            return Optional.empty();
//        }
//    }
//
//    static class EmailSenderStub implements EmailSender {
//        @Override
//        public void send(Email email, String subject, String body) {
//        }
//    }
//
//    static class EmailSenderMock implements EmailSender {
//        List<Email> tos = new ArrayList<>();
//
//        public List<Email> getTos() {
//            return tos;
//        }
//
//        @Override
//        public void send(Email email, String subject, String body) {
//            tos.add(email);
//        }
//    }
//}