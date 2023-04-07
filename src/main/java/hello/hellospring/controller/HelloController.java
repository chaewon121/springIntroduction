package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    /**
     * 스프링 부트 템플릿엔진 기본 viewName 매핑
     * resources:templates/ +{ViewName}+ .html
     */

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // -> http 바디에 직접 넣어주겠다.쓸일 거의 없음
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    /**
     * HttpMessgeConverter ->
     * 1. (jsonConverter) -> 객체가 왔을때 동작
     * 2. (StringConverter) -> 문자가 왔을때 동작
     */

    static class Hello {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }
}
