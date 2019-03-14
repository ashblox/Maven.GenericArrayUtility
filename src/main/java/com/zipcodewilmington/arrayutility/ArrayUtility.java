package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    private T[] array;

    public ArrayUtility(T[] array) {
        this.array = array;
    }

    public T[] mergeArrays(T[] arrayToMerge) {
        T[] mergedArrays = Arrays.copyOf(array, array.length + arrayToMerge.length);
        int otherIndex = 0;
        for (int i = array.length; i < mergedArrays.length; i++) {
            mergedArrays[i] = arrayToMerge[otherIndex];
            otherIndex++;
        }
        return mergedArrays;
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        T[] mergedArrays = mergeArrays(arrayToMerge);
        List<T> mergedArraysAsList = new ArrayList<>(Arrays.asList(mergedArrays));
        return Collections.frequency(mergedArraysAsList, valueToEvaluate);
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T[] mergedArrays = mergeArrays(arrayToMerge);
        T mostCommon = null;
        for (T t : mergedArrays) {
            if (mostCommon == null || countDuplicatesInMerge(arrayToMerge, t) > countDuplicatesInMerge(arrayToMerge, mostCommon)) {
                mostCommon = t;
            }
        }
        return mostCommon;
    }

    public T[] removeValue(T valueToRemove) {
        ArrayList<T> modifiableList = new ArrayList<>(Arrays.asList(array));
        while(modifiableList.contains(valueToRemove))modifiableList.remove(valueToRemove);
        return modifiableList.toArray((T[])Array.newInstance(valueToRemove.getClass(), modifiableList.size()));
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Integer count = 0;
        for (T t : array) {
            if (t.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }
}
