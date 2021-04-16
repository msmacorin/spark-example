package br.com.macorin.spark.controllers;

import br.com.macorin.spark.domain.dtos.requests.CrawlerCreateDTO;
import br.com.macorin.spark.infra.exceptions.FieldValidationException;
import br.com.macorin.spark.infra.exceptions.ResourceNotFoundException;
import br.com.macorin.spark.services.CrawlerService;

import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;
import static br.com.macorin.spark.domain.utils.JsonUtil.*;

public class CrawlerController {

    public CrawlerController() {
        CrawlerService crawlerService = new CrawlerService();

        get("/crawl/:id", (req, res) -> crawlerService.find(req.params("id")), json());
        post("/crawl", (req, res) -> crawlerService.add(toObject(req.body(), CrawlerCreateDTO.class)), json());
        exception(FieldValidationException.class, (e, req, res) -> {
            res.status(HttpStatus.BAD_REQUEST_400);
            res.body(e.getMessage());
        });
        exception(ResourceNotFoundException.class, (e, req, res) -> {
            res.status(HttpStatus.NOT_FOUND_404);
            res.body(e.getMessage());
        });
        after((req, res) -> res.type("application/json"));
    }
}
