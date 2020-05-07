package com.bsuir.teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/teachers")
@Validated
public class TeacherController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherRepository teacherRepository;

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public
    void deleteTeacher(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Teacher> targetObj = teacherRepository.findById(id);
        teacherRepository.deleteById(id);
    }

    @PutMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    HttpStatus addTeacher(@Valid @RequestBody Teacher jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        teacherRepository.save(jsonObj);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public ResponseEntity<HttpStatus> updateTeacher(@Valid @RequestBody Teacher jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        teacherRepository.save(jsonObj);
        return ResponseEntity.status(200).build();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewUser(@Valid @RequestParam String name
            ,@Valid @RequestParam String surname) {
        Teacher n = new Teacher();
        n.setName(name);
        n.setSurname(surname);
        try {
            teacherRepository.save(n);
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause().getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.CREATED;
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
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        return ResponseEntity.of(teacherOptional);
    }

    private void logRequest(HttpServletRequest request) {
        LOGGER.info(String.format("request:\nmethod:%s\nheader:%s\nuri:%s\nfrom addr:%s\nfrom port:%s",
                request.getMethod(),
                request.getHeader("Accept"),
                request.getRequestURI(),
                request.getRemoteAddr(),
                request.getRemotePort()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}