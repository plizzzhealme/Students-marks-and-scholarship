package student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentList implements Serializable {
    private final ArrayList<Student> students;
    private int size;

    public StudentList() {
        this.students = new ArrayList<>();
        this.size = 0;
    }

    public void add(String surname, int group, int activity, int[] marks) {
        students.add(new Student(surname, group, activity, marks));
        size++;
    }

    public void removeRecord(int index) {
        students.remove(index);
        size--;
    }

    public void editSurname(int index, String surname) {
        students.get(index).setSurname(surname);
    }

    public void editGroup(int index, int group) {
        students.get(index).setGroup(group);
    }

    public void editActivity(int index, int activity) {
        students.get(index).setActivity(activity);
    }

    public void editMark(int index, int examNumber, int mark) {
        students.get(index).setMark(examNumber, mark);
    }

    public String getStudentInfo(int index) {
        return students.get(index).toString();
    }



    /*
    сортировка по фамилии
     */
    public void sortBySurname() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getSurname().compareTo(students.get(i + 1).getSurname()) < 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    сортировка по группе
     */
    public void sortByGroup() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getGroup() < (students.get(i + 1).getGroup())) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    сортировка по оценке (нужна указать номер экзамена)
     */
    public void sortByMark(int examNumber) {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getMarks()[examNumber] < (students.get(i + 1).getMarks()[examNumber])) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    сортировка по активности
     */
    public void sortByActivity() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getActivity() < (students.get(i + 1).getActivity())) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    сортировка по степухе
     */
    public void sortByScholarship() {
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).getScholarship() < (students.get(i + 1).getScholarship())) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /*
    возвращает список строк с данными студентов с указанной фамилией
     */
    public List<String> searchBySurname(String surname) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getSurname().equals(surname)) {
                result.add(student.toString());
            }
        }
        return result;
    }

    /*
    возвращает список строк с данными студентов с указанной группой
     */
    public List<String> searchByGroup(int group) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getGroup() == group) {
                result.add(student.toString());
            }
        }
        return result;
    }

    /*
    возвращает список строк с данными студентов
    с указанной оценкой по определенному экзамену
    (номер экзамена указывается в параметрах)
     */
    public List<String> searchByMark(int mark, int examNumber) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getMarks()[examNumber] == mark) {
                result.add(student.toString());
            }
        }
        return result;
    }

    /*
    возвращает список строк с данными студентов с указанной активностью
     */
    public List<String> searchByActivity(int activity) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getActivity() == activity) {
                result.add(student.toString());
            }
        }
        return result;
    }

    /*
    возвращает список строк с данными студентов с указанной стипендией
     */
    public List<String> searchByScholarship(int scholarship) {
        List<String> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getScholarship() == scholarship) {
                result.add(student.toString());
            }
        }
        return result;
    }


    private void swap(int index1, int index2) {
        Student temp = students.get(index1);
        students.set(index1, students.get(index2));
        students.set(index2, temp);
    }

    public int getSize() {
        return size;
    }
}