package org.example.springboot231009.web;


import org.example.springboot231009.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        System.out.println("컨트롤러 HelloController hello() 진입");
        return "hello";
    }


    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        System.out.println("컨트롤러 HelloController helloDto() 진입 ");
        return new HelloResponseDto(name, amount);

    }
}
