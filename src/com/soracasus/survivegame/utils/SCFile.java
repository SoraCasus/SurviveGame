package com.soracasus.survivegame.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class SCFile {

    private static final String FILE_SEPARATOR = "/";

    private String name;
    private String path;

    public SCFile(String path) {
        this.path = FILE_SEPARATOR + path;
        String[] dirs = path.split(FILE_SEPARATOR);
        this.name = dirs[dirs.length - 1];
    }

    public InputStream getStream() {
        return this.getClass().getResourceAsStream(path);
    }

    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getStream()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCFile scFile = (SCFile) o;
        return Objects.equals(path, scFile.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    @Override
    public String toString() {
        return "SCFile{" +
                "name:" + name +
                ", path:" + path +
                '}';
    }
}
