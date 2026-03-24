package org.bahmni.module.eventoutbox.service.impl;

import org.bahmni.module.eventoutbox.dao.OutboxEventDao;
import org.bahmni.module.eventoutbox.model.OutboxEvent;
import org.bahmni.module.eventoutbox.service.OutboxEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("outboxEventService")
public class OutboxEventServiceImpl implements OutboxEventService {

    private OutboxEventDao outboxEventDao;

    @Autowired
    public void setOutboxEventDao(OutboxEventDao outboxEventDao) {
        this.outboxEventDao = outboxEventDao;
    }

    @Override
    @Transactional
    public OutboxEvent save(OutboxEvent outboxEvent) {
        return outboxEventDao.save(outboxEvent);
    }
}
