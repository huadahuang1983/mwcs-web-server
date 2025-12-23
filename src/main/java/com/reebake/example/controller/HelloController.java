package com.reebake.example.controller;

import cn.hutool.core.lang.Dict;
import com.reebake.mwcs.infra.mapper.DataDictMapper;
import com.reebake.mwcs.message.mapper.MessageTemplateMapper;
import com.reebake.mwcs.storage.mapper.FileDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HelloController {
    private final DataDictMapper dataDictMapper;
    private final FileDetailMapper fileDetailMapper;
    private final MessageTemplateMapper messageTemplateMapper;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot 3.5.8!";
    }
}