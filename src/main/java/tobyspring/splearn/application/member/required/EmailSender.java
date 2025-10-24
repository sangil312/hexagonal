package tobyspring.splearn.application.member.required;

import org.springframework.stereotype.Service;
import tobyspring.splearn.domain.shared.Email;

/**
 * 이메일을 발송한다
 */
@Service
public interface EmailSender {
    void send(Email email, String subject, String body);
}
