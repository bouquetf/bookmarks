package org.jaalon.bookmark.core.bookmark

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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Bookmark bookmark = (Bookmark) o

        if (id != bookmark.id) return false
        if (title != bookmark.title) return false
        if (url != bookmark.url) return false

        return true
    }
}
