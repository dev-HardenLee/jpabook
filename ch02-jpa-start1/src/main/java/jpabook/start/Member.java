package jpabook.start;

import javax.persistence.*;  //**

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER")
public class Member {

    @Id // 식별자 필드
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age; // 만약 사용하는 데이터베이스가 대소문자를 구분한다면 @Column(name="AGE")처럼 명시적으로 매핑해야한다.

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", age=" + age + "]";
	}
    
    
}
