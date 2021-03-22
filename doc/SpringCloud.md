# SpringCloud(H版)

## 版本选择

https://start.spring.io/actuator/info

```json
{
	"git": {
		"branch": "100993521962b2a36eebcca56205ca0345bfecf5",
		"commit": {
			"id": "1009935",
			"time": "2021-03-19T13:15:52Z"
		}
	},
	"build": {
		"version": "0.0.1-SNAPSHOT",
		"artifact": "start-site",
		"versions": {
			"spring-boot": "2.4.4",
			"initializr": "0.11.0-SNAPSHOT"
		},
		"name": "start.spring.io website",
		"time": "2021-03-19T13:18:08.098Z",
		"group": "io.spring.start"
	},
	"bom-ranges": {
		"azure": {
			"2.2.4": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1"
		},
		"codecentric-spring-boot-admin": {
			"2.2.4": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"2.3.1": "Spring Boot >=2.3.0.M1 and <2.5.0-M1"
		},
		"solace-spring-boot": {
			"1.0.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.1.0": "Spring Boot >=2.3.0.M1 and <2.5.0-M1"
		},
		"solace-spring-cloud": {
			"1.0.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.1.1": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"2.0.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1"
		},
		"spring-cloud": {
			"Hoxton.SR10": "Spring Boot >=2.2.0.RELEASE and <2.3.10.BUILD-SNAPSHOT",
			"Hoxton.BUILD-SNAPSHOT": "Spring Boot >=2.3.10.BUILD-SNAPSHOT and <2.4.0.M1",
			"2020.0.0-M3": "Spring Boot >=2.4.0.M1 and <=2.4.0.M1",
			"2020.0.0-M4": "Spring Boot >=2.4.0.M2 and <=2.4.0-M3",
			"2020.0.0": "Spring Boot >=2.4.0.M4 and <=2.4.0",
			"2020.0.2": "Spring Boot >=2.4.1 and <2.5.0-M1",
			"2020.0.3-SNAPSHOT": "Spring Boot >=2.4.5-SNAPSHOT"
		},
		"spring-cloud-alibaba": {
			"2.2.1.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1"
		},
		"spring-cloud-gcp": {
			"2.0.0": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		},
		"spring-cloud-services": {
			"2.2.6.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.RELEASE",
			"2.3.0.RELEASE": "Spring Boot >=2.3.0.RELEASE and <2.4.0-M1",
			"2.4.1": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		},
		"spring-geode": {
			"1.2.12.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"1.3.9.RELEASE": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"1.4.3": "Spring Boot >=2.4.0-M1 and <2.5.0-M1",
			"1.5.0-M2": "Spring Boot >=2.5.0-M1"
		},
		"vaadin": {
			"14.4.10": "Spring Boot >=2.1.0.RELEASE and <2.5.0-M1"
		},
		"wavefront": {
			"2.0.2": "Spring Boot >=2.1.0.RELEASE and <2.4.0-M1",
			"2.1.0": "Spring Boot >=2.4.0-M1"
		}
	},
	"dependency-ranges": {
		"native": {
			"0.9.0": "Spring Boot >=2.4.3 and <2.4.4-SNAPSHOT",
			"0.9.1-SNAPSHOT": "Spring Boot >=2.4.4-SNAPSHOT and <2.5.0-M1"
		},
		"okta": {
			"1.4.0": "Spring Boot >=2.2.0.RELEASE and <2.4.0-M1",
			"1.5.1": "Spring Boot >=2.4.0-M1 and <2.4.1",
			"2.0.1": "Spring Boot >=2.4.1 and <2.5.0-M1"
		},
		"mybatis": {
			"2.1.4": "Spring Boot >=2.1.0.RELEASE and <2.5.0-M1"
		},
		"camel": {
			"3.3.0": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.5.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"3.8.0": "Spring Boot >=2.4.0.M1 and <2.5.0-M1"
		},
		"open-service-broker": {
			"3.1.1.RELEASE": "Spring Boot >=2.2.0.RELEASE and <2.3.0.M1",
			"3.2.0": "Spring Boot >=2.3.0.M1 and <2.4.0-M1",
			"3.3.0": "Spring Boot >=2.4.0-M1 and <2.5.0-M1"
		}
	}
}
```

## 微服务构建

**约定>配置>编码**，微服务cloud整体聚合工程

### 父工程

- New Project
- 聚合总父工程名字
- Maven的版本
- 工程名字
- 字符编码
- 注解生效激活
- java编译版本选择8
- File Types过滤

父工程POM

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.atguigu.springcloud</groupId>
  <artifactId>cloud2020</artifactId>
  <version>1.0-SNAPSHOT</version>

  <packaging>pom</packaging>

  <!--统一管理jar包版本-->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!--子模块继承之后，提供作用：锁定版本+子module不用groupId和version-->
  <dependencyManagement>
    <dependencies>
      <!--spring boot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.2.2.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>Hoxton.SR1</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>2.1.0.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
        <optional>true</optional>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

将父工程install到仓库中方便子工程继承

### Rest微服务工程搭建

- 建Module
- 改POM
- 写YML
- 主启动
- 业务类
- 测试

### cloud-provider-payment8001

微服务提供者Module模块

#### pom文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.atguigu.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
~~~

#### application.yml

~~~yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource      #当前数据源操作类型
    driver-class-name: org.gjt.mm.mysql.Driver        #mysql驱动包
    url: jdbc:mysql://localhost:3306/db2019?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.atguigui.springcloud.entities       #所有Entity别名类所在包
~~~

#### 主启动类

~~~java
@SpringBootApplication
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
~~~

#### 建表sql

~~~sql
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付流水号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
~~~

#### entities

主实体：

~~~java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    
    private Long id;
    private String serial;

}
~~~

Json封装体CommonResult：

~~~java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;
}
~~~

#### dao

接口：

~~~java
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
~~~

mybatis映射文件：

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.springcloud.dao.PaymentDao">

    <insert id="create" parameterType="com.atguigu.springcloud.entities.Payment" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO payment(serial) values(#{serial});
    </insert>
    
    <resultMap id="BaseResultMap" type="com.atguigu.springcloud.entities.Payment">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <id column="serial" property="serial" jdbcType="VARCHAR"/>
    </resultMap>
    
    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        SELECT * FROM payment WHERE id=#{id};
    </select>
</mapper>
~~~

#### service

接口：

~~~java
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
~~~

实现：

~~~java
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
~~~

#### controller

~~~java
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果*******" + result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果*******" + payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功",payment);
        }else {
            return new CommonResult(444,"没有对应记录,查询ID:"+id,null);
        }
    }
}
~~~

### cloud-consumer-order80

#### pom文件

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.atguigu.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
~~~

#### application.yml

~~~yml
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order80
~~~

#### 主启动类

~~~java
@SpringBootApplication
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
~~~

#### entities

主实体：

~~~java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;
    private String serial;
}
~~~

Json封装体CommonResult：

~~~java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;
}
~~~

#### config

~~~java
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
~~~

#### controller

~~~java
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
~~~

### 工程重构

发现系统中重复部分entities包

新建工程**cloud-api-common**

pom文件

~~~xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>
~~~

entities包的抽取，使用maven clean，install到仓库中。

将cloud-provider-payment8001、cloud-consumer-order80的entities包删除，引入cloud-api-common坐标即可。

## Eureka服务注册与发现

Eureka采用CS的设计架构，Eureka Server作为服务注册功能的服务器，它是服务注册中心。而系统中的其它微服务，使用Eureka的客户端连接到Eureka Server并维持心跳连接。这样系统的维护人员就可以通过Eureka Server来监控系统中各个微服务是否正常运行。

在服务注册和发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息，比如服务器地址通讯地址等以别名方式注册到注册中心中。另一方（消费者|服务提供者），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地RPC调用远程框架核心设计思想：在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念）。在任何RPC远程框架中，都会有一个注册中心（存放服务地址相关信息（接口地址））。

![Eureka系统架构与Dubbo架构](https://github.com/jackhusky/springcloud/blob/master/images/Eureka%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E4%B8%8EDubbo%E6%9E%B6%E6%9E%84.png)

### Eureka两个组件

Eureka Server提供服务注册服务，各个微服务节点通过配置启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。

Eureka Client是一个java客户端，用于简化与Eureka Server的交互，客户端同时也就是一个内置的、使用轮询(round-robin)负载算法的负载均衡器。在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。

### 单机Eureka的构建

#### 服务端

工程cloud-eureka-server7001：pom文件

~~~xml
<dependencies>
        <!--eureka-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!--引入自己定义的api通用包-->
        <dependency>
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-common</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--boot web actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般为通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
~~~

application.yml

~~~yaml
server:
  port: 7001
eureka:
  instance:
    hostname: localhost
  client:
    # false表示不向注册中心注册自己。
    register-with-eureka: false
    # false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务。
    fetch-registry: false
    service-url:
      # 设置与eureka server交互的地址，查询服务和注册服务都需要依赖这个地址。
      defaultZone: htttp://${eureka.instance.hostname}:${server.port}/eureka/
~~~

主启动类

~~~java
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
~~~

访问：http://localhost:7001/

#### 客户端

服务提供者：cloud-provider-payment8001

pom文件

~~~xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
~~~

application.yml

~~~yaml
eureka:
  client:
    # 表示将自己注册进eureka server，默认为true
    register-with-eureka: true
    # 是否从eureka server抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡。
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka/
~~~

主启动类

~~~java
@SpringBootApplication
@EnableEurekaClient
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
~~~

启动Eureka服务后访问http://localhost:7001/

![eureka截图1.png](https://github.com/jackhusky/springcloud/blob/master/images/eureka%E6%88%AA%E5%9B%BE1.png)

服务消费者 cloud-consumer-order80 按照 cloud-provider-payment8001 配置即可注册到Eureka服务中。

### 集群Eureka构建

原理：

![eureka集群](https://github.com/jackhusky/springcloud/blob/master/images/eureka%E9%9B%86%E7%BE%A4.png)

**问题：微服务RPC远程服务调用最核心的是什么？**

高可用，试想你的注册中心只有一个，它出故障那就呵呵了，会导致整个服务环境不可用。

**解决：搭建Eureka注册中心集群，实现负载均衡+故障容错。**

搭建cloud-eureka-server7002，参照cloud-eureka-server7001即可

修改映射配置添加hosts文件

~~~hosts
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
~~~

修改它们的yml，互相注册：

cloud-eureka-server7001：

~~~yaml
server:
  port: 7001
spring:
  application:
    name: cloud-eureka-service
eureka:
  instance:
    # eureka服务端的实例名称
    hostname: eureka7001.com
  client:
    # false表示不向注册中心注册自己
    register-with-eureka: false
    # false表示自己端就是注册中心,我的职责就是维护服务实例,并不需要检索服务
    fetch-registry: false
    service-url:
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://eureka7002.com:7002/eureka/
~~~

cloud-eureka-server7002：

~~~yaml
server:
  port: 7002
spring:
  application:
    name: cloud-eureka-service2
eureka:
  instance:
    # eureka服务端的实例名称
    hostname: eureka7002.com
  client:
    # false表示不向注册中心注册自己
    register-with-eureka: false
    # false表示自己端就是注册中心,我的职责就是维护服务实例,并不需要检索服务
    fetch-registry: false
    service-url:
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://eureka7001.com:7001/eureka/
~~~

访问它们的服务可以看到互相注册服务

### Eureka客户端的集群配置

修改cloud-provider-payment8001、cloud-consumer-order80的application.yml配置

~~~yaml
eureka:
  client:
    # 表示将自己注册进eureka server，默认为true
    register-with-eureka: true
    # 是否从eureka server抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡。
    fetch-registry: true
    service-url:
      # 单机版
#      defaultZone: http://localhost:7001/eureka/
      # 集群版
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
~~~

分别启动它们，调用接口成功！http://localhost/consumer/payment/get/31

支付服务的集群搭建，cloud-provider-payment8002按照cloud-provider-payment8001搭建即可

在 `PaymentController` 中添加端口的显示，测试负载均衡。

~~~java
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果*******" + result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,O(∩_∩)O哈哈~,端口号是:" + serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果*******" + payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,端口号是:" + serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录,查询ID:"+id,null);
        }
    }
}
~~~

订单服务中的访问支付服务地址不能写死修改为：

~~~java
// 通过在eureka上注册过的微服务名称调用
public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
~~~

并使用 `@LoadBalanced`注解修改`RestTemplate`组件为带有负载均衡能力的

~~~java
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
~~~

依次启动eureka集群、支付服务集群、订单服务，并调用接口可以看到结果中8001/8002端口交替出现

Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号,且该服务还有负载功能了.

### actuator微服务信息完善

eureka监控页面中注册服务中(主机名称:服务名称修改)，以及服务ip的显示。例：

~~~yaml
eureka:
  client:
    # 表示将自己注册进eureka server，默认为true
    register-with-eureka: true
    # 是否从eureka server抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡。
    fetch-registry: true
    service-url:
      # 单机版
#      defaultZone: http://localhost:7001/eureka/
      # 集群版
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
  instance:
    # 修改服务名字
    instance-id: payment8001
      # 显示服务的ip
    prefer-ip-address: true
~~~

### 服务发现Discovery

对于注册eureka里面的微服务,可以通过服务发现来获得该服务的信息。

修改cloud-provider-payment8001的controller

~~~java
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(">>>>>service: {}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(">>>>instance: serviceId = {}, host = {}, port = {}, uri = {}", instance.getServiceId(),
                    instance.getHost(), instance.getPort(), instance.getUri());
        }
        return this.discoveryClient;
    }
~~~

主启动类：

~~~java
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
~~~

测试http://localhost:8001/payment/discovery

### eureka自我保护

保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就不会注销任何服务。

**为什么产生Eureka自我保护机制？**

为了防止EurekaClient可以正常运行，但是与Eureka网络不通情况下，EurekaServer不会立刻将EurekaClient服务剔除。

默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生（延时、卡顿、拥挤）时，微服务与Eureka Server之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka通过“自我保护模式”来解决这个问题——当Eureka Server节点短时间内丢失了过多客户端时（可能发生了网络分区故障），那么这个节点就进入自我保护模式。

在自我保护模式下，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。**属于CAP里面的AP分支**

#### 怎么禁止自我保护模式

注册中心cloud-eureka-server7001、cloud-eureka-server7002修改为：

~~~yaml
eureka:
  instance:
    # eureka服务端的实例名称
    hostname: eureka7001.com
  client:
    # false表示不向注册中心注册自己
    register-with-eureka: false
    # false表示自己端就是注册中心,我的职责就是维护服务实例,并不需要检索服务
    fetch-registry: false
    service-url:
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个地址
      defaultZone: http://eureka7002.com:7002/eureka/
  server:
    # 关闭自我保护机制
    enable-self-preservation: false
~~~

生产者客户端cloud-provider-payment8001

~~~yaml
eureka:
  client:
    # 表示将自己注册进eureka server，默认为true
    register-with-eureka: true
    # 是否从eureka server抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡。
    fetch-registry: true
    service-url:
      # 单机版
#      defaultZone: http://localhost:7001/eureka/
      # 集群版
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
  instance:
    # 修改服务名字
    instance-id: payment8001
    # 显示服务的ip
    prefer-ip-address: true
    # 指示eureka客户端需要多长时间（以秒为单位）将心跳发送到eureka服务器
    lease-renewal-interval-in-seconds: 30
    # Eureka服务端在收到最后一次心跳后等待时间上限 ,单位为秒(默认是90秒),超时剔除服务
    lease-expiration-duration-in-seconds: 40
~~~

启动cloud-eureka-server7001、cloud-eureka-server7002、cloud-provider-payment8001，再停止生产者客户端cloud-provider-payment8001后发现cloud-provider-payment8001被剔除了

## Zookeeper服务注册与发现

新建cloud-provider-payment8004工程

pom.xml

~~~xml
    <dependencies>
        <dependency><!-- 引用自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-common</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--SpringBoot整合Zookeeper客户端-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <exclusions>
                <!--先排除自带的zookeeper3.5.3-->
                <exclusion>
                    <artifactId>zookeeper</artifactId>
                    <groupId>org.apache.zookeeper</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.14版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.14</version>
            <exclusions>
                <exclusion>
                    <artifactId>slf4j-log4j12</artifactId>
                    <groupId>org.slf4j</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
~~~

application.yml

~~~yaml
server:
  port: 8004

spring:
  application:
    name: cloud-payment-service

  cloud:
    zookeeper:
      connect-string: 192.168.44.139:2181
~~~

主启动类：

~~~java
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8004 {
    
    public static void main(String[] args) {
        SpringApplication.run(Payment8004.class, args);
    }
}
~~~

controller

```java
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk() {
        return "Spring Cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
```

调用接口localhost:8004/payment/zk正常返回，且服务注册到zookeeper

![服务注册到zookeeper](https://github.com/jackhusky/springcloud/blob/master/images/服务注册到zookeeper.png)

节点是临时节点

![zookeeper临时节点](https://github.com/jackhusky/springcloud/blob/master/images/zookeeper临时节点.png)

新建cloud-consumerzk-order80工程，按照cloud-provider-payment8004的配置注册到zookeeper中

测试接口通过：localhost/consumer/payment/zk

## Consul服务注册与发现

### 什么是Consul

Consul是HashiCorp公司推出的开源工具，Consul由Go语言开发，部署起来非常容易，只需要极少的可执行程序和配置文件，具有绿色、轻量级的特点。Consul是分布式的、高可用的、 可横向扩展的用于实现分布式系统的服务发现与配置。

### Consul的特点

- 服务发现：提供HTTP/DNS两种发现方式
- 健康监测：支持多种方式，HTTP、TCP、Docker、shell脚本地址华
- KV存储：Key、Value的存储方式
- 多数据中心：Consul支持多数据中心
- 可视化界面

### 使用

查看版本号：consul --version

运行：consul agent -dev

访问Consul的首页：http://localhost:8500

新建cloud-providerconsul-payment8006工程

~~~xml
        <!--SpringCloud consul-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
~~~

~~~yaml
server:
  # consul服务端口
  port: 8006
spring:
  application:
    name: cloud-provider-payment
  cloud:
    consul:
      # consul注册中心地址
      host: localhost
      port: 8500
      discovery:
        hostname: 127.0.0.1
        service-name: ${spring.application.name}
~~~

![8006注册到consul](https://github.com/jackhusky/springcloud/blob/master/images/8006注册到consul.png)

新建cloud-consumerconsul-order80工程，按照cloud-providerconsul-payment8006注册到Consul

测试访问地址http://localhost/consumer/payment/consul正常返回

## 三个注册中心异同点

| 组件      | 语言 | CAP  | 服务健康检查 | 对外暴露接口 | Spring Cloud集成 |
| --------- | ---- | ---- | ------------ | ------------ | ---------------- |
| Eureka    | Java | AP   | 可配支持     | HTTP         | 是               |
| Consul    | GO   | CP   | 支持         | HTTP/DNS     | 是               |
| Zookeeper | Java | CP   | 支持         | 客户端       | 是               |

分区容错性要保证,所以要么是CP,要么是AP；

- C: Consistency(强一致性)
- A: Availability(可用性)
- P: Parttition tolerance(分区容错性)

CAP理论关注粒度是否是数据，而不是整体系统设计的策略

![CAP理论图](https://github.com/jackhusky/springcloud/blob/master/images/CAP理论图.png)

Eureka自我保护，好死不如赖活着

![保证高可用AP](https://github.com/jackhusky/springcloud/blob/master/images/保证高可用AP.png)

Zookeeper临时节点，有就是有，没有就是没有

![保证一致性CP](https://github.com/jackhusky/springcloud/blob/master/images/保证一致性CP.png)

## Ribbon负载均衡调用

Spring Cloud Ribbon是基于NetFlix Ribbon实现的一套客户端负载均衡的工具。主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbon客户端组件提供一系列的配置如连接超时，重试等。在配置文件中列出Load Balancer（LB）后面的所有机器，Ribbon会自动帮你基于某种规则（简单轮训、随机连接等）去连接这些机器。我们很容易使用Ribbon实现自定义的负载均衡算法。

Ribbon也进入了维护模式，未来替换方案LoadBanlancer。

### LB（负载均衡）

就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA（高可用）。常见的负载均衡有软件Nginx、LVS，硬件F5等。

Ribbon本地负载均衡客户端 VS Nginx服务端负载均衡区别：

Nginx是服务器负载均衡，客户端所有请求都会交给Nginx，然后Nginx实现转发请求。即负载均衡石油服务端实现的。

Ribbon本地负载均衡，在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术。

集中式LB（Nginx）：在服务的消费方和提供方之间使用独立的LB设施（可以是硬件F5、也可以是软件Nginx），由该设施负责把访问请求通过某种策略转发至服务的提供方；

进程内LB（Ribbon）：将LB逻辑继承到消费方，消费方从服务注册中心获取有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。Ribbon只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。

Ribbon：负载均衡 + ResTemplate

### Ribbon负载均衡

![Ribbon架构](https://github.com/jackhusky/springcloud/blob/master/images/Ribbon架构.png)

Ribbon工作两步：

- 选择EurekaServer，优先选择在同一个区域内负载较少的server
- 根据用户指定的策略，从server取到的服务注册列表中选择一个地址。Ribbon提供了多种策略：比如轮训、随机和根据响应时间加权

`spring-cloud-starter-netflix-eureka-client `中自带了 `spring-cloud-starter-netflix-ribbon`

getForObject/getForEntity 和postForObject/postEntity

~~~java
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult<Payment>(444,"操作失败");
        }
    }
~~~

**Ribbon核心组件IRule**：根据特定算法从服务列表中选取一个要访问的服务

查看IRule接口的实现

- RoundRobinRule：轮询
- RandomRule：随机
- RetryRule：先按照RoundRobinRule的策略获取服务,如果获取服务失败则在指定时间内进行重试,获取可用的服务
- WeightedResponseTimeRule：对RoundRobinRule的扩展,响应速度越快的实例选择权重越多大,越容易被选择
- BestAvailableRule：会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务,然后选择一个并发量最小的服务
- AvailabilityFilteringRule：先过滤掉故障实例,再选择并发较小的实例
- ZoneAvoidanceRule：默认规则,复合判断server所在区域的性能和server的可用性选择服务器

自定义规则：修改cloud-consumer-order80工程

> 自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下。https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#customizing-the-ribbon-client

~~~java
package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRuleIRule(){
        return new RandomRule();
    }
}
~~~

主启动类：

```java
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(value = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
```

启动cloud-eureka-server7001、cloud-eureka-server7002、cloud-provider-payment8001、cloud-provider-payment8002、cloud-consumer-order80，测试接口 localhost:80/consumer/payment/get/31，可以看到按照我们自定义的规则查找。

**负载均衡算法：rest接口第几次请求数%服务器集群总数量 = 实际调用服务器位置下标，每次服务重启后rest接口计数从1开始。**

cloud-provider-payment8001、cloud-provider-payment8002添加接口

~~~java
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
~~~

修改cloud-consumer-order80工程，去掉 `@LoadBalanced`注解

自定义规则：

~~~java
@Component
public class MyLB implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*****第几次请求next: " + next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {
        int index = getAndIncrement() % instances.size();
        return instances.get(index);
    }
}
~~~

~~~java
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    private LoadBalancer loadBalancer;

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
~~~

测试接口localhost:80/consumer/payment/lb轮训访问服务成功

## OpenFeign服务接口调用

### 概述

Feign是一个声明式WebService客户端，使用Feign能让编写WebService客户端更加简单。

它的使用方法是定义一个服务接口然后在上面添加注解。Feign也支持可插拔式的编码器和解码器。Spring Cloud对Feign进行了封装，使其支持了SpringMVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。

Feign旨在使编写Java Http客户端变得更容易。

前面在使用Ribbon+RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些服务的调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它（以前是Dao接口上面标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可），即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

利用Ribbon维护了Payment的服务列表信息，并且通过轮训实现了客户端的负载均衡。而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

### OpenFeign使用步骤

新建cloud-consumer-feign-order80工程，Feign在消费端使用

pom.xml

~~~xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
~~~

application.yml

~~~yaml
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
~~~

主启动类：

~~~java
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {

    public static void main(String[] args){
      SpringApplication.run(OrderFeignMain80.class,args);
    }
}
~~~

业务类：

~~~java
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
~~~

控制层：

~~~java
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
~~~

### OpenFeign超时控制

服务cloud-provider-payment8001添加接口

~~~java
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
        return serverPort;
    }
~~~

cloud-consumer-feign-order80添加接口

```java
@GetMapping("/payment/feign/timeout")
public String paymentFeignTimeout();
```

~~~java
@GetMapping("/consumer/payment/feign/timeout")
public String timeout(){
    return paymentFeignService.paymentFeignTimeout();
}
~~~

测试接口可以看到错误信息

~~~log
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Sun Mar 21 21:08:00 CST 2021
There was an unexpected error (type=Internal Server Error, status=500).
Read timed out executing GET http://CLOUD-PAYMENT-SERVICE/payment/feign/timeout
feign.RetryableException: Read timed out executing GET http://CLOUD-PAYMENT-SERVICE/payment/feign/timeout
	at feign.FeignException.errorExecuting(FeignException.java:213)
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:115)
~~~

OpenFeign默认等待1秒钟,超过后报错；OpenFeign默认支持Ribbon

~~~yaml
# 设置feign客户端超时时间(OpenFeign默认支持ribbon)
ribbon:
  # 指的是建立连接所用的时间,适用于网络状态正常的情况下,两端连接所用的时间
  ReadTimeout: 5000
  # 指的是建立连接后从服务器读取到可用资源所用的时间
  ConnectTimeout: 5000
~~~

### OpenFeign日志打印功能

Feign提供了日志打印功能，我们可以通过配置来调整日志级别，从而了解Feign中Http请求的细节。说白了就是对Feign接口的调用情况进行监控和输出

- `NONE`，无记录（**DEFAULT**）。
- `BASIC`，只记录请求方法和URL以及响应状态代码和执行时间。
- `HEADERS`，记录基本信息以及请求和响应头。
- `FULL`，记录请求和响应的头文件，正文和元数据。

~~~java
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
~~~

可以看到日志的打印

~~~log
2021-03-21 21:27:41.982 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] ---> GET http://CLOUD-PAYMENT-SERVICE/payment/get/31 HTTP/1.1
2021-03-21 21:27:41.983 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] ---> END HTTP (0-byte body)
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] <--- HTTP/1.1 200 (2008ms)
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] connection: keep-alive
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] content-type: application/json
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] date: Sun, 21 Mar 2021 13:27:43 GMT
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] keep-alive: timeout=60
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] transfer-encoding: chunked
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] 
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] {"code":200,"message":"查询成功,端口号是:8001","data":{"id":31,"serial":"尚硅谷001"}}
2021-03-21 21:27:43.992 DEBUG 8596 --- [p-nio-80-exec-5] c.a.s.service.PaymentFeignService        : [PaymentFeignService#getPaymentById] <--- END HTTP (96-byte body)
~~~

## Hystrix断路器

### 概述

分布式系统面临的问题：复杂分布式体系结构中的应用程序，有数10个依赖关系，每个依赖关系在某些时候将不可避免地失败。

![服务雪崩](https://github.com/jackhusky/springcloud/blob/master/images/服务雪崩.png)

多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其它系统资源紧张，导致整个系统发生更多的级联故障，这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

所以，通常你发现一个模块下的某个实例失败后，这时候这个模块依然还会接收流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫雪崩。

#### 是什么

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

“断路器”本身是一种开关装置，当某个服务单元发生故障后，通过断路器的故障监控（类似熔断保险丝），向调用方法返回一个符合预期的、可处理的备选响应（FallBack），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用的线程不会被长时、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

**Hystrix可以服务降级、服务熔断、接近实时的监控**，Hystrix官宣不再更新。（不发布新版本、不再接受合并请求、被动修复bugs）

### Hystrix重要概念

#### 服务降级

服务器忙，请稍后再试，不让客户端等待并立刻返回一个友好提示。fallback

程序运行异常、超时、服务熔断触发服务降级，线程池/信号量也会导致服务降级。

#### 服务熔断

类似保险丝达到最大服务访问后，直接拒绝访问，拉闸先点，然后调用服务降级的方法并返回友好提示。（服务的降级->进而熔断->恢复调用链路）

#### 服务限流

秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行。

### Hystrix案例

#### 构建和高并发测试

构建cloud-provider-hystrix-payment8001工程，Eureka服务端采用单机版，方便测试。

~~~xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
~~~

```java
@Service
public class PaymentService {

    /**
     * 正常访问
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id + "\t" + "o(∩_∩)o 哈哈";
    }

    /**
     * 超时访问
     */
    public String paymentInfo_TimeOut(Integer id) {
        int num = 3;
        // 暂停3秒钟
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id:" + id + "\t" + "o(∩_∩)o 哈哈(秒)" + num;
    }
}
```

开启Jmeter,来20000个并发压死8001,20000个请求都去访问paymentInfo_TimeOut服务

![压测20000个请求](https://github.com/jackhusky/springcloud/blob/master/images/压测20000个请求.png)

paymentInfo_OK请求也在打圈圈，tomcat的默认工作线程数被打满了,没有多余的线程来分解压力和处理

Jmeter压测结论：上面还只是服务提供者8001自己测试,假如此时外部的消费者80也来访问,那消费者只能干等,最终导致消费端80不满意,服务端8001直接被拖死

使用Feign构建cloud-consumer-feign-hystrix-order80

高并发测试：2w个线程压8001，消费者80微服务再去访问的OK服务8001地址localhost/consumer/payment/hystrix/ok/32

消费者80要么转圈圈，要么延时报错；

~~~log
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Sun Mar 21 22:53:30 CST 2021
There was an unexpected error (type=Internal Server Error, status=500).
Read timed out executing GET http://CLOUD-PROVIDER-HYSTRIX-PAYMENT/payment/hystrix/ok/32
feign.RetryableException: Read timed out executing GET http://CLOUD-PROVIDER-HYSTRIX-PAYMENT/payment/hystrix/ok/32
	at feign.FeignException.errorExecuting(FeignException.java:213)
~~~

#### 故障和导致现象

8001同一层次的其他接口被困死,因为tomcat线程池里面的工作线程已经被挤占完毕；80此时调用8001,客户端访问响应缓慢,转圈圈

正因为有上述故障或不佳表现，才有我们的降级/容错/限流等技术诞生。

#### 如何解决

- 超时导致服务变慢——超时不再等待
- 出错（宕机或程序运行出错）——出错要有兜底

解决：

1、对方服务(8001)超时了，调用者(80)不能一直卡死等待，必须有服务降级。

2、对方服务(8001)down机了，调用者(80)不能一直卡死等待，必须有服务降级。

3、对方服务(8001)ok，调用者(80)自己有故障或有自我要求（自己的等待时间小于服务提供者）。

#### 服务降级

cloud-provider-hystrix-payment8001从自身找问题，设置自身调用超时时间的峰值，峰值内可以正常运行，超过了峰值需要有兜底的方法处理，做服务降级。

一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法

~~~java
    /**
     * 超时访问
     */
    @HystrixCommand(fallbackMethod = "payment_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int num = 5;
        // 暂停3秒钟
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池:    " + Thread.currentThread().getName() + " ===> paymentInfo_TimeOut, id: " + id + "\t" + "耗时: " + num +
                "秒";
    }

    public String payment_TimeOutHandler(Integer id){
        return "线程池:    " + Thread.currentThread().getName() + " ===> paymentInfo_TimeOut, id: " + id + "\t" + "o" +
                "(╥﹏╥)o ";
    }
~~~

主启动类标注注解 `@EnableCircuitBreaker`开启Hystrix的功能

客户端 cloud-consumer-feign-hystrix-order80 也可以做服务降级

```yaml
feign:
  hystrix:
    enabled: true
```

主启动类标注`@EnableHystrix` 注解开启服务消费端Hystrix的功能

```java
@GetMapping("/consumer/payment/hystrix/timeout/{id}")
@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
})
public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
    //int age = 10/0;
    return paymentHystrixService.paymentInfo_TimeOut(id);
}

public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
    return "我是消费者80, 对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
}
```

这样做的问题：

- 每个业务方法对应一个fallback方法，代码膨胀
- fallback方法和业务方法放在一起耦合高

问题1的解决：设置默认的fallback方法，不必每个方法定义fallback方法。使用注解`@DefaultProperties`定义默认的fallback方法。

~~~java
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGloabalFallbackMethod")
public class OrderHyrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80, 对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    public String paymentGloabalFallbackMethod() {
        return "我是消费者80(paymentGloabalFallbackMethod), 对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
}
~~~

问题2的解决：面对服务端的运行超时宕机，为Feign客户端定义的接口添加一个服务降级处理的实现类实现解耦

```yaml
feign:
  hystrix:
    enabled: true
```

```java
@Component
public class PaymentFallbackService implements PaymentHystrixService{

    @Override
    public String paymentInfo_OK(Integer id) {
        return "---->PaymentFallbackService paymentInfo_OK: fallback()";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---->PaymentFallbackService paymentInfo_TimeOut: fallback()";
    }
}
```

```java
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {...}
```

停掉cloud-provider-hystrix-payment8001服务，访问接口localhost/consumer/payment/hystrix/ok/32，可以看到客户端的fallback方法的调用，这样子不会挂起耗死服务器。

#### 服务熔断

熔断机制是应对雪崩效应的一种微服务链路保护机制。当删除链路的某个微服务出错不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点的微服务的调用，快速返回错误的响应信息。当检测到该节点微服务调用响应正常后，恢复调用链路。

Spring Cloud框架中，熔断机制通过Hystrix实现。Hystrix会监控微服务见调用的状况，当失败的调用到一定阈值，缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是`@HystrixCommand`

https://github.com/Netflix/Hystrix/wiki/How-it-Works#circuit-breaker

修改cloud-provider-hystrix-payment8001

```java
/**
 * 测试服务熔断 
 * @see HystrixCommandProperties
 */
@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间范围
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") //失败率
})
public String paymentCircuitBreaker(@PathVariable Integer id){
    if(id < 0){
        throw new RuntimeException("******id不能是负数******");
    }
    String serailNumber = IdUtil.simpleUUID();
    return Thread.currentThread().getName() + "调用成功,流水号:" + serailNumber;
}

public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
    return "id不能是负数,请稍候再试(┬＿┬),id: " + id;
}
```

```java
@GetMapping("/payment/circuit/{id}")
public String paymentCircuitBreaker(@PathVariable("id") Integer id){
    String result = paymentService.paymentCircuitBreaker(id);
    return result;
}
```

测试 cloud-provider-hystrix-payment8001 一次正确，一次错误；多次运行后发现即使正确的访问也不能进行了，多次正确后慢慢恢复链路

熔断类型：熔断打开、熔断关闭、熔断半开

断路器开启或者关闭的条件：

- 当满足一定的阈值的时候（默认10秒钟超过20个请求次数）
- 当失败率达到一定的时候（默认10秒内超过50%的请求次数）
- 到达以上阈值，断路器将会开启
- 当开启的时候，所有请求都不会进行转发
- 一段时间之后（默认5秒），这个时候断路器是半开状态，会让其他一个请求进行转发，如果成功，断路器会会关闭，若失败，继续开启，重复4和5

断路器打开后

1. 再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallback，通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。
2. 原来的主逻辑如果恢复呢？对于这一问题，Hystrix也为我们实现了自动恢复功能。当断路器打开，对主逻辑进行熔断之后，Hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时成为主逻辑，当休眠窗口到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果这次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。

#### 服务限流

Sentinel

### 服务监控Hystrix Dashboard

Hystrix提供了准实时的调用监控，Hystrix会持续记录所有通过Hystrix发起的请求的执行信息。Spring Cloud整合了Hystrix Dashboard，对监控内容转化了可视化界面

构建工程cloud-consumer-hystrix-dashboard9001

```xml
<!--hystrix dashboard-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

```java
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
```

修改cloud-provider-hystrix-payment8001

```java
/**
 * 此配置是为了服务监控而配置，与服务容错本身无观，springCloud 升级之后的坑
 * ServletRegistrationBean因为springboot的默认路径不是/hystrix.stream
 * 只要在自己的项目中配置上下面的servlet即可
 * @return
 */
@Bean
public ServletRegistrationBean getServlet(){
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/hystrix.stream");
    registrationBean.setName("HystrixMetricsStreamServlet");
    return registrationBean;
}
```

![hystrixdashboard监控](https://github.com/jackhusky/springcloud/blob/master/images/hystrixdashboard监控.png)

![hystrixdashboard图形说明](https://github.com/jackhusky/springcloud/blob/master/images/hystrixdashboard图形说明.png)

## Gateway新一代网关

https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/

Spring Cloud全家桶中有个很重要的组件就是网关，在1.x版本中都是采用Zuul网关。但是在2.x版本中，Zuul的升级一直跳票，Spring Cloud自己开发了网关替代Zuul，那就是Spring Cloud Gateway。

Spring Cloud Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器，例如：熔断、限流、重试等。它是基于WebFlux框架实现的，而WebFlux框架底层就是使用了高性能的Reactor模式通信框架Netty。

Spring Cloud Gateway的目标是提供统一的路由方式且基于Filter链的方式提供了网关的功能，例如：安全、监控/指标、限流。

![网关的位置](https://github.com/jackhusky/springcloud/blob/master/images/网关的位置.bmp)

SpringCloud Gateway的特性：

- 基于Spring5，Project Reactor和Spring Boot 2.0进行构建

- 动态路由：能够匹配任何请求属性
- 可以对路由指定Predicate（断言）和Filter（顾虑器）
- 集成Hystrix的断路器功能
- 集成Spring Cloud 服务发现功能
- 易于编写的Predicate（断言）和Filter（顾虑器）
- 请求限流功能
- 支持路径重写

Zuul1.x模型：

Spring Cloud中所集成的Zuul版本，采用的是Tomcat容器，使用的是传统的Servlet IO处理模型。Servlet由Servlet container进行生命周期管理。container启动时构建servlet对象并调用servlet init()进行初始化；container运行时接收请求，为每个请求分配一个线程（一般从线程池中获取空闲线程）然后调用service()；container关闭时调用servlet destory()销毁servlet。

servlet是一个简单的网络IO模型，当请求进入servlet container时，servlet container就会为其绑定一个线程，在并发不高的场景下这种模型是适用的。但是在高并发情况下线程数量就会上涨，而线程资源代价是昂贵的（上下文切换，内存消耗大）严重影响请求的处理时间。在一些简单业务场景下不希望为每个request分配一个线程，只需要1个或几个线程就能应对极大并发的请求，这种业务场景下servlet模型没有优势。

所以Zuul1.x是基于servlet之上的一个阻塞时处理模型，spring实现了处理所有request请求的一个servlet（DispatcherServlet）并由该servlet阻塞式处理。所以Spring Cloud Zuul无法摆脱servlet模型的弊端。

Gateway模型：

传统的Web框架，比如：struts2，springmvc都是基于Servlet API与Servlet容器基础之上运行的。在Serlvet3.1之后有了异步非阻塞的支持。而WebFlux是一个典型非阻塞异步的框架，它的核心是基于Reactor的相关API实现的。相对于传统的web框架来说，它可以运行在诸如Netty、Undertow以及支持Servlet3.1的容器上。非阻塞式+函数式编程（Spring5必须让你使用JAVA8）。

Spring WebFlux 是基于Spring 5.0引入的新的响应式框架，区别于SpringMVC，他不需要依赖Serlvet API，它是完全异步阻塞的，并且基于Reactor来实现响应式流规范。

### 三大核心

- Route（路由）：是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如断言为true则匹配该路由
- Predicate（断言）：参考Java8的java.util.function.Predicate，开发人员可以匹配HTTP请求中的所有内容(例如请求头或请求参数),如果请求与断言相匹配则进行路由
- Filter（过滤）：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或之后对请求进行修改

![gateway工作流程](https://github.com/jackhusky/springcloud/blob/master/images/gateway工作流程.png)

客户端向Spring Cloud Gateway发出请求，然后在Gateway Handler Mapping中找到与请求相匹配的路由，将其发送到Gateway Web Handler。Handler再通过指定的过滤器来讲请求发送到我们实际的服务之星业务逻辑，然后返回。过滤器之间用虚线分开是应为过滤器可能在发送代理请求之前（pre）或之后（post）之星业务逻辑。

Filter在“pre”类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等；在“post”类型的过滤器中可以做响应内容、响应头的修改、日志的输出，流量监控等有着非常重要的作用。

核心逻辑：路由转发+执行过滤器链

### 入门配置

新建工程cloud-gateway-gateway9527

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

```yaml
server:
  port: 9527
spring:
  application:
    name: cloud-gateway
eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka
```

```java
@SpringBootApplication
@EnableEurekaClient
public class GatewayMain9527 {

    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9527.class, args);
    }
}
```

cloud-provider-payment8001 的 controller的lb、get，不想暴露8001端口，希望在8001外面套一层9527

```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: payment_route # 路由的id,没有规定规则但要求唯一,建议配合服务名
          #匹配后提供服务的路由地址
          uri: http://localhost:8001
          predicates:
            - Path=/payment/get/** # 断言，路径相匹配的进行路由
        - id: payment_route2
          uri: http://localhost:8001
          predicates:
            Path=/payment/lb/** #断言,路径相匹配的进行路由
```

访问：localhost:9527/payment/get/31

编码方式：

```java
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder routeBuilder){
    RouteLocatorBuilder.Builder routes = routeBuilder.routes();
    routes.route("path_route_atguigu",
            r -> r.path("/guonei")
                    .uri("https://news.baidu.com/guonei"))
            .route("path_route_atguigu2",
                    r -> r.path("/guoji")
                            .uri("https://news.baidu.com/guoji")).build();
    return routes.build();
}
```

### 通过服务名实现动态

默认情况下Gatway会根据注册中心注册的服务列表,  以注册中心上微服务名为路径创建动态路由进行转发,从而实现动态路由的功能。

```yaml
spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: payment_route # 路由的id,没有规定规则但要求唯一,建议配合服务名
          #匹配后提供服务的路由地址
          uri: lb://CLOUD-PAYMENT-SERVICE
          predicates:
            - Path=/payment/get/** # 断言，路径相匹配的进行路由
        - id: payment_route2
          uri: lb://CLOUD-PAYMENT-SERVICE
          predicates:
            - Path=/payment/lb/** #断言,路径相匹配的进行路由
```

lb://serverName是spring cloud  gatway在微服务中自动为我们创建的负载均衡uri。

### Predicate

启动cloud-gateway-gateway9527的日志：

~~~log
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [After]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Before]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Between]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Cookie]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Header]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Host]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Method]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Path]
2021-03-22 20:28:42.138  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Query]
2021-03-22 20:28:42.139  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [ReadBodyPredicateFactory]
2021-03-22 20:28:42.139  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [RemoteAddr]
2021-03-22 20:28:42.139  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [Weight]
2021-03-22 20:28:42.139  INFO 9416 --- [  restartedMain] o.s.c.g.r.RouteDefinitionRouteLocator    : Loaded RoutePredicateFactory [CloudFoundryRouteService]
~~~

[Route Predicate Factories是什么？](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-request-predicates-factories)

```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 开启从注册中心动态创建路由的功能，利用微服务名称进行路由
      routes:
        - id: payment_route # 路由的id,没有规定规则但要求唯一,建议配合服务名
          #匹配后提供服务的路由地址
          uri: lb://CLOUD-PAYMENT-SERVICE
          predicates:
            - Path=/payment/get/** # 断言，路径相匹配的进行路由
        - id: payment_route2
          uri: lb://CLOUD-PAYMENT-SERVICE
          predicates:
            - Path=/payment/lb/** #断言,路径相匹配的进行路由
#            - After=2021-03-22T20:37:42.431+08:00[Asia/Shanghai] #在这个时间之后才可以访问
#            - Before=2021-03-25T20:37:42.431+08:00[Asia/Shanghai] #在这个时间之前才可以访问
#            - Cookie=username, zzhh #携带的Cookie
#            - Header=X-Request-Id, \d+ #请求带X-Request-Id属性并且值为整数的正则表达式
        
```

~~~shell
curl http://localhost:9527/payment/lb --cookie "username=zzhh"
curl http://localhost:9527/payment/lb -H "X-Request-Id:23"
~~~

### Filter

路由过滤器可用于修改进入的HTTP请求和返回HTTP响应，路由过滤器只能指定路由进行使用。Spring Cloud Gateway内置了很多路由过滤器，他们都由GatewayFilter的工厂类来产生。

[GatewayFilter Factories](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gatewayfilter-factories)、[Global Filters](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#global-filters)

自定义Filter：

```java
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****************come in MyLogGateWayFilter:   " + new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null){
            log.info("*********用户名为null, 非法用户, o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```

访问：localhost:9527/payment/lb?username=ddd 可以通过

## SpringCloud Config分布式配置中心

https://docs.spring.io/spring-cloud-config/docs/2.2.7.RELEASE/reference/html/

分布式系统面临的配置问题？   

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。

SpringCloud提供了ConfigServer来解决这个问题，我们每一个微服务自己带着一个application.yml，上百个配置文件的管理....../(ㄒoㄒ)/~~

![SpringCloudConfig配置中心.wmf](https://github.com/jackhusky/springcloud/blob/master/images/SpringCloudConfig配置中心.wmf)

SpringCloud Config为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

SpringCloud Config分为服务端和客户端两部分。

服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密/解密信息等访问接口。

客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

- 集中管理配置文件
- 不同环境不同配置，动态化的配置更新，分环境部署比如dev/test/prod/beta/release
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
- 当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
- 将配置信息以REST接口的形式暴露。post、curl访问刷新均可...

### 服务端配置与测试

新建microservicecloud-config-3344模块，它是Cloud的配置中心模块，服务端

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: git@github.com:jackhusky/springcloud-config.git #GitHub上面的git仓库名字
          search-paths:
            - springcloud-config # 搜索的目录
      # 读取分支
      label: master
```

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigCenter3344 {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter3344.class, args);
    }
}
```

/{label}/{application}-{profile}.yml：http://config-3344.com:3344/master/application-test.yml

### 客户端配置与测试

```xml
<!-- SpringCloud Config客户端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

applicaiton.yml是用户级的资源配置项，bootstrap.yml是系统级的，优先级更加高

Spring Cloud会创建一个`Bootstrap Context`，作为Spring应用的`Application Context`的父上下文。初始化的时候，`Bootstrap Context`负责从外部源加载配置属性并解析配置。这两个上下文共享一个从外部获取的`Environment`。`Bootstrap`属性有高优先级，默认情况下，它们不会被本地配置覆盖。 `Bootstrap context`和`Application Context`有着不同的约定所以新增了一个`bootstrap.yml`文件，保证`Bootstrap Context`和`Application Context`配置的分离。

bootstrap.yml：

```yaml
spring:
  cloud:
    config:
      name: application #需要从github上读取的资源名称，注意没有yml后缀名
      profile: dev   #本次访问的配置项
      label: master
      uri: http://config-3344.com:3344  #本微服务启动后先去找3344号服务，通过SpringCloudConfig获取GitHub的服务地址
  application:
    name: microservicecloud-config-client
```

```java
@RestController
public class ConfigClientRest {

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/config")
    public String getConfig()
    {
        String str = "applicationName: "+applicationName;
        System.out.println("******str: "+ str);
        return "applicationName: "+applicationName;
    }
}
```

成功实现了客户端3355访问SpringCloud Config3344通过GitHub获取配置信息

问题随之而来,分布式配置的动态刷新问题；

linux运维修改Github上的配置文件内容修改，刷新3344发现ConfigServer配置中心立刻响应，刷新3355发现ConfigClient客户端的没有任何响应，3355没有变化除非自己重启或者重新加载

### Config客户端之动态刷新

避免每次更新配置都要重启客户端微服务3355

```yaml
#暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```java
@RestController
@RefreshScope
public class ConfigClientRest {..}
```

再需要运维人员发送POST请求刷新3355，curl  -X POST "http://localhost:3355/actuator/refresh"

localhost:3355/config 成功获取到最新值

假设有多个微服务3355/3366/3377.....每个微服务都要执行一次post请求,手动刷新?可否广播,一次通知,处处生效?我们想大范围的自动刷新

