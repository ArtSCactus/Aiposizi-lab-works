package org.bsuir.dao.rest;

import com.google.gson.Gson;
import org.bsuir.dao.common.Dao;
import org.bsuir.dao.common.RestRequestExecutor;
import org.bsuir.dao.util.UriFormatter;
import org.bsuir.dto.Lesson;
import org.bsuir.rowmappers.LessonRowMapper;

import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class LessonRestDao extends RestRequestExecutor<Lesson> implements Dao<Lesson> {
    private ResourceBundle uris;

    public LessonRestDao() {
        uris = PropertyResourceBundle.getBundle("requests/REST url");
    }

    @Override
    public Optional<Lesson> getById(Long id) {
        UriFormatter uriFormatter = new UriFormatter();
        String uri = uris.getString("get_lesson_by_id");
        uri = uriFormatter.format(uri, id);
        List<Lesson> teacherList = super.executeGet(uri, new LessonRowMapper());
        if (teacherList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(teacherList.get(0));
        }
    }

    @Override
    public List<Lesson> getAll() {
        return super.executeGet(uris.getString("get_all_lessons"), new LessonRowMapper());
    }

    @Override
    public int save(Lesson item) {
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return super.executePost(uris.getString("update_lesson"), json);
    }

    @Override
    public int removeById(Long id) {
        UriFormatter formatter = new UriFormatter();
        String uri = uris.getString("delete_lesson_by_id");
        uri = formatter.format(uri, id);
        return super.executeDelete(uri);
    }
}
