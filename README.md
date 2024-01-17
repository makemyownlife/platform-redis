![](https://javayong.cn/pics/shipinhao/gongzhonghaonew.png)

# 1 添加依赖

```java
<dependency>
   <groupId>cn.javayong</groupId>
   <artifactId>platform-redis-client-springboot-starter</artifactId>
   <version>1.0.0-SNAPSHOT</version>
</dependency>
```

# 2 配置文件

```yaml
spring:
  redis:
    type: single
    single:
      address: 127.0.0.1:6379
```

# 3 测试类

```java
@Controller
public class SimpleController {

    private final static Logger logger = LoggerFactory.getLogger(SimpleController.class);

    @Autowired
    private RedisOperation redisOperation;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hellodanbiao() {
        String mylife = redisOperation.getStringCommand().get("hello");
        System.out.println(mylife);
        redisOperation.getStringCommand().set("hello", "zhang勇");
        return "hello-service";
    }

}
```