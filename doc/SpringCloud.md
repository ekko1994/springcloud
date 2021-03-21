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

