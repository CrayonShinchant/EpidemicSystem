package com.atguigu.epidemic;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 取代了myabtis.xml
 */
@Configuration
@MapperScan(basePackages = "com.atguigu.epidemic.mapper")
@Slf4j
public class MyBatisConfig {
    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/epidemic");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(50);
        dataSource.setMaxIdle(1);
        dataSource.setMaxWait(4000);
        dataSource.setDefaultAutoCommit(false);
        return dataSource;
    }

    /**
     * 配置sqlsessioinfactory
     *
     * @return
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactory.setDataSource(dataSource);
        SqlSessionFactory factory = null;
        //设置xml文件中类所在的包(设置别名)
        sqlSessionFactory.setTypeAliasesPackage("com.atguigu.epidemic.beans");
        //让mybatis自动将下划线分割的列名转换为驼峰表示的属性名： user_id(数据库--->userId) -> userID(实体类)
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(StdOutImpl.class);
        sqlSessionFactory.setConfiguration(configuration);

        //设置映射xml文件的路径
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/atguigu/epidemic/mapper/*Mapper.xml");
            sqlSessionFactory.setMapperLocations(resources);
            factory = sqlSessionFactory.getObject();
        } catch (Exception e) {
            log.error("解析映射xml文件时异常："+e.getMessage());
        }
            return factory;
    }
}
