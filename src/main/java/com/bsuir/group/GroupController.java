package com.bsuir.group;

import com.bsuir.teacher.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/groups")
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private GroupRepository groupRepository;

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    HttpStatus deleteLesson(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<StudentGroup> targetObj = groupRepository.findById(id);
        if (!targetObj.isPresent()){
            return HttpStatus.NOT_FOUND;
        }
        groupRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/add")
    public @ResponseBody
    HttpStatus addLesson(@Valid @RequestBody StudentGroup jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
       // Gson gson = new Gson();
       // StudentGroup student = gson.fromJson(jsonObj, StudentGroup.class);
        groupRepository.save(jsonObj);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    HttpStatus updateLesson(@Valid @RequestBody StudentGroup jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
       // Gson gson = new Gson();
       // StudentGroup lesson = gson.fromJson(jsonObj, StudentGroup.class);
        groupRepository.save(jsonObj);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewStudent(@RequestParam Long id
            , @RequestParam String name, @RequestParam String specialty){
        StudentGroup n = new StudentGroup(id, name, specialty);
        try {
            groupRepository.save(n);
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause().getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<StudentGroup> getAllStudents() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        return groupRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    StudentGroup getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<StudentGroup> groupOptional = groupRepository.findById(id);
        return groupOptional.orElse(null);
    }

    private void logRequest(HttpServletRequest request) {
        LOGGER.info(String.format("request:\nmethod:%s\nheader:%s\nuri:%s\nfrom addr:%s\nfrom port:%s",
                request.getMethod(),
                request.getHeader("Accept"),
                request.getRequestURI(),
                request.getRemoteAddr(),
                request.getRemotePort()));
    }
}
