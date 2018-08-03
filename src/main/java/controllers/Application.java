package controllers;

/**
 * @author  Ammarah
 * @since 5/11/2017.
 */


class Application {
    public static void main(String... args) {
        org.springframework.context.annotation.AnnotationConfigApplicationContext ctx =
                new org.springframework.context.annotation.AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.refresh();

    }
}
