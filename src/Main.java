import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(72, 2, 4, 90.3);
        GameProgress game2 = new GameProgress(99, 4, 7, 42.73);
        GameProgress game3 = new GameProgress(100, 1, 1, 0.2);
        saveGame("X://Games1//savegames//game1.dat", game1);
        saveGame("X://Games1//savegames//game2.dat", game2);
        saveGame("X://Games1//savegames//game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("X://Games1//savegames//game1.dat");
        arrayList.add("X://Games1//savegames//game2.dat");
        arrayList.add("X://Games1//savegames//game3.dat");
        zipFiles(arrayList);
        File game1dat = new File("X://Games1//savegames//game1.dat");
        File game2dat = new File("X://Games1//savegames//game2.dat");
        File game3dat = new File("X://Games1//savegames//game3.dat");
        if (game1dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void zipFiles(List<String> arrayList) {
        try (ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream("X://Games1//savegames//zip.zip"))) {
            for (String i : arrayList) {
                try (FileInputStream fis = new FileInputStream(i)) {
                    ZipEntry entry = new ZipEntry(i);
                    zipout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zipout.write(fis.read());
                    }
                    zipout.closeEntry();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}