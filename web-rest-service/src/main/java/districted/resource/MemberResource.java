package districted.resource;

import districted.converter.MemberDtoInConverter;
import districted.dto.MemberDtoIn;
import districted.model.BookReview;
import districted.model.Member;
import districted.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberResource {
    private final MemberService memberService;
    private final MemberDtoInConverter memberDtoInConverter;

    @GetMapping
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok(this.memberService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable UUID id) {
        Member body = this.memberService.getById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping()
    public ResponseEntity<Member> createMember(@RequestBody @Validated MemberDtoIn memberDtoIn) {
        Member member = memberDtoInConverter.convert(memberDtoIn);
        Member body = this.memberService.create(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id, @RequestBody @Validated MemberDtoIn memberDtoIn) {
        Member member = memberDtoInConverter.convert(id, memberDtoIn);
        Member body = this.memberService.update(member);
        body.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
        Member member = this.memberService.getById(id);
        this.memberService.delete(member);
        return ResponseEntity.noContent().build();
    }
}
