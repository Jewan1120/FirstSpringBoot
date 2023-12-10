package hello.hellospring.service;

import java.util.List;
import java.util.Optional;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MembeRepository;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberService {

    private final MembeRepository membeRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        membeRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return membeRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> fineOne(Long memberId) {
        return membeRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        membeRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
