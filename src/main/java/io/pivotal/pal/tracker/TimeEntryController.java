package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    TimeEntryRepository  inMemoryTimeEntryRepository;

    @Autowired
    public TimeEntryController (TimeEntryRepository inMemoryTimeEntryRepository){
        this.inMemoryTimeEntryRepository = inMemoryTimeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){

            return new ResponseEntity<>(inMemoryTimeEntryRepository.create(timeEntry), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry temp = inMemoryTimeEntryRepository.find(id);
        if(temp!=null) {
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return ResponseEntity.ok(inMemoryTimeEntryRepository.list());

    }
    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long timeEntryId, @RequestBody TimeEntry timeEntry){
        TimeEntry temp = inMemoryTimeEntryRepository.update(timeEntryId,timeEntry);
        if(temp!=null) {
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable Long timeEntryId){

            inMemoryTimeEntryRepository.delete(timeEntryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
