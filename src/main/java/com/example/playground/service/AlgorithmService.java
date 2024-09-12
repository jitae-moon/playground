package com.example.playground.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AlgorithmService {

    public List<Integer> bubbleSort(List<Integer> list) {
        int length = list.size();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }

        return list;
    }

    public List<int[]> permutation(int n, int m) {
        int[] arr = new int[m];
        boolean[] visited = new boolean[n];

        List<int[]> list = new ArrayList<>();

        getPermutation(n, m, 0, arr, visited, list);

        return list;
    }

    private void getPermutation(int n, int m, int depth, int[] arr, boolean[] visited, List<int[]> list) {
        if (depth == m) {
            list.add(Arrays.copyOf(arr, arr.length));
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    arr[depth] = i + 1;
                    visited[i] = true;
                    getPermutation(n, m, depth + 1, arr, visited, list);
                    visited[i] = false;
                }
            }
        }
    }

}
