package com.example.AP_A3;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "/user")
public class MainController {
    
    UserModel userModel;
    @Autowired
    UserService userService;
    
    @GetMapping (path = "/home")
    public String welcome(){
        return "Welcome! \nPress 1 to Sign in \nPress 2 to Sign up";
    }
    
    @PostMapping(path = "/home")
    public String userSelection(@RequestBody Integer choice){
        
        if (choice.equals(1)){ //sign in
            return "Enter \n1-UserName \n2-Password";
        }
        else if (choice.equals(2)){ //sign up
            return "Enter \n1-UserName \n2-Name \n3-Password \n4-Gender";
        }
        else {
            
            return "Please Enter a valid option";
        }
    }
    @PostMapping(path = "/signup")
    public String signUp(@RequestBody UserModel user){
        if(user == null){
            return "Error Occured while creating account. Make sure you have filled all fields and try again.";
        }
        if(user.password.equals("") || user.name.equals("")|| user.username.equals("")){
            return "Error Occured while creating account. Make sure you have filled all fields and try again.";
        }
        
        userModel = userService.signUp(user);
        if(userModel == null){
            return "Error Occured while creating account. Username is taken.";
        }
        
        return "Account created successfully!.\nDetails entered\nName: "+ userModel.name +"\nUsername: "+userModel.username+ " \nDoB: "+userModel.dob+"\nGender: "+ userModel.gender+"\nEnter \n1 to Update profile \n2 to Change Password \n3 to Delete account \n4 to Logout";
    }
    @PostMapping(path = "/signin")
    public String signIn(@RequestBody UserModel user){
        
        if(user.username.equals("")|| user.password.equals("")){
            return "Username/password is missing. Make sure you have entered all fields.";
        }
        
        userModel = userService.signIn(user);
       
        if(userModel == null){
            return "Either username or password is incorrect. Try again.";
        }
        
        return "Successfully Signed in.\nWelcome! " + userModel.name +"\nEnter \n1 to Update profile \n2 to Change Password \n3 to Delete account \n4 to Logout";
    
    }
    
    @PostMapping (path = "/userprofile")
    public String userProfile(@RequestBody int choice){
        
        if (userModel == null){
            return "Please sign in/sign up to access this page.";
        }
        if(userModel.username.equals("") || userModel.name.equals("") ){
            return "Please sign in/sign up to access this page.";
        }
        
        if(choice == 1){
            return "Enter \n1 to Update name \n2 to Update DOB";
        }
        else if (choice == 2){
            return "Enter your new password and confirm";
        }
        else if (choice ==3){
            return "Are you sure you want to delete your account? \nIf yes Press 1 \nIf no then press 2";
        }
        else if(choice == 4){
            userModel = null;
            return "Successfully logged out.";
        }
        return "Please enter a valid option.";
    }
    
    @RequestMapping (path = "/updateprofile")
    public String updateProfile(@RequestBody int choice){
        
        if (userModel == null){
            return "Please sign in/sign up to access this page.";
        }
        
        if (choice == 1){
            return "Enter updated name";
        }
        else if(choice == 2){
            return "Enter updated DOB";
        }
        return "Invalid option selection.";
    }
    
    @RequestMapping (path= "/updatename")
    public String updateName(@RequestBody String name){
        
        if (userModel == null){
            return "Please sign in/sign up to access this page.";
        }
        
        userModel = userService.updateName(name, userModel);
        
        return "Successfully updated name.";
    }
    
    @RequestMapping (path= "/updatedob")
    public String updateDOB(@RequestBody String dob){
        
        if (userModel == null){
            return "Please sign in/sign up to access this page.";
        }
        
        userModel = userService.updateDOB(dob, userModel);
        
        return "Successfully updated DOB.";
    }
    
    @RequestMapping (path = "/updatepassword")
    public String updatePassword(@RequestBody List<String> password){
        
        if (userModel == null){
            return "Please sign in/sign up to access this page.";
        }
        
        if(password.get(0).equals(password.get(1))){
            
            userModel = userService.updatePassword(password.get(0), userModel);
            return "Successfully updated password.";
        }
        
        return "Both passwords do not match.";
    }
    
    @RequestMapping (path = "/deleteaccount")
    public String deleteAccount(@RequestBody int choice){
        
        if (choice == 1){  
            userModel = userService.deleteAccount(userModel.username);
            return "Account deleted successfully. GoodBye!";
        }
        else if (choice == 2)
            return "Account not deleted. Good to still have you on board with us!";
        else 
            return "Enter a valid Option.";
    }
    
}
