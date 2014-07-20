package org.jaalon.bookmark.model

class Repository {
    def name
    Map<Number, Bookmark> bookmarks = new HashMap<>()
    def index = 0
    def bookmarkIndex = 0

    Repository(Repository repository) {
        this.name = repository.name
        this.index = repository.index
        repository.bookmarks.each { number, bookmark ->
            add(new Bookmark(bookmark))
        }
    }

    Repository(String name) {
        this.name = name
    }

    boolean isEmpty() {
        bookmarks.isEmpty()
    }

    int add(Bookmark aBookmark) {
        bookmarkIndex ++
        aBookmark.index = bookmarkIndex
        bookmarks.put(aBookmark.index, aBookmark)

        return aBookmark.index
    }

    List add(List<Bookmark> bookmarks) {
        return bookmarks.collect { add(it) }
    }

    int getSize() {
        bookmarks.size()
    }

    Bookmark remove(int index) {
        bookmarks.remove(index)
    }

    Bookmark remove(Bookmark bookmark) {
        bookmarks.remove(bookmark.index)
    }

    Bookmark getBookmark(int index) {
        return bookmarks.get(index) as Bookmark
    }

    Bookmark getBookmark(String title) {
        bookmarks.values().find {
            it.title == title
        }
    }

    boolean contains(int index) {
        return bookmarks.containsKey(index)
    }

    List<Bookmark> find(String tag) {
        return bookmarks.values().findAll { it.tags.contains(tag) } as List<Bookmark>
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Repository that = (Repository) o

        if (bookmarks != that.bookmarks) return false
        if (name != that.name) return false
        if (!((index == 0) || (that.index == 0) || (index == that.index))) return false

        return true
    }

}
