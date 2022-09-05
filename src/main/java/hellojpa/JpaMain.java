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

            // 여기까지는 비영속. 아무상태도 아님.
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 여기서부터 영속상태
            // em 내부에 영속성 컨텍스트 통해서 이 멤버가 관리됨.
            em.persist(member);

            tx.commit(); // 정상이면 커밋하고
        } catch (Exception e) {
            tx.rollback(); // 문제 생기면 롤백
        } finally {
            em.close();
        }
        emf.close();
    }
}
