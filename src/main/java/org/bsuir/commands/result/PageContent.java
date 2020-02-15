package org.bsuir.commands.result;

import java.util.*;

public class PageContent {
    private List<?> objectsList;
    private Map<Object, Object> attributes;

    public PageContent(List<?> objectsList) {
        this.objectsList = objectsList;
    }

    public PageContent() {
        objectsList = new ArrayList<>();
        attributes = new HashMap<>();
    }

    public List<?> getObjectsList() {
        return objectsList;
    }

    public void setObjectsList(List<?> objectsList) {
        this.objectsList = objectsList;
    }

    public void setAttribute(Object key, Object value){
        attributes.put(key, value);
    }

    public void removeAttribute(Object key){
        attributes.remove(key);
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageContent)) return false;
        PageContent content = (PageContent) o;
        return Objects.equals(getObjectsList(), content.getObjectsList()) &&
                Objects.equals(attributes, content.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectsList(), attributes);
    }

    @Override
    public String toString() {
        return "PageContent{" +
                "objectsList=" + objectsList +
                ", attributes=" + attributes +
                '}';
    }
}
