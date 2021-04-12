import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume.uuid.equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
        removeNulls(storage);
    }

    private static void removeNulls(Resume[] strs) {
        int i = 0;
        int j = strs.length - 1;
        while (i <= j) {
            if (strs[j] == null) {
                --j;
            } else if (strs[i] != null) {
                ++i;
            } else {
                strs[i] = strs[j];
                strs[j] = null;
                ++i;
                --j;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {  // создать новый масив пройти по старому все обьекты что не нал добавить в масив и вернуть
        int count = 0;
        Resume[] notNullResume = new Resume[storage.length];

        for (Resume resume : storage) {
            if( resume != null){
                notNullResume[count] = resume;
                count++;
            }
        }
        return notNullResume;
    }

    int size() {
        int count = 0;

        for (Resume resume : storage) {
            if(resume != null) {
                count++;
            }
        }
        return count;
    }

}