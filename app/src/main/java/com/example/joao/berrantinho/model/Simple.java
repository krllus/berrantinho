package com.example.joao.berrantinho.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Joao Carlos on 11/22/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */


public class Simple {
    private String id;
    private String title;
    private String content;

    public Simple(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public static List<Simple> generateDumbElements(String content) {
        List<Simple> list = new ArrayList<>();
        Simple simple;

        for (int i = 1; i < 10; i++) {
            String title = String.format(Locale.getDefault(), "Title %02d", i);
            simple = new Simple(title, content);
            list.add(simple);
        }
        return list;
    }

}
