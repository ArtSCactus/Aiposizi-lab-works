package com.bsuir.student;

import com.bsuir.teacher.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/students")
@CrossOrigin(origins = "http://university-view.herokuapp.com")
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentRepository studentRepository;

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    HttpStatus deleteStudent(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Student> targetObj = studentRepository.findById(id);
        if (!targetObj.isPresent()) {
            return HttpStatus.NOT_FOUND;
        }
        studentRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/add")
    public @ResponseBody
    HttpStatus addStudent(@Valid @RequestBody Student jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        studentRepository.save(jsonObj);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    HttpStatus updateStudent(@Valid @RequestBody Student jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        studentRepository.save(jsonObj);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Student> getAllStudents() {
        // This returns a JSON or XML with the user
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        return studentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Student getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Student> teacherOptional = studentRepository.findById(id);
        return teacherOptional.orElse(null);
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

    private void logRequest(HttpServletRequest request) {
        LOGGER.info(String.format("request:\nmethod:%s\nheader:%s\nuri:%s\nfrom addr:%s\nfrom port:%s",
                request.getMethod(),
                request.getHeader("Accept"),
                request.getRequestURI(),
                request.getRemoteAddr(),
                request.getRemotePort()));
    }
}
