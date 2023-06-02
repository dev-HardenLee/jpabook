package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import java.util.List;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오전 1:08
 */
@Repository
public class MemberRepository {
	
	// 스프링 컨테이너가 엔티티 매니저를 관리하고 제공해줌.
	// @PersistenceContext는 컨테이너가 관리하는 엔티티 매니저를 주입하는 어노테이션이다.
    @PersistenceContext
    EntityManager em;
    
    // @PersistenceContext를 사용해서 컨테이너가 관리하는 엔티티 매니저를 주입 받을 수 있어서 엔티티 매니저 팩토리를 직접 사용할 일은 거의 없겠지만,
    // 엔티티 매니저 팩토리를 주입받으려면 다음처럼 @PersistenceUnit 어노테이션을 사용하면 된다.
//    @PersistenceUnit
//    EntityManagerFactory emf;
    
    public void save(Member member) {
        em.persist(member); // 영속화 한다.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
