package org.jaalon.bookmark.core.bookmark

import org.jaalon.bookmark.storage.Storage

/**
 * Created by bouquetf on 19/08/14.
 */
class BookmarkRemover {
    Storage storage

    def BookmarkRemover(Storage storage) {
        this.storage = storage
    }

    def remove(def id) {
        storage.remove("Bookmark " + id)
    }
}
