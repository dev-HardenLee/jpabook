package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 프레임워크와 JUnit 테스트를 통합하려면 @Runwith에 @SpringJUnit4ClassRunner.class를 지정하면 된다.
									    // 이렇게 하면 테스트가 스프링 컨테이너에서 실행되므로 @Autowired와 같은 기능을 사용할 수 있다.
@ContextConfiguration(locations = "classpath:appConfig.xml") // 설정정보로 appConfig.xml을 지정함. 웹과 관련된 정보는 필요하지 않으므로 webAppConfig.xml은 지정하지 않는다.
@Transactional // 보통 @Transactional은 비즈니스 로직이 있는 서비스 계층에서 사용한다.
			   // 이 어노테이션을 테스트에서 사용하면 동작 방식이 달라진다.
			   // 이 때는 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 테스트가 끝나면 트랜잭션을 강제로 롤백한다.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setName("kim");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class) // expected속성에 예외 클래스를 지정하면 테스트의 결과로 지정한 예외가 발생해야 테스트가 성공됨.
    public void 중복_회원_예외() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        //Then
        fail("예외가 발생해야 한다."); // 이 라인까지 오면 실패라는 의미. fail()이 호출되거나, expected에 지정된 예외가 발생하지 않으면 테스트는 실패한다.
    }


}