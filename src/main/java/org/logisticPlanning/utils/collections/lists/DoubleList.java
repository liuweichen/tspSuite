package org.logisticPlanning.utils.collections.lists;

/**
 * An implementation of the {@link java.util.ArrayList} functionality
 * backed by an array of double-precision (64-bit) floating point number
 * (double) (double) values. This representation provides the full
 * functionality of a {@link java.util.List}, but uses the space-efficient
 * primitive data array storage. It has additional methods to speed up the
 * access of {@code long} values plus it implements the interface
 * {@link org.logisticPlanning.utils.math.data.collection.IDataCollection},
 * which makes it especially suitable for the collection and processing of
 * mathematical information.
 */
public class DoubleList extends
    _BasicNumericList<java.lang.Number, double[]> {
  /** The serial version uid */
  private static final long serialVersionUID = 1L;

  /**
   * instantiate the {@code DoubleList}
   */
  public DoubleList() {
    this(-1);
  }

  /**
   * Instantiate the {@code DoubleList}
   *
   * @param capacity
   *          the number of valid elements in the data array
   */
  public DoubleList(final int capacity) {
    super();
    this.m_data = ((capacity == 0) ? org.logisticPlanning.utils.utils.EmptyUtils.EMPTY_DOUBLES
        : //
        new double[(capacity > 0) ? capacity
            : BasicArrayList.DEFAULT_LIST_SIZE]);
  }

  /** {@inheritDoc} */
  @Override
  public final java.lang.Double get(final int index) {
    BasicArrayList.checkIndex(index, this.m_size);
    return (java.lang.Double.valueOf(this.m_data[index]));
  }

  /** {@inheritDoc} */
  @Override
  public final java.lang.Double set(final int index,
      final java.lang.Number element) {
    final java.lang.Double old;

    BasicArrayList.checkIndex(index, this.m_size);
    old = java.lang.Double.valueOf(this.m_data[index]);
    this.m_data[index] = ((element).doubleValue());
    this.modCount++;
    return old;
  }

  /** {@inheritDoc} */
  @Override
  public final void add(final int index, final java.lang.Number element) {
    this.add(index, ((element).doubleValue()));
  }

  /** {@inheritDoc} */
  @Override
  public final boolean add(final java.lang.Number element) {
    this.add(((element).doubleValue()));
    return true;
  }

  /**
   * Add/insert the double-precision (64-bit) floating point number
   * (double) (double) at the given index
   *
   * @param index
   *          the index
   * @param element
   *          the double to be added
   */
  public final void add(final int index, final double element) {
    final double[] data;
    final int s;

    BasicArrayList.checkIndex(index, (s = (this.m_size + 1)));
    data = this.ensureFree(1, index);
    data[index] = element;
    this.m_size = s;
    this.modCount++;
  }

  /**
   * Add/insert the double-precision (64-bit) floating point number
   * (double) (double) at the end of the list
   *
   * @param element
   *          the double to be added
   */
  public final void add(final double element) {
    final double[] data;
    final int index;

    index = this.m_size;
    data = this.ensureFree(1, index);
    data[index] = element;
    this.m_size = (index + 1);
    this.modCount++;
  }

  /** {@inheritDoc } */
  @Override
  public final void delete(final int index) {
    final double[] data;
    int s;

    BasicArrayList.checkIndex(index, (s = this.m_size));
    this.m_size = (--s);
    data = this.m_data;
    if (index < s) {
      System.arraycopy(data, index + 1, data, index, s - index);
    }
    this.modCount++;
  }

  /** {@inheritDoc } */
  @Override
  public final void deleteFast(final int index) {
    final double[] data;
    int s;

    BasicArrayList.checkIndex(index, (s = this.m_size));
    this.m_size = (--s);
    data = this.m_data;
    data[index] = data[s];
    this.modCount++;
  }

  /**
   * fill this list with a given value
   *
   * @param value
   *          the value
   */
  public final void fill(final double value) {
    java.util.Arrays.fill(this.m_data, 0, this.m_size, value);
    this.modCount++;
  }

  /**
   * Aggregate: Visit all the data of this list. There is no guarantee
   * about the order in which the elements are visited.
   *
   * @param aggregate
   *          the aggregate
   */
  public final void aggregate(
      final org.logisticPlanning.utils.math.statistics.aggregates.Aggregate aggregate) {
    int i;

    for (i = this.m_size; (--i) >= 0;) {
      aggregate.visitDouble(this.m_data[i]);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final double getDouble(final int index) {
    BasicArrayList.checkIndex(index, this.m_size);
    return this.m_data[index];
  }

  /**
   * Ensure that there are at least {@code size} free {@code double}s
   * starting at {@code index}.
   *
   * @param size
   *          the size
   * @param index
   *          the index
   * @return the internal data array {@link #m_data}. It might be different
   *         from before
   */
  @Override
  protected final double[] ensureFree(final int size, final int index) {
    int grow;
    final int oldSize, newSize;
    double[] data;

    oldSize = this.m_size;
    newSize = (oldSize + size);

    data = this.m_data;
    if (data.length >= newSize) {
      if (index < oldSize) {
        System.arraycopy(data, index, data, (index + size),
            (oldSize - index));
      }
      return data;
    }

    grow = (newSize << 1);
    if (grow < newSize) {
      grow = newSize;
    }

    data = new double[grow];
    if (index < oldSize) {
      System.arraycopy(this.m_data, 0, data, 0, index);
      System.arraycopy(this.m_data, index, data, (index + size),//
          (oldSize - index));
    } else {
      System.arraycopy(this.m_data, 0, data, 0, oldSize);
    }
    this.m_data = data;

    return data;
  }

  /** {@inheritDoc} */
  @Override
  public final double[] toDataArray() {
    final double[] data, ret;
    final int s;

    s = this.m_size;
    if (s <= 0) {
      return org.logisticPlanning.utils.utils.EmptyUtils.EMPTY_DOUBLES;
    }

    data = this.m_data;
    if (s >= data.length) {
      return data.clone();
    }

    ret = new double[s];
    System.arraycopy(data, 0, ret, 0, s);

    return ret;
  }

  /**
   * copy the list's data to an array
   *
   * @param array
   *          the array
   * @param data
   *          the data
   * @param size
   *          the size
   */
  private static final void __toArray(final Object[] array,
      final double[] data, final int size) {
    int i;

    for (i = size; (--i) >= 0;) {
      array[i] = java.lang.Double.valueOf(data[i]);
    }
  }

  /** {@inheritDoc} */
  @Override
  public final Object[] toArray() {
    final Object[] array;

    array = new Object[this.m_size];
    DoubleList.__toArray(array, this.m_data, this.m_size);

    return array;
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("unchecked")
  public final <T> T[] toArray(final T[] a) {
    final T[] arr;
    final int size;

    size = this.m_size;

    if (a.length >= size) {
      arr = a;
      if (a.length > size) {
        arr[size] = null;
      }
    } else {
      arr = ((T[]) (java.lang.reflect.Array.newInstance(a.getClass()
          .getComponentType(), size)));
    }
    DoubleList.__toArray(arr, this.m_data, size);

    return arr;
  }

  /** {@inheritDoc} */
  @Override
  public void sort() {
    java.util.Arrays.sort(this.m_data, 0, this.m_size);
    this.modCount++;
  }
}
