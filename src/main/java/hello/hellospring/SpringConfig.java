package hello.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import hello.hellospring.repository.MembeRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(membeRepository());
    }

    @Bean
    public MembeRepository membeRepository() {
        return new MemoryMemberRepository();
    }
}
