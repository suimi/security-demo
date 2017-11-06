/*
 * Copyright (c) 2013-2015, 成都中联信通科技股份有限公司
 */
package com.suimi.security.oauth2server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author suimi
 * @date 2017-10-23
 */
public class SimpleTest {

    public class A {

        public A(String a) {
            this.a = a;
        }

        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }


    @Test
    public void test() {
        Optional<A> detail = Optional.ofNullable(new A("test,abce.Tes,saf"));
        Optional<List<String>> strings = detail.map(a -> {
            System.out.println(a);
            return Arrays.stream(a.getA().split(",")).map(s -> {
                System.out.println(s);
                return s.toUpperCase();
            }).collect(Collectors.toList());
        });
        System.out.println(strings.get());
    }
}
