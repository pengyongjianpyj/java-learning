package com.pyj.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/6/3 6:02 下午
 */
@RestController
public class TestController {

  @RequestMapping(value = "/test")
  public Mono<String> test(){
    return Mono.just("hello world!");
  }

}
