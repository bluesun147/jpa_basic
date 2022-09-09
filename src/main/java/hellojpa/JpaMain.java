package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 앱 로딩 시점에 딱 하나만.
        // 엔티티 매니저 팩토리는 하나만 생성해서 앱 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 실제 디비에 저장하는 트랜잭션 단위 할때마다
        // 엔티티 매니저는 쓰레드간 공유 x (쓰고 버리기 close)
        EntityManager em = emf.createEntityManager();

        // jpa에서 '트랜잭션' 이란 단위 매우 중요!
        // 데이터 변경하는 모든 작업은 꼭 트랜잭션 안에서 작업해야 함.
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻기
        tx.begin(); // 트랜잭션 시작

        try {

            /*
            // 여기까지는 비영속. 아무상태도 아님.
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 여기서부터 영속상태
            // em 내부에 영속성 컨텍스트 통해서 이 멤버가 관리됨.
            em.persist(member);

            // db에서 가져온 것이 아니라 1차 캐시에서 조회.
            // 실행해 보면 select문 없음
            Member findMember = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("findMember = " + findMember.getName());
            System.out.println(findMember == findMember2); // 영속 엔티티의 동일성 보장
            */

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B"); // db에 저장했으니 우선 주석처리
            Member member = em.find(Member.class, 150L);
            member.setName("zzzz"); // 변경 후
            // em.persist(member); // 할 필요 없음

//            em.persist(member1);
//            em.persist(member2);
            System.out.println("===========");

            tx.commit(); // 정상이면 커밋하고
            // persist 까지는 insert 쿼리 db에 보내지 않음. commit 하는 순간에 보냄
            // 그동안 쓰기 지연 sql 저장소에 저장해둠.

        } catch (Exception e) {
            tx.rollback(); // 문제 생기면 롤백
        } finally {
            em.close();
        }
        emf.close();
    }
}