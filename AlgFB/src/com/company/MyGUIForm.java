package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;

//добавить: алгоритм!

public class MyGUIForm extends JFrame{
    private JLabel descLabel;
    private JLabel resLabel;
    private JTextArea graphEdit;
    private JTextArea Log;
    private JTextField firstV;
    private JTextField countV;
    private JTextField countE;

    private JButton buttonLoad;
    private JButton buttonInit;
    private JButton buttonStep;
    private JButton buttonRun;
    private JButton buttonNew;
    private JButton buttonClear;

    private Canvas canvas;

    private InputOutput io;
    private Algorithm solution;
    private MyGraph graph;

    private JPanel rootPanel;

    private int stepV;
    private int stepE;

    // ключевое слово super, которое обозначает суперкласс, т.е. класс, производным от которого является текущий класс
    public MyGUIForm() {
        //setBounds(x, y, w, h) - указывает координаты верхней левой вершины окна, а также его ширину и высоту.
        //завершающие настройки
        this.setSize(1050,700);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(1400,700));
        this.setTitle("Algorithm Ford-Bellman.");
        this.rootPanel = new JPanel();
        rootPanel.setLayout(null);      //абсолютное позиционирование
        rootPanel.setBounds(0,0,1400,700);

        this.setBounds(300,100,750,700);
        setContentPane(rootPanel);

        JLabel label = new JLabel();
        Font currentFont = label.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.4F);
        label.setFont(newFont);
        label.setText("Graph data: ");
        label.setBounds(12,12,120,20);
        label.setVisible(true);

        JLabel labelV1 = new JLabel();
        labelV1.setFont(newFont);
        labelV1.setText("Count of vertices: ");
        labelV1.setBounds(12,32,250,20);
        labelV1.setVisible(true);

        JLabel labelV2 = new JLabel();
        labelV2.setFont(newFont);
        labelV2.setText("and edges: ");
        labelV2.setBounds(12,56,250,20);
        labelV2.setVisible(true);

        JLabel labelFV = new JLabel();
        labelFV.setFont(newFont);
        labelFV.setText("First vertex: ");
        labelFV.setBounds(12,92,350,20);
        labelFV.setVisible(true);

        JLabel labelE = new JLabel();
        labelE.setFont(newFont);
        labelE.setText("Edges with weights: ");
        labelE.setBounds(12,162,350,20);
        labelE.setVisible(true);

        JLabel labelExemple = new JLabel();
        labelExemple.setFont(newFont);
        labelExemple.setText("Exemple: first v, second v, weight");
        labelExemple.setBounds(12,187,350,20);
        labelExemple.setVisible(true);

        //кнопки управления
        this.buttonLoad = new JButton("Load");
        this.buttonInit = new JButton("Init");
        this.buttonStep = new JButton("Step");
        this.buttonRun = new JButton("Run");
        this.buttonNew = new JButton("New");
        this.buttonClear = new JButton("Clear");

        //устанавливаем размеры кнопок
        this.buttonLoad.setBounds(100,560,63,24);
        this.buttonInit.setBounds(190,560,63,24);
        this.buttonStep.setBounds(100,605,63,24);
        this.buttonRun.setBounds(190,605,63,24);
        this.buttonNew.setBounds(190,120,63,24);
        this.buttonClear.setBounds(1310,600,70,24);

        //поясняющие надписи
        this.descLabel = new JLabel();
        this.descLabel.setBounds(1060,10,728,48);
        descLabel.setFont(newFont);
        this.descLabel.setText("Description: ");

        this.resLabel = new JLabel();
        this.resLabel.setBounds(300,550,728,60);
        resLabel.setFont(newFont);
        this.resLabel.setText("Result: -");

        //строка для указания кол-ва вершин
        this.graphEdit = new JTextArea("");
        this.graphEdit.setBounds(100,230,200,14400);
        graphEdit.setFont(newFont);
        this.graphEdit.setText("1 2 -1\n2 3 2\n3 4 0\n4 5 -1\n5 6 -1\n6 7 2\n7 8 0\n8 1 -1");//
        JScrollPane scroll = new JScrollPane(graphEdit);
        scroll.setBounds(100,230,153,320);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.Log = new JTextArea("");
        this.Log.setBounds(1060,50,320,14400);
        Log.setFont(newFont);
        JScrollPane scroll2 = new JScrollPane(Log);
        scroll2.setBounds(1060,50,320,520);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.countV = new JTextField("");
        this.countV.setBounds(190,32,63,24);
        countV.setFont(newFont);
        this.countV.setText("8");

        this.countE = new JTextField("");
        this.countE.setBounds(190,56,63,24);
        countE.setFont(newFont);
        this.countE.setText("8");

        this.firstV = new JTextField("");
        this.firstV.setBounds(100,120,63,24);
        firstV.setFont(newFont);
        this.firstV.setText("1");

        this.canvas = new Canvas();
        this.canvas.setBounds(300,12,750,550);
        this.canvas.setVisible(true);

        this.rootPanel.add(this.canvas);

        //устанавливаем видимость всех объектов
        this.buttonLoad.setVisible(true);
        this.buttonInit.setVisible(true);
        this.buttonStep.setVisible(true);
        this.buttonRun.setVisible(true);
        this.buttonNew.setVisible(true);
        this.buttonClear.setVisible(true);
        this.descLabel.setVisible(true);
        this.resLabel.setVisible(true);
        scroll.setVisible(true);
        scroll2.setVisible(true);

        //добавляем объекты на панель
        this.rootPanel.add(this.buttonLoad);
        this.rootPanel.add(this.buttonInit);
        this.rootPanel.add(this.buttonStep);
        this.rootPanel.add(this.buttonRun);
        this.rootPanel.add(this.buttonNew);
        this.rootPanel.add(this.buttonClear);
        this.rootPanel.add(this.countV);
        this.rootPanel.add(this.countE);
        this.rootPanel.add(this.firstV);
        this.rootPanel.add(label);
        this.rootPanel.add(labelV1);
        this.rootPanel.add(labelV2);
        this.rootPanel.add(labelFV);
        this.rootPanel.add(labelE);
        this.rootPanel.add(labelExemple);
        this.rootPanel.add(scroll);
        this.rootPanel.add(scroll2);
        this.rootPanel.add(this.descLabel);
        this.rootPanel.add(this.resLabel);

        buttonRun.setEnabled(false);
        buttonStep.setEnabled(false);
        buttonNew.setEnabled(false);

        rootPanel.setVisible(true);

        io = new InputOutput();

        graph = new MyGraph();
        solution = new Algorithm();
        stepE = 0;
        stepV = 0;

        //открытие файла для чтения
        buttonLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fd = new JFileChooser();//диалоговое окно
                int ret = fd.showDialog(null, "Open file");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fd.getSelectedFile();       //получение выбранного файла

                    try(BufferedReader reader =new BufferedReader(new FileReader(file)))
                    {
                        // читаем из файла построчно
                        String line;
                        graphEdit.setText("");
                        countE.setText("");
                        countV.setText("");
                        firstV.setText("");
                        line = reader.readLine();
                        countE.setText(line);
                        line = reader.readLine();
                        countV.setText(line);
                        line = reader.readLine();
                        firstV.setText(line);

                        while ((line = reader.readLine()) != null) {
                            graphEdit.append(line + "\n");
                        }
                    }
                    catch(IOException ex){
                        resLabel.setText(ex.getMessage());
                    }
                }
            }
        });

        // инициализация графа
        buttonInit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    graph = io.getData(graph, new BufferedReader(new StringReader(graphEdit.getText())),
                                       new BufferedReader(new StringReader(countV.getText())),
                                       new BufferedReader(new StringReader(countE.getText())),
                                       new BufferedReader(new StringReader(firstV.getText()))); //считывание
                    solution.initializete(graph);
                    canvas.setContent(graph);//перерисовывание
                    canvas.select(graph);//выделение начальной и конечной вершин
                    Log.append("Algorithm initialized.\n");
                    resLabel.setText("Result: -");

                    buttonRun.setEnabled(true);
                    buttonStep.setEnabled(true);
                    buttonNew.setEnabled(true);
                    buttonLoad.setEnabled(false);
                    buttonInit.setEnabled(false);
                } catch (Exception e) {
                    resLabel.setText("");
                    Log.append("Exception! \n"+e.getClass().getName()+":\n"+e.getMessage()+"\n");
                }
            }
        });

        buttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    BufferedReader cin = new BufferedReader(new StringReader(firstV.getText()));
                    StringTokenizer tokenizer = new StringTokenizer(cin.readLine());
                    graph.startV = Integer.parseInt(tokenizer.nextToken()); //считываем количество вершин графа
                    graph.newWeight = new int[graph.numV];
                    for (int i = 0; i < graph.numV; ++i) {
                        if (i + 1 == graph.startV) graph.newWeight[i] = 0;
                        else graph.newWeight[i] = 2000000000;
                    }
                    solution.initializete(graph);
                    canvas.setContent(graph);//перерисовывание
                    canvas.select(graph);//выделение начальной вершины
                    Log.append("Algorithm initialized.");
                    resLabel.setText("Result: -");
                    buttonRun.setEnabled(true);
                    buttonStep.setEnabled(true);
                    buttonNew.setEnabled(true);
                    buttonLoad.setEnabled(false);
                    buttonInit.setEnabled(false);
                    stepV = 0;
                    stepE = 0;
                } catch (Exception e) {
                    resLabel.setText("");
                    Log.append("Exception! \n"+e.getClass().getName()+": \n"+e.getMessage()+"\n");
                }
            }
        });

        //выполнение алгоритма

        buttonRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    String mess = solution.fordBellman(graph);
                    String res = io.printData(graph);
                    if (mess.length() == 34) resLabel.setText("Result: " + mess);
                    else resLabel.setText("Result: " + res);
                    Log.append("Algorithm has finished.\n");
                    canvas.setContent(graph);//перерисовывание
                    canvas.select(graph);//выделение начальной и конечной вершин
                    buttonRun.setEnabled(false);
                    buttonStep.setEnabled(false);
                    buttonNew.setEnabled(false);
                    buttonLoad.setEnabled(true);
                    buttonInit.setEnabled(true);
                    stepV = 0;
                    stepE = 0;
                } catch (Exception e) {
                    resLabel.setText("");
                    Log.append("Exception! \n"+e.getClass().getName()+": \n"+e.getMessage()+"\n");
                }
            }
        });

        buttonStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    String mess = solution.move(graph, stepE);
                    Log.append(mess + "\n");
                    canvas.setContent(graph);//перерисовывание
                    canvas.select(graph);//выделение начальной и конечной вершин

                    int ch=0;
                    for (int i=0; i<graph.numV; i++)//перевод имеющегося класса графа в более удобный для алгоритма.
                        for (int j=0; j<graph.IncidList[i].size(); j++) {
                            if (stepE == ch) {
                                canvas.visited(graph,i);
                                canvas.visited(graph,graph.IncidList[i].get(j));
                            }
                            ch++;
                        }

                    stepE++;
                    if(stepE == graph.numE) {
                        stepE = 0;
                        stepV++;
                    }
                    if(stepV == graph.numV - 1){
                        mess = solution.minusCycle(graph);
                        Log.append("Algorithm has finished.\n");
                        String res = io.printData(graph);
                        if (mess.length() == 34) resLabel.setText("Result: " + mess);
                        else resLabel.setText("Result: " + res);
                        stepV = 0;
                        stepE = 0;
                        buttonRun.setEnabled(false);
                        buttonStep.setEnabled(false);
                        buttonNew.setEnabled(false);
                        buttonLoad.setEnabled(true);
                        buttonInit.setEnabled(true);
                    }
                    else {
                        buttonRun.setEnabled(true);
                        buttonStep.setEnabled(true);
                        buttonNew.setEnabled(true);
                        buttonLoad.setEnabled(false);
                        buttonInit.setEnabled(false);
                    }
                } catch (Exception e) {
                    resLabel.setText("");
                    Log.append("Exception! \n"+e.getClass().getName()+": \n"+e.getMessage()+"\n");
                }
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Log.setText("");
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}


