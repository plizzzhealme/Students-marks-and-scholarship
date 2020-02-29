package main;

import student.StudentList;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static StudentList studentList;

    public static void main(String[] args) {
        studentList = new StudentList();
        boolean work = true;
        Scanner in = new Scanner(System.in);

        do {
            showMainMenu();
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
                case 10:
                    break;
                default:
                    System.out.println("Нет такого пункта");
            }
        } while (work);
    }

    private static void addRecord() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите фамилию");
        String surname = in.next();

        System.out.println("Введите группу");
        int group = in.nextInt();

        System.out.println("Введите 5 оценок");
        int[] marks = IntStream.range(0, 5).map(i -> in.nextInt()).toArray();

        System.out.println("Введите активность");
        int activity = in.nextInt();

        try {
            studentList.add(surname, group, activity, marks);
        } catch (Exception e) {
            showExceptionMsg(e);
        }
    }

    private static void removeRecord() {
        System.out.println("Введите номер записи");
        studentList.removeRecord(new Scanner(System.in).nextInt() - 1);
    }

    private static void editRecord() {
        System.out.println("Введите номер записи");
        Scanner in = new Scanner(System.in);
        int index = in.nextInt() - 1;
        System.out.println(studentList.getStudentInfo(index));
        showParameterSelectionMenu();
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

                try {
                    studentList.editMark(index, examNumber, in.nextInt());
                } catch (Exception e) {
                    showExceptionMsg(e);
                }
                break;
            case 4:
                System.out.println("Введите активность");

                try {
                    studentList.editActivity(index, in.nextInt());
                } catch (Exception e) {
                    showExceptionMsg(e);
                }
                break;
            default:
                System.out.println("Нет такого пункта");
        }
    }

    private static void printRecords() {
        System.out.println(studentList);
    }

    private static void search() {
        showSearchAndSortMenu();
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.println("Введите фамилию");
                studentList.searchBySurname(in.next()).forEach(System.out::println);
                break;
            case 2:
                System.out.println("Введите группу");
                studentList.searchByGroup(in.nextInt()).forEach(System.out::println);
                break;
            case 3:
                System.out.println("Введите активность");
                studentList.searchByActivity(in.nextInt()).forEach(System.out::println);
                break;
            case 4:
                System.out.println("Введите номер экзамена");
                int examNumber = in.nextInt();
                System.out.println("Введите оценку");
                studentList.searchByMark(examNumber, in.nextInt()).forEach(System.out::println);
                break;
            case 5:
                System.out.println("Введите стипендию");
                studentList.searchByScholarship(in.nextInt()).forEach(System.out::println);
                break;
            default:
                System.out.println("Нет такого пункта");
        }
    }

    private static void sort() {
        showSearchAndSortMenu();
        Scanner in = new Scanner(System.in);
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

    private static void showMainMenu() {
        System.out.println("Выберите действие:");
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
    }

    private static void showSearchAndSortMenu() {
        showParameterSelectionMenu();
        System.out.println("5. Стипендия");
    }

    private static void showParameterSelectionMenu() {
        System.out.println("Выберите параметр:");
        System.out.println("1. Фамилия");
        System.out.println("2. Группа");
        System.out.println("3. Активность");
        System.out.println("4. Оценка");
    }

    private static void showExceptionMsg(Exception e) {
        System.out.println("Данные не изменены. " + e.getMessage());
    }
}