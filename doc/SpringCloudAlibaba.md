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

![sentinel测试降级RT](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级RT.png)

#### 异常比例

![sentinel测试降级异常比例](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级异常比例.png)

#### 异常数

时间窗口一定要大于60秒

![sentinel测试降级异常数](https://github.com/jackhusky/springcloud/blob/master/images/sentinel测试降级异常数.png)

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

![按url流控](https://github.com/jackhusky/springcloud/blob/master/images/按url限流.png)

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

### 服务熔断功能

Sentinel整合ribbon+openFeign+fallback

cloudalibaba-provider-payment9003、cloudalibaba-provider-payment9003

#### Ribbon系列

```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <dependency>
        <groupId>com.atguigu.cloud</groupId>
        <artifactId>cloud-api-common</artifactId>
        <version>${project.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
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
```

```yaml
server:
  port: 9004

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

management:
  endpoints:
    web:
      exposure:
        include: "*"
```

```java
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "sgdgj23jh4jbh234"));
        hashMap.put(2L, new Payment(2L, "gdfhjskhldkkjlfd"));
        hashMap.put(3L, new Payment(3L, "3kj5kjsdkldsk3od"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult commonResult = new CommonResult(200, "from mysql, serverPort = " + serverPort, payment);
        return commonResult;
    }
}
```

cloudalibaba-consumer-nacos-order84

```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <!--     sentinel-datasource-nacos 后续持久化用   -->
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <dependency>
        <groupId>com.atguigu.cloud</groupId>
        <artifactId>cloud-api-common</artifactId>
        <version>${project.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
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
```

```yaml
server:
  port: 84
spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    sentinel:
      transport:
        dashboard: localhost:8080
        port: 8719

service-url:
  nacos-user-service: http://nacos-payment-provider

#激活sentinel对feign的支持
feign:
  sentinel:
    enabled: true
```

```java
@RestController
public class CircleBeakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常...");
        }else if (result.getData() == null){
            throw new NullPointerException("NullPointerException,该id没有对应记录，空指针异常...");
        }
        return result;
    }
}
```

测试

```java
    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") //没有配置
//    @SentinelResource(value = "fallback", fallback = "handlerFallback") //fallback只负责业务异常
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责控制台配置违规
//    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                    exceptionsToIgnore = IllegalArgumentException.class) //忽略异常，遇到此异常配置会全部失效
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该id没有对应记录，空指针异常...");
        }
        return result;
    }

    // fallback方法
    public CommonResult<Payment> handlerFallback(Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(444, "handlerFallback方法，异常是：" + e.getMessage(), payment);
    }

    //blockHandler方法
    public CommonResult blockHandler(Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "blockHandler-sentinel限流，无此流水：blockException " + blockException);
    }
```

#### Feign系列

修改cloudalibaba-consumer-nacos-order84

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

```yaml
feign:
  sentinel:
    enabled: true
```

```java
@FeignClient(value = "nacos-payment-provider")
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
```

```java
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回，PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
```

```java
@GetMapping("/comsumer/paymentSQL/{id}")
public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
    return paymentService.paymentSQL(id);
}
```

### 规则持久化

一旦我们重启应用,sentinel规则消失,生产环境需要将配置规则进行持久化

将限流规则持久进Nacos保存,只要刷新8401某个rest地址,sentinel控制台的流控规则就能看得到,只要Nacos里面的配置不删除,针对8401上的流控规则持续有效

修改cloudalibaba-sentinel-server8401

```xml
<!--Spring Cloud Alibaba sentinel-datasource-nacos 后续做持久化用到-->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

在nacos中配置：

~~~json
[
    {
        "resource": "/rateLimit/byUrl",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
~~~

## Spring Cloud Alibaba Seata处理分布式事务

分布式事务问题：一次业务操作需要垮多个数据源或需要垮多个系统进行远程调用，就会产生分布式事务问题。

Seata是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务

分布式事务处理过程-ID+三组件模型：

Transaction ID(XID)：全局唯一的事务id

**TC (Transaction Coordinator) - 事务协调者**：维护全局和分支事务的状态，驱动全局事务提交或回滚。

**TM (Transaction Manager) - 事务管理器**：定义全局事务的范围：开始全局事务、提交或回滚全局事务。

**RM (Resource Manager) - 资源管理器**：管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。

#### 处理过程

1. TM 向 TC申请开启一个全局事务，全局事务创建成功并生成一个全局唯一的 XID；
2. XID 在微服务调用链路的上下文中传播；
3. RM 向 TC 注册分支事务，将其纳入 XID 对应全局事务的管辖；
4. TM 向 TC 发起针对 XID 的全局提交或回滚决议；
5. TC 调度 XID 下管辖的全部分支事务完成提交或回滚请求。

![seata处理过程](https://github.com/jackhusky/springcloud/blob/master/images/seata处理过程.png)

#### 配置准备

file.conf主要修改:自定义事务组名称+事务日志存储模式为db+数据库连接

mysql中执行db_store.sql，registry.conf修改type

自己建数据库：

seata_order库下新建t_order表、seata_storage库下新建t_storage表、seata_account库下新建t_account表

~~~sql
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `int` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `money` decimal(11, 0) DEFAULT NULL COMMENT '金额',
  `status` int(1) DEFAULT NULL COMMENT '订单状态:  0:创建中 1:已完结',
  PRIMARY KEY (`int`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage`  (
  `int` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
  `total` int(11) DEFAULT NULL COMMENT '总库存',
  `used` int(11) DEFAULT NULL COMMENT '已用库存',
  `residue` int(11) DEFAULT NULL COMMENT '剩余库存',
  PRIMARY KEY (`int`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存' ROW_FORMAT = Dynamic;
INSERT INTO `t_storage` VALUES (1, 1, 100, 0, 100);

CREATE TABLE `t_account`  (
  `id` bigint(11) NOT NULL COMMENT 'id',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `total` decimal(10, 0) DEFAULT NULL COMMENT '总额度',
  `used` decimal(10, 0) DEFAULT NULL COMMENT '已用余额',
  `residue` decimal(10, 0) DEFAULT NULL COMMENT '剩余可用额度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户表' ROW_FORMAT = Dynamic;
 
INSERT INTO `t_account` VALUES (1, 1, 1000, 0, 1000);

~~~

订单-库存-账户3个库下都需要建各自独立的回滚日志表，conf/目录下的db_undo_log.sql

~~~sql
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
~~~

#### 订单/库存/账户业务微服务准备

业务需求：下订单->减库存->扣余额->改(订单)状态

新建seata-order-service2001

```xml
<dependencies>
    <!-- nacos -->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <!-- nacos -->

    <!-- seata-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
        <exclusions>
            <exclusion>
                <groupId>io.seata</groupId>
                <artifactId>seata-all</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>io.seata</groupId>
        <artifactId>seata-all</artifactId>
        <version>0.9.0</version>
    </dependency>
    <!-- seata-->
    <!--feign-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.22</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!--jdbc-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
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
    <!--hutool 测试雪花算法-->
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-captcha</artifactId>
        <version>5.2.0</version>
    </dependency>
</dependencies>
```

```yaml
server:
  port: 2001

spring:
  application:
    name: seata-order-service
  cloud:
    alibaba:
      seata:
        # 自定义事务组名称需要与seata-server中的对应
        tx-service-group: fsp_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    # 当前数据源操作类型
    type: com.alibaba.druid.pool.DruidDataSource
    # mysql驱动类
    driver-class-name: org.gjt.mm.mysql.Driver
    url: jdbc:mysql://localhost:3306/seata_order?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456
feign:
  hystrix:
    enabled: false
logging:
  level:
    io:
      seata: info

mybatis:
  mapper-locations: classpath:mapper/*.xml
```

file.conf：拷贝seata-server/conf目录下

```conf
transport {
  # tcp udt unix-domain-socket
  type = "TCP"
  #NIO NATIVE
  server = "NIO"
  #enable heartbeat
  heartbeat = true
  #thread factory for netty
  thread-factory {
    boss-thread-prefix = "NettyBoss"
    worker-thread-prefix = "NettyServerNIOWorker"
    server-executor-thread-prefix = "NettyServerBizHandler"
    share-boss-worker = false
    client-selector-thread-prefix = "NettyClientSelector"
    client-selector-thread-size = 1
    client-worker-thread-prefix = "NettyClientWorkerThread"
    # netty boss thread size,will not be used for UDT
    boss-thread-size = 1
    #auto default pin or 8
    worker-thread-size = 8
  }
  shutdown {
    # when destroy server, wait seconds
    wait = 3
  }
  serialization = "seata"
  compressor = "none"
}
service {
  #vgroup->rgroup
  # 事务组名称
  vgroup_mapping.fsp_tx_group = "default"
  #only support single node
  default.grouplist = "127.0.0.1:8091"
  #degrade current not support
  enableDegrade = false
  #disable
  disable = false
  #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
  max.commit.retry.timeout = "-1"
  max.rollback.retry.timeout = "-1"
}

client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
  report.retry.count = 5
  tm.commit.retry.count = 1
  tm.rollback.retry.count = 1
}

## transaction log store
store {
  ## store mode: file、db
  #mode = "file"
  mode = "db"

  ## file store
  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    driver-class-name = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "root"
    password = "123456"
    min-conn = 1
    max-conn = 3
    global.table = "global_table"
    branch.table = "branch_table"
    lock-table = "lock_table"
    query-limit = 100
  }
}
lock {
  ## the lock store mode: local、remote
  mode = "remote"

  local {
    ## store locks in user's database
  }

  remote {
    ## store locks in the seata's server
  }
}
recovery {
  #schedule committing retry period in milliseconds
  committing-retry-period = 1000
  #schedule asyn committing retry period in milliseconds
  asyn-committing-retry-period = 1000
  #schedule rollbacking retry period in milliseconds
  rollbacking-retry-period = 1000
  #schedule timeout retry period in milliseconds
  timeout-retry-period = 1000
}

transaction {
  undo.data.validation = true
  undo.log.serialization = "jackson"
  undo.log.save.days = 7
  #schedule delete expired undo_log in milliseconds
  undo.log.delete.period = 86400000
  undo.log.table = "undo_log"
}

## metrics settings
metrics {
  enabled = false
  registry-type = "compact"
  # multi exporters use comma divided
  exporter-list = "prometheus"
  exporter-prometheus-port = 9898
}

support {
  ## spring
  spring {
    # auto proxy the DataSource bean
    datasource.autoproxy = false
  }
}
```

registry.conf：拷贝seata-server/conf目录下

```conf
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"

  nacos {
    #serverAddr = "localhost"
    serverAddr = "localhost:8848"
    namespace = ""
    cluster = "default"
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"
    application = "default"
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = "0"
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "file"

  nacos {
    serverAddr = "localhost"
    namespace = ""
  }
  consul {
    serverAddr = "127.0.0.1:8500"
  }
  apollo {
    app.id = "seata-server"
    apollo.meta = "http://192.168.1.204:8801"
  }
  zk {
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  etcd3 {
    serverAddr = "http://localhost:2379"
  }
  file {
    name = "file.conf"
  }
}
```

```java
    @Override
    @GlobalTransactional(name = "test",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---->开始新建订单");
        orderDao.create(order);

        log.info("---->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---->订单微服务开始调用库存，做扣减end");

        log.info("---->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---->订单微服务开始调用账户，做扣减end");

        // 修改订单状态，从0到1，1代表已经完成
        log.info("---->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("---->修改订单状态结束");

        log.info("---->下订单结束了，o(*￣︶￣*)o");
    }
```

```
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
public class SeataOrderMainApp2001 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }
}
```

新建seata-storage-service2002、seata-account-service2003

#### 测试

http://localhost:2001/order/create?userId=1&productId=1&count=10&money=100

AccountServiceImpl添加超时，超时异常,没加 `@GlobalTransactional`，当库存和账户金额扣减后,订单状态并没有设置为已经完成,没有从零改为1，而且由于feign的重试机制,账户余额还有可能被多次扣减`@GlobalTransactional(name = "test",rollbackFor = Exception.class)`

分布式事务的执行流程：

- TM开启分布式事务(TM向TC注册全局事务记录)
- 按业务场景,编排数据库、服务等事务内资源(RM向TC汇报资源准备状态)
- TM结束分布式事务,事务一阶段结束(TM通知TC提交/回滚分布式事务)
- TC汇报事务信息,决定分布式事务是提交还是回滚
- TC通知所有RM提交/回滚资源,事务二阶段结束

AT模式如何做到对业务的无侵入？

![seata的AT模式原理](https://github.com/jackhusky/springcloud/blob/master/images/seata的AT模式原理.png)

## 集群高并发情况下如何保证分布式唯一全局id生成

### 问题

ID生成规则部分硬性要求？

1. 全局唯一：不能出现重复的ID号，既然是唯一标识，这是最基本的要求
2. 趋势递增：在MySQL的innoDB引擎中使用的是聚集索引，由于多数RDBMS使用Btree的数据结构来存储索引数据，在主键的选择上面我们应该尽量使用有序的主键保证写入性能
3. 单调递增：保证下一个ID大于上一个ID，例如事务版本号、IM增量信息、排序等特殊需求
4. 信息安全：如果ID是连续的，恶意用户的扒取工作就非常容易做了，直接按照顺序下载指定URL即可，所以在一些应用场景下，需要ID无规则 不规则，让竞争对手不好猜
5. 含时间戳：这样就能在开发中快速了解分布式id的生成时间

ID号生成系统的可用性要求？

- 高可用：发一个获取分布式ID的请求,服务器就要保证99.999%的情况下给我创建一个唯一分布式ID
- 低延迟：发一个获取分布式ID的请求，服务器就要快，极速
- 高QPS：假如并发一口气创建分布式ID请求同时杀过来，服务器要顶得住且一下子成功创建10万

### 一般通用方案？

#### UUID

包含32个16进制数字，以连字号分为5段，形成8-4-4-4-12的36个字符，比如：fc53d482-1279-436f-a313-488bf6e8c7b1

**性能非常高：本地生成，没有网络消耗**，**但是入数据库性能差**

1. 无序，无法预测生成的顺序，不能生产递增有序的数字。

   首先分布式id一般都会作为主键，但是mysql推荐主键要尽量越短越好，UUID每一个都很长，所以不是很推荐。

2. 主键，UUID作为主键时在特定环境会存在一些问题。

   比如做DB主键的场景下，UUID就非常不使用MySQL官方明确建议主键要尽量越短越好，36个字符长度的UUID不符合要求。

3. 索引，B+树索引的分裂

   既然分布式id是主键，然后主键是包含索引的，然后MySQL的索引是通过B+树来实现的，每一次新的UUID数据的插入，为了查询的优化，都会对索引底层的B+树进行修改，因为UUID数据是无序的，所以每一次UUID数据的插入都会对B+树进行很大的修改，这一点很不好。插入完全无序，不但会导致一些中间点产生分裂，也会拜拜创造出很多不饱和的节点，这样大大降低了数据库插入的性能。

#### 数据库自增主键

单机：在分布式里面，数据库的自增ID的主要原理是：数据库自增ID和mysql数据库的replace into实现的，这里的replace into和insert功能类似。

不同点在于：replace into首先尝试插入数据库列表中，如果发现表中已经有此行数据（根据主键或唯一索引判断）则先删除，再插入。否则直接插入新数据。

replace into的含义是插入一条记录，如果表中唯一索引的值遇到冲突，则替换老数据。

集群分布式：那数据库自增ID机制适合做分布式ID吗？不太合适！

1. 系统水平扩展比较困难，比如定义好了步长和机器台数之后，如果要添加机器该怎么做？加入有一台机器发号是1，2，3，4，5（步长是1），这个时候需要扩容一台。可以这样做，把第二台机器的初始值设置的比第一台高很多，貌似还好，如果有100台机器，怎么扩容？所以系统水平扩展方案复杂难以实现。
2. 数据库压力还是很大， 每次获取ID都得读写一次数据库，非常影响性能，不符合分布式ID里面的低延迟和高QPS的规则（在高并发下，如果都去数据库里面获取id，那是分厂影响性能的）。

#### 基于Redis生成全局id策略

单机版：因为Redis是单线程的天生保证原子性，可以使用原子操作INCR和INCRBY来实现。

集群分布式：在Redis集群情况下，同样和MySQL一样需要设置不同的增长步长，同时key一定要设置有限期，可以使用Redis集群获取更高的吞吐量。

假如一个集群中有5台Redis，可以初始化每台Redis的值分别是1，2，3，4，5，然后后步长是5。各个Redis生成的ID为：

A：1，6，11，16，21

B：2，7，12，17，22

C：3，8，13，18，23

D：4，9，14，19，24

E：5，10，15，20，25

> Redis客户端对服务端的每次调用都经历了发送命令，执行命令，返回结果三个过程。其中执行命令阶段，由于Redis是单线程来处理命令的，所有到达服务端的命令都不会立刻执行，所有的命令都会进入一个队列中，然后逐个执行，并且多个客户端发送的命令的执行顺序是不确定的，但是可以确定的是不会有两条命令被同时执行，不会产生并发问题，这就是Redis的单线程基本模型。

### snowflake

Twitter的分布式自增ID算法snowflake，经测试SnowFlake每秒能产生26万个自增可排序的ID。

1. Twitter的SnowFlake生成ID能够按照时间有序生成
2. SnowFlake算法生成ID的结果是一个64bit大小的整数，为一个Long型（转换成字符串后长度最多19）
3. 分布式系统内不会产生ID碰撞（由datacenter和workerId作区分）并且效率高

![SnowFlake结构](https://github.com/jackhusky/springcloud/blob/master/images/SnowFlake结构.png)

解析：

**1bit**

不用，因为二进制中最高位是符号位，1表示负数，0表示正数。生成的id一般都是用正数，所以最高位固定是0。

**41bit**-时间戳，用来记录时间戳，毫秒级

- 41位可以表示2^(41)-1个数字
- 如果只用来表示正整数（计算机中正数包含0），可以表示的数值范围是：0至2^(41)-1，减1是因为可表示的数值范围是从0开始算的，而不是1。
- 也就是说41位可以表示2^(41)-1个毫秒的值，转换成单位年则是`(2^(41)-1)/(1000*60*60*24*365)=69`年

**10bit**-工作机器id，用来记录工作机器id

- 可以部署在2^10=1024个节点，包括5位datacenterId和workerId
- 5位(bit)可以表示的最大正整数是2^5-1=31，即可以用0、1、2、3……31这32个数字来表示不同的datacenterId和workerId

**12bit**-序列号，用来记录通毫秒内产生的不同id

- 12位(bit)可以表示的最大正整数是2^12-1=4095，即可以用0、1、2、3……4095这4096个数字来表示同一机器同一时间戳（毫秒）内产生4096个ID序号。

#### 源码

```java
/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 */
public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1420041600000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数据中心ID(0~31) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    //==============================Test=============================================
    /** 测试 */
    public static void main(String[] args) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}
```

```java
@Component
@Slf4j
public class SnowFlakeComponent {

    private long workId = 0;
    private long datacenterId = 1;
    private Snowflake snowflake = IdUtil.getSnowflake(workId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workId: {}", workId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workId获取失败", e);
            workId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workId, datacenterId);
        return snowflake.nextId();
    }
}
```

优点：

- 毫秒数在高位，自增序列在低位，整个ID都是趋势递增的
- 不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成ID的性能也是非常高的
- 可以根据自身业务特性分配bit位，非常灵活

缺点：

- 依赖机器时钟，如果机器时钟回拨，会导致重复ID生成
- 在单机上是递增的，但是由于设计到分布式环境，每台机器上的时钟不可能完全同步，有时候出现不适全局递增的情况（此缺点可以认为无所谓，一般分布式ID只要求趋势递增，并不会严格要求递增，90%的需求都只要求趋势递增）

#### 其他补充

百度开源的分布式唯一ID生成器UidGenerator；

Leaf——美团点评分布式ID生成系统