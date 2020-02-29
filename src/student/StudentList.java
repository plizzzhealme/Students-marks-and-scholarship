package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentList {
    private final ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    @Override
    public String toString() {
        return students
                .stream()
                .map(student -> student.toString() + "\n")
                .collect(Collectors.joining());
    }

    public void add(String surname, int group, int activity, int[] marks) throws Exception {
        students.add(new Student(surname, group, activity, marks));
    }

    public void removeRecord(int index) {
        students.remove(index);
    }

    public void editSurname(int index, String surname) {
        students.get(index).setSurname(surname);
    }

    public void editGroup(int index, int group) {
        students.get(index).setGroup(group);
    }

    public void editActivity(int index, int activity) throws Exception {
        students.get(index).setActivity(activity);
    }

    public void editMark(int index, int examNumber, int mark) throws Exception {
        students.get(index).setMark(examNumber, mark);
    }

    public String getStudentInfo(int index) {
        return students.get(index).toString();
    }

    public void sortBySurname() {
        students.sort(Comparator.comparing(Student::getSurname));
    }

    public void sortByGroup() {
        students.sort(Comparator.comparingInt(Student::getGroup));
    }

    public void sortByMark(int examNumber) {
        students.sort(Comparator.comparingInt(o -> o.getMarks()[examNumber]));
    }

    public void sortByActivity() {
        students.sort(Comparator.comparingInt(Student::getActivity));
    }

    public void sortByScholarship() {
        students.sort(Comparator.comparingInt(Student::getScholarship));
    }

    public List<String> searchBySurname(String surname) {
        return students
                .stream()
                .filter(student -> student.getSurname().equals(surname))
                .map(Student::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> searchByGroup(int group) {
        return students
                .stream()
                .filter(student -> student.getGroup() == group)
                .map(Student::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> searchByMark(int mark, int examNumber) {
        return students
                .stream()
                .filter(student -> student.getMarks()[examNumber] == mark)
                .map(Student::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> searchByActivity(int activity) {
        return students
                .stream()
                .filter(student -> student.getActivity() == activity)
                .map(Student::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> searchByScholarship(int scholarship) {
        return students
                .stream()
                .filter(student -> student.getScholarship() == scholarship)
                .map(Student::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}