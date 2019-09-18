package sb.livecollection;

import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class LiveCollection<T> extends LiveData<Collection<T>> implements Collection<T> {

    private final Collection<T> collection;

    public LiveCollection() {
        collection = Collections.emptyList();
    }

    public LiveCollection(Collection<T> defaultItems) {
        collection = defaultItems;
    }

    @Nullable
    @Override
    public Collection<T> getValue() {
        return collection;
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
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(@NonNull T1[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(T t) {
        boolean result = collection.add(t);
        notifyData();

        return result;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean result = collection.remove(o);
        notifyData();

        return result;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        boolean result = collection.addAll(c);
        notifyData();

        return result;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        boolean result = collection.removeAll(c);
        notifyData();

        return result;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        boolean result = collection.retainAll(c);
        notifyData();

        return result;
    }

    @Override
    public void clear() {
        collection.clear();
        notifyData();
    }

    protected void notifyData() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            setValue(collection);
        } else {
            postValue(collection);
        }
    }
}
