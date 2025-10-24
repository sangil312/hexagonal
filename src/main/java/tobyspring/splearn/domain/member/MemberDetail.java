package tobyspring.splearn.domain.member;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import tobyspring.splearn.domain.AbstractEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded
    private Profile profile;

    private String introduction;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    void activate() {
        Assert.isTrue(activatedAt == null, "이미 activatedAt은 설정됐습니다");
        this.activatedAt = LocalDateTime.now();
    }

    void deactivate() {
        Assert.isTrue(deactivatedAt == null, "이미 deactivatedAt은 설정됐습니다");
        this.deactivatedAt = LocalDateTime.now();
    }

    void updateInfo(MemberInfoUpdateRequest request) {
        this.profile = new Profile(request.profileAddress());
        this.introduction = request.introduction();
    }
}
