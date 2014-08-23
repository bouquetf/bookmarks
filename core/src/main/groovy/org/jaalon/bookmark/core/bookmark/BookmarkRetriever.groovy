package org.jaalon.bookmark.core.bookmark

import org.jaalon.bookmark.storage.Storage

class BookmarkRetriever {
    private Storage storage

    public BookmarkRetriever(Storage storage) {
        this.storage = storage
    }

    Bookmark retrieve(Long id) {
        return storage.query("Bookmark ", id)
    }

    List<Bookmark> retrieve(String tag) {
        return storage.query("Bookmark", tag)
    }
}
