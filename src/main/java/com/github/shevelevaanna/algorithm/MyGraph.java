package com.github.shevelevaanna.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGraph {
    public Map<Integer, List<Integer>> IncidList = new HashMap<>();  //список инцидентности графа (список из пары вершин (1,2), где напрвление ребра 1 -> 2)
    public Map<Integer, List<Integer>> Weight = new HashMap<>();     //список весов графа (в каком порядке добавляются пары в списке инцид, в таком и добавляются веса)
    public int newWeight[];
    public int numV;    //кол-во вершин
    public int numE;    //кол-во ребер
    public int startV;  //начальная вершина
}
