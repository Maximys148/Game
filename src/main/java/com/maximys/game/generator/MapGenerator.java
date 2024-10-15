package com.maximys.game.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapGenerator {
    private final int width;
    private final int height;
    private final Integer[][] map;
    private final boolean[][] visited;
    private final Random random;

    public MapGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Integer[height][width];
        this.visited = new boolean[height][width];
        this.random = new Random();

        // Инициализация карты
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[y][x] = 1; // Заполняем стенами
                visited[y][x] = false; // Все клетки изначально непосещены
            }
        }
    }

    public Integer[][] generateMaze() {
        generatePath(1, 1); // Начинаем с координат (1, 1)
        return map;
    }

    private void generatePath(Integer x, Integer y) {
        visited[y][x] = true;
        map[y][x] = 0; // Открываем клетку

        // Список направлений (восток, запад, юг, север)
        List<Integer[]> directions = new ArrayList<>();
        directions.add(new Integer[]{0, 1}); // Восток
        directions.add(new Integer[]{0, -1}); // Запад
        directions.add(new Integer[]{1, 0}); // Южный
        directions.add(new Integer[]{-1, 0}); // Северный

        // Перемешиваем направления случайным образом
        Collections.shuffle(directions, random);

        for (Integer[] direction : directions) {
            int newX = x + direction[0] * 2; // Пропускаем одну клетку
            int newY = y + direction[1] * 2; // Пропускаем одну клетку

            if (isInBounds(newX, newY) && !visited[newY][newX]) {
                // Удаляем стену между текущей клеткой и новой
                map[y + direction[1]][x + direction[0]] = 0;
                // Рекурсивно продолжаем генерацию
                generatePath(newX, newY);
            }
        }
    }

    private boolean isInBounds(int x, int y) {
        return x > 0 && x < width && y > 0 && y < height;
    }

    public Object printMap(Integer[][] map) {
        for (Integer[] row : map) {
            System.out.println(row);
        }
        return map;
    }
}
