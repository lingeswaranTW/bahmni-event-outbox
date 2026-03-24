package org.bahmni.module.eventoutbox.service.impl;

import org.bahmni.module.eventoutbox.dao.OutboxEventDao;
import org.bahmni.module.eventoutbox.model.OutboxEvent;
import org.bahmni.module.eventoutbox.service.OutboxEventService;

public class OutboxEventServiceImpl implements OutboxEventService {

    private OutboxEventDao outboxEventDao;

    public void setOutboxEventDao(OutboxEventDao outboxEventDao) {
        this.outboxEventDao = outboxEventDao;
    }

    @Override
    public OutboxEvent save(OutboxEvent outboxEvent) {
        return outboxEventDao.save(outboxEvent);
    }
}
