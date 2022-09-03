package br.com.tagid.tagid.Controller;

import br.com.tagid.tagid.Entities.Tag;
import br.com.tagid.tagid.Service.TagIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagIdController {
    @Autowired
    TagIdService service;

    @GetMapping
    public List<Tag> getAll() {
        return service.listTags();
    }

    @PostMapping
    public ResponseEntity<Tag> createAttendant(@RequestBody Tag tagId) {
        service.insertTagId(tagId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tagId);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tag> updateAttendant(@RequestBody Tag tag) {
        int responseStatus = service.updateTag(tag, tag.getTagId());

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(tag);
    }

    @DeleteMapping("/delete/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteTag(@PathVariable(name = "tagId") String tagId) {
        int responseStatus = service.deleteTag(tagId);

        if (responseStatus == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/verify/{tagId}")
    public boolean isAuthorized (@PathVariable(name = "tagId") String tagId) {
        return service.isAuthorized(tagId);
    }

    @GetMapping("/findByStatus/{status}")
    public List<Tag> findTagsByStatus (@PathVariable(name = "status") boolean status) {
        return service.findTagsByStatus(status);
    }
}
