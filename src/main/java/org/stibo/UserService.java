package org.stibo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
 
    public String getUser() {
        return "Hello, User!";
    }
}
