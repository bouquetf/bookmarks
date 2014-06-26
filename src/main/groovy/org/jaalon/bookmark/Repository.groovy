package org.jaalon.bookmark

class Repository {
    def name
    def bookmarks = [:]
    def index = 0

    Repository(String name) {
        this.name = name
    }

    def isEmpty() {
        bookmarks.isEmpty()
    }

    def add(Bookmark aBookmark) {
        index ++
        aBookmark.index = index
        bookmarks.put(aBookmark.index, aBookmark)

        return aBookmark.index
    }

    def add(List<Bookmark> bookmarks) {
        return bookmarks.collect { add(it) }
    }

    int getSize() {
        bookmarks.size()
    }

    def remove(int index) {
        bookmarks.remove(index)
    }

    def remove(Bookmark bookmark) {
        bookmarks.remove(bookmark.index)
    }

    Bookmark getBookmark(int index) {
        return bookmarks.get(index) as Bookmark
    }

    def contains(int index) {
        return bookmarks.containsKey(index)
    }

    def find(String tag) {
        return bookmarks.values().findAll { it.tags.contains(tag) }
    }
}
