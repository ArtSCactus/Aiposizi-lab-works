package com.bsuir.teacher;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(path = "/teachers")
@Validated
public class TeacherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherRepository teacherRepository;

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    HttpStatus deleteTeacher(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Teacher> targetObj = teacherRepository.findById(id);
        if (!targetObj.isPresent()) {
            return HttpStatus.NOT_FOUND;
        }
        teacherRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/add")
    public @ResponseBody
    HttpStatus addTeacher(@RequestBody String jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Gson gson = new Gson();
        Teacher teacher = gson.fromJson(jsonObj, Teacher.class);
        teacherRepository.save(teacher);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    HttpStatus updateTeacher(@RequestBody String jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Gson gson = new Gson();
        Teacher teacher = gson.fromJson(jsonObj, Teacher.class);
        teacherRepository.save(teacher);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewUser(@RequestParam String name
            , @RequestParam String surname) {
        Teacher n = new Teacher();
        n.setName(name);
        n.setSurname(surname);
        try {
            teacherRepository.save(n);
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause().getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Teacher> getAllUsers() {
        // This returns a JSON or XML with the user
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        return teacherRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Teacher getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return teacherOptional.orElse(null);
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