package org.jaalon.bookmark.storage

interface Storage {
    /**
     * Insert an object in database
     * Query syntax: Object [params]
     *
     * Supported:
     * Bookmark <title> <url>
     * Tag <tagName> <bookmarkid>
     * @param query
     * @return inserted Id
     */
    Long insert(String query, String ...params)

    /**
     * Query for an object in database by its Id
     * @param query
     * @param id
     * @return
     */
    Object query(String query, Long id)

    /**
     * Query for an object by a particular query.
     *
     * @param what the object to return
     * @param query the query to execute. Eg. tag
     * @return
     */
    List<Object> query(String what, String query)

    /**
     * Remove an object and its relations from a query.
     * Eg. "Bookmark <id>" to remove the bookmark marked by this id
     * @param removalQuery the query
     * @return
     */
    void remove(String removalQuery)
}
