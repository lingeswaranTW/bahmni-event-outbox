package org.bahmni.module.eventoutbox;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EMREvent<T> extends ApplicationEvent implements ResolvableTypeProvider {

    private final T entity;
    private final EventAction action;
    private final String category;
    private final String title;
    private final String uri;
    private final String content;
    private final String tags;

    public EMREvent(T entity, EventAction action, String category, String title, String uri, String content) {
        super(entity);
        this.entity = entity;
        this.action = action;
        this.category = category;
        this.title = title;
        this.uri = uri;
        this.content = content;
        this.tags = category;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(this.entity));
    }

    public T getEntity() {
        return entity;
    }

    public EventAction getAction() {
        return action;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }

    public String getContent() {
        return content;
    }

    public String getTags() {
        return tags;
    }
}
