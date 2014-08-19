package org.jaalon.bookmark.storage

import org.jaalon.bookmark.core.Bookmark

/**
 * Created by bouquetf on 19/08/14.
 */
interface Storage {
    Long insert(Bookmark bookmark)

    Bookmark query(String query)

    def remove(String removalQuery)
}
