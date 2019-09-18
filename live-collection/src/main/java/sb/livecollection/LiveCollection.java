package sb.livecollection;

import android.os.Build;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public abstract class LiveCollection<T, C extends Collection<T>> extends LiveData<C> implements Collection<T> {

    private boolean isNotifyingEnabled = true;

    LiveCollection(@NonNull C defaultItems) {
        notifyDataChanged(defaultItems);
    }

    @NonNull
    @Override
    public C getValue() {
        if (super.getValue() == null)
            throw new NullPointerException("LiveCollection.getValue() must not be null!");

        return super.getValue();
    }

    @Override
    public int size() {
        return getValue().size();
    }

    @Override
    public boolean isEmpty() {
        return getValue().isEmpty();
    }

    @Override
    public boolean contains(@Nullable Object o) {
        return getValue().contains(o);
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return getValue().iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return getValue().toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(@NonNull T1[] a) {
        return getValue().toArray(a);
    }

    @Override
    public boolean add(T t) {
        boolean result = getValue().add(t);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean result = getValue().remove(o);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return getValue().containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        boolean result = getValue().addAll(c);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        boolean result = getValue().removeAll(c);
        notifyDataChanged();

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean removeIf(@NonNull Predicate<? super T> filter) {
        boolean result = getValue().removeIf(filter);
        notifyDataChanged();

        return result;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        boolean result = getValue().retainAll(c);
        notifyDataChanged();

        return result;
    }

    @Override
    public void clear() {
        getValue().clear();
        notifyDataChanged();
    }

    public void disableNotifyingDataChanged() {
        isNotifyingEnabled = false;
    }

    public void enableNotifyingDataChanged(boolean notify) {
        isNotifyingEnabled = true;
        if (notify) notifyDataChanged();
    }

    void notifyDataChanged() {
        notifyDataChanged(getValue());
    }

    private void notifyDataChanged(C collection) {
        if (!isNotifyingEnabled) return;

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            setValue(collection);
        } else {
            postValue(collection);
        }
    }
}
