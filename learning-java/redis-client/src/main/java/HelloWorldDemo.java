import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-12-10
 */
public class HelloWorldDemo {

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://@127.0.0.1:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("msg", "hello world");
        System.out.println(syncCommands.get("msg"));
        connection.close();
        redisClient.shutdown();
    }

}
