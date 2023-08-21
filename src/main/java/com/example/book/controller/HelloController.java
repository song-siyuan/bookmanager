package com.example.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songsiyuan
 * @date 2023/8/21 09 29
 * discription
 */

@RestController
@RequestMapping("/hello")

/**
 * @param null
 * @return String
 * @author songsiyuan
 * @date 2023/8/21 9:34
 */
public class HelloController {
    @GetMapping("/welcome")
    public  String welcome() {
        return "Hello Word";
    }
}

