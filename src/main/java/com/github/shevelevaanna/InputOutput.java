package com.github.shevelevaanna;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class InputOutput {
    private PrintWriter cout;

    /**
     * ввод данных с читателя cin
     * передаем ссылку на граф, в который надо считать данные,
     */
    public MyGraph getData(MyGraph graph, BufferedReader cin) throws IOException {
        cout = new PrintWriter(System.out);
        graph = new MyGraph();

        StringTokenizer tokenizer = new StringTokenizer(cin.readLine());
        graph.numV = Integer.parseInt(tokenizer.nextToken()); //считываем количество вершин графа
        graph.numE = Integer.parseInt(tokenizer.nextToken()); //считываем количество ребер графа

        tokenizer = new StringTokenizer(cin.readLine());
        graph.startV = Integer.parseInt(tokenizer.nextToken()); //считываем начальную вершину
        // graph.finishV = Integer.parseInt(tokenizer.nextToken()); //считываем конечную вершину

        graph.newWeight = new int[graph.numV];
        for (int i = 0; i < graph.numV; ++i) {
            if (i + 1 == graph.startV) graph.newWeight[i] = 0;
            else graph.newWeight[i] = 2000000000;
        }

        //инициализация списка инцидентности графа
        graph.IncidList = new ArrayList[graph.numV];
        graph.Weight = new ArrayList[graph.numV];
        for (int i = 0; i < graph.numV; ++i) {
            graph.IncidList[i] = new ArrayList<>();
            graph.Weight[i] = new ArrayList<>();
        }
        //считываем граф, заданный списком ребер
        for (int i = 0; i < graph.numE; ++i) {
            tokenizer = new StringTokenizer(cin.readLine());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            int wt = Integer.parseInt(tokenizer.nextToken());
            v--;
            w--;
            graph.IncidList[v].add(w);
            for (int j = 0; j < graph.IncidList[w].size(); j++) {//Убираем двунаправленные ребра
                if (graph.IncidList[w].get(j) == v) return graph;
            }
            graph.Weight[v].add(wt);
        }
        return graph;
    }

    //вывод результата!!!!   передаем в метод кратчайший путь - данные, которые надо вывести
}
