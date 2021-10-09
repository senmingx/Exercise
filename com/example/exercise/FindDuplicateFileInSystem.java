package com.example.exercise;

import java.util.*;

// LC 609
public class FindDuplicateFileInSystem {
    public List<List<String>> findDuplicate(String[] paths) {
        // Corner case
        if (paths == null) {
            throw new IllegalArgumentException();
        }

        List<List<String>> duplicates = new ArrayList<>();

        // Key: content, value: list of files have same content as key
        Map<String, List<String>> contentToFileMap = new HashMap<>();

        for (String path : paths) {
            String[] path_comp = path.split(" ");
            // path_comp[0] is directory, 1 to end are files with contents
            for (int i = 1; i < path_comp.length; i++) {
                String[] file_comp = path_comp[i].split("\\(");
                // file_comp[0] is file name, file_comp[1] is file content with ) at the end
                file_comp[1].replace(")", "");

                // Add files to contentToFileMap for key content
                List<String> files = contentToFileMap.get(file_comp[1]);
                if (files == null) {
                    files = new ArrayList<>();
                    contentToFileMap.put(file_comp[1], files);
                }
                // dir + '/' + fileName
                files.add(path_comp[0] + "/" + file_comp[0]);
            }
        }

        // Add all values of contentToFileMap to results if duplicate (size > 1)
        for (String content : contentToFileMap.keySet()) {
            List<String> files = contentToFileMap.get(content);
            if (files.size() > 1) {
                duplicates.add(files);
            }
        }

        return duplicates;
    }

    public static void main(String[] args) {
        FindDuplicateFileInSystem sol = new FindDuplicateFileInSystem();
    }
}
