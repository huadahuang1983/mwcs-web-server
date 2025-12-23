package com.reebake.example;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.reebake.mwcs.core.UserContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MyBatisPlusConfig {

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 防止全表删或更新
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        return interceptor;
    }

    /**
     * 配置自动填充
     *
     */
    @Bean
    MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                // 数据类型要与fileName一致
                this.strictInsertFill(metaObject, "createdTime", Date.class, new Date());
                this.strictInsertFill(metaObject, "createdBy", String.class, findUserId());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                // 数据类型要与fileName一致
                this.strictUpdateFill(metaObject, "updatedTime", Date.class, new Date());
                this.strictUpdateFill(metaObject, "updatedBy", String.class, findUserId());
            }
        };
    }

    private String findUserId() {
        return UserContextHolder.getCurrentUserId();
    }
}