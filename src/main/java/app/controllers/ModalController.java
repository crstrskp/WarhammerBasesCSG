package app.controllers;

import io.javalin.Javalin;

public class ModalController {

    public static void addRoutes(Javalin app)
    {
        app.get("/modal", ctx -> ctx.render("modal.html"));

    }
}

