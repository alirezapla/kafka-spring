package ir.metrix.interview;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SmsService {
    ExecutorService es = Executors.newCachedThreadPool();

    public void sendMessage(String phoneNumber, Message message) {
        try {
            Thread.sleep(1000);  //1s
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessagesAsync(String phoneNumber, List<Message> messages ){
        es.execute(() -> sendMessages(phoneNumber, messages));
    }

    public void sendMessages(String phoneNumber, List<Message> messages) {
        try {
            int i = messages.size() / 100;
            int wait = (i + 1) * 1000;
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
