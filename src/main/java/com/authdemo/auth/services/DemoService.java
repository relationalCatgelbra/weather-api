package com.authdemo.auth.services;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String getDemoString() {
        return "This is a demo string";
    }

}
