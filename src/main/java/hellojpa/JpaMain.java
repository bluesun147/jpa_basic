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

           /* Member member = new Member();

            member.setId(2L); // Long 값
            member.setName("helloB");

            em.persist(member); // 저장*/


            // jpql은 테이블 대상이 아닌 객체(Member 객체) 대상으로.
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }


            /*
             Member findMember = em.find(Member.class, 1L); // 찾고
             findMember.setName("HelloJPA"); // 이름 수정하고
             em.persist(findMember); // 저장? 안해도 됨.
             값만 바꿨는데 어떻게 되는거지?
             jpa가 변경이 됐는지 안됐는지 트랜잭션 커밋하기 직전에 다 체크.
             바뀐게 있으면 업데이트 쿼리 날림. 트랜잭션 커밋 직전에.
             */

            tx.commit(); // 정상이면 커밋하고
        } catch (Exception e) {
            tx.rollback(); // 문제 생기면 롤백
        } finally {
            em.close();
        }
        emf.close();
    }
}
