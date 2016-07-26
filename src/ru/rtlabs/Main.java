package ru.rtlabs;

import ru.rtlabs.DB.DBWorker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

       // DBWorker connection = new DBWorker();
        Cas cas = new Cas();
        try {

            System.out.println(cas.getHTML("https://03.r-mis.ru"));

            cas.getResponce();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
