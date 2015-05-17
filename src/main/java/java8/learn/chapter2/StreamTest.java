package java8.learn.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by senyuanwang on 15/4/27.
 */
public class StreamTest {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static void testReduce() {
        Stream<Integer> onlyOne = Stream.of(1);
        Optional<Integer> result = onlyOne.reduce(StreamTest::sum);
        result.ifPresent(System.out::println);
    }

    public static void testSplit() {
        String[] parts = "abafa  afae;flj;fa   dfafa".split("[\\P{L}]+");
        System.out.println(String.join(",", parts));
    }

    public static void testCollect() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("/Users/senyuanwang/IdeaProjects/ALG/src/resource/two cities.txt"));

        HashSet<String> distictWords = lines.flatMap(line -> Stream.of(line.split("[\\P{L}]+"))).collect(HashSet::new, HashSet::add, HashSet::addAll);
        System.out.println(distictWords.size());
        System.out.println(distictWords);
    }

    public static void testCollectToMap() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

        Map<String, Set<String>> languageNames = locales.collect(
                Collectors.toMap(
                        l -> l.getDisplayLanguage(),
                        l -> Collections.singleton(l.getDisplayLanguage(l)),
                        (existing, newValue) -> {
                            Set<String> r = new HashSet<>(existing);
                            r.addAll(newValue);
                            return r;
                        }));

        System.out.println(languageNames);
    }

    public static void main(String[] args) throws IOException {
//        testSplit();
//        testCollect();
//        testCollectToMap();
//        testRandom();
        testZip();
    }

    public static void testRandom() {
        LongStream randoms = random(25214903917L, 11, 1 << 49);
        randoms.limit(100).forEach(x -> System.out.print(x + " > "));
    }

    public static LongStream random(long a, long c, long m) {
        return LongStream.iterate(1, x -> (a * x + c) / m);
    }

    public static <T> boolean isFinite(Stream<T> stream) {
        return false;
    }

    public static void testZip() {
        Stream<Integer> as = Stream.iterate(1, x -> x * 2).limit(100);
        Stream<Integer> bs = Stream.iterate(1, x -> x * 3).limit(100);

        Stream<Integer> cs = zip(as, bs).limit(100);

        cs.forEach(x -> System.out.print(x + " -> "));
    }

    public static <T> Stream<T> zip(Stream<T> a, Stream<T> b) {
//        Objects.requireNonNull(zipper);
        @SuppressWarnings("unchecked")
        Spliterator<T> aSpliterator = (Spliterator<T>) Objects.requireNonNull(a).spliterator();
        @SuppressWarnings("unchecked")
        Spliterator<T> bSpliterator = (Spliterator<T>) Objects.requireNonNull(b).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int both = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);
        int characteristics = both;

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<T> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<T> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<T> cIterator = new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public T next() {
                T t = null;
                if (index == 0) {
                    t = aIterator.next();
                } else if (index == 1) {
                    t = bIterator.next();
                }
                index = (index + 1) % 2;
                return t;
            }
        };

        Spliterator<T> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }

    public static <T> ArrayList<T> joinStreams(Stream<ArrayList<T>> stream) {
        return stream.reduce(new ArrayList<T>(), (a, b) -> {
            a.addAll(b);
            return a;
        });
    }

    public static <T> ArrayList<T> joinStreams1(Stream<ArrayList<T>> stream) {
        return stream.collect(Collectors.reducing((a, b) -> {
            a.addAll(b);
            return a;
        })).orElse(new ArrayList<T>());
    }

    public static <T> ArrayList<T> joinStreams2(Stream<ArrayList<T>> stream) {
        return stream.collect(ArrayList<T>::new, ArrayList<T>::addAll, ArrayList<T>::addAll);
    }
}
