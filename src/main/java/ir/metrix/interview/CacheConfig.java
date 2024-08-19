package ir.metrix.interview;


import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Value("${cache.ttl}")
    private int ttl;
    @Value("${cache.name}")
    private String name;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        instance.getConfig().addMapConfig(new MapConfig(name).setTimeToLiveSeconds(ttl));
        return instance;
    }

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig cfg = ClientConfig.load();
        cfg.setClusterName("phoneCluster");
        return cfg;
    }

}