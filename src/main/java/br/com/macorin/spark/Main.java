package br.com.macorin.spark;

import br.com.macorin.spark.controllers.CrawlerController;
import br.com.macorin.spark.infra.configs.Environment;
import br.com.macorin.spark.infra.exceptions.InvalidArgumentException;
import br.com.macorin.spark.infra.exceptions.RequiredArgumentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        LOGGER.info("Iniciando a aplicação");
        checkEnvironment();
        controllers();
    }
    
    private static void checkEnvironment() {
        LOGGER.info("Carregando as variaveis de ambiente");
        try {
            Environment.getInstance().build();
        } catch (RequiredArgumentException | InvalidArgumentException e) {
            LOGGER.error("Erro ao carregar argumentos", e);
            System.exit(1);
        }
    }

    private static void controllers() {
        LOGGER.info("Criando controllers");
        new CrawlerController();
    }
}
