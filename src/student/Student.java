package student;

import java.io.Serializable;
import java.util.Arrays;

public class Student implements Serializable {
    private static final int[] SCHOLARSHIP_COEFFICIENTS = {0, 80, 100, 125, 150};
    private static final double MIN_AVERAGE_MARK = 6.8;
    private String surname;
    private int group;
    private int activity;
    private final int[] marks;
    private int scholarship;
    private double averageMark;

    public Student(String surname, int group, int activity, int[] marks) {
        this.surname = surname;
        this.group = group;
        this.activity = activity;
        this.marks = new int[5];
        System.arraycopy(marks, 0, this.marks, 0, 5);
        calcAverageMark();
        calcScholarship();
    }

    @Override
    public String toString() {
        String format = "%s, гр. %d, оценки: %s, %s, стипендия: %s%%";
        String marks = Arrays.toString(this.marks);
        String activity;

        if (isActive()) {
            activity = "активный";
        } else {
            activity = "пассивный";
        }
        return String.format(format, this.surname, this.group, marks, activity, this.scholarship);
    }

    private void calcAverageMark() {
        int sum = 0;

        for (int mark : marks) {
            sum += mark;
        }
        averageMark = (double) sum / marks.length;
    }

    private void calcScholarship() {
        boolean isExcellent = true; //флаг для оценок 9 - 10
        boolean isGood = true; //флаг для оценок 7 - 10

        for (int mark : marks) {

            /*
            если есть оценка ниже 4, обнуляем стипендию и сразу кончаем выполнение метода
             */
            if (mark < 4) {
                this.scholarship = SCHOLARSHIP_COEFFICIENTS[0];
                return;
            }

            if (mark < 7) {
                isExcellent = false;
                isGood = false;
            } else if (mark < 9) {
                isExcellent = false;
            }
        }

        if (isExcellent) {
            this.scholarship = isActive() ? SCHOLARSHIP_COEFFICIENTS[4] : SCHOLARSHIP_COEFFICIENTS[3];
            return;
        } else

        if (isGood) {
            this.scholarship = SCHOLARSHIP_COEFFICIENTS[2];
            return;
        }

        if (averageMark >= MIN_AVERAGE_MARK) {
            this.scholarship = SCHOLARSHIP_COEFFICIENTS[2];
        } else {
            this.scholarship = SCHOLARSHIP_COEFFICIENTS[1];
        }
    }

    private boolean isActive() {
        return activity == 1;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
        calcScholarship();
    }

    public int[] getMarks() {
        return marks.clone();
    }

    public void setMark(int examNumber, int mark) {
        this.marks[examNumber] = mark;
        calcAverageMark();
        calcScholarship();
    }

    public int getScholarship() {
        return scholarship;
    }
}