package Intake2020.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement  //annotation기반 트랜잭션 활성화
@Configuration
public class TransactionConfig {
	
	//로딩
	@Autowired
	private ApplicationContext applicationContext;
	
	//application.properties에 데이터소스가 있기때문에 @autowired로 연결하기
	@Autowired
	DataSource dataSource;
	
	
	//스프링이 제공하는 트랜잭션 매니져 등록
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		//System.out.println("dataSource: "+dataSource);
		return new DataSourceTransactionManager(dataSource);
	}

	

	/* 참고용)) mybatis 설정부분 
	 * (mybatis는 dataSource, mapper설정)*/
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource(dataSource);
	    factoryBean.setVfs(SpringBootVFS.class);
	    
	    
	    //mapper 설정(mapper는 여러개 존재해서 아래와 같이 다중선택)
	    Resource[] mapperLocations = applicationContext.getResources("classpath:/mapper/**/mapper-*.xml");
	    
	    //  /mapper//의 의미는 mapper폴더 아래 모든 폴더를 의미함
	    // /mapper-*.xml의 의미는 mapper로 이름이 시작되는 모든 xml형식의 파일을 의미함 
	    
	    factoryBean.setMapperLocations(mapperLocations);
	    return factoryBean.getObject();
	}
	
	  
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		//System.out.println("sqlSessionFactory: "+sqlSessionFactory());
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	

}
