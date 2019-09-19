package sb.livecollection;

import android.os.Build;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public abstract class LiveCollection<E, C extends Collection<E>>
        extends LiveData<C> implements Collection<E> {

    protected abstract C newEmptyCollection();

    protected final C collection;

    protected LiveCollection(C collection) {
        this.collection = collection;
    }

    @Nullable
    @Override
    public C getValue() {
        C c = newEmptyCollection();
        c.addAll(collection);
        return c;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return collection.contains(o);
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return collection.containsAll(c);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(@Nullable Object o) {
        return collection.equals(o);
    }

    @Override
    public int hashCode() {
        return collection.hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Spliterator<E> spliterator() {
        return collection.spliterator();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Stream<E> stream() {
        return collection.stream();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Stream<E> parallelStream() {
        return collection.parallelStream();
    }

    @Override
    public boolean add(E e) {
        boolean result = collection.add(e);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean result = collection.remove(o);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends E> c) {
        boolean result = collection.addAll(c);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        boolean result = collection.removeAll(c);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        boolean result = collection.retainAll(c);
        notifyDataChanged();

        return result;
    }

    @Override
    public void clear() {
        collection.clear();
        notifyDataChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean removeIf(@NonNull Predicate<? super E> filter) {
        boolean result = collection.removeIf(filter);
        notifyDataChanged();

        return result;
    }

    protected void notifyDataChanged() {
        if (Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            setValue(collection);
        } else {
            postValue(collection);
        }
    }
}
