package org.jaalon.bookmark.storage
import org.jaalon.bookmark.core.Bookmark
/**
 * Created by bouquetf on 19/08/14.
 */
interface Storage {
    /**
     * Insert an object in database
     * Query syntax: Object [params]
     *
     * Supported:
     * Tag <tagName> <bookmark>
     * @param query
     * @return
     */
    Long insert(String query, String ...params)

    Bookmark query(String query)

    List<Bookmark> query(String what, String query)

    def remove(String removalQuery)
}
