package org.bsuir.commands.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PageContent {
    private List<?> objectsList;

    public PageContent(List<?> objectsList) {
        this.objectsList = objectsList;
    }

    public PageContent() {
        objectsList = new ArrayList<>();
    }

    public List<?> getObjectsList() {
        return objectsList;
    }

    public void setObjectsList(List<?> objectsList) {
        this.objectsList = objectsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageContent)) return false;
        PageContent that = (PageContent) o;
        return Objects.equals(getObjectsList(), that.getObjectsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectsList());
    }

    @Override
    public String toString() {
        return "PageContent{" +
                "objectsList=" + objectsList +
                '}';
    }
}
