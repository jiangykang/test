
# 快速开发基础模板  

## 一、技术选型  
#### 1、系统环境  
- java EE                 8
- Servlet                 3.0
- Apache Maven            3
#### 2、主框架
- Spring Boot             2.1.3.RELEASE
- Spring Framework        5.0
- Apache Shiro            1.4.0
#### 3、持久层
- Apache MyBatis-Plus     3.3.1
- Alibaba Druid           1.1.20
## 二、内置功能
- 用户管理：用户是系统操作者，该功能主要完成系统用户配置。
- 部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
- 菜单管理：配置系统菜单，操作权限，按钮权限标识等。
- 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
- 字典管理：对系统中经常使用的一些较为固定的数据进行维护。。
- 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
- 登录日志：系统登录日志记录查询包含登录异常。
- 代码生成：前后端代码的生成（java、html、xml、sql)支持CRUD下载 。
- 系统接口：根据业务代码自动生成相关的api接口文档。
- 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
- 在线构建器：拖动表单元素生成相应的HTML代码。
- 连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。

## 三、环境部署
#### 1、准备工作
- JDK >= 1.8 (推荐1.8版本)
- Mysql >= 5.5.0 (推荐5.7版本)
- Maven >= 3.0
#### 2、必要配置
- 修改数据库连接
- 修改redis连接
#### 3、部署系统
- jar部署方式  
使用命令行执行：java –jar app.jar  
- war部署方式  
pom.xml packaging修改为war 放入tomcat服务器webapps
````
SpringBoot去除内嵌tomcat
 <!-- 多模块排除内置tomcat -->
 <dependency>
 	<groupId>org.springframework.boot</groupId>
 	<artifactId>spring-boot-starter-web</artifactId>
 	<exclusions>
 		<exclusion>
 			<groupId>org.springframework.boot</groupId>
 			<artifactId>spring-boot-starter-tomcat</artifactId>
 		</exclusion>
 	</exclusions>
 </dependency>
 		
 <!-- 单应用排除内置tomcat -->		
 <exclusions>
 	<exclusion>
 		<artifactId>spring-boot-starter-tomcat</artifactId>
 		<groupId>org.springframework.boot</groupId>
 	</exclusion>
 </exclusions>
````
## 四、核心技术
#### 1、SpringBoot框架
1、介绍  
Spring Boot是一款开箱即用框架，提供各种默认配置来简化项目配置。让我们的Spring应用变的更轻量化、更快的入门。 在主程序执行main函数就可以运行。你也可以打包你的应用为jar并通过使用java -jar来运行你的Web应用。它遵循"约定优先于配置"的原则， 使用SpringBoot只需很少的配置，大部分的时候直接使用默认的配置即可。可以与Spring Cloud的微服务无缝结合。

Spring Boot2.0 环境要求必须是jdk8或以上版本，Tomcat8或以上版本  
2、优点  
使编码变得简单： 推荐使用注解。  
使配置变得简单： 自动配置、快速构建项目、快速集成新技术能力 没有冗余代码生成和XML配置的要求  
使部署变得简单： 内嵌Tomcat、Jetty、Undertow等web容器，无需以war包形式部署  
使监控变得简单： 自带项目监控  
#### 2、Shiro安全控制
1、介绍  
Apache Shiro是Java的一个安全框架。Shiro可以帮助我们完成：认证、授权、加密、会话管理、与Web集成、缓存等。其不仅可以用在 JavaSE环境，也可以用在 JavaEE 环境。

2、优点      
- 易于理解的 Java Security API
- 简单的身份认证，支持多种数据源
- 对角色的简单的授权，支持细粒度的授权
- 不跟任何的框架或者容器捆绑，可以独立运行   

3、特性  
- Authentication身份认证/登录，验证用户是不是拥有相应的身份  
- Authorization授权，即验证权限，验证某个已认证的用户是否拥有某个权限，即判断用户是否能做事情 SessionManagement会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中  
- Cryptography加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储  
- Caching缓存，比如用户登录后，其用户信息，拥有的角色/权限不必每次去查，提高效率  
- ConcurrencyShiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去  
- Testing提供测试支持  
- RunAs允许一个用户假装为另一个用户（如果他们允许）的身份进行访问  
- RememberMe记住我，这是非常常见的功能，即一次登录后，下次再来的话不用登录了  

4、架构  
- Subject主体，代表了当前的“用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫， 机器人等；即一个抽象概念；所有Subject都绑定到SercurityManager，与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一个门面；SecurityManager才是实际的执行者  
- SecurityManage安全管理器；即所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject； 可以看出它是Shiro的核心，它负责与后边介绍的其他组件进行交互  
- Realm域，Shiro从Realm获取安全数据（如用户，角色，权限），就是说SecurityManager要验证用户身份， 那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以有1个或多个Realm，我们一般在应用中都需要实现自己的Realm  
- SessionManager如果写过Servlet就应该知道Session的概念，Session需要有人去管理它的生命周期，这个组件就是SessionManager  
- SessionDAODAO大家都用过，数据库访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，也可以写入缓存，以提高性能  
- CacheManager缓存控制器，来管理如用户，角色，权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能  
- 应用代码通过Subject来进行认证和授权，而Subject又委托给SecurityManager； 我们需要给Shrio的SecurityManager注入Realm，从而让SecurityManager能得到合法的用户及其权限进行判断，Shiro不提供维护用户/权限，而是通过Realm让开发人员自己注入。  
- Shiro不会去维护用户，维护权限；这些需要自己去设计/提供；然后通过响应的接口注入给Shiro即可  

## 五、主要实现
#### 1、分页
使用MyBatis-Plus分页插件
````//Spring boot方式
    @EnableTransactionManagement
    @Configuration
    @MapperScan("com.baomidou.cloud.service.*.mapper*")
    public class MybatisPlusConfig {
    
        @Bean
        public PaginationInterceptor paginationInterceptor() {
            PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
            // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
            // paginationInterceptor.setOverflow(false);
            // 设置最大单页限制数量，默认 500 条，-1 不受限制
            // paginationInterceptor.setLimit(500);
            // 开启 count 的 join 优化,只针对部分 left join
            paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
            return paginationInterceptor;
        }
    }
````  
XML 自定义分页  
- UserMapper.java 方法内容
````
public interface UserMapper {//可以继承或者不继承BaseMapper
    /**
     * <p>
     * 查询 : 根据state状态查询用户列表，分页显示
     * </p>
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param state 状态
     * @return 分页对象
     */
    IPage<User> selectPageVo(Page<?> page, Integer state);
}
````
- UserMapper.xml 等同于编写一个普通 list 查询，mybatis-plus 自动替你分页
````
<select id="selectPageVo" resultType="com.baomidou.cloud.entity.UserVo">
    SELECT id,name FROM user WHERE state=#{state}
</select>
````
- UserServiceImpl.java 调用分页方法
````
public IPage<User> selectUserPage(Page<User> page, Integer state) {
    // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
    // page.setOptimizeCountSql(false);
    // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
    // 要点!! 分页返回的对象与传入的对象是同一个
    return userMapper.selectPageVo(page, state);
}
````

#### 2、导入导出
#### 3、上传下载
#### 4、事务管理
#### 5、异常统一处理
#### 6、系统日志
#### 7、数据权限
#### 8、代码生成
## 六、项目结构

#### 文件结构
```` 
│                                                                           //项目结构
│  pom.xml                                                                  //项目依赖
│  README.md                                                                //项目介绍  
│    
└─src
    └─main  
        ├─java  
        │  └─com  
        │      └─ls 
        │          │  
        │          │  BussinessServiceApplication.java                      //项目启动文件
        │          │  
        │          ├─system                                                 //系统  
        │          │  │
        │          │  ├─config                                              //配置类
        │          │  │      CustomToken.java                               //密码登录 免密登录区分
        │          │  │      DruidConfig.java                               //Druid配置（数据监控）
        │          │  │      MybatisPlusConfig.java                         //MybatisPlus配置（数据持久层）
        │          │  │      MyRetryLimitCredentialsMatcher.java            //免密登录配置
        │          │  │      MySessionManager.java                          //用sessionId作为token配置
        │          │  │      MyShiroRealm.java                              //shiro鉴权 认证
        │          │  │      RestTemplateConfig.java                        //RestTemplate配置
        │          │  │      ScheduleTask.java                              //定时任务
        │          │  │      ShiroConfig.java                               //shiro 配置
        │          │  │      TaskPoolConfig.java                            //异步线程 配置
        │          │  │
        │          │  ├─constant                                            //常量
        │          │  │      Constants.java                                 //信息常量
        │          │  │
        │          │  ├─controller                                          //控制层
        │          │  │      SysBlockChainController.java                   //区块链相关控制层
        │          │  │      SysCaptchaController.java                      //验证码相关控制层   
        │          │  │      SysDeptController.java                         //部门（组织）管理控制层
        │          │  │      SysDictDataController.java                     //数据字典数据控制层
        │          │  │      SysDictTypeController.java                     //数据字典类型控制层
        │          │  │      SysLoginController.java                        //登录相关控制层
        │          │  │      SysLoginInfoController.java                    //访问日志管理控制层
        │          │  │      SysMenuController.java                         //菜单（权限）管理控制层
        │          │  │      SysOperLogController.java                      //操作日志管理控制层
        │          │  │      SysRoleController.java                         //角色管理控制层
        │          │  │      SysServerController.java                       //系统监控控制层
        │          │  │      SysSyncDataController.java                     //idm同步管理控制层    
        │          │  │      SysUserController.java                         //用户管理控制层
        │          │  │      SysUtilController.java                         //系统管理控制层
        │          │  │      
        │          │  ├─entity                                              //实体
        │          │  │  │  
        │          │  │  ├─common                                           //共通
        │          │  │  │      PreventionCommonEntity.java                 //系统共用继承实体
        │          │  │  │      SysResourceTree.java                        //前端菜单 格式
        │          │  │  │      TreeSelect.java                             //树形label
        │          │  │  │      
        │          │  │  └─server                                           //服务器实体
        │          │  │          
        │          │  ├─enums                                               //枚举
        │          │  │      BusinessStatus.java                            //操作状态
        │          │  │      BusinessType.java                              //业务操作类型
        │          │  │      LoginType.java                                 //登录模式
        │          │  │      OperatorType.java                              //操作人类别
        │          │  │                         
        │          │  ├─exception                                           //异常
        │          │  │      ExceptionHander.java                           //异常统一处理
        │          │  │      
        │          │  ├─interceptor                                         //拦截器
        │          │  │  │  RepeatSubmitInterceptor.java                    //防止重复提交拦截器
        │          │  │  │                  
        │          │  │  ├─annotation                                       //自定义注解
        │          │  │  │      DataScope.java                              //数据权限过滤注解
        │          │  │  │      Excel.java                                  //自定义导出Excel数据注解
        │          │  │  │      Excels.java                                 //Excel注解集
        │          │  │  │      Log.java                                    //自定义操作日志记录注解 
        │          │  │  │      RepeatSubmit.java                           //自定义注解防止表单重复提交  
        │          │  │  │              
        │          │  │  ├─aspect                                           //拦截器  
        │          │  │  │      DataScopeAspect.java                        //数据权限拦截器  
        │          │  │  │      LogAspect.java                              //日志拦截器
        │          │  │  │      MyAdviceFilter.java                         //shiro拦截器  （未开启）
        │          │  │  └─impl     
        │          │  │          SameUrlDataInterceptor.java                //防止表单重复提交
        │          │  │                 
        │          │  ├─mapper                                              //持久层接口      
        │          │  │             
        │          │  ├─mapstruct                                           //实体转换    
        │          │  │             
        │          │  ├─service                                             //业务层接口
        │          │  │  │  
        │          │  │  └─impl                                             //业务层实现
        │          │  │          
        │          │  ├─utils                                               //工具类
        │          │  │      AddressUtils.java                              //获取地址类
        │          │  │      ArithUtils.java                                //精度算法类
        │          │  │      Base64Utils.java                               //Base64工具类     
        │          │  │      Convert.java                                   //类型转换器
        │          │  │      DateUtils.java                                 //时间工具类
        │          │  │      ExcelUtil.java                                 //Excel相关处理
        │          │  │      GenerateIdUtil.java                            //生成自定义id
        │          │  │      HttpUtils.java                                 //通用http发送方法
        │          │  │      IpUtils.java                                   //获取IP方法
        │          │  │      PasswordUtils.java                             //密码相关
        │          │  │      RedisCache.java                                //redis相关
        │          │  │      ReflectUtils.java                              //反射工具类
        │          │  │      RSAUtils.java                                  //RSA加密工具包
        │          │  │      ServletUtils.java                              //客户端工具类
        │          │  │      StringUtils.java                               //字符串工具类
        │          │  │      UserInfoUtils.java                             //用户信息
        │          │  │      VerifyCodeUtils.java                           //验证码工具类
        │          │  │             
        │          │  ├─viewform                                            //接口入参
        │          │  │          
        │          │  └─vo                                                      //特定出参
        │          │          ContextUserInfo.java                              //登录人信息
        │          │          MetaVo.java                                       //配合前端路由
        │          │          RouterVo.java                                     //配合前端路由
        │          │          SysDeptVO.java                
        │          │          SysMenuVO.java                
        │          │          SysRoleVO.java                
        │          │                        
        │          └─tool                                                       //代码生成工具（不需要时直接删除该文件夹）        │                      
        └─resources
            │  application.yml                                                  //yml配置文件
            │  data.sql                                                         //初始化sql
            │  
            ├─mapper                                                            //mapper 层
            │  ├─system                                                         //系统相关sql
            │  │      
            │  └─tool                                                           //代码生成sql 
            │              
            └─vm                                                                //代码生成模板

````
#### 配置文件
````
# application.yml
server:
  port: 8081
spring:
  resources:
    static-locations:
      - classpath:resources
      - classpath:static
  application:
    name: lsprevention
  #数据库相关配置
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://********/********?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false
    username: ********
    password: ********
    #如果需要做初始化,将其改为always
    initialization-mode: never
    data:
      - classpath:data.sql
    type: com.alibaba.druid.pool.DruidDataSource
    #druid相关配置
    druid:
      initial-size: 3
      min-idle: 3
      max-active: 10
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置 StatFilter
      filter:
        stat:
          log-slow-sql: true
          slow-sql-millis: 20000
      validation-query: select 1
  activiti:
    check-process-definitions: false
    database-schema-update: none
  #模板引擎
  thymeleaf:
    mode: HTML
    encoding: utf-8
    # 禁用缓存
    cache: false
  #redis配置
  redis:
    database: 1
    host: ********
    port: 6379
    password: ********
    timeout: 1800000
#erp相关配置
erpuser:
  tokenurl: ********
  ssourl: ********
  orgurl: ********
  empurl: ********
  tokenusername: ********
  tokenpassword: ********
  tokenplatfrom: ********

#区块链相关配置
blockChain:
  baseurl: ********
  geturl: ********
  seturl: ********
  getlist: ********
  getDetail: ********

#swagger 扫描的包路径,如果用多个用,号隔开   
swagebasepackage: com.ls.*.controller
mybatis-plus:
  mapper-locations: mapper/*/*.xml
  type-aliases-package: com.ls.*.entity
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
#文件上传到阿里云配置
oss:
  projectname: ********
  ossurl: ********
  endpoint: ********
  accesskeyid: ********
  accesskeysecret: ********
  bucketName: ********
#代码生成相关配置
gen:
  # 作者
  author: Min.Hu
  # 默认生成包路径 business 需改成自己的模块名称 如 business monitor tool
  packageName: com.ls.business
  # 自动去除表前缀，默认是true
  autoRemovePre: false
  # 表前缀（生成类名不会包含表前缀，多个用逗号分隔）
  tablePrefix: business_

````

