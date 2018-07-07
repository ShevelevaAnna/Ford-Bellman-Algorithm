package com.company;
import javax.swing.*;
import java.awt.*;

public class Canvas extends  JPanel {
    private VGraph content;

    //конструктор
    public Canvas() {
        content = null;
        this.setLayout(null);
    }

    public void select(MyGraph data) {
        content.recolor(data.startV - 1, Color.orange);//изменение цвета
        content.repaint();
    }

    public void visited (MyGraph data, int number) {
        content.recolor(number, Color.pink);//изменение цвета
        content.repaint();
    }

    //обновление содержимого (обновление графа)
    public void setContent(MyGraph data) {
        if (content != null) {
            content.setVisible(false);
        }
        content = new VGraph(data);
        this.add(content);
        this.revalidate();//обновление макета экрана
        this.repaint();//обновление графа
    }

}
