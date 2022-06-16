import redis.clients.jedis.Jedis;

public class RedisController {
    public static void main(String[] args) {
        redisTester();
    }
    public static void redisTester(){
        Jedis jedis = new Jedis("localhost",6379,100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();//开始毫秒数
            while (true){
                long end = System.currentTimeMillis();
                if (end - start >= 1000){//等于一秒时 退出循环
                    break;
                }
                i++;
                jedis.set("test"+i,i+"");
                jedis.close();
            }
        }finally {
            jedis.close();
        }

    }

}
