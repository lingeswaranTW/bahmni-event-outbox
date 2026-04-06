package org.bahmni.module.eventoutbox;

import org.junit.Test;
import org.springframework.core.ResolvableType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EMREventTest {

    static class Appointment {
        private final String uuid;
        Appointment(String uuid) { this.uuid = uuid; }
        public String getUuid() { return uuid; }
    }

    @Test
    public void shouldReturnCorrectResolvableTypeForConcreteEntity() {
        Appointment appointment = new Appointment("apt-uuid-1");
        EMREvent<Appointment> event = new EMREvent<>(
                appointment, "appointment", "Appointment",
                "/openmrs/ws/rest/v1/appointment/apt-uuid-1", "/openmrs/ws/rest/v1/appointment/apt-uuid-1"
        );

        ResolvableType resolvableType = event.getResolvableType();

        assertNotNull(resolvableType);
        assertEquals(EMREvent.class, resolvableType.getRawClass());
        assertEquals(Appointment.class, resolvableType.getGeneric(0).resolve());
    }

    @Test
    public void shouldDefaultTagsToCategoryWhenTagsIsNull() {
        Appointment appointment = new Appointment("apt-uuid-2");
        EMREvent<Appointment> event = new EMREvent<>(
                appointment, "appointment", "Appointment",
                "/openmrs/ws/rest/v1/appointment/apt-uuid-2", "/openmrs/ws/rest/v1/appointment/apt-uuid-2"
        );

        assertEquals("appointment", event.getTags());
    }

    @Test
    public void shouldReturnAllFieldValuesSetInConstructor() {
        Appointment appointment = new Appointment("apt-uuid-3");
        EMREvent<Appointment> event = new EMREvent<>(
                appointment, "appointment", "Appointment",
                "/openmrs/ws/rest/v1/appointment/apt-uuid-3", "{\"uuid\":\"apt-uuid-3\"}"
        );

        assertEquals(appointment, event.getEntity());
        assertEquals("appointment", event.getCategory());
        assertEquals("Appointment", event.getTitle());
        assertEquals("/openmrs/ws/rest/v1/appointment/apt-uuid-3", event.getUri());
        assertEquals("{\"uuid\":\"apt-uuid-3\"}", event.getContent());
        assertEquals("appointment", event.getTags());
    }

}
