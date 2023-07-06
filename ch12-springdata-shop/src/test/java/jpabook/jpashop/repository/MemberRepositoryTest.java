package jpabook.jpashop.repository;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
//	@Rollback(value = true)
	@Ignore
	public void dummyInsert() {
		for(int i=0; i<476; i++) {
			Member member = new Member();
			Address address = new Address("대전광역시", "청사로", "254");
			
			member.setName("member_" + i);
			
			member.setAddress(address);
			
			memberRepository.save(member);
		}// for
	}// dummyInsert

	@Test
	public void pagingTest() {
		PageRequest pageRequest = new PageRequest(0, 10, new Sort(Direction.DESC, "id"));
		
		Page<Member> pageResult = memberRepository.findByNameStartingWith("member_1", pageRequest);
		
		List<Member> memberResult = pageResult.getContent(); // 조회된 데이터
		
		int      getNumber         = pageResult.getNumber(); // 현재 페이지
		int      getSize           = pageResult.getSize();   // 페이지 크기
		int      getTotalPages     = pageResult.getTotalPages(); // 전체 페이지 수 *
		long     getTotalElements  = pageResult.getTotalElements(); // 전체 데이터 수 *
		boolean  hasPreviousPage   = pageResult.hasPrevious(); // 이전 페이지 여부 *
		boolean  hasNextPage       = pageResult.hasNext(); // 다음 페이지 여부 *
		Pageable nextPageable      = pageResult.nextPageable(); // 다음 페이지 객체
		Pageable previousePageable = pageResult.previousPageable(); // 이전 페이지 겍체
		
		System.out.println("getNumber         : " + getNumber        );
		System.out.println("getSize           : " + getSize          );
		System.out.println("getTotalPages     : " + getTotalPages    );
		System.out.println("getTotalElements  : " + getTotalElements );
		System.out.println("hasPreviousPage   : " + hasPreviousPage  );
		System.out.println("hasNextPage       : " + hasNextPage      );
		System.out.println("nextPageable      : " + nextPageable     );
		System.out.println("previousePageable : " + previousePageable);
		
		// 총 페이지 = Math.ceil(페이지 크기 / 전체 데이터 갯수)
		// 끝 페이지 = Math.ceil(현재 페이지 / 페이지 블록 크기) * 페이지 블록 크기
		// 첫 페이지 = 끝 페이지 - (페이지 블록 크기 - 1)
	}// pagingTest
	
}// MemberRepositoryTest














