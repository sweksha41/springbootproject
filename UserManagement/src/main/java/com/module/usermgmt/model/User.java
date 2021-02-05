package com.module.usermgmt.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "UserData")
//@Table(name = "User", schema = "books_lib", catalog = "books_lib")
//@Where(clause = "is_deleted = 0")
//@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
   // @GeneratedValue(generator = "platform-uuid")
   // @GenericGenerator(name = "platform-uuid",
   // strategy = "com.example.BooksMgmt.restservice.common.validation.PlatformUUIDGenerator")
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    
    @Column(name = "is_admin")
    private String isAdmin;

//    @Transient
//    private String token;

//    @Column(name = "creation_date")
//    @CreatedDate
//    private Instant createTimestamp;
//
//    @Column(name = "update_date")
//    @LastModifiedDate
//    private Instant updateTimestamp;

 //   @Column(name = "is_deleted")
 //   @Type(type = "org.hibernate.type.NumericBooleanType")

//    @Column(name = "created_by")
//    @CreatedBy
//    private String createdBy;
//
//    @Column(name = "updated_by")
//    @LastModifiedBy
//    private String updatedBy;

}