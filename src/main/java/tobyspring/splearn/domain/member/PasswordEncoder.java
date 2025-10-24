package tobyspring.splearn.domain.member;

import org.springframework.stereotype.Service;

@Service
public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String password, String passwordHash);
}
