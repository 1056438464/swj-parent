package com.swj.crawler.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.swj.crawler.mapper", sqlSessionTemplateRef = "crawlerSqlSessionTemplate")
public class CrawlerDataSourceConfig {
    @Value("${spring.datasource.druid.crawler.filters}")
    private String filters;

    @Bean(name = "crawlerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.crawler")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "crawlerSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("crawlerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "crawlerTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("crawlerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "crawlerSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("crawlerSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}

