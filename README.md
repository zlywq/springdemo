与 note.txt 内容完全相同，并且以 note.txt 为准。
<br/>因为这里如果不特意支持markdown格式，网页上的排版就比较乱，不一定同步更新。


<br/>这个例子的目标是达到入门并可以实用。
<br/>关键词: spring4 + spring security4 + mybatis3 + spring mvc + junit4 , maven
<br/>在spring-tool-suite-3.6.4.RELEASE-e4.4.2-win32-x86_64里面开发调试通过。一些环境是jdk1.7.0_75, apache-tomcat-7.0.63.
<br/>在sts中可以以import -- existing maven project的方式导进来。
<br/>&emsp;注意某些project或module下面没有 src/test/java , src/test/resources ，建议在import..maven..project的时候先建立这些目录，不然还得在eclipse中配置一阵。


<br/>spring的web项目在maven的支持下分成了4个项目。
<br/>&emsp;一个通用lib，不含spring的任何东西。不会引用其他项目。
<br/>&emsp;一个含有对spring的任何东西的调用或引用的lib，设想的层级是从dao到service层。可以引用其他的lib项目。
<br/>&emsp;一个spring的web项目，含有web所特有的controller层。
<br/>&emsp;一个spring的desk项目，备用，比如在单元测试外再给出一种执行方式，可以在调试或测试时使用。
<br/>&emsp;设想中web项目和desk项目都是顶层项目，不会被其他项目所引用。

<br/>目前暂时决定让顶层的pom.xml包含尽可能多的<dependency>，这样可以简化其他module中的pom.xml。

<br/>在sts的环境中先建立smmpTop的maven的pom工程，然后点中这个工程，选new .. project，以maven project的方式new出smmpLibCommon,smmpLibSpr,smmpDesk，以spring maven web project的方式new出smmpWeb，并注意生成并添加到Working Set。
<br/>&emsp;之所以各个project或artifactId都使用smmp的前缀，是为了让这些project在eclipse的项目区中能够紧靠在一起，而且似乎artifactId就决定了project的名字(比如pom.xml中的<name>就不能影响project的名字)。
<br/>&emsp;&emsp;不过后来发现项目区可以切换成以Working Set为顶级区域显示，倒是不必要这个前缀了。
<br/>&emsp;注意各个pom.xml的改动还得靠手工在文本编辑器中改动，不能靠点鼠标操作的功能向导来做，比如top的pom.xml的<module>还得自行添加。
<br/>&emsp;在pom.xml中添加依赖关系，ide环境看来能够马上感知。


<br/>最简例子是在各个项目里面各放了一个很简单的类，来体现项目间的依赖调用关系。
<br/>在ide中调试web项目时会遇到一点问题。
<br/>&emsp;web项目中引用调用了各个lib的类，在编译时没有问题。但在ide中调试运行时执行到web项目中对其他lib的类有引用调用的类时，有时会报classNotFound的错。
<br/>&emsp;&emsp;后来发现的一种解决办法是稍微改改最顶层的pom.xml里面的东西，此时似乎会导致某些动作的执行或更新，然后在ide中调试运行就正常了。





<br/>spring 与其他模块整合
<br/>&emsp;在web.xml中有两处地方可以加载spring的配置文件，比如<context-param>和<servlet>，但是在<servlet>中加载，会遇到一些坑，比如导致@service的类的事务失效。
<br/>&emsp;&emsp;所以目前决定只使用一处，即<context-param>。而<servlet>加载的那个文件不放实际内容。如

        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/root-context.xml</param-value>
        </context-param>
<br/>&emsp;加载spring的文件，可以利用<import>来分模块。

<br/>与ibatis-mybatis整合
<br/>&emsp;参考spring-backend.xml。
<br/>&emsp;其中一些db名，账号密码被提取到了一个单独的dbconnect.properties文件中，这样方便维护。
<br/>&emsp;在spring配置文件中，用如下方式加载

    <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="locations">
            <value>classpath:dbconnect.properties</value>
        </property>
    </bean>

<br/>&emsp;然后以"${key}"的形式使用，如下

    <bean id="dataSource" destroy-method="close" class="org.apache.commons.dbcp.BasicDataSource">
        <property name="driverClassName" value="${database.driver}" />
        <property name="url" value="${database.url}" />
        <property name="username" value="${database.username}" />
        <property name="password" value="${database.password}" />
        <property name="testWhileIdle" value="true"></property>
        <property name="validationQuery" value="SELECT 1"></property>
        <property name="testOnBorrow" value="true"></property>
    </bean>

<br/>&emsp;目前的整合，除了配置了dataSource，其他的配置如下

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource" />
    </bean>
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />        
        <property name="mapperLocations" value="classpath*:mappers/**/*.xml" /> <!-- 也可用下面scan的方式 -->
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="spr2.ibatisMapper" />
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
	  <constructor-arg index="0" ref="sqlSessionFactory" />
    </bean>

<br/>注意支持@Service,@Autowired等的关键配置语句是<context:component-scan base-package="pkg1,pkg2" />


<br/>与spring security整合
<br/>&emsp;注意需要在web.xml中加

      <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
      </filter>
      <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>

<br/>&emsp;其他就是设置<http>等的配置了，可以写在一个独立的spring-security.xml模块文件里。
<br/>&emsp;&emsp;注意其中如果想自定义一些东西，尽量使用细粒度元素给的扩展，比如<form-login>中的authentication-success-handler-ref之类的，而不要替代<form-login>这个粒度，因为这个bean的某些设置是难于用xml配置来替代已有的代码配置的，跟踪半天代码都不一定彻底搞明白。

<br/>关于spring mvc
<br/>&emsp;关于页面的中文支持，大概要设置两个地方。
<br/>&emsp;&emsp;一个是web.xml中的

	<filter>  
	    <filter-name>encodingFilter</filter-name>  
	    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
	    <init-param>  
	       <param-name>encoding</param-name>  
	       <param-value>UTF-8</param-value>  
	    </init-param>  
	    <init-param>  
	       <param-name>forceEncoding</param-name>  
	       <param-value>true</param-value>  
	    </init-param>  
	</filter>

<br/>&emsp;&emsp;一个是.jsp中的

	<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>

<br/>&emsp;在模块组织上使用了一个include.jsp，包含各个taglib。
<br/>&emsp;&emsp;其中使用了一个自定义的taglib，是

    <%@ taglib prefix="mytld" uri="/WEB-INF/jstlTld/jstl.tld" %> 。
    
<br/>&emsp;所有与web相关的配置在spring-front.xml中
<br/>&emsp;&emsp;其中注意 org.springframework.web.servlet.view.InternalResourceViewResolver 的prefix的设置要对应到实际的目录路径。
<br/>&emsp;这里已经对html和json的呈现做了支持，xml还支持得不好。

<br/>这里的log机制使用的是slf4j+logback。


<br/>与junit整合
<br/>&emsp;参考smmpLibSpr里面的TestPost.java所涉及的东西。





















