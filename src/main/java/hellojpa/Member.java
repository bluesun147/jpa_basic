package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @Table(name = "MBR")
public class Member {

    @Id
    private Long id;

//    @Column(unique = true, length = 10) // ddl 생성 기능. ddl 자동 생성 시에만 사용되고 jpa 실행 로직에는 영향 주지 않음
    private String name;
    private int age;

    // jpa는 기본생성자 있어야 함.
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
