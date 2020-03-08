package com.practice.springbootsecurity.basicauth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
/api/greeting/hello - open for all
/api/user/profile (available to specific user)
/api/sales/report (available to manager)
/api/users/list (available to admin user only)

 - create authorities
 - Authority1
 - Authority2
 - Authority3
*/

@RestController
public class SimpleController {

    @RequestMapping("/api/greeting/hello")
    public String sayHello(){
        return "Hello Available to Everyone...";
    }

    @RequestMapping("/api/user/profile")
    public String profile(){
        return "Profile Available to Logged-In User";
    }

    @RequestMapping("/api/sales/report")
    public String salesReport(){
        return "Sales Report Available to Manager Role Only";
    }

    @RequestMapping("/api/users/list")
    public String userList(){
        return "List of Users available to Admin Role Only";
    }
}