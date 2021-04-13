import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int position = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].uuid != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                position = i;
                size--;
            }
        }
        System.arraycopy(storage, position, storage, position, size - position);

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
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
        return size;
    }

}