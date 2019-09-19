package sb.livecollection;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;

public class LiveList<T> extends LiveCollection<T, List<T>> implements List<T> {

    @Override
    protected List<T> newEmptyCollection() {
        return new ArrayList<>();
    }

    @SafeVarargs
    public LiveList(T... defaultItems) {
        super(new ArrayList<>(Arrays.asList(defaultItems)));
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends T> c) {
        boolean result = getValue().addAll(index, c);
        notifyDataChanged();

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void replaceAll(@NonNull UnaryOperator<T> operator) {
        getValue().replaceAll(operator);
        notifyDataChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sort(@Nullable Comparator<? super T> c) {
        getValue().sort(c);
        notifyDataChanged();
    }

    @Override
    public T get(int index) {
        return getValue().get(index);
    }

    @Override
    public T set(int index, T element) {
        T result = getValue().set(index, element);
        notifyDataChanged();

        return result;
    }

    @Override
    public void add(int index, T element) {
        getValue().add(index,element);
        notifyDataChanged();
    }

    @Override
    public T remove(int index) {
        T result = getValue().remove(index);
        notifyDataChanged();

        return result;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return getValue().indexOf(o);
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return getValue().lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(int index) {
        final ListIterator<T> listIterator = getValue().listIterator(index);

        return new ListIterator<T>() {
            @Override
            public boolean hasNext() {
                return listIterator.hasNext();
            }

            @Override
            public T next() {
                return listIterator.next();
            }

            @Override
            public boolean hasPrevious() {
                return listIterator.hasPrevious();
            }

            @Override
            public T previous() {
                return listIterator.previous();
            }

            @Override
            public int nextIndex() {
                return listIterator.nextIndex();
            }

            @Override
            public int previousIndex() {
                return listIterator.previousIndex();
            }

            @Override
            public void remove() {
                listIterator.remove();
                notifyDataChanged();
            }

            @Override
            public void set(T t) {
                listIterator.set(t);
                notifyDataChanged();
            }

            @Override
            public void add(T t) {
                listIterator.add(t);
                notifyDataChanged();
            }
        };
    }

    @NonNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return getValue().subList(fromIndex, toIndex);
    }
}
