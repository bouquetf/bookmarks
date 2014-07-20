package org.jaalon.bookmark.model

class Bookmark {
    String title
    String url
    List tags = []
    int index

    def Bookmark(Bookmark bookmark) {
        this.title = bookmark.title
        this.url = bookmark.url
        this.index = bookmark.index
        bookmark.tags.each {
            tags.add(it)
        }
    }

    def Bookmark(String title, String url) {
        this.url = url
        this.title = title
    }


    def tag(String aTag) {
        this.tags += aTag
    }

    def tag(List tagList) {
        this.tags += tagList
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Bookmark that = (Bookmark) o

        if (url != that.url) return false
        if (title != that.title) return false
        if (!((index == 0) || (that.index == 0) || (index == that.index))) return false

        return true
    }

}
