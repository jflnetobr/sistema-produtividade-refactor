package src.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Nesta classe implementa-se o padrão Extract Method
*/

public class Util {
  public static void clrscr() {
    try {
      if (System.getProperty("os.name").contains("Windows"))
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      else
        Runtime.getRuntime().exec("clear");
    } catch (Exception ex) {
      System.out.println("-----------------------");
    }
  }

  public static Date parseDate(String date) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false);
    Date newDate = null;

    try {
      newDate = sdf.parse(date);
    } catch (Exception e) {
      throw e;
    }

    return newDate;
  }

  public static String formatDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    String newDate = "ERRO";

    try {
      newDate = sdf.format(date);
    } catch (Exception e) {
      System.out.println("Erro na conversao de data");
    }

    return newDate;
  }
}
