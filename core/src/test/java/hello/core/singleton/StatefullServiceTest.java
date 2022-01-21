package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefullServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefullService statefullService1 = ac.getBean(StatefullService.class);
        StatefullService statefullService2 = ac.getBean(StatefullService.class);

        // ThreadA: 사용자A 10000원 주문
        int userAPrice = statefullService1.order("userA", 10000);
        // ThreadB: 사용자B 20000원 주문
        int userBPrice = statefullService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefullService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefullService1.getPrice()).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefullService statefullService() {
            return new StatefullService();
        }
    }
}