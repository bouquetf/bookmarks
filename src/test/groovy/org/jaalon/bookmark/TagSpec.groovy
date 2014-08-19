package org.jaalon.bookmark
import org.jaalon.bookmark.core.Bookmark
import org.jaalon.bookmark.core.BookmarkAdder
import org.jaalon.bookmark.core.BookmarkRetriever
import org.jaalon.bookmark.storage.InMemoryStorage
import org.jaalon.bookmark.storage.Storage
import spock.lang.Shared
import spock.lang.Specification

class TagSpec extends Specification {
    @Shared Storage storage = new InMemoryStorage()
    @Shared BookmarkAdder bookmarkAdder = new BookmarkAdder(storage)
    @Shared BookmarkRetriever bookmarkRetriever = new BookmarkRetriever(storage)
    @Shared BookmarkTagger bookmarkTagger = new BookmarkTagger(storage)

    def "Tag bookmarks and retrieve them"() {
        given: "A set of bookmarks"
        Bookmark noTagBookmark = bookmarkAdder.add("noTagBookmark", "url")
        Bookmark personalBookmark = bookmarkAdder.add("a personal bookmark", "url")
        Bookmark technicalBookmark1 = bookmarkAdder.add("a technical bookmark", "url")
        Bookmark technicalBookmark2 = bookmarkAdder.add("a technical bookmark 2", "url")

        when: "I tag bookmarks"
        bookmarkTagger.tag(personalBookmark, "personal")
        bookmarkTagger.tag(technicalBookmark1, "technical")
        bookmarkTagger.tag(technicalBookmark2, "technical")

        then: "I can retrieve bookmarks by their tags"
        bookmarkRetriever.retrieve("personal") == [personalBookmark]
        bookmarkRetriever.retrieve("technical") == [technicalBookmark1, technicalBookmark2]
        bookmarkRetriever.retrieve("") == [noTagBookmark, personalBookmark,
                                           technicalBookmark1, technicalBookmark2]

    }

}
