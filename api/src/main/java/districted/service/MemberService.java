package districted.service;

import com.google.common.base.Preconditions;
import districted.model.Book;
import districted.model.BookReview;
import districted.model.Member;
import districted.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getAll() {
        return this.memberRepository.findAll();
    }

    public Member getById(UUID id) {
        return memberRepository.getReferenceById(id);
    }

    @Transactional
    public Member create(Member member) {
        Preconditions.checkArgument(member != null);
        Preconditions.checkArgument(member.getId() == null);

        return this.memberRepository.save(member);
    }

    @Transactional
    public Member update(Member member) {
        Preconditions.checkArgument(member != null);
        Preconditions.checkArgument(member.getId() != null);

        return this.memberRepository.save(member);
    }

    public void delete(Member member) {
        Preconditions.checkArgument(member != null);
        Preconditions.checkArgument(member.getId() != null);

        this.memberRepository.delete(member);
    }
}
