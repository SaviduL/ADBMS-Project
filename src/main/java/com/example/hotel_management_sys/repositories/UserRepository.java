package com.example.hotel_management_sys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.hotel_management_sys.entities.User;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    //These is use for validate a particular user(login)
    @Query(value = "SELECT u FROM User u WHERE u.registered_no=?1 AND u.is_deleted=0")
    User findByRegId(String id);

    //These is use for get last user id
    @Query(value = "SELECT u FROM User u order by u.id  DESC ")
    ArrayList<User> findLastID();

    //These is use for get  active user accounts
    @Query(value = "SELECT COUNT(id) FROM user WHERE is_deleted=0",nativeQuery = true)
    int getActiveUsers();

    //insert user procedure
    @Query(value = "{CALL user_account_create(?1,?2,?3)}",nativeQuery = true)
    int userAccountCreate(String user_registered_no, String name, Integer user_role_id);

    //delete user procedure
    @Query(value = "{CALL user_account_delete(?1)}",nativeQuery = true)
    void userAccountDelete(Integer id);

    //restore user account procedure
    @Transactional
    @Procedure(procedureName = "user_account_delete")
    void userAccountDeleted(Integer id);

    // update user profile procedure
    @Transactional
    @Procedure(procedureName = "profile_update")
    void userProfileUpdate(String reg, String name, String phone, String email);

    //get user id
    @Query(value = "SELECT id FROM user WHERE registered_no=?1 AND is_deleted=0",nativeQuery = true)
    Integer getUserID(String id);

    //get email address
    @Query(value = "SELECT email FROM user WHERE registered_no=?1 AND is_deleted=0",nativeQuery = true)
    String getEmail(String reg);

}
