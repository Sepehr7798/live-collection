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

@SuppressWarnings("unused")
public class LiveList<E> extends LiveCollection<E, List<E>> implements List<E> {

    @Override
    protected List<E> newEmptyCollection() {
        return new ArrayList<>();
    }

    @SafeVarargs
    public LiveList(E... items) {
        super(new ArrayList<>(Arrays.asList(items)));
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends E> c) {
        boolean result = collection.addAll(index, c);
        notifyDataChanged();

        return result;
    }

    @Override
    public E get(int index) {
        return collection.get(index);
    }

    @Override
    public E set(int index, E element) {
        E result = collection.set(index, element);
        notifyDataChanged();

        return result;
    }

    @Override
    public void add(int index, E element) {
        collection.add(index, element);
        notifyDataChanged();
    }

    @Override
    public E remove(int index) {
        E result = collection.remove(index);
        notifyDataChanged();

        return result;
    }

    @Override
    public int indexOf(@Nullable Object o) {
        return collection.indexOf(o);
    }

    @Override
    public int lastIndexOf(@Nullable Object o) {
        return collection.lastIndexOf(o);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int index) {
        final ListIterator<E> listIterator = collection.listIterator(index);

        return new ListIterator<E>() {
            @Override
            public boolean hasNext() {
                return listIterator.hasNext();
            }

            @Override
            public E next() {
                return listIterator.next();
            }

            @Override
            public boolean hasPrevious() {
                return listIterator.hasPrevious();
            }

            @Override
            public E previous() {
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
            public void set(E e) {
                listIterator.set(e);
                notifyDataChanged();
            }

            @Override
            public void add(E e) {
                listIterator.add(e);
                notifyDataChanged();
            }
        };
    }

    @NonNull
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return collection.subList(fromIndex, toIndex);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void replaceAll(@NonNull UnaryOperator<E> operator) {
        collection.replaceAll(operator);
        notifyDataChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void sort(@Nullable Comparator<? super E> c) {
        collection.sort(c);
        notifyDataChanged();
    }
}
