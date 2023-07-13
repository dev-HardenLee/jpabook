package jpabook.jpashop.repository.custom;

import java.util.List;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.vo.MemberSearchVO;

public interface CustomMemberRepository {
	
	List<Member> findBySearch(MemberSearchVO searchVO);
	
}// CustomMemberRepository
