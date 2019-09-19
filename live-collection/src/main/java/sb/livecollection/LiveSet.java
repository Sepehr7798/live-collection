package sb.livecollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LiveSet<T> extends LiveCollection<T, Set<T>> implements Set<T> {

    @Override
    protected Set<T> newEmptyCollection() {
        return new HashSet<>();
    }

    public LiveSet(T... defaultItems) {
        super(new HashSet<>(Arrays.asList(defaultItems)));
    }
}
