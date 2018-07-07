
package com.company;

class Enge {
    public int eFirst;
    public int eSecond;
    public int w;
}

public class Algorithm {
    private Enge listE[];
    public void initializete(MyGraph graph)
    {
        listE = new Enge [graph.numE];
        int ch = 0;
        for (int i=0; i<graph.numV; i++)//перевод имеющегося класса графа в более удобный для алгоритма.
            for (int j=0; j<graph.IncidList[i].size(); j++) {
                listE[ch] = new Enge();
                listE[ch].eFirst = i;
                listE[ch].eSecond = graph.IncidList[i].get(j);
                listE[ch].w = graph.Weight[i].get(j);
                ch++;
            }
    }

    public String move(MyGraph graph, int i) {
        int mem;
        if (graph.newWeight[listE[i].eSecond] > (graph.newWeight[listE[i].eFirst] + listE[i].w)) {
            mem = graph.newWeight[listE[i].eSecond];
            graph.newWeight[listE[i].eSecond] = graph.newWeight[listE[i].eFirst] + listE[i].w; // Обновляем расстояние
            if (mem == 2000000000) {
                return "Edge (v"+(listE[i].eFirst+1)+",v"+(listE[i].eSecond+1)+"): inf > ("+
                        graph.newWeight[listE[i].eFirst] + " + "+listE[i].w +") -> \nweight edge = "+
                        (graph.newWeight[listE[i].eFirst] + listE[i].w);
            }
            else {
                return "Edge (v"+(listE[i].eFirst+1)+",v"+(listE[i].eSecond+1)+"): "+mem+"> ("+
                        graph.newWeight[listE[i].eFirst] + " + "+listE[i].w +") -> \nweight edge = "+
                        graph.newWeight[listE[i].eSecond];
            }

        }
        return "Edge (v"+(listE[i].eFirst+1)+",v"+(listE[i].eSecond+1)+"): "+graph.newWeight[listE[i].eSecond]+"<= ("+
                graph.newWeight[listE[i].eFirst] + " + "+listE[i].w +") -> \nweight edge = "+
                graph.newWeight[listE[i].eSecond];
    }

    public String minusCycle(MyGraph graph) {
        for (int i = 0; i < graph.numE; ++i) {
            if (graph.newWeight[listE[i].eSecond] > graph.newWeight[listE[i].eFirst] + listE[i].w)
                return "Найден цикл отрицательной длины!!!";
        }
        return "";
    }

    public String fordBellman(MyGraph graph)
    {
        String res = "";
        String minus = "";
        for (int v = 0; v < graph.numV - 1; ++v) { // Перебираем веса
            for (int i = 0; i < graph.numE; ++i) {// Перебираем ребра
                res += move(graph, i);
            }
        }

        minus = minusCycle(graph);
        if (minus.length() > 1) return minus;
        else return res;
    }
}
