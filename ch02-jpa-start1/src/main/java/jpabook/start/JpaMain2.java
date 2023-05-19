package jpabook.start;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {
	
	public static void main(String[] args) {
		
		// 1. 앤티티 매니저 설정.
		//   1-1. 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
		
		//   1-2. 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		//   1-3. 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 2. 트랜잭션 관리
			tx.begin(); // 트랜잭션 시작
			
			// 3. 비즈니스 로직
			logic(em); // 비즈니스 로직
			
			tx.commit(); // 트랜잭션 커밋
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); // 에러 발생시에 트랜잭션 롤백
		} finally {
			em.close(); // 엔티티 매니저 종료
		}// try-catch-finally
		
		emf.close(); // 엔티티 매니저 팩토리 종료
		
	}// main

	private static void logic(EntityManager em) {
		String id = "id";
		
		Member member = new Member();
		
		member.setId(id);
		member.setUsername("건희");
		member.setAge(29);
		
		// 등록
		em.persist(member);
		
		// 수정
		member.setAge(30);
		
		// 단 건 조회
		Member findMember = em.find(Member.class, id);
		System.out.println(findMember);
		
		// 목록 조회
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println(members.size());
		
		// 삭제
		em.remove(member);
		
	}// logic

}// JpaMain2

















