package io.github.hingbong.shoot.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleConcurrentHashSet<E> extends AbstractSet<E> {

  private static final Object PRESENT = new Object();
  private final transient ConcurrentHashMap<E, Object> map;

  public SimpleConcurrentHashSet(int initialCapacity) {
    map = new ConcurrentHashMap<>(initialCapacity);
  }

  public Iterator<E> iterator() {
    return map.keySet().iterator();
  }

  public int size() {
    return map.size();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public boolean add(E e) {
    return map.put(e, PRESENT) == null;
  }

  public boolean remove(Object o) {
    return map.remove(o) == PRESENT;
  }

  public void clear() {
    map.clear();
  }
}
