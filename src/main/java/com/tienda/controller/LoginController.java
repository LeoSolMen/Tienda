/*
Proyecto tienda
 */
package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author LeoSolis
*/

@Controller
public class LoginController {
    @GetMapping("/")
    public String index()
    {
        return "login";
    }
} 
