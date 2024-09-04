package districted.converter;

import districted.dto.MemberDtoIn;
import districted.model.Member;
import districted.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberDtoInConverter {
    private final MemberService memberService;

    public Member convert(MemberDtoIn memberDtoIn) {
        return Member.builder()
            .email(memberDtoIn.getEmail())
            .firstName(memberDtoIn.getFirstName())
            .lastName(memberDtoIn.getLastName())
            .password(memberDtoIn.getPassword())
            .username(memberDtoIn.getUsername())
            .build();
    }

    public Member convert(UUID id, MemberDtoIn memberDtoIn) {
        Member member = memberService.getById(id);

        member.setEmail(memberDtoIn.getEmail());
        member.setFirstName(memberDtoIn.getFirstName());
        member.setLastName(memberDtoIn.getLastName());
        member.setPassword(memberDtoIn.getPassword());
        member.setUsername(memberDtoIn.getUsername());

        return member;
    }
}
