package org.bahmni.module.eventoutbox.dao.impl;

import org.bahmni.module.eventoutbox.dao.OutboxEventDao;
import org.bahmni.module.eventoutbox.model.OutboxEvent;
import org.hibernate.SessionFactory;

public class OutboxEventDaoImpl implements OutboxEventDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OutboxEvent save(OutboxEvent outboxEvent) {
        sessionFactory.getCurrentSession().save(outboxEvent);
        return outboxEvent;
    }
}
