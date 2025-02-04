package com.group_imposter.migrate.util.sort;

class ExternalSortKey {

    private int startPost;

    private int length;

    private String sortType;

    private DataType dataType;

    protected ExternalSortKey(int startPost, int length, String sortType) {
        this(startPost, length, sortType, DataType.String);
    }

    protected ExternalSortKey(int startPost, int length, String sortType, DataType dataType) {
        this.startPost = startPost;
        this.length = length;
        this.sortType = sortType;
        this.dataType = dataType;
    }

    protected int getStartPost() {
        return startPost;
    }

    protected int getLength() {
        return length;
    }

    protected String getSortType() {
        return sortType;
    }

    protected DataType getDataType() {
        return dataType;
    }

    protected final int compare(String str1, String str2) throws Exception {
        String key1 = str1.substring(startPost, startPost + length);
        String key2 = str2.substring(startPost, startPost + length);
        return CompareUtil.compare(key1, key2, sortType, dataType);
    }

}
