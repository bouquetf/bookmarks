package org.jaalon.bookmark

/**
 * Created by bouquetf on 19/08/14.
 */
class BookmarkAdder {
    private Storage storage

    public BookmarkAdder(Storage storage) {
        this.storage = storage
    }

    Bookmark add(String title, String url) {
        Bookmark bookmark = new Bookmark()
        bookmark.title = title
        bookmark.url = url
        bookmark.id = storage.insert(bookmark)
        return bookmark
    }
}
