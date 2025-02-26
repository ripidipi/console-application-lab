package inputOutput;

import relatedToTheCollection.Collection;

import java.util.HashSet;
import java.util.Set;

public class RunningFiles {

    private Set<String> filesNames = new HashSet<>();
    private static RunningFiles instance;

    private RunningFiles () {}

    public static RunningFiles getInstance() {
        if (instance == null) {
            instance = new RunningFiles();
        }
        return instance;
    }

    public Set<String> getFilesNames() {
        return filesNames;
    }

    public void addFilesNames(String filesName) {
        this.filesNames.add(filesName);
    }

    public boolean isThere(String fileName) {
        return filesNames.contains(fileName);
    }

    public void removeFilesNames(String filesName) {
        this.filesNames.remove(filesName);
    }
}
