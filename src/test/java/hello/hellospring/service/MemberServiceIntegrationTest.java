package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MembeRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional // RollBack
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MembeRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void testJoin() {
        // Given
        Member member = new Member();
        member.setName("Jewan");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.fineOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("중복회원체크")
    public void duplicateCheck() {
        // Given
        Member member1 = new Member();
        member1.setName("Jewan");

        Member member2 = new Member();
        member2.setName("Jewan");

        // When
        memberService.join(member1);
        IllegalStateException e =
                assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
