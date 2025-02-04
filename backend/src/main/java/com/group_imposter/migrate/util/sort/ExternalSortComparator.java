package com.group_imposter.migrate.util.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExternalSortComparator implements Comparator<String> {
    private List<ExternalSortKey> listKey = new ArrayList<ExternalSortKey>();

    public void addKey(int startPos, int length, String sortType) {
        addKey(startPos, length, sortType, DataType.String);
    }

    public void addKey(int startPos, int length, String sortType, DataType dataType) {
        ExternalSortKey sorter = new ExternalSortKey(startPos, length, sortType, dataType);
        listKey.add(sorter);
    }

    protected List<ExternalSortKey> getListKey() {
        return listKey;
    }

    @Override
    public int compare(String str0, String str1) {
        for (ExternalSortKey key : listKey) {
            int result = 0;
            try {
                result = key.compare(str0, str1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
