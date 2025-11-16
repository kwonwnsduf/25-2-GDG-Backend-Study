package com.example.shop;

import com.example.shop.member.repository.JpaMemberRepository;
import com.example.shop.member.repository.MemberRepository;
import com.example.shop.member.service.MemberServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberServiceImpl memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository();
    }
}
