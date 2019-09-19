package sb.livecollection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class LiveSet<E> extends LiveCollection<E, Set<E>> implements Set<E> {

    @Override
    protected Set<E> newEmptyCollection() {
        return new HashSet<>();
    }

    @SafeVarargs
    public LiveSet(E... items) {
        super(new HashSet<>(Arrays.asList(items)));
    }
}
