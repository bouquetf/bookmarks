package org.jaalon.bookmark

/**
 * Created by bouquetf on 19/08/14.
 */
class InMemoryStorage implements Storage {
    def bookmarks = [:]
    def id = 0

    @Override
    def insert(Bookmark bookmark) {
        id ++
        bookmark.id = id
        bookmarks.put(id, bookmark)
        return bookmark.id
    }

    @Override
    Bookmark query(String query) {
        def idToFind = Integer.parseInt(query.split(' ')[1])
        return bookmarks.get(idToFind)
    }

    @Override
    def remove(String removalQuery) {
        def idToRemove = Integer.parseInt(removalQuery.split(' ')[1])
        bookmarks.remove(idToRemove)
    }
}
