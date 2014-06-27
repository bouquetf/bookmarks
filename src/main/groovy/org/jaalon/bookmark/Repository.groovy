package org.jaalon.bookmark

import org.jaalon.bookmark.mem.InMemoryBookmark

/**
 * Created by bouquetf on 27/06/14.
 */
public interface Repository {
    Bookmark getBookmark(int index)

    List<Bookmark> find(String tag)

    int add(InMemoryBookmark aBookmark)

    List add(List<InMemoryBookmark> bookmarks)

    Bookmark remove(int index)

    Bookmark remove(InMemoryBookmark bookmark)

    int getSize()

    boolean isEmpty()

    boolean contains(int index)
}
