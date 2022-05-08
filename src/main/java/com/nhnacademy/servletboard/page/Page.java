package com.nhnacademy.servletboard.page;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class Page<T> {

    private final List<T> pageList;
    private final int pageNumber;
    private final int pageSize;

    public Page(List<T> pageList, int currentPageNumber, int pageSize) {
        this.pageList = pageList;
        this.pageNumber = currentPageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalPageCount() {
        int additionalPage = pageList.size() % pageSize == 0 ? 0 : 1;
        return pageList.size() / pageSize + additionalPage;
    }

    public long getTotalCount() {
        return pageList.size();
    }

    public List<T> getList() {
        return pageList.stream()
                       .skip(getStartIndex())
                       .limit(pageSize)
                       .collect(toList());
    }

    private int getStartIndex() {
        return (pageNumber - 1) * pageSize;
    }
}
