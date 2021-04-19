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
        if (get(resume.getUuid()) != null) {
            System.out.println("Resume with uuid : " + resume.getUuid() + " will be update");
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println("ERROR");
        }


    }

    public void save(Resume resume) {
        if (get(resume.getUuid()) == null && size < 10_000) {
            System.out.println("Resume with uuid : " + resume.getUuid() + " will be save");
            storage[size] = resume;
            size++;
        } else {
            System.out.println("ERROR");
        }
    }

    public Resume get(String uuid) {
        if (uuid != null) {
            System.out.println("Resume with uuid : " + uuid + " will be get");
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (get(uuid) != null) {
            System.out.println("Resume with uuid : " + uuid + " will be delete");
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = null;
                    size--;
                }
            }
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