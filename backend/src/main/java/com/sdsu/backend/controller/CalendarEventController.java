// *******************************
// ***** UNDO AFTER TESTING ******
// *******************************
// package com.sdsu.backend.controller;

// import com.sdsu.backend.model.CalendarEvent;
// import com.sdsu.backend.repository.CalendarEventRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/events")
// @CrossOrigin(origins = "*")
// public class CalendarEventController {

//     private final CalendarEventRepository repo;

//     public CalendarEventController(CalendarEventRepository repo) {
//         this.repo = repo;
//     }

//     // PLACE CRUD OPERATIONS HERE
//     // CREATE
//     @PostMapping
//     public CalendarEvent createEvent(@RequestBody CalendarEvent event) {
//         return repo.save(event);
//     }

//     // READ ALL
//     @GetMapping
//     public List<CalendarEvent> getAllEvents() {
//         return repo.findAll();
//     }

//     // READ ONE
//     @GetMapping("/{id}")
//     public CalendarEvent getEvent(@PathVariable Long id) {
//         return repo.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
//     }

//     // UPDATE
//     @PutMapping("/{id}")
//     public CalendarEvent updateEvent(@PathVariable Long id, @RequestBody CalendarEvent updated) {
//         return repo.findById(id).map(existing -> {
//             existing.setTitle(updated.getTitle());
//             return repo.save(existing);
//         }).orElseThrow(() -> new RuntimeException("Event not found"));
//     }

//     // DELETE
//     @DeleteMapping("/{id}")
//     public String deleteEvent(@PathVariable Long id) {
//         repo.deleteById(id);
//         return "Event deleted";
//     }

//     // TESTING FOR SINGLE PARAM
//     @PostMapping
// public CalendarEvent createEvent(@RequestBody Map<String, Object> body) {
//     CalendarEvent event = new CalendarEvent.DefaultEvent();
//     if (body.containsKey("title")) {
//         event.setTitle((String) body.get("title"));
//     }
//     return repo.save(event);
// }

// }

package com.sdsu.backend.controller;

import com.sdsu.backend.model.CalendarEvent;
import com.sdsu.backend.model.SimpleCalendarEvent;
import com.sdsu.backend.repository.CalendarEventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class CalendarEventController {

    private final CalendarEventRepository repo;

    public CalendarEventController(CalendarEventRepository repo) {
        this.repo = repo;
    }

    // CREATE (works with only "title")
    @PostMapping
    public CalendarEvent createEvent(@RequestBody Map<String, Object> body) {
        SimpleCalendarEvent event = new SimpleCalendarEvent();
        if (body.containsKey("title")) {
            event.setTitle((String) body.get("title"));
        }
        return repo.save(event);
    }

    // READ ALL
    @GetMapping
    public List<CalendarEvent> getAllEvents() {
        return repo.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public CalendarEvent getEvent(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public CalendarEvent updateEvent(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        return repo.findById(id).map(existing -> {
            if (body.containsKey("title")) {
                existing.setTitle((String) body.get("title"));
            }
            // Add more fields here if needed
            return repo.save(existing);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id) {
        repo.deleteById(id);
        return "Event deleted";
    }

}
