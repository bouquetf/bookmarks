package org.jaalon.bookmark

class Bookmark {
    String title
    String url
    List tags = []
    int index

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

}
