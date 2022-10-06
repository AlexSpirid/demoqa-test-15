package fileParseTest;

import java.util.ArrayList;

public class Task {
    public String firstName;
    public String lastName;
    public int age;
    public boolean user;
    public ArrayList<Content> contents;
    public boolean isDone;
    public static class Content {
        public int taskID;
        public String taskName;
    }

}
