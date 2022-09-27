package hellojpa;

import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// @Table(name = "MBR")
public class Member {

    @Id // pk
    private Long id;

//    @Column(unique = true, length = 10) // ddl 생성 기능. ddl 자동 생성 시에만 사용되고 jpa 실행 로직에는 영향 주지 않음
    @Column(name = "name")
    private String username;
    
    private Integer age;

    // db에는 enum 타입이 없기에 enumerated 어노테이션
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 날짜 매핑 어노테이션. 옛날
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전은 그냥 LocalDate 쓰면 됨
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob // large object
    private String description;

    // db랑 관계없이 메모리에서만 계산하고 싶은 경우
    // db에 안만들어짐
    @Transient
    private int temp;

    // jpa는 기본생성자 있어야 함.
    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
