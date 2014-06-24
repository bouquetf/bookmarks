package org.jaalon.bookmark

class Bookmark {
    private String title
    private String url
    int index

    def Bookmark(String title, String url) {
        this.url = url
        this.title = title
    }
}
