package br.com.macorin.spark.infra.configs;

public class Environment {

    protected static final int DEFAULT_MAX_RESULTS = -1;

    protected static final String MAX_RESULTS = "MAX_RESULTS";

    private int maxResults;

    private static Environment instance;

    private Environment() {
    }

    public static Environment getInstance() {
        if (instance == null) {
            instance = new Environment();
        }
        return instance;
    }

    public void build() {
        this.maxResults = loadMaxResultsFromEnv();
    }

    protected int loadMaxResultsFromEnv() {
        if (System.getenv(MAX_RESULTS) != null) {
            return Integer.parseInt(System.getenv(MAX_RESULTS));
        }
        return DEFAULT_MAX_RESULTS;
    }

    public int getMaxResults() {
        return this.maxResults;
    }
}
