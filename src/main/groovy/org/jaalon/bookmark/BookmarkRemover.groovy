package org.jaalon.bookmark

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
