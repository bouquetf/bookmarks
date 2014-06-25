package org.jaalon.bookmark

/**
 * Created by bouquetf on 25/06/14.
 */
class Repository {
    def name
    def bookmarks = []

    Repository(String name) {
        this.name = name
    }

    def isEmpty() {
        bookmarks.isEmpty()
    }

    def add(String aBookmark) {
        bookmarks.add(aBookmark)
    }

    int getSize() {
        bookmarks.size()
    }

    def remove(String aBookmark) {
        bookmarks.remove(aBookmark)
    }
}
