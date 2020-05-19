package com.swj.crawler.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.FileCopyUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.UUID;

@Configuration
@MapperScan(basePackages = "com.swj.crawler.mapper.sharding", sqlSessionTemplateRef = "ShardingSqlSessionTemplate")
public class ShardingDataSourceConfig {

    @Value("sharding.yml")
    private String ymlLocation;


    @Bean(name = "shardingDataSource")
    public DataSource dataSource() throws IOException, SQLException {
        ClassPathResource cps = new ClassPathResource(ymlLocation);
        File file = null;
        DataSource dataSource = null;
        try (InputStream in = cps.getInputStream()) {
            file = new File(UUID.randomUUID().toString());
            FileCopyUtils.copy(in, Files.newOutputStream(file.toPath()));
            dataSource = YamlShardingDataSourceFactory.createDataSource(file);
        } catch (Exception e) {
            throw e;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
        return dataSource;
    }


    @Bean(name = "shardingSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("shardingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/sharding/*Mapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "couponShardingTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("shardingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ShardingSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("shardingSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
