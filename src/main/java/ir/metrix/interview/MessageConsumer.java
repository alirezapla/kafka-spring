package ir.metrix.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MessageConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class.getName());

    private final MessageProcessorService service;

    MessageConsumer(MessageProcessorService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "${topic.name}",
            batch = "true",
            concurrency = "5"
    )
    public void consume(List<Message> messages) {
        LOG.info(String.valueOf(messages.size()));
        this.service.processMessage(messages);
    }


}


