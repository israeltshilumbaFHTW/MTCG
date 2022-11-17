package at.fhtw.sampleapp.service.packages;


import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.http.Method;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.httpserver.server.Service;
import at.fhtw.sampleapp.service.packages.PackageController;

public class PackageService implements Service {
    private final PackageController packageController;

    public PackageService() {
        this.packageController = new PackageController(new PackageDAL());
    }

    @Override
    public Response handleRequest(Request request) {
            if (request.getMethod() == Method.POST) {
                return  this.packageController.addPackage(request);
            }

            return new Response(
                    HttpStatus.BAD_REQUEST,
                    ContentType.JSON,
                    "[]"
            );
    }
}
