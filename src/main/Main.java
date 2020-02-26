package main;

import student.StudentList;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentList studentList;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        studentList = new StudentList();
        boolean work = true;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("1. Добавить запись");
            System.out.println("2. Удалить запись");
            System.out.println("3. Редактировать запись");
            System.out.println("4. Просмотр записей");
            System.out.println("5. Поиск записей");
            System.out.println("6. Сортировка записей");
            System.out.println("7. Помощь");
            System.out.println("8. Выход");
            System.out.println("9. Импортировать данные из файла");
            System.out.println("10. Экспорт данных в файл");
            int option = in.nextInt();

            switch (option) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    removeRecord();
                    break;
                case 3:
                    editRecord();
                    break;
                case 4:
                    printRecords();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    sort();
                    break;
                case 7:
                    System.out.println("Помоги себе сам");
                    break;
                case 8:
                    work = false;
                    break;
                case 9:
                    readFromFile();
                    break;
                case 10:
                    writeToFile();
                    break;
                default:
                    System.out.println("Нет такого пункта");
            }
        } while (work);


    }

    private static void addRecord() {
        Scanner in = new Scanner(System.in);
        System.out.println("введите фамилию");
        String surname = in.next();
        System.out.println("Введите группу");
        int group = in.nextInt();
        System.out.println("Введите 5 оценок");
        int[] marks = new int[5];
        for (int i = 0; i < 5; i++) {
            marks[i] = in.nextInt();
        }
        System.out.println("Активный? 1 - да, 0 - нет");
        int activity = in.nextInt();
        studentList.add(surname, group, activity, marks);
    }

    private static void removeRecord() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер записи");
        studentList.removeRecord(in.nextInt() - 1);
    }

    private static void editRecord() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер записи");
        int index = in.nextInt() - 1;
        System.out.println(studentList.getStudentInfo(index));
        System.out.println("Что менять?");
        System.out.println("1. Фамилия");
        System.out.println("2. Группа");
        System.out.println("3. Оценка");
        System.out.println("4. Активность");
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.println("Введите фамилию");
                studentList.editSurname(index, in.next());
                break;
            case 2:
                System.out.println("Введите группу");
                studentList.editGroup(index, in.nextInt());
                break;
            case 3:
                System.out.println("Введите номер экзамена от 1 до 5");
                int examNumber = in.nextInt() - 1;
                System.out.println("Введите оценку");
                studentList.editMark(index, examNumber, in.nextInt());
                break;
            case 4:
                System.out.println("Введите активность 0 или 1");
                studentList.editActivity(index, in.nextInt());
                break;
            default:
                System.out.println("Нет такого пункта");
        }
    }

    private static void printRecords() {
        for (int i = 0; i < studentList.getSize(); i++) {
            System.out.println((i + 1) + ". " + studentList.getStudentInfo(i));
        }
    }

    private static void search() {
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите параметр для поиска");
        System.out.println("1. По фамилии");
        System.out.println("2. По группе");
        System.out.println("3. По активности");
        System.out.println("4. По оценке");
        System.out.println("5. По стипендии");
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.println("Введите фамилию");
                List<String> result = studentList.searchBySurname(in.next());

                for (String s : result) {
                    System.out.println(s);
                }
                break;
            case 2:
                System.out.println("Введите группу");
                result = studentList.searchByGroup(in.nextInt());

                for (String s : result) {
                    System.out.println(s);
                }
                break;
            case 3:
                System.out.println("Введите активность");
                result = studentList.searchByActivity(in.nextInt());

                for (String s : result) {
                    System.out.println(s);
                }
                break;
            case 4:
                System.out.println("Введите номер экзамена");
                int examNumber = in.nextInt();
                System.out.println("Введите оценку");
                result = studentList.searchByMark(examNumber, in.nextInt());

                for (String s : result) {
                    System.out.println(s);
                }
                break;
            case 5:
                System.out.println("Введите стипендию");
                result = studentList.searchByScholarship(in.nextInt());

                for (String s : result) {
                    System.out.println(s);
                }
                break;
            default:
                System.out.println("Нет такого пункта");
        }
    }

    private static void sort() {
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите параметр для сортировки");
        System.out.println("1. По фамилии");
        System.out.println("2. По группе");
        System.out.println("3. По активности");
        System.out.println("4. По оценке");
        System.out.println("5. По стипендии");
        int option = in.nextInt();

        switch (option) {
            case 1:
                studentList.sortBySurname();
                break;
            case 2:
                studentList.sortByGroup();
                break;
            case 3:
                studentList.sortByActivity();
                break;
            case 4:
                System.out.println("Введите номер экзамена");
                studentList.sortByMark(in.nextInt());
                break;
            case 5:
                studentList.sortByScholarship();
                break;
            default:
                System.out.println("Нет такого пункта");
        }
    }

    private static void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("students.info");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(studentList);
        oos.flush();
    }

    private static void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("students.info");
        ObjectInputStream ois = new ObjectInputStream(fis);
        studentList = (StudentList) ois.readObject();
    }
}