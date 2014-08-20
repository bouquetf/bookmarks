package org.jaalon.bookmark.storage
import org.jaalon.bookmark.core.Bookmark
/**
 * Created by bouquetf on 19/08/14.
 */
class InMemoryStorage implements Storage {
    def bookmarks = [] // <id, bookmark>
    def tags = [:] // <id, tagName>
    def tagsForBookmarks = [] // <tagId, bookmarkId>
    def bookmarkMaxId = 0l
    def tagMaxId = 0l

    Long insert(Bookmark bookmark) {
        bookmarkMaxId ++
        bookmark.id = bookmarkMaxId
        bookmarks += [[bookmark.id, bookmark.title, bookmark.url]]
        return bookmark.id
    }

    @Override
    Long insert(String what, String ...params) {
        switch (what) {
            case 'Bookmark':
                bookmarkMaxId ++
                def id = bookmarkMaxId
                bookmarks += [[id, params[0], params[1]]]
                return id
            case 'Tag':
                def tagId
                if (tags.values().contains(params[0])) {
                    tagId = tags.find { it.value == params[0] }.key
                } else {
                    tagId = ++ tagMaxId
                    tags.put(tagMaxId, params[0])
                }
                tagsForBookmarks += [[tagId, Long.parseLong(params[1])]]
                return tagId
            default:
                break
        }
    }

    @Override
    Bookmark query(String query) {
        def idToFind = Long.parseLong(query.split(' ')[1])
        def rawBookmark = bookmarks.find { row -> row[0] == idToFind }

        if (rawBookmark == null) {
            return null
        }

        Bookmark b = new Bookmark()
        b.id = rawBookmark[0]
        b.title = rawBookmark[1]
        b.url = rawBookmark[2]
        return b
    }

    @Override
    List<Bookmark> query(String what, String query) {
        if (query.isEmpty()) {
            return bookmarks.collect { row ->
                Bookmark b = new Bookmark()
                b.id = row[0]
                b.title = row[1]
                b.url = row[2]
                b
            }
        } else {
            Long tagId = tags.find { it.value == query }.key
            return tagsForBookmarks
                    .findAll { tagsForBookmark -> tagsForBookmark[0] == tagId }
                    .collect { this.query("Bookmark ${it[1]}") }
        }
    }

    @Override
    def remove(String removalQuery) {
        def idToRemove = Long.parseLong(removalQuery.split(' ')[1])
        bookmarks.removeAll { row -> row[0] == idToRemove }
    }
}
