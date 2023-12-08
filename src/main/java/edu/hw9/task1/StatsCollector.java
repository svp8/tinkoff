package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;

public class StatsCollector {
    private final List<Stat> stats;
    private final ExecutorService executorService;

    public StatsCollector(int pool) {
        this.stats = Collections.synchronizedList(new ArrayList<>());
        this.executorService = Executors.newFixedThreadPool(pool);
    }

    public void push(String name, double[] data) {
        if (executorService.isShutdown()) {
            throw new RuntimeException("ExecutorService is stopped");
        }
        Runnable process = () -> {
            List<Double> metric = DoubleStream.of(data).boxed().toList();
            double max = metric.stream().max(Double::compareTo).get();
            double min = metric.stream().min(Double::compareTo).get();
            double sum = metric.stream().mapToDouble(Double::doubleValue).sum();
            double avg = sum / metric.size();
            stats.add(new Stat(name, max, avg, sum, min));
        };
        executorService.execute(process);
    }

    public List<Stat> getStats() {
        executorService.close();
        return Collections.unmodifiableList(stats);
    }

}
