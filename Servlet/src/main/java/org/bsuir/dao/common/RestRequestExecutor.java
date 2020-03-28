package org.bsuir.dao.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bsuir.exceptions.rest.RequestExecutionException;
import org.bsuir.rowmappers.RowMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public abstract class RestRequestExecutor<T> {
    protected List<T> executeGet(String uri, RowMapper<T> rowMapper) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            List<String> rows = br.lines().collect(Collectors.toList());
            br.close();
            getRequest.releaseConnection();
            return parse(rows, rowMapper);
        } catch (IOException e) {
            throw new RequestExecutionException(e);
        }
    }

    /**
     * Designed to create and update instances in the database.
     * (because of using only 1 method in dao to create and update objects in the database)
     *
     * @param uri  rest server uri.
     * @param json json representation of sending object.
     * @return http status code.
     */
    protected int executePost(String uri, String json) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setHeader("Accept", "application/json; charset=UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(new StringEntity(json));
            CloseableHttpResponse response = client.execute(httpPost);
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RequestExecutionException(e);
        }
    }

    /**
     * Designed to send brand-new instances to the REST server.
     *
     * @param uri  rest server uri.
     * @param json json representation of new instance.
     * @return http status code.
     */
    protected int executePut(String uri, String json) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPut httpPut = new HttpPut(uri);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setEntity(new StringEntity(json));
            CloseableHttpResponse response = client.execute(httpPut);
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RequestExecutionException(e);
        }
    }

    /**
     * Sends delete request.
     *
     * @param uri resource uri.
     * @return http status code.
     * @see RestRequestExecutor#executePost(String, String)
     */
    protected int executeDelete(String uri) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpDelete httpDelete = new HttpDelete(uri);
            httpDelete.setHeader("Accept", "application/json");
            httpDelete.setHeader("Content-type", "application/json");
            CloseableHttpResponse response = client.execute(httpDelete);
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RequestExecutionException(e);
        }
    }

    private List<T> parse(List<String> jsonRows, RowMapper<T> rowMapper) {
        Gson gson = new GsonBuilder().create();
        List<T> list = new ArrayList<>();
        for (String row : jsonRows) {
            if (row.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(row);
                for (int index = 0; index < jsonArray.length(); index++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(index);
                    list.add(gson.fromJson(jsonObject.toString(), rowMapper.getJsonMeta()));
                }
            } else {
                list.add(gson.fromJson(row, rowMapper.getJsonMeta()));
            }
        }
        return list;
    }
}
