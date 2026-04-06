package org.bahmni.module.eventoutbox.eventListener;

import org.bahmni.module.eventoutbox.EMREvent;
import org.bahmni.module.eventoutbox.model.OutboxEvent;
import org.bahmni.module.eventoutbox.service.OutboxEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class EMREventListener {

    private OutboxEventService outboxEventService;

    @Autowired
    public void setOutboxEventService(OutboxEventService outboxEventService) {
        this.outboxEventService = outboxEventService;
    }

    @EventListener
    public void onEMREvent(EMREvent<?> event) {
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setUuid(UUID.randomUUID().toString());
        outboxEvent.setTitle(event.getTitle());
        outboxEvent.setTimestamp(new Date());
        outboxEvent.setUri(event.getUri());
        outboxEvent.setContent(event.getContent());
        outboxEvent.setCategory(event.getCategory());
        outboxEvent.setTags(event.getTags());
        outboxEventService.save(outboxEvent);
    }
}
