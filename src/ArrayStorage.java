import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].uuid)) {
                    return storage[i];
                }
            }
        return null;
    }

    void delete(String uuid) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = null;
                    size--;
                }
            }
        storage = Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] notNullResume = new Resume[size];

        for (int i = 0; i < size; i++) {
            notNullResume[i] = storage[i];
        }
        return notNullResume;
    }

    int size() {
        return size;
    }

}