import com.lc.p2p.common.constants.Constants;
import com.lc.p2p.model.user.User;
import com.lc.p2p.service.loan.BidInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试投资是否会发生超卖现象
 */
public class BidInfoTest {
    public static void main(String[] args) {
        //加载配置文件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        //获取指定的bean
        BidInfoService bidInfoService = (BidInfoService) applicationContext.getBean("bidInfoServiceImpl");

        //创建一个固定的线程
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("loanId",1310699);
        paramMap.put("uid",44);
        paramMap.put("bidMoney",1);

        for(int i = 1;i<1000;i++){
            executorService.submit(new Callable<Object>() {

                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @throws Exception if unable to compute a result
                 */
                @Override
                public Object call() throws Exception {
                    return bidInfoService.invest(paramMap);
                }
            });
        }

    }
}
