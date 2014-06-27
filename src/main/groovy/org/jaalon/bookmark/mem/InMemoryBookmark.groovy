package org.jaalon.bookmark.mem

import org.jaalon.bookmark.Bookmark

class InMemoryBookmark implements Bookmark{
    String title
    String url
    List tags = []
    int index

    def InMemoryBookmark(String title, String url) {
        this.url = url
        this.title = title
    }

    @Override
    def tag(String aTag) {
        this.tags += aTag
    }

    @Override
    def tag(List tagList) {
        this.tags += tagList
    }

}
