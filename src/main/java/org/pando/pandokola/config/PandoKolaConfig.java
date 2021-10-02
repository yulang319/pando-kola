package org.pando.pandokola.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "org.pando.pandokola.dao.pandokola", sqlSessionTemplateRef="pandokolaSessionTemplate")
public class PandoKolaConfig {
    @Primary
    @Bean(name = "pandokolaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pando-kola")
    public DataSource pandokolaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "pandokolaTransactionManager")
    public DataSourceTransactionManager pandokolaTransactionManager(@Qualifier("pandokolaDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "pandokolaSqlSessionFactoryRef")
    public SqlSessionFactory pandokolaSqlSessionFactory(@Qualifier("pandokolaDataSource") DataSource pandokolaDataSource, @Qualifier("pandokolaMybatisConfig")org.apache.ibatis.session.Configuration configuration) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(pandokolaDataSource);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

    @Primary
    @Bean(name = "pandokolaSessionTemplate")
    public SqlSessionTemplate pandokolaSessionTemplate(@Qualifier("pandokolaSqlSessionFactoryRef") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "pandokolaMybatisConfig")
    @ConfigurationProperties(prefix = "mybatis.configuration.pando-kola")
    public org.apache.ibatis.session.Configuration pandokolaMybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }
}
