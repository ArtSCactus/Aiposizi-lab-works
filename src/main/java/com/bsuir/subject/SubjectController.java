package com.bsuir.subject;

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
@RequestMapping("/subjects")
public class SubjectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private SubjectRepository subjectRepository;

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    HttpStatus deleteLesson(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Subject> targetObj = subjectRepository.findById(id);
        if (!targetObj.isPresent()){
            return HttpStatus.NOT_FOUND;
        }
        subjectRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/add")
    public @ResponseBody
    HttpStatus addLesson(@Valid @RequestBody Subject jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
      //  Gson gson = new Gson();
        //Subject student = gson.fromJson(jsonObj, Subject.class);
        subjectRepository.save(jsonObj);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    HttpStatus updateLesson(@Valid @RequestBody Subject jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        //Gson gson = new Gson();
       // Subject subject = gson.fromJson(jsonObj, Subject.class);
        subjectRepository.save(jsonObj);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewStudent(@RequestParam Long id
            , @RequestParam String name, @RequestParam Integer hours){
        Subject n = new Subject(id, name, hours);
        try {
            subjectRepository.save(n);
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause().getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Subject> getAllStudents() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        return subjectRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Subject getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Subject> groupOptional = subjectRepository.findById(id);
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
