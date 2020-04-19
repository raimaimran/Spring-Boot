package com.example.AP_A3;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<UserModel, String>{
    
    //string is primary key 
    //and usermodel is model class
    
    UserModel findByUsername(String username);
    
    @Transactional
    @Modifying
    @Query(value ="UPDATE userprofile SET userprofile.name=:name WHERE userprofile.username =:username", nativeQuery = true)
    void updateName(@Param("name") String name, @Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value ="UPDATE userprofile SET userprofile.dob=:dob WHERE userprofile.username =:username", nativeQuery = true)
    void updateDOB(@Param("dob") String dob, @Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value ="UPDATE userprofile SET userprofile.password=:password WHERE userprofile.username =:username", nativeQuery = true)
    void updatePassword(@Param("password") String password, @Param("username") String username);
    
    @Transactional
    @Modifying
    @Query(value ="Delete from userprofile where userprofile.username=:username", nativeQuery = true)
    void deleteAccount(@Param("username") String username);
    
    
}
