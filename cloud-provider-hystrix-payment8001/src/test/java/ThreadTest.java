import com.bixingyu.springcloud.PaymentHystrixMain8001;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BiXingyu
 * @create 2022-12-25 21:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentHystrixMain8001.class ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ThreadTest {

    @Resource
    private RestTemplate restTemplate;
    private static String serverPort = "8001";

    private Integer count;

    public static final String REQURL= "http://localhost:" + serverPort + "/payment/hystrix/timeout/";

    @Test
    public void testThread(){
        ArrayList<UrlThread> threads = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            count = i;
            threads.add(new UrlThread());
            log.info("线程{}已装填完成" , i + 1);
        }

            for(UrlThread thread : threads){
                thread.start();
            }
    }
    class UrlThread extends Thread implements Runnable{

        @Override
        public void run() {
            restTemplate.getForObject(REQURL +  count,String.class );
            log.info("线程{}执行，请求地址{}" , Thread.currentThread().getName(),REQURL +  count);
        }
    }
}
