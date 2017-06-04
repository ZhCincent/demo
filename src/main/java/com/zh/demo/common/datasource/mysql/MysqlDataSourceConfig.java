package com.zh.demo.common.datasource.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;
import java.io.IOException;

/**
 *
 */

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "com.zh.demo.*.dao", sqlSessionFactoryRef = "firstSqlSessionFactory")
//@DependsOn("mybatisConfig")
public class MysqlDataSourceConfig  implements EnvironmentAware {

    private final Logger LOGGER = LoggerFactory.getLogger(MysqlDataSourceConfig.class);

    private RelaxedPropertyResolver mybatisPropertyResolver;

    /**
     * 获取配置信息
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        mybatisPropertyResolver = new RelaxedPropertyResolver(environment,"mybatis.");
    }

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "firstMysqlDatasource")
    @Primary
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(driver);
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
        return dataSource;
    }

    @Bean(name = "firstTransactionManager")
    @Primary
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "firstSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("firstMysqlDatasource") DataSource datasource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        //添加mybatis配置文件
        PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        bean.setConfigLocation(resourceResolver.getResource(mybatisPropertyResolver.getProperty("config-location")));
        try {
            bean.setMapperLocations(resourceResolver.getResources(mybatisPropertyResolver.getProperty("mapper-locations")));
            return bean.getObject();
        } catch (IOException e) {
            LOGGER.error("获取mapper资源出现异常",e);
            throw new RuntimeException("获取mapper资源出现异常",e);
        } catch (Exception e){
            LOGGER.error("初始化sqlSessionFactory时出现异常",e);
            throw new RuntimeException("初始化sqlSessionFactory时出现异常",e);
        }
    }

//    @Bean(name = "firstSqlSessionFactory")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("firstSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

}

