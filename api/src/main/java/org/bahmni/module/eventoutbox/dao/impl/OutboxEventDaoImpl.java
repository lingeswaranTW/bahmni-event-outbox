package org.bahmni.module.eventoutbox.dao.impl;

import org.bahmni.module.eventoutbox.dao.OutboxEventDao;
import org.bahmni.module.eventoutbox.model.OutboxEvent;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OutboxEventDaoImpl implements OutboxEventDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public OutboxEvent save(OutboxEvent outboxEvent) {
        sessionFactory.getCurrentSession().save(outboxEvent);
        return outboxEvent;
    }
}
