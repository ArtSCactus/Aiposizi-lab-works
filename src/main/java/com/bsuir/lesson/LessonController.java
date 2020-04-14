package com.bsuir.lesson;

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
@RequestMapping(path = "/lessons")
public class LessonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonController.class);
    @Autowired
    private LessonRepository lessonRepository;

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    HttpStatus deleteLesson(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Lesson> targetObj = lessonRepository.findById(id);
        if (!targetObj.isPresent()){
            return HttpStatus.NOT_FOUND;
        }
        lessonRepository.deleteById(id);
        return HttpStatus.OK;
    }

    @PutMapping(path = "/add")
    public @ResponseBody
    HttpStatus addLesson(@Valid @RequestBody Lesson jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
       // Gson gson = new Gson();
      //  Lesson student = gson.fromJson(jsonObj, Lesson.class);
        lessonRepository.save(jsonObj);
        return HttpStatus.CREATED;
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    HttpStatus updateLesson(@Valid @RequestBody Lesson jsonObj) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
      //  Gson gson = new Gson();
       // Lesson lesson = gson.fromJson(jsonObj, Lesson.class);
        lessonRepository.save(jsonObj);
        return HttpStatus.OK;
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewStudent(@RequestParam Long id
            , @RequestParam Long groupId, @RequestParam Long subjectId, @RequestParam Long teacherId) {
        Lesson n = new Lesson(id, groupId, subjectId, teacherId);
        try {
            lessonRepository.save(n);
        } catch (RuntimeException e) {
            LOGGER.error(e.getCause().getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Lesson> getAllStudents() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        return lessonRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Lesson getById(@PathVariable Long id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        logRequest(request);
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        return lessonOptional.orElse(null);
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
