package edu.harvard.we99.services;

import edu.harvard.we99.domain.Experiment;
import edu.harvard.we99.domain.Plate;
import edu.harvard.we99.domain.PlateDimension;
import edu.harvard.we99.domain.PlateType;
import edu.harvard.we99.services.experiments.ExperimentResource;
import edu.harvard.we99.util.ClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import javax.ws.rs.core.Response;
import java.net.URL;

import static edu.harvard.we99.test.BaseFixture.name;
import static org.junit.Assert.assertEquals;

/**
 * @author mford
 */
public class PlateResultsFixture {
    protected final ExperimentService experimentService;
    protected final PlateType plateType;

    public PlateResultsFixture() throws Exception {
        URL url = new URL(WebAppIT.WE99_URL);
        ClientFactory cf = new ClientFactory(url, WebAppIT.WE99_EMAIL, WebAppIT.WE99_PW);

        // install some plate types
        PlateTypeService plateTypeService = cf.create(PlateTypeService.class);
        plateType = plateTypeService.create(
                new PlateType()
                        .withDim(new PlateDimension(10, 10))
                        .withName(name("pt"))
                        .withManufacturer("Foo Inc.")
        );
        experimentService = cf.create(ExperimentService.class);
    }

    protected Experiment createExperiment() throws Exception {
        return experimentService.create(new Experiment(name("experiment")));
    }

    protected Plate createPlate(Experiment experiment) {
        ExperimentResource er = experimentService.getExperiment(experiment.getId());
        return er.getPlates().create(new Plate(name("plate"),
                plateType));
    }

    protected Response postResults(Plate plate, String file) {
        String path = String.format("/experiment/%d/plates/%d/results",
                plate.getExperiment().getId(),
                plate.getId());
        WebClient client = WebClient.create(WebAppIT.WE99_URL + path,
                WebAppIT.WE99_EMAIL, WebAppIT.WE99_PW, null);
        client.type("multipart/form-data");
        ContentDisposition cd = new ContentDisposition("attachment;filename=results.csv");
        Attachment att = new Attachment("file", getClass().getResourceAsStream(file), cd);
        Response response = client.post(new MultipartBody(att));
        assertEquals(200, response.getStatus());
        return response;
    }
}
