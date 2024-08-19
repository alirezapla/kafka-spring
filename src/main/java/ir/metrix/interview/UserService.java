package ir.metrix.interview;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Cacheable(cacheNames = "phone", key = "#userId")
    public String fetchPhoneById(String userId) {
        System.out.println("fetchPhoneById called");
        try {
            Thread.sleep(500);  //0.5s
            return userId;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
