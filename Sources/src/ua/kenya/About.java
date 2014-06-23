package ua.kenya;

import java.io.*;

/**
 * Created by zidd on 3/23/14.
 */
public class About {
    private String about;

    public About(String path) {
        BufferedReader br;
        FileInputStream fis;
        InputStreamReader isr;
        //FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            //fr = new FileReader(new File(path));
            //br = new BufferedReader(fr);
            fis = new FileInputStream(new File(path));
            isr = new InputStreamReader(fis, "UTF8");
            br = new BufferedReader(isr);
            String s;
            while((s = br.readLine()) != null)
                sb.append(s + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        about = sb.toString();
    }

    @Override
    public String toString() {
        return about;
    }
}
