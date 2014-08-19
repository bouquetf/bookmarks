package org.jaalon.bookmark

class BookmarkRetriever {
    private Storage storage

    public BookmarkRetriever(Storage storage) {
        this.storage = storage
    }

    Bookmark retrieve(def id) {
        return storage.query("Bookmark "+id)
    }
}
