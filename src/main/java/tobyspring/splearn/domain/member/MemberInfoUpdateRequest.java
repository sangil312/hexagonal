package tobyspring.splearn.domain.member;

public record MemberInfoUpdateRequest(
        String nickName,
        String profileAddress,
        String introduction
) {
}
