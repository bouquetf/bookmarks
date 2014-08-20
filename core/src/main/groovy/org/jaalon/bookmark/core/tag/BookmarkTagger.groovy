package org.jaalon.bookmark.core.tag

import org.jaalon.bookmark.core.bookmark.Bookmark
import org.jaalon.bookmark.storage.Storage

/**
 * Created by bouquetf on 19/08/14.
 */
class BookmarkTagger {
    Storage storage

    def BookmarkTagger(Storage storage) {
        this.storage = storage
    }

    def tag(Bookmark bookmark, String tag) {
        storage.insert("Tag", tag, bookmark.id.toString())
    }
}
