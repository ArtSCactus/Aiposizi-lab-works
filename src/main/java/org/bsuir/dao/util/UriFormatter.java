package org.bsuir.dao.util;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class UriFormatter {
    private static final String URI_ARGUMENT = "\\{\\?\\}";

    public String format(String uriTemplate, Object... args) {
        for (Object arg : args) {
            uriTemplate = uriTemplate.replaceFirst(URI_ARGUMENT, arg.toString());
        }
        return uriTemplate;
    }
}
