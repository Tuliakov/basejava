package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIndex = findResumeIndex(resume.getUuid());
        if (resumeIndex != -1) {
            storage[resumeIndex] = resume;
            System.out.println("Resume with uuid : " + resume.getUuid() + " updated!");
        } else {
            System.out.println(" ERROR. Resume with uuid : " + resume.getUuid() + " not found!");
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
        if (resumeIndex == -1 && size < storage.length) {
            storage[size] = resume;
            System.out.println("Resume with uuid : " + resume.getUuid() + " saved!");
            size++;
        } else {
            System.out.println("ERROR. Resume already exits! ");
        }
    }

    public Resume get(String uuid) {
        if (uuid != null) {
            if (findResumeIndex(uuid) != -1) {
                System.out.println("Resume with uuid : " + uuid + " got!");
                return storage[findResumeIndex(uuid)];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = findResumeIndex(uuid);
        if (resumeIndex != -1) {
            System.out.println("Resume with uuid : " + uuid + " deleted!");
            storage[findResumeIndex(uuid)] = null;
            System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex);
            size--;
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