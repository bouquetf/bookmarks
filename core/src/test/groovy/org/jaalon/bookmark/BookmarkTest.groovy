package org.jaalon.bookmark

import org.jaalon.bookmark.core.bookmark.Bookmark
import org.jaalon.bookmark.core.bookmark.BookmarkAdder
import org.jaalon.bookmark.core.bookmark.BookmarkRemover
import org.jaalon.bookmark.core.bookmark.BookmarkRetriever
import org.jaalon.bookmark.storage.inmemory.InMemoryStorage
import org.jaalon.bookmark.storage.Storage
import spock.lang.Shared
import spock.lang.Specification

class BookmarkTest extends Specification {
    @Shared Storage inMemoryStorage = new InMemoryStorage()
    @Shared BookmarkAdder bookmarkAdder = new BookmarkAdder(inMemoryStorage)
    @Shared BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(inMemoryStorage)
    BookmarkRemover bookmarkRemover = new BookmarkRemover(inMemoryStorage)

    def "Bookmark lifecycle"() {
        when: "I add several bookmarks"
        Bookmark bookmark = bookmarkAdder.add("title", "http://url.com")
        Bookmark bookmark2 = bookmarkAdder.add("title2", "http://url2.com")

        then: "The bookmark are retrieved and corresponding"
        Bookmark retrievedBookmark = bookmarkRetriever.retrieve(bookmark.id)
        Bookmark retrievedBookmark2 = bookmarkRetriever.retrieve(bookmark2.id)
        retrievedBookmark.correspondsTo(bookmark)
        !retrievedBookmark.correspondsTo(bookmark2)
        retrievedBookmark2.correspondsTo(bookmark2)
        !retrievedBookmark2.correspondsTo(bookmark)

        when: "I add and remove a bookmark"
        Bookmark bookmark3 = bookmarkAdder.add("title3", "http://url3.com")
        bookmarkRemover.remove(bookmark3.id)

        then: "It's not found anymore"
        Bookmark missingBookmark = bookmarkRetriever.retrieve(bookmark3.id)
        missingBookmark == null
    }
}
