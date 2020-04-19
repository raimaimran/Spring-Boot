package com.example.AP_A3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepo userRepo;
    
    UserModel signUp(UserModel user){
        
        UserModel u = userRepo.findByUsername(user.username); //exists with the username
        if ( u != null)
            return null;
        return userRepo.save(new UserModel(user.username, user.name, user.password, user.gender, user.dob));
    }
    
    UserModel signIn(UserModel user){
        
        UserModel u = userRepo.findByUsername(user.username);
        
        if( u == null)
            return null;

        if (u.password.equals(user.password))
            return u;
        
        return null;
    }
    
    UserModel updateName(String name, UserModel u){
        
        userRepo.updateName(name, u.username);
        u.name = name;
        return u;
    }
    
    UserModel updateDOB(String dob, UserModel u){
        userRepo.updateDOB(dob, u.username);
        u.dob = dob;
        return u;
    }
    
    UserModel updatePassword(String password, UserModel u){
        userRepo.updatePassword(password, u.username);
        u.password = password;
        return u;
    }
    
    UserModel deleteAccount(String username){
        
        userRepo.deleteAccount(username);
        return null;
    }
}
