import java.io.RandomAccessFile;

public class studentManager {
    private static final int ID_SIZE = 4; // bytes
    private static final int NAME_SIZE = 20; // Longitud máxima en caracteres del nombre
    private static final int GRADE_SIZE = 4; // bytes

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("./myFiles/students.dat", "rw")) {
            // Agregar estudiantes con notas
            addStudent(raf, 1, "Alex", 90.5f);
            addStudent(raf, 2, "Roberto", 85.0f);

            // Consultar y mostrar las notas de los estudiantes
            showStudent(raf, 1);
            showStudent(raf, 2);

            // Actualizar la nota de un estudiante
            updateStudentGrade(raf, 1, 95.0f);

            // Consultar y mostrar la nota actualizada
            showStudent(raf, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(RandomAccessFile raf, int id, String name, float grade) throws Exception {
        raf.seek(raf.length());
        raf.writeInt(id);
        raf.writeChars(getPaddedName(name));
        raf.writeFloat(grade);
    }

    public static float getStudentGrade(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id));
        raf.readInt();
        raf.readChar(); 
        return raf.readFloat();
    }

    public static void updateStudentGrade(RandomAccessFile raf, int id, float newGrade) throws Exception {
        raf.seek(getSeekPosition(id) + ID_SIZE + NAME_SIZE * 2);
        raf.writeFloat(newGrade);
    }

    public static void showStudent(RandomAccessFile raf, int id) throws Exception {
        String studentName = getStudentName(raf, id);
        float studentGrade = getStudentGrade(raf, id);
        System.out.println("Estudiante " + id + ": " + studentName + " - Nota: " + studentGrade);
    }

    private static long getSeekPosition(int id) {
        return (id - 1) * (ID_SIZE + NAME_SIZE * 2 + GRADE_SIZE);
    }

    private static String getStudentName(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id) + ID_SIZE);
        char[] chars = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) {
            chars[i] = raf.readChar();
        }
        return new String(chars).trim();
    }

    private static String getPaddedName(String name) {
        if (name.length() > NAME_SIZE) {
            return name.substring(0, NAME_SIZE);
        }
        return String.format("%1$-" + NAME_SIZE + "s", name);
    }
}
