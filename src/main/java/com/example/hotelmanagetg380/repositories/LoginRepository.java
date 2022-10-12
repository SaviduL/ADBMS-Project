package com.example.hotelmanagetg380.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.hotelmanagetg380.entities.Login;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface LoginRepository extends CrudRepository<Login,Integer> {

    @Query(value = "SELECT l FROM Login l WHERE l.password=?1 ")
    ArrayList<Login> findByPwd(String pwd);


    @Transactional
    @Procedure(procedureName = "profile_password_update")
    void userPasswordUpdate(String reg,String old_pwd,String new_pwd);
}
