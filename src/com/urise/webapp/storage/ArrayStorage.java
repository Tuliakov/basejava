package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
            System.out.println("Resume with uuid : " + uuid + " updated!");
        } else {
            System.out.println(" ERROR. Resume with uuid : " + uuid + " not found!");
        }
    }

    private int findResumeIndex(String uuid) {
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
        int resumeIndex = findResumeIndex(resume.getUuid());
        if(size < storage.length) {
            if (resumeIndex == -1) {
                storage[size] = resume;
                System.out.println("Resume with uuid : " + resume.getUuid() + " saved!");
                size++;
            } else {
                System.out.println("ERROR. Resume : " + resume.getUuid() + " already exits! ");
            }
        }else {
            System.out.println("Storage is full");
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (uuid != null) {
            if (resumeIndex != -1) {
                System.out.println("Resume with uuid : " + uuid + " got!");
                return storage[resumeIndex];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex != -1) {
            System.out.println("Resume with uuid : " + uuid + " deleted!");
            storage[resumeIndex] = null;
            size--;
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex);
        } else {
            System.out.println("ERROR. Resume with uuid : " + uuid + " not found!");
        }

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