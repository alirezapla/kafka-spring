package ir.metrix.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MessageProcessorService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorService.class.getName());
    private final AtomicInteger processedCount = new AtomicInteger(0);
    private final Object lock = new Object();
    private final UserService userService;
    private final SmsService smsService;
    @Value("${message.count}")
    private int messageCount;

    MessageProcessorService(UserService userService, SmsService smsService) {
        this.userService = userService;
        this.smsService = smsService;
    }

    public void processMessage(List<Message> messages) {
        Map<String, List<Message>> messagesGroupedByUserId = messages.stream().collect(Collectors.groupingBy(Message::getUserId));
        for (var entry : messagesGroupedByUserId.entrySet()) {
            var phoneNumber = userService.fetchPhoneById(entry.getKey());
            smsService.sendMessagesAsync(phoneNumber, entry.getValue());
        }
        LOG.info("Processing Finished");
    }

}
