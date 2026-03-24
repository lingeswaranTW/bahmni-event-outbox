package org.bahmni.module.eventoutbox.dao;

import org.bahmni.module.eventoutbox.model.OutboxEvent;

public interface OutboxEventDao {

    OutboxEvent save(OutboxEvent outboxEvent);
}
