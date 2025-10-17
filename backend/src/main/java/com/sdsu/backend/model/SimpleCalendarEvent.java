// TEST MODEL FOR MYSQL POSTING
package com.sdsu.backend.model;

import jakarta.persistence.Entity;

@Entity
public class SimpleCalendarEvent extends CalendarEvent {
    public SimpleCalendarEvent() {
        super();
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
