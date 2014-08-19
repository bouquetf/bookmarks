package org.jaalon.bookmark

/**
 * Created by bouquetf on 19/08/14.
 */
class Bookmark {
    def id
    String title
    String url

    Boolean correspondsTo(Bookmark other) {
        other.title == this.title &&
        other.url == this.url
    }
}
