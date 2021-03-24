# Spring Cloud Alibaba

https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md

Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。

- **服务限流降级**：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
- **服务注册与发现**：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。
- **分布式配置管理**：支持分布式系统中的外部化配置，配置更改时自动刷新。
- **消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。
- **分布式事务**：使用 `@GlobalTransactional` 注解， 高效并且对业务零侵入地解决分布式事务问题。
- **阿里云对象存储**：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。
- **分布式任务调度**：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。
- **阿里云短信服务**：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

## Nacos作为服务注册中心

服务提供者：cloudalibaba-provider-payment9001、cloudalibaba-provider-payment9002

```xml
<!--Spring Cloud nacos-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

```yaml
server:
  port: 9001
spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # 配置nacos地址
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

服务消费者：cloudalibaba-consumer-nacos-order83

```yaml
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

# 消费者将要去访问的微服务名称(注册成功进nacos的微服务提供者)
service-url:
  nacos-user-service: http://nacos-payment-provider
```

![注册服务到nacos面板](https://github.com/jackhusky/springcloud/blob/master/images/注册服务到nacos面板.png)

**Nacos支持AP和CP**

## Nacos作为服务配置中心

### 基础配置

```xml
<!--nacos config-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
<!--Spring Cloud nacos-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

bootstrap.yml

```yaml
server:
  port: 3377

spring:
  application:
    name: nacos-client-config
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos 服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos 作为配置中心地址
        file-extension: yaml # 指定yaml格式的配置
```

application.yml

```yaml
spring:
  profiles:
    active: dev
```

```java
@RestController
@RefreshScope // 支持nacos的动态刷新功能,实现配置自动更新
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

在Nacos中添加配置信息；DataId

~~~log
${prefix}-${spring.profile.active}.${file-extension}
nacos-config-client-dev.yaml
~~~

- prefix 默认为 spring.application.name 的值
- spring.profile.active 即为当前环境对应的 profile
- file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置

![nacos作为配置中心](https://github.com/jackhusky/springcloud/blob/master/images/nacos作为配置中心.png)

修改Nacos下的yaml配置，再次调用接口发现配置已经刷新

### 分类配置

NameSpace+Group+DataID的关系？

NameSpace可以区分部署环境的，Group和DataID逻辑上区分两个目标对象。

![nacos命名空间组DataId关系](https://github.com/jackhusky/springcloud/blob/master/images/nacos命名空间组DataId关系.png)

比如三个环境：开发、测试、生产环境，可以创建三个NameSpace，它们之间是隔离的。

Group默认是DEFAULT_GROUP，Group可以把不同的微服务划分到同一个组。

Service就是微服务，一个Service可以包含多个Cluster，Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟划分。比如为了容灾，将Service微服务分别部署在杭州和广州机房，这时就可以给杭州机房的Service微服务起一个集群名称（HZ），广州（GZ），还可以让同一个机房的微服务互相调用，以提升性能。

Instance就是微服务的实例。

#### DataID方案

指定`spring.profile.active`和配置文件的DataID来使不同环境下读取不同的配置

默认空间+默认分组+新建dev和test两个DataId

![nacos配置1](https://github.com/jackhusky/springcloud/blob/master/images/nacos配置1.png)

```yaml
spring:
  profiles:
    active: dev
#    active: test
```

测试：http://localhost:3377/config/info，配置什么就加载什么

#### Group方案

![nacos配置2](https://github.com/jackhusky/springcloud/blob/master/images/nacos配置2.png)

```yaml
spring:
  profiles:
    active: info
#    active: dev
#    active: test
```

```yaml
server:
  port: 3377

spring:
  application:
    name: nacos-client-config
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos 服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos 作为配置中心地址
        file-extension: yaml # 指定yaml格式的配置
        group: DEV_GROUP #设置组
```

#### Namespace方案

新建dev/test/的Namespace

![nacos配置3](https://github.com/jackhusky/springcloud/blob/master/images/nacos配置3.png)

```yaml
spring:
  profiles:
#    active: info
#    active: dev
    active: test
```

```yaml
server:
  port: 3377

spring:
  application:
    name: nacos-client-config
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos 服务注册中心地址
      config:
        server-addr: localhost:8848 # nacos 作为配置中心地址
        file-extension: yaml # 指定yaml格式的配置
        group: DEV_GROUP #设置组
        namespace: 653de248-9cd1-4753-9474-d5d270ddb961
```