package org.jaalon.bookmark

/**
 * Created by bouquetf on 19/08/14.
 */
interface Storage {
    def insert(Bookmark bookmark)

    Bookmark query(String query)

    def remove(String removalQuery)
}
