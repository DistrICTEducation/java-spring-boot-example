package districted.service;

import districted.model.Book;
import districted.model.Member;
import districted.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getById(UUID id) {
        return memberRepository.getReferenceById(id);
    }

    @Transactional
    public Member create(Member member) {
        return this.memberRepository.save(member);
    }

    @Transactional
    public Member update(Member member) {
        return this.memberRepository.save(member);
    }

    public void delete(Member member) {
        this.memberRepository.delete(member);
    }
}
