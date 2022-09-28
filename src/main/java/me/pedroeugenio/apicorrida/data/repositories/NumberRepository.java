package me.pedroeugenio.apicorrida.data.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {
    private final int[][] twoDimensionalArray = {{1, 2, 3, 4, 5, 6}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {1},
            {1, 2,}, {1, 2, 3,}};

    public int[][] GetBiArray() {
        return twoDimensionalArray;
    }
}
