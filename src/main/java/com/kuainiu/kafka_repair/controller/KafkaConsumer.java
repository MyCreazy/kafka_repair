package com.kuainiu.kafka_repair.controller;

import com.kuainiu.kafka_repair.service.DataFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by tjh.
 * Date: 2019/5/7 下午2:43
 **/
@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private DataFilterService dataService;


 /*   @KafkaListener(topicPartitions = @TopicPartition(topic = "${spring.kafka.topic}",
            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")))*/

    @KafkaListener(topics = {"${spring.kafka.topic}"})
    public void receive(String message) {
        try {
            dataService.solveMessage(message);
        } catch (Exception ex) {
            logger.error("发生异常:" + ex + "数据[" + message + "]");
        }
    }
}
