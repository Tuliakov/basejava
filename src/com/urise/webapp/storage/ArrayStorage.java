package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;


public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        if (findResumeIndex(resume.getUuid()) != -1) {
            storage[findResumeIndex(resume.getUuid())] = resume;
            System.out.println("Resume with uuid : " + resume.getUuid() + " updated");
        } else {
            System.out.println("ERROR");
        }


    }

    public int findResumeIndex(String uuid) {
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if (get(resume.getUuid()) == null && size < 10_000) {
            storage[size] = resume;
            System.out.println("Resume with uuid : " + resume.getUuid() + " saved");
            size++;
        } else {
            System.out.println("ERROR");
        }
    }

    public Resume get(String uuid) {
        if (uuid != null) {
            if (findResumeIndex(uuid) != -1) {
                System.out.println("Resume with uuid : " + uuid + " got");
                return storage[findResumeIndex(uuid)];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (findResumeIndex(uuid) != -1) {
            System.out.println("Resume with uuid : " + uuid + " deleted");
            storage[findResumeIndex(uuid)] = null;
            size--;
        } else {
            System.out.println("ERROR");
        }
        storage = Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);

    }

    /**
     * @return array, contains only Resumes in com.urise.webapp.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

}