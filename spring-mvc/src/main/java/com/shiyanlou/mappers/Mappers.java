package com.shiyanlou.mappers;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

    // SqlSessionFactory 我们已经通过 spring-config.xml 注入到 Spring 容器中了，直接可以使用
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    // 将 UserMapper 注入到 Spring 容器中，以便使用
    @Bean
    public UserMapper userMapper() throws Exception {
        sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate.getMapper(UserMapper.class);
    }
}