package org.jaalon.bookmark.mem

import org.jaalon.bookmark.Bookmark
import org.jaalon.bookmark.Repository

class InMemoryRepository implements Repository {
    def name
    def bookmarks = [:]
    def index = 0

    InMemoryRepository(String name) {
        this.name = name
    }

    @Override
    boolean isEmpty() {
        bookmarks.isEmpty()
    }

    @Override
    int add(InMemoryBookmark aBookmark) {
        index ++
        aBookmark.index = index
        bookmarks.put(aBookmark.index, aBookmark)

        return aBookmark.index
    }

    @Override
    List add(List<InMemoryBookmark> bookmarks) {
        return bookmarks.collect { add(it) }
    }

    @Override
    int getSize() {
        bookmarks.size()
    }

    @Override
    Bookmark remove(int index) {
        bookmarks.remove(index)
    }

    @Override
    Bookmark remove(InMemoryBookmark bookmark) {
        bookmarks.remove(bookmark.index)
    }

    @Override
    Bookmark getBookmark(int index) {
        return bookmarks.get(index) as InMemoryBookmark
    }

    @Override
    boolean contains(int index) {
        return bookmarks.containsKey(index)
    }

    @Override
    List<Bookmark> find(String tag) {
        return bookmarks.values().findAll { it.tags.contains(tag) } as List<Bookmark>
    }
}
