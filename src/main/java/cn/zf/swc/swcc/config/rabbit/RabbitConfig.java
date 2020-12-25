package cn.zf.swc.swcc.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 定义wcInfoQueue队列
     * @return
     */
    @Bean
    public Queue wcInfoString() {
        return new Queue("wcInfoQueue");
    }
    /**
     * 定义setInfoQueue队列
     * @return
     */
    @Bean
    public Queue setInfoString() {
        return new Queue("setInfoQueue");
    }

    /**
     * 定义sensorConfigQueue队列
     * @return
     */
    @Bean
    public Queue sensorConfigString() {
        return new Queue("sensorConfigQueue");
    }

    /**
     * 定义setDataQueue队列
     * @return
     */
    @Bean
    public Queue setDataString() {
        return new Queue("setDataQueue");
    }

    /**
     * 定义sensorDataQueue队列
     * @return
     */
    @Bean
    public Queue sensorDataString() {
        return new Queue("sensorDataQueue");
    }

    /**
     * 定义energyDataQueue队列
     * @return
     */
    @Bean
    public Queue energyDataString() {
        return new Queue("energyDataQueue");
    }

    /**
     * 定义waterDataQueue队列
     * @return
     */
    @Bean
    public Queue waterDataString() {
        return new Queue("waterDataQueue");
    }
    /**
     * 定义warnDataQueue队列
     * @return
     */
    @Bean
    public Queue warnDataString() {
        return new Queue("warnDataQueue");
    }

    /**
     * 定义heartBeatConnectionQueue队列
     * @return
     */
    @Bean
    public Queue heartBeatConnectionString() {
        return new Queue("heartBeatConnectionQueue");
    }
}