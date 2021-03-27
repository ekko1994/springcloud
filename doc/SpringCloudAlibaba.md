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

## SpringCloud Alibaba Nacos服务注册和配置中心

### Nacos作为服务注册中心

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

### Nacos作为服务配置中心

#### 基础配置

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

#### 分类配置

NameSpace+Group+DataID的关系？

NameSpace可以区分部署环境的，Group和DataID逻辑上区分两个目标对象。

![nacos命名空间组DataId关系](https://github.com/jackhusky/springcloud/blob/master/images/nacos命名空间组DataId关系.png)

比如三个环境：开发、测试、生产环境，可以创建三个NameSpace，它们之间是隔离的。

Group默认是DEFAULT_GROUP，Group可以把不同的微服务划分到同一个组。

Service就是微服务，一个Service可以包含多个Cluster，Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟划分。比如为了容灾，将Service微服务分别部署在杭州和广州机房，这时就可以给杭州机房的Service微服务起一个集群名称（HZ），广州（GZ），还可以让同一个机房的微服务互相调用，以提升性能。

Instance就是微服务的实例。

##### DataID方案

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

##### Group方案

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

##### Namespace方案

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

### Nacos集群和持久化配置

## Spring Cloud Alibaba Sentinel实现熔断与限流

### 安装Sentinel控制台

运行命令

~~~cmd
java -jar sentinel-dashboard-1.7.0.jar
~~~

访问sentinel管理界面，账号密码sentinel：sentinel

~~~log
http://localhost:8080/
~~~

### 初始化演示功能

```xml
<!--Spring Cloud alibaba nacos-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<!--Spring Cloud Alibaba sentinel-datasource-nacos 后续做持久化用到-->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
<!--Spring Cloud alibaba sentinel-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```

```yaml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        # nacos 服务注册中心地址
        server-addr: localhost:8848
    sentinel:
      transport:
        # 配置sentinel dashboard地址
        dashboard: localhost:8080
        # 默认8719端口，加入被占用会自动从8719开始依次+1扫描，直至找到未被占用的端口
        port: 8719

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

![sentinel控制台](https://github.com/jackhusky/springcloud/blob/master/images/sentinel控制台.png)

sentinel采用的是懒加载，访问接口才能在控制台中看到信息

### 流控规则

https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6

#### 直接模式(默认)：

当QPS超过任意规则的阈值后，新的请求就会被立即拒绝，拒绝方式为抛出`FlowException`。这种方式适用于对系统处理能力确切已知的情况下，比如通过压测确定了系统的准确水位时。

阈值类型/单机阈值：

- QPS（每秒钟的请求数量）：当调用该api的QPS达到阈值的时候，进行限流
- 线程数：当调用该api的线程数达到阈值的时候，进行限流

![流控规则直接模式](https://github.com/jackhusky/springcloud/blob/master/images/流控规则直接模式.png)

抛出异常：Blocked by Sentinel(flow limiting)

直接调用默认报错信息，技术方面OK but，是否应该有我们自己的后续处理？类似于有个fallback的兜底方法

#### 关联模式

当与A关联的资源B达到阈值后,就限流自己

![流控规则关联模式](https://github.com/jackhusky/springcloud/blob/master/images/流控规则关联模式.png)

线程密集访问testB，发现访问testA挂掉了

#### 链路模式

只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，进行限流），api级别的针对来源

#### 快速失败效果

直接失败,抛出异常，Blocked by Sentinel(flow limiting)

`com.alibaba.csp.sentinel.slots.block.flow.controller.DefaultController`

#### 预热效果

公式:阈值除以coldFactor(默认值为3)，经过预热时长后才会达到阈值

默认coldFactor为3，即请求QPS从threshold/3开始，经预热时长逐渐升至设定的QPS阈值

![预热效果](https://github.com/jackhusky/springcloud/blob/master/images/预热效果.png)

#### 排队等待

匀速排队，让请求以匀速通过，阈值类型必须射程QPS

每秒1次请求，超过的话就排队等待，等待的超时时间为20000毫秒

![排队等待效果](https://github.com/jackhusky/springcloud/blob/master/images/排队等待效果.png)

### 降级规则

- 慢调用比例 (`SLOW_REQUEST_RATIO`)：选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（即最大的响应时间），请求的响应时间大于该值则统计为慢调用。当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断。
- 异常比例 (`ERROR_RATIO`)：当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。
- 异常数 (`ERROR_COUNT`)：当单位统计时长内的异常数目超过阈值之后会自动进行熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。

上面是1.8文档，测试版本是1.7

#### RT

平均响应时间

![sentinel测试降级RT](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级RT.bmp)

#### 异常比例

![sentinel测试降级异常比例](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级异常比例.bmp)

#### 异常数

时间窗口一定要大于60秒

![sentinel测试降级异常数](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级异常数.bmp)

### 热点key限流

之前的例子都是限流出现问题后，都是sentinel系统默认的提示Blocked by Sentinel (flow limiting)，我们也可以自定义降级方法`@Sentinelesource`

![热点规则](https://github.com/jackhusky/springcloud/blob/master/images/热点规则.png)

```java
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey....";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "deal_testHotKey...";
    }
```

只要请求路径带有参数p1就符合规则

参数特殊配置：

我们期望p1参数当它是某个特殊值时，它的限流和平时的规则不一样

![热点规则特殊情况](https://github.com/jackhusky/springcloud/blob/master/images/热点规则特殊情况.png)

当p1参数是5的情况下，阈值变为100

### 系统规则

https://github.com/alibaba/Sentinel/wiki/%E7%B3%BB%E7%BB%9F%E8%87%AA%E9%80%82%E5%BA%94%E9%99%90%E6%B5%81

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。

![系统规则](https://github.com/jackhusky/springcloud/blob/master/images/系统规则.png)

### @SentinelResource

https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81

#### 按照资源名称处理

cloudalibaba-sentinel-service8401引入

~~~xml
        <dependency>
            <groupId>com.atguigu.cloud</groupId>
            <artifactId>cloud-api-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
~~~

```java
@GetMapping("/byResource")
@SentinelResource(value = "byResource",blockHandler = "handleException")
public CommonResult byResource(){

    return new CommonResult(200,"按资源名称限流测试OK",new Payment(222L, "serial222"));
}

public CommonResult handleException(BlockException exception){
    return new CommonResult(444, exception.getClass().getCanonicalName()+"\t 服务不可用");
}
```

![按资源名称流控](https://github.com/jackhusky/springcloud/blob/master/images/按资源名称流控.png)

停掉8401服务，Sentinel的控制台中流控规则消失了

#### 按照Url地址限流

```java
@GetMapping("/rateLimit/byUrl")
@SentinelResource(value = "byUrl")
public CommonResult byUrl(){

    return new CommonResult(200,"按url限流测试OK",new Payment(222L, "serial222"));
}
```

![按url流控](https://github.com/jackhusky/springcloud/blob/master/images/按url流控.png)

#### 客户自定义限流处理逻辑

```java
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult(555, "自定义的限流处理信息...CustomerBlockHandler");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(555, "自定义的限流处理信息2...CustomerBlockHandler2");
    }
}
```

