package com.shiro.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Component
public class ShiroTagFreeMarkerClass {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @PostConstruct
    public void setSharedViariable()  {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro",new ShiroTags());
        freeMarkerConfigurer.getConfiguration().setNumberFormat("#");
    }
}
