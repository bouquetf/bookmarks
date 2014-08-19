package org.jaalon.bookmark.core

/**
 * Created by bouquetf on 19/08/14.
 */
class Bookmark {
    Long id
    String title
    String url

    Boolean correspondsTo(Bookmark other) {
        other.title == this.title &&
        other.url == this.url
    }
}
