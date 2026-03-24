package org.bahmni.module.eventoutbox.service;

import org.bahmni.module.eventoutbox.model.OutboxEvent;

public interface OutboxEventService {

    OutboxEvent save(OutboxEvent outboxEvent);
}
