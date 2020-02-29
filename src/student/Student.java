package student;

import java.util.Arrays;

public class Student {
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    private static final int[] SCHOLARSHIP_COEFFICIENTS = {0, 80, 100, 125, 150};
    private static final double MIN_AVERAGE_MARK = 6.8;
    private static final int MIN_SCHOLARSHIP_MARK = 4;
    private static final int MIN_GOOD_MARK = 7;
    private static final int MIN_EXCELLENT_MARK = 9;
    private final int[] marks;
    private String surname;
    private int group;
    private int activity;
    private int scholarship;
    private double averageMark;

    public Student(String surname, int group, int activity, int[] marks) throws Exception {
        checkActivity(activity);
        checkMarks(marks);
        this.surname = surname;
        this.group = group;
        this.activity = activity;
        this.marks = new int[5];
        System.arraycopy(marks, 0, this.marks, 0, 5);
        calcAverageMark();
        calcScholarship();
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

    public void setActivity(int activity) throws Exception {
        checkActivity(activity);
        this.activity = activity;
        calcScholarship();
    }

    private boolean isActive() {
        return activity == ACTIVE;
    }

    public void setMark(int examNumber, int mark) throws Exception {
        checkMarks(mark);
        this.marks[examNumber] = mark;
        calcAverageMark();
        calcScholarship();
    }

    public int[] getMarks() {
        return marks.clone();
    }

    public int getScholarship() {
        return scholarship;
    }

    private void checkActivity(int activity) throws Exception {
        if (activity != ACTIVE && activity != INACTIVE) {
            String template = "Недопустимое значение активности: %d. Допустимые значение 0 или 1";
            throw new Exception(String.format(template, activity));
        }
    }

    private void checkMarks(int... marks) throws Exception {
        for (int mark : marks) {
            if (mark < 0 || mark > 10) {
                String template = "Недопустимое значение оценки: %d. Допустимые значение от 0 до 10";
                throw new Exception(String.format(template, mark));
            }
        }
    }

    @Override
    public String toString() {
        String format = "%s, гр. %d, оценки: %s, %s, стипендия: %s%%";
        String marks = Arrays.toString(this.marks);
        String activity = isActive() ? "активный" : "пассивный";
        return String.format(format, surname, group, marks, activity, scholarship);
    }

    private void calcAverageMark() {
        int sum = 0;

        for (int mark : marks) {
            sum += mark;
        }
        averageMark = (double) sum / marks.length;
    }

    private void calcScholarship() {
        boolean isExcellent = true;
        boolean isGood = true;

        for (int mark : marks) {
            if (mark < MIN_SCHOLARSHIP_MARK) {
                scholarship = SCHOLARSHIP_COEFFICIENTS[0];
                return;
            }

            if (mark < MIN_EXCELLENT_MARK) {
                isExcellent = false;
            }

            if (mark < MIN_GOOD_MARK) {
                isGood = false;
            }
        }

        if (isExcellent) {
            scholarship = isActive() ? SCHOLARSHIP_COEFFICIENTS[4] : SCHOLARSHIP_COEFFICIENTS[3];
            return;
        }

        if (isGood) {
            scholarship = SCHOLARSHIP_COEFFICIENTS[2];
            return;
        }
        scholarship = averageMark >= MIN_AVERAGE_MARK ? SCHOLARSHIP_COEFFICIENTS[2] : SCHOLARSHIP_COEFFICIENTS[1];
    }
}