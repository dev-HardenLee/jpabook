package jpabook.start;

import javax.persistence.*;  //**
import java.util.Date;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
@Table(name="MEMBER", uniqueConstraints = {
		@UniqueConstraint( //추가 //**
	        name = "NAME_AGE_UNIQUE",
	        columnNames = {"NAME", "AGE"}
        ) // UNIQUE 제약조건 추가. name : 제약조건명, columnsNames : 제약조건에 포함될 컬럼 명
})
public class Member {
	
	// 데이터베이스가 생성해주는 값을 사용하려면(오라클의 시퀀스, MySQL의 AUTO_INCREMENT) 아래와 같은 전략을 사용한다.
	//	- IDENTITY : 기본 키 생성을 데이터베이스에 위임.
	//	- SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본 키를 할당한다.
	//	- TABLE    : 키 생성 테이블을 사용한다.
	//	- AUTO     : 선택한 데이터베이스에 따라 IDENTITY, SEQUENCE, TABLE 전량 중 하나를 자동으로 선택한다.
	// * SEQUENCE나 IDENTITY경우에는 사용하는 데이터베이스에 의존한다.
	// * 자동 생성 전략을 사용하려면 @Id에 @GeneratedValue를 추가하고 원하는 키 생성 전략을 선택한다.
	// * @GeneratedValue의 기본 전략은 AUTO이다.
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
    // @Column(name = "NAME") //추가 //**
    // 속성 : 1.nullable - false로 설정하면 not null 제약조건을 추가할 수 있다.
    //	     2. length - 문자의 크기를 지정할 수 있다. 
    private String username;
    
    private Integer age;

    //=== 추가
    // 자바의 enum을 사용하려면 @Enumerated 어노테이션으로 매핑해야 한다.
    // 속성 : 1. value
    //			1-1. EnumType.ORDINAL : enum의 순서를 데이터베이스에 저장
    //	  		1-2. EnumType.STRING  : enum의 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // 자바의 날짜 타입은 @Temporal을 사용해서 매핑한다.
    // 속성 : 1. value
    //		 	1-1. TemporalType.DATE      : 날짜,      데이터베이스 date      타입과 매핑. ex) 2023-05-18
    //			1-2. TemporalType.TIME      : 시간,      데이터베이스 time      타입과 매핑. ex) 17:11
    //			1-3. TemporalType.TIMESTAMP : 날짜와 시간, 데이터베이스 timestamp 타입과 매핑. ex) 2023-05-18 17:11:32
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    
    // 길이에 제한이 없다. 데이터베이스의 VARCHAR대신에 CLOB타입으로 저장해야한다.
    // @Lob을 사용하면 CLOB, BLOB 타입을 매핑할 수 있다.
    @Lob
    private String description;

    @Transient // @Transient는 해당 필드를 데이터베이스에 매핑하지 않는다.
    private String temp;


    //Getter, Setter

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

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
