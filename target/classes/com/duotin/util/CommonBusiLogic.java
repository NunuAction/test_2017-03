package com.duotin.util;

import com.duotin.util.beans.SubListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Created by xueshan on 17/1/22.
 */
public class CommonBusiLogic {

    /**
     *将大集合分成多个子集合
     * @param list
     * @param subListSize 大小
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> getSubListBySubListSize(List<T> list, Integer subListSize){
        List<List<T>> allList = new ArrayList<>();
        if(CollectionUtils.isEmpty(list)){
            return Collections.emptyList();
        }
        if(list.size()<=subListSize){
            allList.add(list);
            return allList;
        }
        for(SubListView subListView:getSubListViewList(list.size(), subListSize)){
            allList.add(list.subList(subListView.getStartIndex(),subListView.getEndIndex()));
        }
        return allList;
    }
    private static List<SubListView> getSubListViewList(Integer listSize, Integer subListSize){
        List<SubListView> subListViewList = new ArrayList<>();
        Integer multipleSize=listSize/subListSize;
        for(int i=0;i<listSize/subListSize;i++){
            SubListView subListView = new SubListView();
            subListView.setStartIndex(i);
            subListView.setEndIndex(i+subListSize);
            subListView.setSubListSize(subListSize);
            subListViewList.add(subListView);
        }
        if(listSize%subListSize>0){
            SubListView endSubListView = new SubListView();
            endSubListView.setStartIndex(multipleSize*subListSize);
            endSubListView.setEndIndex(multipleSize*subListSize+listSize%subListSize);
            endSubListView.setSubListSize(subListSize);
            subListViewList.add(endSubListView);
        }
        return subListViewList;

    }
}
