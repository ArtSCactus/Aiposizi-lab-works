package org.bsuir.commands.result;

import java.util.*;

public class PageContent {
    private List<?> tableContent;
    private Map<Object, Object> attributes;

    public PageContent(List<?> tableContent) {
        this.tableContent = tableContent;
        attributes = new HashMap<>();
    }

    public PageContent() {
        tableContent = new ArrayList<>();
        attributes = new HashMap<>();
    }

    public List<?> getTableContent() {
        return tableContent;
    }

    public void setTableContent(List<?> tableContent) {
        this.tableContent = tableContent;
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
        return Objects.equals(getTableContent(), content.getTableContent()) &&
                Objects.equals(attributes, content.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableContent(), attributes);
    }

    @Override
    public String toString() {
        return "PageContent{" +
                "objectsList=" + tableContent +
                ", attributes=" + attributes +
                '}';
    }
}
