package org.jaalon.bookmark.storage
import org.jaalon.bookmark.core.Bookmark
/**
 * Created by bouquetf on 19/08/14.
 */
class InMemoryStorage implements Storage {
    def bookmarks = [:] // <id, bookmark>
    def tags = [:] // <id, tagName>
    def tagsForBookmarks = [] // <tagId, bookmarkId>
    def bookmarkMaxId = 0l
    def tagMaxId = 0l

    @Override
    Long insert(Bookmark bookmark) {
        bookmarkMaxId ++
        bookmark.id = bookmarkMaxId
        bookmarks.put(bookmarkMaxId, bookmark)
        return bookmark.id
    }

    @Override
    Long insert(String query) {
        String[] queryStrings = query.split(' ')
        def tagId = 0l
        if (tags.values().contains(queryStrings[1])) {
            tagId = tags.find { it.value == queryStrings[1] }.key
        } else {
            tagId = ++ tagMaxId
            tags.put(tagMaxId, queryStrings[1])
        }
        tagsForBookmarks += [[tagId, Long.parseLong(queryStrings[2])]]
        return tagId
    }

    @Override
    Bookmark query(String query) {
        def idToFind = Long.parseLong(query.split(' ')[1])
        return bookmarks.get(idToFind)
    }

    @Override
    List<Bookmark> query(String what, String query) {
        if (query.isEmpty()) {
            return bookmarks.values().toList()
        } else {
            Long tagId = tags.find { it.value == query }.key
            return tagsForBookmarks
                    .findAll { tagsForBookmark -> tagsForBookmark[0] == tagId }
                    .collect { bookmarks.get(it[1]) }
        }
    }

    @Override
    def remove(String removalQuery) {
        def idToRemove = Long.parseLong(removalQuery.split(' ')[1])
        bookmarks.remove(idToRemove)
    }
}
